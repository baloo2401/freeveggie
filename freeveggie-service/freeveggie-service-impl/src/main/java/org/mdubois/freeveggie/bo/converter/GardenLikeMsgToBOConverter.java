package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenLikeMsgToBOConverter implements BusinessObjectConverter<GardenLikeBO,GardenLikeMsg> {

    @Override
    public GardenLikeBO createNew(GardenLikeMsg pGardenCommentMsg) {
        GardenLikeBO gardenLikeBO = new GardenLikeBO();
        gardenLikeBO.setCreationDate(SystemTime.asDate());
        //gardenLikeBO.setGarden(null); have to be set by the service
        gardenLikeBO.setStatus(EvaluationStatus.SETTED);
        //gardenLikeBO.setWriter(null); have to be set by the service
        return gardenLikeBO;
    }

    @Override
    public void update(GardenLikeBO pGardenLikeBO, GardenLikeMsg pGardenCommentMsg) {
        //pGardenLikeBO.setCreationDate(SystemTime.asDate()); should not be change
        //gardenLikeBO.setGarden(null); have to be set by the service
        //pGardenLikeBO.setStatus(pGardenCommentMsg.getStatus()); have to be set by the service
        //gardenLikeBO.setWriter(null); have to be set by the service
        throw new UnsupportedOperationException("This methode should never be called");
    }

}
