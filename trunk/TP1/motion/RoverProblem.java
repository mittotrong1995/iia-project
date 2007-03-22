package motion;

import static java.lang.Math.*;
import nPuzzle.NPuzzleState;
import searchalgorithm.Node;
import searchproblem.*;

public class RoverProblem extends InformedSearchProblem {
	
	private int endX, endY, endHeight;
	
	public RoverProblem(RoverState initial, int gx, int gy) {
		super(initial,new RoverState(gx,gy,initial.t));
		endX = gx;
		endY = gy;
		endHeight = initial.t.getHeight(endX, endY );
	}
	
	public double heuristic(Node n) {
		int currX,currY;
		int currHeight,height;
		RoverState state = (RoverState)n.getState();
		currX = state.getCoordX();
		currY = state.getCoordY();
		currHeight = state.t.getHeight(currX, currY);
		
		height = endHeight - currHeight;
		
		if (height < 0)		
			return pow(0.99,sqrt(pow(endX-currX,2) + pow(endY-currY,2) + pow(height,2)));
		else
			return sqrt(pow(endX-currX,2) + pow(endY-currY,2) + pow(height,2));
	}
}
