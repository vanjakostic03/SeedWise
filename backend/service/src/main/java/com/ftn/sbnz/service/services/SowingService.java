package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SowingService {

    @Autowired
    private KieContainer kieContainer;

    public SowingDecision evaluateSowing(
            SeedParameters seedParameters,
            SoilParameters soilParameters,
            StorageParameters storageParameters) {

        KieSession kieSession = kieContainer.newKieSession("seedWiseKSession");

        try {
            SeedQuality seedQuality = new SeedQuality();
            SoilCondition soilCondition = new SoilCondition();
            SowingDecision sowingDecision = new SowingDecision();

            kieSession.insert(seedParameters);
            kieSession.insert(soilParameters);
            kieSession.insert(storageParameters);
            kieSession.insert(seedQuality);
            kieSession.insert(soilCondition);
            kieSession.insert(sowingDecision);

            kieSession.fireAllRules();

            kieSession.getObjects().forEach(obj -> {
                System.out.println("FACT: " + obj.getClass().getSimpleName() + " -> " + obj);
            });
            kieSession.getObjects().forEach(obj -> {
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

            return sowingDecision;

        } finally {
            kieSession.dispose();
        }
    }
}