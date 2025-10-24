package com.techlab.sistema;

import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sistema {

	private Map<Integer, Producto> productos;

	private final Scanner scanner = new Scanner(System.in);
	boolean mostrarMenuPrincipal = true;
	private final String[] OPCIONES_MENU_PRINCIPAL = {
		"Salir",
		"Crear Stock Inicial",
		"Agregar producto",
		"Listar producto",
		"Buscar producto",
		"Editar producto",
		"Eliminar producto",
		"Crear un pedido",
		"Listar pedidos"
	};

	public Sistema() {
		this.productos = new HashMap<>();
	}

	public void iniciar() {

		while (mostrarMenuPrincipal) {
			// TODO: Implementar limpiarPantalla()
			mostrarMenu(OPCIONES_MENU_PRINCIPAL);
			System.out.print("Opcion Elegida: ");
			int opcionElegida = Integer.parseInt(leerIngresoTeclado());
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
		return scanner.nextLine().trim();
	}

	private void ejecutarOpcion(int opcion) {
		switch (opcion) {
			case 0 -> salir();
			case 1 -> crearInventarioInicial();
			case 2 -> agregarProducto(productos);
			case 3 -> listarProductos(productos);
			case 4 -> buscarProductosPorNombre(productos);
			case 5 -> editarProducto(productos);
			case 6 -> eliminarProducto(productos);

			default -> System.out.println("Opción no válida. Intente nuevamente.");
		}
	}

	private void salir() {
		System.out.println("Saliendo...");
		this.mostrarMenuPrincipal = false;
	}

	private void eliminarProducto(Map<Integer, Producto> productos) {
		System.out.println("Elimnar producto");
		System.out.print("Engrese el id del producto que desea eliminar: ");

		int idBorrar = Integer.parseInt(leerIngresoTeclado());

		Producto productoEliminado = productos.remove(idBorrar);

		System.out.println("productoEliminado = " + productoEliminado);
		esperar();
	}


	private void editarProducto(Map<Integer, Producto> productos) {
		int idBuscado = solicitarIdProducto();
		Producto producto = productos.get(idBuscado);

		if (producto == null) {
			System.out.println("No se encontró un producto con ese ID.");
			return;
		}

		System.out.println("Producto a editar: " + producto);

		int opcionElegida = mostrarMenuEdicion();

		switch (opcionElegida) {
			case 1 -> producto.setNombre(solicitarNuevoNombre());
			case 2 -> producto.setPrecio(solicitarNuevoPrecio());
			case 3 -> producto.setStock(solicitarNuevaCantidad());
			case 4 -> {
				producto.setNombre(solicitarNuevoNombre());
				producto.setPrecio(solicitarNuevoPrecio());
				producto.setStock(solicitarNuevaCantidad());
			}
			case 5 -> System.out.println("Saliendo...");
			default -> System.out.println("Opción no válida.");
		}

		System.out.println("Producto actualizado: " + producto);
		esperar();
	}

	private int solicitarIdProducto() {
		System.out.print("Ingrese el ID del producto a editar: ");
		return Integer.parseInt(leerIngresoTeclado());
	}

	private int mostrarMenuEdicion() {
		System.out.println("Ingrese un número correspondiente a la opción que desea editar:");
		System.out.println("1- Nombre");
		System.out.println("2- Precio");
		System.out.println("3- Cantidad");
		System.out.println("4- Todo");
		System.out.println("5- Salir");
		return Integer.parseInt(leerIngresoTeclado());
	}

	private String solicitarNuevoNombre() {
		System.out.print("Ingrese el nuevo nombre: ");
		return leerIngresoTeclado();
	}

	private double solicitarNuevoPrecio() {
		System.out.print("Ingrese el nuevo precio: ");
		return Double.parseDouble(leerIngresoTeclado());
	}

	private int solicitarNuevaCantidad() {
		System.out.print("Ingrese la nueva cantidad: ");
		return Integer.parseInt(leerIngresoTeclado());
	}


	private void esperar() {
		System.out.println("Presione enter para continuar...");
		leerIngresoTeclado();
	}


	private void buscarProductosPorNombre(Map<Integer, Producto> productos) {
		List<Producto> listaProductos = new ArrayList<>(productos.values());
		buscarProductosPorNombre(listaProductos);

	}

	private void buscarProductosPorNombre(List<Producto> productos) {
		System.out.println("Ingrese un nombre de un producto: ");
		String productoBuscado = leerIngresoTeclado().toLowerCase();
		List<Producto> listaProductosEncontrados = new ArrayList<>();

		for (Producto producto : productos) {
			String nombreProducto = producto.getNombre().toLowerCase();

			if (nombreProducto.contains(productoBuscado)) {
				listaProductosEncontrados.add(producto);
			}
		}

		listarProductos(listaProductosEncontrados);
	}


	private void agregarProducto(Map<Integer, Producto> productos) {
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
			"Impresora HP InkJet",
			"Disco SSD Kingston 1TB",
			"Disco SSD Seagate 1TB",
			"Placa de video NVIDIA RTX 4070"
		};

		double[] precios = {
			950000, 85000, 40000, 250000, 300000,
			1200000, 850000, 200000, 250000, 150000, 200000, 1800000
		};

		int[] stocks = {10, 25, 40, 12, 8, 15, 9, 10, 6, 30, 40, 4};

		for (int i = 0; i < nombres.length; i++) {
			Producto producto = new Producto(nombres[i], precios[i], stocks[i]);
			productos.put(producto.getId(), producto);
		}

		System.out.println("Inventario creado:");
		listarProductos(productos);

	}

	private void listarProductos(Map<Integer, Producto> productos) {
		List<Producto> listaProductos = new ArrayList<>(productos.values());
		listarProductos(listaProductos);
	}

	private void listarProductos(List<Producto> listaProductos) {
		if (listaProductos.isEmpty()) {
			System.out.println("\nNo hay productos en el inventario.\n");
			return;
		}

		System.out.println("\nListado de productos:");
		System.out.printf(" %2s | %-30s | %-13s | %-5s%n", "ID", "Nombre", "Precio", "Stock");
		System.out.println("------------------------------------------------------------");

		for (Producto p : listaProductos) {
			System.out.println(p);
		}

		esperar();
	}

}
