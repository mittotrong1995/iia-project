package searchalgorithm;

import java.util.*;

import searchproblem.SearchProblem;

public class TreeSearch implements SearchAlgorithm {

	private boolean done = false;
	private SearchProblem problem;
	private Node goal;
	private Queue<Node> fringe;
	private int expansions;
	private int generated;
	
	public TreeSearch(SearchProblem p, Queue<Node> q) {
		problem = p;
		goal = null;
		fringe = q;
		expansions = 0;
		generated = 0;
	}
	
	public Node searchSolution() {
		if( !done ) {
			goal = search();
			done = true;
			fringe = null;
			problem = null;
		}
		return goal;
	}

	private Node search() {		
		fringe.clear();
		fringe.add(new Node(problem.getInitial()));
		generated++;
		for(;;) {
			if( fringe.isEmpty() ) {
				return null;
			}
			Node n = fringe.remove();
			if( problem.goalTest(n.getState())) {
				return n;
			}
			expansions++;
			List<Node> children = n.Expand();
			generated += children.size();
			fringe.addAll(children);
		}
	}
	
	public Map<String,Number> getMetrics() {
		Map<String,Number> metrics = new LinkedHashMap<String,Number>();
		
		metrics.put("Node Expansions",expansions);
		metrics.put("Nodes Generated",generated);
		return metrics;
	}
	
}
