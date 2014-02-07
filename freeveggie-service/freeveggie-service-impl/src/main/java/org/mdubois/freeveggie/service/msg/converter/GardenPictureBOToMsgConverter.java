package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.service.msg.PictureMsg;

/**
 *
 * @author Mickael Dubois
 */
public class GardenPictureBOToMsgConverter extends AbstractConverter<PictureMsg, GardenPictureBO> {

    @Override
    public PictureMsg doConvert(GardenPictureBO pSource) {
        PictureMsg msg = new PictureMsg();
        msg.setCreationDate(new Date(pSource.getCreationDate().getTime()));
        msg.setId(pSource.getId());
        msg.setMimeType(pSource.getMimeType());
        msg.setObjId(pSource.getGarden().getId());
        msg.setPicture(pSource.getPicture());
        return msg;
    }
}
