package localSearch;

public class Main {

    public static void main(String[] args) {
        InstanceReader r = new InstanceReaderKnapsack();
        Instance inst = r.read(".\\inst.txt");
        double infeasWeight = 100;

        //	localSearch.Policy policy = new localSearch.PolicyFirstImprovement(inst, infeasWeight);
        //	localSearch.Policy policy = new localSearch.PolicySecondImprovement(inst, infeasWeight);
        Policy policy = new PolicyBestImprovement();
        Move move = new Move1Bit(inst);
        //	localSearch.Move move = new localSearch.Move2Bits10(inst);
        //	localSearch.Move move = new localSearch.Move3Bits110(inst);
        //	localSearch.Move move = new localSearch.Move3Bits110Infeasible(inst, infeasWeight);
        //	localSearch.Move move = new localSearch.Move4Bits1100(inst);
        //	localSearch.Move move = new localSearch.Move5Bits11100(inst);
        InitialSolutionHeuristic ih = new InitialSolutionHeuristicKnapsack(inst);
        Solution initSol = ih.run();
        System.out.println("Initial solution value: " + initSol.getValue());

        LocalSearch ls = new LocalSearch(inst, initSol, move, policy, 30);
        Solution bestSol = ls.run();

        System.out.println("Final solution value: " + bestSol.getValue()
                + " - Infeasibility value: " + Math.max(0, (((SolutionKnapsack) bestSol).getTotalWeight() - ((InstanceKnapsack) inst).getCapacity())));
    }
}