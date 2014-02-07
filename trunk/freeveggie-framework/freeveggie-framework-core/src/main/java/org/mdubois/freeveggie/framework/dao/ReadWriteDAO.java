package org.mdubois.freeveggie.framework.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import java.io.Serializable;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 * @param <T> - The entity to have a data access on
 * @param <PK> - The primary key of the business object
 */
public abstract class ReadWriteDAO<T extends BusinessObject<PK>, PK extends Serializable> extends ReadOnlyDAO<T, PK> implements IReadWriteDAO<T, PK> {

    @Override
    public void update(T o) {
//        entityManager.merge(o);
//        entityManager.persist(o);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void delete(T detachedInstance) {
        entityManager.remove(detachedInstance);
    }

    @Override
    public PK save(T newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }
}