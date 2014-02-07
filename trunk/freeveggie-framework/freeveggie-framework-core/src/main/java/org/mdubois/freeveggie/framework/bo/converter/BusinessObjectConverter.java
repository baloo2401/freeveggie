package org.mdubois.freeveggie.framework.bo.converter;

import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 * The is to convert {@link Message} to {@link BusinessObject}.
 * 
 * @author Mickael Dubois
 */
public interface BusinessObjectConverter<Target extends BusinessObject, Source extends Message> {
    /**
     * It will create a new {@link BusinessObject}
     * @param pSource - The message source
     * @return - The new instance object
     */
    Target createNew(final Source pSource);

    /**
     * Update an existing {@link BusinessObject}. This method should not modify the
     * {@link BusinessObject} id.
     * @param pTarget - The target message
     * @param pSource - The source message
     */
    void update(final Target pTarget, final Source pSource);
}
