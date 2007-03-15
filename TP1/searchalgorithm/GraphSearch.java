package searchalgorithm;

import java.util.*;

import searchproblem.*;

public class GraphSearch implements SearchAlgorithm {
	
	SearchProblem prob;
	PriorityQueue<Node> fringe;
	Set<State> closed;
	List<Node> visited;
	int expansions;
	int generated;
	int repstates;
	long time, start;
	

	public GraphSearch(SearchProblem p, PriorityQueue<Node> q) {
			prob = p;
			fringe = q;
			visited = new LinkedList<Node>();
			expansions = 0;
			generated = 0;
			repstates = 0;
			time = 0;
			
	}
	
	public Node searchSolution() {
		
		start = System.nanoTime();
		closed = new HashSet<State>();
		fringe.add(new Node(prob.getInitial()));
		
		while(true){
			if (fringe.isEmpty())
				return null; 
			Node node = fringe.remove();
			if(visited.contains(node))
				repstates++;
			else visited.add(node);
			
			if(prob.goalTest(node.getState())){
				time = System.nanoTime() - start;
				return node;
			}
			
			if(!closed.contains(node.getState())){
				int size = fringe.size();
				closed.add(node.getState());
				fringe.addAll(node.Expand());
				expansions++;
				generated = generated + (fringe.size() - size);
			}
		}
		
	}

	public Map<String,Number> getMetrics() {
		Map<String,Number> metrics = new LinkedHashMap<String,Number>();
		
		metrics.put("Node Expansions",expansions);
		metrics.put("Nodes Generated",generated);
		metrics.put("State repetitions",repstates);		
		metrics.put("Runtime (s)", time/1E9);
		return metrics;
	}
	
}
