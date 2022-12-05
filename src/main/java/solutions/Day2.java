package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import common.InputReader;

public class Day2 {
	HashMap<String, Integer> rock = new HashMap<>();
	HashMap<String, Integer> paper = new HashMap<>();
	HashMap<String, Integer> scissors = new HashMap<>();
	HashMap<String, Integer> win = new HashMap<>();
	HashMap<String, Integer> draw = new HashMap<>();
	HashMap<String, Integer> lose = new HashMap<>();
	HashMap<String, HashMap<String, Integer>> scoreMatrix = new HashMap<>();
	HashMap<String, HashMap<String, Integer>> scoreMatrix2 = new HashMap<>();
	
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		initializeMaps();
		initializeMaps2();
		int sum = 0;
		int sum2 = 0;
		for (String line: input) {
			String[] round = line.split("\\s+");
			sum += calculateScore(round);
			sum2 += calculateScore2(round);
		}
		System.out.println(sum);
		System.out.println(sum2);
	}
	
	private void initializeMaps() {
		rock.put("A", 1 + 3);
		rock.put("B", 1 + 0);
		rock.put("C", 1 + 6);
		paper.put("A", 2 + 6);
		paper.put("B", 2 + 3);
		paper.put("C", 2 + 0);
		scissors.put("A", 3 + 0);
		scissors.put("B", 3 + 6);
		scissors.put("C", 3 + 3);
		scoreMatrix.put("X", rock);
		scoreMatrix.put("Y", paper);
		scoreMatrix.put("Z", scissors);
	}
	
	private void initializeMaps2() {
		win.put("A", 2 + 6);
		win.put("B", 3 + 6);
		win.put("C", 1 + 6);
		lose.put("A", 3 + 0);
		lose.put("B", 1 + 0);
		lose.put("C", 2 + 0);
		draw.put("A", 1 + 3);
		draw.put("B", 2 + 3);
		draw.put("C", 3 + 3);
		scoreMatrix2.put("X", lose);
		scoreMatrix2.put("Y", draw);
		scoreMatrix2.put("Z", win);
	}
	
	private int calculateScore(String[] round) {
		return scoreMatrix.get(round[1]).get(round[0]);
	}
	
	private int calculateScore2(String[] round) {
		return scoreMatrix2.get(round[1]).get(round[0]);
	}
}
