/**
 * Implementacion de la clase Robots.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
package Robot;
import java.util.Stack;

import Laberinto.Laberinto;
import Laberinto.Sala;
import Movimientos.InterfazMovimiento;
import Otros.Direcciones;
import Otros.Llave;
//import Otros.MovimientoRobots;


public abstract class Robots {
	/** Atributo para establecer el nombre del robots.*/
	protected String nombreRobot;
	/** Atributo para establecer el identificador del robot.*/
	protected char idRobot;
	/** Pila dedicada a almacenar las llaves que tiene cada robot.*/
	protected Stack<Llave> llavero;
	/** Entero que establece la posicionX(filas) de la matriz.*/
	protected int posicionX;
	/** Entero que establece la posicionY(columnas) de la matriz.*/
	protected int posicionY;
	/** Entero que almacena el turno actual de cada robot..*/
	private int turnoRobot;
	/** Direcciones que indica la siguiente direccion.*/
	protected Direcciones siguienteMovimiento;
	/**Interfaz de tipo InterfazMovimiento.*/
	protected InterfazMovimiento movimiento;
	/** Constructor por defecto de la clase robots.<br>
	 * PRE: - <br>
	 * POST: el robot se crea con los valores por defecto.<br>
	 * COMP: O(1)<br>
	 * */	
	Robots () {
		
		turnoRobot = 0;
		nombreRobot = "";
		idRobot = '0';
		posicionX = 0;
		posicionY = 0;
		llavero = new Stack<Llave> ();
		Laberinto.getInstancia().devolverSala(0, 0).incRobots();
		
	}
	/** Constructor parametrizado de la clase robots.<br>
	 * PRE: nombre es un String valido, x,y, turno son enteros validos.<br>
	 * POST: el robot se crea con los valores establecidos. <br>
	 * COMP:O(1) <br>
	 * @param nombre, x, y, turno.
	 * */
	Robots (String nombre, int x, int y, int turno) {
		
		nombreRobot = nombre;
		posicionX = x;
		posicionY = y;
		llavero = new Stack<Llave> ();	
		turnoRobot = turno;
		
		Laberinto.getInstancia().devolverSala(x, y).incRobots();
	}

	/** /** Metodo mediante el que indicamos el siguiente movimiento al robot. <br>
	 * PRE: - <br>
	 * POST: el movimiento debe quedar indicado. <br>
	 * COMP: O(1) 
	 * */
	protected  void siguienteMovimiento (){
		
		siguienteMovimiento = movimiento.siguienteMovimiento();
	}

	/** Metodo que nos permite interactuar con la puerta.<br>
	 * PRE: laberinto debe ser un Laberinto valido<br>
	 * POST: la puerta debe recibir la accion en funcion del tipo de robots.<br>
	 * COMP: O(1)<br>
	 * @param laberinto */
	protected  abstract void interactuarPuerta (Laberinto laberinto);
	/** Metodo que nos permite mover los robots.<br>
	 * PRE: laberinto debe ser un Laberinto valido. <br>
	 * POST: el robot debe moverse por el laberinto. <br>
	 * COMP: O(1)<br>
	 * @param laberinto*/
	protected void mover(Laberinto laberinto) {
		
		Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
		siguienteMovimiento();
		moverRobot(laberinto, salaAux);
	}
	/** Metodo que nos permite interactuar con las llaves.<br>
	 * PRE: laberinto debe ser un Laberinto valido.<br>
	 * POST: las llaves deben recibir la accion en funcion del tipo de robots. <br>
	 * COMP:O(1)<br>
	 * @param laberinto */
	protected abstract void interactuarLlave (Laberinto laberinto);
	/** Metodo que devuelve el nombre del robot.<br>
	 * PRE: - <br>
	 * POST: devuelve el nombre del robot. <br>
	 * COMP: O(1)<br>
	 * @return devuelve en un String el nombre del robot.*/
	public String getNombre() {
		
		return nombreRobot;
	}
	/** Metodo que devuelve el identificador del robot.<br>
	 * PRE: - <br>
	 * POST: devuelve el identificador del robot. <br>
	 * COMP: O(1)<br>
	 * @return devuelve en un char el identificador del robot.*/
	public char getIdRobot() {
		
		return idRobot;
	}
	
	/** Metodo que nos permite establecer el turno de cada robot.<br>
	 * PRE: turno debe ser un entero valido. <br>
	 * POST: el turno debe ser establecido correctamente. <br>
	 * COMP:O(1)< br>
	 * @param turno*/
	public void setTurnoRobot(int turno) {
		
		turnoRobot = turno;
	}
	/** Metodo que devuelve el turno del robot.<br>
	 * PRE: - <br>
	 * POST: devuelve el turno del robot. <br>
	 * COMP: O(1)<br>
	 * @return devuelve en un int el turno del robot*/
	public int getTurnoRobot() {
		
		return turnoRobot;
	}
	/** Metodo que nos permite incrementar el turno de cada robot.<br>
	 * PRE:- <br>
	 * POST: el turno dene ser incrementado. <br>
	 * COMP: O(1)<br>
	 * */
	public void incrementarTurno() {
		
		turnoRobot++;
	}
	/** Boleano que nos devuelve del llavero.<br>
	 * PRE: - <br>
	 * POST: debe devolver el estado del llavero. <br>
	 * COMP: O(1)<br>
	 * @return devuelve verdadero si el llavero esta vacio, falso en caso contrario.*/
	public boolean llaverVacio () {
		
		return llavero.isEmpty();
	}
	/** Metodo que nos permite que cada robot interactue con la puerta y con la llave.<br>
	 * PRE: laberinto debe ser un Laberinto valido. <br>
	 * POST: el robot debe interactuar con la puerta o moverse. <br>
	 * COMP: O(1)<br>
	 * @param laberinto*/
	public void robotHaceSusCosas(Laberinto laberinto) {

		if (laberinto.getTurno() == turnoRobot-1) {
			
			interactuarPuerta (laberinto);
			interactuarLlave (laberinto);
			turnoRobot++;
		}
		
		else {

			Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
			salaAux.robotEntra(salaAux.robotSale());			
		}
	}
	/** Metodo que nos devuelve una posicion concreta del laberinto.<br>
	 * PRE: laberinto debe ser un Laberinto valido. <br>
	 * POST: debe devolver una posicion. <br>
	 * COMP: O(1)<br>
	 * @param laberinto*/
	public int posicionToId(Laberinto laberinto) {
		
		return posicionY * laberinto.getMaxColumnas() + posicionX;
	}
	/** Metodo encargado de comprobar que el robot se puede mover y de ser asi moverlo.<br>
	 * PRE: laberinto debe ser un Laberinto valido, sala debe ser una Sala valida.<br>
	 * POST: el robot debe moverse por el laberinto. <br>
	 * COMP: O(1).<br>
	 * @param laberinto, sala.*/
	private void moverRobot(Laberinto laberinto, Sala sala) {
		
		switch (siguienteMovimiento) {
					
			case N:
			
				if(posicionX != 0) {
							
					posicionX -= 1;
				}
						
				break;
						
			case E:
						
				if(posicionY != laberinto.getMaxColumnas() -1) {
							
					posicionY += 1;
				}
						
				break;
						
			case S:
						
				if(posicionX != laberinto.getMaxFilas() -1) {
							
					posicionX += 1;
				}
						
				break;
						
			case O:
				
				if(posicionY != 0) {
							
					posicionY -= 1;
				}
					
				break;	
			}
		Sala salaAux;
		salaAux = laberinto.devolverSala(posicionX, posicionY);
		salaAux.robotEntra(sala.robotSale());
		sala.decRobots();
		salaAux.incRobots();
	}
	/** Metodo que nos permite apilar una llave en el llavero del robot.<br>
	 * PRE: llave debe ser una Llave valida. <br>
	 * POST: la llave debe quedar almacenada en la pila de llaves del robot. <br>
	 * COMP: O(1)<br>
	 * @param llave*/
	public void guardarLlave(Llave llave){
		
		llavero.push(llave);
	}
	
	/** Metodo que nos permite desapilar una llave del llavero del robot.<br>
	 * PRE: - <br>
	 * POST: debe devolver una llave. <br>
	 * COMP: O(1)<br>
	 * @return Llave*/
	public Llave sacarLlave (){
		
		return llavero.pop();
	}	
}

