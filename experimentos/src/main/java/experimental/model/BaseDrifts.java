package experimental.model;

public class BaseDrifts {
    
    private Integer id;
    
    private Base baseId;
    
    private Integer iteracao;

    public BaseDrifts(Base baseId, Integer iteracao) {
        this.baseId = baseId;
        this.iteracao = iteracao;
    }

    public BaseDrifts() {
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Base getBaseId() {
        return baseId;
    }

    public void setBaseId(Base baseId) {
        this.baseId = baseId;
    }

    public Integer getIteracao() {
        return iteracao;
    }

    public void setIteracao(Integer iteracao) {
        this.iteracao = iteracao;
    }

    

}
