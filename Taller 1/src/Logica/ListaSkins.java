package Logica;
import Dominio.*;
public class ListaSkins {
	private Skin [] ls;
	private int maximo;
	private int cantSkins;
	public ListaSkins(int maximo) {
		ls = new Skin[maximo];
    	cantSkins = 0;
    	this.maximo = maximo;
    }
    public int getCantSkins(){
    	return cantSkins;
    }
    public void setCantSkins(int cantSkins) {
    	this.cantSkins = cantSkins;
    }
    public boolean insertarSkin(Skin skin) {
    	if(cantSkins<maximo) {
    		ls[cantSkins] = skin;
    		cantSkins++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Skin getSkin(int i) {
    	if(i >= 0 && i < cantSkins) {
    		return ls[i];
    	}
    	else {
    		return null;
    	}
	}
    public Skin buscarSkin(String nombre) {
    	for(int i = 0;i< this.cantSkins;i++) {
    		if(ls[i].getNombre().equals(nombre)) {
    			return ls[i];
    		}
    	}
    	return null;
	}
}
