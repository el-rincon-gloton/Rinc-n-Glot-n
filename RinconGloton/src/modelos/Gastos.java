/**
 * Mario Toledo
 */
package modelos;

import java.time.LocalDate;

public class Gastos {
private String cod_gasto;
private double Presupuesto;
private double importe_gasto;
private double resultado;
private LocalDate fechas_gasto;



public Gastos(String cod_gasto, double presupuesto, double importe_Gasto, double resultado, LocalDate fechas_gasto) {
	super();
	this.cod_gasto = cod_gasto;
	Presupuesto = presupuesto;
	this.importe_gasto = importe_gasto;
	this.resultado = resultado;
	this.fechas_gasto = fechas_gasto;
}

public double getPresupuesto() {
	return Presupuesto;
}

public String getCod_gasto() {
	return cod_gasto;
}

public double getResultado() {
	return resultado;
}

public LocalDate getFechas_gasto() {
	return fechas_gasto;
}

public double getImporte_gasto() {
	return importe_gasto;
}


}
