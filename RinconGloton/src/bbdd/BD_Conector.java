/*
 * BD_Conector: Se encarga de abrir y cerrar la base de datos es un metodo estatico
 */
package bbdd;

import java.sql.*;

public class BD_Conector {
	static private String base;
	static private String usuario;
	static private String pass;
	static private String url;
	static protected Connection c;
	
	public static void BD_Ini(String bbdd){		
		base=bbdd;
		usuario="root";
		pass="";
		url="jdbc:mysql://localhost/"+base;
	}
	
	public void abrir(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
		 	c=DriverManager.getConnection(url,usuario,pass);
		}
		catch (SQLException e ){
			System.out.println(e.getMessage());
		}
	
	}	
	

	public void cerrar(){
		try{
			c.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
	}
	
}