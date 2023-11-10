package com.example.microusuarios.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microusuarios.model.Rol;
import com.example.microusuarios.model.Usuario;

import com.example.microusuarios.repository.RolRepository;
import com.example.microusuarios.repository.UsuarioRepository;
import com.example.microusuarios.response.RolResponseRest;


@Service
public class RolService {
	@Autowired
	RolRepository rolRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly=true)
	public ResponseEntity<RolResponseRest> getAll() {
		
		RolResponseRest response = new RolResponseRest();
		try {
			List<Rol> rol = (List<Rol>)rolRepository.findAll();
			response.getRolResponse().setRol(rol);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<RolResponseRest> getById(Long id) {
		
		RolResponseRest response = new RolResponseRest();
		List <Rol> list = new ArrayList<>();
		try {
			Optional<Rol> rol = rolRepository.findById(id);
			if (rol.isPresent()) {
				list.add(rol.get());
				response.getRolResponse().setRol(list);
				response.setMetadaData("ok", "00", "Rol encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<RolResponseRest> save(Rol rol) {
		RolResponseRest response = new RolResponseRest();
		List <Rol> list = new ArrayList<>();
		
		try {
			Rol rolSaved = rolRepository.save(rol);
			if (rolSaved != null) {
				list.add(rolSaved);
				response.getRolResponse().setRol(list);
				response.setMetadaData("ok", "00", "Rol guardado");
			}
			else {
				response.setMetadaData("nok", "-1", "Rol no guardado");
				return new ResponseEntity<RolResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar rol");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<RolResponseRest> updateById(Rol rol, Long id) {
		RolResponseRest response = new RolResponseRest();
		List <Rol> list = new ArrayList<>();
		try {
			Optional<Rol> rolSearch = rolRepository.findById(id);
			if (rolSearch.isPresent()) {
				rolSearch.get().setNombre(rol.getNombre());
				Rol rolToUpdate = rolRepository.save(rolSearch.get());
				if (rolToUpdate != null) {
					list.add(rolToUpdate);
					response.getRolResponse().setRol(list);
					response.setMetadaData("ok", "00", "Rol actualizado");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Rol no actualizado");
					return new ResponseEntity<RolResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Rol no encontrado");
				return new ResponseEntity<RolResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar rol");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<RolResponseRest> deleteById(Long id) {
		RolResponseRest response = new RolResponseRest();
		try {
			rolRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<RolResponseRest> vincularUsuarioRol(Long idRol, Long idUsuario) {
		 RolResponseRest responseR = new RolResponseRest();

		    try {
		        Optional<Rol> rolSearch = rolRepository.findById(idRol);
		        Optional<Usuario> usuarioSearch = usuarioRepository.findById(idUsuario);

		        if (rolSearch.isPresent() && usuarioSearch.isPresent()) {
		            Rol rol = rolSearch.get();
		            Usuario usuario = usuarioSearch.get();
		            if(!rol.getUsuarios().contains(usuario)) {
		            	rol.getUsuarios().add(usuario);// Agregar el usuario al rol
		            	usuario.getRoles().add(rol); // Agregar el rol al usuario
		            	 
		            	rolRepository.save(rol);
		            	usuarioRepository.save(usuario);
			            

			            responseR.setMetadaData("ok", "00", "Usuario vinculado al rol correctamente");
		            }
		            else {
		            	responseR.setMetadaData("nok", "-1", "El usuario ya se encuentra vinculado al rol");
		            }

		            
		        } else {
		            responseR.setMetadaData("nok", "-1", "Rol o usuario no encontrado");
		            return new ResponseEntity<RolResponseRest>(responseR, HttpStatus.BAD_REQUEST);
		        }
		    } catch (Exception e) {
		        responseR.setMetadaData("Respuesta error", "-1", "Error al vincular el usuario al rol");
		        e.printStackTrace();
		    }

		    return new ResponseEntity<RolResponseRest>(responseR, HttpStatus.OK);
	}

	public ResponseEntity<RolResponseRest> desVincularUsuarioRol(Long idRol, Long idUsuario) {
		RolResponseRest responseR = new RolResponseRest();

	    try {
	        Optional<Rol> rolSearch = rolRepository.findById(idRol);
	        Optional<Usuario> usuarioSearch = usuarioRepository.findById(idUsuario);

	        if (rolSearch.isPresent() && usuarioSearch.isPresent()) {
	        	Rol rol = rolSearch.get();
	            Usuario usuario = usuarioSearch.get();
	           
	            if(rol.getUsuarios().contains(usuario)) {
	            	
	            	rol.getUsuarios().remove(usuario);
	            	usuario.getRoles().remove(rol);
	            	rolRepository.save(rol);
	            	usuarioRepository.save(usuario);
		            
		            

		            responseR.setMetadaData("ok", "00", "Usuario desvinculado al rol correctamente");
	            }
	            else {
	            	responseR.setMetadaData("nok", "-1", "El usuario no se encuentra vinculado al rol");
	            }

	            
	        } else {
	        	responseR.setMetadaData("nok", "-1", "Rol o usuario no encontrado");
	            return new ResponseEntity<RolResponseRest>(responseR, HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	    	responseR.setMetadaData("Respuesta error", "-1", "Error al desvincular el usuario al rol");
	        e.printStackTrace();
	    }

	    return new ResponseEntity<RolResponseRest>(responseR, HttpStatus.OK);
	}
	
}
