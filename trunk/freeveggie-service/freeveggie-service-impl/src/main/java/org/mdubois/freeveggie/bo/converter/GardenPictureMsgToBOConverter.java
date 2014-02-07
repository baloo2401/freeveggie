package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.PictureMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenPictureMsgToBOConverter implements BusinessObjectConverter<GardenPictureBO, PictureMsg> {

    @Override
    public GardenPictureBO createNew(PictureMsg pGardenRequestMsg) {
        GardenPictureBO gardenPictureBO = new GardenPictureBO();
        gardenPictureBO.setCreationDate(SystemTime.asDate());
        gardenPictureBO.setMimeType(pGardenRequestMsg.getMimeType());
        gardenPictureBO.setPicture(pGardenRequestMsg.getPicture());
        //gardenPictureBO.setGarden(null); this should be done by the service
        return gardenPictureBO;
    }

    @Override
    public void update(GardenPictureBO pGardenRequestBO, PictureMsg pGardenRequestMsg) {
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
