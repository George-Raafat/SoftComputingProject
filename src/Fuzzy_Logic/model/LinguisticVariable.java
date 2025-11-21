package Fuzzy_Logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void setValue(double val) {
        if (val < minRange) val = minRange;
        if (val > maxRange) val = maxRange;
        this.crispValue = val;
    }

    public double getValue() {
        return this.crispValue;
    }

    public double getMembershipDegree(String fuzzySetName) {
        Optional<FuzzySet> set = fuzzySets.stream()
                .filter(fs -> fs.getName().equals(fuzzySetName))
                .findFirst();

        if (set.isPresent()) {
            return set.get().getMembershipDegree(this.crispValue);
        } else {
            throw new IllegalArgumentException("Fuzzy set not found: " + fuzzySetName);
        }
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