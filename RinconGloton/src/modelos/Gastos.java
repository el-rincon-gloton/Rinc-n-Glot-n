/**
 * Mario Toledo.
 */
package modelos;

import java.time.LocalDate;

public class Gastos {
private LocalDate fechas_gasto;
private String descripcion_gasto;
private int cod_gasto;
private double importe_gasto;

public Gastos(LocalDate fechas_gasto, String descripcion_gasto, int cod_gasto, double importe_gasto) {
	super();
	this.fechas_gasto = fechas_gasto;
	this.descripcion_gasto = descripcion_gasto;
	this.cod_gasto = cod_gasto;
	this.importe_gasto = importe_gasto;
}

public int getCod_gasto() {
	return cod_gasto;
}

public LocalDate getFechas_gasto() {
	return fechas_gasto;
}

public double getImporte_gasto() {
	return importe_gasto;
}

public String getDescripcion_gasto() {
	return descripcion_gasto;
}


}
