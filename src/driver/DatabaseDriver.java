package driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

//
public class DatabaseDriver implements java.sql.Driver {

    public DatabaseDriver() {
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) {
            return null;
        }
        return getConnection(info.getProperty("user"), url, this);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        if (url.length() < 12) {
            return false;
        }
        return url.substring(5, 12).equals("databaseSQL");
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public abstract databaseSQLConnection getConnection
            (String user, String url, Driver d)
            throws SQLException;
}
