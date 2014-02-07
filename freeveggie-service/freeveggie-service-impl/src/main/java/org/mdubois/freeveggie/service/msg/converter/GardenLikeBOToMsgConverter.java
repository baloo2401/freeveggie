package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenLikeBOToMsgConverter extends AbstractConverter<GardenLikeMsg, GardenLikeBO> implements Converter<GardenLikeMsg, GardenLikeBO> {

    /**
     * {@link Converter<AddressMsg, AddressBO>}
     */
    @Inject
    private Converter<GardenMsg, GardenBO> gardenBOToMsgConverter;
    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;

    @Override
    public GardenLikeMsg doConvert(GardenLikeBO pGardenLikeBO) {
        GardenLikeMsg msg = new GardenLikeMsg();
        msg.setId(pGardenLikeBO.getId());
        msg.setCreationDate(pGardenLikeBO.getCreationDate());
        msg.setStatus(pGardenLikeBO.getStatus());
        if (pGardenLikeBO.getGarden() != null) {
            msg.setGarden(gardenBOToMsgConverter.convert(pGardenLikeBO.getGarden()));
        }
        if (pGardenLikeBO.getWriter() != null) {
            msg.setWriter(partialUserBOToMsgConverter.convert(pGardenLikeBO.getWriter()));
        }
        return msg;
    }
}
