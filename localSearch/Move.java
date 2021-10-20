package localSearch;

public interface Move
{
	public Solution run(Solution sol, Policy policy, int timeLimit);
}
