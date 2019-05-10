package modelos;

import java.time.LocalDate;

/**
 * @author Adrian Crespo
 *
 */
public class Producto {
	 private String Ident_producto;
	 private String Cod_prov;
	 private LocalDate Fecha_entrega;
	 private LocalDate Fecha_caducidad;
	 private int Coste;
	 private String Cod_emple;
	 
	public Producto(String ident_producto, String cod_prov, LocalDate fecha_entrega, LocalDate fecha_caducidad,
			int coste, String cod_emple) {
		super();
		Ident_producto = ident_producto;
		Cod_prov = cod_prov;
		Fecha_entrega = fecha_entrega;
		Fecha_caducidad = fecha_caducidad;
		Coste = coste;
		Cod_emple = cod_emple;
	}
	 
}
