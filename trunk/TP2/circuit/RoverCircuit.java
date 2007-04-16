package circuit;

import java.util.*;
/**
 * 	Classe que instancia a classe abstracta Individual
 */
public class RoverCircuit extends Individual {

	private static Random gen = new Random();
	private static Individual[] children = new Individual[2];
	
	private int size;
	public int[] circuit;
	private ObservationData data;
	private Double fitness;
	
	public RoverCircuit(ObservationData data) {
		this.size = data.getSize();
		this.data = data;
		this.fitness = null;
		List<Integer> c = new ArrayList<Integer>(size);
		int i;
		for(i=0; i < size; i++ )
			c.add(i);
		Collections.shuffle(c);
		circuit = new int[size];
		i=0;
		for(int v:c)
			circuit[i++] =  v;
	}

	public RoverCircuit(ObservationData data, int[] circuit) {
		this.size = data.getSize();
		this.data = data;
		this.circuit = circuit;
		this.fitness = null;
	}
	
	
	
	@Override
	public Object clone() {
		return new RoverCircuit(data, circuit.clone());
	}
	
	private boolean contains(int num, int[] obj){
		for(int i = 0; i< size ; i++){
			if (num == obj[i])
				return true;
		}
				
		return false;
	}

	
	@Override
	public double fitness() {
		if( fitness != null )
			return fitness;
		
		int time = data.getSpot(circuit[0]).firstTime();
		for(int i=1; i < size; i++ ) {
			time += data.getSpot(circuit[i]).durationObservation(time) + data.getCost(circuit[i-1],circuit[i]);
		}
		time += data.getCost(circuit[size-1], circuit[0]);
		
		fitness = (double) time;
		return 0;
	}
	
	@Override
	public Individual[] crossover(Individual other) {
		int r1 = gen.nextInt(size-1);
		int r2 = gen.nextInt(size-2);
		int cut1, cut2, j;
		
		
		if( r2 >= r1 ){
			cut1 = r1+1;
			cut2 = r2+1;
		} else {
			cut1 = r2+1;
			cut2 = r1+1;
		}

		int[] child1 = new int[size];
		int[] child2 = new int[size];
		for(int i=0;i<size;i++){
			child1[i] = -1;
			child2[i] = -1;
		}
		
		int[] father = this.circuit;
		int[] mother = ((RoverCircuit) other).circuit;
		
		for(int i=cut1; i <= cut2;i++) {
			child1[i] = father[i]; 
			child2[i] = mother[i];		
		}
		
		// copies from mum to child1
		j =0;
		for(int i=0; i < size; i++) {
			if( !contains(mother[i], child1)) {
				child1[j] = mother[i];
				j++;
				if( j==cut1)
					j=cut2+1;
			}
		}
		j=0;
		// copies from dad to child2
		for(int i=0; i < size; i++) {
			
			if( !contains(father[i], child2)) {
				child2[j] = father[i];
				j++;
				if( j==cut1)
					j=cut2+1;
			}
		}
		
		children[0] = new RoverCircuit(data, child1);
		children[1] = new RoverCircuit(data, child2);
		
		return children;
	}

	

	@Override
	public void mutate() {
	/*	int swap1 = gen.nextInt(size);
		int swap2 = gen.nextInt(size-1);
		if( swap2 >= swap1 )
			swap2++;
		int aux = circuit[swap1];
		circuit[swap1] = circuit[swap2];
		circuit[swap2] = aux;
			fitness = null;*/
		
		int num = gen.nextInt(size/20);
		int r1,r2,aux;
		
		for(int i=0;i<num;i++){
			r1 = gen.nextInt(size);
			r2 = gen.nextInt(size);
			
			aux = circuit[r1];
			circuit[r1] = circuit[r2];
			circuit[r2] = aux;
		}
		
	}
	
	
	public String toString() {
		String out = "";
		for(int i=0; i < size; i++)
			out += data.getSpot(circuit[i]).getName()+" ";
		return out;
	}
}
