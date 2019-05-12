package codigo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import bbdd.BD_Conector;
import bbdd.BD_Usuarios;
import modelos.BloqueadaException;
import modelos.Cliente;

public class Main {

	public static void main(String[] args) throws BloqueadaException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int opc = 0, repetido = 0,mod;
		boolean bloquear = false;
		String tipo = null, nombre, contraseña, contraseña2, apellido, correo, nombreUsu,codigoEmple,confirmar,dir,tel;
		BD_Conector.BD_Ini("rincón gloton");
		BD_Usuarios bd = new BD_Usuarios();
		System.out.println("welocome to the rincon gloton page");
		System.out.println("------------------------");
		System.out.println("¿que deseas hacer?  1.darse de alta ,2.login");
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
						&& bd.comporbarContraseñaUsuarios(nombre, contraseña) == 0) {
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
					&& bd.comporbarContraseñaclientes(nombre, contraseña) == 0);

			if (bloquear == true) {
				break;
			}
			if (bd.comporbarContraseñaUsuarios(nombre, contraseña) == 1) {
				tipo = bd.login(nombre, contraseña);
				if (tipo.equalsIgnoreCase("administrador")) {
					System.out.println("1.adminitrar empleados ");
					System.out.println("administrar pedidos");
					System.out.println("administrar proveedores");
					System.out.println("listar gastos y ventas");
					opc = sc.nextInt();
					switch (opc) {
					case 1:
						System.out.println("1.despedir empleado");
						System.out.println("2.modificar datos de un empleado");
						System.out.println("3.dar de alta un empleado");
						opc = sc.nextInt();
						switch (opc) {
						case 1:
							System.out.println("anota el codigo del empleado");
							codigoEmple=sc.nextLine();
							System.out.println("seguro que deseas despedir a este empleado? pobrecito");
							confirmar=sc.nextLine();
							if(confirmar.equalsIgnoreCase("si")){
								//eliminar el usuario
							}
							else {
								System.out.println("bien hecho amigo");
							}
							break;
						case 2:
							System.out.println("anota el codigo del empleado");
							codigoEmple=sc.nextLine();
							System.out.println("¿que deseas modificar 1.direccion 2.telefono?");
							mod=sc.nextInt();
							if (mod==1) {
								System.out.println("anota la direccion");
								dir=sc.nextLine();
								//modificar el empleado con el codigo y la direccion
							}
							if (mod==2) {
								System.out.println("anota el telefono");
								tel=sc.nextLine();
								//modificar el empleado con el codigo y el telefono
							}
								if(mod!=1 && mod!=2) {
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
							//hacer el insert del trbajador
							break;
						}
						System.out.println("administrar pedidos");
						System.out.println("administrar proveedores");
						System.out.println("listar gastos y ventas");
					}
				}

				if (tipo.equalsIgnoreCase("encargado")) {
					System.out.println("realizar pedido de productos");
					System.out.println("revisar stock de productos");
					System.out.println("");
					System.out.println("recoger orden");
					System.out.println("listar pedidos en proceso");

				}

				if (tipo.equalsIgnoreCase("empleado")) {
					System.out.println("recoger orden");
					System.out.println("listar pedidos en proceso");

				}

			}
			if (bd.comporbarContraseñaclientes(nombre, contraseña) == 1) {
				System.out.println("aministrar cuenta");
				System.out.println("crear un orden");
				System.out.println("ver orden");
				System.out.println("cancelar orden");
			}

			break;

		}
		// poner un do while para repetir siempre
		System.out.println("hasta luegi");
	}

}

