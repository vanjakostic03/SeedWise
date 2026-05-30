package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;

public class GoalDependency {           //veza izmedju cilja i podcilj
    @Position(0)
    private String goal;
    @Position(1)
    private String requirement;

    public GoalDependency() {}

    public GoalDependency(String goal, String requirement) {
        this.goal = goal;               //cilj npr. setvs
        this.requirement = requirement; //zahtev npr. kvalitet semena
    }

    public String getGoal() {
        return goal;
    }

    public String getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return "GoalDependency{" +
                "goal='" + goal + '\'' +
                ", requirement='" + requirement + '\'' +
                '}';
    }
}
