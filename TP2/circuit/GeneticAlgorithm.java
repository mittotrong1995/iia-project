package circuit;

import java.util.Random;
/**
 * Classe que "implementa" o algoritmo gen�tico
 */
public class GeneticAlgorithm {
	
	private Population pop;
	private float pcrossover = (float)0.6;
	private float pmutate = (float)0.001;

	/**
	 * Construtor
	 * @param pop uma popula��o
	 */
	GeneticAlgorithm(Population pop) {
		this.pop = pop;
	}
	/**
	 * Construtor
	 * @param pop uma popula��o
	 * @param pcrossover a probabilidade de crossover
	 * @param pmutate a probabilidade de muta��o
	 */
	GeneticAlgorithm(Population pop, float pcrossover, float pmutate) {
		this.pop = pop;
		this.pcrossover = pcrossover;
		this.pmutate = pmutate;
		
	}
	
	/**
	 * 	M�todo que pesquisa e devolve o melhor indiv�duo encontrado
	 * @return pop.getBestIndividual(), o melhor indiv�duo
	 */
	public Individual search() {
		Random rand = new Random();
		double bestFit = pop.getBestFit();
		int counter = 0;
		
		while (counter < 30000){
		
			Population newpop = new Population();
			newpop.addIndividual(pop.getBestIndividual());
			while(newpop.size() < pop.size()){
				Individual father = pop.selectIndividual();
				Individual mother = pop.selectIndividual();
				Individual [] child;
				
				if (rand.nextFloat() <= pcrossover)
					child = father.crossover(mother);
				else{
					child = new Individual[2];
					child[0] = (Individual)father.clone();
					child[1] = (Individual)mother.clone();
				}
				
				for(int i=0; i< child.length;i++){
					if(rand.nextFloat() <= pmutate){
						child[i].mutate();
					}
					newpop.addIndividual(child[i]);
				}
				pop = newpop;
				
				if (pop.getBestFit() < bestFit){
					bestFit = pop.getBestFit();
					counter = 0;
				}
				else
					counter++;
			}
		}
		return pop.getBestIndividual();
	}
}
