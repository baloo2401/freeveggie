package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.RelationShipBO;

/**
 *
 * @author Mickael Dubois
 */
public class RelationShipMatcher extends BusinessObjectMatcher<RelationShipBO> {

    private RelationShipBO relationShipBO;

    public RelationShipMatcher(RelationShipBO pRelationShipBO) {
        super(pRelationShipBO);
        this.relationShipBO = pRelationShipBO;
    }

    @Override
    public boolean matches(Object pRelationshipBO) {
        if (super.matches(pRelationshipBO)) {
            if (pRelationshipBO instanceof RelationShipBO) {
                RelationShipBO obj = (RelationShipBO) pRelationshipBO;
                boolean toReturn = this.relationShipBO.getAnswer().equals(obj.getAnswer());
                toReturn = toReturn && this.relationShipBO.getCreationDate().equals(obj.getCreationDate());
                toReturn = toReturn && this.relationShipBO.getId().equals(obj.getId());
                toReturn = toReturn && this.relationShipBO.getRecipient().equals(obj.getRecipient());
                toReturn = toReturn && this.relationShipBO.getRequest().equals(obj.getRequest());
                toReturn = toReturn && this.relationShipBO.getSender().equals(obj.getSender());
                toReturn = toReturn && this.relationShipBO.getStatus().equals(obj.getStatus());
                toReturn = toReturn && this.relationShipBO.getType().equals(obj.getType());

                return toReturn;
            }
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
