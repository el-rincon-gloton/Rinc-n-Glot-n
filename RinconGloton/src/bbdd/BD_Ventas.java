/**
 * Mario Toledo
 */
package bbdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import norm.Ventas;
import norm.Ventas;

public class BD_Ventas extends BD_Conector {
	
	private static Statement s;		
	private static ResultSet reg;
	
	public BD_Ventas(String fileName) {
		super(fileName);
	}	
	
	public  boolean añadirVentas(Ventas vn){
		String cadena="INSERT INTO ventas VALUES('" + vn.getCod_Venta() + "','" + vn.getBeneficio()+"','"+ vn.getFechaVenta() +"','"+ vn.getLugar() +"')"; 	
		
		try{
		this.abrir();
		s=c.createStatement();
		s.executeUpdate(cadena);
		s.close();
		this.cerrar();
		return true;
		}
		catch ( SQLException e){
			this.cerrar();
			return false;}
		}
	public Vector <Ventas> buscarVentas(String Cod_Venta){
		String cadenaSQL = "Select * from ventas where Cod_Ventas='" + Cod_Venta +"'";
		Vector<Ventas> venta = new Vector<Ventas>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				java.sql.Date t=reg.getDate("FechaVenta");
				LocalDate fecha = t.toLocalDate();
				venta.add(new Ventas(reg.getString("Cod_Venta"), reg.getDouble("Beneficio"),fecha,reg.getString("Lugar")));
			}
			s.close();
			this.cerrar();
			return venta;
		} catch (SQLException e) {
			return null;
		}
	}
	public Vector <Ventas> buscarVentasTotal(){
		String cadenaSQL = "Select * from ventas";
		Vector<Ventas> venta = new Vector<Ventas>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				java.sql.Date t=reg.getDate("FechaVenta");
				LocalDate fecha = t.toLocalDate();
				venta.add(new Ventas(reg.getString("Cod_Venta"), reg.getDouble("Beneficio"),fecha,reg.getString("Lugar")));
			}
			s.close();
			this.cerrar();
			return venta;
		} catch (SQLException e) {
			return null;
		}
	}
	

}
