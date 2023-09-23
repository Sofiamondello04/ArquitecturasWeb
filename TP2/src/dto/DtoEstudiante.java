package dto;

public class DtoEstudiante {
	
	private int dni;
    private String nombre;
    private String apellido;
    
    
	public DtoEstudiante(int dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
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


	@Override
	public String toString() {
		return "DtoEstudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
    
    
}
