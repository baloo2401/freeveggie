package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.junit.Before;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.dao.impl.ProfileDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class ProfilDAOTest extends ReadWriteDAOTest<ProfileBO ,Long> {

    private ProfileDAO profilDAO;

    @Before
    public void setUp() {

        profilDAO = new ProfileDAO();
        profilDAO.setEntityManager(em);
    }



    @Override
    public ProfileBO createEntity() {
        return null;
    }

    @Override
    public IReadWriteDAO<ProfileBO, Long> getDao() {
        return profilDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return null;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
