package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class Day8 {
	/**
	 * For each tree, we can simply iterate in each direction until we find a tree that is the same size or bigger
	 * than the tree of interest.
	 * O(n) runtime
	 */
	public void solve(String path) {
		List<List<Integer>> trees = InputReader.readFileAs2DIntegerList(path);
		int numVisible = 0;
		List<Integer> scenicScores = new ArrayList<>();
		for (int i = 0; i < trees.size(); i++) {
			for (int j = 0; j < trees.get(i).size(); j++) {
				if (i == 0 || i == trees.size() - 1 || j == 0 || j == trees.get(0).size() - 1) {
					numVisible++;
				} else {
					if (isVisible(trees, trees.get(i).get(j), i, j)) {
						numVisible++;
					}
				}
				scenicScores.add(calculateScenicScore(trees, trees.get(i).get(j), i, j));
			}
		}
		System.out.println(numVisible);
		System.out.println(Collections.max(scenicScores));

	}
	
	private boolean isVisible(List<List<Integer>> trees, int currentHeight, int currI, int currJ) {
		boolean visibleLeft = true, visibleRight = true, visibleUp = true, visibleDown = true;
		for (int i = currI - 1; i >= 0; i--) {
			if (trees.get(i).get(currJ) >= currentHeight) {
				visibleUp = false;
				break;
			}
		}
		for (int i = currI + 1; i < trees.size(); i++) {
			if (trees.get(i).get(currJ) >= currentHeight) {
				visibleDown = false;
				break;
			}
		}
		for (int j = currJ - 1; j >= 0; j--) {
			if (trees.get(currI).get(j) >= currentHeight) {
				visibleLeft = false;
				break;
			}
		}
		for (int j = currJ + 1; j < trees.get(0).size(); j++) {
			if (trees.get(currI).get(j) >= currentHeight) {
				visibleRight = false;
				break;
			}
		}
		return visibleDown || visibleUp || visibleLeft || visibleRight;
	}
	
	private int calculateScenicScore(List<List<Integer>> trees, int currentHeight, int currI, int currJ) {
		int leftScore = 0;
		int rightScore = 0;
		int upScore = 0;
		int downScore = 0;
		for (int i = currI - 1; i >= 0; i--) {
			if (trees.get(i).get(currJ) >= currentHeight) {
				upScore++;
				break;
			}
			upScore++;
			
		}
		for (int i = currI + 1; i < trees.size(); i++) {
			if (trees.get(i).get(currJ) >= currentHeight) {
				downScore++;
				break;
			}
			downScore++;
		}
		for (int j = currJ - 1; j >= 0; j--) {
			if (trees.get(currI).get(j) >= currentHeight) {
				leftScore++;
				break;
			}
			leftScore++;
		}
		for (int j = currJ + 1; j < trees.get(0).size(); j++) {
			if (trees.get(currI).get(j) >= currentHeight) {
				rightScore++;
				break;
			}
			rightScore++;
		}
		return downScore * upScore * leftScore * rightScore;
	}
}
