
package Laberinto;

import java.util.LinkedList;
import EstrucurasDeDatos.Arbol;
import Otros.Llave;

/**
 * Implementacion de la clase puerta.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class Puerta {
	/** Boleano que indica el estado de la puerta. 
	 * Devuelve verdadero si esta abierta y falso en caso contrario. 
	 */
	private boolean puertaAbierta;
	/** Boleano que indica el estado de la puerta. 
	 * Devuelve verdadero si esta configurada y falso en caso contrario. 
	 */
	private boolean puertaConfigurada;
	/** Entero que nos permite almacenar la profundidad minima del arbol para que se abra la puerta.*/
	private int profundidadApertura;
	/** Entero que nos permite almacenar los nodos minimos que deben quedar en el arbol para que se abra la puerta*/
	private int nodosMinimosApertura;
	/** Arbol binario de busqueda que contine las llaves disponibles para abrir la cerradura.*/
	private Arbol llavesCerradura;
	/** Arbol binario de busqueda que contiene las llaves que vamos probando para abrir la cerradira.*/
	private Arbol llavesProbadas;
	
	/**
	 * Constructor por defecto de la clase Puerta.<br>
	 * PRE: -<br>
	 * POST: Inicializa la Puerta.<br>
	 * COMP: O(1)
	 */
	public Puerta () {
		
		puertaConfigurada = false;		
	}
	/**
	 * Constructor parametrizado de la clase Puerta.<br>
	 * PRE: codigoPuerta es un array de Llaves valido, numElementos es un entero valido.<br>
	 * POST: Inicializa la Puerta.<br>
	 * COMP: O(1)
	 * 
	 * @param codigoPuerta
	 * @param numElementos
	 */
	public Puerta (Llave codigoPuerta[], int numElementos) {
		
		puertaConfigurada = true;
		puertaAbierta = false;
		
		nodosMinimosApertura = 0;
		profundidadApertura = 0;
		
		llavesCerradura = new Arbol();
		llavesProbadas = new Arbol();
		
		algoritmoConfiguracionPuerta(0, numElementos - 1, codigoPuerta);
	}
	/** Metodo encargado de configurar la puerta.<br>
	 * PRE: priero, ultimo son enteros validos, codigoPuerta es un array valido. <br>
	 * POST: La puerta debe quedar configurada
	 * COMP: O(1)
	 * @Param primero, ultimo, codigoPuerta
	 * */
	private void algoritmoConfiguracionPuerta(int primero, int ultimo, Llave codigoPuerta[]) {
		
		int aux = puntoMedio(primero,ultimo);
		
		llavesCerradura.insertar(codigoPuerta[aux]);	
		
		if (primero != ultimo) {
						
			algoritmoConfiguracionPuerta(primero, aux - 1, codigoPuerta);		
			algoritmoConfiguracionPuerta(aux + 1, ultimo, codigoPuerta);
		}	 
	}
	/** Metodo que calcula el punto medio del array para hacer la insercion en el arbol correctamente.<br>
	 * PRE: primero y ultimo deben ser enteros validos<br>
	 * POST: devuelve el putno medio<br>
	 * COMP: O(1)<br>
	 * @return el valor entero de la division. */
	private int puntoMedio (int primero, int ultimo) {
		
		return (ultimo + primero) / 2;
	}
	/** Metodo encargado de hacer la llamada a la configuracion de la puerta. <br>
	 * PRE: codigoPuerta es un array valido, numElementos, profundidad, nodosApertura son enteros validos. <br>
	 * POST: la puerta debe quedar configurada.<br>
	 * COMP: O(1)<br>
	 * @param codigoPuerta es el array que contiene las llaves.<br>
	 * @param numElementos contiene el numero de elementos del array.<br>
	 * @param profundidad indica la profundidad minima de apertura de la puerta.<br>
	 * @param nodosApertura indica los nodos mínimos que deben quedar en el arbol para abrir la puerta.*/
	public void configurarPuerta (Llave codigoPuerta[], int numElementos, int profundidad,
			int nodosApertura) {
		
		puertaConfigurada = true;
		puertaAbierta = false;
		
		setProfundidad(profundidad);
		setNodosMinimos(nodosApertura);
		
		llavesCerradura = new Arbol();
		llavesProbadas = new Arbol();
		
		algoritmoConfiguracionPuerta(0, numElementos - 1, codigoPuerta);	
	}
	/** Metodo encargado de probar las llaves para abrir la puerta. <br>
	 * PRE: llavePrueba es una llave valida.<br>
	 * POST: la llave es probada correctamente.<br>
	 * COMP: O(1)<br>
	 * @return devuelve verdadero si la llave probada es buena y falso en caso contrario.*/
	public boolean probarLlave (Llave llavePrueba) {
		
		boolean llaveBuena = false;
		
		if(!llavesProbadas.pertenece(llavePrueba)) {
			
			if(llavesCerradura.pertenece(llavePrueba)) {
				
				llavesProbadas.insertar(llavePrueba);
				llavesCerradura.borrar(llavePrueba);
				intentarAbrirPuerta ();
				llaveBuena = true;
			}
		}
		
		else {
			
			alarma();
		}
		
		return llaveBuena;
	}
	/** Metodo encargado de abrir la puerta. <br>
	 * PRE: -<br>
	 * POST: cambia el estado de la puerta si se cumplen las condiciones de apertura.<br>
	 * COMP: O(1)*/
	private void intentarAbrirPuerta () {
		
		if (llavesCerradura.profundidadArbol() <= profundidadApertura
				&& llavesCerradura.numeroNodosInterno() <= nodosMinimosApertura) {
			
			puertaAbierta = true;
		}
	}
	/** Metodo que nos devuelve el estado de la puerta.<br>
	 * PRE: -<br>
	 * POST: devuelve el estado de la puerta. <br>
	 * @return devuelve true si la puerta esta configurada y false en caso contrario.*/
	public boolean esPuertaConfigurada () {
		
		return puertaConfigurada;
	}
	/** Metodo que nos devuelve el estado de l apuerta.<br>
	 * PRE: - <br>
	 * POST: devuelve el estado de la puerta.
	 * COMP:O(1)<br>
	 * @return devuelve true si la puerta esta abierta y false en caso contrario. */
	public boolean esPuertaAbierta () {
		
		return puertaAbierta;
	}
	/** Metodo que establece la profundidad de la puerta.<br>
	 * PRE: nuevaProfundiad es un entero valido.<br>
	 * POST: la profundida queda modificada<br>
	 * COMP: O(1)<br>
	 * @rparam nuevaProfundidad */
	public void setProfundidad (int nuevaProfundidad) {
		
		profundidadApertura = nuevaProfundidad;
	}
	/** 
	 * Metodo que obtiene la profundidad de apertura de la puerta.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con la profundidad de apertura de la puerta.<br>
	 * COMP: O(1)
	 * @param profundidadApertura
	 */
	public int getProfundidad () {
		
		return profundidadApertura;
	}
	/**Metodo que asigna el el valor al atributo.<br>
	 * PRE: nuevaCantidadNodos es un entero valido.<br>
	 * POST: El atributo nodosMinimosApertura toma el valor dado.<br>
	 * COMP: O(1)
	 * 
	 * @param nodosMinimosAPertura
	 */
	public void setNodosMinimos (int nuevaCantidadNodos) {
		
		nodosMinimosApertura = nuevaCantidadNodos;
	}
	/** 
	 * Metodo que obtiene los nodos minimos para abrir la puerta.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con los nodos minimos de apertura.<br>
	 * COMP: O(1)
	 * @param nodosMinimosAPertura
	 */
	public int getNodosMinimos () {
		
		return nodosMinimosApertura;
	}
	/** Metodo encargado de dar un aviso en caso de que se pruebe dos veces la misma llave.<br>
	 * PRE: - <br>
	 * POST: lanza un mensaje si la llave es repetida. <br>
	 * COMP: O(1)*/
	private void alarma () {
		
		System.out.println("Alarma, se ha intentado probar la misma llave des veces.");
	}
	/** Metodo que nos devuelve la profundidad y los nodos minimos estableciods para la apertura de la puerta<br>
	 * PRE: - <br>
	 * POST: visualiza un mensaje con dichos valores. <br>
	 * COMP: O(1)
	 * */
	public void profundidadYnNodosMinimos() {
		
		System.out.println("El numero de nodos minimo es:" + llavesCerradura.numeroNodosInterno());
		System.out.println("La profundidad del arbol es: " + llavesCerradura.profundidadArbol());
	}
	/** Metodo utilizado para mostrar las llaves de la cerradura. <br>
	 * PRE: - <br>
	 * POST: guarda las llaves en una linkedList<br>
	 * COMP: O(1)<br>
	 * @return retorna en una linkedList las llaves de la cerradura
	 * */
	public LinkedList<Llave> mostrarLlavesCerraduraPuerta() {
		
		return llavesCerradura.wrapper();
	}
	/** Metodo utilizado para mostrar las llaves probadas. <br>
	 * PRE: - <br>
	 * POST: guarda las llaves en una linkedList<br>
	 * COMP: O(1)<br>
	 * @return retorna en una linkedList las llaves probadas.
	 * */
	public LinkedList<Llave> mostrarLlavesProbadasPuerta() {
		
		return llavesProbadas.wrapper();
	}
}


