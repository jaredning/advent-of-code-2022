package solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import common.InputReader;

public class Day5 {
	/**
	 *                 [B] [L]     [J]    
	 *             [B] [Q] [R]     [D] [T]
	 *             [G] [H] [H] [M] [N] [F]
	 *         [J] [N] [D] [F] [J] [H] [B]
	 *     [Q] [F] [W] [S] [V] [N] [F] [N]
	 * [W] [N] [H] [M] [L] [B] [R] [T] [Q]
	 * [L] [T] [C] [R] [R] [J] [W] [Z] [L]
	 * [S] [J] [S] [T] [T] [M] [D] [B] [H]
	 *  1   2   3   4   5   6   7   8   9 
	 */
	List<ArrayDeque<String>> yard = Arrays.asList(
			new ArrayDeque<>(Arrays.asList("W", "L", "S")),
			new ArrayDeque<>(Arrays.asList("Q", "N", "T", "J")),
			new ArrayDeque<>(Arrays.asList("J", "F", "H", "C", "S")),
			new ArrayDeque<>(Arrays.asList("B", "G", "N", "W", "M", "R", "T")),
			new ArrayDeque<>(Arrays.asList("B", "Q", "H", "D", "S", "L", "R", "T")),
			new ArrayDeque<>(Arrays.asList("L", "R", "H", "F", "V", "B", "J", "M")),
			new ArrayDeque<>(Arrays.asList("M", "J", "N", "R", "W", "D")),
			new ArrayDeque<>(Arrays.asList("J", "D", "N", "H", "F", "T", "Z", "B")),
			new ArrayDeque<>(Arrays.asList("T", "F", "B", "N", "Q", "L", "H"))
	);

	List<ArrayDeque<String>> yard2 = Arrays.asList(
			new ArrayDeque<>(Arrays.asList("W", "L", "S")),
			new ArrayDeque<>(Arrays.asList("Q", "N", "T", "J")),
			new ArrayDeque<>(Arrays.asList("J", "F", "H", "C", "S")),
			new ArrayDeque<>(Arrays.asList("B", "G", "N", "W", "M", "R", "T")),
			new ArrayDeque<>(Arrays.asList("B", "Q", "H", "D", "S", "L", "R", "T")),
			new ArrayDeque<>(Arrays.asList("L", "R", "H", "F", "V", "B", "J", "M")),
			new ArrayDeque<>(Arrays.asList("M", "J", "N", "R", "W", "D")),
			new ArrayDeque<>(Arrays.asList("J", "D", "N", "H", "F", "T", "Z", "B")),
			new ArrayDeque<>(Arrays.asList("T", "F", "B", "N", "Q", "L", "H"))
	);
	
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		for (String line : input) {
			String[] orders = line.split(" ");
			performOrder(Integer.parseInt(orders[1]), Integer.parseInt(orders[3]), Integer.parseInt(orders[5]));
			performOrder2(Integer.parseInt(orders[1]), Integer.parseInt(orders[3]), Integer.parseInt(orders[5]));
		}
		String output1 = "";
		String output2 = "";
		for (ArrayDeque<String> stack : yard) {
			output1 += stack.peek();
		}
		for (ArrayDeque<String> stack : yard2) {
			output2 += stack.peek();
		}
		System.out.println(output1);
		System.out.println(output2);
	}
	
	private void performOrder(int numToMove, int start, int target) {
		for (int i = 0; i < numToMove; i++) {
			String crate = yard.get(start - 1).pop();
			yard.get(target - 1).push(crate);
		}
	}
	
	private void performOrder2(int numToMove, int start, int target) {
		ArrayDeque<String> craneIsHolding = new ArrayDeque<>();
		for (int i = 0; i < numToMove; i++) {
			String crate = yard2.get(start - 1).pop();
			craneIsHolding.push(crate);
		}
		for (int i = 0; i < numToMove; i++) {
			yard2.get(target - 1).push(craneIsHolding.pop());
		}
	}
}
