package codigo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Vector;

import bbdd.BD_Conector;
import bbdd.BD_Usuarios;
import modelos.BloqueadaException;
import modelos.Carta;
import modelos.Empleados;
import modelos.Gastos;
import modelos.Orden;
import modelos.PedidoProducto;
import modelos.Producto;
import modelos.Proveedores;
import modelos.Ventas;

public class Main {

	public static void main(String[] args) throws BloqueadaException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int v[] = new int[5];
		int opc = 0, opc2 = 0, opc3 = 0, opc4 = 0, repetido = 0, mod, eli = 0, pedido1, pedido2, pedido3, unidades,
				telefono, codigo2, telefonoModificado = 0, confir = 0, numSegSocial, num = 0, pro1, pro2, pro3, pro4,
				pro5, num1, confirmar2, opcCoc, opc53, opcG;
		double precio = 0, importe;
		String variable2 = null;
		boolean bloquear = false, con, seguir = false;
		String tipo = null, nombre, contraseña, contraseña2, apellido = null, correo, nombreUsu, codigoEmple, confirmar,
				dir = null, codigo, tel = null, dato, dni, cargo, identProducto, codEmple, nomProv, descripcion;
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

				int confirmar3 = bd.añadir(nombreUsu, nombre, apellido, correo, contraseña);
				if (confirmar3 == 1) {
					System.out.println("cliente añadido con exito");
				}
				if (confirmar3 == 0) {
					System.out.println("se ha producido un erro en la base de datos");
				}
				if (confirmar3 == -1) {
					System.out.println("se ha producido un error de sql");
				}
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
					bd.comporbarContraseñaEncargado(nombre, contraseña);
					if (bd.comporbarContraseñaclientes(nombre, contraseña) == 0
							&& bd.comporbarContraseñaUsuarios(nombre, contraseña) == 0
							&& bd.comporbarContraseñaEmpleados(nombre, contraseña) == 0
							&& bd.comporbarContraseñaEncargado(nombre, contraseña) == 0) {
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
						&& bd.comporbarContraseñaEmpleados(nombre, contraseña) == 0
						&& bd.comporbarContraseñaEncargado(nombre, contraseña) == 0);

