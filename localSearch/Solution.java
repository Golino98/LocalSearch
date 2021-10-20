package localSearch;

import java.util.Set;

public interface Solution {
    public Set<Integer> getIDs();
    public boolean isFeasible();
    public int getValue();
}