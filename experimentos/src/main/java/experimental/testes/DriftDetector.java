package experimental.testes;

//Classe que abstrai Detecção de Drift

import experimental.model.BaseDrifts;
import java.util.List;
import moa.classifiers.core.driftdetection.ChangeDetector;
import moa.classifiers.core.driftdetection.DDM;

public class DriftDetector {
    List<BaseDrifts> listaBaseDrifts = null;
    public ChangeDetector detectorOraculo = null;
    boolean usarDetector;
    
    public DriftDetector(List<BaseDrifts> listaDrifts) {
        setup(listaDrifts);
    }
    
    private void setup(List<BaseDrifts> listaDrifts)
    {

            if (listaDrifts != null && !listaDrifts.isEmpty())
            {
                listaBaseDrifts = listaDrifts;
                this.usarDetector = false;
            }
            else
            {
                detectorOraculo = new DDM();
                this.usarDetector = true;
            }

    }
    
    public void input(double inputValue)
    {
        if (usarDetector)
        {
            detectorOraculo.input(inputValue);
        }
    }
    
    public boolean isDriftOcorreu(int iteracao){
        

            if (this.usarDetector)
            {
                if (detectorOraculo.getChange())
                {
                    return true;
                }
            }
            else
            {
                //Procura na lista de drift
                for (BaseDrifts baseDrift : listaBaseDrifts) {
                    if (baseDrift.getIteracao() == iteracao)
                    {
                        return true;
                    }
                }
            }
            
            return false;
    }
    
}
