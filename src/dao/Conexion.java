package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	
	private static Connection conn = null;
	private static String login = "JAVA";
	private static String clave = "admin";
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,login,clave);
			//conn.setAutoCommit(false);
			if(conn != null) {
				System.out.println("Conexion exitosa en Oracle");
			}else {
				System.out.println("ERROR de Conexion en Oracle");
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR CONEXION" + e.getMessage());
		}
		return conn;
	}
	
	public void desconectar() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("Error al desconectar" + e.getMessage());
		}
	}
	
	
	private Connection conn1;
	
	public static Connection connectdb(){
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());			
			Connection connection = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:XE","JAVA","admin");

			System.out.println("Conexion exitosa en Oracle");
			return connection;
		} catch (SQLException e) {
			System.out.println("este es el problema ");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static void cerrarConexion(){
		try {
			Conexion.connectdb().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
