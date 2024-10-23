package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabajoFinal.Entidad.Docente;
import com.example.trabajoFinal.Repositorio.DocenteRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DocenteServiceImp implements DocenteService {

    private final DocenteRepositorio docenteRepositorio;

    @Autowired
    public DocenteServiceImp(DocenteRepositorio docenteRepositorio) {
        this.docenteRepositorio = docenteRepositorio;
    }

    @Override
    public List<Docente> obtenerTodosLosDocentes() {
        return docenteRepositorio.findAll();
    }

    @Override
    public Optional<Docente> obtenerDocentePorLegajo(Long legajo) {
        return docenteRepositorio.findById(legajo);
    }

    @Override
    public Docente guardarDocente(Docente docente) {
        return docenteRepositorio.save(docente);
    }

    @Override
    public void eliminarDocente(Long legajo) {
        docenteRepositorio.deleteById(legajo);
    }
    
     
    @Override
	public Docente actualizarDocente(Long legajo, Docente nuevosDatosDocente) {
		Docente docenteExistente = docenteRepositorio.findById(legajo)
	            .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con legajo: " + legajo));
        docenteExistente.setNombre(nuevosDatosDocente.getNombre());

        return docenteRepositorio.save(docenteExistente);
    }
}

