/**
 * Implementacion de la clase Laberinto.<br>
 * @version 5.0 <br>
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte<br>
 * Asignatura Desarrollo de Programas<br>
 * Curso 14/15<br>
 * Nombre grupo: IngenierosEnProcesion<br>
 * Numero de entrega: Enero<br>
 */

package Laberinto;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Otros.GenAleatorios;
import Otros.Llave;
import Otros.MiExcepcion;
import Robot.Robots;
import EstrucurasDeDatos.Grafo;
import InteractuarFicheros.Cargador;
import InteractuarFicheros.FicheroCarga;
import InteractuarFicheros.VolcarFichero;
import Laberinto.SalaConPuerta;

public class Laberinto {
	/** Vector que nos permite crear el array bidimensional con las salas.*/
	private Sala [][] SalasLab;	
	/** Puntero a la sala de la puerta para acceder a ella mas facilmente*/
	private SalaConPuerta salaPuerta;	
	/** Sala donde entraran los robots ganadores */
	private Sala salaGanadores;
	/** Entero que nos permite establcer el numero maximo de columnas del array.*/	
	private int maxColumnas;
	/** Entero que nos permite establcer el numero maximo de filas del array.*/
	private int maxFilas;
	/** Entero que nos permite establcer el numero maximo de turnos del laberinto.*/
	private int maxTurnos;
	/** Entero que nos permite almacenar el turno actual del laberinto.*/
	private int turnoLaberinto;
	/** Entero para establecer la profundidad minima a la que se va a abrir la puerta.*/
	private int profundidadPuerta;
	/** Entero para establecer los nodos minimos a los que se va a abrir al puerta.*/
	private int nodosPuerta;
	/**Grafo en el que nos apoyamos para poder ir de una sala a otra.*/
	private Grafo grafo;
	/** Instancia del patron de Singleton */
	private static Laberinto instancia = null;
	
	
	/* Constructores ---------------------------------*/
	/**
	 * Constructor por defecto de la clase laberinto(es publico porque es necesario para los test).<br>
	 */
	public Laberinto() {
		
	}
	/**
	 * Constructor parametrizado de la clase laberinto.<br>
	 * PRE: maxF(altura) es un entero valido, maxC(anchura) es un entero valido, idSalaPuerta es un identificador valido, profPuerta es una profundiad valida.<br>
	 * POST: Inicializa el laberinto.<br>
	 * @param maxF, maxC, idSalaPuerta, profPuerta<br>
	 * COMP: O(n2)
	 */
	public Laberinto(int maxF, int maxC, int idSalaPuerta,  int profPuer) {
		
		/** Inicializacion parametrizada de los atributos. */
		maxFilas = maxF;
		maxColumnas = maxC;
		/* maxFilas * maxColumnas >= 15 */
		maxTurnos = 50;
		Llave llaveroConfiguracion[] = llaveroGenerico();
		profundidadPuerta = profPuer;
		nodosPuerta = 15;
		
		grafo = new Grafo();
						
		turnoLaberinto = 0;
		
		/** Creamos las salas del laberinto. */
		
		SalasLab  = new Sala[maxFilas][maxColumnas];
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j< maxColumnas; j++) {
				
				SalasLab[i][j] = new Sala();
				SalasLab[i][j].setIdSala(matrizAVector(i, j));
				grafo.nuevoNodo(matrizAVector(i,j));
				
			}
		}
		
		int auxX = vectorAMatrizX(idSalaPuerta);
		int auxY = vectorAMatrizY(idSalaPuerta);
		
		SalasLab[auxY][auxX] =
				new SalaConPuerta(llaveroConfiguracion, 15, profundidadPuerta, nodosPuerta);
		salaPuerta = (SalaConPuerta) SalasLab[auxY][auxX];	
		
		salaPuerta.setIdSala(idSalaPuerta);
		
		salaGanadores = new Sala();
	
		kruskal ();
		
		repartirLlaves();
	
	}

	/* Principal ---------------------------------*/
	
	/** Metodo mediante el que controlamos los turnos del laberinto y volcamos en cada turno lo que esta ocuriendo.<br>
	 * PRE: volcar y pw deben ser paramatros correctos.<br>
	 * POST: La informacion debe ser volcada correctamente. <br>
	 * COMP: O(n)<br>
	 * @param VolcarFichero, PrintWriter
	 * */
	private void gestorDeTurnos(VolcarFichero volcar, PrintWriter pw) {
		
		while(turnoLaberinto < maxTurnos && !salaPuerta.esPuertaAbierta()) {
			
			moverRobotLaberinto();
			turnoLaberinto++;
			
	       	volcar.volcadoVarios(pw);
	       	volcar.volcarLaberinto(pw);
	       	volcar.volcadoSalas(pw);
			volcar.volcadoRobots(pw);
			
		}
		
		if (turnoLaberinto == maxTurnos) {
			
			System.out.println ("La simulacion ha acabado agotandose los turnos");
		}
		
		else {
			
			System.out.println ("La simulacion ha acabado abriendose la puerta");
		}
	}
	
	/** Metodo mediante el que movemos los robots por el laberinto. <br>
	 * PRE: - <br>
	 * POST: Los robots se deben mover correctamente por el laberinto<br>
	 * COMP: O(n3)
	 * */
	private void moverRobotLaberinto() {
		
		Robots robotAux;
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j< maxColumnas; j++) {
				
				int robotsEnSala = SalasLab[i][j].cuantosRob();
				
				while (robotsEnSala != 0) {
					
					robotAux = SalasLab[i][j].mostrarRobot();
					robotAux.robotHaceSusCosas(this);
					robotsEnSala--;
				}			
			}	
		}
	}
	
	/* Get Set  ---------------------------------*/
	/** 
	 * Metodo que obtiene el valor del turno del atributo turnoLaberinto.<br>
	 * PRE : .<br>
	 * POST: Se devuelve turno del laberinto.<br>
	 * COMP: O(1)
	 */
	public int getTurno() {
		
		return turnoLaberinto;
	}
	/** 
	 * Metodo que obtiene el valor del turno maximo del atributo maxTurnos.<br>
	 * PRE : .<br>
	 * POST: Se devuelve turno maximo del laberinto.<br>
	 * COMP: O(1)
	 */
	public int getTurnoMax() {
		
		return maxTurnos;
	}
	
	/** 
	 * Metodo que obtiene el valor maximo de columnas del laberinto.<br>
	 * PRE : .<br>
	 * POST: Se devuelve el numero maximo de columnas.<br>
	 * COMP: O(1)
	 */
	public int getMaxColumnas() {
		
		return maxColumnas;
	}
	/** 
	 * Metodo que obtiene el valor maximo de filas del laberinto.<br>
	 * PRE : .<br>
	 * POST: Se devuelve el numero maximo de filas.<br>
	 * COMP: O(1)
	 */
	public int getMaxFilas() {
		
		return maxFilas;
	}
	/** 
	 * Metodo que obtiene el valor lineal de una posicion de la matriz.<br>
	 * PRE : .<br>
	 * POST: Se devuelve la posicion pasada como parametro en forma lieneal.<br>
	 * COMP: O(1)
	 * @param x , y, SalasLab
	 */
	public Sala devolverSala(int x, int y ) {
		
		return SalasLab[x][y];
	}
	/** 
	 * Metodo que obtiene la sala en la que esta la puerta.<br>
	 * PRE : .<br>
	 * POST: Se devuelve una sala.<br>
	 * COMP: O(1)
	 */
	public SalaConPuerta devolverSalaConPuerta() {
		
		return salaPuerta;
	}
	/** 
	 * Metodo que obtiene la sala de los robots ganadores.<br>
	 * PRE : .<br>
	 * POST: Se devuelve la sala de ganadores.<br>
	 * COMP: O(1)
	 */
	public Sala devovlerSalaGanadores() {
		
		return salaGanadores;
	}
	/** 
	 * Metodo que obtiene la profundidad del arbol de llaves.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con la profundidad.<br>
	 * COMP: O(1)
	 */
	public int getProfundidadPuerta () {
		
		return profundidadPuerta;
	}
	/** 
	 * Metodo que obtiene el numero de nodos del arbol de llaves.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con la cantidad de nodos.<br>
	 * COMP: O(1)
	 */
	public int getNodosPuerta () {
		
		return nodosPuerta;
	}
	/**
	 * Metodo mediante el que insertamos un robots en una de las salas del laberinto. <br>
	 * PRE: x, y deben ser enteros validos, robAux debe ser un Robot valido. <br>
	 * POST: el robot debe quedar insertado en la sala correcta.
	 * COMP: O(1)<br>
	 * @param int, Robots
	 * */
	public void insertarRobot(int x, int y,Robots robAux)  {
		
		SalasLab[x][y].robotEntra(robAux);
	}
	/** 
	 * Metodo que obtiene el grafo.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un grafo.<br>
	 * COMP: O(1)
	 */
	public Grafo getGrafo() {
		
		return grafo;
	}
	
	/* Singleton ---------------------------------*/
	/**
	 * Metodo que devuelve la instancia unica del laberinto.<br>
	 * PRE: -<br>
	 * POST: Devuelve la instancia unica del laberinto.<br>
	 * COMP: O(1)
	 * 
	 * @return Mapa
	 */
	public static Laberinto getInstancia () {
		
		if (instancia == null)
			instancia = new Laberinto();
		
		return instancia;
	}
	/**
	 * Metodo que devuelve la instancia unica del Mapa.<br>
	 * PRE: anchura es un entero valido, altura es un entero valido, idSalaPuerta es un entero valido, profPuerta es un entero valido.<br>
	 * POST: Devuelve la instancia unica del Mapa.<br>
	 * COMP: O(n2)
	 * 
	 * @param anchura
	 * @param altura
	 * @param idSalaPuerta
	 * @param profPuerta
	 * 
	 * @return Laberinto
	 */
	public static Laberinto getInstancia (int anchura, int altura, int idSalaPuerta, int profPuerta) {
		
		if (instancia == null) {
			
			instancia = new Laberinto (anchura, altura, idSalaPuerta, profPuerta);
		}
		return instancia;
	}
	
	/* Utilidad ---------------------------------*/
	/**
	 * Metodo mediante el que repartimos las llaves en las salas del laberinto.<br>
	 * PRE: -<br>
	 * POST: Las llaves deben quedar insertadas en las salas.<br>
	 * COMP: O(n)
	 * */
	public void repartirLlaves () {
		
		reutilizarIDDerribo();
		
		boolean nodosVisitados[] = new boolean[maxFilas * maxColumnas];
		
		for (int i = 0; i < maxFilas * maxColumnas; i++) {
			
			nodosVisitados[i] = false;
		}
		
		ArrayList<Integer> historicoPaso = new ArrayList<Integer> ();
		int idFin = salaPuerta.getIdSala();
		
		nodosVisitados[0] = true;
		historicoPaso.add(0); 
				
		backtracking (nodosVisitados, historicoPaso, idFin, 1, 0);
		
		distribuirLlaves (buscarSalasFrecuentadas());
	}
	/**
	 * Metodo que calcula las salas con mayor frecuencia de paso. <br>
	 * PRE: - <br>
	 * POST: el resultado debe quedar almacenado en un vector de enteros.<br>
	 * COMP: O(n3)
	 * */
	private int[] buscarSalasFrecuentadas() {
		
		int[] idSalasConLlave = new int[9];
		int contador = 0;
		int frecuencia = buscarMayorFrecuenciaDePaso();
		
		while (contador != 9) {
			
			for (int i = 0; i < maxFilas; i++) {
				
				for (int j = 0; j < maxColumnas; j++) {
					
					if ((i!=0 || j!=0) && (i!=maxFilas-1 || j!=maxColumnas-1)
							&& contador != 9 && devolverSala(i,j).getIdDerribo() == frecuencia){
						
						idSalasConLlave[contador] = devolverSala(i,j).getIdSala();
						contador++;
					}
				}	
			}	
			frecuencia--;
		}
		
		return idSalasConLlave;
	}
	/**
	 * Metodo que encuentra la sala con mayor frecuencia de paso. <br>
	 * PRE: - <br>
	 * POST: devuelve en un entero la sala con mayor frecuencia.<br>
	 * COMP: O(n2) 
	 * */
	private int buscarMayorFrecuenciaDePaso() {
		
		int maximo = 0;
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j < maxColumnas; j++) {
				
				if (maximo < devolverSala(i,j).getIdDerribo()) {
					
					maximo = devolverSala(i,j).getIdDerribo();
				}
			}
		}
		
		return maximo;
	}
	/** Metodo que el atributo IDDerribo de todas las salas del laberinto a 0.<br>
	 * PRE: - <br>
	 * POST: las salas se ponen a 0. 
	 * COMP: O(n2)*/
	private void reutilizarIDDerribo () {
		
		Sala salaAux;
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j < maxColumnas; j++) {
				
				salaAux = devolverSala(i,j);
				salaAux.setIdDerribo(0);
			}
		}
	}
	
	/** Metodo empleado para la distribucion de llaves en las salas indicadas.<br>
	 * PRE: el vector debe contener llaves. <br>
	 * POST: las llaves deben quedar insertadas en las salas adecuadas.<br>
	 * COMP: O(n2)
	 *  @param int
	 *  */
	private void distribuirLlaves(int[] idSalasConLlave) {
		
		int llavesPorSala = 5;
		int llaveActual = 0;
		
		boolean incrementoImpar = false;
		
		for (int k = 0; k < idSalasConLlave.length; k++) {
			
			int aux = idSalasConLlave[k];
	
				for (int i = 0; i < llavesPorSala; i++) {
					
					Llave llaux = new Llave(llaveActual);
					SalasLab[aux/maxColumnas][aux%maxColumnas].dejarLlaveSala(llaux);
					
					if(llaveActual % 2 == 0 || incrementoImpar) {
						
						llaveActual++;
						incrementoImpar = false;
					}
					
					else {
						
						incrementoImpar = true;
					}
				}	
		}
	}	
	
	/** Metodo que genera un vector con las llaves para configurar la puerta de salida.<br>
	 * PRE: - <br>
	 * POST: las llaves deben ser generadas en le vector. <br>
	 * COMP: O(n)
	 *  */
	public Llave[] llaveroGenerico () {
		
		Llave[] llaveroConfiguracion = new Llave[15];
		
		for (int i = 0; i < 15; i++) {
			
			llaveroConfiguracion[i]= new Llave(2 * i + 1);
		}
		
		return llaveroConfiguracion;
	}
	
	/** Metodo que nos transforma una matriz en un vector.<br>
	 * PRE: filas y columnas deben ser enteros validos<br>
	 * POST: devuelve un entero con la posicion lienal. <br>
	 * COMP: O(1)<br>
	 * @param int
	 *  */
	public int matrizAVector(int filas, int columnas) {
		
		return (filas * maxColumnas) + columnas;
	}
	/**
	 * Metodo que transforma una posicion lineal en matricial.<br>
	 * PRE: id debe ser un entero valido.<br>
	 * POST: devuelve un entero con la posicion de la fila correspondiente.<br>
	 * COMP: O(1) <br>
	 * @param int
	 * */
	private int vectorAMatrizX(int id) {
				
		return (id % maxColumnas); 
	}
	/**
	 * Metodo que transforma una posicion lineal en matricial.<br>
	 * PRE: id debe ser un entero valido.<br>
	 * POST: devuelve un entero con la posicion de la columna correspondiente.<br>
	 * COMP: O(1) <br>
	 * @param int
	 * */
	private int vectorAMatrizY(int id) {
		
		return (id / maxColumnas);
	}
	
		
		
	/* Profundidad ---------------------------------*/
	/** Metodo utilizado para recorrer todos los caminos posibles entre dos puntos. <br>
	 * PRE: nodosVisitados en un vector de boleanos, historico paso es un arrayList de enteros validos, idFin, etapa, cursor son enteros validos.<br>
	 * POST: aumenta el idDerribo de las salas si pasa por ellas en cada camino que encuentre. <br>
	 * COMP: O(2^n)<br>
	 * @param nodosVisitados, historicoPaso, idFin, cursor. */
	private void backtracking (boolean[] nodosVisitados, ArrayList<Integer> historicoPaso, int idFin, int etapa, int cursor) {
			
		if (cursor == idFin) {
			
			for (int i = 0; i < etapa; i++) {
				
				devolverSala(vectorAMatrizY(historicoPaso.get(i)),vectorAMatrizX(historicoPaso.get(i))).incIdDerribo();
			}
		}
		
		else {
			
			// Ir al este
			if (grafo.adyacente(cursor, cursor + 1) && nodosVisitados[cursor + 1] == false) {
				
				nodosVisitados[cursor + 1] = true;
				historicoPaso.add(cursor + 1);
				
				backtracking (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + 1);
				
				nodosVisitados[cursor + 1] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al sur
			
			if (grafo.adyacente(cursor, cursor + maxColumnas) && nodosVisitados[cursor + maxColumnas] == false) {
				
				nodosVisitados[cursor + maxColumnas] = true;
				historicoPaso.add(cursor + maxColumnas);
				
				backtracking (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor + maxColumnas);
				
				nodosVisitados[cursor + maxColumnas] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al oeste
			
			if (grafo.adyacente(cursor, cursor - 1) && nodosVisitados[cursor - 1] == false) {
				
				nodosVisitados[cursor - 1] = true;
				historicoPaso.add(cursor - 1);
				
				backtracking (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - 1);
				
				nodosVisitados[cursor - 1] = false;
				historicoPaso.remove(etapa);
			}
			
			// Ir al norte
			
			if (grafo.adyacente(cursor, cursor - maxColumnas) && nodosVisitados[cursor - maxColumnas] == false) {
				
				nodosVisitados[cursor - maxColumnas] = true;
				historicoPaso.add(cursor - maxColumnas);
				
				backtracking (nodosVisitados, historicoPaso, idFin, etapa + 1, cursor - maxColumnas);
				
				nodosVisitados[cursor - maxColumnas] = false;
				historicoPaso.remove(etapa);
			}
		}
	}

	/* Kruskal ---------------------------------*/
	
	/**
	 * Metodo que tira las paredes de la matriz formando el laberinto.<br>
	 * PRE: -<br>
	 * POST: Crea el laberinto tirando paredes del tablero.<br>
	 * COMP: O(n2)
	 */
	public void kruskal () {
		
		ArrayList<Pared> coleccionParedes = new ArrayList<Pared>();
		int contadorAux = 0;
		Pared paredAux;
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j< maxColumnas; j++) {
				
				if (i != 0) {
					
					paredAux = new Pared(matrizAVector(i,j),matrizAVector(i-1,j));
					coleccionParedes.add(paredAux);
					contadorAux++;
				}
				
				if (j != maxColumnas-1) {
					
					paredAux = new Pared(matrizAVector(i,j),matrizAVector(i,j+1));
					coleccionParedes.add(paredAux);
					contadorAux++;
				}
				
				if (i != maxFilas-1) {
					
					paredAux = new Pared(matrizAVector(i,j),matrizAVector(i+1,j));
					coleccionParedes.add(paredAux);
					contadorAux++;
				}
				
				if (j != 0) {
					
					paredAux = new Pared(matrizAVector(i,j),matrizAVector(i,j-1));
					coleccionParedes.add(paredAux);
					contadorAux++;
				}								
			}
		}
			
		generarRandomLab (coleccionParedes, contadorAux);
		
		grafo.floyd();
		grafo.warshall();
						
		crearAtajos();
		
		grafo.floyd();
		grafo.warshall();
	}
	/** Metodo que genera un laberinto aleatorio.<br>
	 * PRE: coleccionParedes es un arrayList de paredes valido, candidatos es un entero valido. <br>
	 * COMP: O(n^3)
	 * @param coleccionParedes, candidatos. */	
	private void generarRandomLab (ArrayList<Pared> coleccionParedes, int candidatos) {
		
		int numRand;
		Pared pared;
		Sala salaAux1;
		Sala salaAux2;
		int auxColum, auxFil;
		
		while (!coleccionParedes.isEmpty()) {
			
			numRand = GenAleatorios.generarNumero(candidatos);
			candidatos--;
						
			pared = coleccionParedes.get(numRand);
			coleccionParedes.remove(numRand);
			
			auxColum = vectorAMatrizX(pared.getSala1());
			auxFil = vectorAMatrizY(pared.getSala1());
			salaAux1 = devolverSala (auxFil, auxColum);
			
			auxColum = vectorAMatrizX(pared.getSala2());
			auxFil = vectorAMatrizY(pared.getSala2());
			salaAux2 = devolverSala (auxFil, auxColum);
			
			if (salaAux1.getIdDerribo() != salaAux2.getIdDerribo()) {
				
				grafo.nuevoArco(pared.getSala1(), pared.getSala2(), 1);
				grafo.nuevoArco(pared.getSala2(), pared.getSala1(), 1);

				propagarIDDerribo (salaAux1.getIdDerribo(), salaAux2.getIdDerribo());

			}
		}				
	}
	/**Metodo que pone el mismo identificador a todas las salas que esten conectadas. <br>
	 * PRE: nuevoID, viejoID son enteros validos.<br>
	 * POST: las salas conectadas deben quedar con el mismo ID. <br>
	 * COMP: O(n^2)<br>
	 * @param nuevoID, viejoID
	 * */
	private void propagarIDDerribo (int nuevoID, int viejoID) {
		
		for (int i = 0; i < maxFilas; i++) {
			
			for (int j = 0; j < maxColumnas; j++) {
				
				if (devolverSala(i,j).getIdDerribo() == viejoID) {
					
					devolverSala(i,j).setIdDerribo(nuevoID);
				}
			}
		}
	}
	/**
	 * Metodo que genera atajos.<br>
	 * PRE: -<br>
	 * POST: Crea atajos dentro del laberinto tirando paredes.<br>
	 * COMP: O(n)
	 */
	private void crearAtajos () {
		// NSOE
		int rango = maxFilas * maxColumnas;
		int paredesADerribar = (maxFilas * maxColumnas * 5) / 100;
		
		int numRand;
		while (paredesADerribar != 0) {
			
			numRand = GenAleatorios.generarNumero(rango);

			if (hayAtajo (numRand, numRand - maxColumnas)) {
				
				grafo.nuevoArco(numRand, numRand - maxColumnas, 1);
				grafo.nuevoArco(numRand - maxColumnas, numRand, 1);
				paredesADerribar--;
			}
			
			else if(hayAtajo (numRand, numRand + maxColumnas)) {
				
				grafo.nuevoArco(numRand, numRand + maxColumnas, 1);
				grafo.nuevoArco(numRand + maxColumnas, numRand, 1);
				paredesADerribar--;
			}
			
			else if(hayAtajo (numRand, numRand - 1) && numRand % maxColumnas != 0 ) {
				
				grafo.nuevoArco(numRand, numRand - 1, 1);
				grafo.nuevoArco(numRand -1, numRand, 1);
				paredesADerribar--;
			}
			
			else if(hayAtajo (numRand, numRand + 1) && (numRand+1) % maxColumnas != 0) {

				grafo.nuevoArco(numRand, numRand + 1, 1);
				grafo.nuevoArco(numRand + 1, numRand, 1);
				paredesADerribar--;
			}
		}
	}
	/**
	 * Metodo utilizado para saber si hay atajo entre dos salas del laberinto.<br>
	 * PRE: ID1, ID2 deben ser enteros validos. <br>
	 * POST: devuelve true si hay atajo entre los dos identificadores y false en caso contrario. <br>
	 * COMP: O(n)
	 * @param int
	 * */
	private boolean hayAtajo (int ID1, int ID2) {
		
		if (grafo.adyacente(ID1, ID2) || ID2 >= (maxFilas * maxColumnas) || ID2 < 0) {
			
			return false;
		}
		
		int contador = 0;
	
		while (contador < 3) {
			
			ID1 = grafo.siguiente(ID1, ID2);

			if (ID1 == ID2) {

				return false;
			}
			
			contador++;
		}

		return true;
	}
	
	/* Main ---------------------------------*/
	/**
	 * Metodo principal de la clase Laberinto.<br>
	 * PRE: -<br>
	 * POST: Ejecuta las funciones necesarias para la simulacion.
	 * @throws IOException 
	 * @throws MiExcepcion */
	public static void main(String[] args) throws IOException, MiExcepcion {
		
		Laberinto lab = null; 
		
		FileWriter fichero = new FileWriter("registro.log"); 
       	PrintWriter pw = new PrintWriter(fichero);
      
		/**  
		instancia asociada al fichero de entrada inicio.txt
		*/
		Cargador cargador = new Cargador(lab, pw );
		
		try {
			/**  
			Metodo que procesa linea a linea el fichero de entrada inicio.txt
			*/
		     FicheroCarga.procesarFichero("inicio.txt", cargador);
		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepcion capturada al procesar fichero: "+valor.getMessage());
		}
		catch (IOException valor)  {
			System.err.println ("Excepcion capturada al procesar fichero: "+valor.getMessage());
		}
		
	 	VolcarFichero volcar = new VolcarFichero();      	

       	volcar.volcadoVarios(pw);
       	volcar.volcarLaberinto(pw);
       	volcar.volcadoSalas(pw);
		volcar.volcadoRobots(pw);
		
		lab = Laberinto.getInstancia();
		
		lab.gestorDeTurnos(volcar, pw);
		
		pw.close();	
	}
}


