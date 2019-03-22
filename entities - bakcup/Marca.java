package com.codetreatise.modelo.entities;
// Generated 12-mar-2019 11:13:14 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private List<Productogenerico> productogenericos;

	public Marca() {
	}

	public Marca(String nombre, String descripcion, List<Productogenerico> productogenericos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.productogenericos = productogenericos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marca")
	public List<Productogenerico> getProductogenericos() {
		return this.productogenericos;
	}

	public void setProductogenericos(List<Productogenerico> productogenericos) {
		this.productogenericos = productogenericos;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", productogenericos="
				+ productogenericos + "]";
	}

}
