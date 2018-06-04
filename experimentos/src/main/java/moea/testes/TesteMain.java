/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moea.testes;

import org.moeaframework.Executor;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;

/**
 *
 * @author regis
 */
public class TesteMain {
    public static void main(String[] args) {
        NondominatedPopulation result = new Executor().
                withProblemClass(TesteMOEA.class)
                .withAlgorithm("NSGAII")
                .withMaxEvaluations(1)
                .run();
        
        for (Solution solution : result) {
            System.out.println(solution.getObjective(0) + " " + -solution.getObjective(1));
        }
        
        new Plot().add("NSGAII", result).show();
    }
}
