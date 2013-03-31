package util.concurrency.forkAndJoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.stopWatch.Stopwatch;

public class ForkAndJoinLoopUtilExample {

	public static class GenericProblem {

		private final String name;

		/**
		 * @param name
		 */
		GenericProblem(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		List<Integer> solution() {
			List<Integer> results = new ArrayList<Integer>();
			for (int i = 0; i < 1000; i++) {
				results.add(i);
			}
			return results;
		}
	}

	/**
	 *
	 */
	public static class GenericProblemAdaptor implements
			ProblemSolution<List<Integer>> {

		GenericProblem gp;

		public GenericProblemAdaptor(GenericProblem gp) {
			this.gp = gp;
		}

		@Override
		public String getProblemName() {
			return gp.getName();
		}

		@Override
		public List<Integer> solve() {
			return gp.solution();
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// This example will show how you can improve performance of your
		// application using Java fork and join method in an abstract manner.

		// Suppose you have to loop over lots of time consuming objects Ex:
		// GenericProblem
		int numOfProblems = 10000;
		List<GenericProblem> problems = new ArrayList<GenericProblem>();
		for (int i = 0; i < numOfProblems; i++) {
			problems.add(new GenericProblem("problem" + i));
		}

		// To use this framework, all you need is to create an adapter to your
		// class which implements problem ProblemSolution<SolutionType>
		List<GenericProblemAdaptor> adaptors = new ArrayList<>();
		for (GenericProblem gp : problems) {
			adaptors.add(new GenericProblemAdaptor(gp));
		}

		// Once the adaptor is ready, all you need to do is call the static
		// method for ForkAndJoinLoopUtil.solve(). It identifies total no of
		// processors on your system and accordingly creates number of threads
		// to process this loop faster. Lets measure it too.
		//
		Stopwatch sw = new Stopwatch();
		sw.start();
		Map<String, List<Integer>> solutionMap1 = ForkAndJoinLoopUtil
				.solve(adaptors);
		sw.measure("fork & Join");

		// Now try simple way and measure the time
		Map<String, List<Integer>> solutionMap2 = new HashMap<String, List<Integer>>();
		for (GenericProblem gp : problems) {
			solutionMap2.put(gp.getName(), gp.solution());
		}
		sw.measure("Simple looping");
		sw.stop(true);
	}

}
