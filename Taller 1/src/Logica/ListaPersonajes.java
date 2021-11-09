package Logica;
import Dominio.*;
public class ListaPersonajes {
	private Personaje [] lp;
	private int maximo;
	private int cantPersonajes;
	public ListaPersonajes(int maximo) {
		lp = new Personaje[maximo];
    	cantPersonajes = 0;
    	this.maximo = maximo;
    }
    public int getCantPersonajes(){
    	return cantPersonajes;
    }
    public void setCantPersonajes(int cantPersonajes) {
    	this.cantPersonajes = cantPersonajes;
    }
    public boolean insertarPersonaje(Personaje personaje) {
    	if(cantPersonajes<maximo) {
    		lp[cantPersonajes] = personaje;
    		cantPersonajes++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Personaje getPersonaje(int i) {
    	if(i >= 0 && i < cantPersonajes) {
    		return lp[i];
    	}
    	else {
    		return null;
    	}
	}
    public Personaje buscarPersonaje(String nombre) {
    	for(int i = 0;i< this.cantPersonajes;i++) {
    		if(lp[i].getNombre().equals(nombre)) {
    			return lp[i];
    		}
    	}
    	return null;
	}
}
