/**
 * @author sb
 */
package util.concurrency.forkAndJoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * The aim was to do the abstraction of join(), compute() methods etc.
 * 
 * @param <P>
 *            Problem with one solution
 * @param <S>
 *            Data type of 1 solution
 */
public final class ProblemSolutions<P extends ProblemSolution<S>, S> extends
		RecursiveTask<Map<String, S>> {

	private static final long serialVersionUID = 1L;
	private final List<P> problems;
	private final int threshold;

	/**
	 * @param problems
	 *            - List of problems
	 * @param threshold
	 */
	public ProblemSolutions(List<P> problems, int threshold) {
		this.problems = problems;
		this.threshold = threshold;
	}

	/*-
	 * If the problems size is less than threshold it executes it without using fork and join way. 
	 * If the problems size is more than threshold, it uses fork and join method.  
	 */
	@Override
	public Map<String, S> compute() {
		Map<String, S> solutionsMap = new HashMap<String, S>();
		int sizeOfTheProblem = problems.size();
		if (sizeOfTheProblem <= threshold) {
			solutionsMap.putAll(compute(problems));
		} else {
			int mid = sizeOfTheProblem / 2;
			ProblemSolutions<P, S> firstHalf = new ProblemSolutions<P, S>(
					problems.subList(0, mid), threshold);
			ProblemSolutions<P, S> secondHalf = new ProblemSolutions<P, S>(
					problems.subList(mid, sizeOfTheProblem), threshold);
			// The order is very important from here
			firstHalf.fork();
			// This is calculated in the current thread itself
			solutionsMap.putAll(secondHalf.compute());
			// This is going to block the current thread until all the firstHalf
			// work is done, that is why it is invoked at the end.
			solutionsMap.putAll(firstHalf.join());
		}
		return solutionsMap;
	}

	/**
	 * Single thread would have directly invoked this method.
	 * 
	 * @param problems
	 * @return
	 */
	private Map<String, S> compute(List<P> problems) {
		Map<String, S> solutionMap = new HashMap<String, S>();
		for (P problem : problems) {
			solutionMap.put(problem.getProblemName(), problem.solve());
		}
		return solutionMap;
	}

}
