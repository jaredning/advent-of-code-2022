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
	
	public static List<List<String>> readFileAsNestedListOfStrings(String path, int groupSize) {
		File file = new File(path);
		List<List<String>> input = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			ArrayList<String> list = new ArrayList<>();
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i >= groupSize) {
					input.add(list);
					list = new ArrayList<>();
					i = 0;
				}
				list.add(line);
				i++;
			}
			input.add(list);
		} catch (Exception e) {
			System.out.println("Exception!");
		}
		return input;
	}
	
	public static String readFileAsString(String path) {
		File file = new File(path);
		String input = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				input = line;
			}
		} catch (Exception e) {
			System.out.println("Exception!");
		}
		return input;
	}
}
