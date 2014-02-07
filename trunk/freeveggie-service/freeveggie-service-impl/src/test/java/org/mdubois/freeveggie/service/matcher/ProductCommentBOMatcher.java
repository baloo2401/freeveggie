package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.mdubois.freeveggie.bo.ProductCommentBO;

/**
 *
 * @author Mickael Dubois
 */
public class ProductCommentBOMatcher extends BusinessObjectMatcher<ProductCommentBO> {

    private static final String NOTES_ARE_NOTE_MATHING = "Note's are note mathing";
    private static final String COMMENTS_ARE_NOTE_MATHING = "Comment's are note mathing";
    private static final String CREATIONS_ARE_NOTE_MATHING = "Creation date's are note mathing";
    private static final String GARDENS_ARE_NOTE_MATHING = "Product's are note mathing";
    private static final String STATUS_ARE_NOTE_MATHING = "Status's are note mathing";
    private static final String WRITERS_ARE_NOTE_MATHING = "Writer's are note mathing";
    private ProductCommentBO gardenCommentBO;

    public ProductCommentBOMatcher(ProductCommentBO pProductCommentBO) {
        super(pProductCommentBO);
        this.gardenCommentBO = pProductCommentBO;
    }

    @Override
    public boolean matches(Object item) {
        if (super.matches(item)) {
            if (item instanceof ProductCommentBO) {
                ProductCommentBO obj = (ProductCommentBO) item;
                if (testComment(obj)) {
                    if (testProduct(obj)) {
                        if (testWriter(obj)) {
                            if (testCreationDate(obj)) {
                                if (testStatus(obj)) {
                                    if (testNote(obj)) {
                                        return true;
                                    }
                                    errorDescription = NOTES_ARE_NOTE_MATHING;
                                }
                                errorDescription = STATUS_ARE_NOTE_MATHING;
                            }
                            errorDescription = CREATIONS_ARE_NOTE_MATHING;
                        }
                        errorDescription = WRITERS_ARE_NOTE_MATHING;
                    }
                    errorDescription = GARDENS_ARE_NOTE_MATHING;
                }
                errorDescription = COMMENTS_ARE_NOTE_MATHING;
            }
            return false;
        }
        return false;


    }

    private boolean testComment(ProductCommentBO obj) {

        if (!StringUtils.isEmpty(gardenCommentBO.getComment()) && !StringUtils.isEmpty(obj.getComment())) {
            if (gardenCommentBO.getComment().trim().equals(obj.getComment().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(gardenCommentBO.getComment()) && StringUtils.isEmpty(obj.getComment())) {
            return true;
        }
        return false;
    }

    private boolean testCreationDate(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getCreationDate() != null && pProductCommentBO.getCreationDate() != null) {
            return DateUtils.isSameDay(this.gardenCommentBO.getCreationDate(), pProductCommentBO.getCreationDate());
        } else if (this.gardenCommentBO.getCreationDate() == null && pProductCommentBO.getCreationDate() == null) {
            return true;
        }
        return false;
    }

    private boolean testStatus(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getStatus() != null && pProductCommentBO.getStatus() != null) {
            return this.gardenCommentBO.getStatus().equals(pProductCommentBO.getStatus());
        } else if (this.gardenCommentBO.getStatus() == null && pProductCommentBO.getStatus() == null) {
            return true;
        }
        return false;
    }

    private boolean testNote(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getNote() != null && pProductCommentBO.getNote() != null) {
            return this.gardenCommentBO.getNote().equals(pProductCommentBO.getNote());
        } else if (this.gardenCommentBO.getNote() == null && pProductCommentBO.getNote() == null) {
            return true;
        }
        return false;
    }

    private boolean testProduct(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getProduct() != null && pProductCommentBO.getProduct() != null) {
            if (this.gardenCommentBO.getProduct().getId() != null && pProductCommentBO.getProduct().getId() != null) {
                return this.gardenCommentBO.getProduct().getId().equals(pProductCommentBO.getProduct().getId());
            }
        } else if (this.gardenCommentBO.getProduct() == null && pProductCommentBO.getProduct() == null) {
            return true;
        }
        return false;
    }

    private boolean testWriter(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getWriter() != null && pProductCommentBO.getWriter() != null) {
            if (this.gardenCommentBO.getWriter().getId() != null && pProductCommentBO.getWriter().getId() != null) {
                return this.gardenCommentBO.getWriter().getId().equals(pProductCommentBO.getWriter().getId());
            }
        } else if (this.gardenCommentBO.getWriter() == null && pProductCommentBO.getWriter() == null) {
            return true;
        }
        return false;
    }

    private boolean testId(ProductCommentBO pProductCommentBO) {
        if (this.gardenCommentBO.getId() != null && pProductCommentBO.getId() != null) {
            return this.gardenCommentBO.getId().equals(pProductCommentBO.getId());
        } else if (this.gardenCommentBO.getId() == null && pProductCommentBO.getId() == null) {
            return true;
        }
        return false;
    }
}
