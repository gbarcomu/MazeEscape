package Movimientos;

import java.util.LinkedList;
import java.util.Queue;

import EstrucurasDeDatos.Grafo;
import Laberinto.Laberinto;
import Otros.Direcciones;

public class MovimientoCuatroEsquinas implements InterfazMovimiento {

	private Queue<Direcciones> colaDir;	
	private Grafo grafo;
	private Laberinto lab;
	private int esquina;
	
	public MovimientoCuatroEsquinas() { 
		
		lab = Laberinto.getInstancia();
		grafo = lab.getGrafo();
		colaDir = new LinkedList<Direcciones>();
		generarRuta();
		esquina = 2; //Esquina NE
	}
	/**Metodo que devuelve el siguiente movimiento. <br>
	 * PRE: - <br>
	 * POST: devuelve la siguiente direccion de la cola mientras no este vacia. <br>
	 * COMP: O(1)<br>
	 * @return Direcciones.*/
	@Override
	public Direcciones siguienteMovimiento() {
		
		if(colaDir.isEmpty()) {
			
			recalcularRuta();
		}
		return colaDir.poll();
	}
	/**Metodo que devuelve el siguiente movimiento. <br>
	 * PRE: esquina debe tener un valor entre 1 y 4. <br>
	 * POST: devuelve la siguiente direccion de la cola. <br>
	 * COMP: O(n)<br>
	 * @return Direcciones.*/
	private void recalcularRuta() {
		
		int col = lab.getMaxColumnas()-1;
		int fil = lab.getMaxFilas()-1;
		
		switch (esquina) {
		
		case 1:
			
			generarTramoRuta(0, lab.matrizAVector(fil, 0));
			esquina = 3;
			
			break;
			
		case 2:
			
			generarTramoRuta(lab.matrizAVector(0, col), 0);
			esquina = 1;
			
			break;
			
		case 3:
			
			generarTramoRuta(lab.matrizAVector(fil, 0), lab.matrizAVector(fil, col));
			esquina = 4;
			
			break;
			
		case 4:
			
			generarTramoRuta(lab.matrizAVector(fil, col), lab.matrizAVector(0, col));
			esquina = 2;
			
			break;
		}
		
	}
	/**Metodo que calcula ruta mas corta entre dos puntos. <br>
	 * PRE: pos, posFin son enteros validos. <br>
	 * POST: se guarda en la cola las direcciones que marca la ruta minima. <br>
	 * COMP: O(n)<br>
	 * @param pos, posFin.*/
	private void generarTramoRuta(int pos, int posFin) {
		
		while (posFin != pos) {
			
			int aux;
			aux = grafo.siguiente(pos, posFin); 

			if((aux - pos) > 0) {
				
				if((aux - pos) == 1) {
					
					colaDir.add(Direcciones.E);
				}				
				else {
					
					colaDir.add(Direcciones.S);
				}
			}			
			else {
				
				if((aux - pos) == -1) {
					
					colaDir.add(Direcciones.O);
				}
				
				else {
					
					colaDir.add(Direcciones.N);
				}
			}
			
			pos = aux;
			 
		}		
	}
	
	public void generarRuta() {
		
		generarTramoRuta(lab.devolverSalaConPuerta().getIdSala(), lab.matrizAVector(0, lab.getMaxColumnas()-1));
	}

}
