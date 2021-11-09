package Logica;
import Dominio.*;
import ucn.StdIn;
import ucn.StdOut;
public class SistemaUCNLolImpl implements SistemaUCNLol{
	private ListaCuentas listaCuentas;
	private ListaPersonajes listaPersonajes;
	private ListaSkins listaSkins;
	public boolean ingresarCuenta(String nombre,String clave,String nick, int nivel, int rp,String region) {
		Cuenta cuenta = new Cuenta(nombre,clave,nick,nivel,rp,region);
		boolean ingreso = listaCuentas.insertarCuenta(cuenta);
		cuenta.setRecaudacion(rp);
		return ingreso;
	}
	public boolean ingresarPersonaje(String nombre,String rol) {
		Personaje personaje = new Personaje(nombre,rol);
		boolean ingreso = listaPersonajes.insertarPersonaje(personaje);
		return ingreso;
	}
	public boolean ingresarSkin(String nombre,String calidad, int precio) {
		Skin skin = new Skin(nombre,calidad,precio);
		boolean ingreso = listaSkins.insertarSkin(skin);
		return ingreso;
	}
	public boolean AsociarSkinPersonaje(String nombre_personaje, String nombre_skin) {
		Skin skin = listaSkins.buscarSkin(nombre_skin);
		Personaje personaje = listaPersonajes.buscarPersonaje(nombre_personaje);
		boolean ingreso = personaje.getLps().insertarSkin(skin);
		return ingreso;
	}
	public boolean AsociarPersonajeJugador(String nombre_cuenta, String nombre_personaje) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre_cuenta);
		Personaje personaje = listaPersonajes.buscarPersonaje(nombre_personaje);
		boolean ingreso = cuenta.getLcp().insertarPersonaje(personaje);
		int recaudacion = cuenta.getRecaudacion();
		cuenta.setRecaudacion(recaudacion + 975);
		cuenta.getLcp().buscarPersonaje(nombre_personaje).getLps().setCantSkins(0);
		return ingreso;
	}
	public boolean AsociarSkinPersonajeJugador(String nombre_cuenta,String nombre_personaje, String nombre_skin) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre_cuenta);
		Personaje personaje = cuenta.getLcp().buscarPersonaje(nombre_personaje);
		Skin skin = listaSkins.buscarSkin(nombre_skin);
		boolean ingreso = personaje.getLps().insertarSkin(skin);
		int recaudacion = cuenta.getRecaudacion();
		cuenta.setRecaudacion(recaudacion + skin.getPrecio());
		int recaudacion_personaje = listaPersonajes.buscarPersonaje(nombre_personaje).getRecaudacion();
		listaPersonajes.buscarPersonaje(nombre_personaje).setRecaudacion(recaudacion_personaje + skin.getPrecio());
		return ingreso;
	}
	public boolean ComprarSkin(String nombre_personaje, String nombre_cuenta) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre_cuenta);
		for(int i = 0;i< cuenta.getLcp().getCantPersonajes();i++) {
			if(cuenta.getLcp().getPersonaje(i).getNombre().equals(nombre_personaje)) {
				return false;
			}
		}
		int i;
		for(i = 0;i<listaPersonajes.getCantPersonajes();i++) {
			if(listaPersonajes.getPersonaje(i).getNombre().equals(nombre_personaje)) {
				break;
			}
		}
		if(i == listaPersonajes.getCantPersonajes()) {
			return false;
		}
		Personaje personaje = listaPersonajes.getPersonaje(i);
		StdOut.print("Ingrese nombre skin: ");
		String nombre_skin = StdIn.readString();
		int j;
		for(j = 0;j< personaje.getLps().getCantSkins();j++) {
			if(personaje.getLps().getSkin(j).getNombre().equals(nombre_skin)) {
				break;
			}
		}
		if(j == personaje.getLps().getCantSkins()) {
			return false;
		}
		Skin skin = personaje.getLps().getSkin(j);
		if(cuenta.getRp() < skin.getPrecio()) {
			return false;
		}
		cuenta.getLcp().getPersonaje(i).getLps().insertarSkin(skin);
		int recaudacionPersonaje = personaje.getRecaudacion();
		personaje.setRecaudacion(recaudacionPersonaje + skin.getPrecio());
		int recaudacionCuenta = cuenta.getRecaudacion();
		cuenta.setRecaudacion(recaudacionCuenta + skin.getPrecio());
		int rp = cuenta.getRp();
		cuenta.setRp(rp - skin.getPrecio());
		return true;
	}
	public boolean ComprarPersonaje(String nombre_personaje,String nombre_cuenta) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre_cuenta);
		for(int i = 0;i< cuenta.getLcp().getCantPersonajes();i++) {
			if(cuenta.getLcp().getPersonaje(i).getNombre().equals(nombre_personaje)) {
				return false;
			}
		}
		int i;
		for(i = 0;i<listaPersonajes.getCantPersonajes();i++) {
			if(listaPersonajes.getPersonaje(i).getNombre().equals(nombre_personaje)) {
				break;
			}
		}
		if(i == listaPersonajes.getCantPersonajes()) {
			return false;
		}
		Personaje personaje = listaPersonajes.getPersonaje(i);
		if(cuenta.getRp() < personaje.getPrecio()) {
			return false;
		}
		cuenta.getLcp().insertarPersonaje(personaje);
		int recaudacionPersonaje = personaje.getRecaudacion();
		personaje.setRecaudacion(recaudacionPersonaje + personaje.getPrecio());
		int recaudacionCuenta = cuenta.getRecaudacion();
		cuenta.setRecaudacion(recaudacionCuenta + personaje.getPrecio());
		int nivel_cuenta = cuenta.getNivel();
		cuenta.setNivel(nivel_cuenta + 1);
		int rp = cuenta.getRp();
		cuenta.setRp(rp - personaje.getPrecio());
		return true;
	}
	public String SkinsDisponibles(String nombre) {
		String despliegue = "";
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		for(int i = 0; i<listaSkins.getCantSkins(); i++) {
			int j;
			for(j = 0; i< cuenta.getLcp().getCantPersonajes();j++) {
				int k;
				for(k = 0; i<cuenta.getLcp().getPersonaje(j).getLps().getCantSkins();k++) {
					if(cuenta.getLcp().getPersonaje(j).getLps().getSkin(k).getNombre().equals(nombre)) {
						break;
					}
				}
				if(k != cuenta.getLcp().getPersonaje(j).getLps().getCantSkins()) {
					break;
				}
			}
			if(j == cuenta.getLcp().getCantPersonajes()) {
				despliegue += listaSkins.getSkin(i) + "\n";
			}
		}
		return despliegue;
	}
	public String MostrarInventario(String nombre) {
		String despliegue = "";
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		if(cuenta.getLcp().getCantPersonajes() < 1) {
			despliegue = "No tiene personajes";
		}
		else {
			despliegue = "Personajes:" +"\n";
			for(int i = 0; i < cuenta.getLcp().getCantPersonajes(); i++) {
				despliegue += cuenta.getLcp().getPersonaje(i).getNombre() + "\n";
				if(cuenta.getLcp().getPersonaje(i).getLps().getCantSkins() < 1) {
					despliegue += "no tiene skins"+ "\n";
				}
				else {
					despliegue += "Skins: " + "\n";
					for(int j = 0; j<cuenta.getLcp().getPersonaje(i).getLps().getCantSkins();j++) {
						despliegue += cuenta.getLcp().getPersonaje(i).getLps().getSkin(j) + "/n";
					}
				}
			}
		}
		
		return despliegue;
	}
	public boolean RecargarRp(String nombre,int cantidad) {
		if(cantidad < 1) {
			return false;
		}
		else {
			Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
			int actual = cuenta.getRp();
			cuenta.setRp(actual + cantidad);
			return true;
		}
	}
	public String MostrarDatosCuenta(String nombre) {
		String despliegue = "";
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		String clave = OcultarClave(cuenta.getClave());
		despliegue += "Nombre: " +cuenta.getNombre() + "Nick: " + cuenta.getNick() +  "Clave: " + clave;
		return despliegue;
	}
	public String OcultarClave(String clave) {
		String [] partes = clave.split("|");
		for(int i = 0; i< partes.length -3;i++) {
			partes[i] = "*";
		}
		String claveNueva = "";
		for(int i = 0; i< partes.length;i++) {
			claveNueva += partes[i];
		}
		return claveNueva;
	}
	public boolean cambiarClave(String claveNueva, String nombre) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		cuenta.setClave(claveNueva);
		return true;
	}
	public String recaudacionVentasPorRol() {
		String despliegue = "";
		int adc = 0;
		int sup = 0;
		int jg = 0;
		int top = 0;
		int mid = 0;
		for(int i = 0;i<listaPersonajes.getCantPersonajes();i++) {
			if(listaPersonajes.getPersonaje(i).getRol().equals("ADC")) {
				adc += listaPersonajes.getPersonaje(i).getPrecio();
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("SUP")) {
				sup += listaPersonajes.getPersonaje(i).getPrecio();
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("JG")) {
				jg += listaPersonajes.getPersonaje(i).getPrecio();
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("TOP")) {
				top += listaPersonajes.getPersonaje(i).getPrecio();
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("MID")) {
				mid += listaPersonajes.getPersonaje(i).getPrecio();
			}
		}
		adc = (int) (adc * 6.15);
		sup = (int) (sup * 6.15);
		jg = (int) (jg * 6.15);
		top = (int) (top * 6.15);
		mid = (int) (mid * 6.15);
		despliegue += "Top: "+ top + " CLP " + "Jg: "+ jg + " CLP " +  "Mid: "+ mid + " CLP " + "Adc: "+ adc + " CLP " + "Sup: "+ sup + " CLP ";
		return despliegue;
	}
	public String RecaudacionVentasPorRegion() {
		String recaudacion = "";
		int las = 0;
		int lan = 0;
		int euw = 0;
		int kr = 0;
		int na = 0;
		int ru = 0;
		for(int i = 0; i< listaCuentas.getCantCuentas();i++) {
			if(listaCuentas.getCuenta(i).getRegion().equals("LAS")) {
				las += listaCuentas.getCuenta(i).getRecaudacion();			
			}
			if(listaCuentas.getCuenta(i).getRegion().equals("LAN")) {
				lan += listaCuentas.getCuenta(i).getRecaudacion();
			}
			if(listaCuentas.getCuenta(i).getRegion().equals("EUW")) {
				euw += listaCuentas.getCuenta(i).getRecaudacion();
			}
			if(listaCuentas.getCuenta(i).getRegion().equals("KR")) {
				kr += listaCuentas.getCuenta(i).getRecaudacion();
			}
			if(listaCuentas.getCuenta(i).getRegion().equals("NA")) {
				na += listaCuentas.getCuenta(i).getRecaudacion();
			}
			if(listaCuentas.getCuenta(i).getRegion().equals("RU")) {
				ru += listaCuentas.getCuenta(i).getRecaudacion();
			}
		}
		las = (int) (las * 6.15);
		lan = (int) (lan * 6.15);
		euw = (int) (euw * 6.15);
		kr = (int) (kr * 6.15);
		na = (int) (na * 6.15);
		ru = (int) (ru * 6.15);
		recaudacion += "LAS: " + las + " CLP " + "LAN: " + lan + " CLP " + "EUW: " + euw + " CLP " + "KR: " + kr + " CLP " + "NA: " +na + " CLP "+ "RU: " + ru + " CLP ";
		return recaudacion;
	}
	public String RecaudacionPersonajes() {
		String despliegue = "";
		for(int i = 0; i< listaPersonajes.getCantPersonajes();i++) {
			despliegue += listaPersonajes.getPersonaje(i).getNombre() + " " + (listaPersonajes.getPersonaje(i).getRecaudacion() * 6.15) + " CLP" + "\n";
		}
		return despliegue;
	}
	public String CantidadPorRol() {
		String despliegue = "";
		int adc = 0;
		int sup = 0;
		int mid = 0;
		int jg = 0;
		int top = 0;
		for(int i = 0;i<listaPersonajes.getCantPersonajes();i++) {
			if(listaPersonajes.getPersonaje(i).getRol().equals("ADC")) {
				adc ++;
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("SUP")) {
				sup ++;
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("JG")) {
				jg ++;
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("TOP")) {
				top ++;
			}
			if(listaPersonajes.getPersonaje(i).getRol().equals("MID")) {
				mid ++;
			}
		}
		despliegue += "Top: "+ top + "Jg: "+ jg +  "Mid: "+ mid +  "Adc: "+ adc + "Sup: "+ sup ;
		return despliegue;
	}
	public boolean bloquearJugador(String nombre) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		if(cuenta == null) {
			return false;
		}
		cuenta.setNombre("Bloquado");;
		return true;
	}
	public boolean verificarCuenta(String nombre_cuenta) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre_cuenta);
		if(cuenta == null) {
			return false;
		}
		return true;
	}
	public boolean verificarClave(String nombre, String clave) {
		Cuenta cuenta = listaCuentas.buscarCuenta(nombre);
		if(clave.equals(cuenta.getClave())) {
			return true;
		}
		return false;
	}
	public boolean ingresarEstadistica(String nombre,int cantidad) {
		Personaje personaje = listaPersonajes.buscarPersonaje(nombre);
		int recaudacion = personaje.getRecaudacion();
		personaje.setRecaudacion(recaudacion + cantidad);
		return true;
	}
	public String dataPersonaje() {
		String data = "";
		for(int i = 0; i< listaPersonajes.getCantPersonajes();i++) {
			Personaje personaje = listaPersonajes.getPersonaje(i);
			data+= personaje.getNombre() + "," + personaje.getRol() + "," + personaje.getLps().getCantSkins();
			for(int j = 0;j< personaje.getLps().getCantSkins();j++) {
				data+=","+ personaje.getLps().getSkin(j).getNombre() + "," + personaje.getLps().getSkin(j).getCalidad() + "\n";
			}
		}
		return data;
	}
}	
