package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class Day1 {
	/**
	 * We simply sum each sub-list, then sort the list of sums and return the max.
	 * O(n log n) runtime (due to sorting)
	 */
	public void solve(String path) throws Exception {
		List<List<Integer>> input = InputReader.readFileAsNestedListOfInts(path);
		Integer sum;
		List<Integer> listOfSums = new ArrayList<>();
		for (List<Integer> list: input) {
			sum = 0;
			for (Integer num : list) {
				sum+= num;
			}
			listOfSums.add(sum);
		}
		Collections.sort(listOfSums);
		Collections.reverse(listOfSums);
		System.out.println(listOfSums.get(0));
		System.out.println(listOfSums.get(0) + listOfSums.get(1) + listOfSums.get(2));
	}
	
	
}
