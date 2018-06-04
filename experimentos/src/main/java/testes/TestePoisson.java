package testes;

import br.ufam.metodo.util.calculo.Matematica;


public class TestePoisson {
    public static void main(String[] args) {
        System.out.println("Poison " + Matematica.getPoisson(0.25));
        System.out.println("Poison " + Matematica.getPoisson(0.5));
        System.out.println("Poison " + Matematica.getPoisson(0.75));
        System.out.println("Poison " + Matematica.getPoisson(0.95));
        System.out.println("Poison " + Matematica.getPoisson(1));
    }
}
