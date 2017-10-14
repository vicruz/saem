package com.marti.educacion.saem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marti.educacion.saem.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsuario(String usuario);
	
	@Modifying
	@Query("Delete From User us Where us.id = :usuarioId")
	int deleteById(@Param("usuarioId")int usuarioId);
	
	@Query("Select us From User us Where us.id = :usuarioId")
	User findUserById(@Param("usuarioId")int usuarioId);
	
	@Query("Select us From User us Where us.usuario not in (:usuario)")
	List<User> findAllMinus(@Param("usuario") String usuario);
	
}
