package experimental.bases;

import experimental.model.Base;
import experimental.model.BaseDrifts;
import java.util.List;

/**
 *
 * @author regis
 */
public interface BaseFactory {
    public Base getBase();
    public List<BaseDrifts> getBaseDrifts();
}
