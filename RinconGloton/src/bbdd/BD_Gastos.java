/**
 * Mario Toledo
 */
package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import modelos.Gastos;


public class BD_Gastos extends BD_Conector {

	private static Statement s;		
	private static ResultSet reg;
	
	public BD_Gastos(String fileName) {
		super(fileName);
	}
	
	public  boolean añadirGasto(Gastos gs){
		String cadena="INSERT INTO gastos VALUES('" + gs.getCod_gasto() + "','"+ gs.getPresupuesto() +"','"+ gs.getImporte_gasto() +"','"+ gs.getResultado() +"','"+ gs.getFechas_gasto() +"')"; 	
		
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
			return false;
		}
		
}
	
	public Vector <Gastos> buscarGastos(String Cod_Gasto){
		String cadenaSQL = "Select * from gastos where Cod_Gasto='" + Cod_Gasto +"'";
		Vector<Gastos> gasto = new Vector<Gastos>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				java.sql.Date t=reg.getDate("fechas_gasto");
				LocalDate fecha = t.toLocalDate();
				gasto.add(new Gastos(reg.getString("cod_gasto"),reg.getDouble("presupuesto"), reg.getDouble("importe_gasto"),reg.getDouble("resultado"),fecha));
			}
			s.close();
			this.cerrar();
			return gasto;
		} catch (SQLException e) {
			return null;
		}
	}

	public Vector <Gastos> buscarGastosTotal(){
		String cadenaSQL = "Select * from gastos";
		Vector<Gastos> gasto = new Vector<Gastos>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				java.sql.Date t=reg.getDate("fecha");
				LocalDate fecha = t.toLocalDate();
				gasto.add(new Gastos(reg.getString("cod_gasto"),reg.getDouble("presupuesto"), reg.getDouble("importe_gasto"),reg.getDouble("resultado"),fecha));
			}
			s.close();
			this.cerrar();
			return gasto;
		} catch (SQLException e) {
			return null;
		}
	}
}
