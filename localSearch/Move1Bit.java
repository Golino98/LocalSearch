package localSearch;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class Move1Bit implements Move {
    private InstanceKnapsack inst;

    public Move1Bit(Instance inst) {
        this.inst = (InstanceKnapsack) inst;
    }

    @Override
    public Solution run(Solution sol, Policy policy, int timeLimit) {
        Instant start = Instant.now();
        SolutionKnapsack sk = (SolutionKnapsack) sol;
        SolutionKnapsack currentSol = sk;
        int countSol = 0;
        for (int i : inst.getIds()) {
            if (!sol.getIDs().contains(i) && inst.getWeight(i) + sk.getTotalWeight() < inst.getCapacity() && sol.getValue() + inst.getProfit(i) > currentSol.getValue()) {
                countSol++;
                Set<Integer> copy = sk.getIDs().stream().collect(Collectors.toSet());
                copy.add(i);
                currentSol = new SolutionKnapsack(inst, copy);
                if (policy.check(sol, currentSol, currentSol) || Duration.between(start, Instant.now()).getSeconds() >= timeLimit)
                    break;
            }
        }
        System.out.println("Feasible candidate solutions checked: " + countSol);
        return currentSol;
    }
}