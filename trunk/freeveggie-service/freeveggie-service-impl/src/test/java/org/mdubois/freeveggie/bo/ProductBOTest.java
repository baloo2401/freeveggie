package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class ProductBOTest extends BusinessObjectTest<ProductBO>{

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(GardenBO.class, new GardenBO());
        toReturn.put(RefProductBO.class, new RefProductBO());
        toReturn.put(ExchangeType.class, ExchangeType.GIVES);
        toReturn.put(CultureMode.class, CultureMode.BALCONY);
        toReturn.put(CultureType.class, CultureType.BIO);
        toReturn.put(Status.class, Status.CREATED);
        return toReturn;
    }
}
