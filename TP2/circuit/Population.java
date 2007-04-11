package circuit;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
/**
 * 	Classe usada para a representa��o de uma popula��o.
 */
public class Population {
	
	/**
	 * 	Construtor relativo � classe Population
	 */
	
	/**
	 * 	Construtor onde se especifica a popola��o
	 * @param p um array de indiv�duos
	 */
	
	/**
	 * Selecciona e devolve um indiv�duo da popula��o, tendo em conta a sua fitness
	 * @return um array de indiv�duos
	 */
	public Individual selectIndividual() {
		
		// Verifica se necessita de calcular os valores de probabilidade de selec��o de cada indiv�duo
		if( currupt ) {
			double total=0.0;
			for(int i=0; i < pop.size(); i++) {
				total += pop.get(i).fitness();
				acum.add(total/sumOfFitness);
			}
			currupt = false;
		}
		
		double r = gen.nextDouble();
		int pos = Collections.binarySearch(acum, r);
		
		if( pos >= 0)
			return pop.get(pos);
		else
			return pop.get(-(pos+1));
		
	}
	/**
	 * Adiciona um indiv�duo � popula��o
	 * @param ind, um indiv�duo
	 */
	public void addIndividual(Individual ind) {
		size++;
		pop.add(ind);
		double f = ind.fitness();
		sumOfFitness += f; 
		if( f > worstFit ) {
			worstFit = f;
			worstInd = ind;
		}
		if( f < bestFit ) {
			bestFit = f;
			bestInd = ind;
		}
	}
	

}
