package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;

public class GoalStatus {           //da li je neki cilj ispunjen
    @Position(0)
    private String goal;            //cilj
    @Position(1)
    private boolean satisfied;
    @Position(2)
    private String reason;

    public GoalStatus() {}

    public GoalStatus(String goal, boolean satisfied, String reason) {
        this.goal = goal;
        this.satisfied = satisfied;
        this.reason = reason;
    }

    public String getGoal() {
        return goal;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public String getReason() {
        return reason;
    }
}
