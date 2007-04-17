package circuit;

import java.util.Random;


/**
 * Classe que "implementa" o algoritmo genético
 */
public class GeneticAlgorithm {
	
	private Population pop;
	private float pcrossover = (float)0.6;
	private float pmutate = (float)0.001;
	public long runtime;
	public int generations;
	private int mutation;

	/**
	 * Construtor
	 * @param pop uma população
	 */
	GeneticAlgorithm(Population pop, int mutation) {
		this.pop = pop;
		this.runtime = 0;
		this.generations = 0;
		this.mutation = mutation;
	}
	/**
	 * Construtor
	 * @param pop uma população
	 * @param pcrossover a probabilidade de crossover
	 * @param pmutate a probabilidade de mutação
	 */
	GeneticAlgorithm(Population pop, float pcrossover, float pmutate, int mutation) {
		this.pop = pop;
		this.pcrossover = pcrossover;
		this.pmutate = pmutate;
		this.runtime = 0;
		this.generations = 0;
		this.mutation = mutation;
		
	}

public Individual search() {
		long startTime = System.currentTimeMillis();
		Random gen = new Random();
		double best = Double.POSITIVE_INFINITY;
		int bestMutation = -1;
		
		double lastfitpop = pop.getBestFit();
		int ctr=0;
		
		while( ctr < 10000 ) {

			Population newpop = pop.getElite(1);
			
			while( newpop.size() < pop.size() ) {
				Individual father = pop.selectIndividual();
				Individual mother = pop.selectIndividual();
				Individual[] child;
				
				if (gen.nextFloat() <= pcrossover ) {
					child = father.crossover(mother);
				} else {
					child = new Individual[2];
					child[0]=(Individual) father.clone();
					child[1]=(Individual) mother.clone();
				}
				
				if (mutation == 1){
					for ( Individual o : child ) {
	
						if (gen.nextFloat() <= pmutate ) {
							o.mutate(mutation);
						}
						newpop.addIndividual( o );
	
					}
				}
				else{
					Individual auxchild;
					for(int i=0; i< child.length;i++){
						auxchild = child[i];
						if(gen.nextFloat() <= pmutate){
							for(int j =0;j<100;j++){
								auxchild.mutate(mutation);
								double f = child[i].fitness(); 
								if ( f < best){
									best = f;
									bestMutation = i;
								}
								auxchild = child[i];
							}
						}
						if (bestMutation != -1){
							newpop.addIndividual(child[bestMutation]);
						}
						else
							newpop.addIndividual(child[i]);
					}
				}
			}
			pop = newpop;
			if( pop.getBestFit() < lastfitpop ) {
				ctr=0;
				lastfitpop = pop.getBestFit();
				System.out.print(pop.size()+" "+pop.getBestFit()+" "+pop.getSumOfFitness()/pop.size()+" "+pop.getWorstFit()+ " ");
				System.out.println(pop.getBestIndividual());
			} else {
				ctr++;
			}
			this.generations++;
		}

		runtime = System.currentTimeMillis()-startTime;
		return pop.getBestIndividual();
	}
}
	

