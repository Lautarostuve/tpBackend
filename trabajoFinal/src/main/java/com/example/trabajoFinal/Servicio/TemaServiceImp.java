package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabajoFinal.Entidad.Tema;
import com.example.trabajoFinal.Repositorio.TemaRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TemaServiceImp implements TemaService {

    private final TemaRepositorio temaRepositorio;

    @Autowired
    public TemaServiceImp(TemaRepositorio temaRepositorio) {
        this.temaRepositorio = temaRepositorio;
    }

    @Override
    public List<Tema> obtenerTodosLosTemas() {
        return temaRepositorio.findAll();
    }

    @Override
    public Optional<Tema> obtenerTemaPorId(Integer id) {
        return temaRepositorio.findById(id);
    }

    @Override
    public Tema guardarTema(Tema tema) {
        return temaRepositorio.save(tema);
    }

    @Override
    public void eliminarTema(Integer id) {
        temaRepositorio.deleteById(id);
    }
    
     
    @Override
	public Tema actualizarTema(Integer id, Tema nuevosDatosTema) {
		Tema temaExistente = temaRepositorio.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con id: " + id));
        temaExistente.setNombre(nuevosDatosTema.getNombre());
        temaExistente.setDescripcion(nuevosDatosTema.getDescripcion());
      
        
        return temaRepositorio.save(temaExistente);
    }
}
