package com.quest.patientRecord.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Connector {

	public static Connection getConnectionInfo() throws ClassNotFoundException, SQLException {

		java.sql.Connection connection = null;

		try {
			Properties prop = new Properties();

			InputStream input = new FileInputStream(System.getenv("CATALINA_HOME") + "/apache-tomcat-8.0.37/conf/dbProperties.properties");
			prop.load(input);

			String driverName = prop.getProperty("DRIVER");
			String url = prop.getProperty("URL");
			String username = prop.getProperty("USERNAME");
			String password = prop.getProperty("PASSWORD");

			// STEP 2: Register JDBC driver
			Class.forName(driverName);

			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception ce) {

			System.out.println(ce);
		}

		return connection;
	}

}
