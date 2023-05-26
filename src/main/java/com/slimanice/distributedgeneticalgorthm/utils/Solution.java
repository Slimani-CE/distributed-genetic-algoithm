package com.slimanice.distributedgeneticalgorthm.utils;

import jade.core.AID;

public class Solution {
    private AID aid;
    private String solution;
    private int fitness;

    public Solution(AID aid, String solution, int fitness) {
        this.aid = aid;
        this.solution = solution;
        this.fitness = fitness;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
