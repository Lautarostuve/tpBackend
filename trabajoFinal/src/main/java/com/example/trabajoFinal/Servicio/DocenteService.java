package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;

import com.example.trabajoFinal.Entidad.Docente;

public interface DocenteService {

	List<Docente> obtenerTodosLosDocentes();
    Optional<Docente> obtenerDocentePorLegajo(Long legajo);
    Docente guardarDocente(Docente docente);
    void eliminarDocente(Long legajo);
    
    public Docente actualizarDocente(Long legajo, Docente nuevosDatosDocente);
	
}
