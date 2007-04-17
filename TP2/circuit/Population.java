package circuit;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
/**
 * 	Classe usada para a representa��o de uma popula��o.
 */
public class Population {
	private boolean currupt;
	private int size;
	private ArrayList<Individual> pop;
	private ArrayList<Double> acum;
	private static Random gen = new Random();
	private Individual bestInd;
	private Individual worstInd;
	private double bestFit;
	private double worstFit;
	private double sumOfFitness;
	
	/**
	 * 	Construtor relativo � classe Population
	 */
	Population(){
		this.currupt = true;
		this.size = 0;
		this.pop = new ArrayList<Individual>(100);
		this.acum = new ArrayList<Double>(100);
		this.sumOfFitness=0.0;
		this.bestInd = null;
		this.worstInd = null;
		this.bestFit = Double.POSITIVE_INFINITY;
		this.worstFit = Double.NEGATIVE_INFINITY;
	}
	
	
	/**
	 * 	Construtor onde se especifica a popola��o
	 * @param p um array de indiv�duos
	 */
	
	Population(Individual[] indy){
		for(int i =0; i< indy.length; i++)
			this.pop.add(indy[i]);
	}
	
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
			if (-(pos+1) >= pop.size())
				return pop.get(pop.size()-1);
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
	
	public Population getElite(int n){
		Population p = new Population();
		p.addIndividual(this.bestInd);
		return p;
	}


	public ArrayList<Double> getAcum() {
		return acum;
	}


	public double getBestFit() {
		return bestFit;
	}


	public Individual getBestIndividual() {
		return bestInd;
	}


	public double getSumOfFitness() {
		return sumOfFitness;
	}


	public double getWorstFit() {
		return worstFit;
	}


	public Individual getWorstIndividual() {
		return worstInd;
	}


	public int size() {
		return size;
	}
	
}