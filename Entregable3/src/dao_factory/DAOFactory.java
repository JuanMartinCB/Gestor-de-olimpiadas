package dao_factory;

import dao_interfaces.*;
import dao_IMPL.*;

public class DAOFactory {	
	public static I_DAODeportista getDeportista() {
		return new DAODeportista_IMPL();
	}
	public static I_DAOPais getPais() {
		return new DAOPais_IMPL();
	}
	public static I_DAODisciplina getDisciplina() {
		return new DAODisciplina_IMPL();
	}
	public static I_DAOAtletaEnDisciplina getAtletaEnDisciplina() {
		return new DAOAtletaEnDisciplina_IMPL();
	}
}
