package dao_interfaces;

import java.util.List;

import objetos.AtletaEnDisciplina;

public interface I_DAOAtletaEnDisciplina {
	public void cargar(AtletaEnDisciplina a);

	public void modificar(AtletaEnDisciplina a);

	public AtletaEnDisciplina buscar(int id);

	public AtletaEnDisciplina buscarID_Deportista(int id);
	
	public void eliminar(AtletaEnDisciplina a);

	public List<AtletaEnDisciplina> tolist();
}
