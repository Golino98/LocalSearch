package localSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class InstanceReaderKnapsack implements InstanceReader {
    @Override
    public Instance read(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            List<String> lines = br.lines().collect(Collectors.toList());

            int numItems = Integer.parseInt(lines.get(0).trim());
            int capacity = Integer.parseInt(lines.get(1).trim());
            int[] weights = new int[numItems];
            int[] profits = new int[numItems];
            for (int i = 2; i < 2 + numItems; i++) {
                String[] split = lines.get(i).split("\\s+");
                weights[i - 2] = Integer.parseInt(split[0]);
                profits[i - 2] = Integer.parseInt(split[1]);
            }
            return new InstanceKnapsack(capacity, profits, weights);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}