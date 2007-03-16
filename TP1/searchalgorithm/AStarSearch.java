package searchalgorithm;

import java.util.*;

import searchproblem.InformedSearchProblem;
import searchproblem.SearchProblem;

public class AStarSearch implements SearchAlgorithm {
	
	
private GraphSearch graph;
	
	public AStarSearch(SearchProblem p) {
	}
	
	public Node searchSolution() {
		return graph.searchSolution();
	}
	
	public Map<String,Number> getMetrics() {
		return graph.getMetrics();
	}

}
