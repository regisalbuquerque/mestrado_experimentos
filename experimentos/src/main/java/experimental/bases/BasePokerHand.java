package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public class BasePokerHand implements BaseFactory{
    
    Base base = new Base("PokerHand", "PokerHand", "src/main/resources/dados/poker-lsn.arff", 829200, true);

    @Override
    public Base getBase() {
        return base;
    }

    @Override
    public List<BaseDrifts> getBaseDrifts() {
        return null;
    }
    
}
