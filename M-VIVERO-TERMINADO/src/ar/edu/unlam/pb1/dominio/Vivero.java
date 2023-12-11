package ar.edu.unlam.pb1.dominio;

public class Vivero {

	private String nombre;
	private static double saldo = 0;
	private Planta[] plantas;
	private String contrasenia;

	public Vivero(String nombre, int cupoMacetas) {
		// TODO: Completar el constructor. La contrasenia debe generarse y asignarse al
		// vivero en este constructor
		this.nombre = nombre;
		this.plantas = new Planta[cupoMacetas];
		generarContrasenia();
	}

	public static double obtenerSaldo() {
		return saldo;
	}

	public boolean iniciarSesion(String contrasenia) {
		return this.contrasenia.equals(contrasenia);
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean agregarPlanta(Planta planta) {
		// TODO: Agregar la planta al array solo si no existe otra con el mismo codigo
		// en el array de plantas. Considerar usar el metodo
		// buscarPlantaPorCodigo(codigo)
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] == null) {
				// poner if afuera del for para buscarlo una unica vez
				//si ya existe sumarle su stock
				if(buscarPlantaPorCodigo(planta.getCodigo()) == null) {
					plantas[i] = planta;
					return true;
				}
			}
		}
		return false;
	}

	public boolean venderPlanta(int codigo, int cantidadAVender) {
		// TODO: Para vender una planta, primero debemos revisar que exista (buscando
		// por codigo por ejemplo) y luego verificar que tenga stock para satisfacer la
		// cantidad a vender. Si es viable la venta, se debera modificar el stock de la
		// planta en el array de plantas (considerar el metodo modificarStockDePlantaPorCodigo()) para
		// disminuir el mismo, y se deberá
		// acumular el precio final de la venta al saldo del vivero. Ejemplo: Precio final de planta $10, cantidad de plantas en la venta 10 unidades, entonces se acumulan $100 al saldo del vivero
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null && plantas[i].getCodigo() == codigo) {
				if(cantidadAVender <= plantas[i].getStock()) {
					plantas[i].disminuirStock(cantidadAVender);
					Vivero.saldo += calcularVentas(plantas[i], cantidadAVender);
					return true;
				}
			}
		}
		return false;
	}



	private double calcularVentas(Planta planta, int cantidadAVender) {
		return planta.obtenerPrecioFinal() * cantidadAVender;
	}

	public Planta buscarPlantaPorCodigo(int codigo) {
		// TODO: Buscar la planta en el array de plantas utilizando el codigo de la
		// planta. En caso de no existir, devolver null.
		
		//usar break o while para al encontrarla no seguir buscando
		Planta plantaEncontrada = null;
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null && plantas[i].getCodigo() == codigo) {
				plantaEncontrada = plantas[i];
				break;
			}
		}
		return plantaEncontrada;
	}

	public Planta[] buscarPlantasQueContienen(String texto) {
		// TODO: Generar un nuevo array con las plantas que en su nombre, contengan el
		// texto suministrado. El array no debe poseer espacios, es decir, las plantas
		// deben ingresar ordenadamente (no permitir posiciones en null entre las plantas ingresadas).
		Planta[] plantasQueContienenTextoEspecificado = new Planta[plantas.length];
		int contador = 0;
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null) {
				if(plantas[i].getNombre().contains(texto)) {
					plantasQueContienenTextoEspecificado[contador++] = plantas[i];
				}
			}
		}
		return plantasQueContienenTextoEspecificado;
	}

	public boolean modificarStockDePlantaPorCodigo(int codigo, int stock) {
		// TODO: Iterar el array de plantas y en caso de existir alguna y que coincida
		// con el codigo suministrado, actualizar el stock de la misma.
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null && plantas[i].getCodigo() == codigo) {
				plantas[i].aumentarStock(stock);
				return true;
			}
		}
		return false;
	}

	public Planta[] obtenerPlantasDeTipoOrdenadasPorNombreAscendende(TipoDePlanta tipoDePlanta) {
		// TODO: Generar un nuevo array de plantas que coincidan con el tipo de planta
		// suministrado.
		//Antes de devolverlo, se debera ordenar por nombre ascendente.
		// Considerar el metodo ordenarPlantasPorNombreAscendente(array)
		Planta[] plantasOrdenadasAscendentementePorNombre = new Planta[plantas.length];
		int contador = 0;
		for(int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null && plantas[i].getTipoDePlanta().equals(tipoDePlanta)) {
				plantasOrdenadasAscendentementePorNombre[contador++] = plantas[i];
			}
		}
		ordenarPlantasPorNombreAscendente(plantasOrdenadasAscendentementePorNombre);
		return plantasOrdenadasAscendentementePorNombre;
	}

	@Override
	public String toString() {
		return "Vivero '" + nombre + "'. Saldo actual=" + saldo + "]";
	}

	private String generarContrasenia() {
		// TODO: Generar un contrasenia aleatoria y devolverla. La misma debe poseer:
		// 4 caracteres (en el orden que desee) los cuales deben ser:
		// 1 numero.
		// 1 letra mayuscula.
		// 2 letras minusculas.
		// Considerar el metodo obtenerCaracterAleatorio(1,10).
		String contraseniaActual = "";
		contraseniaActual += obtenerCaracterAleatorio(48, 57);
		contraseniaActual += obtenerCaracterAleatorio(65, 90);
		contraseniaActual += obtenerCaracterAleatorio(97, 122);
		contraseniaActual += obtenerCaracterAleatorio(97, 122);
		return this.contrasenia = contraseniaActual;
	}
	
	

	private char obtenerCaracterAleatorio(int posicionInicial, int posicionFinal) {
		// TODO: Generar un caracter aleatorio considerando las posiciones de los caracteres en la tabla ASCII
		int numeroAscii = 0;
		do {
			//numeroAscii = (int)(Math.random() * posicionFinal - posicionInicial) + posicionInicial;	
			numeroAscii = (int)(Math.random() * posicionFinal) + posicionInicial;	
		}while(numeroAscii < posicionInicial || numeroAscii > posicionFinal);
		return (char)numeroAscii;
	}

	private Planta[] ordenarPlantasPorNombreAscendente(Planta[] plantasAOrdenar) {
		// TODO: Ordenar las plantas en el array suministrado por nombre de manera ascendente y devolver el array 
		Planta aux = null;
		for (int i = plantasAOrdenar.length -1; i > 0; i--) {
		for (int j = 0; j < i; j++) {
			if(plantasAOrdenar[j] != null && plantasAOrdenar[j+1] != null ) {
			if(plantasAOrdenar[j].getNombre().compareTo(plantasAOrdenar[j+1].getNombre()) > 0) {
				aux = plantasAOrdenar[j+1];
				plantasAOrdenar[j+1] = plantasAOrdenar[j];
				plantasAOrdenar[j] = aux;
			}
			}
		}
	}
		return plantasAOrdenar;
	}

	public Planta[] mostrarPlantasEnVivero() {
		Planta[] plantasEnVivero = new Planta[plantas.length];
		int contador = 0;
		for (int i = 0; i < plantas.length; i++) {
			if(plantas[i] != null) {
				plantasEnVivero[contador++] = plantas[i];
			}
		}
		return plantasEnVivero;
	}

}
