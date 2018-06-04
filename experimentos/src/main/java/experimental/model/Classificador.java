package experimental.model;

import br.ufam.metodo.util.dados.Dados;
import moa.classifiers.Classifier;

public interface Classificador {
    public Classifier reset(Dados dados);
    public boolean resetWhenDrift();
    public boolean isMetodo();
    public boolean isEnsemble();
    
}
