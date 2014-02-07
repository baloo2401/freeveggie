package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.ProductBO;

/**
 *
 * @author Mickael Dubois
 */
public class GardenBOMatcher extends BusinessObjectMatcher<GardenBO> {

    private static final String NAMES_ARE_NOTE_MATHING = "Name's are not mathing";
    private static final String COMMENTS_ARE_NOTE_MATHING = "Comment's are not mathing";
    private static final String ADDRESSS_ARE_NOTE_MATHING = "Address's are not mathing";
    private static final String LIKES_ARE_NOTE_MATHING = "Garden's are not mathing";
    private static final String DESCRIPTION_ARE_NOTE_MATHING = "Description's are not mathing";
    private static final String WRITERS_ARE_NOTE_MATHING = "Writer's are not mathing";
    private static final String PRODUCTS_ARE_NOTE_MATHING = "Product's are not matching";
    private static final String PICTURE_FILENAME_ARE_NOTE_MATHING = "Picture filename are not matching";
    /**
     * {@link GardenBO}
     */
    private GardenBO gardenBO;

    public GardenBOMatcher(GardenBO pGardenBO) {
        super(pGardenBO);
        this.gardenBO = pGardenBO;
    }

    @Override
    public boolean matches(Object item) {
        if (super.matches(item)) {
            if (item instanceof GardenBO) {
                GardenBO obj = (GardenBO) item;
                if (testComments(obj)) {
                    if (testLikes(obj)) {
                        if (testOwner(obj)) {
                            if (testAddress(obj)) {
                                if (testDescription(obj)) {
                                    if (testName(obj)) {
                                            if (testProducts(obj)) {
                                                //TODO : Matche other property
                                                return true;
                                            }
                                            errorDescription = PRODUCTS_ARE_NOTE_MATHING;
                                            return false;
                                        }
                                    errorDescription = NAMES_ARE_NOTE_MATHING;
                                    return false;
                                }
                                errorDescription = DESCRIPTION_ARE_NOTE_MATHING;
                                return false;
                            }
                            errorDescription = ADDRESSS_ARE_NOTE_MATHING;
                            return false;
                        }
                        errorDescription = WRITERS_ARE_NOTE_MATHING;
                        return false;
                    }
                    errorDescription = LIKES_ARE_NOTE_MATHING;
                    return false;
                }
                errorDescription = COMMENTS_ARE_NOTE_MATHING;
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean testProducts(GardenBO pGardenBO) {
        if (pGardenBO.getProducts() != null && gardenBO.getProducts() != null) {
            if (pGardenBO.getProducts().size() == gardenBO.getProducts().size()) {
                boolean equalsProductFind = false;
                for (ProductBO pGardenCommnent : pGardenBO.getProducts()) {
                    for (ProductBO gardenProduct : gardenBO.getProducts()) {
                        if (new ProductBOMatcher(gardenProduct).matches(pGardenCommnent)) {
                            equalsProductFind = true;
                            break;
                        }
                    }
                    if (!equalsProductFind) {
                        return false;
                    }
                    equalsProductFind = false;
                }
                return true;
            }
        } else if (pGardenBO.getProducts() == null && gardenBO.getProducts() == null) {
            return true;
        }
        return false;
    }

    private boolean testComments(GardenBO pGardenBO) {
        if (pGardenBO.getComments() != null && gardenBO.getComments() != null) {
            if (pGardenBO.getComments().size() == gardenBO.getComments().size()) {
                boolean equalsCommentFind = false;
                for (GardenCommentBO pGardenCommnent : pGardenBO.getComments()) {
                    for (GardenCommentBO gardenComment : gardenBO.getComments()) {
                        if (new GardenCommentBOMatcher(gardenComment).matches(pGardenCommnent)) {
                            equalsCommentFind = true;
                            break;
                        }
                    }
                    if (!equalsCommentFind) {
                        return false;
                    }
                    equalsCommentFind = false;
                }
                return true;
            }
        } else if (pGardenBO.getComments() == null && gardenBO.getComments() == null) {
            return true;
        }
        return false;
    }

    private boolean testLikes(GardenBO pGardenBO) {
        if (pGardenBO.getLikes() != null && gardenBO.getLikes() != null) {
            if (pGardenBO.getLikes().size() == gardenBO.getLikes().size()) {
                boolean equalsLikeFind = false;
                for (GardenLikeBO pGardenCommnent : pGardenBO.getLikes()) {
                    for (GardenLikeBO gardenLike : gardenBO.getLikes()) {
                        if (new GardenLikeBOMatcher(gardenLike).matches(pGardenCommnent)) {
                            equalsLikeFind = true;
                            break;
                        }
                    }
                    if (!equalsLikeFind) {
                        return false;
                    }
                    equalsLikeFind = false;
                }
                return true;
            }
        } else if (pGardenBO.getLikes() == null && gardenBO.getLikes() == null) {
            return true;
        }
        return false;
    }

    private boolean testOwner(GardenBO pGardenBO) {
        if (this.gardenBO.getOwner() != null && pGardenBO.getOwner() != null) {
            if (this.gardenBO.getOwner().getId() != null && pGardenBO.getOwner().getId() != null) {
                return this.gardenBO.getOwner().getId().equals(pGardenBO.getOwner().getId());
            }
        } else if (this.gardenBO.getOwner() == null && pGardenBO.getOwner() == null) {
            return true;
        }
        return false;
    }

    private boolean testName(GardenBO pGardenBO) {

        if (!StringUtils.isEmpty(gardenBO.getName()) && !StringUtils.isEmpty(pGardenBO.getName())) {
            if (gardenBO.getName().trim().equals(pGardenBO.getName().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(gardenBO.getName()) && StringUtils.isEmpty(pGardenBO.getName())) {
            return true;
        }
        return false;
    }

    private boolean testDescription(GardenBO pGardenBO) {
        if (!StringUtils.isEmpty(gardenBO.getDescription()) && !StringUtils.isEmpty(pGardenBO.getDescription())) {
            if (gardenBO.getDescription().trim().equals(pGardenBO.getDescription().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(gardenBO.getDescription()) && StringUtils.isEmpty(pGardenBO.getDescription())) {
            return true;
        }
        return false;
    }

    private boolean testAddress(GardenBO pGardenBO) {
        if (pGardenBO.getAddress() != null && gardenBO.getAddress() != null) {
            return new AddressBOMatcher(pGardenBO.getAddress()).matches(gardenBO.getAddress());
        } else if (pGardenBO.getAddress() == null && gardenBO.getAddress() == null) {
            return true;
        }
        return false;
    }
}
