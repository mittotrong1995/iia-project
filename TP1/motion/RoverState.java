package motion;

import java.util.ArrayList;
import java.util.List;

import motion.Terrain.TerrainType;


import static java.lang.Math.*;

import searchproblem.*;


public class RoverState extends State {
	
	public enum RoverOperator {
		N, S, E, W, NE, NW, SE, SW
	}
	
	protected int currX, currY;
	protected BitmapTerrain t;
	protected double res;
	
		
	public RoverState(int x, int y, BitmapTerrain t){
		currX = x;
		currY = y;
		this.t = t;
		res = 0;
	}
	
	@Override
	public Object clone() {
		return new RoverState( currX, currY, t);
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
		
		
		switch ((RoverOperator)op) {
		case N: 
			return moveN();
		case S:
			return moveS();
		case W:
			return moveW();
		case E:
			return moveE();
		case NE:
			return moveNE();
		case NW:
			return moveNW();
		case SE:
			return moveSE();
		case SW:
			return moveSW();
		}
		return 0;
	}
	// Nestas funcoes falta multiplicar pelo valor da distancia euclidiana
	// Ainda nao programada
	private double moveSW() {
		res = moveCost(currX-1,currY+1,true);
		currX--;
		currY++;
		return res;
	}

	private double moveSE() {
		res = moveCost(currX+1,currY+1,true);
		currX++;
		currY++;
		return res;
	}

	private double moveNW() {
		res = moveCost(currX-1,currY-1,true);
		currX--;
		currY--;
		return res;
	}

	private double moveNE() {
		res = moveCost(currX+1,currY-1,true);
		currX++;
		currY--;
		return res;
	}

	private double moveE() {
		res = moveCost(currX+1,currY,false);
		currX++;
		return res;
	}

	private double moveW() {
		res = moveCost(currX-1,currY,false);
		currX++;
		return res;
	}

	private double moveS() {
		res = moveCost(currX,currY+1,false);
		currY++;
		return res;
	}

	private double moveN() {
		res = moveCost(currX,currY-1, false);
		currY--;
		return res;
	}
	
	public double moveCost(int x, int y, boolean b){
		int factor;
		double height;
		
		if (t.getTerrainType(x, y).equals(TerrainType.SAND))
			factor = 2;
		else
			if (t.getTerrainType(x, y).equals(TerrainType.ROCK))
				factor = 3;
			else
				factor = 1;
		
		if (t.getHeight(x, y) > t.getHeight(currX, currY))
			height = 1.01;
		else if (t.getHeight(x, y) < t.getHeight(currX, currY))
				height = 0.99;
			 else
				height = 1.0; 
		
		if(b == false)
			return (height*factor);
		else
			return (height*factor* sqrt(2));
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
			return currY+1 < t.getVerticalSize();
		case W:
			return currX-1 > 0;
		case E:
			return currX+1 < t.getHorizontalSize();
		case NE:
			return (currX+1 < t.getHorizontalSize() && currY-1 > 0);
		case NW:
			return (currX-1 > 0 && currY-1 > 0);
		case SE:
			return (currX+1 < t.getHorizontalSize() && currY+1 < t.getVerticalSize());
		case SW:
			return (currX-1 > 0 && currY+1 < t.getVerticalSize());
		}
		return false;
	}

	@Override
	public Arc successorState(Object op) {
		RoverState child = (RoverState) this.clone();
		return new Arc(this,child,op,child.applyOperator(op));
	}

	public int getCoordY() {
		return currY;
	}
	
	public int getCoordX() {
		return currX;
	}

}
