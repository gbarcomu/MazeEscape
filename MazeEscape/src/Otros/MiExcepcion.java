package Otros;
/**
 * Implementacion de la clase MiExcepcion.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class MiExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	/**Constructor por defecto de la clase MiExcepcion.<br>
	 * PRE: - <br>
	 * POST: llama al constructor del padre.<br>
	 * COMP: O(1).<br>
	 * */
	public MiExcepcion () {
	
		super();
	}
	/**Constructor parametri<ado de la clase MiExcepcion.<br>
	 * PRE: cadena es un String valido.<br>
	 * POST: llama al constructor del padre.<br>
	 * COMP: O(1).<br>
	 * @param cadena
	 * */
	public MiExcepcion (String cadena) {
		
		super(cadena);
	}
}