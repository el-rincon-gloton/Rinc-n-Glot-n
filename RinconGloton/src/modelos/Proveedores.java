package modelos;

/**
 * @author Jcaballero.
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

	public String getCod_prov() {
		return cod_prov;
	}

	public void setCod_prov(String cod_prov) {
		this.cod_prov = cod_prov;
	}

	public String getNombre_prov() {
		return nombre_prov;
	}

	public void setNombre_prov(String nombre_prov) {
		this.nombre_prov = nombre_prov;
	}

	public String getApellido_prov() {
		return apellido_prov;
	}

	public void setApellido_prov(String apellido_prov) {
		this.apellido_prov = apellido_prov;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
}
