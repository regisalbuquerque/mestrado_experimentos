package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public class BaseSpam implements BaseFactory{
    
    Base base = new Base("Spam", "Spam", "src/main/resources/dados/spam.arff", 9324, true);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        return null;
    }
    
    
    
}
