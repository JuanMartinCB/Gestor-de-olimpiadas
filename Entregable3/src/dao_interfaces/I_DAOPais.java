package dao_interfaces;

import java.util.List;

import objetos.Pais;

public interface I_DAOPais {
	public void cargar(Pais p);

	public void modificar(Pais p);

	public Pais buscar(int id);

	public Pais buscarID(String nombre);
	
	public void eliminar(Pais p);

	public List<Pais> listar();
	
	public int pedirId();
	
}
