package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnetion() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl"); //ler url do arquivo file com endereco do banco
				conn = DriverManager.getConnection(url, props); // passando endereço e info do banco
			}
			catch(SQLException e) {
				throw new DbExcepetion(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnetion() {
		
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbExcepetion(e.getMessage());
			}
		}
		
	}
	//Ler arquivo db com config do banco
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props  = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e) {
			throw new DbExcepetion(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbExcepetion(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbExcepetion(e.getMessage());
			}
		}
	}
}
