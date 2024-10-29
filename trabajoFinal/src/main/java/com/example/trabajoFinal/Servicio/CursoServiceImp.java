package com.example.trabajoFinal.Servicio;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabajoFinal.Entidad.Curso;
import com.example.trabajoFinal.Repositorio.CursoRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepositorio cursoRepositorio;

    @Autowired
    public CursoServiceImp(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    @Override
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepositorio.findAll();
    }

    @Override
    public Optional<Curso> obtenerCursoPorId(Integer id) {
        return cursoRepositorio.findById(id);
    }

    @Override
    public Curso guardarCurso(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    @Override
    public void eliminarCurso(Integer id) {
        cursoRepositorio.deleteById(id);
    }
    
    
    // Implementación para obtener los cursos que finalizan en una fecha específica
    @Override
    public List<Curso> obtenerCursosPorFechaFin(LocalDate fechaFin) {
        return cursoRepositorio.findAll().stream()
            .filter(curso -> curso.getFechaFin().equals(fechaFin))
            .collect(Collectors.toList());
    }


    // Implementación para obtener los alumnos del curso vigente de un profesor
    @Override
    public List<String> obtenerAlumnosPorProfesor(Long legajoProfesor) {
        return cursoRepositorio.findAll().stream()
            .filter(curso -> curso.getDocente().getLegajo().equals(legajoProfesor) )
            .flatMap(curso -> curso.getAlumnos().stream())
            .map(alumno -> alumno.getNombre())
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Curso> obtenerCursosVigentesPorProfesor(Long legajoProfesor, LocalDate fecha) {
        return cursoRepositorio.findAll().stream()
            .filter(curso -> curso.getDocente().getLegajo().equals(legajoProfesor))  // Filtrar por legajo del profesor
            .filter(curso -> !curso.getFechaInicio().isAfter(fecha) && !curso.getFechaFin().isBefore(fecha))  // Vigencia del curso
            .collect(Collectors.toList());
    }

    
    @Override
	public Curso actualizarCurso(Integer id, Curso nuevosDatosCurso) {
		Curso CursoExistente = cursoRepositorio.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con id: " + id));
        CursoExistente.setTema(nuevosDatosCurso.getTema());
        CursoExistente.setFechaInicio(nuevosDatosCurso.getFechaInicio());
        CursoExistente.setFechaFin(nuevosDatosCurso.getFechaFin());
        CursoExistente.setDocente(nuevosDatosCurso.getDocente());
        CursoExistente.setPrecio(nuevosDatosCurso.getPrecio());
        CursoExistente.setAlumnos(nuevosDatosCurso.getAlumnos());
        
        return cursoRepositorio.save(CursoExistente);
    }
}
