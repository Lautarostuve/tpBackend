package com.example.trabajoFinal.Controlador;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.trabajoFinal.Entidad.Alumno;
import com.example.trabajoFinal.Entidad.Curso;
import com.example.trabajoFinal.Servicio.CursoService;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/cursos")
public class CursoControlador {

    @Autowired
    private CursoService cursoServicio;

    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("")
	public List<Curso> obtenerTodosLosCursos(){
    	return cursoServicio.obtenerTodosLosCursos();
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> obtenerCursoPorId(@PathVariable("id") Integer id) {
        Optional <Curso> curso = cursoServicio.obtenerCursoPorId(id);
        if (curso.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(curso);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/saludovich")
    public String funcionMostrar(){
		return "Hola y bienvenidos";
	}
    
    @CrossOrigin(origins= "http://localhost:4200")  //obtener curso por la fecha fin
    @GetMapping("/fecha-fin")
    public List<Curso> obtenerCursosPorFechaFin(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return cursoServicio.obtenerCursosPorFechaFin(java.sql.Date.valueOf(fechaFin));
    }

    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/profesor/{legajo}/alumnos")
    public ResponseEntity<List<String>> obtenerAlumnosPorProfesor(@PathVariable("legajo") Long legajoProfesor) {
        List<String> alumnos = cursoServicio.obtenerAlumnosPorProfesor(legajoProfesor);
        if (alumnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumnos);
    }
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/profesor/{fecha}/{legajo}")
    public List<Curso> obtenerCursosVigentesPorProfesor(@PathVariable("legajo") Long legajoProfesor, @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
    	return cursoServicio.obtenerCursosVigentesPorProfesor(legajoProfesor, java.sql.Date.valueOf(fecha));
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<Curso> guardarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoServicio.guardarCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoServicio.actualizarCurso(id, curso);
        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
    }
 
    
    @CrossOrigin(origins= "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        cursoServicio.eliminarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}