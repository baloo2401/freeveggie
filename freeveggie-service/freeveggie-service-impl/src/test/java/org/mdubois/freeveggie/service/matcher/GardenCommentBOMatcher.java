package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.mdubois.freeveggie.bo.GardenCommentBO;

/**
 *
 * @author Mickael Dubois
 */
public class GardenCommentBOMatcher extends BusinessObjectMatcher<GardenCommentBO> {

    private static final String NOTES_ARE_NOTE_MATHING = "Note's are note mathing";
    private static final String COMMENTS_ARE_NOTE_MATHING = "Comment's are note mathing";
    private static final String CREATIONS_ARE_NOTE_MATHING = "Creation date's are note mathing";
    private static final String GARDENS_ARE_NOTE_MATHING = "Garden's are note mathing";
    private static final String STATUS_ARE_NOTE_MATHING = "Status's are note mathing";
    private static final String WRITERS_ARE_NOTE_MATHING = "Writer's are note mathing";
    private GardenCommentBO gardenCommentBO;

    public GardenCommentBOMatcher(GardenCommentBO pGardenCommentBO) {
        super(pGardenCommentBO);
        this.gardenCommentBO = pGardenCommentBO;
    }

    @Override
    public boolean matches(Object item) {
        if (super.matches(item)) {
            if (item instanceof GardenCommentBO) {
                GardenCommentBO obj = (GardenCommentBO) item;
                if (testComment(obj)) {
                    if (testGarden(obj)) {
                        if (testWriter(obj)) {
                            if (testCreationDate(obj)) {
                                if (testStatus(obj)) {
                                    if (testNote(obj)) {
                                        return true;
                                    }
                                    errorDescription = NOTES_ARE_NOTE_MATHING;
                                    return false;
                                }
                                errorDescription = STATUS_ARE_NOTE_MATHING;
                                return false;
                            }
                            errorDescription = CREATIONS_ARE_NOTE_MATHING;
                            return false;
                        }
                        errorDescription = WRITERS_ARE_NOTE_MATHING;
                        return false;
                    }
                    errorDescription = GARDENS_ARE_NOTE_MATHING;
                    return false;
                }
                errorDescription = COMMENTS_ARE_NOTE_MATHING;
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean testComment(GardenCommentBO obj) {

        if (!StringUtils.isEmpty(gardenCommentBO.getComment()) && !StringUtils.isEmpty(obj.getComment())) {
            if (gardenCommentBO.getComment().trim().equals(obj.getComment().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(gardenCommentBO.getComment()) && StringUtils.isEmpty(obj.getComment())) {
            return true;
        }
        return false;
    }

    private boolean testCreationDate(GardenCommentBO pGardenCommentBO) {
        if (this.gardenCommentBO.getCreationDate() != null && pGardenCommentBO.getCreationDate() != null) {
            return DateUtils.isSameDay(this.gardenCommentBO.getCreationDate(), pGardenCommentBO.getCreationDate());
        } else if (this.gardenCommentBO.getCreationDate() == null && pGardenCommentBO.getCreationDate() == null) {
            return true;
        }
        return false;
    }

    private boolean testStatus(GardenCommentBO pGardenCommentBO) {
        if (this.gardenCommentBO.getStatus() != null && pGardenCommentBO.getStatus() != null) {
            return this.gardenCommentBO.getStatus().equals(pGardenCommentBO.getStatus());
        } else if (this.gardenCommentBO.getStatus() == null && pGardenCommentBO.getStatus() == null) {
            return true;
        }
        return false;
    }

    private boolean testNote(GardenCommentBO pGardenCommentBO) {
        if (this.gardenCommentBO.getNote() != null && pGardenCommentBO.getNote() != null) {
            return this.gardenCommentBO.getNote().equals(pGardenCommentBO.getNote());
        } else if (this.gardenCommentBO.getNote() == null && pGardenCommentBO.getNote() == null) {
            return true;
        }
        return false;
    }

    private boolean testGarden(GardenCommentBO pGardenCommentBO) {
        if (this.gardenCommentBO.getGarden() != null && pGardenCommentBO.getGarden() != null) {
            if (this.gardenCommentBO.getGarden().getId() != null && pGardenCommentBO.getGarden().getId() != null) {
                return this.gardenCommentBO.getGarden().getId().equals(pGardenCommentBO.getGarden().getId());
            }
        } else if (this.gardenCommentBO.getGarden() == null && pGardenCommentBO.getGarden() == null) {
            return true;
        }
        return false;
    }

    private boolean testWriter(GardenCommentBO pGardenCommentBO) {
        if (this.gardenCommentBO.getWriter() != null && pGardenCommentBO.getWriter() != null) {
            if (this.gardenCommentBO.getWriter().getId() != null && pGardenCommentBO.getWriter().getId() != null) {
                return this.gardenCommentBO.getWriter().getId().equals(pGardenCommentBO.getWriter().getId());
            }
        } else if (this.gardenCommentBO.getWriter() == null && pGardenCommentBO.getWriter() == null) {
            return true;
        }
        return false;
    }
}
