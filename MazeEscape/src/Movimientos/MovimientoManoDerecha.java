package Movimientos;

import java.util.LinkedList;
import java.util.Queue;

import EstrucurasDeDatos.Grafo;
import Laberinto.Laberinto;
import Otros.Direcciones;

public class MovimientoManoDerecha implements InterfazMovimiento {
	
	private Queue<Direcciones> colaDir;	
	
	public MovimientoManoDerecha() {
		
		colaDir = new LinkedList<Direcciones>();
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

	/** Metodo que calcula la ruta entre dos puntos dirigiendose hacia la derecha siempre que sea posible. <br>
	 * PRE: pos es un entero valido. <br>
	 * POST: se guarda en la cola la ruta calculada. <br>
	 * COMP: O(n). <br>
	 * @param pos*/
	@Override
	public void generarRuta() {
		
		Laberinto lab = Laberinto.getInstancia();
		int pos = lab.matrizAVector(lab.getMaxFilas()-1, 0);
		Grafo grafo = lab.getGrafo() ;
		int idPuerta = lab.devolverSalaConPuerta().getIdSala();
		int contador = 0;
		Direcciones anterior = Direcciones.E;
		Direcciones siguiente = Direcciones.S;
		
		while (idPuerta != pos && contador <= 50) {
			
			switch (anterior) {
			
			case S:
				
				if(grafo.adyacente(pos, pos - 1)){
					
					siguiente = Direcciones.O;
					anterior = Direcciones.E;
					
					colaDir.add(siguiente);
					contador++;
					pos--;
				}
				
				else {
					
					anterior = Direcciones.O;
				}
				
				
				break;
				
			case E:
				
				if(grafo.adyacente(pos, pos + lab.getMaxColumnas())){
					
					siguiente = Direcciones.S;
					anterior = Direcciones.N;
					
					colaDir.add(siguiente);
					contador++;
					pos += lab.getMaxColumnas();
				}
				
				else {
					
					anterior = Direcciones.S;
				}
				
				break;
				
			case N:
				
				if(grafo.adyacente(pos, pos + 1)){
					
					siguiente = Direcciones.E;
					anterior = Direcciones.O;
					
					colaDir.add(siguiente);
					contador++;
					pos++;
				}
				
				else {
					
					anterior = Direcciones.E;
				}
				
				break;
				
			case O:
				
				if(grafo.adyacente(pos, pos - lab.getMaxColumnas())){
					
					siguiente = Direcciones.N;
					anterior = Direcciones.S;
					
					colaDir.add(siguiente);
					contador++;
					pos -= lab.getMaxColumnas();
				}
				
				else {
					
					anterior = Direcciones.N;
				}
				
				break;
			
			}
		}	
	}
	}

