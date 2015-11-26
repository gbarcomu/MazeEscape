package Laberinto;
/**
 * Implementacion de la clase pared.<br>
 * @version 5.0<br>
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte<br>
 * Asignatura Desarrollo de Programas<br>
 * Curso 14/15<br>
 * Nombre grupo: IngenierosEnProcesion<br>
 * Numero de entrega: Enero<br>
 */
public class Pared {
	/** Identificdor de la sala1(origen)*/
	private int sala1;
	/**Ifdentificador de la sala2(destino)*/
	private int sala2;
	/**
	 * Constructor por defecto de la clase Pared.<br>
	 * PRE: -<br>
	 * POST: Inicializa la Pared.<br>
	 * COMP: O(1)
	 */
	Pared (int sala1, int sala2) {
		
		this.sala1 = sala1;
		this.sala2 = sala2;
	}
	/** 
	 * Metodo que obtiene la posicion de una sala.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con identificador de la sala.<br>
	 * COMP: O(1)
	 * @param sala1
	 */
	public int getSala1 () {
		
		return sala1;
	}
	/** 
	 * Metodo que obtiene la posicion de una sala.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con identificador de la sala.<br>
	 * COMP: O(1)
	 * @param sala2
	 */
	public int getSala2 () {
		
		return sala2;
	}
	/**Metodo que se utiliza para visualizar dos salas.<br>
	 * PRE: - <br>
	 * POST: Debe  mostrar las dos salas. <br>
	 * COMP: O(1)*/
	public void mostrar() {
		
		System.out.println(sala1 + " " + sala2);
	}
}
