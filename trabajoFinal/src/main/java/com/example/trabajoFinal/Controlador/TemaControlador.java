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

import com.example.trabajoFinal.Entidad.Tema;
import com.example.trabajoFinal.Servicio.TemaService;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/temas")
public class TemaControlador {

    @Autowired
    private TemaService temaServicio;
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/")
	public List<Tema> obtenerTodosLosTemas(){
    	return temaServicio.obtenerTodosLosTemas();
    }
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/saludovich")
    public String funcionMostrar(){
		return "Hola y bienvenidos";
	}
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tema>> obtenerTemaPorId(@PathVariable("id") Integer id) {
        Optional <Tema> tema = temaServicio.obtenerTemaPorId(id);
        if (tema.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tema);
    }
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<Tema> guardarTema(@RequestBody Tema tema) {
        Tema nuevoTema = temaServicio.guardarTema(tema);
        return new ResponseEntity<>(nuevoTema, HttpStatus.CREATED);
    }
    
    
    @CrossOrigin(origins= "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Tema> actualizarTema(@PathVariable Integer id, @RequestBody Tema tema) {
        Tema temaActualizado = temaServicio.actualizarTema(id, tema);
        return new ResponseEntity<>(temaActualizado, HttpStatus.OK);
    }
 
    
    @CrossOrigin(origins= "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTema(@PathVariable Integer id) {
        temaServicio.eliminarTema(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}
