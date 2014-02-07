package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.service.msg.PictureMsg;

/**
 *
 * @author Mickael Dubois
 */
public class ProductPictureBOToMsgConverter extends AbstractConverter<PictureMsg, ProductPictureBO> {

    @Override
    public PictureMsg doConvert(ProductPictureBO pSource) {
        PictureMsg msg = new PictureMsg();
        msg.setCreationDate(new Date(pSource.getCreationDate().getTime()));
        msg.setId(pSource.getId());
        msg.setMimeType(pSource.getMimeType());
        msg.setObjId(pSource.getProduct().getId());
        msg.setPicture(pSource.getPicture());
        return msg;
    }
}
