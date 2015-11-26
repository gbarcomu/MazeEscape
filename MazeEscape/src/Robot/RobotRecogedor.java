
package Robot;
import Laberinto.Laberinto;
import Laberinto.Sala;
import Laberinto.SalaConPuerta;
import Otros.Llave;
/**
 * Implementacion de la clase robotRecogedor.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class RobotRecogedor extends Robots {
	/** Constructor parametrizado de la clase RobotRecogedor.<br>
	 * PRE: nombre es un String valido, marca es un char valido. x, y, turno son enteros validos.<br>
	 * POST: los datos se establecen correctamente. <br>
	 * COMP: O(1)<br>
	 * @param nobre, marca, x, y, turno. */
	public RobotRecogedor (String nombre, int x, int y, int  turno) {
	
		super(nombre, x, y, turno);
	}
	/** Metodo que nos permite intentar abrir la puerta.(funcion de este tipo de robot)<br>
	 * PRE: laberinto es un Laberinto valido. <br>
	 * POST: el robot debe interactuar con la puerta. <br>
	 * COMP: O(1)<br>
	 * @Param laberinto */
	protected void interactuarPuerta (Laberinto laberinto) {		

		Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
		
		if (salaAux instanceof SalaConPuerta) {
		
			if(!llaverVacio()) {

				Llave llaveAux = llavero.lastElement();					
				((SalaConPuerta) salaAux).ProbarLlave(llaveAux);									
				llavero.pop();
			}
			
			if(((SalaConPuerta) salaAux).esPuertaAbierta()) {
				
				laberinto.devovlerSalaGanadores().robotEntra(salaAux.robotSale());
				salaAux.decRobots();
			}
			
			else {
				
				salaAux.robotEntra(salaAux.robotSale());
			}			
		}
		
		else {
			
			mover (laberinto);
		}
	}
	/** Metodo que nos permite recoger una llave de las salas del laberinto.(funcion de este tipo de robot) <br>
	 * PRE: laberinto es un Laberinto valido. <br>
	 * POST: el robot debe probar una llave en la cerradura. <br>
	 * COMP: O(1)<br>
	 * @param laberinto*/
	protected void interactuarLlave (Laberinto laberinto) {
			
		Sala salaAux = laberinto.devolverSala(posicionX, posicionY);
		
		if (!salaAux.llaveroVacio()) {
			
			Llave llaveAux;
			
			llaveAux = salaAux.recogerLlaveSala();
			llavero.push(llaveAux);

		}
	}	
}
