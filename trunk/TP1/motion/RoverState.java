package motion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nPuzzle.NPuzzleState;
import nPuzzle.NPuzzleState.NPuzzleOperator;
import static java.lang.Math.*;

import searchproblem.*;


public class RoverState extends State {
	
	public enum RoverOperator {
		N, S, E, W, NE, NW, SE, SW
	}
	
	protected int currX, currY;
		
	public RoverState(int x, int y){
		currX = x;
		currY = y;
	}
	
	@Override
	public Object clone() {
		return new RoverState( currX, currY);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + currX;
		result = PRIME * result + currY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RoverState other = (RoverState) obj;
		if (currX != other.currX)
			return false;
		if (currY != other.currY)
			return false;
		return true;
	}

	@Override
	public double applyOperator(Object op) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Arc> successorFunction() {
		List<Arc> children = new ArrayList<Arc>(4);
		for(RoverOperator action : RoverOperator.values() ) {
			if( applicableOperator(action))
				children.add(successorState(action));
		}
		return children;
	}

	private boolean applicableOperator(RoverOperator action) {
		if ( !(action instanceof RoverOperator) )
			return false;
		
		RoverOperator op = (RoverOperator) action;
		switch (op) {
		case N: 
			return currY-1 < 0;
		case S:
			return currY+1 < 100;
		case W:
			return currX-1 > 0;
		case E:
			return currX+1 < 100;
		case NE:
			return (currX+1 > 100 && currY-1 > 0);
		case NW:
			return (currX-1 > 0 && currY+1 < 100);
		case SE:
			return (currX+1 > 100 && currY-1 < 0);
		case SW:
			return (currX-1 > 100 && currY+1 <100);
		}
		return false;
	}

	@Override
	public Arc successorState(Object op) {
		RoverState child = (RoverState) this.clone();
		return new Arc(this,child,op,child.applyOperator(op));
	}
	

}
