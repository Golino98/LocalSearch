package localSearch;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class Move3Bits110Infeasible implements Move
{
	private InstanceKnapsack inst;
	private double infeasWeight;
	
	public Move3Bits110Infeasible(Instance inst, double infeasWeight)
	{
		this.inst = (InstanceKnapsack)inst;
		this.infeasWeight = infeasWeight;
	}
	
	@Override
	public Solution run(Solution sol, Policy policy, int timeLimit)
	{
		Instant start = Instant.now();
		SolutionKnapsack sk = (SolutionKnapsack) sol;
		SolutionKnapsack currentSol = sk;
		int countSol = 0;
		for(int i : inst.getIds())
		{
			if(!sol.getIDs().contains(i))
			{
				for(int k : inst.getIds())
				{
					if(!sol.getIDs().contains(k) && i != k)
					{
						for(int j : sol.getIDs())
						{
							int infeas = Math.max(0, inst.getWeight(i)+inst.getWeight(k)+sk.getTotalWeight()-inst.getWeight(j) - inst.getCapacity());
							
							if(sol.getValue()+inst.getProfit(i)+inst.getProfit(k)-inst.getProfit(j)-infeas*infeasWeight > currentSol.getValue()-(Math.max(0,currentSol.getTotalWeight()-inst.getCapacity()))*infeasWeight)
							{
								Set<Integer> copy = sk.getIDs().stream().collect(Collectors.toSet());
								copy.add(i);
								copy.add(k);
								copy.remove(j);
								currentSol = new SolutionKnapsack(inst, copy);
								countSol++;
								if(policy.check(sol, currentSol, currentSol) || Duration.between(start, Instant.now()).getSeconds() >= timeLimit)
								{
									System.out.println("Feasible candidate solutions checked: "+countSol);
									return currentSol;						
								}
							}
						}						
					}
				}
				 
			}
		}
		System.out.println("Feasible candidate solutions checked: "+countSol);
		return currentSol;
	}
}