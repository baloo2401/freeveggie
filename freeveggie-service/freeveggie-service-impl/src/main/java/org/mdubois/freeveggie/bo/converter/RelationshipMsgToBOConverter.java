package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipMsgToBOConverter implements BusinessObjectConverter<RelationshipBO, RelationshipMsg> {

    @Override
    public RelationshipBO createNew(RelationshipMsg pRelationshipMsg) {
        RelationshipBO relationshipBO = new RelationshipBO();
        relationshipBO.setCreationDate(SystemTime.asDate());
        //Has to be set by the service
        //relationshipBO.setRecipient(pRelationshipMsg.getRecipient()); have to be set by the service
        relationshipBO.setRequest(pRelationshipMsg.getRequest());
        //has to be set by ther service
        //relationshipBO.setSender(pRelationshipMsg.getSender());have to be set by the service 
        relationshipBO.setStatus(RelationshipStatus.PENDING);
        relationshipBO.setType(pRelationshipMsg.getType());
        return relationshipBO;
    }

    @Override
    public void update(RelationshipBO pRelationshipBO, RelationshipMsg pRelationshipMsg) {
        //pRelationshipBO.setCreationDate(SystemTime.asDate()); have to be set by the service
        //has to be set by ther service
        //pRelationshipBO.setRecipient(pRelationshipMsg.getRecipient()); have to be set by the service
        //pRelationshipBO.setRequest(pRelationshipMsg.getRequest()); have to be set by the service
        //has to be set by ther service
        //pRelationshipBO.setSender(pRelationshipMsg.getSender()); have to be set by the service
        //pRelationshipBO.setType(pRelationshipMsg.getType()); have to be set by the service
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
