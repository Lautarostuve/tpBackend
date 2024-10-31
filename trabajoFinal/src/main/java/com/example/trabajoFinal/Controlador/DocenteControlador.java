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

import com.example.trabajoFinal.Entidad.Docente;
import com.example.trabajoFinal.Servicio.DocenteService;

@RestController
@RequestMapping("/docentes")
public class DocenteControlador {

    @Autowired
    private DocenteService docenteServicio;

    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/")
	public List<Docente> obtenerTodosLosDocentes(){
    	return docenteServicio.obtenerTodosLosDocentes();
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/saludo")
    public String funcionMostrar(){
		return "Hola y bienvenidos";
	}
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/{legajo}")
    public ResponseEntity<Optional<Docente>> obtenerDocentePorId(@PathVariable("legajo") Long legajo) {
        Optional <Docente> docente = docenteServicio.obtenerDocentePorLegajo(legajo);
        if (docente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(docente);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<Docente> guardarDocente(@RequestBody Docente docente) {
        Docente nuevoDocente = docenteServicio.guardarDocente(docente);
        return new ResponseEntity<>(nuevoDocente, HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PutMapping("/{legajo}")
    public ResponseEntity<Docente> actualizarDocente(@PathVariable Long legajo, @RequestBody Docente docente) {
        Docente docenteActualizado = docenteServicio.actualizarDocente(legajo, docente);
        return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);
    }
 
    
    @CrossOrigin(origins= "http://localhost:4200")
    @DeleteMapping("/{legajo}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable long legajo) {
        docenteServicio.eliminarDocente(legajo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}