package dao_interfaces;

import java.util.List;

import objetos.Disciplina;

public interface I_DAODisciplina {
	public void cargar(Disciplina d);

	public void modificar(Disciplina d);

	public Disciplina buscar(int id);
	
	public Disciplina buscarID(String nombre);

	public void eliminar(Disciplina d);

	public List<Disciplina> listar();
}
