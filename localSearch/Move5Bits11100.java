package localSearch;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class Move5Bits11100 implements Move
{
	private InstanceKnapsack inst;
	
	public Move5Bits11100(Instance inst)
	{
		this.inst = (InstanceKnapsack)inst;
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
					if(!sol.getIDs().contains(k) && k != i)
					{
						for(int n : inst.getIds())
						{
							if(!sol.getIDs().contains(n) && k != n && i != n)
							{
								for(int j : sol.getIDs())
								{
									for(int m : sol.getIDs())
									{
										if(m != j && inst.getWeight(i)+inst.getWeight(k)+inst.getWeight(n)+sk.getTotalWeight()-inst.getWeight(j) -inst.getWeight(m) < inst.getCapacity() && sol.getValue()+inst.getProfit(i)+inst.getProfit(k)+inst.getProfit(n)-inst.getProfit(j)-inst.getProfit(m)>currentSol.getValue())
										{
											Set<Integer> copy = sk.getIDs().stream().collect(Collectors.toSet());
											copy.add(i);
											copy.add(k);
											copy.add(n);
											copy.remove(j);
											copy.remove(m);
											countSol++;
											currentSol = new SolutionKnapsack(inst, copy);
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
				}
				 
			}
		}
		System.out.println("Feasible candidate solutions checked: "+countSol);
		return currentSol;
	}
}