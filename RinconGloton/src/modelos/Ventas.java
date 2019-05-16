/**
 * Mario Toledo.
 */
package modelos;

import java.time.LocalDate;

public class Ventas {
private double Beneficio;
private int cod_orden;
private int Cod_Venta;
private LocalDate fechaVenta;




public Ventas(double beneficio, int cod_orden, int cod_Venta, LocalDate fechaVenta) {
	super();
	Beneficio = beneficio;
	this.cod_orden = cod_orden;
	Cod_Venta = cod_Venta;
	this.fechaVenta = fechaVenta;
}

public int getCod_Venta() {
	return Cod_Venta;
}

public double getBeneficio() {
	return Beneficio;
}

public LocalDate getFechaVenta() {
	return fechaVenta;
}

public int getCod_orden() {
	return cod_orden;
}



}
