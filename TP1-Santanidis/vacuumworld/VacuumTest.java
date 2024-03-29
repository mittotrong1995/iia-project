package vacuumworld;
import searchalgorithm.*;

public class VacuumTest {

	public static void main(String[] args) {
		boolean[] world = {true, true, false, false, true};
		VacuumState init = new VacuumState(world,1,5);
				
		VacuumProblem prob = new VacuumProblem(init);
		SearchAlgorithm bfs = new BreadthFirstSearch(prob);
		//SearchAlgorithm bfs = new UniformCostSearch(prob);
		//SearchAlgorithm bfs = new DepthFirstSearch(prob);
		System.out.println(bfs.searchSolution().getPath());	
		System.out.println(bfs.getMetrics());
	}

}
