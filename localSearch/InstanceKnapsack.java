package localSearch;

import java.util.HashSet;
import java.util.Set;

public class InstanceKnapsack implements Instance
{
	private int[] profits;
	private int[] weights;
	private int capacity;
	private Set<Integer> ids;
	
	public InstanceKnapsack(int capacity, int[] profits, int[] weights)
	{
		this.capacity = capacity;
		this.profits = profits;
		this.weights = weights;
		
		ids = new HashSet<>();
		for (int i = 0; i < weights.length; i++)
		{
			ids.add(i);
		}
	}
	
	public int getProfit(int i)
	{
		return profits[i];
	}
	
	public int getWeight(int i)
	{
		return weights[i];
	}
	
	public int getCapacity()
	{
		return capacity;
	}

	@Override
	public Set<Integer> getIds()
	{
		return ids;
	}
}