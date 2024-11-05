package com.example.trabajoFinal.Controlador;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


import com.example.trabajoFinal.Entidad.Curso;
import com.example.trabajoFinal.Servicio.CursoService;

@RestController
@CrossOrigin(origins= "http://localhost:4200") //permite el acceso CORS del front
@RequestMapping("/cursos") //define la ruta base /cursos para todas las solicitudes a este controlador
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
            return ResponseEntity.noContent().build(); //Si el curso no existe, responde con erorr noContent 204
        }
        return ResponseEntity.ok(curso); //devuelve el curso y codigo 200 ok
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/saludo")
    public String funcionMostrar(){
		return "Hola y bienvenidos";
	}
    
    @CrossOrigin(origins= "http://localhost:4200")  //obtener curso por la fecha fin
    @GetMapping("/fecha-fin") //La fecha se pasa como un parámetro de consulta (?fecha=yyyy-MM-dd)
    public List<Curso> obtenerCursosPorFechaFin(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return cursoServicio.obtenerCursosPorFechaFin(fechaFin);
    }

    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/profesor/{legajo}/alumnos")
    public ResponseEntity<List<String>> obtenerAlumnosPorProfesor(@PathVariable("legajo") Long legajoProfesor) {
        Set<String> alumnosSet = new HashSet<>(cursoServicio.obtenerAlumnosPorProfesor(legajoProfesor)); // Eliminar duplicados usando un Set
        if (alumnosSet.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<String> alumnosUnicos = new ArrayList<>(alumnosSet); // Convertir el Set de vuelta a una List
        return ResponseEntity.ok(alumnosUnicos);
    }

    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/profesor/{fecha}/{legajo}")
    public List<Curso> obtenerCursosVigentesPorProfesor(@PathVariable("legajo") Long legajoProfesor, @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
    	return cursoServicio.obtenerCursosVigentesPorProfesor(legajoProfesor, fecha);
    }
    
    //utiliza la fecha actual
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/profesor/{legajo}")
    public List<Curso> obtenerCursosVigentesPorProfesor2(@PathVariable("legajo") Long legajoProfesor){
    	return cursoServicio.obtenerCursosVigentesPorProfesor2(legajoProfesor);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<Curso> guardarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoServicio.guardarCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED); //devuelve codigo 201
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //devuelve codigo 204
    }
    
    

}