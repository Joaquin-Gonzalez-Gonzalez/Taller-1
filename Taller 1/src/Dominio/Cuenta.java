package Dominio;
import Logica.*;
public class Cuenta {
	private String nombre;
	private String clave;
	private String nick;
	private int nivel;
	private int rp;
	private String region;
	boolean estado;
	private int recaudacion;
	ListaPersonajes lcp;
	public Cuenta (String nombre, String clave,String nick,int nivel, int rp,String region){
		this.nombre = nombre;
		this.clave = clave;
		this.nick = nick;
		this.nivel = nivel;
		this.rp = rp;
		this.region = region;
		this.estado = true;
		this.recaudacion = 0;
		this.lcp = new ListaPersonajes(155);
	}
	public int getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(int recaudacion) {
		this.recaudacion = recaudacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getRp() {
		return rp;
	}
	public void setRp(int rp) {
		this.rp = rp;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public ListaPersonajes getLcp() {
		return lcp;
	}
	public void setLcp(ListaPersonajes lcp) {
		this.lcp = lcp;
	}
	
}
