package Movimientos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import EstrucurasDeDatos.Grafo;
import Laberinto.Laberinto;
import Otros.Direcciones;

public class MovimientoProfundidad implements InterfazMovimiento {

	private Queue<Direcciones> colaDir;	
	private ArrayList<Integer> mejorCamino;
	private Grafo grafo;
	private Laberinto lab;
	
	public MovimientoProfundidad() {
		
		colaDir = new LinkedList<Direcciones>();
		mejorCamino = new ArrayList<Integer>();
		lab = Laberinto.getInstancia();
		grafo = lab.getGrafo();
		generarRuta();		
	}
	
	/**Metodo que devuelve el siguiente movimiento. <br>
	 * PRE: - <br>
	 * POST: devuelve la siguiente direccion de la cola mientras no este vacia. <br>
	 * COMP: O(1)<br>
	 * @return Direcciones.*/
	@Override
	public Direcciones siguienteMovimiento() {
		
		return colaDir.poll();
	}
	/**Metodo que prepara los parametros para hacer una llamada recursiva a ruta profundidad. <br>
	 * PRE: cursor es un entero valido. <br>
	 * POST: guarda las direcciones correspondientes del recorrido en profundidad. <br>
	 * COMP: O(2^n)<br>
	 * @param cursor
	 * */
	@Override
	public void generarRuta() {
		
		int cursor = 0;		
		ArrayList<Integer> historicoPaso = new ArrayList<Integer>();
		boolean nodosVisitados[] = new boolean[lab.getMaxColumnas() * lab.getMaxFilas()];
		
		for (int i = 0; i < lab.getMaxFilas() * lab.getMaxColumnas(); i++) {
			
			nodosVisitados[i] = false;
		}
		
		int idFin = lab.devolverSalaConPuerta().getIdSala();
		
		nodosVisitados[0] = true;
		historicoPaso.add(0); 
		
		calcularRutaProfundidad(nodosVisitados,historicoPaso,idFin,1,cursor);
		
		IDADirecciones();

	}
	/**Metodo que dada una ruta por los id de las salas que pasa devuelve en la cola la ruta convertida en direcciones. <br>
	 * PRE: - <br>
	 * POST: colaDir se llena con las direcciones del recorrido. <br>
	 * COMP: O(n)<br> 
	 * */
	private void IDADirecciones () {		
		
		for (int i = 1; i < mejorCamino.size(); i++) {

			if((mejorCamino.get(i) - mejorCamino.get(i - 1)) > 0) {
				
				if((mejorCamino.get(i) - mejorCamino.get(i - 1)) == 1) {
					colaDir.add(Direcciones.E);
				}				
				else {
					colaDir.add(Direcciones.S);
				}
			}			
			else {
				
				if((mejorCamino.get(i) - mejorCamino.get(i - 1)) == -1) {
					colaDir.add(Direcciones.O);
				}
				
				else {
					colaDir.add(Direcciones.N);
				}
			}
		}
	}
	/**Metodo que calcula explorando todas las rutas posibles entre dos puntos. <br>
	 * PRE: nosdisvisitados es un vextor de boleanos valido, historicoPaso es un arrayList valido, idFin, etapa,cursor son enteros validos. <br>
	 * POST: se almacena el mejor camino de todos los posibles.<br>
	 * COMP: O(2^n)<br>
	 * @param nodosVisitados, historicoPaso, idFin, etapa, cursor. <br>
	 * */
	public void calcularRutaProfundidad(boolean[] nodosVisitados, ArrayList<Integer> historicoPaso, int idFin, int etapa, int cursor) {
		
		if (cursor == idFin) {
			boolean distinto = false;
			int i = 0;
				
			if (mejorCamino.size() == 0) {
				
				swapArrayList(historicoPaso);
			}
			
			else {
				
				while (i < historicoPaso.size() && i < mejorCamino.size() && distinto == false) {
					
					if (historicoPaso.get(i) < mejorCamino.get(i)) {
						
						swapArrayList(historicoPaso);
						distinto = true;
					}
					
					else if (historicoPaso.get(i) > mejorCamino.get(i)) {
						
						distinto = true;
					}
					
					i++;
				}
			}
		}
		
		else {
			
			// Ir al este
			if (grafo.adyacente(cursor, cursor + 1) && nodosVisitados[cursor + 1] == false) {
				
				nodosVisitados[cursor + 1] = true;
				historicoPaso.add(cursor + 1);
				
				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + 1);
				
				nodosVisitados[cursor + 1] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al sur
			
			if (grafo.adyacente(cursor, cursor + lab.getMaxColumnas()) && nodosVisitados[cursor + lab.getMaxColumnas()] == false) {
				
				nodosVisitados[cursor + lab.getMaxColumnas()] = true;
				historicoPaso.add(cursor + lab.getMaxColumnas());
				
				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + lab.getMaxColumnas());
				
				nodosVisitados[cursor + lab.getMaxColumnas()] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al oeste
			
			if (grafo.adyacente(cursor, cursor - 1) && nodosVisitados[cursor - 1] == false) {
				
				nodosVisitados[cursor - 1] = true;
				historicoPaso.add(cursor - 1);
				
				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - 1);
				
				nodosVisitados[cursor - 1] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al norte
			
			if (grafo.adyacente(cursor, cursor - lab.getMaxColumnas()) && nodosVisitados[cursor - lab.getMaxColumnas()] == false) {
				
				nodosVisitados[cursor - lab.getMaxColumnas()] = true;
				historicoPaso.add(cursor - lab.getMaxColumnas());
				
				calcularRutaProfundidad (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - lab.getMaxColumnas());
				
				nodosVisitados[cursor - lab.getMaxColumnas()] = false;
				historicoPaso.remove(etapa);
			}
		}
	}
	/**Metodo auxiliar para guardar el mejor camino en el Backtracking. <br>
	 * PRE; historicoPaso es un arrayList de enteros valido. <br>
	 * POST: almacena el mejor camino. <br>
	 * COMP: O(n)
	 * @param historicoPaso*/
	private void swapArrayList (ArrayList<Integer> historicoPaso) {
		
		mejorCamino.clear();
		
		for (int i = 0; i < historicoPaso.size(); i++) {
			
			mejorCamino.add(historicoPaso.get(i));
		}
	}


}
