package solutions;

import java.util.ArrayList;
import java.util.List;

import common.InputReader;

public class Day10 {
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		int cycle = 1;
		int registerX = 1;
		int total = 0;
		List<Integer> interestingValues = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (String command : input) {
			if (command.contains("noop")) {
				checkValue(cycle, registerX, interestingValues, sb);
				cycle++;
			} else {
				checkValue(cycle, registerX, interestingValues, sb);
				cycle++;
				String[] action = command.split(" ");
				checkValue(cycle, registerX, interestingValues, sb);
				int amount = Integer.parseInt(action[1]);
				registerX += amount;
				cycle++;
			}
		}
		for (int i = 0; i < interestingValues.size(); i++) {
			total += interestingValues.get(i) * (20 + 40 * i);
		}
		System.out.println(total);
		System.out.println(sb);
	}
	
	private void checkValue(int cycle, int registerX, List<Integer> interestingValues, StringBuilder sb) {
		if (Math.abs((cycle - 1) % 40 - registerX) <= 1) {
			sb.append("#");
		} else {
			sb.append(".");
		}
		if ((cycle - 20) % 40 == 0) {
			interestingValues.add(registerX);
		}
		if (cycle % 40 == 0) {
			sb.append("\n");
		}
	}
}
