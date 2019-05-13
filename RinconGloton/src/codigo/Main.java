package codigo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Vector;

import bbdd.BD_Conector;
import bbdd.BD_Usuarios;
import modelos.BloqueadaException;
import modelos.Cliente;
import modelos.Empleados;
import modelos.PedidoProducto;
import modelos.Producto;
import modelos.Proveedores;

public class Main {

	public static void main(String[] args) throws BloqueadaException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int opc = 0, opc2 = 0, opc3 = 0, opc4 = 0, repetido = 0, mod, eli = 0, pedido1, pedido2, pedido3, unidades,
				telefono, codigo2, telefonoModificado = 0, confir=0;
		double precio;
		String variable2 = null;
		boolean bloquear = false ,con;
		String tipo = null, nombre, contraseña, contraseña2, apellido = null, correo, nombreUsu, codigoEmple, confirmar,
				dir = null, codigo, tel = null, dato;
		BD_Conector.BD_Ini("rincón gloton");
		BD_Usuarios bd = new BD_Usuarios();
		System.out.println("welocome to the rincon gloton page");
		System.out.println("------------------------");
		do {
			System.out.println("¿que deseas hacer my friend?  \n1.darse de alta \n2.login \n3.salir");
			opc = sc.nextInt();
			switch (opc) {
			case 1:

				sc.nextLine();
				System.out.println("anota el nombre de usuario");
				nombreUsu = sc.nextLine();
				System.out.println("anote su nombre ");
				nombre = sc.nextLine();
				System.out.println("anote su apellido ");
				apellido = sc.nextLine();
				System.out.println("anota el correo electronico de contacto");
				correo = sc.nextLine();
				do {
					System.out.println("anota la contraseña");
					contraseña = sc.nextLine();
					System.out.println("repite la contraseña");
					contraseña2 = sc.nextLine();
					if (!contraseña2.equalsIgnoreCase(contraseña)) {
						System.out.println("no coinciden las contraseñas,vuelve a intentarlo");
					}
				} while (!contraseña2.equalsIgnoreCase(contraseña));
				bd.añadir(nombreUsu, nombre, apellido, correo, contraseña);
				break;
			case 2:
				repetido = 0;
				sc.nextLine();
				System.out.println("anota el nombre de usuario para entrar en la aplicacion");
				nombre = sc.nextLine();
				try {
					bd.mostrarbloq(nombre);
				} catch (BloqueadaException e) {
					System.out.println("cuenta bloqueada");
					break;
				}
				LocalTime primera = LocalTime.now();
				do {
					System.out.println("anota la contraseña");
					contraseña = sc.nextLine();
					if (bd.comporbarContraseñaclientes(nombre, contraseña) == 0
							&& bd.comporbarContraseñaUsuarios(nombre, contraseña) == 0
							&& bd.comporbarContraseñaEmpleados(nombre, contraseña) == 0) {
						System.out.println("contraseña  incorrecta");

						if (repetido == 4) {
							LocalTime ultima = LocalTime.now();
							long minutos = ChronoUnit.MINUTES.between(primera, ultima);
							if (minutos <= 30) {

								int pp = bd.bloquear(nombre);
								System.out.println(pp);
								System.out.println("tarjeta bloqueada");
								bloquear = true;
								break;
							}

						}
						repetido++;
					}
				} while (bd.comporbarContraseñaUsuarios(nombre, contraseña) == 0
						&& bd.comporbarContraseñaclientes(nombre, contraseña) == 0
						&& bd.comporbarContraseñaEmpleados(nombre, contraseña) == 0);

				if (bloquear == true) {
					break;
				}
				if (bd.comporbarContraseñaUsuarios(nombre, contraseña) == 1) {
					tipo = bd.login(nombre, contraseña);
					if (tipo.equalsIgnoreCase("administrador")) {
						System.out.println("1.adminitrar empleados ");
						System.out.println("2.administrar pedidos");
						System.out.println("3.administrar proveedores");
						System.out.println("4.listar gastos y ventas");
						int opc10 = sc.nextInt();
						switch (opc10) {
						case 1:
							System.out.println("1.dcaespedir empleado");
							System.out.println("2.modifir datos de un empleado");
							System.out.println("3.dar de alta un empleado");
							opc2 = sc.nextInt();
							switch (opc2) {
							case 1:
								
									System.out.println("Aqui te mostramos la lista de los empleados con los que contamos");
									Vector<Empleados> v8 = bd.mostrarEmpleados();
									if (v8.size() == 0) {
										System.out.println("en este momento no hay encargados para hecer los pedidos");
									}
									for (int i = 0; i < v8.size(); i++) {
										System.out.println(i + 1 + "-" + " El empleado con este codigo "
												+ v8.get(i).getCod_emple() + " con nombre " + v8.get(i).getNombre()
												+ " con apellido " + v8.get(i).getApellido() + " con dni "
												+ v8.get(i).getDNI() + " que vive en " + v8.get(i).getDireccion()
												+ " con el telefono " + v8.get(i).getTelefono()
												+ " que tiene este numero de seguridad social "
												+ v8.get(i).getNum_seguridadSocial() + ", en la empresa hace este trabajo "
												+ v8.get(i).getCargoEnLaEmpresa() + " y es encargado "
												+ v8.get(i).getEsEncargado());
									}
									System.out.println("¿que empleado deseas despedir?");
									 confir=sc.nextInt();
									 con = bd.borrarEmpleado(v8.get(confir-1).getCod_emple());
									if(con==true) {
										System.out.println("empleado eliminado");
								}
									else {
										System.out.println("se ha producido un error en la eliminacion del empleado");
									}
								break;
							case 2:
								System.out.println("Aqui te mostramos la lista de los empleados con los que contamos");
								Vector<Empleados> v9 = bd.mostrarEmpleados();
								if (v9.size() == 0) {
									System.out.println("en este momento no hay encargados para hecer los pedidos");
								}
								for (int i = 0; i < v9.size(); i++) {
									System.out.println(i + 1 + "-" + " El empleado con este codigo "
											+ v9.get(i).getCod_emple() + " con nombre " + v9.get(i).getNombre()
											+ " con apellido " + v9.get(i).getApellido() + " con dni "
											+ v9.get(i).getDNI() + " que vive en " + v9.get(i).getDireccion()
											+ " con el telefono " + v9.get(i).getTelefono()
											+ " que tiene este numero de seguridad social "
											+ v9.get(i).getNum_seguridadSocial() + ", en la empresa hace este trabajo "
											+ v9.get(i).getCargoEnLaEmpresa() + " y es encargado "
											+ v9.get(i).getEsEncargado());
								}
								System.out.println("indica que trabajador deseas modificar");
								confir=sc.nextInt();
								
								System.out.println("¿que deseas modificar 1.direccion 2.telefono?");
								mod = sc.nextInt();
							
								sc.nextLine();
								if (mod == 1) {
									System.out.println("anota la direccion");
									dato="Direccion";
									dir = sc.nextLine();
									 con = bd.modificarEmpleados(dato, dir, v9.get(confir-1).getCod_emple());
									 if (con==true) {
										 System.out.println("modificacion realizada");
									 }
									 else {
										 System.out.println("se ha producido un error en la modificacion");
									 }
								}
								if (mod == 2) {
									System.out.println("anota el telefono");
									dato="Telefono";
									telefono = sc.nextInt();
									con = bd.modificarTelefonoEMpleados(dato, telefono, v9.get(confir-1).getCod_emple());
									 if (con==true) {
										 System.out.println("modificacion realizada");
									 }
									 else {
										 System.out.println("se ha producido un error en la modificacion");
									 }
								}
								if (mod != 1 && mod != 2) {
									System.out.println("opcion erronea");
								}
								break;
							case 3:
								System.out.println("introduce el codigo de empleado del trabajador");
								System.out.println("anota el nombre del trabajador");
								System.out.println("anota el apellido del trabajador");
								System.out.println("anota el dni del trabajador");
								System.out.println("anota la direccion del trabajador");
								System.out.println("introduce el telefono del trabajador");
								System.out.println("introduce el numero de la seguridad social del emlpeado");
								System.out.println("introduce el cargo en la empresa del empleado ");
								// hacer el insert del trbajador
								break;
							}
						case 2:
							System.out.println(
									"¿que deseas hacer con los pedidos? \n1.listar pedidos \n2.eliminar pedidos \n3.añadir pedido");
							opc3 = sc.nextInt();
							switch (opc3) {
							case 1:
								Vector<PedidoProducto> v4 = bd.mostrarPedidosProductos();
								if (v4.size() == 0) {
									System.out.println("no hay pedidos en este momento");
									break;
								}
								for (int i = 0; i < v4.size(); i++) {
									System.out.println(i + 1 + "-" + " el pedido con codigo: "
											+ v4.get(i).getCod_pedido() + " fue creado por el empleado con codigo: "
											+ v4.get(i).getCod_emple() + " ha pedido al proveedor: "
											+ v4.get(i).getCod_proveedor() + " el prodcuto: "
											+ v4.get(i).getIdent_producto() + " el dia: " + v4.get(i).getFecha_encargo()
											+ " la cantidad de: " + v4.get(i).getCantidad()
											+ ". Este pedido tiene un coste de: " + v4.get(i).getGastos() + "€");
								}

								break;
							case 2:
								Vector<PedidoProducto> v5 = bd.mostrarPedidosProductos();
								if (v5.size() == 0) {
									System.out.println("no hay pedidos para eliminar en este momento");
									break;
								}
								for (int i = 0; i < v5.size(); i++) {
									System.out.println(i + 1 + "-" + " el pedido con codigo: "
											+ v5.get(i).getCod_pedido() + " fue creado por el empleado con codigo: "
											+ v5.get(i).getCod_emple() + " ha pedido al proveedor: "
											+ v5.get(i).getCod_proveedor() + " el prodcuto: "
											+ v5.get(i).getIdent_producto() + " el dia: " + v5.get(i).getFecha_encargo()
											+ " la cantidad de: " + v5.get(i).getCantidad()
											+ ". Este pedido tiene un coste de: " + v5.get(i).getGastos() + "€");
								}
								System.out.println("¿que pedido deseas eliminar?");
								eli = sc.nextInt();

								 con = bd.borrarPedidoProducto(v5.get(eli - 1).getCod_pedido());
								if (con == true) {
									System.out.println("pedido eliminado");
								} else {
									System.out.println("no se pudo eliminar el pedido");
								}
								break;
							case 3:

								/*
								 * Vector<Proveedores> v7= bd.mostrarProveedores(); Vector<Empleados> v8=
								 * bd.mostrarEmpleados();
								 */

								System.out.println("Aqui puedes ver el listado de los productos");
								Vector<Producto> v6 = bd.mostrarProductos();
								if (v6.size() == 0) {
									System.out.println("en este momento no hay productos para listar");
								}
								for (int i = 0; i < v6.size(); i++) {
									System.out.println(i + 1 + "-" + " El producto de identificador "
											+ v6.get(i).getIdent_producto() + " que proviene del proveedor "
											+ v6.get(i).getCod_prov() + " con una fecha de entrega "
											+ v6.get(i).getFecha_entrega() + " con una fecha de caducidad "
											+ v6.get(i).getFecha_caducidad() + " que tiene un coste de "
											+ v6.get(i).getCoste() + " €, y pertenece al empleado con este codigo : "
											+ v6.get(i).getCod_emple());
								}

								System.out.println("¿de que podructo deseas crear el pedido?");
								pedido1 = sc.nextInt();
								System.out.println("Aqui te mostramos el listado de los proveedores");
								Vector<Proveedores> v7 = bd.mostrarProveedores();
								if (v7.size() == 0) {
									System.out.println("en este momento no hay proveedores para solicitar pedidos");
								}
								for (int i = 0; i < v7.size(); i++) {
									System.out.println(i + 1 + "-" + " El proveedor el codigo "
											+ v7.get(i).getCod_prov() + " con nombre " + v7.get(i).getNombre_prov()
											+ " con apellido " + v7.get(i).getApellido_prov() + " de la direccion "
											+ v7.get(i).getDireccion() + " y con este telefono "
											+ v7.get(i).getTelefono());
								}
								System.out.println("¿a que proveedor le quieres hacer el pedido?");
								pedido2 = sc.nextInt();
								System.out.println("Aqui te mostramos la lista de los empleados con los que contamos");
								Vector<Empleados> v8 = bd.mostrarEmpleados();
								if (v8.size() == 0) {
									System.out.println("en este momento no hay encargados para hecer los pedidos");
								}
								for (int i = 0; i < v8.size(); i++) {
									System.out.println(i + 1 + "-" + " El empleado con este codigo "
											+ v8.get(i).getCod_emple() + " con nombre " + v8.get(i).getNombre()
											+ " con apellido " + v8.get(i).getApellido() + " con dni "
											+ v8.get(i).getDNI() + " que vive en " + v8.get(i).getDireccion()
											+ " con el telefono " + v8.get(i).getTelefono()
											+ " que tiene este numero de seguridad social "
											+ v8.get(i).getNum_seguridadSocial() + ", en la empresa hace este trabajo "
											+ v8.get(i).getCargoEnLaEmpresa() + " y es encargado "
											+ v8.get(i).getEsEncargado());
								}
								System.out.println("¿que encargado va ha hacer el pedido?");
								pedido3 = sc.nextInt();
								System.out.println("¿cuantas unidades deseas pedir?");
								unidades = sc.nextInt();
								System.out.println("¿cual es el precio por unidad?");
								precio = sc.nextInt();
								bd.añadirPedido_producto(v6.get(pedido1 - 1).getIdent_producto(),
										v7.get(pedido2 - 1).getCod_prov(), v8.get(pedido3 - 1).getCod_emple(),
										(unidades * precio), unidades);
								break;

							
							}
							break;
						case 3:

							System.out.println("1.Eliminar los datos del proveedor");
							System.out.println("2.Modificar datos del proveedor");
							System.out.println("3.Añadir un proveedor");
							System.out.println("4.listar proveedores");
							opc4 = sc.nextInt();

							switch (opc4) {
							case 1:
								System.out.println("Cual es el proveedor del cual desea eliminar sus datos");
								System.out.println("Aqui te mostramos el listado de los proveedores");
								Vector<Proveedores> v7 = bd.mostrarProveedores();
								for (int i = 0; i < v7.size(); i++) {
									System.out.println(i + 1 + "-" + " El proveedor con el codigo "
											+ v7.get(i).getCod_prov() + " con nombre " + v7.get(i).getNombre_prov()
											+ " con apellido " + v7.get(i).getApellido_prov() + " de la direccion "
											+ v7.get(i).getDireccion() + " y con este telefono "
											+ v7.get(i).getTelefono());
								}
								System.out.println("que proveedor deseas eliminar");
								int jaja = sc.nextInt();
								 con = bd.borrarProveedor(v7.get(jaja - 1).getCod_prov());
								if (con == true) {
									System.out.println("proveedor eliminado");
								} else {
									System.out.println(
											"no se puede eliminar el proveedor puesto que tienes pedidos con el,primero elimina los pedidos");
									System.out.println(
											"si no existe pedidos con este proveedor y sigue dando error,contacte con nosotros");
								}
								break;
							case 2:
								sc.nextLine();
								do {
									System.out.println("anota el codigo de proveedor");
									codigo = sc.nextLine();
									codigo2 = bd.comporbarCodProv(codigo);
									if (codigo2 == 0) {
										System.out.println("el codigo de proveedor no existe");
									}
								} while (codigo2 == 0);
								if (codigo2 == -1) {
									System.out.println("se ha producido un error");
									break;
								}
								if (codigo2 == 1) {
								System.out.println("Que deseas modificar del proveedor");
								System.out.println("1.Modificar el nombre");
								System.out.println("2.Modificar el apellido");
								System.out.println("3.Modificar la direccion");
								System.out.println("4.Modificar el telefono");
								int opcModifica = sc.nextInt();
								if (opcModifica == 1) {
									sc.nextLine();
									dato = "nombre_prov";
									System.out.println("anota el nuevo nombre");
									variable2=sc.nextLine();
									bd.modificarProveedor(dato, variable2,codigo);
									if(bd.modificarProveedor(dato, variable2,codigo) == 1) {
										System.out.println("El proveedor se ha modificado con exito");
									}else {
										System.out.println("No se ha podido modificar el proveedor, intentelo mas tarde");
									}
								}
								if (opcModifica == 2) {
									sc.nextLine();
									System.out.println("Introduce el nuevo apellido del proveedor");
									variable2=sc.nextLine();
									dato = "Apellido_prov";
									bd.modificarProveedor(dato, variable2,codigo);
									if(bd.modificarProveedor(dato, variable2,codigo) == 1) {
										System.out.println("El proveedor se ha modificado con exito");
									}else {
										System.out.println("No se ha podido modificar el proveedor, intentelo mas tarde");
									}

								}
								if (opcModifica == 3) {
									sc.nextLine();
									System.out.println("Introduce la nueva direccion del proveedor");
									variable2=sc.nextLine();
									dato = "Direccion";
									bd.modificarProveedor(dato, variable2,codigo);
									if(bd.modificarProveedor(dato, variable2,codigo) == 1) {
										System.out.println("El proveedor se ha modificado con exito");
									}else {
										System.out.println("No se ha podido modificar el proveedor, intentelo mas tarde");
									}

								}
								if (opcModifica == 4) {
									System.out.println("Introduce el nuevo telefono del proveedor");
									telefonoModificado=sc.nextInt();								
									bd.modificarProveedorTelefono(codigo,telefonoModificado);
									if(bd.modificarProveedorTelefono(codigo,telefonoModificado) == 1) {
										System.out.println("El proveedor se ha modificado con exito");
									}else {
										System.out.println("No se ha podido modificar el proveedor, intentelo mas tarde");
									}
								}
								}
								break;
							case 3:
								sc.nextLine();
								do {
									System.out.println("anota el codigo de proveedor");
									codigo = sc.nextLine();
									codigo2 = bd.comporbarCodProv(codigo);
									if (codigo2 == 1) {
										System.out.println("el codigo de proveedor ya existe");
									}
								} while (codigo2 == 1);
								if (codigo2 == -1) {
									System.out.println("se ha producido un error");
								}
								if (codigo2 == 0) {
									System.out.println("anota el nombre del contacto del proveedor");
									nombre = sc.nextLine();
									System.out.println("anota el apellido del contacto del proveedor");
									apellido = sc.nextLine();
									System.out.println("anota la direccion del proveedor");
									dir = sc.nextLine();
									System.out.println("anota el telefono del proveedor");
									telefono = sc.nextInt();
									 con = bd.añadirProveedor(codigo, nombre, apellido, dir, telefono);

									if (con == true) {
										System.out.println("proveedor introducido");

									}
									if (con == false) {
										System.out.println("se ha producido un error al crear el proveedor");
									}
								}
								break;
							case 4:
								Vector<Proveedores> v8 = bd.mostrarProveedores();
								for (int i = 0; i < v8.size(); i++) {
									System.out.println(i + 1 + "-" + "El proveedor con el codigo "
											+ v8.get(i).getCod_prov() + " con nombre " + v8.get(i).getNombre_prov()
											+ " con apellido " + v8.get(i).getApellido_prov() + " de la direccion "
											+ v8.get(i).getDireccion() + " y con este telefono "
											+ v8.get(i).getTelefono());
								}
								break;

							}

							break;
						case 4:
							break;
						}
					}

					if (tipo.equalsIgnoreCase("encargado")) {
						System.out.println("realizar pedido de productos");
						System.out.println("revisar stock de productos");
						System.out.println("");
						System.out.println("recoger orden");
						System.out.println("listar ordenes en proceso");
						System.out.println("entregar orden");

					}
				}
				if (bd.comporbarContraseñaEmpleados(nombre, contraseña) == 1) {

					System.out.println("recoger orden");
					System.out.println("listar ordenes en proceso");
					System.out.println("entregar orden");
				}

				if (bd.comporbarContraseñaclientes(nombre, contraseña) == 1) {
					System.out.println("aministrar cuenta");
					System.out.println("crear un orden");
					System.out.println("ver orden");
					System.out.println("cancelar orden");
				}

				break;

			case 3:
				System.out.println("hasta luegi");
				break;
			}
		} while (opc != 3);

	}

}
