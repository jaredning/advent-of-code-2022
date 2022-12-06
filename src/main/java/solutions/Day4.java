package solutions;

import java.util.List;

import common.InputReader;

public class Day4 {
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		int numFullOverlaps = 0;
		int numOverlaps = 0;
		for (String line : input) {
			String[] pairs = line.split(",");
			int[] firstRange = convertStringArrayToIntArray(pairs[0].split("-"));
			int[] secondRange = convertStringArrayToIntArray(pairs[1].split("-"));
			if (rangesFullyOverlap(firstRange, secondRange)) {
				numFullOverlaps++;
			}
			if (rangesOverlap(firstRange, secondRange)) {
				numOverlaps++;
			}
		}
		System.out.println(numFullOverlaps);
		System.out.println(numOverlaps);
	}
	
	private boolean rangesFullyOverlap(int[] firstRange, int[] secondRange) {
		if ((firstRange[0] <= secondRange[0] && firstRange[1] >= secondRange[1]) 
				|| (secondRange[0] <= firstRange[0] && secondRange[1] >= firstRange[1])) {
			return true;
		}
		return false;
	}
	
	private boolean rangesOverlap(int[] firstRange, int[] secondRange) {
		if ((firstRange[1] >= secondRange[0] && firstRange[0] < secondRange[1]) 
				|| (secondRange[1] >= firstRange[0] && secondRange[0] < firstRange[1])) {
			return true;
		}
		return false;
	}
	
	private int[] convertStringArrayToIntArray(String[] array) {
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = Integer.parseInt(array[i]);
		}
		return result;
	}
}
