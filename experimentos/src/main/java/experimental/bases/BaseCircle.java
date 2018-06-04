package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseCircle implements BaseFactory{

    Base base = new Base("Circle", "Circle", "src/main/resources/dados/circle.arff", 4000, false);
    
    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        List<BaseDrifts> baseDrifts = new ArrayList<>();
        
        //DRIFTS -------------------------------------------
        for (int i = 1001; i< 4000; i=i+1000)
        {
            baseDrifts.add(new BaseDrifts(base, i));
        }
        
        return baseDrifts;
    }
    
}
