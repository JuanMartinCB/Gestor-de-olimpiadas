package dao_IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao_interfaces.I_DAOPais;
import objetos.Pais;

public class DAOPais_IMPL  implements I_DAOPais {
	private static Connection link;
	@Override
	public void cargar(Pais p) {
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("INSERT INTO `pais` VALUES(?, ?)");
			st.clearParameters();
			st.setInt(1, p.getId());
			st.setString(2, p.getName());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 

	}

	@Override
	public void modificar(Pais p) {
		try {
			
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("Update pais set nombre = ? where id = ?  ");
			st.clearParameters();
			st.setInt(2, p.getId());
			st.setString(1, p.getName());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 

	}

	@Override
	public Pais buscar(int id) {
		Pais p = null;
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT nombre FROM pais WHERE id = ?");
			st.clearParameters();
			st.setInt(1, id);
			st.executeQuery();
			st.getResultSet().next();
			p = new Pais(id, st.getResultSet().getString("nombre")); 
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 
		return p;
	}
	public Pais buscarID(String nombre) {
		Pais p = null;
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT id FROM pais WHERE nombre = ?");
			st.clearParameters();
			st.setString(1, nombre);
			st.executeQuery();
			st.getResultSet().next();
			//System.out.println(st.getResultSet().getString("nombre"));
			p = new Pais(st.getResultSet().getInt("id"), nombre);
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 
		return p;
	}

	@Override
	public void eliminar(Pais p) {
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("DELETE FROM pais WHERE id = ?");
			st.setInt(1, p.getId());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 

	}

	@Override
	public List<Pais> listar() {
		List<Pais> list = null;
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT * FROM pais");
			list = new ArrayList<Pais>();
			st.executeQuery();
			while (st.getResultSet().next()) {
				Pais d = new Pais();
				d.setId(st.getResultSet().getInt("id"));
				d.setName(st.getResultSet().getString("nombre"));
				list.add(d);
			}
			st.close();

		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		}
			
		return list;
	}
	
	public int pedirId() {
		int i = 0;
		try {
	        link= Conexion_IMPL.getConexion().getLink();
	        Statement stmt = link.createStatement();
	        ResultSet resul = stmt.executeQuery("SELECT * FROM pais");
	        while(resul.next()) { 
	            if(resul.isLast())
	                i=resul.getInt("id");
	            }
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		}
		return ++i;
    
    }
	

}
