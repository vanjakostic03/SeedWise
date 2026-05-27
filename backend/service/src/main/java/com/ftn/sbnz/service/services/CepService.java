package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.events.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CepService {

    private final KieSession cepSession;

    public CepService(KieContainer kieContainer) {
        this.cepSession = kieContainer.newKieSession("cepKSession");
    }

    public void addEvent(Object event) {
        cepSession.insert(event);
        cepSession.fireAllRules();
    }

    public List<WarningEvent> processStorageTemperature(StorageTemperatureEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(new Date());
        }
        addEvent(event);
        return getWarningsForEntity(event.getStorageId());
    }

    public List<WarningEvent> processStorageHumidity(StorageHumidityEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(new Date());
        }
        addEvent(event);
        return getWarningsForEntity(event.getStorageId());
    }

    public List<WarningEvent> processStoragePest(PestDetectionEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(new Date());
        }
        addEvent(event);
        return getWarningsForEntity(event.getStorageId());
    }

    public List<WarningEvent> processSoilMoisture(SoilMoistureEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(new Date());
        }
        addEvent(event);
        return getWarningsForEntity(event.getSoilId());
    }

    public List<WarningEvent> processSoilTemperature(SoilTemperatureEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(new Date());
        }
        addEvent(event);
        return getWarningsForEntity(event.getSoilId());
    }



    public List<WarningEvent> getWarningsForEntity(Long storageId) {
        return cepSession.getObjects()
                .stream()
                .filter(WarningEvent.class::isInstance)
                .map(WarningEvent.class::cast)
                .filter(w -> w.getEntityId().equals(storageId))
                .toList();
    }
}