package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.GardenBO;

/**
 *
 * @author Mickael Dubois
 */
public class GardenMatcher extends BaseMatcher<GardenBO> {

    private GardenBO gardenBO;
    
    public GardenMatcher(GardenBO pGardenBO) {
        this.gardenBO = pGardenBO;
    }

    @Override
    public boolean matches(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void describeTo(Description description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
