package searchalgorithm;

import java.util.*;

import searchproblem.*;

public class DepthFirstSearch implements SearchAlgorithm {
	
	private boolean done = false;
	private SearchProblem problem;
	private Node goal;
	private int expansions;
	private int generated;
	private int repeated;
	private long time;
	
	public DepthFirstSearch(SearchProblem p) {
		this.problem = p;
	}

	public Node searchSolution() {
		if( !done ) {
			long startTime = System.nanoTime();		
			goal = search(new Node(problem.getInitial()), new HashSet<Node>());
			time = System.nanoTime() - startTime;
			done = true;
			problem = null;
		}
		return goal;
	}
	
	private Node search(Node n, Set<Node> ancestors) {
		if( problem.goalTest(n.getState()) ) {
			return n;
		} else if (ancestors.contains(n)) {
			repeated++;
			return null;
		}
		ancestors.add(n);
		List<Node> children = n.Expand();
		expansions++;
		generated += children.size();
		for(Node successor : children ) {
			Node result = search(successor,ancestors);
			if( result != null )
				return result;
		}
		ancestors.remove(n);
		return null;
	}
	
	public Map<String,Number> getMetrics() {
		Map<String,Number> metrics = new LinkedHashMap<String,Number>();
		
		metrics.put("Node Expansions",expansions);
		metrics.put("Nodes Generated",generated);
		metrics.put("State repetitions",repeated);		
		metrics.put("Runtime (s)", time/1E9);
		return metrics;
	}

}
