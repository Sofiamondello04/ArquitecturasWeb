package repositories;

import java.util.List;


import dto.DtoCantidadInscriptosPorCarrera;
import dto.DtoReporte;
import entities.Carrera;
import entities.Estudiante;

@SuppressWarnings("unused")
public interface CarreraRepository {
	void insertarCarrera(Carrera c);
	List<DtoReporte> Reporte();
}
