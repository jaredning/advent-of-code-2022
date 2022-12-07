package solutions;

import java.util.HashMap;
import java.util.HashSet;

import common.InputReader;

public class Day6 {
	public void solve(String path) {
		String input = InputReader.readFileAsString(path);
		char[] characters = input.toCharArray();
		findMarker(characters, 4);
		findMarker(characters, 14);
	}
	
	private void findMarker(char[] input, int markerSize) {
		int left = 0;
		int right = 0;
		int solution = 0;
		HashMap<Character, Integer> characterPositions = new HashMap<>();
		HashSet<Character> seen = new HashSet<>();
		while (left < input.length && right < input.length) {
			if (seen.contains(input[right])) {
				int posOfDuplicate = characterPositions.remove(input[right]);
				characterPositions.put(input[right], right);
				while (left <= posOfDuplicate) {
					seen.remove(input[left]);
					left++;
				}
				left = posOfDuplicate + 1;
				seen.add(input[right]);
				right++;
			} else {
				seen.add(input[right]);
				characterPositions.put(input[right], right);
				right++;
			}
			if (seen.size() == markerSize) {
				solution = right;
				break;
			}
		}
		System.out.println(solution);
	}
}
