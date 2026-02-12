package dao_interfaces;

import java.util.List;
import objetos.Deportista;

public interface I_DAODeportista {
	
	public void cargar(Deportista d);

	public void modificar(Deportista d);

	public Deportista buscar(int d);

	public void eliminar(Deportista d);

	public List<Deportista> listar();
	
	public int pedirId();
	
}
