package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.events.WarningEvent;
import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.dtos.SowingRequestDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            List<WarningEvent> warnings = cepService.getWarningsForStorage(request.getStorageId());
            for (WarningEvent w : warnings) {
                session.insert(w);
            }
            session.fireAllRules();

            session.getObjects().forEach(obj -> {
                System.out.println("FACT: " + obj.getClass().getSimpleName() + " -> " + obj);
            });
            session.getObjects().forEach(obj -> {
                if (obj instanceof SeedQuality) {
                    SeedQuality sq = (SeedQuality) obj;
                    System.out.println("SeedQuality: " + sq.getSeedStatus() + " - " + sq.getReason());
                }
                if (obj instanceof SoilCondition) {
                    SoilCondition sc = (SoilCondition) obj;
                    System.out.println("SoilCondition: " + sc.getSoilStatus() + " - " + sc.getReason());
                }

                if (obj instanceof SowingDecision) {
                    SowingDecision sd = (SowingDecision) obj;
                    System.out.println("SowingDecision: " + sd.getCanSow() + " - " + sd.getExplanation());
                }
            });

            return extractDecision(session);

        } finally {
            session.dispose();
        }
    }

    //helpers
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

        StorageParameters storageParameters = new StorageParameters(
                request.getStorageId(),
                request.getStorageTemperature(),
                request.getStorageHumidity(),
                request.isPestsPresent()
        );

        SeedQuality seedQuality = new SeedQuality();
        SoilCondition soilCondition = new SoilCondition();
        SowingDecision sowingDecision = new SowingDecision();

        session.insert(seedParameters);
        session.insert(soilParameters);
        session.insert(storageParameters);
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

}