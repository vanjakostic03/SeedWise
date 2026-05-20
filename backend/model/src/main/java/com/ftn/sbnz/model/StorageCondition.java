package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StorageCondition {

    private String storageId;
    private double temperature;
    private double humidity;
    private boolean pestsPresent;

    private boolean safe = true;
    private String warningMessage = "";

}
