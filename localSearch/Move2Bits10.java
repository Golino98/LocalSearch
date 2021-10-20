package localSearch;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class Move2Bits10 implements Move
{
	private InstanceKnapsack inst;
	
	public Move2Bits10(Instance inst)
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
				for(int j : sol.getIDs())
				{
					if(inst.getWeight(i)+sk.getTotalWeight()-inst.getWeight(j) < inst.getCapacity() && sol.getValue()+inst.getProfit(i)-inst.getProfit(j)>currentSol.getValue())
					{
						Set<Integer> copy = sk.getIDs().stream().collect(Collectors.toSet());
						copy.add(i);
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
		System.out.println("Feasible candidate solutions checked: "+countSol);
		return currentSol;
	}
}