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
import modelos.Carta;
import modelos.Empleados;
import modelos.Gastos;
import modelos.Orden;
import modelos.PedidoProducto;
import modelos.Producto;
import modelos.Proveedores;
import modelos.Ventas;

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
/**
 * funcion para comprobar que los datos del login sean correctos y para logearse
 * @param nombre
 * @param contraseña
 * @return String para comporbar el login
 */

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
/**
 * funcion para añadir un  nuevo cliente
 * @param nombreUsu
 * @param nombre
 * @param apellido
 * @param correo
 * @param contraseña
 * @return int para comporbar que se ha insertado la linea
 */
	public int añadir(String nombreUsu, String nombre, String apellido, String correo, String contraseña) {
		String cadena = "INSERT INTO cliente VALUES('" + nombreUsu + "','" + nombre + "','" + apellido + "','" + correo
				+ "','" + contraseña + "','" + 0 + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
/**
 * funcion para comporbar que se ha insertado el empleado
 * @param nombreUsu
 * @param nombre
 * @param apellido
 * @param correo
 * @param contraseña
 * @return int para comporbar que se ha insertado la linea
 */
	public int añadirEmpleado(String nombreUsu, String nombre, String apellido, String correo, String contraseña) {
		String cadena = "INSERT INTO cliente VALUES('" + nombreUsu + "','" + nombre + "','" + apellido + "','" + correo
				+ "','" + contraseña + "','" + 0 + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
/**
 * funcion que comprueba que el usuario y una contraseña correspondan correctamente a un empleado
 * @param nombre
 * @param contraseña
 * @return int para comprobar que se ha obtenido algun valor de la select
 */
	public int comporbarContraseñaclientes(String nombre, String contraseña) {
		
		String cadena = "SELECT * FROM cliente WHERE Ident_usuario LIKE '" + nombre + "' AND Contraseña LIKE '"
				+ contraseña + "'";
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
/**
 * funcion para  comporbar que el usuario y la contraseña introducidad corresponden a un usuario 
 * @param nombre
 * @param contraseña
 * @return int para comprobar que se ha obtenido algun valor de la select
 */
	public int comporbarContraseñaUsuarios(String nombre, String contraseña) {
		String cadena = "SELECT * FROM usuarios WHERE Login LIKE '" + nombre + "' AND Password LIKE '" + contraseña
				+ "'";
		
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
	/**
	 * metodo para comporbar si un empleado es un cocinero
	 * @param nombre
	 * @param contraseña
	 * @return int para comprobar que la select devuelve un dato
	 */
	public int comporbarCocinero(String nombre, String contraseña) {
		String cargo="cocinero";
		String cadena = "SELECT * FROM empleados WHERE Cod_Emple LIKE '" + nombre + "' AND Cargo_en_la_empresa LIKE '" + cargo + "'";
		
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
/**
 * funcion para comprobar que un numero de proveedor es correcto
 * @param cod_prov
 * @return int para comrpobar que la select devuelve algun dato
 */
	public int comporbarCodProv(String cod_prov) {
		String cadena = "SELECT * FROM proveedores WHERE Cod_prov LIKE '" + cod_prov + "'";
		
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
/**
 * funion para comprobar que la contraseña y el usuario corresponde a un empleado
 * @param nombre
 * @param contraseña
 * @return int para comprobar que el select ha devuelve un valor
 */
	public int comporbarContraseñaEmpleados(String nombre, String contraseña) {
		String cadena = "SELECT * FROM empleados WHERE Cod_Emple LIKE '" + nombre + "' AND contraseña  LIKE '"
				+ contraseña +"' AND encargado LIKE '"
						+ 0 + "'";
		
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
	/**
	 * funcion para comprobar que el usuario es un encargado
	 * @param nombre
	 * @param contraseña
	 * @return int para comporbar que la select devuelve algun valor
	 */
	public int comporbarContraseñaEncargado(String nombre, String contraseña) {
		String cadena = "SELECT * FROM empleados WHERE Cod_Emple LIKE '" + nombre + "' AND contraseña  LIKE '"
				+ contraseña + "' AND encargado LIKE '"
						+ 1 + "'";
		
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next()) {
				t = reg.getString(1);
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			return -1;
		}
	}
/**
 * funcion para comprobar que la cuenta este bloqueada
 * @param nombre
 * @return int para comporbar que la select devuelve algun valor
 * @throws BloqueadaException
 */
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
/**
 * funcion para bloquear una cuenta por exceso de fallos al introducir la contraseña
 * @param nombre
 * @return int para comprobar que se ha ejecutado el update
 */
	public int bloquear(String nombre) {
		String cadena = "UPDATE cliente SET bloqueada =" + 1 + " WHERE Ident_usuario LIKE '" + nombre + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return 1;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}

	/**
	 * funcion que devuelve los pedidos de los productos de la base de datos
	 * @return Vector con la ionformacion de los pedidos de los productos
	 */
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

	/**
	 * funcion que devuelve los proveedores de la base de datos
	 * @return Vector con la informacion de los proveedores de la base de datos
	 */
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
/**
 * funcion que devuelve la informacion sobre los encargados 
 * @return Vector con los datos de los encargados
 */
	public Vector<Empleados> mostrarEnacrgados() {
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
	/**
	 * funcion que muestra la informacion de los empleados
	 * @return Vector con los datos de los empleados de la base de datos
	 */
	public Vector<Empleados> mostrarEmpleados() {
		Vector<Empleados> v = new Vector<Empleados>();
		String cadena = "SELECT * FROM empleados";
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
/**´
 * funcion que muestra los productos que estan disponibles
 * @return Vector con los datos de los productos
 */
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
						reg.getDouble("Coste"), reg.getString("Cod_emple"),reg.getInt("cantidad")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}

	}
	/**
	 * funcion que muestra el stock disponible de nuestros productos
	 * @return Vector que guarda los datos del stock de los productos
	 */
	public Vector<Producto> mostrarStock() {
		Vector<Producto> v = new Vector<Producto>();
		String cadena = "SELECT * FROM productos";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Producto(reg.getString("Ident_producto"), 
						reg.getInt("cantidad")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}
	}
	/**
	 * funcion que muestra la carta disponible de la aplicacion
	 * @return Vector con la carta disponible en el momento y todos sus datos
	 */
	public Vector<Carta> mostrarCarta() {
		Vector<Carta> v = new Vector<Carta>();
		String cadena = "SELECT * FROM carta";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Carta(reg.getString("nombre_producto"),reg.getDouble("precio_unidad")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}
	}
/**
 * funcion para eliminar pedidos de productos
 * @param cod
 * @return int para comporbar que se ha realizado el delete
 */
	public int borrarPedidoProducto(int cod) {
		String cadena = "DELETE  FROM pedidos_productos WHERE Cod_pedido ='" + cod + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {

			return -1;

		}

	}
/**
 * funcion para eliminar un proveedor
 * @param codProv
 * @return int para comprobar que se ha eliminado el proveedor
 */
	public int borrarProveedor(String codProv) {
		String cadena = "DELETE FROM proveedores WHERE Cod_prov ='" + codProv + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {

			return -1;

		}

	}
/**
 * funcion para añadir un pedido de un producto
 * @param Ident_Producto
 * @param Cod_Prov
 * @param Cod_emple
 * @param Gastos
 * @param cantidad
 * @return int para comporbar que se ha insertado el pedido del producto
 */
	public int añadirPedido_producto(String Ident_Producto, String Cod_Prov, String Cod_emple, Double Gastos,
			int cantidad) {
		String cadena = "INSERT INTO pedidos_productos (Ident_Producto,Cod_Prov,Cod_emple,Gastos,Fecha_encargo,cantidad) VALUES('"
				+ Ident_Producto + "','" + Cod_Prov + "','" + Cod_emple + "','" + Gastos + "','" + LocalDate.now()
				+ "','" + cantidad + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
	
	/**
	 * funcion para modificar la informacion de los proveedores
	 * @param dato
	 * @param variable2
	 * @param codigo
	 * @return int para comporbar que se ha ejecutado con exito el update
	 */
	public int modificarProveedor(String dato, String variable2,String codigo) {
		String cadena = "UPDATE proveedores SET " +dato+ " = '" +variable2+ "' WHERE Cod_prov LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**
	 * funcion para modificar la informacion de lo empleados
	 * @param dato
	 * @param variable2
	 * @param codigo
	 * @return int para comporbar que se ha ejecutado con exito el update
	 */
	public int modificarEmpleados(String dato, String variable2,String codigo) {
		String cadena = "UPDATE empleados SET " +dato+ " = '" +variable2+ "' WHERE Cod_Emple LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			return 0;
			

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**´
	 * funcion para modificar el telefono de los empleados
	 * @param dato
	 * @param variable2
	 * @param codigo
	 * @return int para comporbar que se ha ejecutado con exito el update
	 */
	public int modificarTelefonoEMpleados(String dato, int variable2,String codigo) {
		String cadena = "UPDATE empleados SET " +dato+ " = '" +variable2+ "' WHERE Cod_Emple LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			return 0;
			

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**
	 * funcion para modificar el telefono de los proveedores
	 * @param codigo
	 * @param telefonoModificado
	 * @return int para comporbar que se ha ejecutado con exito el update
	 */
	public int modificarProveedorTelefono( String codigo,int telefonoModificado) {
		String cadena = "UPDATE proveedores SET Telefono = '"+telefonoModificado+"' WHERE Cod_prov LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul =s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**
	 * funcion para añadir un empleado a la base de datos
	 * @param cod_emp
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param direccion
	 * @param telefono
	 * @param numSegSocial
	 * @param cargo
	 * @param contraseña
	 * @return int para comporbar que se ha insertado el emplado correctamente
	 */
	public int añadirEmpleado(String cod_emp, String nombre, String apellido,String dni, String direccion, int telefono,int numSegSocial,String cargo,String contraseña) {
		String cadena = "INSERT INTO  empleados VALUES('" + cod_emp + "','" + nombre + "','" + apellido + "','"+ dni + "','" + direccion + "','" + telefono + "','" + numSegSocial + "','" + cargo +"','" + contraseña +"','" + 0 + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
	/**
	 * funcion para añadir un proveedor a la base de datos
	 * @param cod_prov
	 * @param nombre
	 * @param apellido
	 * @param direccion
	 * @param telefono
	 * @return int para comprobar que se ha añadido el proveedor
	 */
	public int añadirProveedor(String cod_prov, String nombre, String apellido, String direccion, int telefono) {
		String cadena = "INSERT INTO proveedores  VALUES('" + cod_prov + "','" + nombre + "','" + apellido + "','"
				+ direccion + "','" + telefono + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
	/**
	 * funcion para eliminar un empleado de la base de datos
	 * @param cod_Emp
	 * @return int para comporbar que se ha eliminado el empleado
	 */
	public int borrarEmpleado(String cod_Emp) {
		String cadena = "DELETE FROM empleados WHERE Cod_Emple  ='" + cod_Emp + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {

			return -1;

		}

	}
	/**´
	 * funcion para añadir una orden a la base de datos
	 * @param cod_empleado
	 * @param precio
	 * @param direccion
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @return int para comporbar que se ha añadido la orden
	 */
	public int añadirOrden(String cod_empleado,double precio,String direccion,int p1,int p2,int p3,int p4,int p5) {
		
		String cadena = "INSERT INTO orden (Cod_emple,Precio,Direccion,producto1,producto2,producto3,producto4,producto5) VALUES('"
				+ cod_empleado + "','" + precio + "','" + direccion + "','" + p1 + "','" + p2
				+ "','" + p3 + "','" +p4+ "','" +p5+ "')";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			this.cerrar();
			return 0;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}
	/**
	 * funcion para mostrar las ordenes de la base de datos
	 * @return Vector con la informacion de las ordenes
	 */
	public Vector<Orden> mostrarOrden() {
		Vector<Orden> v = new Vector<Orden>();
		String cadena = "SELECT * FROM orden";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Orden(reg.getString("Cod_orden"),reg.getString("Cod_emple"),reg.getDouble("Precio"),reg.getString("Direccion"),reg.getInt("producto1"),reg.getInt("producto2"),reg.getInt("producto3"),reg.getInt("producto4"),reg.getInt("producto5")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}
	}
	/**
	 * funcion para modificar la informacion de los clientes
	 * @param dato
	 * @param variable2
	 * @param codigo
	 * @return int para comprobar que se ha realizado el update 
	 */
	public int modificarCliente(String dato, String variable2,String codigo) {
		
		String cadena = "UPDATE cliente SET " +dato+ " = '" +variable2+ "' WHERE Ident_usuario LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 0;
			}
			s.close();
			return 1;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**
	 * funcion para desbloquear la cuenta de un cliente
	 * @param codigo
	 * @return int para comporbar que se ha ejecutado el update
	 */
	public int desbloquearCliente(String codigo) {
		
		String cadena = "UPDATE cliente SET bloqueada  = '" +0+ "' WHERE Ident_usuario LIKE '" +codigo+ "'";

		try {
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			
			if (resul == 1) {
				return 1;
			}
			s.close();
			return 0;
			
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	/**
	 * funcion para eliminar una orden de la base de datos
	 * @param cod_Emp
	 * @return int para comporbar que se ha ejecutado la orden
	 */
	public int borrarOrden(String cod_Emp) {
		String cadena = "DELETE FROM orden WHERE Cod_orden ='" + cod_Emp + "'";

		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			int resul = s.executeUpdate(cadena);
			this.cerrar();
			if (resul == 1) {
				return 1;
			}
			s.close();
			return 0;
		} catch (SQLException e) {

			return -1;

		}

	}
	/**
	 * funcion para añadir un gasto a la base de datos
	 * @param descripcion
	 * @param importe
	 * @return int para comprobar que se ha realizado el insert
	 */
	public int añadirGasto(String descripcion,double importe ){
		String cadena="INSERT INTO gastos (descripcion_gasto,importe) VALUES('"+descripcion+ "','"+importe+"')"; 	
		
		try{
		this.abrir();
		s=c.createStatement();
		int resul =s.executeUpdate(cadena);
		if (resul == 1) {
			return 1;
		}
		s.close();
		this.cerrar();
		return 0;
		
		}catch ( SQLException e){
			this.cerrar();
			return -1;
		}
		
}
/**
 * funcion para obtener la informacion de los gastos de la base de datos
 * @return Vector con la informacion de los gastos
 */
	public Vector<Gastos> mostrarGastos() {
		Vector<Gastos> v = new Vector<Gastos>();
		String cadena = "SELECT * FROM gastos";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Gastos(reg.getDate("fecha_gastos").toLocalDate(),reg.getString("descripcion_gasto"),reg.getInt("cod_gasto"),reg.getDouble("importe")));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}
	}
	/**
	 * funcion que muestra la informacion de las ventas de la base de datos
	 * @return Vector con los datos de las ventas
	 */
	public Vector<Ventas> mostrarVentas() {
		Vector<Ventas> v = new Vector<Ventas>();
		String cadena = "SELECT * FROM Ventas";
		try {

			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				v.add(new Ventas(reg.getDouble("beneficios"),reg.getInt("cod_orden"),reg.getInt("Cod_venta"),reg.getDate("fecha_venta").toLocalDate()));
			}

			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;

		}
	}
}

