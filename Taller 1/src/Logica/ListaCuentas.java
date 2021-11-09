package Logica;
import Dominio.*;
public class ListaCuentas {
	private Cuenta [] lc;
	private int maximo;
	private int cantCuentas;
	public ListaCuentas(int maximo) {
		lc = new Cuenta[maximo];
    	cantCuentas = 0;
    	this.maximo = maximo;
    }
    public int getCantCuentas(){
    	return cantCuentas;
    }
    public void setCantCuentas(int cantCuentas) {
    	this.cantCuentas = cantCuentas;
    }
    public boolean insertarCuenta(Cuenta cuenta) {
    	if(cantCuentas<maximo) {
    		lc[cantCuentas] = cuenta;
    		cantCuentas++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Cuenta getCuenta(int i) {
    	if(i >= 0 && i < cantCuentas) {
    		return lc[i];
    	}
    	else {
    		return null;
    	}
	}
    public Cuenta buscarCuenta(String nombre) {
    	for(int i = 0;i< this.cantCuentas;i++) {
    		if(lc[i].getNombre().equals(nombre)) {
    			return lc[i];
    		}
    	}
    	return null;
	}
}
