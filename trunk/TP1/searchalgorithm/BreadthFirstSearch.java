package searchalgorithm;
import java.util.*;
import java.lang.*;

import searchproblem.SearchProblem;

public class BreadthFirstSearch implements SearchAlgorithm {

	private GraphSearch graph;
	
	public BreadthFirstSearch(SearchProblem p) {
		PriorityQueue<Node> fifo = new PriorityQueue<Node>();
		graph = new GraphSearch(p,fifo);
	}
	
	public Node searchSolution() {
		return graph.searchSolution();
	}
	
	public Map<String,Number> getMetrics() {
		return graph.getMetrics();
	}
	
}
