/*
 * BD_Conector: Se encarga de abrir y cerrar la base de datos
 */
package bbdd;

import java.sql.*;


public class BD_Conector {
	private String base;
	private String usuario;
	private String pass;
	private String url;
	protected Connection c;
	
	
	
	public BD_Conector(String bbdd){		
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
