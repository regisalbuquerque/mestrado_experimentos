package experimental.model;

import br.ufam.metodo.util.dados.Dados;
import br.ufam.metodo.util.model.EnsembleOnLineBagging;
import moa.classifiers.Classifier;


public class EnsembleFactory implements Classificador{
    private Integer id;
    
    private double lambda;
    private int numClassificadores;
    private int numBaseLearners;
    private boolean reset;
    private Integer ordem;
    private String classe;
    
    private Classifier classificador;

    public EnsembleFactory(double lambda, int numClassificadores, int numBaseLearners, boolean reset, Integer ordem, String classe) {
        this.lambda = lambda;
        this.numClassificadores = numClassificadores;
        this.numBaseLearners = numBaseLearners;
        this.reset = reset;
        this.ordem = ordem;
        this.classe = classe;
    }

    public EnsembleFactory() {
    }
    
    @Override
    public Classifier reset(Dados dados) {
        EnsembleOnLineBagging ensemble = new EnsembleOnLineBagging();
        ensemble.lambdaOption.setValue(this.getLambda());
        ensemble.ensembleSizeOption.setValue(this.getNumClassificadores());
        ensemble.numBaseLeanersOption.setValue(this.getNumBaseLearners());

        ensemble.setModelContext(dados.getDataHeader());
        ensemble.prepareForUse();
        
        classificador = ensemble;
        
        return ensemble;
    }
    
    @Override
	public Classifier getClassificador() {
		return this.classificador;
	}
    

    @Override
    public boolean resetWhenDrift() {
        return this.reset;
    }
    
    @Override
    public boolean isMetodo() {
        return false;
    }

    @Override
    public boolean isEnsemble() {
        return true;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public int getNumClassificadores() {
        return numClassificadores;
    }

    public void setNumClassificadores(int numClassificadores) {
        this.numClassificadores = numClassificadores;
    }

    public int getNumBaseLearners() {
        return numBaseLearners;
    }

    public void setNumBaseLearners(int numBaseLearners) {
        this.numBaseLearners = numBaseLearners;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
    
    

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }


    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

}
