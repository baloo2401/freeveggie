package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.ISecurityBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class SecurityBeanIT extends AbstractBeanIntegrationTest {

    /**
     * {@link ISecurityBeanLocal}
     */
    private ISecurityBean securityBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/SecurityBeanLocal");
        securityBean = (ISecurityBean) bean;
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void controlPassword() throws Exception {
        String password = EncryptionUtils.getMD5("password");

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_password = '" + password + "' WHERE usr_id = 1";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        pAuthentificationMsg.setLogin("baloo2401");
        pAuthentificationMsg.setPassword("password");

        UserMsg result = securityBean.controlPassword(pAuthentificationMsg);
        Assert.assertEquals((Long) 1L, result.getId());
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test(expected = BusinessException.class)
    public void controlPasswordNotValid() throws Exception {
        String password = EncryptionUtils.getMD5("password");

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_password = '" + password + "' WHERE usr_id = 1";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        pAuthentificationMsg.setLogin("baloo2401");
        pAuthentificationMsg.setPassword("test");

        securityBean.controlPassword(pAuthentificationMsg);
    }

    @Test
    public void controlTempPassword() throws Exception {
        String tempPassword = "temppassword";
        String tempPasswordEncrypted = EncryptionUtils.getMD5(tempPassword);

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_temp_password = '" + tempPasswordEncrypted + "' WHERE usr_id = 2";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        pAuthentificationMsg.setLogin("popy2809");
        pAuthentificationMsg.setPassword(tempPassword);

        UserMsg result = securityBean.controlTempPassword(pAuthentificationMsg);
        Assert.assertEquals((Long) 2L, result.getId());

        pAuthentificationMsg.setLogin("baloo2401");
        pAuthentificationMsg.setPassword("test");
    }

    @Test(expected = BusinessException.class)
    public void controlTempPasswordNotValid() throws Exception {
        String tempPassword = "temppassword";
        String tempPasswordEncrypted = EncryptionUtils.getMD5(tempPassword);

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_temp_password = '" + tempPasswordEncrypted + "' WHERE usr_id = 2";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        pAuthentificationMsg.setLogin("baloo2401");
        pAuthentificationMsg.setPassword("test");

        securityBean.controlPassword(pAuthentificationMsg);
    }

    @Test
    public void hasTempPassword() throws Exception {
        String login = "archivetest";
        String encryptLogin = EncryptionUtils.encrypt(login);
        String encryptPassword = EncryptionUtils.getMD5("tempPassword2");

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_temp_password = '" + encryptPassword + "' WHERE usr_id = 6";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        boolean result = securityBean.hasTempPassword(encryptLogin);
        Assert.assertEquals(result, true);

        //Set the user test as a validate status
        sql = "UPDATE t_user SET usr_temp_password = '' WHERE usr_id = 6";
        stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        result = securityBean.hasTempPassword(encryptLogin);
        Assert.assertEquals(result, false);
    }

    @Test(expected = BusinessException.class)
    public void hasTempPassword2() throws Exception {
        securityBean.hasTempPassword("aa");
    }

    @Test
    public void isExistingLogin() {
        boolean result = securityBean.isExistingLogin("archivetest");
        Assert.assertEquals(true, result);
        result = securityBean.isExistingLogin("arc");
        Assert.assertEquals(false, result);
    }

    @Test
    public void isExistingEmail() {
        boolean result = securityBean.isExistingEmail("dubois.mickael@gmail.com");
        Assert.assertEquals(result, true);
        result = securityBean.isExistingEmail("dubois.mickael");
        Assert.assertEquals(result, false);
    }

    @Test
    public void generateTempPassword() throws Exception {

        //Set the user test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_user SET usr_temp_password = '' WHERE usr_id = 5";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        final String pEmail = "deletetest@gmail.com";
        securityBean.generateTempPassword(pEmail);

        boolean result = securityBean.hasTempPassword(EncryptionUtils.encrypt("deletetest"));
        Assert.assertEquals(result, true);

    }
}
