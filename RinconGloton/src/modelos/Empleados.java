package modelos;

public class Empleados {
	
	/**
	 * @author Jcaballero
	 *
	 */
	private String cod_emple;
	private String nombre;
	private String apellido;
	private String DNI;
	private String direccion;
	private int telefono;
	private int num_seguridadSocial;
	private String cargoEnLaEmpresa;
	
	public Empleados(String cod_emple, String nombre, String apellido, String dNI, String direccion, int telefono,
			int num_seguridadSocial, String cargoEnLaEmpresa) {
		super();
		this.cod_emple = cod_emple;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.direccion = direccion;
		this.telefono = telefono;
		this.num_seguridadSocial = num_seguridadSocial;
		this.cargoEnLaEmpresa = cargoEnLaEmpresa;
	}
	
	
}
