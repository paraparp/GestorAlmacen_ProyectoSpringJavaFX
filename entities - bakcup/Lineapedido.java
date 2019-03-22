package com.codetreatise.modelo.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lineapedido")
public class Lineapedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Articulo articulo;
	private Pedido pedido;
	private int cantidad;
	private BigDecimal precio;

	public Lineapedido() {
	}

	public Lineapedido(Articulo articulo, Pedido pedido, int cantidad) {
		this.articulo = articulo;
		this.pedido = pedido;
		this.cantidad = cantidad;
	}

	public Lineapedido(Articulo articulo, Pedido pedido, int cantidad, BigDecimal precio) {
		this.articulo = articulo;
		this.pedido = pedido;
		this.cantidad = cantidad;
		this.precio = precio;
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
	@JoinColumn(name = "idArticulo", nullable = false)
	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPedido", nullable = false)
	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Column(name = "cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "precio")
	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Lineapedido [id=" + id + ", articulo=" + articulo + ", pedido=" + pedido + ", cantidad=" + cantidad
				+ ", precio=" + precio + "]";
	}

}
