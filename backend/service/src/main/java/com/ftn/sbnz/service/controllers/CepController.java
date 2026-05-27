package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.events.*;
import com.ftn.sbnz.model.models.WarningEvent;
import com.ftn.sbnz.service.services.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @PostMapping("/storage-temperature")
    public ResponseEntity<List<WarningEvent>> storageTemperature(@RequestBody StorageTemperatureEvent event) {
        return ResponseEntity.ok(cepService.processStorageTemperature(event));
    }

    @PostMapping("/storage-humidity")
    public ResponseEntity<List<WarningEvent>> storageHumidity(@RequestBody StorageHumidityEvent event) {
        return ResponseEntity.ok(cepService.processStorageHumidity(event));
    }

    @PostMapping("/storage-pest")
    public ResponseEntity<List<WarningEvent>> storagePest(@RequestBody PestDetectionEvent event) {
        return ResponseEntity.ok(cepService.processStoragePest(event));
    }

    @PostMapping("/soil-moisture")
    public ResponseEntity<List<WarningEvent>> soilMoisture(@RequestBody SoilMoistureEvent event) {
        return ResponseEntity.ok(cepService.processSoilMoisture(event));
    }

    @PostMapping("/soil-temperature")
    public ResponseEntity<List<WarningEvent>> soilTemperature(@RequestBody SoilTemperatureEvent event) {
        return ResponseEntity.ok(cepService.processSoilTemperature(event));
    }
}