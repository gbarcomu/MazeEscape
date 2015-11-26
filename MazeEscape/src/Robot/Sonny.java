/**
* Implementacion de la clase Sonny
* @version 2.0
* @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
* Asignatura Desarrollo de Programas<br/>
* Curso 14/15
* Nombre grupo: IngenierosEnProcesion
* Numero de entrega: EC2
*/
package Robot;

import Movimientos.MovimientoManoIzquierda;


public class Sonny extends RobotRecogedor {
	/** Constructor parametrizado de la clase Sonny.<br>
	 * PRE: nombre es un String valido, marca es un char valido. x, y, turno son enteros validos.<br>
	 * POST: los datos se establecen correctamente. <br>
	 * COMP: O(1)<br>
	 * @param nobre, marca, x, y, turno. */
	public Sonny (String nombre,char marca,int x, int y, int turno) {
		
		super (nombre, x, y, turno);
		movimiento = new MovimientoManoIzquierda();
		super.idRobot = marca;
	}
}