				if (bloquear == true) {
					break;
				}
				if (bd.comporbarContraseñaUsuarios(nombre, contraseña) == 1) {
					tipo = bd.login(nombre, contraseña);
					if (tipo.equalsIgnoreCase("administrador")) {
						do {
							System.out.println("1.adminitrar empleados ");
							System.out.println("2.administrar pedidos");
							System.out.println("3.administrar proveedores");
							System.out.println("4.administrar gastos y ventas");
							int opc10 = sc.nextInt();
							switch (opc10) {
							case 1:
								System.out.println("1.dcaespedir empleado");
								System.out.println("2.modificar datos de un empleado");
								System.out.println("3.dar de alta un empleado");
								opc2 = sc.nextInt();
								switch (opc2) {
								case 1:

									System.out.println(
											"Aqui te mostramos la lista de los empleados con los que contamos");
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
												+ v8.get(i).getNum_seguridadSocial()
												+ ", en la empresa hace este trabajo " + v8.get(i).getCargoEnLaEmpresa()
												+ " y es encargado " + v8.get(i).getEsEncargado());
									}
									System.out.println("¿que empleado deseas despedir?");
									confir = sc.nextInt();
									int confirmar4 = bd.borrarEmpleado(v8.get(confir - 1).getCod_emple());
									if (confirmar4 == 1) {
										System.out.println("empleado borrado con exito");
									}
									if (confirmar4 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar4 == -1) {
										System.out.println("se ha producido un error de sql");
									}
									break;
								case 2:
									System.out.println(
											"Aqui te mostramos la lista de los empleados con los que contamos");
									Vector<Empleados> v9 = bd.mostrarEmpleados();
									if (v9.size() == 0) {
										System.out.println("en este momento no hay empleados que modificar");
									}
									for (int i = 0; i < v9.size(); i++) {
										System.out.println(i + 1 + "-" + " El empleado con este codigo "
												+ v9.get(i).getCod_emple() + " con nombre " + v9.get(i).getNombre()
												+ " con apellido " + v9.get(i).getApellido() + " con dni "
												+ v9.get(i).getDNI() + " que vive en " + v9.get(i).getDireccion()
												+ " con el telefono " + v9.get(i).getTelefono()
												+ " que tiene este numero de seguridad social "
												+ v9.get(i).getNum_seguridadSocial()
												+ ", en la empresa hace este trabajo " + v9.get(i).getCargoEnLaEmpresa()
												+ " y es encargado " + v9.get(i).getEsEncargado());
									}
									System.out.println("indica que trabajador deseas modificar");
									confir = sc.nextInt();

									System.out.println("¿que deseas modificar 1.direccion 2.telefono?");
									mod = sc.nextInt();

									sc.nextLine();
									if (mod == 1) {
										System.out.println("anota la direccion");
										dato = "Direccion";
										dir = sc.nextLine();
										int confirmar5 = bd.modificarEmpleados(dato, dir,
												v9.get(confir - 1).getCod_emple());
										if (confirmar5 == 1) {
											System.out.println("direccion modificado con exito");
										}
										if (confirmar5 == 0) {
											System.out.println("se ha producido un error en la base de datos");
										}
										if (confirmar5 == -1) {
											System.out.println("se ha producido un error de sql");
										}
									}
									if (mod == 2) {
										System.out.println("anota el telefono");
										dato = "Telefono";
										telefono = sc.nextInt();
										int confirmar6 = bd.modificarTelefonoEMpleados(dato, telefono,
												v9.get(confir - 1).getCod_emple());
										if (confirmar6 == 1) {
											System.out.println("telefono modificado con exito");
										}
										if (confirmar6 == 0) {
											System.out.println("se ha producido un error en la base de datos");
										}
										if (confirmar6 == -1) {
											System.out.println("se ha producido un error de sql");
										}

									}
									if (mod != 1 && mod != 2) {
										System.out.println("opcion erronea");
									}
									break;
								case 3:
									sc.nextLine();
									System.out.println("introduce el codigo de empleado del trabajador");
									codigo = sc.nextLine();
									System.out.println("anota el nombre del trabajador");
									nombre = sc.nextLine();
									System.out.println("anota el apellido del trabajador");
									apellido = sc.nextLine();
									System.out.println("anota el dni del trabajador");
									dni = sc.nextLine();
									System.out.println("anota la direccion del trabajador");
									dir = sc.nextLine();
									System.out.println("introduce el cargo en la empresa del empleado ");
									cargo = sc.nextLine();
									System.out.println("anota la contraseña del empleado");
									contraseña = sc.nextLine();
									System.out.println("introduce el telefono del trabajador");
									telefono = sc.nextInt();
									System.out.println("introduce el numero de la seguridad social del emlpeado");
									numSegSocial = sc.nextInt();

									int confirmar7 = bd.añadirEmpleado(codigo, nombre, apellido, dni, dir, telefono,
											numSegSocial, cargo, contraseña);
									if (confirmar7 == 1) {
										System.out.println("empleado borrado con exito");
									}
									if (confirmar7 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar7 == -1) {
										System.out.println("se ha producido un error de sql");
									}

								}
								break;
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
												+ v4.get(i).getIdent_producto() + " el dia: "
												+ v4.get(i).getFecha_encargo() + " la cantidad de: "
												+ v4.get(i).getCantidad() + ". Este pedido tiene un coste de: "
												+ v4.get(i).getGastos() + "€");
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
												+ v5.get(i).getIdent_producto() + " el dia: "
												+ v5.get(i).getFecha_encargo() + " la cantidad de: "
												+ v5.get(i).getCantidad() + ". Este pedido tiene un coste de: "
												+ v5.get(i).getGastos() + "€");
									}
									System.out.println("¿que pedido deseas eliminar?");
									eli = sc.nextInt();
									int confirmar8 = bd.borrarPedidoProducto(v5.get(eli - 1).getCod_pedido());
									if (confirmar8 == 1) {
										System.out.println("pedido eliminado con exito");
									}
									if (confirmar8 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar8 == -1) {
										System.out.println("se ha producido un error de sql");
									}

									break;
								case 3:

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
												+ v6.get(i).getCoste()
												+ " €, y pertenece al empleado con este codigo : "
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
									System.out.println(
											"Aqui te mostramos la lista de los enacragdos para hacer el pedido");
									Vector<Empleados> v8 = bd.mostrarEnacrgados();
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
												+ v8.get(i).getNum_seguridadSocial()
												+ ", en la empresa hace este trabajo " + v8.get(i).getCargoEnLaEmpresa()
												+ " y es encargado " + v8.get(i).getEsEncargado());
									}
									System.out.println("¿que encargado va ha hacer el pedido?");
									pedido3 = sc.nextInt();
									System.out.println("¿cuantas unidades deseas pedir?");
									unidades = sc.nextInt();
									System.out.println("¿cual es el precio por unidad?");
									precio = sc.nextInt();
									int confirmar10 = bd.añadirPedido_producto(v6.get(pedido1 - 1).getIdent_producto(),
											v7.get(pedido2 - 1).getCod_prov(), v8.get(pedido3 - 1).getCod_emple(),
											(unidades * precio), unidades);
									if (confirmar10 == 1) {
										System.out.println("pedido creado con exito");
									}
									if (confirmar10 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar10 == -1) {
										System.out.println("se ha producido un error de sql");
									}

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
									int confirmar11 = bd.borrarProveedor(v7.get(jaja - 1).getCod_prov());
									if (confirmar11 == 1) {
										System.out.println("proveedor eliminado con exito");
									}
									if (confirmar11 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar11 == -1) {
										System.out.println("se ha producido un error de sql");
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
											variable2 = sc.nextLine();
											bd.modificarProveedor(dato, variable2, codigo);
											if (bd.modificarProveedor(dato, variable2, codigo) == 1) {
												System.out.println("El proveedor se ha modificado con exito");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == 0) {
												System.out.println("se ha producido un error en la base de datos");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == -1) {
												System.out.println("se ha producido un error de sql");
											}
										}
										if (opcModifica == 2) {
											sc.nextLine();
											System.out.println("Introduce el nuevo apellido del proveedor");
											variable2 = sc.nextLine();
											dato = "Apellido_prov";
											bd.modificarProveedor(dato, variable2, codigo);
											if (bd.modificarProveedor(dato, variable2, codigo) == 1) {
												System.out.println("El proveedor se ha modificado con exito");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == 0) {
												System.out.println("se ha producido un error en la base de datos");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == -1) {
												System.out.println("se ha producido un error de sql");
											}

										}
										if (opcModifica == 3) {
											sc.nextLine();
											System.out.println("Introduce la nueva direccion del proveedor");
											variable2 = sc.nextLine();
											dato = "Direccion";
											bd.modificarProveedor(dato, variable2, codigo);
											if (bd.modificarProveedor(dato, variable2, codigo) == 1) {
												System.out.println("El proveedor se ha modificado con exito");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == 0) {
												System.out.println("se ha producido un error en la base de datos");
											}
											if (bd.modificarProveedor(dato, variable2, codigo) == -1) {
												System.out.println("se ha producido un error de sql");

											}
										}
										if (opcModifica == 4) {
											System.out.println("Introduce el nuevo telefono del proveedor");
											telefonoModificado = sc.nextInt();
											bd.modificarProveedorTelefono(codigo, telefonoModificado);
											if (bd.modificarProveedorTelefono(codigo, telefonoModificado) == 1) {
												System.out.println("El proveedor se ha modificado con exito");
											}
											if (bd.modificarProveedorTelefono(codigo, telefonoModificado) == 0) {
												System.out.println("se ha producido un error en la base de datos");
												if (bd.modificarProveedorTelefono(codigo, telefonoModificado) == -1) {
													System.out.println("se ha producido un error de sql");

												}
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
										int confirmar12 = bd.añadirProveedor(codigo, nombre, apellido, dir, telefono);

										if (confirmar12 == 1) {
											System.out.println("proveedor añadido con exito");
										}
										if (confirmar12 == 0) {
											System.out.println("se ha producido un error en la base de datos");
										}
										if (confirmar12 == -1) {
											System.out.println("se ha producido un error de sql");
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
								System.out.println("1.añadir gasto");
								System.out.println("2.listar todos los gastos");
								System.out.println("3.listar todas las ventas");
								opcG = sc.nextInt();
								switch (opcG) {
								case 1:
									sc.nextLine();
									System.out.println("anota la descripcion del gasto");
									descripcion = sc.nextLine();
									System.out.println("importe del gastos");
									importe = sc.nextDouble();
									int confirmar13 = bd.añadirGasto(descripcion, importe);
									if (confirmar13 == 1) {
										System.out.println("gasto añadido con exito");
									}
									if (confirmar13 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar13 == -1) {
										System.out.println("se ha producido un error de sql");
									}
									break;
								case 2:
									System.out.println("esta es la lista de los gastos");
									Vector<Gastos> v1 = bd.mostrarGastos();
									for (int i = 0; i < v1.size(); i++) {
										System.out.println("el dia: " + v1.get(i).getFechas_gasto()
												+ " se pordujo un gasto con codigo de gasto numero: "
												+ v1.get(i).getCod_gasto() + " por el importe de: "
												+ v1.get(i).getImporte_gasto() + " y esta es la descripcion del gasto: "
												+ v1.get(i).getDescripcion_gasto());
									}
									break;
								case 3:
									System.out.println("esta es la lista de los ventas");
									Vector<Ventas> v2 = bd.mostrarVentas();
									for (int i = 0; i < v2.size(); i++) {
										System.out.println("la venta realizada el: " + v2.get(i).getFechaVenta()
												+ " con el codigo de orden: " + v2.get(i).getCod_orden()
												+ " y con el codigo de venta: " + v2.get(i).getCod_Venta()
												+ " a proporcionado un beneficio de: " + v2.get(i).getBeneficio()
												+ " euros");
									}
									break;
								}

								break;
							default:
								System.out.println("opcion incorrecta");
								break;
							}

							sc.nextLine();
							System.out.println("¿deseas hacer otra cosa?");
							String seguirr = sc.nextLine();
							if (seguirr.equalsIgnoreCase("si")) {
								seguir = true;
							} else {
								seguir = false;
							}

						} while (seguir == true);

					}
				}

				if (bd.comporbarContraseñaEncargado(nombre, contraseña) == 1) {

					do {
						System.out.println("1.revisar stock de productos");
						System.out.println("2.recoger orden");
						System.out.println("3.listar ordenes en proceso");
						int opcEncargado = sc.nextInt();

						switch (opcEncargado) {
						case 1:
							System.out.println("estos son los productos y sus cantidades que tenemos actualmente");
							Vector<Producto> v11 = bd.mostrarStock();
							for (int i = 0; i < v11.size(); i++) {
								System.out.println("hay: " + v11.get(i).getCantidad()
										+ " del producto con identificador: " + v11.get(i).getIdent_producto() + " ");
							}
							break;
						case 2:
							precio = 0;
							System.out.println("esta es nuetra carta:");
							Vector<Carta> v20 = bd.mostrarCarta();
							do {
								for (int i = 0; i < v20.size(); i++) {
									System.out.println(i + 1 + "-" + v20.get(i).getNombreProducto()
											+ " tiene un precio de: " + v20.get(i).getPrecioUnidad() + " €");
								}
								System.out.println("elige numero del producto");
								num1 = sc.nextInt();
								v[num] = num1;
								num++;
								precio = precio + v20.get(num1 - 1).getPrecioUnidad();
								sc.nextLine();
								System.out.println("deseas elegir otro producto");
								String seguirr = sc.nextLine();
								if (seguirr.equalsIgnoreCase("si")) {
									seguir = true;
								} else {
									seguir = false;
								}
							} while (seguir == true && num < 5);

							System.out.println("¿el pedido es a domicilio?");
							String confirmar1 = sc.nextLine();
							if (confirmar1.equalsIgnoreCase("si")) {
								System.out.println("anota tu direccion");
								dir = sc.nextLine();
								int confirmar14 = bd.añadirOrden(nombre, precio, dir, v[0], v[1], v[2], v[3], v[4]);
								System.out.println("su pedido tiene un precio de: " + precio
										+ " en 25-30 minutos recibira en su domicilio el pedido");
								if (confirmar14 == 0) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar14 == -1) {
									System.out.println("se ha producido un error de sql");
								}

							} else {
								int confirmar15 = bd.añadirOrden(nombre, precio, null, v[0], v[1], v[2], v[3], v[4]);
								System.out.println("su pedido tiene un precio de: " + precio
										+ " en aproximadamente 10 minutos tendra lista su pedido");
								if (confirmar15 == 0) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar15 == -1) {
									System.out.println("se ha producido un error de sql");
								}
							}
							break;
						case 3:
							System.out.println("esta es la lista de ordenes en proceso: ");
							Vector<Orden> v15 = bd.mostrarOrden();
							for (int i = 0; i < v15.size(); i++) {
								if (v15.get(i).getP2() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v15.get(i).getP1());
									continue;
								}
								if (v15.get(i).getP3() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el productos con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2());
									continue;
								}
								if (v15.get(i).getP4() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido los productos con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2() + "," + v15.get(i).getP3());
									continue;
								}

								if (v15.get(i).getP5() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2() + "," + v15.get(i).getP3() + "," + v15.get(i).getP4());
									continue;
								}

								System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
										+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
										+ " ha pedido el producto con codigo: " + v15.get(i).getP1() + ","
										+ v15.get(i).getP2() + "," + v15.get(i).getP3() + "," + v15.get(i).getP4() + ","
										+ v15.get(i).getP5());

							}
							break;
						default:
							System.out.println("opcion incorrecta");
							break;

						}

						System.out.println("¿deseas hacer otra cosa?");
						String seguirr = sc.nextLine();
						if (seguirr.equalsIgnoreCase("si")) {
							seguir = true;
						} else {
							seguir = false;
						}
					} while (seguir == true);

				}

				if (bd.comporbarContraseñaEmpleados(nombre, contraseña) == 1) {
					if (bd.comporbarCocinero(nombre, contraseña) == 1) {
						System.out.println("1.listar ordenes en proceso");
						System.out.println("2.sacar comanda");
						opcCoc = sc.nextInt();
						switch (opcCoc) {
						case 1:
							System.out.println("esta es la lista de ordenes en proceso: ");
							Vector<Orden> v15 = bd.mostrarOrden();
							for (int i = 0; i < v15.size(); i++) {
								if (v15.get(i).getP2() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v15.get(i).getP1());
									continue;
								}
								if (v15.get(i).getP3() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el productos con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2());
									continue;
								}
								if (v15.get(i).getP4() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido los productos con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2() + "," + v15.get(i).getP3());
									continue;
								}

								if (v15.get(i).getP5() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2() + "," + v15.get(i).getP3() + "," + v15.get(i).getP4());
									continue;
								}

								System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
										+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
										+ " ha pedido el producto con codigo: " + v15.get(i).getP1() + ","
										+ v15.get(i).getP2() + "," + v15.get(i).getP3() + "," + v15.get(i).getP4() + ","
										+ v15.get(i).getP5());

							}

							break;
						case 2:
							System.out.println("estos son las ordenes disponibles ");
							Vector<Orden> v20 = bd.mostrarOrden();
							for (int i = 0; i < v20.size(); i++) {
								if (v20.get(i).getP2() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v20.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v20.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v20.get(i).getP1());
									continue;
								}
								if (v20.get(i).getP3() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v20.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v20.get(i).getCod_emple()
											+ " ha pedido el productos con codigo: " + v20.get(i).getP1() + ","
											+ v20.get(i).getP2());
									continue;
								}
								if (v20.get(i).getP4() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v20.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v20.get(i).getCod_emple()
											+ " ha pedido los productos con codigo: " + v20.get(i).getP1() + ","
											+ v20.get(i).getP2() + "," + v20.get(i).getP3());
									continue;
								}

								if (v20.get(i).getP5() == 0) {
									System.out.println(i + 1 + "-" + "la orden con codigo: " + v20.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v20.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v20.get(i).getP1() + ","
											+ v20.get(i).getP2() + "," + v20.get(i).getP3() + "," + v20.get(i).getP4());
									continue;
								}

								System.out.println(i + 1 + "-" + "la orden con codigo: " + v20.get(i).getCod_orden()
										+ " creada por el empleado con codigo: " + v20.get(i).getCod_emple()
										+ " ha pedido el producto con codigo: " + v20.get(i).getP1() + ","
										+ v20.get(i).getP2() + "," + v20.get(i).getP3() + "," + v20.get(i).getP4() + ","
										+ v20.get(i).getP5());

							}
							System.out.println("elige la comanda que deseas sacar");
							opc53 = sc.nextInt();
							confirmar2 = bd.borrarOrden(v20.get(opc53 - 1).getCod_orden());
							if (confirmar2 == 1) {
								System.out.println("orden eliminada");
							}
							if (confirmar2 == 0) {
								System.out.println("no se ha podido eliminar la orden");
							}
							if (confirmar2 == -1) {
								System.out.println("se ha producido un error en la base de datos");
							}
							break;
						default:
							System.out.println("opcion erronea");
							break;

						}

					}
					if (bd.comporbarCocinero(nombre, contraseña) == 0) {
						do {
							System.out.println("1.recoger orden");
							System.out.println("2.listar ordenes en proceso");
							int opcEmple = sc.nextInt();
							switch (opcEmple) {
							case 1:
								precio = 0;
								System.out.println("esta es nuetra carta:");
								Vector<Carta> v20 = bd.mostrarCarta();
								do {
									for (int i = 0; i < v20.size(); i++) {
										System.out.println(i + 1 + "-" + v20.get(i).getNombreProducto()
												+ " tiene un precio de: " + v20.get(i).getPrecioUnidad() + " €");
									}
									System.out.println("elige numero del producto");
									num1 = sc.nextInt();
									v[num] = num1;
									num++;
									if (num == 5) {
										System.out.println("solo se pueden anotar 5 productos por orden");
										break;
									}
									precio = precio + v20.get(num1 - 1).getPrecioUnidad();
									sc.nextLine();
									System.out.println("deseas elegir otro producto");
									String seguirr = sc.nextLine();
									if (seguirr.equalsIgnoreCase("si")) {
										seguir = true;
									} else {
										seguir = false;
									}
								} while (seguir == true && num < 5);
								System.out.println("¿el pedido es a domicilio?");
								String confirmar1 = sc.nextLine();
								if (confirmar1.equalsIgnoreCase("si")) {
									System.out.println("anota tu direccion");
									dir = sc.nextLine();
									int confirmar14 = bd.añadirOrden(nombre, precio, dir, v[0], v[1], v[2], v[3], v[4]);
									System.out.println("su pedido tiene un precio de: " + precio
											+ " en 25-30 minutos recibira en su domicilio el pedido");
									if (confirmar14 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar14 == -1) {
										System.out.println("se ha producido un error de sql");
									}
								} else {
									int confirmar15 = bd.añadirOrden(nombre, precio, null, v[0], v[1], v[2], v[3],
											v[4]);
									System.out.println("su pedido tiene un precio de: " + precio
											+ " en aproximadamente 10 minutos tendra lista su pedido");
									if (confirmar15 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar15 == -1) {
										System.out.println("se ha producido un error de sql");
									}
								}
								break;
							case 2:
								System.out.println("esta es la lista de ordenes en proceso: ");
								Vector<Orden> v15 = bd.mostrarOrden();
								for (int i = 0; i < v15.size(); i++) {
									if (v15.get(i).getP2() == 0) {
										System.out.println(i + 1 + "-" + "la orden con codigo: "
												+ v15.get(i).getCod_orden() + " creada por el empleado con codigo: "
												+ v15.get(i).getCod_emple() + " ha pedido el producto con codigo: "
												+ v15.get(i).getP1());
										continue;
									}
									if (v15.get(i).getP3() == 0) {
										System.out.println(i + 1 + "-" + "la orden con codigo: "
												+ v15.get(i).getCod_orden() + " creada por el empleado con codigo: "
												+ v15.get(i).getCod_emple() + " ha pedido el productos con codigo: "
												+ v15.get(i).getP1() + "," + v15.get(i).getP2());
										continue;
									}
									if (v15.get(i).getP4() == 0) {
										System.out.println(i + 1 + "-" + "la orden con codigo: "
												+ v15.get(i).getCod_orden() + " creada por el empleado con codigo: "
												+ v15.get(i).getCod_emple() + " ha pedido los productos con codigo: "
												+ v15.get(i).getP1() + "," + v15.get(i).getP2() + ","
												+ v15.get(i).getP3());
										continue;
									}

									if (v15.get(i).getP5() == 0) {
										System.out.println(i + 1 + "-" + "la orden con codigo: "
												+ v15.get(i).getCod_orden() + " creada por el empleado con codigo: "
												+ v15.get(i).getCod_emple() + " ha pedido el producto con codigo: "
												+ v15.get(i).getP1() + "," + v15.get(i).getP2() + ","
												+ v15.get(i).getP3() + "," + v15.get(i).getP4());
										continue;
									}

									System.out.println(i + 1 + "-" + "la orden con codigo: " + v15.get(i).getCod_orden()
											+ " creada por el empleado con codigo: " + v15.get(i).getCod_emple()
											+ " ha pedido el producto con codigo: " + v15.get(i).getP1() + ","
											+ v15.get(i).getP2() + "," + v15.get(i).getP3() + "," + v15.get(i).getP4()
											+ "," + v15.get(i).getP5());

								}
								break;
							default:
								System.out.println("opcion incorrecta");
								break;
							}

							System.out.println("¿deseas hacer otra cosa?");
							String seguirr = sc.nextLine();
							if (seguirr.equalsIgnoreCase("si")) {
								seguir = true;
							} else {
								seguir = false;
							}
						} while (seguir == true);

					}
				}

				if (bd.comporbarContraseñaclientes(nombre, contraseña) == 1) {
					do {
						System.out.println("1.aministrar cuenta");
						System.out.println("2.crear un orden");
						System.out.println("3.cancelar orden");
						int opcCli = sc.nextInt();
						switch (opcCli) {
						case 1:
							System.out.println("1.cambiar correo de la cuenta");
							System.out.println("2.cambiar contraseña de la cuenta");
							System.out.println("3.desbloquear cuenta");
							int opcModCli = sc.nextInt();

							switch (opcModCli) {
							case 1:
								dato = "Correo";
								sc.nextLine();
								System.out.println("anota el nuevo correo");
								variable2 = sc.nextLine();
								int confirmar16 = bd.modificarCliente(dato, variable2, nombre);
								if (confirmar16 == 0) {
									System.out.println("correo modificado con exito");
								}
								if (confirmar16 == 1) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar16 == -1) {
									System.out.println("se ha producido un error de sql");
								}
								break;
							case 2:
								dato = "Contraseña";
								sc.nextLine();
								System.out.println("anota la nueva contraseña");
								variable2 = sc.nextLine();
								int confirmar17 = bd.modificarCliente(dato, variable2, nombre);
								if (confirmar17 == 1) {
									System.out.println("contraseña modificada con exito");
								}
								if (confirmar17 == 0) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar17 == -1) {
									System.out.println("se ha producido un error de sql");
								}
								break;
							case 3:
								dato = "bloqueada";
								sc.nextLine();
								System.out.println("anota tu contraseña de nuevo");
								contraseña2 = sc.nextLine();
								if (bd.comporbarContraseñaclientes(nombre, contraseña2) == 1) {
									int confirmar18 = bd.desbloquearCliente(nombre);
									if (confirmar18 == 1) {
										System.out.println("tarjeta desbloqueada con exito");
									}
									if (confirmar18 == 0) {
										System.out.println("se ha producido un error en la base de datos");
									}
									if (confirmar18 == -1) {
										System.out.println("se ha producido un error de sql");
									}
								} else {
									System.out.println("la contraseña no coincide");
								}
								break;
							default:
								System.out.println("opcion erronea");
								break;
							}
							break;
						case 2:
							precio = 0;
							System.out.println("esta es nuetra carta:");
							Vector<Carta> v20 = bd.mostrarCarta();
							do {
								for (int i = 0; i < v20.size(); i++) {
									System.out.println(i + 1 + "-" + v20.get(i).getNombreProducto()
											+ " tiene un precio de: " + v20.get(i).getPrecioUnidad() + " €");
								}
								System.out.println("elige numero del producto");
								num1 = sc.nextInt();
								v[num] = num1;
								sc.nextLine();
								num++;
								if (num == 5) {
									System.out.println("solo se pueden anotar 5 productos por orden");
									break;
								}
								precio = precio + v20.get(num1 - 1).getPrecioUnidad();
								System.out.println("deseas elegir otro producto");
								String seguirr = sc.nextLine();
								if (seguirr.equalsIgnoreCase("si")) {
									seguir = true;
								} else {
									seguir = false;
								}
							} while (seguir == true && num < 5);

							System.out.println("¿el pedido es a domicilio?");
							String confirmar1 = sc.nextLine();
							if (confirmar1.equalsIgnoreCase("si")) {
								System.out.println("anota tu direccion");
								dir = sc.nextLine();
								int confirmar18 = bd.añadirOrden(nombre, precio, dir, v[0], v[1], v[2], v[3], v[4]);
								System.out.println("su pedido tiene un precio de: " + precio
										+ " en 25-30 minutos recibira en su domicilio el pedido");
								if (confirmar18 == 0) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar18 == -1) {
									System.out.println("se ha producido un error de sql");
								}
							} else {
								int confirmar19 = bd.añadirOrden(nombre, precio, null, v[0], v[1], v[2], v[3], v[4]);
								System.out.println("su pedido tiene un precio de: " + precio
										+ " en aproximadamente 10 minutos tendra lista su pedido");
								if (confirmar19 == 0) {
									System.out.println("se ha producido un error en la base de datos");
								}
								if (confirmar19 == -1) {
									System.out.println("se ha producido un error de sql");
								}
							}
							break;
						case 3:
							sc.nextLine();
							System.out.println("anota el codigo de la orden a anular");
							codigo = sc.nextLine();
							confirmar2 = bd.borrarOrden(codigo);
							if (confirmar2 == 1) {
								System.out.println("orden eliminada con exito");
							}
							if (confirmar2 == 0) {
								System.out.println("no se ha podido eliminar la orden");
							}
							if (confirmar2 == -1) {
								System.out.println("se ha producido un error en la base de datos");
							}
							break;
						default:
							System.out.println("opcion erronea");
							break;

						}
						System.out.println("¿deseas hacer otra cosa?");
						String seguirr = sc.nextLine();
						if (seguirr.equalsIgnoreCase("si")) {
							seguir = true;
						} else {
							seguir = false;
						}
					} while (seguir == true);

				}

				break;

			case 3:
				System.out.println("hasta luegi");
				break;
			}
		} while (opc != 3);

	}

}
