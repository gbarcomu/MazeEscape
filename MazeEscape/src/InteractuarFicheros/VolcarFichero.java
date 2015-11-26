package InteractuarFicheros;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import EstrucurasDeDatos.Grafo;
import Laberinto.Laberinto;
import Otros.Llave;
import Robot.Robots;

/**
 * Implementacion de la clase volcarFichero.
 * 
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte Asignatura
 *           Desarrollo de Programas<br/>
 *           Curso 14/15 Nombre grupo: IngenierosEnProcesion Numero de entrega:
 *           Enero
 */
public class VolcarFichero {
	/** Dato mediante el que instanciamos el laberinto. */
	private Laberinto lab;
	/** Cola de robot auxiliar utilizada como apoyo. */
	private Queue<Robots> robotsEnSalaAux;
	/** LinkedList de llaves auxiliar utilizada como apoyo. */
	private LinkedList<Llave> llavesEnSalaAux;
	/** Pila de llaves auxiliar utilizada como apoyo. */
	private Stack<Llave> llavesEnSalaAux2;

	/**
	 * Constructor por defecto de la clase VolcarFichero. Inicializa el
	 * laberinto. <br>
	 * PRE: - <br>
	 * POST: Inicializa el laberinto.<br>
	 * COMP: O(1)
	 * */
	public VolcarFichero() {

		lab = Laberinto.getInstancia();
	}

	/**
	 * Metodo que vuelva en un fichero apuntado por pw. <br>
	 * PRE: pw se ha inicializado correctamente. <br>
	 * POST: Vuelva en un fichero apuntado por pw. COMP: O(n2)
	 * 
	 * @param PrinterWriter
	 * */
	public void volcadoVarios(PrintWriter pw) {

		pw.println("(turno:" + lab.getTurno() + ")");
		pw.println("(laberinto:"
				+ ((lab.getMaxFilas() * lab.getMaxColumnas()) - 1) + ")");

		String estado;

		if (lab.devolverSalaConPuerta().esPuertaAbierta()) {

			estado = "abierta";
		}

		else {

			estado = "cerrada";
		}
		pw.print("(puerta:" + estado + ":" + lab.getProfundidadPuerta() + ":");

		LinkedList<Llave> llavesLab = lab.devolverSalaConPuerta()
				.mostrarLlavesPuerta();

		for (int i = 0; i < llavesLab.size(); i++) {

			pw.print(" " + llavesLab.get(i).getIdLlave());

		}
		pw.print(":");
		LinkedList<Llave> llavesProb = lab.devolverSalaConPuerta()
				.mostrarLlavesProbadas();

		for (int i = 0; i < llavesProb.size(); i++) {

			pw.print(" " + llavesProb.get(i).getIdLlave());

		}

		llavesLab.clear();
		llavesProb.clear();
		pw.println(")");

	}

	/**
	 * Metodo que vuelca en un fichero apuntado por pw las salas del laberinto.<br>
	 * PRE: pw se ha inicializado correctamente.<br>
	 * POST: Vuelca en un fichero apuntado por pw las salas del laberinto.<br>
	 * COMP: O(n3) <br>
	 * 
	 * @param PrintWriter
	 */
	public void volcadoSalas(PrintWriter pw) {

		llavesEnSalaAux = new LinkedList<Llave>();
		Llave llaveAux;
		for (int i = 0; i < lab.getMaxFilas(); i++) {

			for (int j = 0; j < lab.getMaxColumnas(); j++) {

				if (!lab.devolverSala(i, j).llaveroVacio()) {

					pw.print("(sala:" + lab.devolverSala(i, j).getIdSala()
							+ ":");

					while (!lab.devolverSala(i, j).llaveroVacio()) {

						llaveAux = lab.devolverSala(i, j).recogerLlaveSala();
						pw.print(" " + llaveAux.getIdLlave());
						llavesEnSalaAux.add(llaveAux);
					}
					pw.println(")");
				}
				recomponerLlavesSala(i, j);

			}
		}
	}

	/**
	 * Metodo mediante el que controlamos que no se pierdan las llaves que hay
	 * en la sala. <br>
	 * PRE: i y j son enteros validos.<br>
	 * POST: Las llaves de la sala no deben perderse. <br>
	 * COMP: O(n) <br>
	 * 
	 * @param int
	 * */
	public void recomponerLlavesSala(int i, int j) {

		while (!llavesEnSalaAux.isEmpty()) {

			lab.devolverSala(i, j).dejarLlaveSala(llavesEnSalaAux.poll());
		}
	}

