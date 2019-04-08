package com.paraparp.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Empleado empleado;
	private Proveedor proveedor;
	private String codigo;
	private Date fechaPedido;
	private Date fechaRecibido;
	private BigDecimal  gastos;
	private String detalles;
	private List<Lineapedido> lineapedidos;

	public Pedido() {
	}

	public Pedido(Empleado empleado, Proveedor proveedor, String codigo) {
		this.empleado = empleado;
		this.proveedor = proveedor;
		this.codigo = codigo;
	}

	public Pedido(Empleado empleado, Proveedor proveedor, String codigo, Date fechaPedido, Date fechaRecibido,
			BigDecimal gastos, String detalles, List<Lineapedido> lineapedidos) {
		this.empleado = empleado;
		this.proveedor = proveedor;
		this.codigo = codigo;
		this.fechaPedido = fechaPedido;
		this.fechaRecibido = fechaRecibido;
		this.gastos = gastos;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEmpleado", nullable = false)
	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idProveedor", nullable = false)
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@Column(name = "codigo", nullable = false, length = 45)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaPedido", length = 10)
	public Date getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaRecibido", length = 10)
	public Date getFechaRecibido() {
		return this.fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	@Column(name = "gastos")
	public BigDecimal getGastos() {
		return this.gastos;
	}

	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	@Column(name = "detalles", length = 200)
	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido")
	public List<Lineapedido> getLineapedidos() {
		return this.lineapedidos;
	}

	public void setLineapedidos(List<Lineapedido> lineapedidos) {
		this.lineapedidos = lineapedidos;
	}
	


	@Override
	public String toString() {
		return codigo + " - " + proveedor + " - " + fechaRecibido ;
	}
	

}
