package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bean.IUserBean;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class UserBeanIT extends AbstractBeanIntegrationTest {

    private IUserBean userBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/UserBeanLocal");
        userBean = (IUserBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetUserByCountry() throws Exception {

        Integer pRefCountryId = null;
        Integer pRefProductId = null;
        List expResult = null;
        List result = userBean.getUserByCountry(pRefCountryId, pRefProductId, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetUserByCountry2() throws Exception {
        Integer pRefCountryId = 76;
        Integer pRefProductId = 123;
        List expResult = null;
        List result = userBean.getUserByCountry(pRefCountryId, pRefProductId, null);
        assertEquals(expResult, result);
    }

    /*
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetUserByCountry3() throws Exception {
        Integer pRefCountryId = 76;
        Integer pRefProductId = 1;
        List<UserMsg> result = userBean.getUserByCountry(pRefCountryId, pRefProductId, null);
        assertEquals(5, result.size());
    }

    /*42
     * Test of getUserByState method, of class ReferenceBean.
     */
    @Test
    public void testGetUserByState() throws Exception {
        Integer pRefStateId = 12;
        Integer pRefProductId = 1;
        List<UserMsg> result = userBean.getUserByState(pRefStateId, pRefProductId, null);
        assertEquals(2, result.size());
        assertEquals("baloo2401", result.get(0).getUsername());
    }

    /**
     * Test of getUserByRegion method, of class ReferenceBean.
     */
    @Test
    public void testGetUserByRegion() throws Exception {
        Integer pRefRegionId = 42;
        Integer pRefProductId = 1;
        List<UserMsg> result = userBean.getUserByRegion(pRefRegionId, pRefProductId, null);
        assertEquals(2, result.size());
        assertEquals("baloo2401", result.get(0).getUsername());
    }

    /*
     * Test of getUserByCity method, of class ReferenceBean.
     */
    @Test
    public void testGetUserByCity() throws Exception {
        Integer pRefCityId = 15582;
        Integer pRefProductId = 1;
        List<UserMsg> result = userBean.getUserByCity(pRefCityId, pRefProductId, null);
        assertEquals(2, result.size());
        assertEquals("baloo2401", result.get(0).getUsername());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testGetUserById() throws Exception {
        Long pUserId = 2L;
        UserMsg result = userBean.getUserById(pUserId);
        assertEquals("popy2809", result.getUsername());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testBlacklist() throws Exception {
        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_rus_id = 2 WHERE usr_id = 4";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(9L);
        Long pUserId = 4L;
        userBean.blacklist(pContextMsg, pUserId);
        UserMsg userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.BLACKLISTED, userBlacklist.getStatus());
        userBean.unblacklist(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.VALIDED, userBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testDelete() throws Exception {
        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_rus_id = 2 WHERE usr_id = 5";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(9L);
        Long pUserId = 5L;
        userBean.delete(pContextMsg, pUserId);
        UserMsg userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.DELETED, userBlacklist.getStatus());
        userBean.undelete(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.VALIDED, userBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testArchive() throws Exception {
        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_rus_id = 2 WHERE usr_id = 6";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(9L);
        Long pUserId = 6L;
        userBean.archive(pContextMsg, pUserId);
        UserMsg userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.ARCHIVED, userBlacklist.getStatus());
        userBean.unarchive(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserStatus.VALIDED, userBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testUpgradeUser() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(9L);
        Long pUserId = 7L;
        userBean.changeRole(pContextMsg, pUserId, UserRole.USER);
        UserMsg userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.USER, userBlacklist.getRole());
        userBean.upgrade(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.MANAGER, userBlacklist.getRole());
        userBean.downgrade(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.USER, userBlacklist.getRole());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testUpgradeAdmin() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(9L);
        Long pUserId = 8L;
        userBean.changeRole(pContextMsg, pUserId, UserRole.ADMIN);
        UserMsg userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.ADMIN, userBlacklist.getRole());
        userBean.upgrade(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.SUPERADMIN, userBlacklist.getRole());
        userBean.downgrade(pContextMsg, pUserId);
        userBlacklist = userBean.getUserById(pUserId);
        assertEquals(UserRole.ADMIN, userBlacklist.getRole());
    }
}
