/**
 * 
 */
package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.BloqueadaException;
import modelos.Cliente;
import modelos.Usuarios;

/**
 * @author j.caballero
 *
 */
public class BD_Usuarios extends BD_Conector {

	private static Statement s;
	private static ResultSet reg;

	public BD_Usuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String login(String nombre, String contraseña) {
		String cadena = "SELECT Oficio FROM usuarios WHERE Login LIKE '" + nombre + "' AND Password LIKE '" + contraseña
				+ "'";
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

	public boolean añadir(String nombreUsu, String nombre, String apellido, String correo, String contraseña) {
		String cadena = "INSERT INTO cliente VALUES('" + nombreUsu + "','" + nombre + "','" + apellido + "','" + correo
				+ "','" + contraseña + "','" + 0 + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			if (resul == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}

	}

	public int comporbarContraseñaclientes(String nombre, String contraseña) {
		int i = 0;
		String cadena = "SELECT * FROM cliente WHERE Ident_usuario LIKE '" + nombre + "' AND Contraseña LIKE '"
				+ contraseña + "'";
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				i++;
			}
			s.close();
			this.cerrar();
			return i;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int comporbarContraseñaUsuarios(String nombre, String contraseña) {
		String cadena = "SELECT * FROM usuarios WHERE Login LIKE '" + nombre + "' AND Password LIKE '" + contraseña
				+ "'";
		int i = 0;
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				i++;
			}
			s.close();
			this.cerrar();
			return i;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int mostrarbloq(String nombre) throws BloqueadaException {
		int pp = 0;
		String cadena = "SELECT bloqueada FROM cliente WHERE Ident_usuario LIKE '" + nombre + "';";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				if (reg.getInt("bloqueada") == 1) {
					throw new BloqueadaException("no se puede retirar dinero por que la tarjeta esta bloqueada");

				}
			}
			s.close();
			this.cerrar();
			return pp;
		} catch (SQLException e) {
			return 0;

		}
	}

	public int bloquear(String nombre) {
		String cadena = "UPDATE cliente SET bloqueada =" + 1 + " WHERE Ident_usuario LIKE '" + nombre + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
}
