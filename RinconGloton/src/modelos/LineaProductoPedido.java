/**
 * Yago Ozores
 */

package modelos;

import java.time.LocalDate;

public class LineaProductoPedido {
	private String ident_producto;
	private String linea_prodcuto;
	private LocalDate fecha_caducidad;
	private double coste;
	
	

	public LineaProductoPedido(String ident_producto, String linea_prodcuto, LocalDate fecha_caducidad, double coste) {
		super();
		this.ident_producto = ident_producto;
		this.linea_prodcuto = linea_prodcuto;
		this.fecha_caducidad = fecha_caducidad;
		this.coste = coste;
	}
	
	

	public String getIdent_producto() {
		return ident_producto;
	}

	public void setIdent_producto(String ident_producto) {
		this.ident_producto = ident_producto;
	}

	public String getLinea_prodcuto() {
		return linea_prodcuto;
	}

	public void setLinea_prodcuto(String linea_prodcuto) {
		this.linea_prodcuto = linea_prodcuto;
	}

	public LocalDate getFecha_caducidad() {
		return fecha_caducidad;
	}

	public void setFecha_caducidad(LocalDate fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public double getCoste() {
		return coste;
	}
}