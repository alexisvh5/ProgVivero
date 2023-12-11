package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.MenuPrincipal;
import ar.edu.unlam.pb1.dominio.Planta;
import ar.edu.unlam.pb1.dominio.TipoDePlanta;
import ar.edu.unlam.pb1.dominio.Vivero;

public class GestionDeVivero {
	private static final int SALIR = 99;
	private static final String MENU_TIPO_DE_PLANTA = "\n\nIngrese el tipo de planta:\n1 - Hierba\n2 - Mata\n3 - Arbusto\n4 - Arbol";
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		String nombreVivero = ingresarString("\nIngrese el nombre del vivero");
		int cantidadMacetas = ingresarNumeroEntero("\nIngrese la cantidad maxima de macetas");

		Vivero vivero = new Vivero(nombreVivero, cantidadMacetas);
		

		mostrarPorPantalla("\n\nLa contrasenia generada es: " + vivero.getContrasenia());

		boolean sesionIniciada = false;

		do {
			// TODO: completar el inicio de sesion y la validacion
			sesionIniciada = ingresarContrasenia(vivero, sesionIniciada);
			
		} while (!sesionIniciada);
		
		int numeroIngresado = 0;
		MenuPrincipal opcionMenuPrincipal = null;

		do {
			mostrarPorPantalla("\n\n################################\nVivero " + vivero.getNombre());
			mostrarMenuPrincipal();
			numeroIngresado = validarOpcionIngresada();
			// TODO: Obtener la opcion del menu principal indicada por el usuario.
			// Considerar el caso de ingresar 99 (Salir)
			opcionMenuPrincipal = obtenerOpcionDeMenuPrincipal(numeroIngresado);

			switch (opcionMenuPrincipal) {
			case AGREGAR_PLANTA:
				// TODO: Ingresar una planta utilizando el metodo ingresarPlanta() y luego
				// mostrar si fue posible o no realizar la accion
				agregarPlanta(vivero);
				break;
			case MODIFICAR_STOCK_PLANTA:
				// TODO: Solicitar el codigo y nuevo stock de la planta a la cual se le quiere
				// actualizar el stock e indicar con mensajes si fue posible realizar la
				// operacion
				modificarStockDePlantaIndicada(vivero);
				break;
			case BUSCAR_PLANTA_POR_CODIGO:
				// TODO: Ingresar el codigo de la planta para buscarla, en caso de existir
				// mostrarla, caso contrario indicar con un mensaje que no se encontro
				buscarPlantaPorCodigo(vivero);
				break;
			case BUSCAR_PLANTAS_QUE_CONTIENEN:
				// TODO: Ingresar el texto a buscar en los nombres de las plantas y mostrar las
				// que contenga el texto (se provee de un metodo mostrarPlantas())
				buscarPlantasQueContienen(vivero);
				break;
			case VENDER_PLANTA:
				// TODO: Se debe solicitar el ingreso del codigo y cantidad a vender de la
				// planta. Mostrar un mensaje de exito o error segun corresponda
				venderPlanta(vivero);
				break;
			case OBTENER_PLANTAS_DEL_TIPO:
				// TODO: Ingresar el tipo de planta utilizando el metodo
				// ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA). Luego se debe obtener las plantas
				// de ese tipo y mostrarlas (se provee de un metodo mostrarPlantas())
				obtenerPlantasDelTipo(vivero);
				break;
			case SALIR:
				// TODO: Antes de salir, mostrar el estado actual del vivero para visualizar el
				// saldo
				mostrarPorPantalla("Saldo actual del vivero: " + Vivero.obtenerSaldo());
				mostrarPorPantalla("\n\nHasta luego!");
				break;
			}

			// TODO: Completar la condicion
		} while (!opcionMenuPrincipal.equals(MenuPrincipal.SALIR));

	}

	private static int validarOpcionIngresada() {
		int numeroIngresado;
		
		do {
		numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
		if(numeroIngresado == SALIR) {
			numeroIngresado = 7;
		}
		if(numeroIngresado < 1 || numeroIngresado > 7) {
			mostrarPorPantalla("Ingresa una opcion valida");
		}
		}while(numeroIngresado < 1 || numeroIngresado > 7);
		
		return numeroIngresado;
	}

	private static void obtenerPlantasDelTipo(Vivero vivero) {
		TipoDePlanta plantaSeleccionada = ingresarTipoDePlanta("Ingresa una opcion");
		Planta[] plantasOrdenadas = vivero.obtenerPlantasDeTipoOrdenadasPorNombreAscendende(plantaSeleccionada);
		mostrarPlantas(plantasOrdenadas);
	}

	private static void venderPlanta(Vivero vivero) {
		mostrarPlantas(vivero.mostrarPlantasEnVivero());
		int codigo = ingresarNumeroEntero("Codigo: ");
		int cantidadAVender = ingresarNumeroEntero("Cantidad a vender: ");
		boolean operacionExitosa = vivero.venderPlanta(codigo, cantidadAVender);
		if(operacionExitosa) {
			mostrarPorPantalla("Venta realizada exitosamente");
		}else {
			mostrarPorPantalla("Hubo un error");
		}
	}

	private static void buscarPlantasQueContienen(Vivero vivero) {
		String textoIngresado = ingresarString("Ingresa el texto a buscar: ");
		Planta[] plantasQueContienen = null;
		plantasQueContienen = vivero.buscarPlantasQueContienen(textoIngresado);
		mostrarPlantas(plantasQueContienen);
	}

	private static void buscarPlantaPorCodigo(Vivero vivero) {
		mostrarPlantas(vivero.mostrarPlantasEnVivero());
		int codigoIngresado = ingresarNumeroEntero("Codigo: ");
		Planta plantaEncontrada = vivero.buscarPlantaPorCodigo(codigoIngresado);
		if(plantaEncontrada != null) {
			mostrarPorPantalla(plantaEncontrada.toString());
		} else {
			mostrarPorPantalla("Planta no encontrada");
		}
	}

	private static void modificarStockDePlantaIndicada(Vivero vivero) {
		mostrarPlantas(vivero.mostrarPlantasEnVivero());
		int codigoPlanta = ingresarNumeroEntero("Codigo de planta: ");
		int nuevoStock = ingresarNumeroEntero("Ingresa la cantidad del stock: ");
		boolean operacionRealizada = vivero.modificarStockDePlantaPorCodigo(codigoPlanta,nuevoStock);
		if(operacionRealizada) {
			mostrarPorPantalla("Operacion exitosa");
		}else {
			mostrarPorPantalla("Operacion fallida");
		}
	}

	private static void agregarPlanta(Vivero vivero) {
		Planta plantaIngresada = crearPlanta();
		boolean hayEspacio = vivero.agregarPlanta(plantaIngresada);
		if(hayEspacio) {
			mostrarPorPantalla("Planta agregada exitosamente");
		}else {
			mostrarPorPantalla("Hubo un error");
		}
	}

	private static boolean ingresarContrasenia(Vivero vivero, boolean sesionIniciada) {
		String contraseniaIngresada = ingresarString("Ingresa la contrasenia: ");
		if(contraseniaIngresada.equals(vivero.getContrasenia())) {
			mostrarPorPantalla("Bienvenido!");
			sesionIniciada = true;
		} else {
			mostrarPorPantalla("Contrasenia incorrecta");
		}
		return sesionIniciada;
	}

	private static Planta crearPlanta() {
		// TODO: Solicitar el ingreso de los datos de una planta y devolver una
		// instancia de Planta. Considerar el uso del metodo
		// ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA)
	
		int codigo = ingresarNumeroEntero("Codigo: ");
		TipoDePlanta tipoDePlantaSeleccionada = ingresarTipoDePlanta("Tipo de planta: ");
		String nombreDePlanta = ingresarString("Nombre: ");
		double precio = ingresarDouble("Precio: ");
		int stock = ingresarNumeroEntero("Stock: ");
		Planta planta = new Planta(codigo,tipoDePlantaSeleccionada,nombreDePlanta,precio,stock);
		return planta;
	}

	private static MenuPrincipal obtenerOpcionDeMenuPrincipal(int numeroIngresado) {
		// TODO: Devolver la opcion seleccionada por el usuario existente en el enum
		return MenuPrincipal.values()[numeroIngresado-1];
	}

	private static TipoDePlanta ingresarTipoDePlanta(String mensaje) {
		// TODO: Solicitar el ingreso de la opcion requerida y devolver la opcion del
		// enum
		int opcionIngresada = 0;
		mostrarPorPantalla(MENU_TIPO_DE_PLANTA);
		do {
		opcionIngresada = teclado.nextInt();
		if(opcionIngresada < 1 || opcionIngresada > 4) {
			mostrarPorPantalla("Ingrese una opcion valida");
		}
		}while(opcionIngresada < 1 || opcionIngresada > 4);
		return TipoDePlanta.values()[opcionIngresada-1];
	}

	private static int ingresarNumeroEntero(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static double ingresarDouble(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		mostrarPorPantalla(mensaje);
		return teclado.nextDouble();
	}

	private static void mostrarPlantas(Planta[] plantas) {
		boolean hayPlantas = false;
		for (int i = 0; i < plantas.length; i++) {
			if (plantas[i] != null) {
				mostrarPorPantalla("\n" + plantas[i]);
				hayPlantas = true;
			}
		}
		if(!hayPlantas) {
			mostrarPorPantalla("No hay plantas");
		}
	}

	private static void mostrarMenuPrincipal() {
		mostrarPorPantalla(
				"\n\n1) Agregar planta\n2) Modificar stock de planta\n3) Buscar planta por su codigo\n4) Buscar plantas que en su nombre contienen"
				+ "\n5) Vender planta\n6) Mostrar plantas por tipo de planta\n\n99) Salir");
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
