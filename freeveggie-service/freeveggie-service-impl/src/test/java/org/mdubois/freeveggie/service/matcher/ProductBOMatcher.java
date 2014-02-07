package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.*;

/**
 *
 * @author Mickael Dubois
 */
public class ProductBOMatcher extends BusinessObjectMatcher<ProductBO> {

    private static final String NAMES_ARE_NOTE_MATHING = "Name's are not mathing";
    private static final String COMMENTS_ARE_NOTE_MATHING = "Comment's are not mathing";
    private static final String EXCHANGE_TYPE_ARE_NOTE_MATHING = "Exchange type's are not mathing";
    private static final String LIKES_ARE_NOTE_MATHING = "Product's are not mathing";
    private static final String DESCRIPTION_ARE_NOTE_MATHING = "Description's are not mathing";
    private static final String CULTURE_TYPE_ARE_NOTE_MATHING = "Culuture type's are not mathing";
    private static final String CULTURE_MODE_ARE_NOTE_MATHING = "Culuture mode's are not mathing";
    private static final String GARDEN_ARE_NOTE_MATHING = "Garden's are not matching";
    private static final String PICTURE_FILENAME_ARE_NOTE_MATHING = "Picture filename are not matching";
    private ProductBO productBO;

    public ProductBOMatcher(ProductBO pProductBO) {
        super(pProductBO);
        this.productBO = pProductBO;
    }

    @Override
    public boolean matches(Object item) {
        if (super.matches(item)) {
            if (item instanceof ProductBO) {
                ProductBO lProductBO = (ProductBO) item;
                if (testComments(lProductBO)) {
                    if (testLikes(lProductBO)) {
                        if (testCultureMode(lProductBO)) {
                            if (testCultureType(lProductBO)) {
                                if (testExchangeType(lProductBO)) {
                                    if (testDescription(lProductBO)) {
                                        if (testName(lProductBO)) {
                                                if (testGarden(lProductBO)) {
                                                    return true;
                                                }
                                                errorDescription = GARDEN_ARE_NOTE_MATHING;
                                                return false;
                                        }
                                        errorDescription = NAMES_ARE_NOTE_MATHING;
                                        return false;
                                    }
                                    errorDescription = DESCRIPTION_ARE_NOTE_MATHING;
                                    return false;
                                }
                                errorDescription = EXCHANGE_TYPE_ARE_NOTE_MATHING;
                                return false;
                            }
                            errorDescription = CULTURE_TYPE_ARE_NOTE_MATHING;
                            return false;
                        }
                        errorDescription = CULTURE_MODE_ARE_NOTE_MATHING;
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

    private boolean testComments(ProductBO pProductBO) {
        if (pProductBO.getComments() != null && productBO.getComments() != null) {
            if (pProductBO.getComments().size() == productBO.getComments().size()) {
                boolean equalsCommentFind = false;
                for (ProductCommentBO pProductCommnent : pProductBO.getComments()) {
                    for (ProductCommentBO productComment : productBO.getComments()) {
                        if (new ProductCommentBOMatcher(productComment).matches(pProductCommnent)) {
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
        } else if (pProductBO.getComments() == null && productBO.getComments() == null) {
            return true;
        }
        return false;
    }

    private boolean testLikes(ProductBO pProductBO) {
        if (pProductBO.getLikes() != null && productBO.getLikes() != null) {
            if (pProductBO.getLikes().size() == productBO.getLikes().size()) {
                boolean equalsLikeFind = false;
                for (ProductLikeBO pProductCommnent : pProductBO.getLikes()) {
                    for (ProductLikeBO productLike : productBO.getLikes()) {
                        if (new ProductLikeBOMatcher(productLike).matches(pProductCommnent)) {
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
        } else if (pProductBO.getLikes() == null && productBO.getLikes() == null) {
            return true;
        }
        return false;
    }

    private boolean testName(ProductBO pProductBO) {

        if (!StringUtils.isEmpty(productBO.getName()) && !StringUtils.isEmpty(pProductBO.getName())) {
            if (productBO.getName().trim().equals(pProductBO.getName().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(productBO.getName()) && StringUtils.isEmpty(pProductBO.getName())) {
            return true;
        }
        return false;
    }

    private boolean testDescription(ProductBO pProductBO) {
        if (!StringUtils.isEmpty(productBO.getDescription()) && !StringUtils.isEmpty(pProductBO.getDescription())) {
            if (productBO.getDescription().trim().equals(pProductBO.getDescription().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(productBO.getDescription()) && StringUtils.isEmpty(pProductBO.getDescription())) {
            return true;
        }
        return false;
    }

    private boolean testCultureMode(ProductBO pProductBO) {
        if (pProductBO.getCultureMode() != null && productBO.getCultureMode() != null) {
            if (pProductBO.getCultureMode().equals(productBO.getCultureMode())) {
                return true;
            }
        } else if (pProductBO.getCultureMode() == null && productBO.getCultureMode() == null) {
            return true;
        }
        return false;
    }

    private boolean testCultureType(ProductBO pProductBO) {
        if (pProductBO.getCultureType() != null && productBO.getExchangeType() != null) {
            if (pProductBO.getCultureType().equals(productBO.getExchangeType())) {
                return true;
            }
        } else if (pProductBO.getCultureType() == null && productBO.getExchangeType() == null) {
            return true;
        }
        return false;
    }

    private boolean testExchangeType(ProductBO pProductBO) {
        if (pProductBO.getExchangeType() != null && productBO.getCultureType() != null) {
            if (pProductBO.getExchangeType().equals(productBO.getCultureType())) {
                return true;
            }
        } else if (pProductBO.getExchangeType() == null && productBO.getCultureType() == null) {
            return true;
        }
        return false;
    }

    private boolean testGarden(ProductBO pProductBO) {
        if (pProductBO.getGarden() != null && productBO.getGarden() != null) {
            if (pProductBO.getGarden().getId().equals(productBO.getGarden().getId())) {
                return true;
            }
        } else if (pProductBO.getGarden() == null && productBO.getGarden() == null) {
            return true;
        }
        return false;
    }
}
