package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.events.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ClassObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CepService {

    @Autowired
    private KieContainer kieContainer;

    // CEP sesija je dugotrajna — čuva events tokom vremena
    private KieSession cepSession;

    @Autowired
    public void initCepSession() {
        cepSession = kieContainer.newKieSession("cepKSession");
    }

    public List<WarningEvent> processStorageTemperature(StorageTemperatureEvent event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
        return getWarnings();
    }

    public List<WarningEvent> processStorageHumidity(StorageHumidityEvent event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
        return getWarnings();
    }

    public List<WarningEvent> processPestDetection(PestDetectionEvent event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
        return getWarnings();
    }

    public List<WarningEvent> processSoilMoisture(SoilMoistureEvent event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
        return getWarnings();
    }

    public List<WarningEvent> processSoilTemperature(SoilTemperatureEvent event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
        return getWarnings();
    }

    private List<WarningEvent> getWarnings() {
        Collection<?> warnings = cepSession.getObjects(
                new ClassObjectFilter(WarningEvent.class)
        );
        return warnings.stream()
                .map(w -> (WarningEvent) w)
                .collect(Collectors.toList());
    }
}