package com.ftn.sbnz.model.models;


import com.ftn.sbnz.model.enums.SeedStatus;

public class SeedQuality {

    private SeedStatus seedStatus = SeedStatus.NEEDS_PROCESSING;
    private String reason = "";

    public SeedQuality() {
        this.seedStatus = SeedStatus.NEEDS_PROCESSING;
        this.reason = "";
    }

    public SeedQuality(SeedStatus seedStatus, String reason) {
        this.seedStatus = seedStatus;
        this.reason = reason;
    }

    public SeedStatus getSeedStatus() {
        return seedStatus;
    }

    public void setSeedStatus(SeedStatus seedStatus) {
        this.seedStatus = seedStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
