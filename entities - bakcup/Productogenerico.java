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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productogenerico")
public class Productogenerico implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Categoria categoria;
	private Marca marca;
	private String codigo;
	private String nombre;
	private String descripcion;
	private List<Articulo> articulos;

	public Productogenerico() {
	}

	public Productogenerico(Categoria categoria, Marca marca, String codigo, String nombre) {
		this.categoria = categoria;
		this.marca = marca;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Productogenerico(Categoria categoria, Marca marca, String codigo, String nombre, String descripcion,
			List<Articulo> articulos) {
		this.categoria = categoria;
		this.marca = marca;
		this.codigo = codigo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMarca", nullable = false)
	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Column(name = "codigo", nullable = false, length = 20)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productogenerico")
	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Productogenerico [id=" + id + ", categoria=" + categoria + ", marca=" + marca + ", codigo=" + codigo
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + ", articulos=" + articulos + "]";
	}

}
