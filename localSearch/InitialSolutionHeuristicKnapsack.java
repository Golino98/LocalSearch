package localSearch;

import java.util.HashSet;
import java.util.Set;


public class InitialSolutionHeuristicKnapsack implements InitialSolutionHeuristic
{
	private InstanceKnapsack inst;
	
	public InitialSolutionHeuristicKnapsack(Instance inst)
	{
		this.inst = (InstanceKnapsack) inst;
	}
	
	@Override
	public Solution run()
	{
		Set<Integer> ids = new HashSet<>();
		int totalWeights = 0;
		for (int i = 0; i < inst.getIds().size(); i++)
		{
			if(totalWeights + inst.getWeight(i) < inst.getCapacity()/3)
			{
				ids.add(i);
				totalWeights += inst.getWeight(i);
			}
		}
		return new SolutionKnapsack(inst, ids);
	}
}