package modelos;

/**
 * @author Adrian Crespo
 *
 */

public class Orden {
	private String Cod_orden;
	private String Cod_emple;
	private Double Precio;
	private String Direccion;
	private int p1;
	private int p2;
	private int p3;
	private int p4;
	private int p5;

	

	public Orden(String cod_orden, String cod_emple, Double precio, String direccion, int p1, int p2, int p3, int p4,
			int p5) {
		super();
		Cod_orden = cod_orden;
		Cod_emple = cod_emple;
		Precio = precio;
		Direccion = direccion;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
	}



	public String getCod_orden() {
		return Cod_orden;
	}



	public String getCod_emple() {
		return Cod_emple;
	}



	public Double getPrecio() {
		return Precio;
	}



	public String getDireccion() {
		return Direccion;
	}



	public int getP1() {
		return p1;
	}



	public int getP2() {
		return p2;
	}



	public int getP3() {
		return p3;
	}



	public int getP4() {
		return p4;
	}



	public int getP5() {
		return p5;
	}



}

