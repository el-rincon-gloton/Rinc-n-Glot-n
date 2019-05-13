/**
 * Mario Toledo
 */
package modelos;

import java.time.LocalDate;

public class Ventas {
private String Cod_Venta;
private double Beneficio;
private LocalDate fechaVenta;
private String lugar;

public Ventas(String cod_Venta, double beneficio, LocalDate fechaVenta, String lugar) {
	super();
	Cod_Venta = cod_Venta;
	Beneficio = beneficio;
	this.fechaVenta = fechaVenta;
	this.lugar = lugar;
}

public String getCod_Venta() {
	return Cod_Venta;
}

public double getBeneficio() {
	return Beneficio;
}

public LocalDate getFechaVenta() {
	return fechaVenta;
}

public String getLugar() {
	return lugar;
}


}
