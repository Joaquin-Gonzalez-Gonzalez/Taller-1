package Logica;

public interface SistemaUCNLol {
	/**
	 * this function adds a account to the system
	 * @param nombre
	 * @param clave
	 * @param nick
	 * @param nivel
	 * @param rp
	 * @param region
	 * @return
	 */
	public boolean ingresarCuenta(String nombre,String clave,String nick, int nivel, int rp,String region);
	/**
	 * this function adds a character to the system
	 * @param nombre
	 * @param rol
	 * @return
	 */
	public boolean ingresarPersonaje(String nombre,String rol);
	/**
	 * Adds a skin to the system
	 * @param nombre
	 * @param calidad
	 * @param precio
	 * @return
	 */
	public boolean ingresarSkin(String nombre,String calidad, int precio);
	/**
	 * associate a skin to the character
	 * @param nombre_personaje
	 * @param nombre_skin
	 * @return
	 */
	public boolean AsociarSkinPersonaje(String nombre_personaje, String nombre_skin);
	/**
	 * Associate a character to the player
	 * @param nombre_cuenta
	 * @param nombre_personaje
	 * @return
	 */
	public boolean AsociarPersonajeJugador(String nombre_cuenta, String nombre_personaje);
	/**
	 * Associate a skin to the character of a player
	 * @param nombre_cuenta
	 * @param nombre_personaje
	 * @param nombre_skin
	 * @return
	 */
	public boolean AsociarSkinPersonajeJugador(String nombre_cuenta,String nombre_personaje, String nombre_skin);
	/**
	 * The player can buy a skin
	 * @param nombre_personaje
	 * @param nombre_cuenta
	 * @return
	 */
	public boolean ComprarSkin(String nombre_personaje, String nombre_cuenta);
	/**
	 * the player can buy a character
	 * @param nombre_personaje
	 * @param nombre_cuenta
	 * @return
	 */
	public boolean ComprarPersonaje(String nombre_personaje,String nombre_cuenta);
	/**
	 * this function display all the skins that the player dont have
	 * @param nombre
	 * @return
	 */
	public String SkinsDisponibles(String nombre);
	/**
	 * this function display the inventory of the player
	 * @param nombre
	 * @return
	 */
	public String MostrarInventario(String nombre);
	/**
	 * The player can buy rp
	 * @param nombre
	 * @param cantidad
	 * @return
	 */
	public boolean RecargarRp(String nombre,int cantidad);
	/**
	 * this function displat the data of the account
	 * @param nombre
	 * @return
	 */
	public String MostrarDatosCuenta(String nombre);
	/**
	 * this function hidden a part of the pass
	 * @param clave
	 * @return
	 */
	public String OcultarClave(String clave);
	/**
	 * the player can change their pass
	 */
	public boolean cambiarClave(String claveNueva, String nombre);
	/**
	 * this function return all the sales by role
	 * @return
	 */
	public String recaudacionVentasPorRol();
	/**
	 * this function returns all the sales by region
	 * @return
	 */
	public String RecaudacionVentasPorRegion();
	/**
	 * this function returns all the sales by characters
	 * @return
	 */
	public String RecaudacionPersonajes();
	/**
	 * this function returns the number of characters per role
	 * @return
	 */
	public String CantidadPorRol();
	/**
	 * this function blocks a player
	 * @param nombre
	 * @return
	 */
	public boolean bloquearJugador(String nombre);
	/**
	 * this function verify the account
	 * @param nombre_cuenta
	 * @return
	 */
	public boolean verificarCuenta(String nombre_cuenta);
	/**
	 * this functio verificate the pass of the account
	 * @param nombre
	 * @param clave
	 * @return
	 */
	public boolean verificarClave(String nombre, String clave);
	/**
	 * this function add the stadistics of the characters
	 * @param nombre
	 * @param cantidad
	 * @return
	 */
	public boolean ingresarEstadistica(String nombre,int cantidad);
}
