/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.util.Random;
import moa.core.MiscUtils;

/**
 *
 * @author regis
 */
public class TesteAleatoriedadePoison {
    public static void main(String[] args) {
        Random classifierRandom = new Random(1);
        
        for (int i = 0; i < 20; i++) {
            int k = MiscUtils.poisson(1, classifierRandom);
            System.out.println("k="+k);
        }
    }
}
