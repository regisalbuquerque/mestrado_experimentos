package testes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TesteInversaoLista {
	public static void main(String args[])
	{
		System.out.println("Teste de inversão de lista");
		
		List<List<Integer>> listaPredicoes = new ArrayList<List<Integer>>();
		listaPredicoes.add(Arrays.asList(1,1,0,0,1)); //Iteracao 1
		listaPredicoes.add(Arrays.asList(1,1,1,1,1)); //Iteracao 2
		listaPredicoes.add(Arrays.asList(0,1,0,1,0)); //Iteracao 3
		
		Integer[][] arrayTransposto = new Integer[listaPredicoes.get(0).size()][listaPredicoes.size()];
		
		for (int c = 0; c < listaPredicoes.get(0).size(); c++)
		{
			for(int i = 0; i < listaPredicoes.size(); i++)
			{
				arrayTransposto[c][i] = listaPredicoes.get(i).get(c); //Invertendo
			}
		}
		
		for (int c = 0; c < arrayTransposto.length; c++)
		{
			for(int i = 0; i < arrayTransposto[c].length; i++)
			{
				System.out.print(" " + arrayTransposto[c][i]);
			}
			System.out.println("");
		}
		
		
		
//		1, 1, 0
//		1, 1, 1
//		0, 1, 0
//		0, 1, 1
//		1, 1, 0
	
		
		
	}
}
