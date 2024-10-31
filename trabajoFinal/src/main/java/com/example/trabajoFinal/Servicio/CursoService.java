package com.example.trabajoFinal.Servicio;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import com.example.trabajoFinal.Entidad.Curso;

public interface CursoService {
    List<Curso> obtenerTodosLosCursos();
    Optional<Curso> obtenerCursoPorId(Integer id);
    Curso guardarCurso(Curso curso);
    void eliminarCurso(Integer id);
    
    // Cursos que finalizan en una fecha determinada
    List<Curso> obtenerCursosPorFechaFin(LocalDate fechaFin);

    // Alumnos del curso vigente de un profesor
    List<String> obtenerAlumnosPorProfesor(Long legajoProfesor);
    
    List<Curso> obtenerCursosVigentesPorProfesor(Long legajoProfesor, LocalDate fecha);
    
    public Curso actualizarCurso(Integer id, Curso nuevosDatosCurso);
}
