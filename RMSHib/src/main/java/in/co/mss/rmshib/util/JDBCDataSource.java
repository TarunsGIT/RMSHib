package in.co.mss.rmshib.util;

import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {

	private static JDBCDataSource datasource;// object creation

	private JDBCDataSource() {// Default constructor
	}

	private ComboPooledDataSource cpds = null;// object creation

	/**
	 * Create instance of Connection Pool
	 * 
	 * @return
	 */

	/***************************** OBJECT INSTANSTION ****************************************/

	public static JDBCDataSource getInstance() {// OBJECT INSTANSTION
		if (datasource == null) {
			ResourceBundle rb = ResourceBundle
					.getBundle("in.co.mss.rmshib.bundle.system");
			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));// LOAD
																		// DRIVER
			} catch (Exception e) {
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(new Integer((String) rb
					.getString("initialPoolSize")));
			datasource.cpds.setAcquireIncrement(new Integer((String) rb
					.getString("acquireIncrement")));
			datasource.cpds.setMaxPoolSize(new Integer((String) rb
					.getString("maxPoolSize")));
			datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb
					.getString("timeout")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb
					.getString("minPoolSize")));
		}
		return datasource;
	}

	/**
	 * Gets the connection from ComboPooledDataSource
	 * 
	 * @return connection
	 */
	public static Connection getConnection() throws Exception {
		return getInstance().cpds.getConnection();
	}

	/**
	 * Closes a connection
	 * 
	 * @param connection
	 * @throws Exception
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void trnRollback(Connection connection)
			throws ApplicationException {// COUSTOM EXCEPTION(APPLICATION
											// EXCEPTION)
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.toString());
			}
		}
	}

}
