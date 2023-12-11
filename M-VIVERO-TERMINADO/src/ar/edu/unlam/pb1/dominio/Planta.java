package ar.edu.unlam.pb1.dominio;

public class Planta {

	private final double GANANCIA_HIERBA_MATA = 0.2;
	private final double GANANCIA_ARBUSTO = 0.6;
	private final double GANANCIA_ARBOL = 0.130;
	private int codigo;
	private TipoDePlanta tipoDePlanta;
	private String nombre;
	private double precioBase;
	private int stock;

	public Planta(int codigo, TipoDePlanta tipoDePlanta, String nombre, double precio, int stock) {
		// TODO: Completar el constructor
		this.codigo = codigo;
		this.tipoDePlanta = tipoDePlanta;
		this.nombre = nombre;
		this.precioBase = precio;
		this.stock = stock;
	}

	public double obtenerPrecioFinal() {
		// TODO: Calcular y devolver el precio final de la planta. Para ello, se debera revisar el TipoDePlanta considerando:
		// Hierba o Mata: se incrementa un 20% el precio base.
		// Arbusto: se incrementa un 60% el precio base.
		// Arbol: se incrementa un 130% el precio base.
		// Usar las constantes
		double valorObtenido = 0.0;
		if(this.tipoDePlanta.equals(TipoDePlanta.HIERBA) || this.tipoDePlanta.equals(TipoDePlanta.MATA)) {
			//puede ser que no modifique y retorne el valor calculado
			valorObtenido = precioBase + (precioBase * GANANCIA_HIERBA_MATA);
			// de momento 
			
		}else if(this.tipoDePlanta.equals(TipoDePlanta.ARBUSTO)) {
			valorObtenido = this.precioBase + (this.precioBase *  GANANCIA_ARBUSTO);
		} else if(this.tipoDePlanta.equals(TipoDePlanta.ARBOL)) {
			valorObtenido = this.precioBase + (this.precioBase *  GANANCIA_ARBOL);
		}
		//o switch
		return valorObtenido;
	}



	@Override
	public String toString() {
		return "Planta [codigo=" + codigo + ", tipoDePlanta=" + tipoDePlanta + ", nombre=" + nombre + ", precioBase="
				+ precioBase + ", stock=" + stock + "]";
	}

	public double getGANANCIA_HIERBA_MATA() {
		return GANANCIA_HIERBA_MATA;
	}


	public double getGANANCIA_ARBUSTO() {
		return GANANCIA_ARBUSTO;
	}

	public double getGANANCIA_ARBOL() {
		return GANANCIA_ARBOL;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TipoDePlanta getTipoDePlanta() {
		return tipoDePlanta;
	}

	public void setTipoDePlanta(TipoDePlanta tipoDePlanta) {
		this.tipoDePlanta = tipoDePlanta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void disminuirStock(int cantidadAVender) {
		this.stock -= cantidadAVender;
		
	}

	public void aumentarStock(int stock) {
		this.stock += stock;
		
	}


}
