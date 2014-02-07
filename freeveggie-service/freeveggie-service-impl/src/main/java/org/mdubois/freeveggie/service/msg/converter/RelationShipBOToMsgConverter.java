package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RelationShipBOToMsgConverter extends AbstractConverter<RelationShipMsg, RelationShipBO> implements Converter<RelationShipMsg, RelationShipBO> {

    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;

    @Override
    public RelationShipMsg doConvert(RelationShipBO pRelationShipBO) {
        RelationShipMsg msg = new RelationShipMsg();
        msg.setAnswer(pRelationShipBO.getAnswer());
        msg.setId(pRelationShipBO.getId());
        msg.setCreationDate(pRelationShipBO.getCreationDate());
        if (pRelationShipBO.getRecipient() != null) {
            msg.setRecipient(partialUserBOToMsgConverter.convert(pRelationShipBO.getRecipient()));
        }
        if (pRelationShipBO.getSender() != null) {
            msg.setSender(partialUserBOToMsgConverter.convert(pRelationShipBO.getSender()));
        }
        msg.setType(pRelationShipBO.getType());
        msg.setStatus(pRelationShipBO.getStatus());
        msg.setRequest(pRelationShipBO.getRequest());
        return msg;
    }
}
