package Dominio;
import Logica.*;
public class Personaje {
	private String nombre;
	private String rol;
	private int precio;
	private int recaudacion;
	private ListaSkins lps;
	public Personaje(String nombre,String rol) {
		this.nombre = nombre;
		this.rol = rol;
		this.precio = 975;
		this.recaudacion = 0;
		this.lps = new ListaSkins(100);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(int recaudacion) {
		this.recaudacion = recaudacion;
	}
	public ListaSkins getLps() {
		return lps;
	}
	public void setLps(ListaSkins lps) {
		this.lps = lps;
	}
	
}
