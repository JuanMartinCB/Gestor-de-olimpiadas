package dao_IMPL;

import java.sql.*;

import javax.swing.JOptionPane;

import dao_interfaces.*;
import java.sql.*;

public class Conexion_IMPL {
	private static Connection link;
	private static Conexion_IMPL conexion;
	private final String DB_URL = "jdbc:mysql://localhost:3306/tokyo2021_e3?serverTimezone=UTC";
	private static boolean condicion;
	
	//credenciales -- deberian llegar desde el interfaz de configuracion
	//private final String USER = "root";
	//private final String PW = "1234";
	
	
	public static boolean getCondicion(){
		return condicion;
	}
	
	
	public static Conexion_IMPL getConexion(String USER, String PW) throws SQLException {
		
		if(conexion == null)
			conexion = new Conexion_IMPL(USER, PW);
		else {
			conexion.cerrar();
			conexion = new Conexion_IMPL(USER, PW);
			
		}
		return conexion;
	}
	
	public static Conexion_IMPL getConexion() throws SQLException {
		if (conexion != null) {
			return conexion;
		}
		else {
			System.out.println("Error conexion");
			throw new SQLException();
		}
		
	}
	
	public Connection getLink() {
		return link;
	}
	
	private Conexion_IMPL(String USER, String PW) throws SQLException{
		try {
			condicion=true;
			link = DriverManager.getConnection(DB_URL, USER, PW);
			JOptionPane.showMessageDialog(null,"Conexion exitosa");
		}
		catch (ClassCastException e) {
			System.out.println("no se encontró el driver");
		} 
		catch (SQLException e){
			condicion=false;
			JOptionPane.showMessageDialog(null,"Error de conexion");
			while ( e!=null){
				System.out.println("SQL Exception:"+ e.getMessage());
				System.out.println("Error SQL ANSI-92:"+ e.getSQLState());
				System.out.println("Código de error del Proveedor:"+ e.getErrorCode());
				e = e.getNextException();
			}
		}
	}
	
	public static void cerrar() throws SQLException {
		try {
			
	
		if (link != null) {
			if (!link.isClosed()) {
				link.close();
			}
		}
		}catch (SQLException e){
			while ( e!=null){
				System.out.println("SQL Exception:"+ e.getMessage());
				System.out.println("Error SQL ANSI-92:"+ e.getSQLState());
				System.out.println("Código de error del Proveedor:"+ e.getErrorCode());
				e = e.getNextException();
			}
		}
	}
}
