package testes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TesteClasseInterna {
    
    public static class Conjunto implements Comparable<Conjunto>
    {
        Double valor;
        String nome;

        Conjunto(Double valor, String nome)
        {
            this.valor = valor;
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "Conjunto{" + "valor=" + valor + ", nome=" + nome + '}';
        }

        public static Comparator<Conjunto> ConjuntoComparator = new Comparator<Conjunto>() {
            @Override
            public int compare(Conjunto o1, Conjunto o2) {
                return o1.valor.compareTo(o2.valor);
            }
        };

        @Override
        public int compareTo(Conjunto o) {
            return this.valor.compareTo(o.valor);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("experimentos.preliminares.TesteClasseInterna.main()");
        
        List<Conjunto> lista = new ArrayList<>();
        lista.add(new Conjunto(5.0, "Cinco"));
        lista.add(new Conjunto(1.0, "Um"));
        lista.add(new Conjunto(10.0, "Dez"));
        lista.add(new Conjunto(7.0, "Sete"));
        lista.add(new Conjunto(8.0, "Oito"));
        lista.add(new Conjunto(5.0, "Zero"));
        lista.add(new Conjunto(3.0, "Três"));
        
        for (Conjunto conjunto : lista) {
            System.out.println(conjunto);
        }
        
        Collections.sort(lista); //.sort(lista, Conjunto.ConjuntoComparator);
        
        System.out.println("ORDENADO:");
        for (Conjunto conjunto : lista) {
            System.out.println(conjunto);
        }
        
        
        System.out.println("Size:" + lista.size());
        
        int median_index;
        if (lista.size() % 2 == 0)
            median_index = lista.size()/2-1;
        else
            median_index = lista.size()/2;
        
        System.out.println("Median Index:" + median_index);
        
        System.out.println("Median :" + lista.get(median_index));
        
    }
}
