package modelos;

/**
 * @author Jcaballero
 *
 */

public class Proveedores {

	private String cod_prov;
	private String nombre_prov;
	private String apellido_prov;
	private String direccion;
	private int telefono;
	
	public Proveedores(String cod_prov, String nombre_prov, String apellido_prov, String direccion, int telefono) {
		super();
		this.cod_prov = cod_prov;
		this.nombre_prov = nombre_prov;
		this.apellido_prov = apellido_prov;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
}
