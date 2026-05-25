package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.SowingStatus;

public class SowingDecision {
    private Long parcelId;
    private Long seedSeriesId;
    private SowingStatus canSow = SowingStatus.NOT_RECOMMENDED;
    private String explanation = "";


    public SowingDecision() {
        this.parcelId = 0L;
        this.seedSeriesId = 0L;
        this.canSow = SowingStatus.NOT_RECOMMENDED;
        this.explanation = "";
    }

    public SowingDecision(Long parcelId, Long seedSeriesId, SowingStatus canSow, String explanation) {
        this.parcelId = parcelId;
        this.seedSeriesId = seedSeriesId;
        this.canSow = canSow;
        this.explanation = explanation;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Long getSeedSeriesId() {
        return seedSeriesId;
    }

    public void setSeedSeriesId(Long seedSeriesId) {
        this.seedSeriesId = seedSeriesId;
    }

    public SowingStatus getCanSow() {
        return canSow;
    }

    public void setCanSow(SowingStatus canSow) {
        this.canSow = canSow;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
