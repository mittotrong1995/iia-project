package circuit;

import java.io.*;

public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		String datastr = "";
		BufferedReader reader = new BufferedReader( new FileReader("C:\\Documents and Settings\\Santanidis\\My Documents\\FCT\\10º Semestre\\IIA\\Trabalhos\\t2\\CincoLinha.txt"));
		
		String line = reader.readLine();
		while( line != null) {
			datastr += line + "\n";
			line = reader.readLine();
		}   
		
		
		ObservationData r = new ObservationData(datastr);
		
		/*int maxdist=0;
		int mindist=Integer.MAX_VALUE;
		
		for(int i=0; i < r.getSize(); i++ )
			for(int j=0; j < r.getSize(); j++ ) {
				maxdist = max(maxdist,r.getCost(i, j));
				if( i != j ) mindist = min(mindist,r.getCost(i, j));
			}
		
		System.out.println(mindist+" "+maxdist);*/
			
		/*
		Random gen = new Random();
		int size = 20;
		
		ObservationData.ObservationInterval interval = r.new ObservationInterval(0,ObservationData.SOL-1);
		SortedSet<ObservationData.ObservationInterval> is = new TreeSet<ObservationData.ObservationInterval>();
		is.add(interval);
		
		ObservationData.ObservationSpot[] spot = new ObservationData.ObservationSpot[size];
		int[][] distances = new int[size][size];
		for( int s =0 ; s < size ; s++) {
			spot[s] = r.new ObservationSpot("P"+s,gen.nextInt(ObservationData.SOL/2),is);
		}
		for( int i=0; i < size; i++)
			for( int j=0; j < size; j++)
				if( i != j )
					distances[i][j] = gen.nextInt(6*ObservationData.SOL);
		
		r = new ObservationData(size,spot,distances);
		*/	
		RoverCircuit p1 = new RoverCircuit(r);
		RoverCircuit p2 = new RoverCircuit(r);
		
		System.out.println(p1);
		System.out.println(p1.fitness());
		System.out.println(p2);
		System.out.println(p2.fitness());
		
		Individual[] offspring = p1.crossover(p2);
		System.out.println(offspring[0]);
		System.out.println(offspring[0].fitness());
		System.out.println(offspring[1]);
		System.out.println(offspring[1].fitness());
		
		Population p = new Population();
		
		for(int i=0; i < 200; i++) {
			Individual ind = new RoverCircuit(r);
			System.out.println(ind);
			System.out.println(ind.fitness());
			p.addIndividual(ind);
		}
		double x = p.getSumOfFitness() / p.size();
		System.out.println(p.getBestFit() + " "+ x + " " +p.getWorstFit());		
		System.out.println(p.getBestIndividual());

		System.out.println(p.selectIndividual());
		System.out.println(p.selectIndividual());
		
		GeneticAlgorithm ga = new GeneticAlgorithm(p,0.9f,0.2f);
		
		System.out.println("--------------------");
		System.out.println("Best individual found: "+ga.search());
		

		/*
		for(int i=0; i < 10; i++) {
			System.out.print(c);
			System.out.println(c.getEnergy());
			c = c.getNeighbour();
		}
		
		SimulatedAnnealing<RoverCircuit> sa = new GeometricSchedule<RoverCircuit>(c,1000000000.0,0.9999,10000000,10);
		
		for(int i=0; i < 1; i++) {
			c = sa.search();
		
			System.out.println(c);
			System.out.println(c.getEnergy());
			System.out.println(sa.getMetrics());
		}
		*/

        
        
	}

}
