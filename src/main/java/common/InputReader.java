package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
	public static List<List<Integer>> readFileAsNestedListOfInts(String path) throws Exception {
		File file = new File(path);
		List<List<Integer>> input = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			ArrayList<Integer> list = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.length() == 0) {
					input.add(list);
					list = new ArrayList<>();
				} else {
					Integer integer = Integer.valueOf(line);
					list.add(integer);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception!");
		}
		return input;
	}
	
	public static List<String> readFileAsListOfStrings(String path) {
		File file = new File(path);
		List<String> input = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				input.add(line);
			}
		} catch (Exception e) {
			System.out.println("Exception!");
		}
		return input;
	}
}
