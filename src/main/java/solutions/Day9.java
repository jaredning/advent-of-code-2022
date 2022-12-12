package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import common.InputReader;

public class Day9 {
	public void solve(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		Position headPos = new Position(0, 0);
		Position tailPos = new Position(0, 0);
		Set<Position> visitedPositions = new HashSet<>();
		visitedPositions.add(tailPos);
		for (String action : input) {
			String[] command = action.split(" ");
			int numMoves = Integer.parseInt(command[1]);
			switch (command[0]) {
				case "U":
					for (int i = 0; i < numMoves; i++) {
						headPos.incrementY();
						tailPos = calculateTailPos(headPos, tailPos);
						visitedPositions.add(tailPos);
					}
					break;
				case "D":
					for (int i = 0; i < numMoves; i++) {
						headPos.decrementY();
						tailPos = calculateTailPos(headPos, tailPos);
						visitedPositions.add(tailPos);
					}
					break;
				case "L":
					for (int i = 0; i < numMoves; i++) {
						headPos.decrementX();
						tailPos = calculateTailPos(headPos, tailPos);
						visitedPositions.add(tailPos);
					}
					break;
				case "R":
					for (int i = 0; i < numMoves; i++) {
						headPos.incrementX();
						tailPos = calculateTailPos(headPos, tailPos);
						visitedPositions.add(tailPos);
					}
					break;
			}
		}
		System.out.println(visitedPositions.size());
	}
	
	public void solve2(String path) {
		List<String> input = InputReader.readFileAsListOfStrings(path);
		List<Position> positions = new ArrayList<>();
		int numKnots = 10;
		for (int i = 0; i < numKnots; i++) {
			positions.add(new Position(0, 0));
		}
		Set<Position> visitedPositions = new HashSet<>();
		visitedPositions.add(positions.get(numKnots - 1));
		for (String action : input) {
			String[] command = action.split(" ");
			int numMoves = Integer.parseInt(command[1]);
			switch (command[0]) {
				case "U":
					for (int i = 0; i < numMoves; i++) {
						positions.get(0).incrementY();
						for (int j = 1; j < positions.size(); j++) {
							positions.set(j, calculateTailPos(positions.get(j - 1), positions.get(j)));
						}
						visitedPositions.add(positions.get(numKnots - 1));
					}
					break;
				case "D":
					for (int i = 0; i < numMoves; i++) {
						positions.get(0).decrementY();
						for (int j = 1; j < positions.size(); j++) {
							positions.set(j, calculateTailPos(positions.get(j - 1), positions.get(j)));
						}
						visitedPositions.add(positions.get(numKnots - 1));
					}
					break;
				case "L":
					for (int i = 0; i < numMoves; i++) {
						positions.get(0).decrementX();
						for (int j = 1; j < positions.size(); j++) {
							positions.set(j, calculateTailPos(positions.get(j - 1), positions.get(j)));
						}
						visitedPositions.add(positions.get(numKnots - 1));
					}
					break;
				case "R":
					for (int i = 0; i < numMoves; i++) {
						positions.get(0).incrementX();
						for (int j = 1; j < positions.size(); j++) {
							positions.set(j, calculateTailPos(positions.get( j - 1), positions.get(j)));
						}
						visitedPositions.add(positions.get(numKnots - 1));
					}
					break;
			}
		}
		System.out.println(visitedPositions.size());
	}
	private Position calculateTailPos(Position headPos, Position currentTailPos) {
		Position newTailPos = new Position(currentTailPos.getX(), currentTailPos.getY());
		if (Math.abs(headPos.getX() - currentTailPos.getX()) <= 1 && Math.abs(headPos.getY() - currentTailPos.getY()) <= 1) {
			// Tail is within 1 step of the head in any direction, no movement required
			return newTailPos;
		} else if (Math.abs(headPos.getX() - currentTailPos.getX()) > 1 && Math.abs(headPos.getY() - currentTailPos.getY()) == 0) {
			// Tail is on the same row as the Head but is 2 columns away
			if (headPos.getX() > currentTailPos.getX()) {
				newTailPos.incrementX();
			} else {
				newTailPos.decrementX();
			}
		} else if (Math.abs(headPos.getX() - currentTailPos.getX()) == 0 && Math.abs(headPos.getY() - currentTailPos.getY()) > 1) {
			// Tail is on the same column as the Head but is 2 rows away
			if (headPos.getY() > currentTailPos.getY()) {
				newTailPos.incrementY();
			} else {
				newTailPos.decrementY();
			}
		} else {
			// Tail needs to move diagonally - different row and column
			if (headPos.getX() > currentTailPos.getX()) {
				newTailPos.incrementX();
				if (headPos.getY() > currentTailPos.getY()) {
					newTailPos.incrementY();
				} else {
					newTailPos.decrementY();
				}
			} else {
				newTailPos.decrementX();
				if (headPos.getY() > currentTailPos.getY()) {
					newTailPos.incrementY();
				} else {
					newTailPos.decrementY();
				}
			}
		}
		return newTailPos;
	}
	
	class Position {
		int x;
		int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void incrementX() {
			this.x += 1;
		}
		
		public void decrementX() {
			this.x -= 1;
		}
		
		public void incrementY() {
			this.y += 1;
		}
		
		public void decrementY() {
			this.y -= 1;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Position that = (Position) o;
			return x == that.x && y == that.y;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
