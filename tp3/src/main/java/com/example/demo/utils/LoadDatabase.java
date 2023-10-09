package com.example.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Carrera;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Inscripcion;
import com.example.demo.repository.CarreraRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.InscripcionRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabaseFromCSVCarreras(@Qualifier("carreraRepository") CarreraRepository carreraRepository) {
		return args -> {
			String packageName = LoadDatabase.class.getPackage().getName(); // Obtiene el nombre del paquete actual
			String csvFileName = "carreras.csv";
			String csvFilePath = packageName.replace('.', '/') + "/" + csvFileName;

			try (InputStream inputStream = LoadDatabase.class.getClassLoader().getResourceAsStream(csvFilePath);
					InputStreamReader reader = new InputStreamReader(inputStream);
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

				boolean skipHeader = true;
				for (CSVRecord csvRecord : csvParser) {
					if (skipHeader) {
						skipHeader = false;
						continue; // Salta la primera fila (encabezados)
					}
					// Leer datos del CSV y crear objetos Estudiante, Carrera e Inscripcion
					int id_carrera = Integer.parseInt(csvRecord.get(0));
					String carrera = csvRecord.get(1);
					int duracion = Integer.parseInt(csvRecord.get(2));

					// Crear Carreras
					Carrera c = new Carrera(id_carrera, carrera, duracion);
					carreraRepository.save(c);

				}

			} catch (IOException e) {
				log.error("Error al leer el archivo CSV: " + e.getMessage());
			}
		};
	}

	@Bean
	CommandLineRunner initDatabaseFromCSVEstudiantes(
			@Qualifier("estudianteRepository") EstudianteRepository estudianteRepository) {
		return args -> {
			String packageName = LoadDatabase.class.getPackage().getName(); // Obtiene el nombre del paquete actual
			String csvFileName = "estudiantes.csv";
			String csvFilePath = packageName.replace('.', '/') + "/" + csvFileName;

			try (InputStream inputStream = LoadDatabase.class.getClassLoader().getResourceAsStream(csvFilePath);
					InputStreamReader reader = new InputStreamReader(inputStream);
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

				boolean skipHeader = true;
				for (CSVRecord csvRecord : csvParser) {
					if (skipHeader) {
						skipHeader = false;
						continue; // Salta la primera fila (encabezados)
					}
					// Leer datos del CSV y crear objetos Estudiante, Carrera e Inscripcion
					int dni = Integer.parseInt(csvRecord.get(0));
					String nombre = csvRecord.get(1);
					String apellido = csvRecord.get(2);
					int edad = Integer.parseInt(csvRecord.get(3));
					String genero = csvRecord.get(4);
					String ciudad = csvRecord.get(5);
					int numLibretaUniversitaria = Integer.parseInt(csvRecord.get(6));

					// Crear Estudiante
					Estudiante estudiante = new Estudiante(dni, nombre, apellido, edad, genero, ciudad,
							numLibretaUniversitaria, null);
					estudianteRepository.save(estudiante);

				}

			} catch (IOException e) {
				log.error("Error al leer el archivo CSV: " + e.getMessage());
			}
		};
	}
	
	  @Bean CommandLineRunner initDatabaseFromCSVInscripciones(
			  @Qualifier("inscripcionRepository") InscripcionRepository inscripcionRepository , 
			  @Qualifier("estudianteRepository") EstudianteRepository estudianteRepository,
			  @Qualifier("carreraRepository") CarreraRepository carreraRepository) 
	  			{ return args -> { 
	  				String packageName = LoadDatabase.class.getPackage().getName(); // Obtiene el nombre del paquete actual 
	  				String csvFileName = "inscripcion.csv"; 
	  				String csvFilePath = packageName.replace('.', '/') + "/" + csvFileName;
	  
	  				try (InputStream inputStream = LoadDatabase.class.getClassLoader().getResourceAsStream(csvFilePath);
	  						InputStreamReader reader = new InputStreamReader(inputStream); 
	  						CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) { boolean skipHeader = true; 
	  						for (CSVRecord csvRecord : csvParser) { 
	  							if (skipHeader) { 
	  								skipHeader = false; 
	  								continue; // Salta la primera fila (encabezados)
	  								 } // Leer datos del CSV y crear objetos Estudiante, Carrera e Inscripcion 
	  							
	  							//int id = Integer.parseInt(csvRecord.get(0)); 
	  							int id_estudiante = Integer.parseInt(csvRecord.get(1)); 
	  							int id_carrera = Integer.parseInt(csvRecord.get(2)); 
	  							int inscripcion = Integer.parseInt(csvRecord.get(3)); 
	  							int graduacion = Integer.parseInt(csvRecord.get(4)); 
	  							int antiguedad = Integer.parseInt(csvRecord.get(5));

	  							Optional<Estudiante> e = estudianteRepository.findById(id_estudiante);
	  							
	  							Optional<Carrera> c = carreraRepository.findById(id_carrera);
	  							
//
//		  							System.out.println(id_estudiante);
//		  							//System.out.println(e.get());
//		  							System.out.println(id_carrera);
//		  							//System.out.println(c.get());
//		  							System.out.println(inscripcion);
//		  							System.out.println(graduacion);
//		  							System.out.println(antiguedad);
//	  							 
	  							if(e.isPresent() && c.isPresent()) {
	  								Inscripcion i = new Inscripcion(e.get(), c.get(), inscripcion, graduacion, antiguedad);
  									inscripcionRepository.save(i);
  									
  		  							//System.out.println(id_estudiante);
  		  							//System.out.println(e.get().toString());
  		  							//System.out.println(id_carrera);
  		  							//System.out.println(c.get().toString());
  		  							//System.out.println(inscripcion);
  		  							//System.out.println(graduacion);
  		  						//	System.out.println(antiguedad);
	  							}
	  }
	  
	  } catch (IOException e) { log.error("Error al leer el archivo CSV: " +
	  e.getMessage()); } }; 
}
	  
	 
	  
//	  @Bean CommandLineRunner initDatabase(@Qualifier("estudianteRepository")
//	  EstudianteRepository repository) { return args -> { log.info("Preloading " +
//	 repository.save(new Estudiante("Bernardo", "Gonzalez", 32, "Masculino",
//	  "Tandil", (int) 35418667, null))); log.info("Preloading " +
//	  repository.save(new Estudiante("Sofia", "Mondelo", 28, "Femenino", "Tandil",
//	  (int) 38418467, null))); log.info("Preloading " + repository.save(new
//	  Estudiante("Mauro", "Valerioti", 29, "NoBinario", "Tandil", (int) 38602821,
//	  null))); }; }
	  
//	  @Bean CommandLineRunner initDatabase2(@Qualifier("carreraRepository")
//	  CarreraRepository repository) { return args -> { log.info("Preloading " +
//	  repository.save(new Carrera("tudai", (int) 3))); log.info("Preloading " +
//	  repository.save(new Carrera("ing sistemas", (int) 5))); }; }
//	  
//	  @Bean CommandLineRunner initDatabase3(@Qualifier("inscripcionRepository")
//	  InscripcionRepository repository) { return args -> { log.info("Preloading " +
//	  repository.save(new Inscripcion())); }; }
//	 
}