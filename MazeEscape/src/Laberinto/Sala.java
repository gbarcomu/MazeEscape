package Laberinto;

import java.util.*;

import Otros.ComparaLlave;
import Otros.Llave;
import Robot.Robots;
/**
 * Implementacion de la clase sala.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class Sala {
	/** Cola de robots que almacena los robots que hay en cada sala.*/
	private Queue<Robots> robotsEnSala;
	/** LinkeList de llaves que almacena las llaves que hay en la sala.*/
	private LinkedList<Llave> llavesEnSala;
	/** Atributo de sala.*/
	private int IDSala;
	/**Atributo para idetificar las paredes de las salas a derribar*/
	private int IDDerribo;
	/**Atributo utilizado para saber cuantos robots hay en una sala en cada momento.*/
	private int cuantosRobots;

	/** Constructor por defecto de la clase sala.<br>
	 * PRE:-<br>
	 * POST: La sala queda configurada con la configuracion por defecto. <br>
	 * COMP: O(1)*/
	public Sala () {
		
		IDSala = -1;		
		llavesEnSala = new LinkedList<Llave>();
		robotsEnSala = new LinkedList<Robots>();
		cuantosRobots = 0;
	}
	/** Metodo que incrementa el numero de robots.<br>
	 * PRE: -<br>
	 * POST: el numero de robots queda incrementado<br>
	 * COMP:O(1)*/	
	public void incRobots() {
		
		cuantosRobots++;
	}
	/** Metodo que decrementa el numero de robots.<br>
	 * PRE: -<br>
	 * POST: el numero de robots queda decrementado<br>
	 * COMP:O(1)*/
	public void decRobots() {
		
		cuantosRobots--;
	}
	/** Metodo que devuelve un entero con el numero de robots.<br>
	 * PRE: -<br>
	 * POST: el numero de robots queda almacenado en el entero.<br>
	 * COMP:O(1)<br>
	 * @return int*/
	public int cuantosRob() {
		
		return cuantosRobots;
	}
	/**Metodo que asigna el identificador al atributo.<br>
	 * PRE: id es un entero valido.<br>
	 * POST: El atributo IDDerribo e IDSala toman el valor dado.<br>
	 * COMP: O(1)
	 * 
	 * @param id
	 */
	public void setIdSala (int id) {
		
		IDDerribo = id;
		IDSala = id;
	}
	/** 
	 * Metodo que obtiene el identificador de la sala.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con el identificador de la sala.<br>
	 * COMP: O(1)<br>
	 * @reurn int
	 */
	public int getIdSala() {
		
		return IDSala;
	}
	/** 
	 * Metodo que obtiene el identificador de derribo.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con el identificador de derribo.<br>
	 * COMP: O(1)
	 * @return int
	 */
	public int getIdDerribo() {
		
		return IDDerribo;
	}
	/**Metodo que asigna el identificador al atributo.<br>
	 * PRE: valor es un entero valido.<br>
	 * POST: El atributo IDDerribo toma el valor dado.<br>
	 * COMP: O(1)
	 * 
	 * @param valor
	 */
	public void setIdDerribo(int valor) {
		
		IDDerribo = valor;
	}
	/** Metodo que incrementa el IDDerribo. <br>
	 * PRE: - <br>
	 * POST: el id queda incrementado. 
	 * COMP:O(1)
	 * */
	public void incIdDerribo() {
		
		IDDerribo++;
	}
	
	/** Meodo que almacena un robot en la cola de robots de la sala.<br>
	 * PRE: robot es un Robots valido<br>
	 * POST: el robot queda almacenado.<br>
	 * COMP: O(1)<br>
	 * @param robot*/
	public void robotEntra (Robots robot) {
		
		robotsEnSala.add(robot);
	}
	/** Metodo que nos dice si hay robots en la cola de robots de la sala. <br>
	 * PRE: - <br>
	 * POST: devuelve si hay o no robot en la sala.<br>
	 * COMP:O(1)<br>
	 * @return devuelve true si hay robots y false en caso contrario.*/
	public boolean hayRobot() {
		
		if(robotsEnSala.isEmpty()) {
			
			return false;
		}
		
		else {
			
			return true;			
		}		
	}
	/** Metodo que obtiene el primer robots almacenado en la estructura de datos cola.<br>
	 * PRE:-<br>
	 * POST: devuelve el primer robot. <br>
	 * COMP: O(1)<br>
	 * @return devuelve el primer robot almacenado. */
	public Robots mostrarRobot() {
	
		return robotsEnSala.peek();
	}
	/** Metodo que extrae(desencola) un robots de la cola de robots de la sala.<br>
	 * PRE: - <br>
	 * POST: el robot queda eliminado de la cola. <br>
	 * COMP: O(1)<br>
	 * @return devuelve un robots.*/
	public Robots robotSale () {
		
		Robots robotAux = robotsEnSala.peek();
		robotsEnSala.remove();
		
		return robotAux;
	}

	/** Metodo que devuelve una llave.<br>
	 * PRE: -<br>
	 * POST: la llave queda eliminada. <br>
	 * COMP: O(1)<br>
	 * @return devuelve la llave de la cima de la pila. */
	public Llave recogerLlaveSala() {
		
		return llavesEnSala.poll();
	}
	/** Metodo que almacena una llave en la pila de la sala.<br>
	 * PRE: llave es una Llave valida.<br>
	 * POST: la llave queda almacenada.<br>
	 * COMP:O(1)<br>
	 * @param llave*/
	public void dejarLlaveSala(Llave llave) {
		
		llavesEnSala.add(llave);
		
		Collections.sort(llavesEnSala,new ComparaLlave());   
	}
	/** Metodo que nos dice si hay llaves en la pila o no. <br>
	 * PRE: -<br>
	 * POST: devuelve si hay llaves o no. <br>
	 * COMP: O(1)<br>
	 * @return devuelve true si hay llaves y false en caso contrario.*/
	public boolean llaveroVacio(){
		
		if (llavesEnSala.isEmpty()) {
			
			return true;
		}
		
		else {
			
			return false;
		}
	}
	
}
