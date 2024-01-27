package dto;

public class DtoCantidadInscriptosPorCarrera {
	
	private String nombreCarrera;
    private Long cantidadInscriptos;
    
	public DtoCantidadInscriptosPorCarrera(String nombreCarrera, Long cantidadInscriptos) {
		this.nombreCarrera = nombreCarrera;
		this.cantidadInscriptos = cantidadInscriptos;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public Long getCantidadInscriptos() {
		return cantidadInscriptos;
	}

	public void setCantidadInscriptos(Long cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}

	@Override
	public String toString() {
	    return String.format("    nombreCarrera='%s',\n" +
	            "    cantidadInscriptos=%d\n" +
	            "]", nombreCarrera, cantidadInscriptos);
	}
}
