package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.ParameterStatus;


public class ParameterEvaluation {
    private String parameterName;
    private ParameterStatus status;
    private String reason;

    public ParameterEvaluation(String parameterName, ParameterStatus status, String reason) {
        this.parameterName = parameterName;
        this.status = status;
        this.reason = reason;
    }

    public ParameterEvaluation(){
        this.parameterName = "";
        this.status = ParameterStatus.FAILED;
        this.reason = "";
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public ParameterStatus getStatus() {
        return status;
    }

    public void setStatus(ParameterStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ParameterEvaluation{" +
                "parameterName='" + parameterName + '\'' +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
