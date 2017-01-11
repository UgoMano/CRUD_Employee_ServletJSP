package com.dbcon;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	
	public static Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/MyConn");
		Connection conn = ds.getConnection();
		System.out.println("dbConLookp():: Datasource Connection is called."+ds.getLogWriter());
		return conn;
	}

}
