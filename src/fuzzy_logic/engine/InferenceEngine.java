//package fuzzy_logic.engine;
//
//import fuzzy_logic.strategies.defuzzification.DefuzzificationStrategy;
//import fuzzy_logic.strategies.operators.FuzzyOperator;
//
//public abstract class InferenceEngine {
//    protected RuleBase ruleBase;
//    protected FuzzyOperator operator;
//    protected DefuzzificationStrategy defuzzifier;
//
//    public void process() {
//        // 1. Fuzzify inputs (handled inside Variable class usually)
//
//        // 2. Evaluate Rules (Infer)
//        // Calculate firing strength for every rule
//
//        // 3. Aggregate
//        // Combine results of all rules
//
//        // 4. Defuzzify
//        double result = defuzzifier.defuzzify(aggregatedOutput);
//    }
//}