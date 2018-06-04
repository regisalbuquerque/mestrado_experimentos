package experimental.model;

public class Base {
    
    private Integer id;
    
    private String nome;
    
    private String descricao;
    
    private String pathFile;
    
    private int numInstances;
    
    private boolean real;
    
    

    public Base(String nome, String descricao, String pathFile, int numInstances, boolean real) {
        this.nome = nome;
        this.descricao = descricao;
        this.pathFile = pathFile;
        this.numInstances = numInstances;
        this.real = real;
    }

    public Base() {
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getNumInstances() {
        return numInstances;
    }

    public void setNumInstances(int numInstances) {
        this.numInstances = numInstances;
    }

    public boolean isReal() {
        return real;
    }

    public void setReal(boolean real) {
        this.real = real;
    }
    
    
    
    
}
