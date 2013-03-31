/**
 * @author sb
 */
package util.concurrency.forkAndJoin;

/*-
 * It represents a problem with the method to solve the problem. 
 * Subclass it and show the solution of one problem. 
 * S refers to Solution of any type
 */
public interface ProblemSolution<S> {

	/**
	 * @return name of the problem
	 */
	public abstract String getProblemName();

	/**
	 * 
	 * @return S - solution
	 */
	public abstract S solve();

}
