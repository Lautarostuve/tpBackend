package com.example.trabajoFinal.Repositorio;

import com.example.trabajoFinal.Entidad.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepositorio extends JpaRepository<Docente, Long> {
	
	
}
