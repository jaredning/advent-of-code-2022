package solutions;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

public class Day11 {
	public void solve(String path) {
		List<Monkey> monkeys = new ArrayList<>();
		/*
		Monkey monkey0 = new Monkey((num) -> num * 19, 23, 2, 3);
		monkey0.startingItems(79L, 98L);
		monkeys.add(monkey0);
		Monkey monkey1 = new Monkey((num) -> num + 6, 19, 2, 0);
		monkey1.startingItems(54L, 65L, 75L, 74L);
		monkeys.add(monkey1);
		Monkey monkey2 = new Monkey((num) -> num * num, 13, 1, 3);
		monkey2.startingItems(79L, 60L, 97L);
		monkeys.add(monkey2);
		Monkey monkey3 = new Monkey((num) -> num + 3, 17, 0, 1);
		monkey3.startingItems(74L);
		monkeys.add(monkey3);
		*/
		Monkey monkey0 = new Monkey(num -> num * 11, 2, 7, 4);
		monkey0.startingItems(89L, 95L, 92L, 64L, 87L, 68L);
		monkeys.add(monkey0);
		Monkey monkey1 = new Monkey(num -> num + 1, 13, 3, 6);
		monkey1.startingItems(87L, 67L);
		monkeys.add(monkey1);
		Monkey monkey2 = new Monkey(num -> num + 6, 3, 1, 6);
		monkey2.startingItems(95L, 79L, 92L, 82L, 60L);
		monkeys.add(monkey2);
		Monkey monkey3 = new Monkey(num -> num * num, 17, 7, 0);
		monkey3.startingItems(67L, 97L, 56L);
		monkeys.add(monkey3);
		Monkey monkey4 = new Monkey(num -> num * 7, 19, 5, 2);
		monkey4.startingItems(80L, 68L, 87L, 94L, 61L, 59L, 50L, 68L);
		monkeys.add(monkey4);
		Monkey monkey5 = new Monkey(num -> num + 8, 7, 2, 1);
		monkey5.startingItems(73L, 51L, 76L, 59L);
		monkeys.add(monkey5);
		Monkey monkey6 = new Monkey(num -> num + 5, 11, 3, 0);
		monkey6.startingItems(92L);
		monkeys.add(monkey6);
		Monkey monkey7 = new Monkey(num -> num + 7, 5, 4, 5);
		monkey7.startingItems(99L, 76L, 78L, 76L, 79L, 90L, 89L);
		monkeys.add(monkey7);
		
		for (int round = 1; round <= 10000; round++) {
			for (int i = 0; i < monkeys.size(); i++) {
				for (int j = monkeys.get(i).getCurrentNumberOfItems() - 1; j >= 0; j--) {
					Pair result = monkeys.get(i).inspectItem();
					if (result.getTestPassed()) {
						monkeys.get(monkeys.get(i).getThrowToIfTrue()).catchItem(result.getItem());
					} else {
						monkeys.get(monkeys.get(i).getThrowToIfFalse()).catchItem(result.getItem());
					}
				}
			}
		}
		for (int i = 0; i < monkeys.size(); i++) {
			System.out.println("Monkey " + i + " inspected items " + monkeys.get(i).getNumInspects() + " times.");
		}
		List<Integer> numInspects = monkeys.stream().map(Monkey::getNumInspects).collect(Collectors.toList());
		Collections.sort(numInspects);
		Collections.reverse(numInspects);
		System.out.println(BigInteger.valueOf(numInspects.get(0)).multiply(BigInteger.valueOf(numInspects.get(1))));
	}
	
	class Monkey {
		ArrayDeque<Long> items = new ArrayDeque<>();
		ToLongFunction<Long> operation;
		int test;
		int throwToIfTrue ;
		int throwToIfFalse;
		int numInspects = 0;
		
		public Monkey(ToLongFunction<Long> operation, int test, int throwToIfTrue, int throwToIfFalse) {
			this.operation = operation;
			this.test = test;
			this.throwToIfTrue = throwToIfTrue;
			this.throwToIfFalse = throwToIfFalse;
		}
		
		public void startingItems(Long... items) {
			for (Long item : items) {
				this.items.offer(item);
			}
		}
		
		public void catchItem(Long item) {
			items.offer(item % 9699690);
		}
		
		public Pair inspectItem() {
			Long item = items.poll();
			numInspects++;
			item = operation.applyAsLong(item);
			boolean testPassed = item % test == 0;
			return new Pair(testPassed, item);
		}
		
		public ArrayDeque<Long> getItems() {
			return this.items;
		}
		
		public int getCurrentNumberOfItems() {
			return this.items.size();
		}
		
		public int getThrowToIfTrue() {
			return this.throwToIfTrue;
		}
		
		public int getThrowToIfFalse() {
			return this.throwToIfFalse;
		}
		
		public int getNumInspects() {
			return this.numInspects;
		}
	}
	
	class Pair {
		boolean testPassed;
		Long item;
		
		public Pair(boolean testPassed, Long item) {
			this.testPassed = testPassed;
			this.item = item;
		}
		
		public boolean getTestPassed() {
			return this.testPassed;
		}
		
		public Long getItem() {
			return this.item;
		}
	}
}
