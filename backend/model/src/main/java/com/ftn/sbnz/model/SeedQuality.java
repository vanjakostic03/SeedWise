package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeedQuality {
    private String seriesId;
    private double moisture;
    private double purity;
    private double thousandGrainMass;
    private double germination;
    private double fusarium;
    private boolean biologicalImpurities;
    private int ageYears;

    private boolean acceptable = true;
    private String rejectionReason = "";

}
