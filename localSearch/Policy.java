package localSearch;

public interface Policy
{
	public boolean check(Solution initSol, Solution currentSol, Solution bestSolution);
}
