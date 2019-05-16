/**
 *. 
 */
package modelos;

/**
 * @author emili
 *
 */
public class Carta {

	private String nombreProducto;
	private Double precioUnidad;
	
	
	public String getNombreProducto() {
		return nombreProducto;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
	}



	public Carta(String nombreProducto, Double precioUnidad) {
		super();
		this.nombreProducto = nombreProducto;
		this.precioUnidad = precioUnidad;
		
	}
	
}
