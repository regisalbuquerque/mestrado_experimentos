package experimental.analise;

/**
 *
 * @author regis
 */
public class ResultadoAnalise 
{
    private String codigo;
    private int numIteracoes;
    private int numAcertos;

    public ResultadoAnalise(String codigo) {
        this.codigo = codigo;
        numAcertos = 0;
        numIteracoes = 0;
    }
    
    

    public void isAcertou(boolean acertou)
    {
        if (acertou) numAcertos++;
        numIteracoes++;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public int getNumIteracoes() {
		return numIteracoes;
	}

	public int getNumAcertos() {
        return numAcertos;
    }
    
    public double getTaxa()
    {
    	return ((double) numAcertos / (double) numIteracoes ) * (double) 100;
    }
    
    
            
}
