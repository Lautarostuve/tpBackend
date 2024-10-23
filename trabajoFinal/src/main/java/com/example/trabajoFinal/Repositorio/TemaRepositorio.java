package com.example.trabajoFinal.Repositorio;

import com.example.trabajoFinal.Entidad.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Integer> {
	
	
}