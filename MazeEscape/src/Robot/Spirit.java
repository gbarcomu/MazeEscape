/**
* Implementacion de la clase Spirit
* @version 2.0
* @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
* Asignatura Desarrollo de Programas<br/>
* Curso 14/15
* Nombre grupo: IngenierosEnProcesion
* Numero de entrega: EC2
*/
package Robot;

import Movimientos.MovimientoManoDerecha;


public class Spirit extends RobotRecogedor {
	/** Constructor parametrizado de la clase Spirit.<br>
	 * PRE: nombre es un String valido, marca es un char valido. x, y, turno son enteros validos.<br>
	 * POST: los datos se establecen correctamente. <br>
	 * COMP: O(1)<br>
	 * @param nobre, marca, x, y, turno. */
	public Spirit (String nombre, char marca, int x, int y, int turno) {
		
		super (nombre, x, y, turno);
		movimiento = new MovimientoManoDerecha();
		super.idRobot = marca;
	}
}
