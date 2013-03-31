JoinAndForkUtility
==================

Utility class to easily use join and fork feature from java 7.

Easy to use and improve performance for time consuming for loops. 

It is more of an attempt to decouple fork/join/compute method etc etc from the business problem. 



Usages 
  If you have to loop over time consuming method [ say List<Integer> compute] for lots of objects [say class BusinessProblem]. 
  
  Steps
  
  1. Create an adaptor for BusinessProblem which implements ProblemSolution<List<Integer> 

  2. Invoke Map<String,SolutionType> ForkAndJoinLoopUtil.solve(AdaptorToYourProblem<SolutionType>);


It is as simple as that.  



Examples 

  ForkAndJoinLoopUtilExample.java 
  

Results for example on my machine with qauad core cpu.


Tasks   	-> time (seconds)

--------------------------------------

fork & Join 		-> 32.94075471

Simple looping 		-> 9.620913634999999

Sum(Tasks)	-> 42.561668345  
