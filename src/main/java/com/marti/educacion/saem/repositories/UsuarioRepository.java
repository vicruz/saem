package com.marti.educacion.saem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marti.educacion.saem.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByUsuario(String usuario);
	
	@Modifying
	@Query("Delete From Usuario us Where us.usuarioId = :usuarioId")
	int deleteById(@Param("usuarioId")int usuarioId);
	
	@Query("Select us From Usuario us Where us.usuarioId = :usuarioId")
	Usuario findUserById(@Param("usuarioId")int usuarioId);
	
	@Query("Select us From Usuario us Where us.usuario not in (:usuario)")
	List<Usuario> findAllMinus(@Param("usuario") String usuario);
	
}
