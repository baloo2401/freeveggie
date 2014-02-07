package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenCommentMsgToBOConverter implements BusinessObjectConverter<GardenCommentBO,GardenCommentMsg> {

    @Override
    public GardenCommentBO createNew(GardenCommentMsg pGardenCommentMsg) {
        GardenCommentBO gardenCommentBO = new GardenCommentBO();
        gardenCommentBO.setComment(pGardenCommentMsg.getComment());
        gardenCommentBO.setCreationDate(SystemTime.asDate());
        //gardenCommentBO.setGarden(null); have to be set by the service
        gardenCommentBO.setNote(pGardenCommentMsg.getNote());
        gardenCommentBO.setStatus(EvaluationStatus.SETTED);
        //gardenCommentBO.setWriter(null); have to be set by the service
        return gardenCommentBO;
    }

    @Override
    public void update(GardenCommentBO pGardenCommentBO, GardenCommentMsg pGardenCommentMsg) {
        pGardenCommentBO.setComment(pGardenCommentMsg.getComment());
        //pGardenCommentBO.setCreationDate(SystemTime.asDate()); should not be change
        //gardenCommentBO.setGarden(null); have to be set by the service
        pGardenCommentBO.setNote(pGardenCommentMsg.getNote());
        //gardenCommentBO.setWriter(null); have to be set by the service
    }

}
