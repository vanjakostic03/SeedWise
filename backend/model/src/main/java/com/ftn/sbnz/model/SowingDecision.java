package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SowingDecision {
    private String parcelId;
    private String seedSeriesId;
    private boolean canSow = false;
    private String explanation = "";
}
