package com.example.trabajoFinal.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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

import org.springframework.web.bind.annotation.RestController;

import com.example.trabajoFinal.Entidad.Alumno;
import com.example.trabajoFinal.Servicio.AlumnoService;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/alumnos")
public class AlumnoControlador {

    @Autowired
    private AlumnoService alumnoServicio;

    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("")
	public List<Alumno> obtenerTodosLosAlumnos(){
    	return alumnoServicio.obtenerTodosLosAlumnos();
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/saludovich")
    public String funcionMostrar(){
		return "Hola y bienvenidos";
	}
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Alumno>> obtenerAlumnoPorId(@PathVariable("id") Integer id) {
        Optional <Alumno> alumno = alumnoServicio.obtenerAlumnoPorId(id);
        if (alumno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumno);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<Alumno> guardarAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoServicio.guardarAlumno(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        Alumno alumnoActualizado = alumnoServicio.actualizarAlumno(id, alumno);
        return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
    }
 
    @CrossOrigin(origins= "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Integer id) {
        alumnoServicio.eliminarAlumno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}