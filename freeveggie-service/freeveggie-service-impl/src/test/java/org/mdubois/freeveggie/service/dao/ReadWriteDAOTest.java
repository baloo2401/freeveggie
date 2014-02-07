package org.mdubois.freeveggie.service.dao;

import java.io.Serializable;
import org.apache.commons.beanutils.BeanComparator;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;


/**
 *
 * @author Mickael Dubois
 * @param <T> - The entity to have a data access on
 * @param <PK> - The primary key of the entity
 */
public abstract class ReadWriteDAOTest<T extends BusinessObject<PK>, PK extends Serializable> extends ReadOnlyDAOTest<T,PK> {

    /**
     *
     * @return
     */
    public abstract T createEntity();


    @Test
    public void insert() {
        T entity = createEntity();
        if(entity!=null){
            int size = getDao().getAll(false,null).size();
            getDao().save(entity);
            Assert.assertEquals(size + 1, getDao().getAll(false, null).size());
            getDao().flush();
            T entityDB = getDao().get(entity.getId());
            BeanComparator beanComparator = new BeanComparator("id");

            Assert.assertTrue(beanComparator.compare(entity, entityDB) == 0);
        }
    }

    @Test
    public void delete() {
        T entity = createEntity();
        if(entity!=null){
            int size = getDao().getAll(false, null).size();
            getDao().save(entity);
            getDao().flush();
            getDao().delete(entity);
            getDao().flush();
            Assert.assertEquals(size, getDao().getAll(false, null).size());
        }
    }

    @Override
    public abstract IReadWriteDAO<T, PK> getDao();
}
