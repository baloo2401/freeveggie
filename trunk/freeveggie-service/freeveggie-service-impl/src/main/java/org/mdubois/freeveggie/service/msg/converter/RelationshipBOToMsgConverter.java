package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipBOToMsgConverter extends AbstractConverter<RelationshipMsg, RelationshipBO> implements Converter<RelationshipMsg, RelationshipBO> {

    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;

    @Override
    public RelationshipMsg doConvert(RelationshipBO pRelationshipBO) {
        RelationshipMsg msg = new RelationshipMsg();
        msg.setAnswer(pRelationshipBO.getAnswer());
        msg.setId(pRelationshipBO.getId());
        msg.setCreationDate(pRelationshipBO.getCreationDate());
        if (pRelationshipBO.getRecipient() != null) {
            msg.setRecipient(partialUserBOToMsgConverter.convert(pRelationshipBO.getRecipient()));
        }
        if (pRelationshipBO.getSender() != null) {
            msg.setSender(partialUserBOToMsgConverter.convert(pRelationshipBO.getSender()));
        }
        msg.setType(pRelationshipBO.getType());
        msg.setStatus(pRelationshipBO.getStatus());
        msg.setRequest(pRelationshipBO.getRequest());
        return msg;
    }
}
