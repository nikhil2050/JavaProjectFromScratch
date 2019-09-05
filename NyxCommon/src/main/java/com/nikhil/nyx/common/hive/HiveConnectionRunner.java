package com.nikhil.nyx.common.hive;

import java.sql.SQLException;

public class HiveConnectionRunner {

	public static void main(String[] args) {
		String tableName = "empdatatest";
		String sql;
		
		try {
			System.out.println("\nDrop Table : ");
			HiveConnection.execute("drop table " + tableName, null);
			System.out.println("Drop Table : Ok");
		} catch (SQLException e) {
			System.out.println("No table must be available with the provided tableName.");
			e.printStackTrace();
		}

		try {
			System.out.println("\nCreate Table : ");
			HiveConnection.execute("create table " + tableName + " (id int, name string, dept string)", null);
			System.out.println("Create Table : Ok");
		} catch (SQLException e) {
			System.out.println("Create new table failed.");
			e.printStackTrace();
		}

		try {
			System.out.println("\nInsert Records : ");
			sql = "insert into table " + tableName + " values(?,?,?)";
			Object[] params = new Object[]{3, "Ram", "Sales2"};
			HiveConnection.executeUpdate(sql, params);
//			sql = "insert into table " + tableName + " values(3,'Ram','Sales3')";
//			HiveConnection.executeUpdate(sql, null);
			System.out.println("\n Insert Records : Ok");
		} catch (SQLException e) {
			System.out.println("Record insertion failed.");
			e.printStackTrace();
		}		

		sql = "select * from "+ tableName +" limit 100";
		try {
			System.out.println("\nSelect Records : ");
			System.out.println(HiveConnection.executeQuery(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
