package com.example.microusuarios.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.repository.UsuarioRepository;
import com.example.microusuarios.response.UsuarioResponseRest;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
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
	




}
