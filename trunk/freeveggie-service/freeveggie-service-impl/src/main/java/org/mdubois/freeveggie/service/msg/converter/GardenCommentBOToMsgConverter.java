package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenCommentBOToMsgConverter extends AbstractConverter<GardenCommentMsg, GardenCommentBO> implements Converter<GardenCommentMsg, GardenCommentBO> {

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
    public GardenCommentMsg doConvert(GardenCommentBO pGardenCommentBO) {
        GardenCommentMsg msg = new GardenCommentMsg();
        msg.setId(pGardenCommentBO.getId());
        msg.setCreationDate(pGardenCommentBO.getCreationDate());
        msg.setStatus(pGardenCommentBO.getStatus());
        if (pGardenCommentBO.getGarden() != null) {
            msg.setGarden(gardenBOToMsgConverter.convert(pGardenCommentBO.getGarden()));
        }
        if (pGardenCommentBO.getWriter() != null) {
            msg.setWriter(partialUserBOToMsgConverter.convert(pGardenCommentBO.getWriter()));
        }
        msg.setComment(pGardenCommentBO.getComment());
        msg.setNote(pGardenCommentBO.getNote());
        return msg;
    }
}
