package circuit;

import java.io.*;


public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader console = new BufferedReader(stdin);
		String input;
		String filename=new String("C:\\CincoLinha.txt");
		int choice=6;
		int mutation=1;
		int initpop = 200;
		GeneticAlgorithm ga;
		
		while(true){
		System.out.println("Menu:");
		System.out.println(" 1 - Escolher Ficheiro");
		System.out.println(" 2 - Mutação");
		System.out.println(" 3 - População inicial");
		System.out.println(" 4 - Default");
		System.out.println(" 5 - Run");
		System.out.println(" 6 - Exit");
		System.out.print("> ");
		
		try {
			input = console.readLine();
			choice = new Integer(input).intValue();
		} catch (Exception e) {
			System.exit(1);
		}
		try {
			
			switch(choice){
			case 1:
					int file= 1;
					System.out.println("1 - Cinco Linhas");
					System.out.println("2 - Cinquenta anel");
					System.out.print("> ");
					try{
						
						input = console.readLine();
						file = new Integer(input).intValue();
					}catch (Exception e) {
						System.exit(1);
					}
					switch(file){
					case 1: 
						filename = new String("C:\\CincoLinha.txt");
						break;
					case 2:
						filename = new String("C:\\CinquentaAnel.txt");
						break;
					default:
						filename = new String("C:\\CincoLinha.txt");
						break;
					}
				break;
			case 2:
				int mut = 1;
				System.out.println("1 - Swap");
				System.out.println("2 - Hill Climbing");
				System.out.print("> ");
				try{
					input = console.readLine();
					mut = new Integer(input).intValue();
				}catch (Exception e) {
					System.exit(1);
				}
				switch(mut){
				case 1: 
					mutation = 1;
					break;
				case 2:
					mutation = 2;
					break;
				default:
					mutation = 1;
					break;
				}
				break;
			case 3:
				System.out.println("Insira o valor da população ");
				System.out.print("> ");
				try{
					input = console.readLine();
					initpop = new Integer(input).intValue();
				}catch (Exception e) {
					System.exit(1);
				}
				break;
			case 4:
				System.out.println("Valores default: ");
				System.out.println();
				System.out.println("Ficheiro: CincoLinha.txt");
				System.out.println("Mutação: Swap");
				System.out.println("População inicial: 200");
				System.out.println();
				System.out.println();
				System.out.println("Deseja alterar para default? S/N");
				try{
					input = console.readLine();
					char resp = input.charAt(0);
					if (resp == 'S' || resp == 's'){
						filename=new String("C:\\CincoLinha.txt");
						mutation=1;
						initpop = 200; 
					}
				}catch (Exception e) {
					System.exit(1);
				}
				break;
			case 5:
				String toObservation = "";
				BufferedReader in = new BufferedReader( new FileReader(filename));
				
				String line = in.readLine();
				while( line != null) {
					toObservation += line + "\n";
					line = in.readLine();
				}
				ObservationData r = new ObservationData(toObservation);
				
				Population p = new Population();
				
				for(int i=0; i < initpop; i++) {
					Individual ind = new RoverCircuit(r);
					p.addIndividual(ind);
				}
				if (filename.compareTo("C:\\CincoLinha.txt") == 0 )
					ga = new GeneticAlgorithm(p,0.9F,0.01F,mutation);
				else
					ga = new GeneticAlgorithm(p,0.6F,0.001F,mutation);
				
				System.out.println("Searching...");
				Individual indy = ga.search();
				System.out.println("Best individual: "+indy);
				System.out.println("Best fit: "+ indy.fitness());
				System.out.println("Worst individual: "+ p.getWorstIndividual());
				System.out.println("Worst fit: "+ p.getWorstFit());
				System.out.println("Runtime: "+ ga.runtime/1000+ " s");
				System.out.println("Geracoes: "+ga.generations);
			default:
				System.exit(1);
				break;
			}
		} 
		catch (Exception x) {
			//System.out.println("Find Error.");
			System.out.println(x);
		}
	}
	}

}

