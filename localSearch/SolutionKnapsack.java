package localSearch;

import java.util.Set;

public class SolutionKnapsack implements Solution
{
	private Set<Integer> ids;
	private InstanceKnapsack inst;
	private int sumWeights;
	private int sumProfits;
	
	public SolutionKnapsack(Instance inst, Set<Integer> ids)
	{
		this.ids = ids;
		this.inst = (InstanceKnapsack) inst;
		sumWeights = -1;
		sumProfits = -1;
	}
	
	@Override
	public Set<Integer> getIDs()
	{
		return ids;
	}

	@Override
	public boolean isFeasible()
	{
		if(sumWeights < 0)
			sumWeights = ids.stream().mapToInt(id -> inst.getWeight(id)).sum();
		
		return sumWeights <= inst.getCapacity();
	}
	
	public int getTotalWeight()
	{
		if(sumWeights < 0)
			sumWeights = ids.stream().mapToInt(id -> inst.getWeight(id)).sum();
		
		return sumWeights;
	}

	@Override
	public int getValue()
	{
		if(sumProfits < 0)
			sumProfits = ids.stream().mapToInt(id -> inst.getProfit(id)).sum();
		
		return sumProfits;
	}
}