	/**
	 * Metodo que vuelca en un fichero apuntado por pw las Colas de robots,asi
	 * como sus llaves, de las Salas.<br>
	 * PRE: pw se ha inicializado correctamente.<br>
	 * POST: Vuelca en un fichero apuntado por pw las Colas de los robots y sus
	 * llaves.<br>
	 * COMP: O(n4)
	 * 
	 * @param PrintWriter
	 */
	public void volcadoRobots(PrintWriter pw) {
		Llave llaveAux;

		robotsEnSalaAux = new LinkedList<Robots>();
		llavesEnSalaAux2 = new Stack<Llave>();

		for (int i = 0; i < lab.getMaxFilas(); i++) {

			for (int j = 0; j < lab.getMaxColumnas(); j++) {

				while (lab.devolverSala(i, j).hayRobot()) {

					Robots robAux = lab.devolverSala(i, j).robotSale();
					pw.print("(" + robAux.getNombre() + ":"
							+ robAux.getIdRobot() + ":" + lab.matrizAVector(i, j) + ":" + robAux.getTurnoRobot() + ": ");
					
					robotsEnSalaAux.add(robAux);

					while (!robAux.llaverVacio()) {

						llaveAux = robAux.sacarLlave();
						pw.print(llaveAux.getIdLlave() + " ");
						llavesEnSalaAux2.push(llaveAux);
					}
					recomponerLlaves(robAux);

					pw.println(")");
				}
				recomponerRobots(i, j);
			}
		}

		while (lab.devovlerSalaGanadores().hayRobot()) {

			Robots robAux = lab.devovlerSalaGanadores().robotSale();

			pw.println("(" + robAux.getNombre() + ":" + robAux.getIdRobot()
					+ ":" + -1 + ":" + -1 + ") ");
		}
	}

	/**
	 * Metodo mediante el que controlamos que no se pierdan los robots que hay
	 * en la sala. <br>
	 * PRE: i y j son enteros validos.<br>
	 * POST: Los robots de la sala no deben perderse. <br>
	 * COMP: O(n) <br>
	 * 
	 * @param int
	 * */
	private void recomponerRobots(int i, int j) {

		Robots rob;
		while (!robotsEnSalaAux.isEmpty()) {

			rob = robotsEnSalaAux.poll();
			lab.devolverSala(i, j).robotEntra(rob);
		}
	}

	/**
	 * Metodo mediante el que controlamos que no se pierdan las llaves que tiene
	 * cada robots. <br>
	 * PRE: robAux es un robots valido.<br>
	 * POST: Las llaves de los robots no deben perderse. <br>
	 * COMP: O(n) <br>
	 * 
	 * @param Robots
	 * */
	private void recomponerLlaves(Robots robAux) {

		Llave llaveAux;
		while (!llavesEnSalaAux2.isEmpty()) {

			llaveAux = llavesEnSalaAux2.pop();
			robAux.guardarLlave(llaveAux);
		}
	}

	/**
	 * Metodo que vuelca en un fichero apuntado por pw el laberinto.<br>
	 * PRE: pw se ha inicializado correctamente.<br>
	 * POST: Vuelca en un fichero apuntado por pw el laberinto.<br>
	 * COMP: O(n3) <br>
	 * 
	 * @param PrintWriter
	 */
	public void volcarLaberinto(PrintWriter pw) {

		Grafo gAux = lab.getGrafo();

		for (int j = 0; j < lab.getMaxColumnas(); j++) {

			pw.print(" _");
		}

		pw.println();

		for (int i = 0; i < lab.getMaxFilas(); i++) {

			pw.print("|");

			for (int j = 0; j < lab.getMaxColumnas(); j++) {

				int cuantos = lab.devolverSala(i, j).cuantosRob();

				if (cuantos == 0) {

					if (i != lab.getMaxFilas() - 1
							&& gAux.adyacente(lab.matrizAVector(i, j),
									lab.matrizAVector(i + 1, j))) {

						pw.print(" ");
					}

					else {

						pw.print("_");
					}
				}

				else if (cuantos == 1) {

					pw.print(lab.devolverSala(i, j).mostrarRobot().getIdRobot());
				}

				else {

					pw.print(cuantos);
				}

				if (j != lab.getMaxColumnas() - 1) {

					if (gAux.adyacente(lab.matrizAVector(i, j),
							lab.matrizAVector(i, j + 1))) {

						pw.print(" ");
					}

					else {

						pw.print("|");
					}

				}

			}

			pw.print("|");
			pw.println();
		}
		pw.println();
	}
}
