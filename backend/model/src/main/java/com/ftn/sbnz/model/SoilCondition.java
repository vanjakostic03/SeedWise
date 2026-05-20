package com.ftn.sbnz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoilCondition {
    private String parcelId;
    private double temperature;
    private double moisture;
    private boolean insectsPresent;
    private boolean plowed;
    private boolean fertilized;
    private double ph;
    // sta sa temperaturom vazduha?

    private boolean suitable = true;
    private String unsuitableReason = "";
}
