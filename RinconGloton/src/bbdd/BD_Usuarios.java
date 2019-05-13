/**
 * 
 */
package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import modelos.BloqueadaException;
import modelos.Cliente;
import modelos.Empleados;
import modelos.PedidoProducto;
import modelos.Producto;
import modelos.Proveedores;
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

	public boolean añadirEmpleado(String nombreUsu, String nombre, String apellido, String correo, String contraseña) {
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

	public int comporbarCodProv(String cod_prov) {
		String cadena = "SELECT * FROM proveedores WHERE Cod_prov LIKE '" + cod_prov + "'";
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
			return -1;
		}
	}

	public int comporbarContraseñaEmpleados(String nombre, String contraseña) {
		String cadena = "SELECT * FROM empleados WHERE Cod_Emple LIKE '" + nombre + "' AND contraseña  LIKE '"
				+ contraseña + "'";
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

	// He puesto una ' al final de cada select
	public Vector<PedidoProducto> mostrarPedidosProductos() {
		Vector<PedidoProducto> v = new Vector<PedidoProducto>();
		String cadena = "SELECT * FROM pedidos_productos";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new PedidoProducto(reg.getString("ident_producto"), reg.getString("Cod_Prov"),
						reg.getString("Cod_emple"), reg.getDouble("Gastos"), reg.getDate("Fecha_encargo").toLocalDate(),
						reg.getInt("cantidad"), reg.getInt("Cod_Pedido")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}

	}

	// He puesto una ' al final de cada select
	public Vector<Proveedores> mostrarProveedores() {
		Vector<Proveedores> v = new Vector<Proveedores>();
		String cadena = "SELECT * FROM proveedores";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Proveedores(reg.getString("cod_prov"), reg.getString("nombre_prov"),
						reg.getString("apellido_prov"), reg.getString("direccion"), reg.getInt("telefono")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}

	}

	public Vector<Empleados> mostrarEmpleados() {
		Vector<Empleados> v = new Vector<Empleados>();
		String cadena = "SELECT * FROM empleados WHERE encargado LIKE '" + 1 + "'";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Empleados(reg.getString("Cod_Emple"), reg.getString("Nombre"), reg.getString("Apellido"),
						reg.getString("DNI"), reg.getString("Direccion"), reg.getInt("Telefono"),
						reg.getInt("Num_seguridad_social"), reg.getString("Cargo_en_la_empresa"),
						reg.getInt("encargado")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}

	}

	public Vector<Producto> mostrarProductos() {
		Vector<Producto> v = new Vector<Producto>();
		String cadena = "SELECT * FROM productos";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Producto(reg.getString("Ident_producto"), reg.getString("Cod_prov"),
						reg.getDate("Fecha_entrega").toLocalDate(), reg.getDate("Fecha_caducidad").toLocalDate(),
						reg.getInt("Coste"), reg.getString("Cod_emple")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}

	}

	public boolean borrarPedidoProducto(int cod) {
		String cadena = "DELETE  FROM pedidos_productos WHERE Cod_pedido ='" + cod + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			s.close();
			if (resul == 1) {
				return true;
			}
			this.cerrar();
			return false;
		} catch (SQLException e) {

			return false;

		}

	}

	public boolean borrarProveedor(String codProv) {
		String cadena = "DELETE FROM proveedores WHERE Cod_prov ='" + codProv + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			s.close();
			if (resul == 1) {
				return true;
			}
			this.cerrar();
			return false;
		} catch (SQLException e) {

			return false;

		}

	}

	public boolean añadirPedido_producto(String Ident_Producto, String Cod_Prov, String Cod_emple, Double Gastos,
			int cantidad) {
		String cadena = "INSERT INTO pedidos_productos (Ident_Producto,Cod_Prov,Cod_emple,Gastos,Fecha_encargo,cantidad) VALUES('"
				+ Ident_Producto + "','" + Cod_Prov + "','" + Cod_emple + "','" + Gastos + "','" + LocalDate.now()
				+ "','" + cantidad + "')";

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
	
	//para modificar nombre, apellido, direccion
	public int modificarProveedor(String dato, String variable2,String codigo) {
		String cadena = "UPDATE proveedores SET " +dato+ " = '" +variable2+ "' WHERE Cod_prov LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return 1;

		} catch (SQLException e) {
			this.cerrar();
			return 0;
		}
	}
	
	public int modificarProveedorTelefono( String codigo,int telefonoModificado) {
		String cadena = "UPDATE proveedores SET Telefono = '"+telefonoModificado+"' WHERE Cod_prov LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return 1;

		} catch (SQLException e) {
			this.cerrar();
			return 0;
		}
	}

	public boolean añadirProveedor(String cod_prov, String nombre, String apellido, String direccion, int telefono) {
		String cadena = "INSERT INTO proveedores  VALUES('" + cod_prov + "','" + nombre + "','" + apellido + "','"
				+ direccion + "','" + telefono + "')";

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
}
