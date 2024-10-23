package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;

import com.example.trabajoFinal.Entidad.Tema;

public interface TemaService {

	List<Tema> obtenerTodosLosTemas();
    Optional<Tema> obtenerTemaPorId(Integer id);
    Tema guardarTema(Tema tema);
    void eliminarTema(Integer id);
    
    public Tema actualizarTema(Integer id, Tema nuevosDatosTema);
	
}
