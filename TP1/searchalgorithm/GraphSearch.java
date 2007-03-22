package searchalgorithm;

import java.util.*;
import searchproblem.*;

public class GraphSearch implements SearchAlgorithm {
	
	SearchProblem p;
	Queue<Node> fringe;
	//Set<State> closed;
	List<Node> visited;
	private HashSet<State> closed;
	int expansions;
	int generated;
	int repeated;
	long time, start;
	

	public GraphSearch(SearchProblem p, Queue<Node> q) {
			this.p = p;
			fringe = q;
			visited = new LinkedList<Node>();
			closed = new HashSet<State>();
			expansions = 0;
			generated = 0;
			repeated = 0;
			time = 0;
			
	}
	/*
	public Node searchSolution() {
		
		start = System.nanoTime();
		fringe.add(new Node(p.getInitial()));
		
		while(true){
			if (fringe.isEmpty())
				return null; 
			Node node = fringe.remove();
			if(visited.contains(node))
				repstates++;
			else visited.add(node);
			
			if(p.goalTest(node.getState())){
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
		
	}*/
	
	
	public Node searchSolution() {
	Node node = new Node(p.getInitial());
	fringe.add(node);
	long startingTime = System.nanoTime();
	for (;;)
	{
		if (fringe.isEmpty())
		{
			time = System.nanoTime()-startingTime;
			return null;
		}

		node = fringe.remove();
		if (p.goalTest(node.getState()))
		{
			time = System.nanoTime()-startingTime;
			return node;
		}
		
		
		if (!closed.contains(node.getState()) )
		{
			List<Node> children = node.Expand();
			closed.add(node.getState());
			fringe.addAll(children);
			expansions++;
			repeated++;
			generated += children.size();
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
