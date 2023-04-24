package kuit.springbasic.jdbc.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static BasicDataSource ds;

    public static DataSource getDataSource() {
        if (ds == null) {
            ds = new BasicDataSource();
            ds.setDriverClassName(ConnectionConst.DB_DRIVER);
            ds.setUrl(ConnectionConst.DB_URL);
            ds.setUsername(ConnectionConst.DB_USERNAME);
            ds.setPassword(ConnectionConst.DB_PW);
        }
        return ds;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
