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

Simple looping 		-> 6.666775879

fork & Join 		-> 3.792459488

Sum(Tasks)	-> 10.459235367
