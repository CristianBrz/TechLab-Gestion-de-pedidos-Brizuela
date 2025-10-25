package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {

	private Producto producto;
	private int cantidad;

	public LineaPedido(Producto producto, int cantidad) {
		setProducto(producto);
		setCantidad(cantidad);
	}


	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		validarProducto(producto);
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		validarCantidad(cantidad, this.producto);
		this.cantidad = cantidad;
	}

	private void validarProducto(Producto producto) {
		if (producto == null) {
			throw new IllegalArgumentException(
				"El producto no puede ser nulo");
		}
	}

	private void validarCantidad(int cantidad, Producto producto) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException(
				"La cantidad no puede ser menor a cero");
		}

		if (producto.getStock() < cantidad) {
			throw new IllegalArgumentException("Stock insuficiente: ");
		}


	}
}
