/**
 * Yago Ozores
 */

package bbdd;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

import modelos.PedidoProducto;


public class BD_pedido_producto extends BD_Conector {

	public BD_pedido_producto(String fileName) {
		super();
	}

	private static Statement s;
	private static ResultSet reg;

	public boolean añadirPedidoProducto(PedidoProducto pepro) {
		String cadena = "INSERT INTO pedidos_productos VALUES('" + pepro.getIdent_producto() + "','"
				+ pepro.getCod_proveedor() + "','" + pepro.getCod_emple() + "','" + pepro.getGastos() + "','"
				+ pepro.getFecha_encargo() + "')";

		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}

	}
	
	

	public String buscarPedidoProducto(PedidoProducto pepro) {
		String cadena="SELECT ident_producto FROM pedidos_productos WHERE ident_producto='" + pepro.getIdent_producto() +"' AND cod_proveedor LIKE'" + pepro.getCod_proveedor() +"'";
 

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next())
				t = reg.getString(1);
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {

			return null;

		}

	}
	public String borrarPedidoProducto(PedidoProducto pepro) {
		String cadena="DELETE ident_producto FROM pedidos_productos WHERE ident_producto='" + pepro.getIdent_producto() +"' AND cod_proveedor LIKE'" + pepro.getCod_proveedor() +"'";
 

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next())
				t = reg.getString(1);
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {

			return null;

		}

	}
	
	
	

	public Vector<PedidoProducto> mostrarLineaOrden() {
		Vector<PedidoProducto> v = new Vector<PedidoProducto>();
		String cadena = "SELECT * FROM linea_orden";

		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date t=reg.getDate("fecha_encargo");
				LocalDate fecha = t.toLocalDate();
				v.add(new PedidoProducto(reg.getString("ident_producto"), reg.getString("cod_proveedor"),
						reg.getString("cod_emple"), reg.getDouble("gastos"), fecha));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {

			return null;

		}

	}

}
