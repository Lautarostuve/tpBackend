package com.example.trabajoFinal.Servicio;

import java.util.List;
import java.util.Optional;

import com.example.trabajoFinal.Entidad.Alumno;


public interface AlumnoService {

	List<Alumno> obtenerTodosLosAlumnos();
    Optional<Alumno> obtenerAlumnoPorId(Integer id);
    Alumno guardarAlumno(Alumno alumno);
    void eliminarAlumno(Integer id);
    
    public Alumno actualizarAlumno(Integer id, Alumno nuevosDatosAlumno);
}
