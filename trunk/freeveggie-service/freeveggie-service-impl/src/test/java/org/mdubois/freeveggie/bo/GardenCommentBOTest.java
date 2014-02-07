package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class GardenCommentBOTest extends BusinessObjectTest<GardenCommentBO>{

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(GardenBO.class, new GardenBO());
        toReturn.put(PartialUserBO.class, new PartialUserBO());
        toReturn.put(EvaluationStatus.class, EvaluationStatus.SETTED);
        toReturn.put(EvaluationNote.class, EvaluationNote.GOOD);
        return toReturn;
    }
}
