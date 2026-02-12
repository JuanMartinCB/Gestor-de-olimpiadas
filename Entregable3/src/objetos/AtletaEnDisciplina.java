package objetos;

public class AtletaEnDisciplina {
	private int idDeportista;
	private int idDisciplina;

	public AtletaEnDisciplina() {
	};

	public AtletaEnDisciplina(int idDeportista, int idDisciplina) {
		super();
		this.idDeportista = idDeportista;
		this.idDisciplina = idDisciplina;
	}

	public int getIdDeportista() {
		return idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

}

