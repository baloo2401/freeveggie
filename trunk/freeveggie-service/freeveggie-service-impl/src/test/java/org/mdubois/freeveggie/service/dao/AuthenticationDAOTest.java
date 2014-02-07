package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.impl.AuthenticationDAO;
import org.mdubois.freeveggie.dao.impl.UserDAO;
import org.mdubois.freeveggie.framework.test.DaoUnitTest;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class AuthenticationDAOTest extends DaoUnitTest {

    private AuthenticationDAO authenticationDAO;
    private UserDAO userDAO;

    @Before
    public void setUp() {
        authenticationDAO = new AuthenticationDAO();
        authenticationDAO.setEntityManager(em);

        userDAO = new UserDAO();
        userDAO.setEntityManager(em);
    }

    @Test
    public void testControlPasswordFalse(){
        Assert.assertEquals(null, authenticationDAO.controlPassword("balo", "motdepassedetest123"));
    }

    @Test
    public void testControlPasswordFalse1(){
        Assert.assertEquals(null, authenticationDAO.controlPassword("baloo", "motdepassedetest12"));
    }

    @Test
    public void testControlPasswordFalse2(){
        Assert.assertEquals(null, authenticationDAO.controlPassword("baloo", "Motdepassedetest123"));
    }

    @Test
    public void testControlPasswordTrue(){
        UserBO userBO = userDAO.get(1L);
        Assert.assertEquals(userBO, authenticationDAO.controlPassword("baloo", "motdepassedetest123"));
    }

    @Test
    public void testControlTempPasswordFalse(){
        UserBO userBO = userDAO.get(2L);
        Assert.assertEquals(null, authenticationDAO.controlTempPassword("36156", "motdepassedetest123"));
    }

    @Test
    public void testControlTempPasswordFalse1(){
        Assert.assertEquals(null, authenticationDAO.controlTempPassword("baloo", "motdepassedetest12"));
    }

    @Test
    public void testControlTempPasswordFalse2(){
        Assert.assertEquals(null, authenticationDAO.controlTempPassword("baloo", "Motdepassedetest123"));
    }

    @Test
    public void testControlTempPasswordFalse3(){
        Assert.assertEquals(null, authenticationDAO.controlTempPassword("baloo", "motdepassedetest123"));
    }

    @Test
    public void testControlTempPasswordTrue(){
        UserBO userBO = userDAO.get(2L);
        Assert.assertEquals(userBO, authenticationDAO.controlTempPassword("3615", "motdepassedetest123"));
    }
}
