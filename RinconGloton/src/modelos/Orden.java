package modelos;

/**
 * @author Adrian Crespo
 *
 */

public class Orden {
	private String Cod_orden;
	private String Cod_emple;
	private String Precio;
	private String Direccion;
	private String Ident_producto;
	
	public Orden(String cod_orden, String cod_emple, String precio, String direccion, String ident_producto) {
		super();
		Cod_orden = cod_orden;
		Cod_emple = cod_emple;
		Precio = precio;
		Direccion = direccion;
		Ident_producto = ident_producto;
	}
	
	
	
	
}
