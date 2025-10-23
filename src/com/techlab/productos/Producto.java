package com.techlab.productos;

public class Producto {

	private static int proximoId = 0;
	private int id;
	private String nombre;
	private double precio;
	private int stock;

	public Producto(String nombre, double precio, int stock) {
		this.id = asignarId();
		setNombre(nombre);
		setPrecio(precio);
		setStock(stock);
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		validarNombre(nombre);
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		validarPrecio(precio);
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		validarStock(stock);
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("%3d | %-30s | $%12.2f | %5d", id, nombre, precio, stock);
	}

	private int asignarId() {
		return proximoId++;
	}

	private void validarNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("Nombre inválido");
		}
	}

	private void validarPrecio(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}
	}

	private void validarStock(int stock) {
		if (stock < 0) {
			throw new IllegalArgumentException("El stock no puede ser negativo");
		}
	}

}
