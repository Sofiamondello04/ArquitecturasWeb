package com.example.microusuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.microusuarios.model.Usuario;

//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Monopatin, Integer>. Lo cambie para poder usar optional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	
	
	/*@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	
	Optional<Usuario> findUserByEmailIgnoreCase(@Param("email") String email);*/
	
	@Query("SELECT u From Usuario u JOIN FETCH u.roles WHERE u.email = :email ")
	Optional<Usuario> findUserByEmailIgnoreCase( String email );
    boolean existsUsersByEmailIgnoreCase(String email );

}
