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
@Table(name = "articulo")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Color color;
	private Productogenerico productogenerico;
	private Talla talla;
	private String codigoBarras;
	private Double precioVenta;
	private Integer stock;
	private String foto;
	private String detalles;
	private List<Lineapedido> lineapedidos;

	public Articulo() {
	}

	public Articulo(Color color, Productogenerico productogenerico, Talla talla, String codigoBarras) {
		this.color = color;
		this.productogenerico = productogenerico;
		this.talla = talla;
		this.codigoBarras = codigoBarras;

	}

	public Articulo(Color color, Productogenerico productogenerico, Talla talla, String codigoBarras,
			Double precioVenta, Integer stock, String foto, String detalles, List<Lineapedido> lineapedidos) {
		this.color = color;
		this.productogenerico = productogenerico;
		this.talla = talla;
		this.codigoBarras = codigoBarras;

		this.precioVenta = precioVenta;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idColor", nullable = false)
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProductoGenerico", nullable = false)
	public Productogenerico getProductogenerico() {
		return this.productogenerico;
	}

	public void setProductogenerico(Productogenerico productogenerico) {
		this.productogenerico = productogenerico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTalla", nullable = false)
	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	@Column(name = "codigoBarras", nullable = false, length = 30)
	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Column(name = "precioVenta", precision = 22, scale = 0)
	public Double getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "foto", length = 200)
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Column(name = "detalles", length = 45)
	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo")
	public List<Lineapedido> getLineapedidos() {
		return this.lineapedidos;
	}

	public void setLineapedidos(List<Lineapedido> lineapedidos) {
		this.lineapedidos = lineapedidos;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", color=" + color + ", productogenerico=" + productogenerico + ", talla=" + talla
				+ ", codigoBarras=" + codigoBarras + ", precioVenta=" + precioVenta + ", stock=" + stock + ", foto="
				+ foto + ", detalles=" + detalles + ", lineapedidos=" + lineapedidos + "]";
	}

}
