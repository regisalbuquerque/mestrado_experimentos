package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseSEAAbruptNoise implements BaseFactory{
    
    Base base = new Base("SEAAbruptNoise", "SEAAbruptNoise", "src/main/resources/dados/sea_abrupt_noise.arff", 10000, false);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        List<BaseDrifts> baseDrifts = new ArrayList<>();
        
        //DRIFTS -------------------------------------------
        for (int i = 1001; i< 10000; i=i+1000)
        {
            baseDrifts.add(new BaseDrifts(base, i));
        }
        
        return baseDrifts;
    }

}
