package org.mdubois.freeveggie.framework.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * This is a helper class to make using DBUnit a bit easier.  It includes methods
 * for initializing the test database and loading test data.
 * @author Mickael Dubois
 *
 */
public final class DbUnitHelper {
    private static IDatabaseConnection con;
    private static int txnManagerRefCount = 0;

    /**
     * Initialize the test database.  This method will connect to the database
     * of the given <code>connection</code> and run the SQL statements specified
     * by the <code>inputFile</code>.  Each SQL statement should be terminated by
     * a blank line (or EOF).  Lines can be commented out by beginning the line
     * with two dashes ("--"). An example SQL file:
     *
     * <pre><code>         DROP TABLE IF EXISTS foo;
     *
     *          CREATE TABLE foo (
     *                  id int primary key,
     *                  bar varchar(255),
     *                  --baz boolean NOT NULL
     *          );
     *
     *          --We're no longer using this sequence.  Do not create it.
     *          --CREATE SEQUENCE foo_seq;</code></pre>
     *
     * will drop the table "foo" if it exists, then create it with two columns,
     * "id", and "bar".  The column baz and the sequence "foo_seq" will not be
     * created, as they are commented out.
     *
     * <b>NOTE:</b>  This method must be called before any other method in the
     * class, as it initializes the connection used in the other methods.
     * @param connection  The java.sql.Connection to use
     * @param inputFile The file containing the DDL to run
     * @throws SQLException
     * @throws IOException
     */
    public static void initDatabase(Connection connection, String inputFile)
        throws SQLException, IOException, DatabaseUnitException {
        Statement stmt = null;
        BufferedReader in = null;
        try{
            stmt = connection.createStatement();
            con = new DatabaseConnection(connection);
            in = new BufferedReader(new FileReader(inputFile));
            StringBuilder sql = new StringBuilder();
            String line = in.readLine();

            while (line != null) {
                if (line.trim().equals("")) {
                    if (sql.length() > 0) {
                        stmt.executeUpdate(sql.toString());
                        sql = new StringBuilder();
                    }
                } else if (!line.startsWith("--")) {
                    sql.append(line);
                }
                line = in.readLine();
            }
            // If there's a query left (i.e., no blank line at the EoF), run it
            if (sql.length() > 0) {
                stmt.executeUpdate(sql.toString());
            }
        } finally {
            if(in!=null){
                in.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }
    }

    /**
     * Loads the DBUnit test data.
     * @param fileName  The name of the XML DBUnit input file
     * @throws IOException
     * @throws DatabaseUnitException
     * @throws SQLException
     */
    public static void loadData(String fileName)
        throws IOException, DatabaseUnitException, SQLException {
        InputStream testData = System.class.getResourceAsStream(fileName);
        FlatXmlDataSet dataSet = new FlatXmlDataSet(testData);
        DatabaseOperation.CLEAN_INSERT.execute(con, dataSet);
    }

    /**
     * Close the DBUnit connection
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        con.close();
    }

    /**
     * EXPERIMENTAL:  Start a transaction, if one has not already been started,
     * and increment the reference counter.
     * @param entityManager The EM on which to start the transaction
     */
    public static void startTransaction(EntityManager entityManager) {
        EntityTransaction txn = entityManager.getTransaction();
        if (!txn.isActive()) {
            txn.begin();
        }
        txnManagerRefCount++;
    }

    /**
     * EXPERIMENTAL:  Zero the reference counter, then rollback the
     * transaction, if one has been started.  If a rollback is requested, we
     * assume an error has occured, so we proceed, regardless of the reference
     * count value
     * @param entityManager The EM on which to start the transaction
     */
    public static void rollbackTransaction(EntityManager entityManager) {
        txnManagerRefCount = 0;
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * EXPERIMENTAL:  Decrement the reference counter.  If the count is now zero,
     * and a transaction is active, commit it.
     * @param entityManager
     */
    public static void commitTransaction(EntityManager entityManager) {
        txnManagerRefCount--;
        if (txnManagerRefCount == 0 && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }

    private DbUnitHelper() {
    }
}