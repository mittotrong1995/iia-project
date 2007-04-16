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
		/*Random rand = new Random();
		double bestFit = pop.getBestFit();
		double best = Double.POSITIVE_INFINITY;
		int bestMutation = -1;
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
				
				//Individual auxchild;
				
				for(int i=0; i< child.length;i++){
					//auxchild = child[i];
					if(rand.nextFloat() <= pmutate){
						for(int j =0;j<100;j++){
							auxchild.mutate();
							double f =child[i].fitness(); 
							if ( f < best){
								best = f;
								bestMutation = i;
							}
							auxchild = child[i];
						}
						child[i].mutate();
						
					}
					if (bestMutation != -1)
						newpop.addIndividual(child[bestMutation]);
					else
						newpop.addIndividual(child[i]);
				}
				pop = newpop;
				
				if (pop.getBestFit() <= bestFit){
					bestFit = pop.getBestFit();
					counter = 0;
				}
				else
					counter++;
			}
		}
		return pop.getBestIndividual();*/
		Random gen = new Random();

		double lastfitpop = pop.getBestFit();
		int ctr=0;
		
		while( ctr < 30000 ) {

			Population newpop = pop.getElite(1);
			
			while( newpop.size() < pop.size() ) {
				Individual father = pop.selectIndividual();
				Individual mother = pop.selectIndividual();
				Individual[] offspring;
				
				if (gen.nextFloat() <= pcrossover ) {
					offspring = father.crossover(mother);
				} else {
					offspring = new Individual[2];
					offspring[0]=(Individual) father.clone();
					offspring[1]=(Individual) mother.clone();
				}
				
				/*for ( Individual o : offspring ) {
					if (gen.nextFloat() <= pmutate ) {
						o.mutate();
					}
					newpop.addIndividual( o );
				}*/
				Individual auxchild;
				double best = Double.POSITIVE_INFINITY;
				int bestMutation = -1;
				
				for(int i=0; i< offspring.length;i++){
					auxchild = offspring[i];
					if(gen.nextFloat() <= pmutate){
						for(int j =0;j<100;j++){
							auxchild.mutate();
							double f = offspring[i].fitness(); 
							if ( f < best){
								best = f;
								bestMutation = i;
							}
							auxchild = offspring[i];
						}
						
					}
					if (bestMutation != -1){
						newpop.addIndividual(offspring[bestMutation]);
					}
					else
						newpop.addIndividual(offspring[i]);
			}
			
			pop = newpop;
			if( pop.getBestFit() < lastfitpop ) {
				ctr=0;
				lastfitpop = pop.getBestFit();
				System.out.print(pop.size()+" "+pop.getBestFit()+" "+pop.getWorstFit()+ " ");
				System.out.println(pop.getBestIndividual());
			} else {
				ctr++;
			}
		}
		}
		return pop.getBestIndividual();
		
	}
}
