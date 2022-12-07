package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class Day7 {

	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		Directory root = buildFileSystemTree(input);
		calculateDirectoryFileSize(root);
		List<Integer> directorySizes = getDirectoryFileSizes(root, new ArrayList<>());
		List<Integer> directoriesWithSizeLessThan100000 = directorySizes.stream().filter(directorySize -> directorySize <= 100000).toList();
		int sum = 0;
		for (Integer directorySize : directoriesWithSizeLessThan100000) {
			sum += directorySize;
		}
		System.out.println(sum);
		
		Collections.sort(directorySizes);
		int currentSpace = 70000000 - directorySizes.get(directorySizes.size() - 1);
		for (Integer directorySize : directorySizes) {
			if (currentSpace + directorySize >= 30000000) {
				System.out.println(directorySize);
				break;
			}
		}
		
	}
	
	private Directory buildFileSystemTree(List<String> input) {
		Directory root = new Directory();
		Directory current = root;
		for (String command : input.subList(2, input.size())) {
			if (command.contains("$ cd ..")) {
				current = current.getParent();
			} else if (command.contains("$ cd")) {
				Directory child = new Directory();
				child.setParent(current);
				current.addChild(child);
				current = child;
			} else if (command.contains("$ ls")) {
				// do nothing
			} else if (command.contains("dir")) {
				// do nothing
			} else {
				int fileSize = Integer.parseInt(command.split(" ")[0]);
				current.setTotalSize(current.getTotalSize() + fileSize);
			}
		}
		return root;
	}
	
	private int calculateDirectoryFileSize(Directory root) {
		if (root.getChildren().isEmpty()) {
			return root.getTotalSize();
		}
		int currentSize = root.getTotalSize();
		for (Directory child : root.getChildren()) {
			currentSize += calculateDirectoryFileSize(child);
		}
		root.setTotalSize(currentSize);
		return root.getTotalSize();
	}
	
	private List<Integer> getDirectoryFileSizes(Directory root, List<Integer> directoryFileSizes) {
		if (root.getChildren().isEmpty()) {
			directoryFileSizes.add(root.getTotalSize());
			return directoryFileSizes;
		}
		for (Directory child : root.getChildren()) {
			getDirectoryFileSizes(child, directoryFileSizes);
		}
		directoryFileSizes.add(root.getTotalSize());
		return directoryFileSizes;
	}
	
	class Directory {
		int totalSize = 0;
		Directory parent = null;
		List<Directory> children = new ArrayList<>();
		
		public void setTotalSize(int totalSize) {
			this.totalSize = totalSize;
		}
		
		public void setParent(Directory parent) {
			this.parent = parent;
		}
		
		public void addChild(Directory child) {
			this.children.add(child);
		}
		
		public int getTotalSize() {
			return this.totalSize;
		}
		
		public Directory getParent() {
			return this.parent;
		}
		
		public List<Directory> getChildren() {
			return this.children;
		}
	}
}

