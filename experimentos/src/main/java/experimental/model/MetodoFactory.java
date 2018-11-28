package experimental.model;

import br.ufam.metodo.util.dados.Dados;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.Classifier;

public class MetodoFactory implements Classificador {

    private Integer id;
    private String codigo;
   
    private final AbstractClassifier classificadorConfigs;
    private Classifier classificador;

    public MetodoFactory(AbstractClassifier classificadorConfigs) {
        this.classificadorConfigs = classificadorConfigs;
    }

    @Override
    public Classifier reset(Dados dados) {
        classificador = classificadorConfigs.copy();
        

        classificador.setModelContext(dados.getDataHeader());
        classificador.prepareForUse();

        return classificador;
    }
    
    @Override
	public Classifier getClassificador() {
		return this.classificador;
	}
    

	@Override
    public boolean resetWhenDrift() {
        return false; //Para métodos o reset é INTERNO ao método (a critério do método)
    }

    @Override
    public boolean isMetodo() {
        return true;
    }

    @Override
    public boolean isEnsemble() {
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


}


/*



if (null != this.getAlgoritmo()) {
            switch (this.getAlgoritmo()) {
                case HoeffdingTree:
                    HoeffdingTree metodoHoeffdingTree = new HoeffdingTree();
                    classificador = metodoHoeffdingTree;
                    break;
                case NaiveBayes:
                    NaiveBayes metodoNaiveBayes = new NaiveBayes();
                    classificador = metodoNaiveBayes;
                    break;
                case DDM:
                    DriftDetectionMethodClassifier metodoDDM = new DriftDetectionMethodClassifier();
                    metodoDDM.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
                    classificador = metodoDDM;
                    break;
                case LeveragingBag:
                    LeveragingBag metodoLeveraging = new LeveragingBag();
                    metodoLeveraging.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
                    classificador = metodoLeveraging;
                    break;
                case MetodoV11:
                    MetodoClassificadorV11 metodoClassificadorV11 = new MetodoClassificadorV11();
                    metodoClassificadorV11.ensemblesNumberOption.setValue(this.getNumEnsembles());
                    metodoClassificadorV11.ensembleSizeOption.setValue(this.getNumClassificadores());
                    metodoClassificadorV11.lambdasOption.setValueViaCLIString(this.getLambdas());
                    metodoClassificadorV11.selectionOptionMedidaCalculo.setChosenLabel(this.getMedidaCalculo());
                    metodoClassificadorV11.selectionOptionEstrategiaSelecao.setChosenLabel(this.getEstrategiaSelecao());
                    metodoClassificadorV11.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.getEstrategiaRecuperacao());
                    metodoClassificadorV11.ensemblesNumRemoverRecuperacaoOption.setValue(this.getNumRemoverRecuperacao());
                    metodoClassificadorV11.numBaseLeanersOption.setValue(this.getNumBaseLearners());
                    metodoClassificadorV11.baseLearner1Option.setValueViaCLIString(this.getBaseLearner1());
                    metodoClassificadorV11.baseLearner2Option.setValueViaCLIString(this.getBaseLearner2());
                    metodoClassificadorV11.baseLearner3Option.setValueViaCLIString(this.getBaseLearner3());
                    metodoClassificadorV11.baseLearner4Option.setValueViaCLIString(this.getBaseLearner4());
                    metodoClassificadorV11.baseLearner5Option.setValueViaCLIString(this.getBaseLearner5());
                    classificador = metodoClassificadorV11;
                    break;
                case MetodoV12:
                    MetodoClassificadorV12 metodoClassificadorV12 = new MetodoClassificadorV12();
                    metodoClassificadorV12.ensemblesNumberOption.setValue(this.getNumEnsembles());
                    metodoClassificadorV12.ensembleSizeOption.setValue(this.getNumClassificadores());
                    metodoClassificadorV12.lambdasOption.setValueViaCLIString(this.getLambdas());
                    metodoClassificadorV12.selectionOptionMedidaCalculo.setChosenLabel(this.getMedidaCalculo());
                    metodoClassificadorV12.selectionOptionEstrategiaSelecao.setChosenLabel(this.getEstrategiaSelecao());
                    metodoClassificadorV12.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.getEstrategiaRecuperacao());
                    metodoClassificadorV12.ensemblesNumRemoverRecuperacaoOption.setValue(this.getNumRemoverRecuperacao());
                    metodoClassificadorV12.numBaseLeanersOption.setValue(this.getNumBaseLearners());
                    metodoClassificadorV12.numWindowOption.setValue(this.getNumWindow());
                    metodoClassificadorV12.numKNearestNeighborsOption.setValue(this.getNumkNearest());
                    metodoClassificadorV12.baseLearner1Option.setValueViaCLIString(this.getBaseLearner1());
                    metodoClassificadorV12.baseLearner2Option.setValueViaCLIString(this.getBaseLearner2());
                    metodoClassificadorV12.baseLearner3Option.setValueViaCLIString(this.getBaseLearner3());
                    metodoClassificadorV12.baseLearner4Option.setValueViaCLIString(this.getBaseLearner4());
                    metodoClassificadorV12.baseLearner5Option.setValueViaCLIString(this.getBaseLearner5());
                    classificador = metodoClassificadorV12;
                    break;
                default:
                    break;
            }
        }

*/



//    private Integer numEnsembles;
//
//    private Integer numClassificadores;
//
//    private String lambdas;
//
//    private String medidaCalculo;
//
//    private String estrategiaSelecao;
//
//    private String estrategiaRecuperacao;
//
//    private Integer numRemoverRecuperacao;
//
//    private Integer numBaseLearners;
//
//    private Integer numWindow;
//
//    private Integer numkNearest;
//
//    private String baseLearner1;
//
//    private String baseLearner2;
//
//    private String baseLearner3;
//
//    private String baseLearner4;
//
//    private String baseLearner5;

//    public MetodoFactory(AlgoritmoAprendizado algoritmo) {
//        this.algoritmo = algoritmo;
//    }
