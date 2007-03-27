package searchalgorithm;

import java.util.*;
import searchproblem.*;

public class GraphSearch implements SearchAlgorithm {
	
	SearchProblem p;
	Queue<Node> fringe;
	HashMap<Node,Node> closed;
	int expansions;
	int generated;
	int repeated;
	long time, start;
	

	public GraphSearch(SearchProblem p, Queue<Node> q) {
			this.p = p;
			fringe = q;
			closed = new HashMap<Node,Node>();
			expansions = 0;
			generated = 0;
			repeated = 0;
			time = 0;
			
	}
	
	public Node searchSolution() {
		
		start = System.nanoTime();
		fringe.add(new Node(p.getInitial()));
		
		while(true){
			if (fringe.isEmpty())
				return null; 
			Node node = fringe.remove();
			
			
			if(p.goalTest(node.getState())){
				time = System.nanoTime() - start;
				return node;
			}
			
			if(!closed.containsKey(node)){
				int size = fringe.size();
				closed.put(node,node);
				fringe.addAll(node.Expand());
				expansions++;
				generated = generated + (fringe.size() - size);
			}
			else 
			{
				if (closed.get(node).getPathCost() > node.getPathCost()){
					closed.remove(node);
					closed.put(node, node);
					repeated++;
				}
			}
		}
		
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
