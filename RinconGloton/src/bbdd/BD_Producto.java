package bbdd;

/**
 * @author Adrian Crespo
 *
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;


import modelos.Producto;

public class BD_Producto extends BD_Conector {
	private static Statement s;		
	private static ResultSet reg;
	
	public BD_Producto(String bbdd) {
		super(bbdd);
		// TODO Auto-generated constructor stub
	}
	
	public  int añadir_Producto(Producto pro){	
		String cadenaSQL="INSERT INTO productos VALUES('" + pro.getIdent_producto() + "','" +
		pro.getCod_prov()+"','"+ pro.getFecha_entrega()+"','"+ pro.getFecha_caducidad()+"','"+ pro.getCoste()
		+"','"+ pro.getCod_emple()+"')"; 	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch ( SQLException e){			
			return -1;
		}
	}
	
	public  Vector<Producto> listado_Producto(String Ident_producto){
		String cadenaSQL="SELECT * from productos WHERE Ident_producto='"+Ident_producto+"'";
		Vector<Producto> listaProducto=new Vector<Producto>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				// La fecha que se extrae de la bbdd es sql.Date, hay que transformarla a LocalDate
				java.sql.Date f=reg.getDate("Fecha_entrega");
				java.sql.Date f2=reg.getDate("Fecha_caducidad");
				LocalDate fentrega=f.toLocalDate();
				LocalDate fcaducidad=f.toLocalDate();
				listaProducto.add(new Producto(reg.getString("Ident_producto"),reg.getString("Cod_prov"),fentrega,fcaducidad,reg.getInt("Coste"),reg.getString("Cod_emple")));
				
			}
			s.close();
			this.cerrar();
			return listaProducto;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	
	public int borrar_Producto(Producto pro){
		String cadena="DELETE FROM productos WHERE Ident_producto='" + pro.getIdent_producto()+ "' AND Cod_prov='" + pro.getCod_prov()+"' AND Fecha_entrega='" + pro.getFecha_entrega()
		+"' AND Fecha_caducidad='" + pro.getFecha_caducidad()+"' AND Coste='"+pro.getCoste()+"' AND Cod_emple='"+pro.getCod_emple()+ "')";	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadena);	
		s.close();
		this.cerrar();
		return filas;
		
		}
		catch ( SQLException e){
			this.cerrar();
			return -1;
		}
	}
	
	public  String buscarProducto(Producto pro){
		String cadena="SELECT Ident_producto FROM productos WHERE Ident_producto='" +  pro.getIdent_producto()+ "' AND Cod_prov='" + pro.getCod_prov()+"' AND Fecha_entrega='" + pro.getFecha_entrega()+ 
				"' AND Fecha_caducidad='" + pro.getFecha_caducidad()+"' AND Coste='"+pro.getCoste()+"' AND Cod_emple='"+pro.getCod_emple()+ "')";	
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
