package repositories;

import java.util.List;

import dto.DtoCantidadInscriptosPorCarrera;
import entities.Inscripcion;

public interface InscripcionRepository {

	void matricularEstudiante(Inscripcion i);
	
	List<DtoCantidadInscriptosPorCarrera> carrerasConInscriptos();

}
