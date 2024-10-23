package com.example.trabajoFinal.Repositorio;

import com.example.trabajoFinal.Entidad.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {
	
	
}