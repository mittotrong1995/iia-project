package nPuzzle;

import java.util.*;

import searchalgorithm.*;
import searchproblem.*;

public class NPuzleTest {

	public static void main(String[] args) {

		Number s[][] = { {7,2,4}, {5,null,6}, {8,3,1} };
		Number f[][] = { {1,2,3}, {4,null,5}, {6,7,8} };
		NPuzzleState init = new NPuzzleState(s,3);
		NPuzzleState goal = new NPuzzleState(f,3);
				
		NPuzzleProblem prob = new NPuzzleProblem(init,goal);
//		SearchAlgorithm u = new UniformCostSearch(prob);
		SearchAlgorithm u = new BreadthFirstSearch(prob);
//		SearchAlgorithm u = new AStarSearch(prob);
		
		Node n = u.searchSolution();
		if( n != null)
			System.out.println(n.getPath());
		System.out.println(u.getMetrics());
	}

}
