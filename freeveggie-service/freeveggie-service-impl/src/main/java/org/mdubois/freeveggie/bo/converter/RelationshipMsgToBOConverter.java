package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipMsgToBOConverter implements BusinessObjectConverter<RelationShipBO, RelationShipMsg> {

    @Override
    public RelationShipBO createNew(RelationShipMsg pRelationShipMsg) {
        RelationShipBO relationShipBO = new RelationShipBO();
        relationShipBO.setCreationDate(SystemTime.asDate());
        //Has to be set by the service
        //relationShipBO.setRecipient(pRelationShipMsg.getRecipient()); have to be set by the service
        relationShipBO.setRequest(pRelationShipMsg.getRequest());
        //has to be set by ther service
        //relationShipBO.setSender(pRelationShipMsg.getSender());have to be set by the service 
        relationShipBO.setStatus(RelationshipStatus.PENDING);
        relationShipBO.setType(pRelationShipMsg.getType());
        return relationShipBO;
    }

    @Override
    public void update(RelationShipBO pRelationShipBO, RelationShipMsg pRelationShipMsg) {
        //pRelationShipBO.setCreationDate(SystemTime.asDate()); have to be set by the service
        //has to be set by ther service
        //pRelationShipBO.setRecipient(pRelationShipMsg.getRecipient()); have to be set by the service
        //pRelationShipBO.setRequest(pRelationShipMsg.getRequest()); have to be set by the service
        //has to be set by ther service
        //pRelationShipBO.setSender(pRelationShipMsg.getSender()); have to be set by the service
        //pRelationShipBO.setType(pRelationShipMsg.getType()); have to be set by the service
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
