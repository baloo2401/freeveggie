package org.mdubois.freeveggie.framework.test;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public abstract class DaoUnitTest {

    private static boolean _databaseInit = false;
    protected static EntityManagerFactory emFactory;
    protected static EntityManager em;
    protected static Connection connection;

    @BeforeClass
    public static void setUpClass() throws Exception {
        if (_databaseInit == false) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");

            } catch (Exception ex) {
                fail("Exception during HSQL database startup.");
            }
            try {
                DbUnitHelper.initDatabase(connection, "./src/main/resources/init_schema.sql");
                DbUnitHelper.initDatabase(connection, "./src/main/resources/init_reference.sql");
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Exception during init database.");
            }
            try {
                emFactory = Persistence.createEntityManagerFactory("FREEVEGGIE_PU_TEST");
                em = emFactory.createEntityManager();
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Exception during JPA EntityManager instanciation.");
            }
            setUpDatabase();
            _databaseInit = true;
        }
    }

    @Before
    public void beginTransaction() {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
    }

    private static void setUpDatabase() {
        IDataSet dataSet;
        IDatabaseConnection dbConn;
        try {
            dataSet = new FlatXmlDataSet(getDatasetFile());
            dbConn = new DatabaseConnection(connection);
            //this line might be usefull one day. if we use special datatype
            DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
        } catch (Exception ex) {
            fail("Exception during loading data : " + ex.getMessage());
        }
    }

    protected static File getDatasetFile() {
        return new File("./src/test/resources/dataset.xml");
    }
}