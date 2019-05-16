package modelos;

public class Empleados {

	/**
	 * @author Jcaballero.
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
	private int esEncargado;

	public Empleados(String cod_emple, String nombre, String apellido, String dNI, String direccion, int telefono,
			int num_seguridadSocial, String cargoEnLaEmpresa, int esEncargado) {
		super();
		this.cod_emple = cod_emple;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.direccion = direccion;
		this.telefono = telefono;
		this.num_seguridadSocial = num_seguridadSocial;
		this.cargoEnLaEmpresa = cargoEnLaEmpresa;
		this.esEncargado = esEncargado;
	}

	public String getCod_emple() {
		return cod_emple;
	}

	public void setCod_emple(String cod_emple) {
		this.cod_emple = cod_emple;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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

	public int getNum_seguridadSocial() {
		return num_seguridadSocial;
	}

	public void setNum_seguridadSocial(int num_seguridadSocial) {
		this.num_seguridadSocial = num_seguridadSocial;
	}

	public String getCargoEnLaEmpresa() {
		return cargoEnLaEmpresa;
	}

	public void setCargoEnLaEmpresa(String cargoEnLaEmpresa) {
		this.cargoEnLaEmpresa = cargoEnLaEmpresa;
	}

	public int getEsEncargado() {
		return esEncargado;
	}

	public void setEsEncargado(int esEncargado) {
		this.esEncargado = esEncargado;
	}

	
	
	

}
