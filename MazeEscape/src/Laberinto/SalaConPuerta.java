package Laberinto;

import java.util.LinkedList;
import Otros.Llave;
/**
 * Implementacion de la clase SalaConPuerta.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class SalaConPuerta extends Sala{
	/** Atributo de tipo puerta que establece la puerta de salida.*/
	private Puerta puertaSalida;
	/** Constructor por defecto de la SalaConPuerta.<br>
	 * PRE: -<br>
	 * POST: crea la puerta de salida.<br>
	 * COMP: O(1)*/
	public SalaConPuerta() {
		
		puertaSalida = new Puerta();
	}
	/** Constructor parametrizado de la SalaConPuerta.<br>
	 * PRE: codigoPuerta es un array valido, numELementos, profundidad, nodosApertura son enteros validos. <br>
	 * POST: la puerta queda configurada. <br<
	 * COMP: O(1).
	 * */
	SalaConPuerta(Llave codigoPuerta[], int numElementos, int profundidad, int nodosApertura) {
		
		puertaSalida = new Puerta();
		configurarPuerta(codigoPuerta, numElementos, profundidad, nodosApertura);
		
	}
	/** Metodo que nos permite probar una llave en la puerta de salida.<br>
	 * PRE: llavePrueba es una Llave valida.<br>
	 * POST: la llave queda probada. <br>
	 * COMP: O(1)<br>
	 * @Param llavePrueba*/
	public void ProbarLlave(Llave llavePrueba) {
		
		puertaSalida.probarLlave(llavePrueba);
	}
	/** Metodo que nos devuelve el estado de la puerta de salida.<br>
	 * PRE: - <br>
	 * POST: devuelve el estado de la puerta. <br>
	 * COMP: O(1)<br>
	 * @return devuelve true si la puerta esta abierta y false en caso contrario.  */
	public boolean esPuertaAbierta() {
		
		return puertaSalida.esPuertaAbierta();
	}
	/** Metodo que nos permite configurar la puerta de salida con los parametros dados.
	 * PRE: codigoPuerta es un array valido, numELementos, profundidad, nodosApertura son enteros validos. <br>
	 * POST: la puerta queda configurada.<br>
	 * COMP: O(1)<br>
	 * @param codigoPuerta, numElementos, profundidad, nodosApertura. 
	 * */
	public void configurarPuerta(Llave codigoPuerta[], int numElementos, int profundidad, int nodosApertura) {
		
		puertaSalida.configurarPuerta(codigoPuerta, numElementos, profundidad, nodosApertura);
	}
	/** Metodo utilizado para mostrar las llaves de la cerradura. <br>
	 * PRE: - <br>
	 * POST: guarda las llaves en una linkedList<br>
	 * COMP: O(1)<br>
	 * @return retorna en una linkedList las llaves de la cerradura
	 * */
	public LinkedList<Llave> mostrarLlavesPuerta() {
		
		return (puertaSalida.mostrarLlavesCerraduraPuerta());
	}
	/** Metodo utilizado para mostrar las llaves probadas. <br>
	 * PRE: - <br>
	 * POST: guarda las llaves en una linkedList<br>
	 * COMP: O(1)<br>
	 * @return retorna en una linkedList las llaves probadas.
	 * */
	public LinkedList<Llave> mostrarLlavesProbadas() {
		
		return (puertaSalida.mostrarLlavesProbadasPuerta());
	}
}
