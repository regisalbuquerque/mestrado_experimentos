package moea.testes;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

/**
 *
 * @author regis
 */
public class TesteMOEA extends AbstractProblem{
    
    public TesteMOEA(){
        super(1, 2);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(getNumberOfVariables(), 
        getNumberOfObjectives());
 
        for (int i = 0; i < getNumberOfVariables(); i++) {
          solution.setVariable(i, EncodingUtils.newInt(1, 12));
        }

        return solution;
    }
    
    @Override
    public void evaluate(Solution solution) {
    //double[] x = CoreUtils.castVariablesToDoubleArray(solution);
    int x = EncodingUtils.getInt(solution.getVariable(0));
    double[] f = new double[numberOfObjectives];
 
        switch (x) {
            case 1:
                f[0] = 0.2;
                f[1] = 97.4;
                break;
            case 2:
                f[0] = 0.08;
                f[1] = 97;
                break;
            case 3:
                f[0] = 0.08;
                f[1] = 97;
                break;
            case 4:
                f[0] = 0.08;
                f[1] = 97;
                break;
            case 5:
                f[0] = 0.8;
                f[1] = 97;
                break;
            case 6:
                f[0] = 0.2;
                f[1] = 97.4;
                break;
            case 7:
                f[0] = 0.8;
                f[1] = 96.2;
                break;
            case 8:
                f[0] = 0.16;
                f[1] = 92.6;
                break;
            case 9:
                f[0] = 0.2;
                f[1] = 89.4;
                break;
            case 10:
                f[0] = 0.32;
                f[1] = 50.2;
                break;
            case 11:
                f[0] = 0.32;
                f[1] = 52.6;
                break;
            case 12:
                f[0] = 0.24;
                f[1] = 50;
                break;
            default:
                break;
        }
        
        //solution.setObjectives(f);
        solution.setObjective(0, f[0]);
        solution.setObjective(1, -f[1]);
    }


    
    
    
}
