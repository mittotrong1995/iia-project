package circuit;

import java.io.*;

public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
				
		String data = "";
		BufferedReader r = new BufferedReader( new FileReader("C:\\Documents and Settings\\Santanidis\\My Documents\\FCT\\10º Semestre\\IIA\\Trabalhos\\t2\\CincoLinha.txt"));
		
		String line = r.readLine();
		while( line != null) {
			data += line + "\n";
			line = r.readLine();
		}
		
		
		ObservationData obs = new ObservationData(data);
		
		Population p = new Population();
		
		for(int i=0; i < 200; i++) {
			Individual ind = new RoverCircuit(obs);
			/*System.out.println(ind);
			System.out.println(ind.fitness());*/
			p.addIndividual(ind);
		}
		System.out.println("Best Fit: "+ p.getBestFit());
		System.out.println("Average Fit: "+ p.getBestFit()/p.getWorstFit());
		System.out.println("Worst Fit " +p.getWorstFit());		
		
		System.out.print("Best Individual: "+ p.getBestIndividual());
		System.out.println("   Fit: "+ p.getBestFit());
		double fit = p.selectIndividual().fitness();
		
		System.out.println("Individual :"+p.selectIndividual()+ "  Fit: "+ fit);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(p,0.9f,0.2f);
		
		System.out.println("--------------------");
		System.out.println("Best individual found: "+ga.search());

	}

}
