package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class Carrera {
	@Id
	private int id_carrera;
	@Column(nullable=false)// indica que el campo no puede ser nulo
	private String nombre;
	@Column 
	private int duracion;
	/*La anotacion mappedBy corresponde al atributo carrera de la clase Inscripción.*/
	@OneToMany (mappedBy = "carrera", fetch=FetchType.LAZY)
	private List<Inscripcion> inscriptos;

	public Carrera(int id_carrera, String nombre, int duracion) {
			this.id_carrera = id_carrera;
			this.nombre = nombre;
			this.duracion = duracion;
			this.inscriptos = new ArrayList<Inscripcion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inscripcion> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Inscripcion> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public int getId_carrera() {
		return id_carrera;
	}

	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id_carrera + ", nombre=" + nombre + ", inscriptos=" + inscriptos + "]";
	}

	
	

}
