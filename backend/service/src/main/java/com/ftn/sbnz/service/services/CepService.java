package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.events.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

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

    public List<WarningEvent> getWarningsForStorage(Long storageId) {
        return cepSession.getObjects()
                .stream()
                .filter(WarningEvent.class::isInstance)
                .map(WarningEvent.class::cast)
                .filter(w -> w.getEntityId().equals(storageId))
                .toList();
    }
}