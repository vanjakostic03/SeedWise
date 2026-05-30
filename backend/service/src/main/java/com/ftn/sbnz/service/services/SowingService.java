package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.WarningEvent;
import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.dtos.SowingRequestDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.api.runtime.rule.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SowingService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private CepService cepService;


    public SowingDecision evaluateSowing(SowingRequestDTO request) {

        KieSession session = kieContainer.newKieSession("seedWiseKSession");

        try {

            insertFacts(session, request);
            insertCepWarnings(session, request.getStorageId());
            insertCepWarnings(session, request.getParcelId());
            session.fireAllRules();

//            session.getObjects().forEach(obj -> {
//                System.out.println("FACT: " + obj.getClass().getSimpleName() + " -> " + obj);
//            });
//            session.getObjects().forEach(obj -> {
//                if (obj instanceof SeedQuality) {
//                    SeedQuality sq = (SeedQuality) obj;
//                    System.out.println("SeedQuality: " + sq.getSeedStatus() + " - " + sq.getReason());
//                }
//                if (obj instanceof SoilCondition) {
//                    SoilCondition sc = (SoilCondition) obj;
//                    System.out.println("SoilCondition: " + sc.getSoilStatus() + " - " + sc.getReason());
//                }
//
//                if (obj instanceof SowingDecision) {
//                    SowingDecision sd = (SowingDecision) obj;
//                    System.out.println("SowingDecision: " + sd.getCanSow() + " - " + sd.getExplanation());
//                }
//            });

            return extractDecision(session);

        } finally {
            session.dispose();
        }
    }


    public List<String> explainSowing(SowingRequestDTO request) {
        KieSession session = kieContainer.newKieSession("seedWiseKSession");

        try {
            insertFacts(session, request);
            insertCepWarnings(session, request.getStorageId());
            insertCepWarnings(session, request.getParcelId());
            insertBackwardGoalTree(session);

            session.fireAllRules();

            session.getObjects(o -> o instanceof GoalStatus)
                    .forEach(System.out::println);
            session.getObjects(o -> o instanceof WarningEvent)
                    .forEach(w -> {
                        WarningEvent we = (WarningEvent) w;
                        System.out.println(
                                we.getType() + " | " +
                                        we.getMessage()
                        );
                    });

            QueryResults results = session.getQueryResults(
                    "failedRequirement",
                    "sowing",
                    Variable.v,
                    Variable.v
            );

            List<String> explanations = new ArrayList<>();

            for (QueryResultsRow row : results) {
                String failedGoal = (String) row.get("failedGoal");
                String reason = (String) row.get("reason");
                explanations.add(failedGoal + ": " + reason);

            }

            return explanations;
        } finally {
            session.dispose();
        }
    }

    //helpers

    private void insertCepWarnings(KieSession session, Long entityId) {
        if (entityId == null) return;

        List<WarningEvent> warnings = cepService.getWarningsForEntity(entityId);
        for (WarningEvent warning : warnings) {
            session.insert(warning);
        }
    }

    public void insertFacts(KieSession session, SowingRequestDTO request){
        SeedParameters seedParameters = new SeedParameters(
                request.getSeriesId(),
                request.getSeedMoisture(),
                request.getPurity(),
                request.getThousandGrainMass(),
                request.getGermination(),
                request.getGerminationEnergy(),
                request.getFusarium(),
                request.isBiologicalImpurities(),
                request.getAgeYears()
        );

        SoilParameters soilParameters = new SoilParameters(
                request.getParcelId(),
                request.getSoilTemperature(),
                request.getSoilMoisture(),
                request.isInsectsPresent(),
                request.isPlowed(),
                request.isFertilized(),
                request.getPh(),
                request.getAirTemperature()
        );



        SeedQuality seedQuality = new SeedQuality();
        SoilCondition soilCondition = new SoilCondition();
        SowingDecision sowingDecision = new SowingDecision();

        session.insert(seedParameters);
        session.insert(soilParameters);
        session.insert(seedQuality);
        session.insert(soilCondition);
        session.insert(sowingDecision);
    }


    private SowingDecision extractDecision(KieSession kieSession) {
        return kieSession.getObjects(o -> o instanceof SowingDecision)
                .stream()
                .map(o -> (SowingDecision) o)
                .findFirst()
                .orElseGet(() -> {
                    SowingDecision fallback = new SowingDecision();
                    fallback.setExplanation("No decision generated");
                    return fallback;
                });
    }

    private void insertBackwardGoalTree(KieSession session) {
        session.insert(new GoalDependency("sowing", "seed-quality"));
        session.insert(new GoalDependency("sowing", "soil-condition"));
        session.insert(new GoalDependency("sowing", "storaging"));

        session.insert(new GoalDependency("seed-quality", "moisture"));
        session.insert(new GoalDependency("seed-quality", "purity"));
        session.insert(new GoalDependency("seed-quality", "thousandGrainMass"));
        session.insert(new GoalDependency("seed-quality", "germination"));
        session.insert(new GoalDependency("seed-quality", "germinationEnergy"));
        session.insert(new GoalDependency("seed-quality", "fusarium"));
        session.insert(new GoalDependency("seed-quality", "biologicalImpurities"));
        session.insert(new GoalDependency("seed-quality", "ageYears"));

        session.insert(new GoalDependency("soil-condition", "soilTemperature"));
        session.insert(new GoalDependency("soil-condition", "soilMoisture"));
        session.insert(new GoalDependency("soil-condition", "air-temperature"));
        session.insert(new GoalDependency("soil-condition", "ph"));
        session.insert(new GoalDependency("soil-condition", "pestsPresent"));
        session.insert(new GoalDependency("soil-condition", "plowed"));
        session.insert(new GoalDependency("soil-condition", "fertilized"));

        session.insert(new GoalDependency("storaging", "STORAGE_OVERHEAT"));
        session.insert(new GoalDependency("storaging", "STORAGE_HUMIDITY"));
        session.insert(new GoalDependency("storaging", "STORAGE_PEST"));
        session.insert(new GoalDependency("storaging", "SOIL_OVERMOISTURE"));
        session.insert(new GoalDependency("storaging", "SOIL_DRY"));
        session.insert(new GoalDependency("storaging", "SOIL_COLD"));
    }

}