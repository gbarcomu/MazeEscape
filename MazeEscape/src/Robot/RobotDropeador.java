/**
 * Implementacion de la clase RobotDropeador.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
package Robot;

import Laberinto.Laberinto;
import Laberinto.Sala;
import Laberinto.SalaConPuerta;
import Movimientos.MovimientoCuatroEsquinas;
import Otros.Llave;
//import Otros.MovimientoRobots;


public class RobotDropeador extends Robots{
	/** Construcor parametrizado de la clase RobotDroeador.
	 * PRE: nombre es un String valido, marca es un char valido, llavero inicial es un vector de llaves valido, x, y, turno son enteros validos.<br>
	 * POST: El objeto se debe crear. <br>
	 * COMP: O(1)< br>
	 * @param nombre, marca, llaveroInicia, x, y, turno */
	public RobotDropeador (String nombre, char marca, Llave llaveroInicial[],int x, int y, int turno) {
		 
		super(nombre, x, y, turno);
		
		movimiento = new MovimientoCuatroEsquinas();

		for (int i = 0; i < llaveroInicial.length; i++) {

			guardarLlave(llaveroInicial[i]);
		}
			
		super.idRobot = marca;
	}
	/** Metodo que nos permite volver a configurar la puerta(es la funcion de este tipo de robots.<br>
	 * PRE: laberinto debe ser un Laberinto valido<br>
	 * POST: la puerta debe quedar reconfigurada.<br>
	 * COMP: O(1)<br>
	 * @param laberinto */
	protected void interactuarPuerta (Laberinto laberinto) {
		
		Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
		
		if (salaAux instanceof SalaConPuerta) {
			
			Llave llaveroConfiguracion[] = laberinto.llaveroGenerico();
			
			int auxProfu = laberinto.getProfundidadPuerta();
			int auxNodos = laberinto.getNodosPuerta();
			
			((SalaConPuerta) salaAux).configurarPuerta(llaveroConfiguracion, 15, auxProfu, auxNodos);
			
			System.out.println ("Un robot asimo ha reconfigurado la puerta");
			
		}
		
		super.mover(laberinto);
	}
	/** Metodo que nos permite dejar llaves en las salas del laberinto.(funcion de este tipo de robots)<br>
	 * PRE: laberinto debe ser un Laberinto valido.<br>
	 * POST: las llaves deben quedar en las salas. 
	 * COMP:O(1)<br>
	 * @param laberinto */
	protected void interactuarLlave (Laberinto laberinto) {
		
		Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
		
		if (salaAux.getIdSala() % 2 == 0){
			
			if (!llavero.isEmpty()) {

				salaAux.dejarLlaveSala(llavero.pop());
			}
		}				
	}
	
	
}
