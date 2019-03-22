package com.codetreatise.modelo.entities;

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
@Table(name = "talla")
public class Talla implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private List<Articulo> articulos;

	public Talla() {
	}

	public Talla(String nombre) {
		this.nombre = nombre;
	}

	public Talla(String nombre, String descripcion, List<Articulo> articulos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.articulos = articulos;
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

	@Column(name = "nombre", nullable = false, length = 45)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "talla")
	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Talla [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", articulos=" + articulos
				+ "]";
	}

}
