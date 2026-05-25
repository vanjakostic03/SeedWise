package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.SeedParameters;
import com.ftn.sbnz.model.models.SoilParameters;
import com.ftn.sbnz.model.models.SowingDecision;
import com.ftn.sbnz.model.models.StorageParameters;
import com.ftn.sbnz.service.dtos.SowingRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.sbnz.service.services.SowingService;

@RestController
@RequestMapping("/api/sowing")
public class SowingController {
    @Autowired
    private SowingService sowingService;

    @PostMapping("/evaluate")
    public ResponseEntity<SowingDecision> evaluate(@RequestBody SowingRequestDTO request) {

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

        SowingDecision decision = sowingService.evaluateSowing(
                seedParameters, soilParameters, storageParameters
        );

        return ResponseEntity.ok(decision);
    }
}
