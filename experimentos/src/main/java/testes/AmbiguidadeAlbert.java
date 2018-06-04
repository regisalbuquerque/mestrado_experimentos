package testes;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.trees.HoeffdingTree;
import weka.core.Instance;

public class AmbiguidadeAlbert {

	/**
	 * Declarei um conjunto de classificadores do tipo HoeffdingTree. 
	 * Porém você pode utilizar qual classificador quiser.
	 */
	private HoeffdingTree[] conjuntoClassificadoresHT;
	
	/**
	 * Instância atual para qual vai ser calculada a ambiguidade.
	 */
	private Instance instancia;
	
	/**
	 * Quantidade de classificadores dentro do ensemble.
	 */
	private int qtdClassificadores;
	
	/**
	 * Construtor da classe.
	 * @param conjuntoClassificadores: O conjunto de classificadores.
	 */
	public AmbiguidadeAlbert(HoeffdingTree[] conjuntoClassificadores) {
		// TODO Auto-generated constructor stub
		//Cópia para a variável local o conjunto passado pelo parâmetro.
		this.conjuntoClassificadoresHT = conjuntoClassificadores;
		//Variável inteira que armazena a quantidade de classificadores dentro do conjunto.
		this.qtdClassificadores = conjuntoClassificadores.length;
	}
		
	
	/**
	 * O método que faz o calculo da ambiguidade local para cada instância.
	 * @param instanciaAtual: Instância atual.
	 * @return: Retorna a ambiguidade da instância.
	 * @throws Exception
	 */
	public double calcularAmbiguidadeLocalHT(Instance instanciaAtual) throws Exception{
		//Declara a variável que armazenará a ambiguidade calculada.
		double ambiguidade = 0.0;
		/**
		 * Da forma que está implementado só funciona para problemas de classificação binários.
		 */
		//Armazena a quantidade de votos que a classe 0 recebeu.
		int qtdClasse0 = 0;
		//Armazena a quantidade de votos que a classe 1 recebeu.
		int qtdClasse1 = 0;
		//Copia para a variável local a instância passado por parâmetro.
		this.instancia = instanciaAtual;
		//Variável inteira que armazena a quantidade de votos que a classe minoritária recebeu.
		int votosMinoritarios = 0;
		//Para cada classificador pertencente ao conjunto (ensemble) é realizado a classificação da instância atual.
		for(int i = 0; i < conjuntoClassificadoresHT.length; i++){
			//Para cada classificador realiza a predição da instância atual e armazena na variável predicao.
			//O Weka trabalha com valores 0.0 para a primeira classe e 1.0 para a segunda classe.
			double predicao =conjuntoClassificadoresHT[i].classifyInstance(this.instancia);
			//Se o valor da predicao é igual a 0.0 então classificador i classificou a instância atual com classe 0. 
			if(predicao==0.0){
				//Classe zero recebe mais um voto.
				qtdClasse0++;
			}
			//Se o valor da predicao é igual a 1.0 então o classificador i classificou a instância atual com classe 1.
			if(predicao==1.0){
				//Classe um recebe mais um voto.
				qtdClasse1++;
			}
		}//Fim do for dentro do conjunto de classificadores.
		
		//Verifica qual classe recebeu mais votos.
		if(qtdClasse0>qtdClasse1){
			//Se a classe 0 recebeu mais votos então armazenamos os votos da classe perdedora.
			votosMinoritarios = qtdClasse1;
		}else{
			//Se a classe 1 recebeu mais votos então armazenamos os votos da classe perdedora.
			votosMinoritarios = qtdClasse0;
		}
		//Dividimos a quantidade de votos que recebeu a classe perdedora e dividimos pela quantidade de classificadores no conjunto.		
		ambiguidade = (double) votosMinoritarios/this.qtdClassificadores;
		return ambiguidade;
	}
	
	public Instance getInstancia() {
		return instancia;
	}
	public void setInstancia(Instance instancia) {
		this.instancia = instancia;
	}
	public int getQtdClassificadores() {
		return qtdClassificadores;
	}
	public void setQtdClassificadores(int qtdClassificadores) {
		this.qtdClassificadores = qtdClassificadores;
	}

	public HoeffdingTree[] getConjuntoClassificadoresHT() {
		return conjuntoClassificadoresHT;
	}
	public void setConjuntoClassificadoresHT(HoeffdingTree[] conjuntoClassificadoresHT) {
		this.conjuntoClassificadoresHT = conjuntoClassificadoresHT;
	}
	
}
