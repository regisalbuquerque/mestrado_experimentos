package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseLine implements BaseFactory{
    
    Base base = new Base("Line", "Line", "src/main/resources/dados/line.arff", 2000, false);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        List<BaseDrifts> baseLineDrifts = new ArrayList<>();
        baseLineDrifts.add(new BaseDrifts(base, 1001));
        
        return baseLineDrifts;
        
    }
    
    
    
}
