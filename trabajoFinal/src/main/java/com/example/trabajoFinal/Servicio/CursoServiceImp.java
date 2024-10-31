package com.example.trabajoFinal.Servicio;

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

    @Autowired //autowired le indica a Spring que debe inyectar una instancia de CursoRepositorio cuando cree CursoServiceImp.
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
    public List<Curso> obtenerCursosPorFechaFin(LocalDate fechaFin) {//obtiene todos los cursos y luego los filtra por los que su fechaFin es igual a la fecha parametro
        return cursoRepositorio.findAll().stream()  //procesa los elementos de una lista en una secuencia
            .filter(curso -> curso.getFechaFin().equals(fechaFin))
            .collect(Collectors.toList()); //collect me permite recolectar el resultado del filtro en la lista de cursos
    }


    // Implementación para obtener los alumnos del curso vigente de un profesor
    @Override
    public List<String> obtenerAlumnosPorProfesor(Long legajoProfesor) {
        return cursoRepositorio.findAll().stream()
            .filter(curso -> curso.getDocente().getLegajo().equals(legajoProfesor) )
            .flatMap(curso -> curso.getAlumnos().stream()) //convierte cada curso en un stream de alumnos, luego combina todos esos streams de alumnos en uno solo.
            .map(alumno -> alumno.getNombre()) //transforma cada alumno en su nombre.
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
