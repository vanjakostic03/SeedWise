package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.SoilStatus;

public class SoilCondition {

    private SoilStatus soilStatus = SoilStatus.NOT_READY;
    private String reason = "";

    public SoilCondition() {
        this.soilStatus = SoilStatus.NOT_READY;
        this.reason = "";
    }

    public SoilCondition(String reason, SoilStatus soilStatus) {
        this.reason = reason;
        this.soilStatus = soilStatus;
    }

    public SoilStatus getSoilStatus() {
        return soilStatus;
    }

    public void setSoilStatus(SoilStatus soilStatus) {
        this.soilStatus = soilStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
