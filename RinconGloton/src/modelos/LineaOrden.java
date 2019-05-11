/**
 * Yago Ozores
 */

package modelos;

public class LineaOrden {
	private String cod_orden;
	private String ident_producto;
	private double cantidad;
	private String anotacion;
	
	
	
	public LineaOrden(String cod_orden, String ident_producto, double cantidad, String anotacion) {
		super();
		this.cod_orden = cod_orden;
		this.ident_producto = ident_producto;
		this.cantidad = cantidad;
		this.anotacion = anotacion;
	}
	
	

	public String getCod_orden() {
		return cod_orden;
	}

	public void setCod_orden(String cod_orden) {
		this.cod_orden = cod_orden;
	}

	public String getIdent_producto() {
		return ident_producto;
	}

	public void setIdent_producto(String ident_producto) {
		this.ident_producto = ident_producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}

	@Override
	public String toString() {
		return "LineaOrden [cod_orden=" + cod_orden + ", ident_producto=" + ident_producto + ", cantidad=" + cantidad
				+ ", anotacion=" + anotacion + "]";
	}
	
	
	
	

}
