package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOModel {
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public DAOModel() { // create db object instance
		conn = new DBConnect();
	}

	// CREATE TABLE METHOD
	public void createTable() {
		try {

			// Open a connection
			System.out.println("Connecting to database to create Table...");
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

			String sql = "CREATE TABLE 510_Lab4_DU " + "(pid INTEGER not NULL AUTO_INCREMENT, "
					+ " id VARCHAR(10), " + " income numeric(8,2), " + " pep VARCHAR(6), " + " PRIMARY KEY ( pid ))";
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); // close db connection
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		}
	}

	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.connect().createStatement();
			String sql = null;			

			// Include all object data to the database table
			for (int i = 0; i < robjs.length; ++i) {

				// finish string assignment to insert all object data
				// (id, income, pep) into your database table
				sql = "INSERT INTO 510_Lab4_DU(id,income,pep) " + "VALUES (' " + robjs[i].getId()
						+ " ', ' " + robjs[i].getIncome() + " ', ' " + robjs[i].getPep() + " ' )";

				stmt.executeUpdate(sql);
				
			}
			System.out.println("Records Inserted!");
			conn.connect().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// INSERT INTO METHOD

	public ResultSet retrieveRecords() {
		ResultSet rs = null;

		try {
			stmt = conn.connect().createStatement();

			String sql = "SELECT pid,id,income,pep from 510_Lab4_DU order by pep desc";
			rs = stmt.executeQuery(sql);
			conn.connect().close();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;
	}

}
