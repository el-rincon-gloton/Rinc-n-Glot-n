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
	private String contrase�a;
	private String correo;
	
	public Cliente( String nombreUsu,String nombre, String apellido,String correo,String contrase�a) {
		super();
		this.nombreUsu = nombreUsu;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrase�a = contrase�a;

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

	public String getContrase�a() {
		return contrase�a;
	}

	public String getCorreo() {
		return correo;
	}
}
