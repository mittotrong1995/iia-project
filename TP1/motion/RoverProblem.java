package motion;

import static java.lang.Math.*;
import nPuzzle.NPuzzleState;
import searchalgorithm.Node;
import searchproblem.*;

public class RoverProblem extends InformedSearchProblem {
	
	private RoverState goal;
	
	public RoverProblem(RoverState initial, RoverState goal) {
		super(initial,goal);
		this.goal = goal;
	}
	
	public double heuristic(Node n) {
		int currX,currY,endX,endY;
		RoverState state = (RoverState)n.getState();
		currX = state.getCoordX();
		currY = state.getCoordY();
		endX = goal.getCoordX();
		endY = goal.getCoordY();
		
		return sqrt(pow(endX-currX,2) + pow(endY-currY,2));			
	}

}
