/**
 * Yago Ozores.
 */

package modelos;

import java.time.LocalDate;

public class PedidoProducto {
	


	private String ident_producto;
	private String cod_proveedor;
	private String cod_emple;
	private double gastos;
	private LocalDate fecha_encargo;
	private int cantidad;
	private int cod_pedido;
	



	public int getCod_pedido() {
		return cod_pedido;
	}


	public PedidoProducto(String ident_producto, String cod_proveedor, String cod_emple, double gastos,
			LocalDate fecha_encargo, int cantidad, int cod_pedido) {
		super();
		this.ident_producto = ident_producto;
		this.cod_proveedor = cod_proveedor;
		this.cod_emple = cod_emple;
		this.gastos = gastos;
		this.fecha_encargo = fecha_encargo;
		this.cantidad = cantidad;
		this.cod_pedido = cod_pedido;
	}


	public String getIdent_producto() {
		return ident_producto;
	}


	public void setIdent_producto(String ident_producto) {
		this.ident_producto = ident_producto;
	}


	public String getCod_proveedor() {
		return cod_proveedor;
	}


	public void setCod_proveedor(String cod_proveedor) {
		this.cod_proveedor = cod_proveedor;
	}


	public String getCod_emple() {
		return cod_emple;
	}


	public void setCod_emple(String cod_emple) {
		this.cod_emple = cod_emple;
	}


	public double getGastos() {
		return gastos;
	}


	public void setGastos(double gastos) {
		this.gastos = gastos;
	}


	public LocalDate getFecha_encargo() {
		return fecha_encargo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setFecha_encargo(LocalDate fecha_encargo) {
		this.fecha_encargo = fecha_encargo;
	}
}