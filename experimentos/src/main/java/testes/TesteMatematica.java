package testes;

import java.util.Arrays;
import java.util.List;

import br.ufam.metodo.util.calculo.Matematica;

public class TesteMatematica {
	public static void main(String[] args)
	{
		System.out.println("Teste de Matemática!");
		
		List<Integer> lista = Arrays.asList(1,1,2,3,4,5,2,3,2);
		
		System.out.println("Mais frequente: " + Matematica.mostCommon(lista));
		
		System.out.println("Segundo mais frequente: " + Matematica.secondMostCommon(lista));
	}
}
