package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabajoFinal.Entidad.Alumno;
import com.example.trabajoFinal.Repositorio.AlumnoRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlumnoServiceImp implements AlumnoService {

    private final AlumnoRepositorio alumnoRepositorio;

    @Autowired
    public AlumnoServiceImp(AlumnoRepositorio alumnoRepositorio) {
        this.alumnoRepositorio = alumnoRepositorio;
    }

    @Override
    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepositorio.findAll();
    }

    @Override
    public Optional<Alumno> obtenerAlumnoPorId(Integer id) {
        return alumnoRepositorio.findById(Long.valueOf(id));
    }

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    @Override
    public void eliminarAlumno(Integer id) {
        alumnoRepositorio.deleteById(Long.valueOf(id));
    }
    
     
    @Override
	public Alumno actualizarAlumno(Integer id, Alumno nuevosDatosAlumno) {
		Alumno alumnoExistente = alumnoRepositorio.findById(Long.valueOf(id))
	            .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con id: " + id));
        alumnoExistente.setNombre(nuevosDatosAlumno.getNombre());
        alumnoExistente.setFechaNacimiento(nuevosDatosAlumno.getFechaNacimiento());
      
      
        
        return alumnoRepositorio.save(alumnoExistente);
    }
}
