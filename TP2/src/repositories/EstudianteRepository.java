package repositories;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import entities.Estudiante;

import entities.Estudiante;

public interface EstudianteRepository {
	void altaEstudiante(Estudiante e) throws SQLIntegrityConstraintViolationException;
	List<Estudiante> listaEstudianteOdenadoPorNombre() throws SQLException;
	Estudiante estudiantePorLibreta(int numeroLibreta) throws SQLException;
	List<Estudiante> listaEstudiantePorGenero(String genero);
	
	
	
	
	
	
	
	
	
	
	
	
}
