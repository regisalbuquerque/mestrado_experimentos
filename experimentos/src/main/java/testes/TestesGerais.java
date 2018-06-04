package testes;

public class TestesGerais {
    public static void main(String[] args) {
        System.out.println("Teste...");
        
        int size = 9;
        
        int minimo = 0;
        int maximo = size-1;
        int mediana;
        if (size % 2 == 0)
            mediana  = size/2-1; //Se for PAR pega o menor do meio. Ex: 1 2 (3) 4 5 6
        else
            mediana = size/2; 
        
        
        int medianaMinimo;
        if ((mediana+1) % 2 == 0)
            medianaMinimo  = (mediana+1)/2-1; //Se for PAR pega o menor do meio. Ex: 1 2 (3) 4 5 6
        else
            medianaMinimo = (mediana+1)/2; 

        int distancia = maximo - mediana;
        int medianaMaximo = mediana + distancia/2;
        
        System.out.println("Minimo : " + minimo);
        System.out.println("Med-Min: " + medianaMinimo);
        System.out.println("Mediana: " + mediana);
        System.out.println("Med-Max: " + medianaMaximo);
        System.out.println("Maximo : " + maximo);
        
        
        
    }
}   
