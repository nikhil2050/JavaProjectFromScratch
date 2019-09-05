package com.nikhil.nyx.common.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveConnection {

    private static HiveConnection instance;
    private Connection connection;
    private String url = "jdbc:hive2://host:10000/default;";
    private String username = "username";
    private String password = "pw";

    private HiveConnection() throws SQLException {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static HiveConnection getInstance() throws SQLException {

    	if (instance == null || instance.getConnection().isClosed()) {
            instance = new HiveConnection();
        }
        return instance;
    }
    
	public static void execute(String query, Object[] params) throws SQLException {
		Connection conn = HiveConnection.getInstance().getConnection();
		PreparedStatement pStmt = conn.prepareStatement(query);
		if(null!=params) {
			pStmt.setInt(1, Integer.parseInt(params[0].toString()));
			pStmt.setString(2, params[1].toString());
			pStmt.setString(3, params[2].toString());
		}
		pStmt.execute();
	}
	public static void executeUpdate(String query, Object[] params) throws SQLException {
		Connection conn = HiveConnection.getInstance().getConnection();
		PreparedStatement pStmt = conn.prepareStatement(query);
		if(null!=params){
			pStmt.setInt(1, Integer.parseInt(params[0].toString()));
			pStmt.setString(2, params[1].toString());
			pStmt.setString(3, params[2].toString());
		}
		pStmt.executeUpdate();
	}
	
	public static String executeQuery(String query) throws SQLException {
		ResultSet rs = null;
		StringBuilder str = new StringBuilder("");
		Connection conn = HiveConnection.getInstance().getConnection();
		Statement pStmt = conn.createStatement();
		
		rs = pStmt.executeQuery(query); 

//			// Check if rows are present
//			if (rs.last()) {
//				if (rs.getRow() == 0) {
//					return "No records found !!!";
//				} else {
//					rs.beforeFirst();
//				}
//			} else {
//				return "No records found !!!";
//			}

		ResultSetMetaData rsmd = null;
		int columnsNumber = 0;
		rsmd = rs.getMetaData();
		columnsNumber = rsmd.getColumnCount();

		// Fetch ColNames
		for (int i = 1; i <= columnsNumber; i++) {
			str.append(rsmd.getColumnName(i) + "\t|");
		}
		str.append("\n");

		// Fetch Rows
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				str.append(columnValue + "\t|");
			}
			str.append("\n");
		}

		return str.toString();
	}

}
