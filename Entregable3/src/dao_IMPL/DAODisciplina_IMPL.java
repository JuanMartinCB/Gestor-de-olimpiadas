package dao_IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao_interfaces.I_DAODisciplina;
import objetos.Disciplina;

public class DAODisciplina_IMPL  implements I_DAODisciplina {
	private static Connection link;
	@Override
	public void cargar(Disciplina d) {
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("INSERT TO `disciplina` VALUES(?, ?)");
			st.clearParameters();
			st.setInt(1, d.getId());
			st.setString(2, d.getNombre());
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
	public void modificar(Disciplina d) {
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("Update disciplina set nombres = ? where id = ?  ");
			st.clearParameters();
			st.setInt(2, d.getId());
			st.setString(1, d.getNombre());
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
	public Disciplina buscar(int id) {
		Disciplina d = null;
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT nombre FROM disciplina WHERE id = ?");
			st.clearParameters();
			st.setInt(1, id);
			st.executeQuery();
			st.getResultSet().next();
			d = new Disciplina(st.getResultSet().getString("nombre"), id);
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
	public Disciplina buscarID(String nombre) {
		Disciplina d = null;
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT id FROM disciplina WHERE nombre = ?");
			st.clearParameters();
			st.setString(1, nombre);
			st.executeQuery();
			st.getResultSet().next();
			d = new Disciplina(nombre,st.getResultSet().getInt("id"));
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
	public void eliminar(Disciplina d) {
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("DELETE FROM disciplina WHERE id = ?");
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
	public List<Disciplina> listar() {
		List<Disciplina> list = null;
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT * FROM disciplina");
			list = new ArrayList<Disciplina>();
			st.executeQuery();
			while (st.getResultSet().next()) {
				Disciplina d = new Disciplina();
				d.setId(st.getResultSet().getInt("id"));
				d.setNombre(st.getResultSet().getString("nombre"));
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

}
