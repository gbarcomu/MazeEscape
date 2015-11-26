/**
 * Implementacion de la clase Bender.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
package Robot;

import Movimientos.MovimientoProfundidad;


public class Bender extends RobotRecogedor {
	/** Constructor parametrizado de la clase Bender.<br>
	 * PRE: nombre es un String valido, marca es un char valido. x, y, turno son enteros validos.<br>
	 * POST: los datos se establecen correctamente. <br>
	 * COMP: O(1)<br>
	 * @param nobre, marca, x, y, turno. */
	public Bender (String nombre,char marca, int x, int y, int turno) {
		
		super (nombre, x, y, turno);
		movimiento = new MovimientoProfundidad();
		super.idRobot = marca;
	}
}
