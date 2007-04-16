package circuit;

import java.io.*;

public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		String toObservation = "";
		BufferedReader in = new BufferedReader( new FileReader("C:\\Documents and Settings\\Santanidis\\My Documents\\FCT\\10º Semestre\\IIA\\Trabalhos\\t2\\CinquentaAnel.txt"));
		
		String line = in.readLine();
		while( line != null) {
			toObservation += line + "\n";
			line = in.readLine();
		}   
		
		
		ObservationData r = new ObservationData(toObservation);
		
		Population p = new Population();
		
		for(int i=0; i < 200; i++) {
			Individual ind = new RoverCircuit(r);
			p.addIndividual(ind);
		}	
		System.out.println(p.getBestIndividual());
		System.out.println("Fit: "+ p.getBestIndividual().fitness());
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm(p,0.9F,0.001F);
		
		Individual indy = algorithm.search();
		System.out.println("Best individual found: "+ indy);
		System.out.println("Fit: "+ indy.fitness());
	}

}
