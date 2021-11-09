package Logica;

import java.io.IOException;

import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;
public class App {
	/**
	 * This is the main function
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		SistemaUCNLol sistema = new SistemaUCNLolImpl();
		LeerPersonajes(sistema);
		LeerCuentas(sistema);
		LeerEstadisticas(sistema);
		InicioSesion(sistema);
	}
	/**
	 * this function is the login
	 * @param sistema
	 */
	public static void InicioSesion(SistemaUCNLol sistema) {
		StdOut.print("Ingrese nombre cuenta: ");
		String nombre = StdIn.readString();
		if(nombre.equals("ADMIN")) {
			StdOut.print("Ingrese su clave: ");
			String pass = StdIn.readString();
			while(!pass.equals("ADMIN")) {
				StdOut.print("Clave incorrecta, ingrese nuevamente: ");
				pass = StdIn.readString();
			}
			MenuAdmin(sistema);
		}
		else {
			boolean verificar = sistema.verificarCuenta(nombre);
			while(verificar == false) {
				StdOut.println("Cuenta no encontrada o bloqueada");
				StdOut.print("Desea registrarse(si/no): ");
				String respuesta = StdIn.readString();
				while(!respuesta.equals("si") && !respuesta.equals("no")) {
					StdOut.print("Desea registrarse(si/no): ");
					respuesta = StdIn.readString();
				}
				if(respuesta.equals("si")) {
					Registro(sistema);
				}
				else {
					StdOut.print("Ingrese nombre cuenta: ");
					nombre = StdIn.readString();
					verificar = sistema.verificarCuenta(nombre);
				}
			}
			MenuCliente(sistema,nombre);
			
		}
		
	}
	/**
	 * This function is the register 
	 * @param sistema
	 */
	public static void Registro(SistemaUCNLol sistema) {
		StdOut.print("Nombre: ");
		String nombre = StdIn.readString();
		StdOut.print("Clave: ");
		String clave = StdIn.readString();
		StdOut.print("Nick: ");
		String nick = StdIn.readString();
		StdOut.print("Region: ");
		String region  = StdIn.readString();
		sistema.ingresarCuenta(nombre, clave, nick, 0, 0, region);
	}
	/**
	 * this function is the menu of the client
	 * @param sistema
	 * @param nombre
	 */
	public static void MenuCliente(SistemaUCNLol sistema, String nombre) {
		String respuesta = "";
		while(!respuesta.equals("7")){
			StdOut.println("1.- Comprar Skin");
			StdOut.println("2.- Comprar Personaje");
			StdOut.println("3.- Skins Disponibles");
			StdOut.println("4.- Mostrar Inventario");
			StdOut.println("5.- Recargar Rp");
			StdOut.println("6.- Mostrar Datos Cuenta");
			StdOut.println("7.- Salir");
			StdOut.print("Respuesta: ");
			respuesta = StdIn.readString();
			if(respuesta.equals("1")) {
				StdOut.print("Nombre personaje: ");
				String nombre_personaje = StdIn.readString();
				boolean verificar = sistema.ComprarSkin(nombre_personaje, nombre);
				if(verificar == false) {
					StdOut.println("No se ha podido realizar");
				}
				else {
					StdOut.println("Realizado con exito");
				}
			}	
			if(respuesta.equals("2")) {
				StdOut.print("Nombre personaje: ");
				String nombre_personaje = StdIn.readString();
				boolean verificar = sistema.ComprarPersonaje(nombre_personaje, nombre);
				if(verificar == false) {
					StdOut.println("No se ha podido realizar");
				}
				else {
					StdOut.println("Realizado con exito");
				}
			}
			if(respuesta.equals("3")) {
				StdOut.println(sistema.SkinsDisponibles(nombre));
			}
			if(respuesta.equals("4")) {
				StdOut.println(sistema.MostrarInventario(nombre));
			}
			if(respuesta.equals("5")) {
				StdOut.print("Ingrese rp a cargar: ");
				int cantidad = StdIn.readInt();
				boolean verificar = sistema.RecargarRp(nombre, cantidad);
				if(verificar == false) {
					StdOut.println("No se ha podido realizar");
				}
				else {
					StdOut.println("Realizado con exito");
				}
			}
			if(respuesta.equals("6")) {
				StdOut.println(sistema.MostrarDatosCuenta(nombre));
				StdOut.print("Desea cambiar la clave(si/no): ");
				String answer = StdIn.readString();
				if(answer.equals("si")) {
					StdOut.print("Ingrese clave nueva: ");
					String clave_nueva = StdIn.readString();
					sistema.cambiarClave(clave_nueva, nombre);
				}
			}
		}
	}
	/**
	 * This function is the Admin menu
	 * @param sistema
	 */
	public static void MenuAdmin(SistemaUCNLol sistema) {
		String respuesta = "";
		do {
			StdOut.println("1.- Desplegar recaudacion de ventas por rol");
			StdOut.println("2.- Desplegar recaudacion total de ventas por region");
			StdOut.println("3.- Desplegar recaudaciond de ventas por personaje");
			StdOut.println("4.- Desplegar cantidad de personajes por rol existente");
			StdOut.println("5.- Agregar personaje");
			StdOut.println("6.- Agregar Skin");
			StdOut.println("7.- Bloquear Jugador");
			StdOut.println("8.- Desplegar Cuentas");
			StdOut.println("9.- Salir");
			StdOut.print("Respuesta: ");
			respuesta = StdIn.readString();
			if(respuesta.equals("1")) {
				StdOut.print(sistema.recaudacionVentasPorRol());
			}	
			if(respuesta.equals("2")) {
				StdOut.println(sistema.RecaudacionVentasPorRegion());
			}
			if(respuesta.equals("3")) {
				StdOut.println(sistema.RecaudacionPersonajes());
			}
			if(respuesta.equals("4")) {
				StdOut.println(sistema.CantidadPorRol());
			}
			if(respuesta.equals("5")) {
				StdOut.print("Nombre: ");
				String nombre = StdIn.readString();	
				StdOut.print("Rol: ");
				String rol = StdIn.readString();
				sistema.ingresarPersonaje(nombre, rol);
				
			}
			if(respuesta.equals("6")) {
				StdOut.print("Nombre personaje: ");
				String nombre_personaje = StdIn.readString(); 
				StdOut.print("Nombre Skin: ");
				String nombre_skin = StdIn.readString();
				StdOut.print("Calidad : ");
				String calidad = StdIn.readString();
				int precio = 0;
				if(calidad.equals("M")) {
					precio = 3250;
				}
				if(calidad.equals("D")) {
					precio = 2750;
				}
				if(calidad.equals("L")) {
					precio = 1820;
				}
				if(calidad.equals("E")) {
					precio = 1350;
				}
				if(calidad.equals("N")) {
					precio = 975;
				}
				sistema.ingresarSkin(nombre_skin, calidad, precio);
				sistema.AsociarSkinPersonaje(nombre_personaje, nombre_skin);
			}
			if(respuesta.equals("7")) {
				StdOut.print("Nombre jugador: ");
				String nombre = StdIn.readString();
				sistema.bloquearJugador(nombre);
			}
			if(respuesta.equals("8")) {
				
			}

		}while(!respuesta.equals("9"));
	}
	/** 
	 * This function read a txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerCuentas(SistemaUCNLol sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Cuentas.txt");
		while(!arch1.isEndFile()) {
			Registro reg = arch1.getRegistro();
			String nombre = reg.getString();
			String clave = reg.getString();
			String nick = reg.getString();
			int nivel = reg.getInt();
			int rp = reg.getInt();
			sistema.ingresarCuenta(nombre, clave, nick, nivel, rp, nick);
			int cantCampeones = reg.getInt();
			for(int i = 0;i< cantCampeones ; i++) {
				String campeon = reg.getString();
				sistema.AsociarPersonajeJugador(nombre, campeon);
				int cantSkins = reg.getInt();
				for(int j = 0;j< cantSkins ; j++) {
					String skin = reg.getString();
					sistema.AsociarSkinPersonajeJugador(nombre, campeon, skin);
				}
			}
		}
		arch1.close();
	}
	/**
	 * this function read a txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerPersonajes(SistemaUCNLol sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Personajes.txt");
		while(!arch1.isEndFile()) {
			Registro reg = arch1.getRegistro();
			String nombre = reg.getString();
			String rol = reg.getString();
			sistema.ingresarPersonaje(nombre, rol);
			int cantSkins = reg.getInt();
			for(int j = 0;j< cantSkins ; j++) {
				String skin = reg.getString();
				String calidad = reg.getString();
				int precio = 0;
				if(calidad.equals("M")) {
					precio = 3250;
				}
				if(calidad.equals("D")) {
					precio = 2750;
				}
				if(calidad.equals("L")) {
					precio = 1820;
				}
				if(calidad.equals("E")) {
					precio = 1350;
				}
				if(calidad.equals("N")) {
					precio = 975;
				}
				sistema.ingresarSkin(skin, calidad, precio);
				sistema.AsociarSkinPersonaje(nombre, skin);
			}
		}
		arch1.close();
	}
	/**
	 * this function read a txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerEstadisticas(SistemaUCNLol sistema) throws IOException{
		ArchivoEntrada arch1 = new ArchivoEntrada("Estadisticas.txt");
		while(!arch1.isEndFile()) {
			Registro reg = arch1.getRegistro();
			String nombre = reg.getString();
			int recaudacion = reg.getInt();
			sistema.ingresarEstadistica(nombre, recaudacion);
		}
	}
}
