package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.InputReader;

public class Day3 {
	/**
	 * We can simply add the contents of each bag / each half bag to a Set and take the intersection(s) to find the common elements.
	 * O(n) runtime
	 */
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		int sumOfPriorities = 0;
		for (String string : input) {
			List<Integer> stringAsASCII = convertToASCII(string);
			int mid = stringAsASCII.size() / 2;
			List<Integer> firstHalf = stringAsASCII.subList(0, mid);
			List<Integer> secondHalf = stringAsASCII.subList(mid, stringAsASCII.size());
			HashSet<Integer> firstHalfSet = new HashSet<>(firstHalf);
			HashSet<Integer> secondHalfSet = new HashSet<>(secondHalf);
			firstHalfSet.retainAll(secondHalfSet);
			if (!firstHalfSet.isEmpty()) {
				sumOfPriorities += convertToPriority(firstHalfSet.stream().toList().get(0));
			}
		}
		System.out.println(sumOfPriorities);
	}
	
	public void solve2(String path) {
		List<List<String>> input = InputReader.readFileAsNestedListOfStrings(path, 3);
		int sumOfPriorities = 0;
		for (List<String> group : input) {
			List<Integer> firstBag = convertToASCII(group.get(0));
			List<Integer> secondBag = convertToASCII(group.get(1));
			List<Integer> thirdBag = convertToASCII(group.get(2));
			HashSet<Integer> firstSet = new HashSet<>(firstBag);
			HashSet<Integer> secondSet = new HashSet<>(secondBag);
			HashSet<Integer> thirdSet = new HashSet<>(thirdBag);
			firstSet.retainAll(secondSet);
			firstSet.retainAll(thirdSet);
			if (!firstSet.isEmpty()) {
				sumOfPriorities += convertToPriority(firstSet.stream().toList().get(0));
			}
		}
		System.out.println(sumOfPriorities);
	}
	
	private List<Integer> convertToASCII(String input) {
		List<Integer> inputAsASCII = new ArrayList<>();
		for (char c: input.toCharArray()) {
			int ascii = (int) c;
			inputAsASCII.add(ascii);
		}
		return inputAsASCII;
	}
	
	private int convertToPriority(Integer input) {
		if ((input >= 65) && (input <= 90)) {
			return input - 38;
		} else if ((input >= 97) && (input <= 122)) {
			return input - 96;
		} else {
			System.out.println("Error! " + input);
		}
		return 0;
	}
}
