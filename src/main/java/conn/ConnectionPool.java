package conn;

import com.mysql.cj.log.Log;
import exception.DBException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool(){}

    private static ConnectionPool instance = null;

    public static synchronized ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Context ctx;
        Connection c;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/payment");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DBException("Can't connect to database", e);
        }
        return c;
    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
                throw new DBException("Can't rollback connection", e);
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch(Exception e) {
                LOGGER.warn(e.getMessage());
                throw new DBException("Can't close" + closeable.getClass().getSimpleName(), e);
            }
        }
    }
}