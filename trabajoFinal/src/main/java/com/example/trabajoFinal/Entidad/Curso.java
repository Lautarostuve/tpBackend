package com.example.trabajoFinal.Entidad;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity  //marca la clase como una entidad JPA mapeada a la tabla de la DB
@Table(name="cursos")
public class Curso {
	
	@Id //Lo define como PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //EL ID se genera automaticamente, autoincrementado
	private int id;
	
	@ManyToOne  //muchos cursos pueden estar asociados a un tema
	@JoinColumn(name = "tema_id")
	Tema tema;
	
	@Column
	private LocalDate fechaInicio;
	
	@Column
	private LocalDate fechaFin;
	
	@ManyToOne //muchos cursos pueden estar asociados a un docente
	@JoinColumn(name = "docente_legajo")
	Docente docente;
	
	@Column
	private Double precio; //puede tener decimales con double
	
	
	@ManyToMany(fetch = FetchType.LAZY)  //muchos cursos pueden tener muchos alumnos, los alumnos pueden tener muchos cursos
    @JoinTable(  //Se especifica la tabla intermedia curso_alumno que contiene las FK de curso_id y alumno_id
        name = "curso_alumno",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private List<Alumno> alumnos;
	
	
	
//Constructores
	public Curso() {} //Para que JPA pueda crear instancias de la entidad 

	public Curso(int id, Tema tema_id, LocalDate fechaInicio, LocalDate fechaFin, Docente docente_legajo, Double precio, List<Alumno> alumnos) {
		super();
		this.id = id;
		this.tema = tema_id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.docente = docente_legajo;
		this.precio = precio;
		this.alumnos = alumnos;
	} 

//Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema_id) {
		this.tema = tema_id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente_legajo) {
		this.docente = docente_legajo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
}
