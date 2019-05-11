package bbdd;

/**
 * @author Adrian Crespo
 *
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import modelos.Orden;

public class BD_Orden extends BD_Conector{
	private static Statement s;		
	private static ResultSet reg;
	
	public BD_Orden(String bbdd) {
		super(bbdd);
		// TODO Auto-generated constructor stub
	}
	

	public  int añadir_Orden(Orden or){	
		String cadenaSQL="INSERT INTO orden VALUES('" + or.getCod_orden() + "','" +
		or.getCod_emple()+"','"+ or.getPrecio()+"','"+ or.getDireccion()+"','"+ or.getIdent_producto()+"')"; 	
		
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
	
	public  Vector<String> listado_Orden(){
		String cadenaSQL="SELECT Cod_orden from orden";
		Vector<String> listaOrden=new Vector<String>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listaOrden.add(reg.getString(1));
			}			
			s.close();
			this.cerrar();
			return listaOrden;
		}
		catch ( SQLException e){
		//	System.out.println(e.getMessage());
			return null;
			
		}
	}
	
	public int borrar_Orden(Orden or){
		String cadena="DELETE FROM orden WHERE Cod_orden='" +  or.getCod_orden()+ "' AND Cod_emple='" + or.getCod_emple()+"' AND Precio='" + or.getPrecio()
		+"' AND Direccion='" + or.getDireccion()+"' AND Ident_producto='"+or.getIdent_producto()+ "')";	
		
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
	
	public  String buscarOrden(Orden or){
		String cadena="SELECT Cod_Orden FROM orden WHERE Cod_orden='" +  or.getCod_orden()+ "' AND Cod:emple='" + or.getCod_emple()+"' AND Precio='" + or.getPrecio() + 
				"' AND Direccion='" + or.getDireccion()+"' AND Ident_producto='"+or.getIdent_producto()+ "')";	
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