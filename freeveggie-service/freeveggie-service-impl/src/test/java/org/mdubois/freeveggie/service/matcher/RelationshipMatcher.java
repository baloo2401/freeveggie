package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.RelationshipBO;

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipMatcher extends BusinessObjectMatcher<RelationshipBO> {

    private RelationshipBO relationshipBO;

    public RelationshipMatcher(RelationshipBO pRelationshipBO) {
        super(pRelationshipBO);
        this.relationshipBO = pRelationshipBO;
    }

    @Override
    public boolean matches(Object pRelationshipBO) {
        if (super.matches(pRelationshipBO)) {
            if (pRelationshipBO instanceof RelationshipBO) {
                RelationshipBO obj = (RelationshipBO) pRelationshipBO;
                boolean toReturn = this.relationshipBO.getAnswer().equals(obj.getAnswer());
                toReturn = toReturn && this.relationshipBO.getCreationDate().equals(obj.getCreationDate());
                toReturn = toReturn && this.relationshipBO.getId().equals(obj.getId());
                toReturn = toReturn && this.relationshipBO.getRecipient().equals(obj.getRecipient());
                toReturn = toReturn && this.relationshipBO.getRequest().equals(obj.getRequest());
                toReturn = toReturn && this.relationshipBO.getSender().equals(obj.getSender());
                toReturn = toReturn && this.relationshipBO.getStatus().equals(obj.getStatus());
                toReturn = toReturn && this.relationshipBO.getType().equals(obj.getType());

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
