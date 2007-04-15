package circuit;

import java.util.*;

import circuit.Individual;
import circuit.ObservationData;

/**
 * 	Classe que instancia a classe abstracta Individual
 */
public class RoverCircuit extends Individual {

	private static Random gen = new Random();
	private static Individual[] children = new Individual[2];
	
	private int size;
	private Integer[] circuit;
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
		circuit = new Integer[size];
		i=0;
		for(int v:c)
			circuit[i++] = v;
	}

	public RoverCircuit(ObservationData data, Integer[] circuit) {
		this.size = data.getSize();
		this.data = data;
		this.circuit = circuit;
		this.fitness = null;
	}
	
	
	
	@Override
	public Object clone() {
		return new RoverCircuit(data, circuit.clone());
	}

	@Override
	public Individual[] crossover(Individual other) {
		int r1 = gen.nextInt(size-1);
		int r2 = gen.nextInt(size-2);
		int cut1, cut2;
		
		
		if( r2 >= r1 ){
			cut1 = r1+1;
			cut2 = r2+1;
		} else {
			cut1 = r2+1;
			cut2 = r1+1;
		}
		
		
		Vector<Integer> child1 = new Vector<Integer>();
		Vector<Integer> child2 = new Vector<Integer>();
		Integer[] father = this.circuit;
		Integer[] mother = ((RoverCircuit) other).circuit;
		
		for(int i=cut1; i<cut2;i++) {
			child1.add(i, father[i]); 
			child2.add(i, mother[i]);		
		}
		
		// copies from mum to child1
		for(int i=0; i < size; i++) {
			int j =0;
			if( !child1.contains(mother[i])) {
				child1.add(j, mother[i]);
				j++;
				if( j==cut1)
					j=cut2;
			}
		}
		
		// copies from dad to child2
		for(int i=0; i < size; i++) {
			int j =0;
			if( !child2.contains(father[i])) {
				child2.add(j, father[i]);
				j++;
				if( j==cut1)
					j=cut2;
			}
		}
		
		children[0] = new RoverCircuit(data,(Integer [])child1.toArray());
		children[1] = new RoverCircuit(data,(Integer [])child2.toArray());
		
		return children;
	}

	@Override
	public double fitness() {
		if (fitness != null)
			return fitness;
		
		int fitaux = data.getSpot(circuit[0]).firstTime();
		
		for(int i=0; i<size; i++){
			if (i>0)
				fitaux += data.getSpot(circuit[i]).durationObservation(fitaux)+ data.getCost(circuit[i], circuit[i+1]);
			else
				fitaux += data.getSpot(circuit[i]).durationObservation(fitaux);
		}
		fitaux+= data.getCost(circuit[size-1], circuit[0]);
		
		return fitaux;
	}

	@Override
	public void mutate() {
		int i1 = gen.nextInt(size-1);
		int i2 = gen.nextInt(size-1);
		int aux;
		
			aux = circuit[i1];
			circuit[i1] = circuit[i2];
			circuit[i2] = aux;
			fitness = null;
	}
	
}
