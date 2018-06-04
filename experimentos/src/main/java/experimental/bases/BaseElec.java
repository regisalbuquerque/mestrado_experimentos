package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseElec implements BaseFactory{
    
    Base base = new Base("Elec", "Elec", "src/main/resources/dados/elec.arff", 45312, true);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        return null;
    }
    
}
