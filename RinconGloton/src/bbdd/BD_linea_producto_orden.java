/**
 * Yago Ozores
 */

package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import modelos.LineaOrden;
import modelos.LineaProductoPedido;


public class BD_linea_producto_orden extends BD_Conector{

	public BD_linea_producto_orden(String fileName) {
		super(fileName);

	}
	
	private static Statement s;
	private static ResultSet reg;

	public boolean añadirLineaOrdenProducto(LineaProductoPedido lpp) {
		String cadena = "INSERT INTO linea_pedido_producto VALUES('" + lpp.getIdent_producto() + "','" + lpp.getLinea_prodcuto() + "','"
				+ lpp.getFecha_caducidad() + "','" + lpp.getCoste() + "')";

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
	
	//Falta el metodo de modificar, a ver que ponemos
	
	
	public String borrarLineaOrdenProducto(LineaProductoPedido lpp) {
		String cadena="DELETE ident_producto FROM pedidos_productos WHERE cod_orden='" + lpp.getIdent_producto() +"' AND linea_producto LIKE'" + lpp.getLinea_prodcuto() +"'";
 

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
	
	
	public String  buscarLineaOrden(LineaProductoPedido lpp) {
		String cadena="SELECT ident_producto FROM linea_producto_pedido WHERE linea_prodcuto='" + lpp.getLinea_prodcuto()+"'";
		
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getString(1);							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
		
		
		
	}

}
