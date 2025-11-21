package Fuzzy_Logic.model;

import java.util.ArrayList;
import java.util.List;

public class LinguisticVariable {
    private String name;
    private double minRange;
    private double maxRange;
    private List<FuzzySet> fuzzySets;
    private double crispValue;

    public LinguisticVariable(String name, double minRange, double maxRange) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.fuzzySets = new ArrayList<>();
    }

    public void addFuzzySet(FuzzySet fuzzySet) {
        fuzzySets.add(fuzzySet);
    }

    public double getMembershipValue(String SetName) {
        return 0.0;
    }

    public String getName() {
        return name;
    }
    public double getMinRange() {
        return minRange;
    }
    public double getMaxRange() {
        return maxRange;
    }
    public List<FuzzySet> getFuzzySets() {
        return fuzzySets;
    }
    public double getCrispValue() {
        return crispValue;
    }
    public void setCrispValue(double crispValue) {
        this.crispValue = crispValue;
    }
}