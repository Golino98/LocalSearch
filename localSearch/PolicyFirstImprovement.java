package localSearch;

public class PolicyFirstImprovement implements Policy
{
	private InstanceKnapsack inst;
	private double infeasWeight;
	
	public PolicyFirstImprovement(Instance inst, double infeasWeight)
	{
		this.inst = (InstanceKnapsack) inst;
		this.infeasWeight = infeasWeight;
	}
	
	@Override
	public boolean check(Solution initSol, Solution currentSol, Solution bestSolution)
	{
		SolutionKnapsack cs = (SolutionKnapsack)currentSol;
		SolutionKnapsack is = (SolutionKnapsack)initSol;
		return currentSol.getValue() -infeasWeight*Math.max(0, cs.getTotalWeight()-inst.getCapacity())> initSol.getValue() -infeasWeight*Math.max(0, is.getTotalWeight()-inst.getCapacity());
	}
}