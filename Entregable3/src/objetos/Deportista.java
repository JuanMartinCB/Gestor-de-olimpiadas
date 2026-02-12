package objetos;

public class Deportista {
	private String nombre;
	private String apellido;
	private int id;
	private String email;
	private String telefono;
	private int id_pais;

	public Deportista() {
	};

	public Deportista(int id, String apellido, String nombre, String email, String telefono,int id_pais) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.email = email;
		this.telefono = telefono;
		this.id_pais=id_pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return email;
	}

	public void setMail(String mail) {
		this.email = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	
	
}
