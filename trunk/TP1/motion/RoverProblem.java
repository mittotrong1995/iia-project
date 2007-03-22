package motion;

import static java.lang.Math.*;
import nPuzzle.NPuzzleState;
import searchalgorithm.Node;
import searchproblem.*;

public class RoverProblem extends InformedSearchProblem {
	
	
	public RoverProblem(RoverState initial, RoverState goal) {
		super(initial,goal); 
	}
	
	public double heuristic(Node n) {
		return 1;
	}

}
