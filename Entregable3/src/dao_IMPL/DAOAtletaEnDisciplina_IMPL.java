package dao_IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao_interfaces.I_DAOAtletaEnDisciplina;
import objetos.AtletaEnDisciplina;

public class DAOAtletaEnDisciplina_IMPL implements I_DAOAtletaEnDisciplina {
    private static Connection link;
	@Override
	public void cargar(AtletaEnDisciplina a) {
		try {
			
		    link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("INSERT INTO `deportista_en_disciplina` VALUES(?, ?)");
			st.clearParameters();
			st.setInt(1, a.getIdDeportista());
			st.setInt(2, a.getIdDisciplina());
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
	public void modificar(AtletaEnDisciplina a) {
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("Update deportista_en_disciplina set id_disciplina = ? where id_deportista = ?  ");
			st.clearParameters();
			st.setInt(1, a.getIdDisciplina());
			st.setInt(2, a.getIdDeportista());
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
	public AtletaEnDisciplina buscar(int id) {
		AtletaEnDisciplina a = null;
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT id_disciplina FROM deportista_en_disciplina WHERE id_deportista = ?");
			st.clearParameters();
			st.setInt(1, id);
			st.executeQuery();
			st.getResultSet().next();
			a = new AtletaEnDisciplina(id, st.getResultSet().getInt("id_disciplina"));
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 
		return a;
	}
	public AtletaEnDisciplina buscarID_Deportista(int id) {
		AtletaEnDisciplina a = null;
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT id_deportista FROM deportista_en_disciplina WHERE id_disciplina = ?");
			st.clearParameters();
			st.setInt(1, id);
			st.executeQuery();
			st.getResultSet().next();
			a = new AtletaEnDisciplina(st.getResultSet().getInt("id_deportista"),id);
			st.close();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception: " + e.getMessage());
				System.out.println("Error SQL ANSI-92: " + e.getSQLState());
				System.out.println("Código de error del Proveedor: " + e.getErrorCode());
				e = e.getNextException();
			}
		} 
		return a;
	}

	@Override
	public void eliminar(AtletaEnDisciplina a) {
		try {
			link= Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("DELETE FROM deportista_en_disciplina WHERE id_deportista = ?");
			st.setInt(1, a.getIdDeportista());
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
	public List<AtletaEnDisciplina> tolist() {
		List<AtletaEnDisciplina> list = null;
		try {
			link=Conexion_IMPL.getConexion().getLink();
			PreparedStatement st = link.prepareStatement("SELECT * FROM deportista_en_disciplina");
			list = new ArrayList<AtletaEnDisciplina>();
			st.executeQuery();
			while (st.getResultSet().next()) {
				AtletaEnDisciplina a = new AtletaEnDisciplina();
				a.setIdDeportista(st.getResultSet().getInt("id_deportista"));
				a.setIdDisciplina(st.getResultSet().getInt("id_disciplina"));
				list.add(a);
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
