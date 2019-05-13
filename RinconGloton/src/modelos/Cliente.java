/**
 * 
 */
package modelos;

/**
 * @author jcaballero
 *
 */
public class Cliente {

	private String nombre;
	private String apellido;
	private String nombreUsu;
	private String contraseña;
	private String correo;
	
	public Cliente( String nombreUsu,String nombre, String apellido,String correo,String contraseña) {
		super();
		this.nombreUsu = nombreUsu;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contraseña = contraseña;

	} 
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombreUsu() {
		return nombreUsu;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getCorreo() {
		return correo;
	}
}
