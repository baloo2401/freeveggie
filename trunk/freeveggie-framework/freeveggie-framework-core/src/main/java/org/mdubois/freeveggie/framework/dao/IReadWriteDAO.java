package org.mdubois.freeveggie.framework.dao;

import org.mdubois.freeveggie.framework.bo.BusinessObject;
import java.io.Serializable;

/**
 * The interface of the read and write data access object.
 *
 * @author Mickael Dubois
 * @param <ENTITY> - The business object which this class have access to
 * @param <PK> - The primary key of the business object which this class have access to.
 */
public interface IReadWriteDAO<ENTITY  extends BusinessObject<PK>, PK extends Serializable> extends IReadOnlyDAO<ENTITY, PK> {

    /**
     * Save an object to the database
     * @param transientInstance - The entity to save
     * @return The value of the primary key create for this save.
     */
    PK save(ENTITY transientInstance);

    /**
     * Update an entity.
     * @param detachedInstance - The entity to update.
     */
    //TODO MDU to delete
    void update(ENTITY detachedInstance);

    /**
     * Delete a entity from the database.
     * @param detachedInstance - The entity to delete.
     */
    void delete(ENTITY detachedInstance);

    /**
     * Flush the state of the current transaction.
     */
    void flush();
}
