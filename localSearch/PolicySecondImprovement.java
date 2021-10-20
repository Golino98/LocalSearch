package localSearch;

public class PolicySecondImprovement implements Policy
{
	private int improvements = 0;
	private InstanceKnapsack inst;
	private double infeasWeight;
	
	public PolicySecondImprovement(Instance inst, double infeasWeight)
	{
		this.inst = (InstanceKnapsack) inst;
		this.infeasWeight = infeasWeight;
	}
	
	@Override
	public boolean check(Solution initSol, Solution currentSol, Solution bestSolution)
	{
		improvements++;
		SolutionKnapsack cs = (SolutionKnapsack)currentSol;
		SolutionKnapsack is = (SolutionKnapsack)initSol;

		if(currentSol.getValue() -infeasWeight*Math.max(0, cs.getTotalWeight()-inst.getCapacity())> initSol.getValue() - infeasWeight*Math.max(0, is.getTotalWeight()-inst.getCapacity()) && improvements == 2)
		{
			improvements = 0;
			return true;
		}
		
		return false;
	}	
}