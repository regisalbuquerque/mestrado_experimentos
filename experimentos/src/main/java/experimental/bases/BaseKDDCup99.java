package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseKDDCup99 implements BaseFactory{
    
    Base base = new Base("KDDCup99", "KDDCup99", "src/main/resources/dados/KDDCup99_10.arff", 489844, true);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        return null;
    }
    
}
