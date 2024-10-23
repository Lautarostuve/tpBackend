package com.example.trabajoFinal.Repositorio;

import com.example.trabajoFinal.Entidad.Curso;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Integer> {

	
}