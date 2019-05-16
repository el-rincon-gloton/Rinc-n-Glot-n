package modelos;

import java.time.LocalDate;

/**
 * @author Adrian Crespo.
 *
 */
public class Producto {
	 private String Ident_producto;
	 private String Cod_prov;
	 private LocalDate Fecha_entrega;
	 private LocalDate Fecha_caducidad;
	 private double Coste;
	 private String Cod_emple;
	 private int cantidad;
	 


	public Producto(String ident_producto, int cantidad) {
		super();
		Ident_producto = ident_producto;
		this.cantidad = cantidad;
	}

	

	public Producto(String ident_producto, String cod_prov, LocalDate fecha_entrega, LocalDate fecha_caducidad,
			double coste, String cod_emple, int cantidad) {
		super();
		Ident_producto = ident_producto;
		Cod_prov = cod_prov;
		Fecha_entrega = fecha_entrega;
		Fecha_caducidad = fecha_caducidad;
		Coste = coste;
		Cod_emple = cod_emple;
		this.cantidad = cantidad;
	}



	public String getIdent_producto() {
		return Ident_producto;
	}

	public void setIdent_producto(String ident_producto) {
		Ident_producto = ident_producto;
	}

	public String getCod_prov() {
		return Cod_prov;
	}

	public void setCod_prov(String cod_prov) {
		Cod_prov = cod_prov;
	}

	public LocalDate getFecha_entrega() {
		return Fecha_entrega;
	}

	public void setFecha_entrega(LocalDate fecha_entrega) {
		Fecha_entrega = fecha_entrega;
	}

	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public LocalDate getFecha_caducidad() {
		return Fecha_caducidad;
	}

	public void setFecha_caducidad(LocalDate fecha_caducidad) {
		Fecha_caducidad = fecha_caducidad;
	}

	public double getCoste() {
		return Coste;
	}

	public void setCoste(int coste) {
		Coste = coste;
	}

	public String getCod_emple() {
		return Cod_emple;
	}

	public void setCod_emple(String cod_emple) {
		Cod_emple = cod_emple;
	}
	
	
	
	 
}
