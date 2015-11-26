package Otros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import EstrucurasDeDatos.Grafo;
import Laberinto.Laberinto;
/**
 * Implementacion de la clase MovimientoRobots.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
//public class MovimientoRobots {
//	/** Cola utilizada para almacenar las direcciones.*/
//	private Queue<Direcciones> colaDir;
//	/**Instancia de la clase laberinto.*/
//	private Laberinto lab;
//	/**Instancia de la clase gafo.*/
//	private Grafo grafo;
//	/** ArrayList utilizado para almacenar el mejor camino que toma el robot.*/
//	private ArrayList<Integer> mejorCamino;
//	/** Atributo saber cual es la siguiente esquina hacia la que tiene que ir el robot Asimo.*/
//	int esquina;
//	/**Constructor por defecto de la calse MovimientoRobots.<br>
//	 * PRE: - <br>
//	 * POST: El movimiento debe ser creado correctamente. <br>
//	 * COMP: O(1)*/
//	public MovimientoRobots() {
//		
//		colaDir = new LinkedList<Direcciones>();
//		lab = Laberinto.getInstancia();
//		grafo = lab.getGrafo();
//		mejorCamino = new ArrayList<Integer>();
//		
//		esquina = 2; //Esquina NE
//	}
//	/**Constructor parametrizado de la clase MovimientoRObots.<br>
//	 * PRE: - <br>
//	 * POST: El movimiento debe quedar inicializado con los parametros dados.<br>
//	 * COMP: O(2^n)<br>
//	 * @param tipo*/
//	public MovimientoRobots(int tipo) {
//		
//		colaDir = new LinkedList<Direcciones>();
//		lab = Laberinto.getInstancia();
//		grafo = lab.getGrafo();
//		mejorCamino = new ArrayList<Integer>();
//		
//		esquina = 2; //Esquina NE
//		
//		switch (tipo) {
//		
//		case 1:
//			
//			wrapper(0);
//			break;
//			
//		case 2:
//			
//			calcularRutaManoIzquierda(0);
//			break;
//			
//		case 3:
//			
//			calcularRutaManoDerecha(lab.matrizAVector(lab.getMaxFilas()-1, 0));
//			break;
//			
//		case 4:
//			
//			calcularRutaMinima(lab.devolverSalaConPuerta().getIdSala(), lab.matrizAVector(0, lab.getMaxColumnas()-1));
//			break;
//		}		
//	}
//	/**Metodo que dada una ruta por los id de las salas que pasa devuelve en la cola la ruta convertida en direcciones. <br>
//	 * PRE: - <br>
//	 * POST: colaDir se llena con las direcciones del recorrido. <br>
//	 * COMP: O(n)<br> 
//	 * */
//	public void IDADirecciones () {
//		
//		for (int i = 1; i < mejorCamino.size(); i++) {
//
//			if((mejorCamino.get(i) - mejorCamino.get(i - 1)) > 0) {
//				
//				if((mejorCamino.get(i) - mejorCamino.get(i - 1)) == 1) {
//					colaDir.add(Direcciones.E);
//				}				
//				else {
//					colaDir.add(Direcciones.S);
//				}
//			}			
//			else {
//				
//				if((mejorCamino.get(i) - mejorCamino.get(i - 1)) == -1) {
//					colaDir.add(Direcciones.O);
//				}
//				
//				else {
//					colaDir.add(Direcciones.N);
//				}
//			}
//		}
//	}
//	/**Metodo que calcula ruta mas corta entre dos puntos. <br>
//	 * PRE: pos, posFin son enteros validos. <br>
//	 * POST: se guarda en la cola las direcciones que marca la ruta minima. <br>
//	 * COMP: O(n)<br>
//	 * @param pos, posFin.*/
//	public void calcularRutaMinima(int pos, int posFin) {
//
//		while (posFin != pos) {
//			
//			int aux;
//			aux = grafo.siguiente(pos, posFin); 
//
//			if((aux - pos) > 0) {
//				
//				if((aux - pos) == 1) {
//					
//					colaDir.add(Direcciones.E);
//				}				
//				else {
//					
//					colaDir.add(Direcciones.S);
//				}
//			}			
//			else {
//				
//				if((aux - pos) == -1) {
//					
//					colaDir.add(Direcciones.O);
//				}
//				
//				else {
//					
//					colaDir.add(Direcciones.N);
//				}
//			}
//			
//			pos = aux;
//			 
//		}
//		
//		
//	}
//	/** Metodo que calcula la ruta entre dos puntos dirigiendose hacia la derecha siempre que sea posible. <br>
//	 * PRE: pos es un entero valido. <br>
//	 * POST: se guarda en la cola la ruta calculada. <br>
//	 * COMP: O(n). <br>
//	 * @param pos*/
//	public void calcularRutaManoDerecha(int pos) {
//		
//		int idPuerta = lab.devolverSalaConPuerta().getIdSala();
//		int contador = 0;
//		Direcciones anterior = Direcciones.E;
//		Direcciones siguiente = Direcciones.S;
//		
//		while (idPuerta != pos && contador <= 50) {
//			
//			switch (anterior) {
//			
//			case S:
//				
//				if(grafo.adyacente(pos, pos - 1)){
//					
//					siguiente = Direcciones.O;
//					anterior = Direcciones.E;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos--;
//				}
//				
//				else {
//					
//					anterior = Direcciones.O;
//				}
//				
//				
//				break;
//				
//			case E:
//				
//				if(grafo.adyacente(pos, pos + lab.getMaxColumnas())){
//					
//					siguiente = Direcciones.S;
//					anterior = Direcciones.N;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos += lab.getMaxColumnas();
//				}
//				
//				else {
//					
//					anterior = Direcciones.S;
//				}
//				
//				break;
//				
//			case N:
//				
//				if(grafo.adyacente(pos, pos + 1)){
//					
//					siguiente = Direcciones.E;
//					anterior = Direcciones.O;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos++;
//				}
//				
//				else {
//					
//					anterior = Direcciones.E;
//				}
//				
//				break;
//				
//			case O:
//				
//				if(grafo.adyacente(pos, pos - lab.getMaxColumnas())){
//					
//					siguiente = Direcciones.N;
//					anterior = Direcciones.S;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos -= lab.getMaxColumnas();
//				}
//				
//				else {
//					
//					anterior = Direcciones.N;
//				}
//				
//				break;
//			
//			}
//		}	
//	}
//	/** Metodo que calcula la ruta entre dos puntos dirigiendose hacia la izquierda siempre que sea posible. <br>
//	 * PRE: pos es un entero valido. <br>
//	 * POST: se guarda en la cola la ruta calculada. <br>
//	 * COMP: O(n). <br>
//	 * @param pos*/
//	public void calcularRutaManoIzquierda(int pos) {
//		
//		int idPuerta = lab.devolverSalaConPuerta().getIdSala();
//		int contador = 0;
//		Direcciones anterior = Direcciones.N;
//		Direcciones siguiente = Direcciones.O;
//		
//		while (idPuerta != pos && contador <= 50) {
//			
//			switch (anterior) {
//			
//			case S:
//				
//				if(grafo.adyacente(pos, pos + 1)){
//					
//					siguiente = Direcciones.E;
//					anterior = Direcciones.O;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos++;
//				}
//				
//				else {
//					
//					anterior = Direcciones.E;
//				}
//				
//				
//				break;
//				
//			case E:
//				
//				if(grafo.adyacente(pos, pos - lab.getMaxColumnas())){
//					
//					siguiente = Direcciones.N;
//					anterior = Direcciones.S;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos -= lab.getMaxColumnas();
//				}
//				
//				else {
//					
//					anterior = Direcciones.N;
//				}
//				
//				break;
//				
//			case N:
//				
//				if(grafo.adyacente(pos, pos - 1)){
//					
//					siguiente = Direcciones.O;
//					anterior = Direcciones.E;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos--;
//				}
//				
//				else {
//					
//					anterior = Direcciones.O;
//				}
//				
//				break;
//				
//			case O:
//				
//				if(grafo.adyacente(pos, pos + lab.getMaxColumnas())){
//					
//					siguiente = Direcciones.S;
//					anterior = Direcciones.N;
//					
//					colaDir.add(siguiente);
//					contador++;
//					pos += lab.getMaxColumnas();
//				}
//				
//				else {
//					
//					anterior = Direcciones.S;
//				}
//				
//				break;
//			
//			}
//		}	
//	}	
//	/**Metodo que prepara los parametros para hacer una llamada recursiva a ruta profundidad. <br>
//	 * PRE: cursor es un entero valido. <br>
//	 * POST: guarda las direcciones correspondientes del recorrido en profundidad. <br>
//	 * COMP: O(2^n)<br>
//	 * @param cursor
//	 * */
//	public void wrapper (int cursor) {
//		
//		ArrayList<Integer> historicoPaso = new ArrayList<Integer>();
//		boolean nodosVisitados[] = new boolean[lab.getMaxColumnas() * lab.getMaxFilas()];
//		
//		for (int i = 0; i < lab.getMaxFilas() * lab.getMaxColumnas(); i++) {
//			
//			nodosVisitados[i] = false;
//		}
//		
//		int idFin = lab.devolverSalaConPuerta().getIdSala();
//		
//		nodosVisitados[0] = true;
//		historicoPaso.add(0); 
//		
//		calcularRutaProfundidad(nodosVisitados,historicoPaso,idFin,1,cursor);
//		
//		IDADirecciones();
//	}
//	/**Metodo auxiliar para guardar el mejor camino en el Backtracking. <br>
//	 * PRE; historicoPaso es un arrayList de enteros valido. <br>
//	 * POST: almacena el mejor camino. <br>
//	 * COMP: O(n)
//	 * @param historicoPaso*/
//	private void swapArrayList (ArrayList<Integer> historicoPaso) {
//		
//		mejorCamino.clear();
//		
//		for (int i = 0; i < historicoPaso.size(); i++) {
//			
//			mejorCamino.add(historicoPaso.get(i));
//		}
//	}
//	/**Metodo que calcula explorando todas las rutas posibles entre dos puntos. <br>
//	 * PRE: nosdisvisitados es un vextor de boleanos valido, historicoPaso es un arrayList valido, idFin, etapa,cursor son enteros validos. <br>
//	 * POST: se almacena el mejor camino de todos los posibles.<br>
//	 * COMP: O(2^n)<br>
//	 * @param nodosVisitados, historicoPaso, idFin, etapa, cursor. <br>
//	 * */
//	public void calcularRutaProfundidad(boolean[] nodosVisitados, ArrayList<Integer> historicoPaso, int idFin, int etapa, int cursor) {
//
//		if (cursor == idFin) {
//			boolean distinto = false;
//			int i = 0;
//				
//			if (mejorCamino.size() == 0) {
//				
//				swapArrayList(historicoPaso);
//			}
//			
//			else {
//				
//				while (i < historicoPaso.size() && i < mejorCamino.size() && distinto == false) {
//					
//					if (historicoPaso.get(i) < mejorCamino.get(i)) {
//						
//						swapArrayList(historicoPaso);
//						distinto = true;
//					}
//					
//					else if (historicoPaso.get(i) > mejorCamino.get(i)) {
//						
//						distinto = true;
//					}
//					
//					i++;
//				}
//			}
//		}
//		
//		else {
//			
//			// Ir al este
//			if (grafo.adyacente(cursor, cursor + 1) && nodosVisitados[cursor + 1] == false) {
//				
//				nodosVisitados[cursor + 1] = true;
//				historicoPaso.add(cursor + 1);
//				
//				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + 1);
//				
//				nodosVisitados[cursor + 1] = false;
//				historicoPaso.remove(etapa);
//			}
//			
//			// Ir al sur
//			
//			if (grafo.adyacente(cursor, cursor + lab.getMaxColumnas()) && nodosVisitados[cursor + lab.getMaxColumnas()] == false) {
//				
//				nodosVisitados[cursor + lab.getMaxColumnas()] = true;
//				historicoPaso.add(cursor + lab.getMaxColumnas());
//				
//				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + lab.getMaxColumnas());
//				
//				nodosVisitados[cursor + lab.getMaxColumnas()] = false;
//				historicoPaso.remove(etapa);
//			}
//			
//			// Ir al oeste
//			
//			if (grafo.adyacente(cursor, cursor - 1) && nodosVisitados[cursor - 1] == false) {
//				
//				nodosVisitados[cursor - 1] = true;
//				historicoPaso.add(cursor - 1);
//				
//				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - 1);
//				
//				nodosVisitados[cursor - 1] = false;
//				historicoPaso.remove(etapa);
//			}
//			
//			// Ir al norte
//			
//			if (grafo.adyacente(cursor, cursor - lab.getMaxColumnas()) && nodosVisitados[cursor - lab.getMaxColumnas()] == false) {
//				
//				nodosVisitados[cursor - lab.getMaxColumnas()] = true;
//				historicoPaso.add(cursor - lab.getMaxColumnas());
//				
//				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - lab.getMaxColumnas());
//				
//				nodosVisitados[cursor - lab.getMaxColumnas()] = false;
//				historicoPaso.remove(etapa);
//			}
//		}
//	}
//
//
//	/**Metodo que devuelve el siguiente movimiento. <br>
//	 * PRE: esquina debe tener un valor entre 1 y 4. <br>
//	 * POST: devuelve la siguiente direccion de la cola. <br>
//	 * COMP: O(n)<br>
//	 * @return Direcciones.*/
//	public Direcciones siguienteMovimientoMin() {
//		
//		if (colaDir.isEmpty()) {
//			
//			int col = lab.getMaxColumnas()-1;
//			int fil = lab.getMaxFilas()-1;
//			
//			switch (esquina) {
//			
//			case 1:
//				
//				calcularRutaMinima(0, lab.matrizAVector(fil, 0));
//				esquina = 3;
//				
//				break;
//				
//			case 2:
//				
//				calcularRutaMinima(lab.matrizAVector(0, col), 0);
//				esquina = 1;
//				
//				break;
//				
//			case 3:
//				
//				calcularRutaMinima(lab.matrizAVector(fil, 0), lab.matrizAVector(fil, col));
//				esquina = 4;
//				
//				break;
//				
//			case 4:
//				
//				calcularRutaMinima(lab.matrizAVector(fil, col), lab.matrizAVector(0, col));
//				esquina = 2;
//				
//				break;
//			}
//		}
//			
//		return colaDir.poll();
//	}
//	/**Metodo que devuelve el siguiente movimiento. <br>
//	 * PRE: - <br>
//	 * POST: devuelve la siguiente direccion de la cola mientras no este vacia. <br>
//	 * COMP: O(1)<br>
//	 * @return Direcciones.*/
//	public Direcciones siguienteMovimiento() {
//		
//		return colaDir.poll();
//	}
//	/**Metodo que muestra por pantalla la cola de direcciones. <br>
//	 * PRE: - <br>
//	 * POST: visualiza la cola por pantalla dejandola vacia. <br>
//	 * COMP: O(n)*/
//	public void mostrar () {
//
//		
//		while (!colaDir.isEmpty()) {
//			
//			System.out.println(siguienteMovimiento());
//		}
//	}
//}
