package com.example.microusuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.response.MonopatinResponseRest;
import com.example.microusuarios.model.Cuenta;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.repository.CuentaRepository;
import com.example.microusuarios.repository.UsuarioRepository;
import com.example.microusuarios.response.UsuarioResponseRest;
import com.example.demo.model.mysql.Monopatin;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	CuentaRepository cuentaRepository;
	 @Autowired
	   private PasswordEncoder passwordEncoder;
	 @Autowired
	 private RestTemplate restTemplate;
	
	@Transactional(readOnly=true)
	public ResponseEntity<UsuarioResponseRest> getAll() {
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		try {
			List<Usuario> usuario = (List<Usuario>)usuarioRepository.findAll();
			response.getUsuarioResponse().setUsuario(usuario);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<UsuarioResponseRest> getById(Long id) {
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		List <Usuario> list = new ArrayList<>();
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if (usuario.isPresent()) {
				list.add(usuario.get());
				response.getUsuarioResponse().setUsuario(list);
				response.setMetadaData("ok", "00", "Usuario encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<UsuarioResponseRest> save(Usuario usuario) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List <Usuario> list = new ArrayList<>();
		
		try {
			// Codificar la contraseña antes de guardarla en la base de datos
            String rawPassword = usuario.getPassword(); // Obtener la contraseña sin codificar
            String encodedPassword = passwordEncoder.encode(rawPassword);
            usuario.setPassword(encodedPassword); // Establecer la contraseña codificada en el objeto Usuari
			Usuario usuarioSaved = usuarioRepository.save(usuario);
			if (usuarioSaved != null) {
				list.add(usuarioSaved);
				response.getUsuarioResponse().setUsuario(list);
				response.setMetadaData("ok", "00", "Usuario guardado");
			}
			else {
				response.setMetadaData("nok", "-1", "Usuario no guardado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar usuario");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<UsuarioResponseRest> updateById(Usuario usuario, Long id) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		List <Usuario> list = new ArrayList<>();
		try {
			Optional<Usuario> usuarioSearch = usuarioRepository.findById(id);
			if (usuarioSearch.isPresent()) {
				usuarioSearch.get().setNombre(usuario.getNombre());
				usuarioSearch.get().setApellido(usuario.getApellido());
				usuarioSearch.get().setNroCelular(usuario.getNroCelular());
				usuarioSearch.get().setEmail(usuario.getEmail());
				Usuario usuarioToUpdate = usuarioRepository.save(usuarioSearch.get());
				if (usuarioToUpdate != null) {
					list.add(usuarioToUpdate);
					response.getUsuarioResponse().setUsuario(list);
					response.setMetadaData("ok", "00", "Usuario actualizado");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Usuario no actualizado");
					return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar usuario");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<UsuarioResponseRest> deleteById(Long id) {
		UsuarioResponseRest response = new UsuarioResponseRest();
		try {
			usuarioRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<UsuarioResponseRest> vincularCuentaUsuario(Long idUsuario, Long idCuenta) {
		 UsuarioResponseRest responseU = new UsuarioResponseRest();

		    try {
		        Optional<Usuario> usuarioSearch = usuarioRepository.findById(idUsuario);
		        Optional<Cuenta> cuentaSearch = cuentaRepository.findById(idCuenta);

		        if (usuarioSearch.isPresent() && cuentaSearch.isPresent()) {
		            Usuario usuario = usuarioSearch.get();
		            Cuenta cuenta = cuentaSearch.get();
		            if(!usuario.getCuentas().contains(cuenta)) {
		            	usuario.getCuentas().add(cuenta); // Agregar la cuenta al usuario
		            	cuenta.getUsuarios().add(usuario); // Agregar el usuario a la cuenta
		            	usuarioRepository.save(usuario);
			            cuentaRepository.save(cuenta);

			            responseU.setMetadaData("ok", "00", "Cuenta vinculada al usuario correctamente");
		            }
		            else {
		            	responseU.setMetadaData("nok", "-1", "La cuenta ya se encuentra vinculada al usuario");
		            }

		            
		        } else {
		            responseU.setMetadaData("nok", "-1", "Usuario o cuenta no encontrada");
		            return new ResponseEntity<UsuarioResponseRest>(responseU, HttpStatus.BAD_REQUEST);
		        }
		    } catch (Exception e) {
		        responseU.setMetadaData("Respuesta error", "-1", "Error al vincular la cuenta al usuario");
		        e.printStackTrace();
		    }

		    return new ResponseEntity<UsuarioResponseRest>(responseU, HttpStatus.OK);
	}

	
	
	/*---------------------------administrador------------------------------------------*/
		
	@Transactional
	public ResponseEntity<UsuarioResponseRest> anularCuenta(Long idUsuario, Long idCuenta) {
		UsuarioResponseRest responseU = new UsuarioResponseRest();

	    try {
	        Optional<Usuario> usuarioSearch = usuarioRepository.findById(idUsuario);
	        Optional<Cuenta> cuentaSearch = cuentaRepository.findById(idCuenta);

	        if (usuarioSearch.isPresent() && cuentaSearch.isPresent()) {
	            Usuario usuario = usuarioSearch.get();
	            Cuenta cuenta = cuentaSearch.get();
	            if(usuario.getCuentas().contains(cuenta)) {
	            	usuario.getCuentas().remove(cuenta);
	            	cuenta.getUsuarios().remove(usuario);
	            	usuarioRepository.save(usuario);
		            cuentaRepository.save(cuenta);
		            

		            responseU.setMetadaData("ok", "00", "Cuenta desvinculada al usuario correctamente");
	            }
	            else {
	            	responseU.setMetadaData("nok", "-1", "La cuenta no se encuentra vinculada al usuario");
	            }

	            
	        } else {
	            responseU.setMetadaData("nok", "-1", "Usuario o cuenta no encontrada");
	            return new ResponseEntity<UsuarioResponseRest>(responseU, HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        responseU.setMetadaData("Respuesta error", "-1", "Error al desvincular la cuenta al usuario");
	        e.printStackTrace();
	    }

	    return new ResponseEntity<UsuarioResponseRest>(responseU, HttpStatus.OK);
	}


	
	/*@Transactional
	 * public List<Monopatin> requiereMantenimiento() {
		 HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		    List<Monopatin> response = (List<Monopatin>) restTemplate.exchange(
		    		"http://localhost:8081/api/v1/monopatines/mantenimientoMonopatines",
		            HttpMethod.GET,
		            requestEntity,
		            new ParameterizedTypeReference<List<Monopatin>>() {});
		     
		    return response;
	}*/
	
	@Transactional
	public List<Monopatin> requiereMantenimiento() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<List<Monopatin>> responseEntity = restTemplate.exchange(
	            "http://localhost:8081/api/v1/monopatines/mantenimientoMonopatines",
	            HttpMethod.GET,
	            requestEntity,
	            new ParameterizedTypeReference<List<Monopatin>>() {});

	    List<Monopatin> response = responseEntity.getBody();
	    
	    return response;
	}

	
	
}
