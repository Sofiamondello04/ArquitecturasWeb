package repositories;

import java.sql.SQLIntegrityConstraintViolationException;

import entities.Inscripcion;

public interface InscripcionRepository {

	void matricularEstudiante(Inscripcion i);

}
