package com.techlab.sistema;

import com.techlab.productos.Producto;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sistema {

	private Map<Integer, Producto> productos;
	private final Scanner scanner = new Scanner(System.in);
	private final String[] OPCIONES_MENU_PRINCIPAL = {
		"SALIR",
		"Agregar producto",
		"Listar productos",
		"Buscar producto",
		"Actualizar producto",
		"Eliminar producto",
		"Crear un pedido",
		"Listar pedidos"
	};

	public Sistema() {
		this.productos = new HashMap<>();
	}

	public void iniciar() {
		crearInventarioInicial();

		boolean mostrarMenuPrincipal = true;

		while (mostrarMenuPrincipal) {
// TODO: Implementar limpiarPantalla()

			mostrarMenu(OPCIONES_MENU_PRINCIPAL);

			int opcionElegida = Integer.parseInt(leerIngresoTeclado());

			System.out.println("opcionElegida = " + opcionElegida);

			ejecutarOpcion(opcionElegida);

		}

	}

	private void mostrarEncabezado() {
		System.out.println("""
			╔═════════════════════════════════════════════════════╗
			║                     - TechLab -                     ║
			║            Sistema de gestión de pedidos            ║
			╚═════════════════════════════════════════════════════╝
			""");
	}

	private void mostrarMenu(String[] opciones) {
		mostrarEncabezado();

		StringBuilder sb = new StringBuilder(
			"Ingrese un número correspondiente a la opcion deseada:\n");
		int cantidadOpcionesMenu = opciones.length;

		for (int numeroOpcion = 0; numeroOpcion < cantidadOpcionesMenu; numeroOpcion++) {
			String opcion = opciones[numeroOpcion];

			sb.append(String.format("%s -> %s\n", numeroOpcion, opcion));
		}

		System.out.println(sb);

	}

	private String leerIngresoTeclado() {
		return scanner.nextLine();
	}

	private void ejecutarOpcion(int opcion) {
		switch (opcion) {
			case 1 -> agregarPorducto(productos);
			case 2 -> listarProductos();

			default -> System.out.println("Opción no válida. Intente nuevamente.");
		}
	}

	private void agregarPorducto(Map<Integer, Producto> productos) {
		System.out.println("\nAgregando producto...");

		System.out.print("Ingrese el nombre del producto: ");
		String nombre = leerIngresoTeclado();

		System.out.print("Ingrese el precio del producto: ");
		double precio = Double.parseDouble(leerIngresoTeclado());

		System.out.print("Ingrese el stock inicial: ");
		int stockInicial = Integer.parseInt(leerIngresoTeclado());

		Producto nuevoProducto = new Producto(nombre, precio, stockInicial);

		productos.put(nuevoProducto.getId(), nuevoProducto);

	}

	private void crearInventarioInicial() {
		String[] nombres = {
			"Notebook Lenovo ThinkPad",
			"Mouse Logitech MX Master 3",
			"Teclado Mecánico Redragon",
			"Monitor Samsung 27\" Curvo",
			"Auriculares Sony WH-1000XM5",
			"Smartphone Samsung Galaxy S24",
			"Tablet Apple iPad Air",
			"Impresora HP LaserJet",
			"Disco SSD Kingston 1TB",
			"Placa de video NVIDIA RTX 4070"
		};

		double[] precios = {
			950000, 85000, 40000, 250000, 300000,
			1200000, 850000, 200000, 150000, 1800000
		};

		int[] stocks = {10, 25, 40, 12, 8, 15, 9, 6, 30, 4};

		for (int i = 0; i < nombres.length; i++) {
			Producto producto = new Producto(nombres[i], precios[i], stocks[i]);
			productos.put(producto.getId(), producto);
		}

	}

	private void listarProductos() {
		if (productos.isEmpty()) {
			System.out.println("\nNo hay productos en el inventario.\n");
			return;
		}

		System.out.println("\nListado de productos:");
		System.out.printf(" %2s | %-30s | %-13s | %-5s%n", "ID", "Nombre", "Precio", "Stock");
		System.out.println("------------------------------------------------------------");

		for (Producto p : productos.values()) {
			System.out.println(p);
		}

		System.out.println("\nPrecione cualquier tecla para continuar...");
		leerIngresoTeclado();
	}

}
