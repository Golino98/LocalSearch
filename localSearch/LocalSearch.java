package localSearch;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LocalSearch {
    private Instance inst;
    private Solution initSolution;
    private int timeLimit;
    private Instant start;
    private Move move;
    private Policy policy;
    private List<Integer> solutionValues;

    public LocalSearch(Instance inst, Solution initSolution, Move move, Policy policy, int timeLimit) {
        this.inst = inst;
        this.initSolution = initSolution;
        this.timeLimit = timeLimit;
        this.move = move;
        this.policy = policy;
        solutionValues = new ArrayList<Integer>();
    }

    public Solution run() {
        Solution best = initSolution;
        start = Instant.now();
        double elapsedTime = 0;
        Solution currentSol = best;
        int iteration = 0;

        while (elapsedTime < timeLimit) {
            currentSol = move.run(currentSol, policy, (int) (timeLimit - elapsedTime));

            elapsedTime = Duration.between(start, Instant.now()).getSeconds();

            System.out.println("Iteration " + iteration++ + " - Current best objective function value: " + currentSol.getValue());

            if (currentSol.getValue() <= best.getValue()) {
                System.out.println("Local Optimum reached");
                break;
            }
            solutionValues.add(currentSol.getValue());

            if (currentSol.getValue() > best.getValue()) {
                best = currentSol;
            }
        }
        return best;
    }

    public List<Integer> getSolutionValues() {
        return solutionValues;
    }
}