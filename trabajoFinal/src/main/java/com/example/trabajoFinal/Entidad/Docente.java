package com.example.trabajoFinal.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "docentes")
public class Docente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long legajo;
	@Column(name = "nombre")
	private String nombre;
	
	
	public Docente() {}
	
	public Docente(Long legajo, String nombre) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Docente [legajo=" + legajo + ", nombre=" + nombre + "]";
	}

	public Long getLegajo() {
		return legajo;
	}

	public void setLegajo(Long legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
}
