package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.framework.bo.BusinessObject;

/**
 *
 * @author Mickael Dubois
 */
public abstract class BusinessObjectMatcher<T> extends BaseMatcher<T> {

    private static final String IDS_ARE_NOTE_MATHING = "Id's are note mathing";
    private static final String OBJECT_IS_NOT_A_BUSINESS_OBJECT = "The object is not a BusinnessObject";

    private BusinessObject businessObject;

    protected String errorDescription = "";

    public BusinessObjectMatcher(BusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    @Override
    public boolean matches(Object item) {
        if (item instanceof BusinessObject) {
            BusinessObject obj = (BusinessObject) item;
                if (testId(obj)) {
                    return true;
                }
                errorDescription = IDS_ARE_NOTE_MATHING;
        }
        errorDescription = OBJECT_IS_NOT_A_BUSINESS_OBJECT;
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(errorDescription);
    }

     private boolean testId(BusinessObject pBusinessObject) {
        if (this.businessObject.getId() != null && pBusinessObject.getId() != null) {
            return this.businessObject.getId().equals(pBusinessObject.getId());
        }else if (this.businessObject.getId() == null && pBusinessObject.getId() == null) {
            return true;
        }
        return false;
    }
}
