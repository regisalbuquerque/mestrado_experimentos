package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseForestCovertype implements BaseFactory{
    
    Base base = new Base("ForestCovertype", "ForestCovertype", "src/main/resources/dados/covtypeNorm.arff", 581012, true);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        return null;
    }
    
}
