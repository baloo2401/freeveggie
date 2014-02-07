package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.GardenLikeBO;

/**
 *
 * @author Mickael Dubois
 */
public class GardenLikeBOMatcher extends BaseMatcher<GardenLikeBO> {

    /**
     * {@link GardenLikeBO}
     */
    private GardenLikeBO gardenLikeBO;

    public GardenLikeBOMatcher(GardenLikeBO pGardenLikeBO) {
        this.gardenLikeBO = pGardenLikeBO;
    }

    @Override
    public boolean matches(Object o) {
        if (o instanceof GardenLikeBO) {
            GardenLikeBO gardenLikeBOToTest = (GardenLikeBO) o;
            if (gardenLikeBOToTest.getGarden().getId().equals(gardenLikeBO.getGarden().getId())) {
                if (gardenLikeBOToTest.getWriter().getId().equals(gardenLikeBO.getWriter().getId())) {
                    if (gardenLikeBOToTest.getStatus().equals(gardenLikeBO.getStatus())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
