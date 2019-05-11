/**
 * Yago Ozores
 */

package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import modelos.LineaOrden;
import modelos.PedidoProducto;

public class BD_lineaOrden extends BD_Conector {
	public BD_lineaOrden(String fileName) {
		super(fileName);

	}

	private static Statement s;
	private static ResultSet reg;

	public boolean añadirLineaOrden(LineaOrden lo) {
		String cadena = "INSERT INTO linea_orden VALUES('" + lo.getAnotacion() + "','" + lo.getCantidad() + "','"
				+ lo.getCod_orden() + "','" + lo.getIdent_producto() + "')";

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

	public String  buscarLineaOrden(LineaOrden lo) {
		String cadena="SELECT cod_orden FROM linea_orden WHERE cod_orden='" + lo.getCod_orden() +"' AND ident_producto='" + lo.getIdent_producto() +"'";
		
		
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
	/*
	public String borrarLineaOrden(LineaOrden lo) {
		String cadena="DELETE ident_producto FROM linea_orden WHERE cod_orden='" + lo.getCod_orden() +"' AND ident_producto LIKE'" + lo.getIdent_producto() +"'";
 

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
	*/
	
	
	
	
	public Vector<LineaOrden> mostrarLineaOrden(){
		Vector<LineaOrden> v=new Vector<LineaOrden>();
		String cadena="SELECT * FROM linea_orden";
		
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(new LineaOrden(reg.getString("cod_orden"),reg.getString("ident_producto"),reg.getDouble("cantidad"),reg.getString("anotacion")));
			}
			
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
	
			return null;
			
		}
		
	}

}
