package com.paraparp.modelo.entities;


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
@Table(name = "articulo")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String color;
	private Productogenerico productogenerico;
	private String talla;
	private String codigoBarras;
	private int stock;
	private String foto;
	private String detalles;
	private List<Lineapedido> lineapedidos;

	public Articulo() {
	}


	
	public Articulo(String color, Productogenerico productogenerico, String talla, String codigoBarras,
			 int stock, String foto, String detalles, List<Lineapedido> lineapedidos) {
		this.color = color;
		this.productogenerico = productogenerico;
		this.talla = talla;
		this.codigoBarras = codigoBarras;

		this.stock = stock;
		this.foto = foto;
		this.detalles = detalles;
		this.lineapedidos = lineapedidos;
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


	public String getColor() {
		return this.color;
	}

	@Column(name = "color", length = 20)
	public void setColor(String color) {
		this.color = color;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idProductoGenerico", nullable = false)
	public Productogenerico getProductogenerico() {
		return this.productogenerico;
	}

	public void setProductogenerico(Productogenerico productogenerico) {
		this.productogenerico = productogenerico;
	}

	@Column(name = "talla", length = 20)
	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Column(name = "codigoBarras", nullable = false, length = 30)
	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	@Column(name = "stock")
	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name = "foto", length = 200)
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Column(name = "detalles", length = 300)
	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "articulo")
	public List<Lineapedido> getLineapedidos() {
		return this.lineapedidos;
	}

	public void setLineapedidos(List<Lineapedido> lineapedidos) {
		this.lineapedidos = lineapedidos;
	}
	
	
	public void actualizarStock(int cantidad) {
		
		this.stock = this.stock + cantidad;
	}


	@Override
	public String toString() {
		return "Articulo [id=" + id + ", color=" + color + ", productogenerico=" + productogenerico + ", talla=" + talla
				+ ", codigoBarras=" + codigoBarras +  ", stock=" + stock + ", foto="
				+ foto + ", detalles=" + detalles + "]";
	}

}
