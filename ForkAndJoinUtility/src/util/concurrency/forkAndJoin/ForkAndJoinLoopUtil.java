/**
 * @author sb
 */
package util.concurrency.forkAndJoin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Loop your problems utilizing the power of multiCore machine using java fork
 * and join
 */
public class ForkAndJoinLoopUtil {

	/*-
	 * The framework requires user to provide list of problems 
	 * This utility will then provide the solution of all the problems in optimal time using fork-join principle.
	 * 
	 * 
	 * @param problems
	 *            	- List of problems
	 * @return solutions 
	 * 				- Map of problem name to Solutions
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static <P extends ProblemSolution<S>, S> Map<String, S> solve(
			List<P> problems) throws Exception {
		int processors = Runtime.getRuntime().availableProcessors();
		int threshold = Math.max(1, (problems.size() / processors));
		ForkJoinPool pool = new ForkJoinPool(processors);
		ProblemSolutions<P, S> ps = new ProblemSolutions<P, S>(problems,
				threshold);
		pool.invoke(ps);
		try {
			return ps.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new Exception(e.getMessage());
		}
	}

}
