package dao_IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import dao_interfaces.*;
import objetos.Deportista;

public class DAODeportista_IMPL implements I_DAODeportista {
	private static Connection link;
	@Override
	public void cargar(Deportista d) {
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("INSERT INTO `deportista` VALUES(?, ?, ?, ?, ?,?)");
			st.clearParameters();
			st.setInt(1, d.getId());
			st.setString(2, d.getApellido());
			st.setString(3, d.getNombre());
			st.setString(4, d.getMail());
			st.setString(5, d.getTelefono());
			st.setInt(6, d.getId_pais());
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
	public void modificar(Deportista d) {
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("UPDATE deportista set apellidos = ?, nombres = ?, email = ?, telefono = ?, id_pais = ? where id = ?  ");
			st.clearParameters();
			st.setInt(6, d.getId());
			st.setString(1, d.getApellido());
			st.setString(2, d.getNombre());
			st.setString(3, d.getMail());
			st.setString(4, d.getTelefono());
			st.setInt(5, d.getId_pais());
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
	public Deportista buscar(int id) {
		Deportista d = null;
		try {

			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st= link.prepareStatement("SELECT * FROM deportista WHERE id = ?");
			st.clearParameters();
			st.setInt(1, id);
			st.executeQuery();
			st.getResultSet().next();
			d = new Deportista(id, st.getResultSet().getString("apellidos"), st.getResultSet().getString("nombres"),
					st.getResultSet().getString("email"), st.getResultSet().getString("telefono"),st.getResultSet().getInt("id_pais"));
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		}

		return d;
	}

	@Override
	public void eliminar(Deportista d) {
		try {

			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("DELETE FROM deportista WHERE id = ?");
			st.setInt(1, d.getId());
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
	public List<Deportista> listar() {
		List<Deportista> lista = null;
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT * FROM deportista");
			lista = new ArrayList<Deportista>();
			st.executeQuery();
			while (st.getResultSet().next()) {
				Deportista d = new Deportista();
				d.setId(st.getResultSet().getInt("id"));
				d.setApellido(st.getResultSet().getString("apellidos"));
				d.setNombre(st.getResultSet().getString("nombres"));
				d.setMail(st.getResultSet().getString("email"));
				d.setTelefono(st.getResultSet().getString("telefono"));
				d.setId_pais(st.getResultSet().getInt("id_pais"));
				lista.add(d);
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

		return lista;
	}
	
	public int pedirId() {
		int i = 0;
		try {
	        link= Conexion_IMPL.getConexion().getLink();
	        Statement stmt = link.createStatement();
	        ResultSet resul = stmt.executeQuery("SELECT * FROM deportista");
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
