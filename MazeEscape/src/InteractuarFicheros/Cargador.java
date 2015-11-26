package InteractuarFicheros;

import java.io.PrintWriter;
import java.util.List;

import Otros.MiExcepcion;
import Laberinto.Laberinto;
import Otros.Llave;
import Robot.Bender;
import Robot.RobotDropeador;
import Robot.Sonny;
import Robot.Spirit;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las lineas y se van creando todas las instancias de la simulacion
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
public class Cargador {
	/**  
	numero de elementos distintos que tendra la simulacion - lab, Bender, Sonny, Spirit, Asimo
	*/
	static final int NUMELTOSCONF  = 5;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	/**  
	referencia a la instancia del patronn Singleton
	*/
	private Laberinto lab;
	private VolcarFichero volcar;
	private PrintWriter pw;
	/*
	 * necesitamos esta referencia para poder instanciar los robots y poder utilizar el laberinto*/
	
	/**
	 *  constructor parametrizado 
	 *  @param e referencia a la instancia del patron Singleton
	 */
	public Cargador(Laberinto labAux, PrintWriter pwAux) {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("LABERINTO", 5);
		mapeo[1]= new DatoMapeo("BENDER", 4);		
		mapeo[2]= new DatoMapeo("SONNY", 4);
		mapeo[3]= new DatoMapeo("SPIRIT", 4);
		mapeo[4]= new DatoMapeo("ASIMO", 4);
		
		lab = labAux;
		pw = pwAux;
	}
	
	/**
	 *  busca en mapeo el elemento obtenido del fichero inicio.txt y devuelve la posicion en la que esta<br>
	 *  @param elto elemento a buscar en el array<br>
	 *  @return res posicion en mapeo de dicho elemento 
	 */
	private int queElemento(String elto)  {
	    int res=-1;
	    boolean enc=false;
	    for (int i=0; (i < NUMELTOSCONF && !enc); i++)  {
	        if (mapeo[i].getNombre().equals(elto)) {
	            res=i;
	            enc=true;
	        }
	    }
	    return res;
	}
	
	/**
	 *  metodo que crea las distintas instancias de la simulacion 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 * @throws MiExcepcion 
	 */
	public void crear(String elto, int numCampos, List<String> vCampos) throws MiExcepcion	{
	    //Si existe elemento y el numero de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);

	    //Comprobacion de datos basicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	       //procesar
	       switch(queElemento(elto)) {
	        case 0:	   
	            crearLab(numCampos,vCampos);
	            break;
	        case 1:
	            crearBender(numCampos,vCampos);
	            break;
	        case 2:
	            crearSonny(numCampos,vCampos);
	            break;
	        case 3:
	            crearSpirit(numCampos,vCampos);
	            break;
	        case 4:
	            crearAsimo(numCampos,vCampos);
	            break;
	        
	     	}
	    }
	    else
	        System.out.println("ERROR Cargador::crear: Datos de configuraci�n incorrectos... " + elto + "," + numCampos+"\n");
	}

	/**
	 *  metodo que crea una instancia de la clase Planta
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws MiExcepcion 
	 */
	private void crearLab(int numCampos, List<String> vCampos) throws MiExcepcion{
	    System.out.println("Creado lab: " + vCampos.get(0)+"\n");
	  	    		
	    int maxF;
 	    int maxC;
	    int idSalaPuerta;
	    int profPuerta;    
	    
	    if (Integer.parseInt(vCampos.get(1)) > 0) 	
		    maxC = Integer.parseInt(vCampos.get(1));		    
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Parametro anchura no valido.");
	    
	    if (Integer.parseInt(vCampos.get(2)) > 0)
		    maxF = Integer.parseInt(vCampos.get(2));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Parametro altura no valido.");
	    
	    if (Integer.parseInt(vCampos.get(3)) >= 0 || Integer.parseInt(vCampos.get(3)) < maxF*maxC)
		    idSalaPuerta = Integer.parseInt(vCampos.get(3));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Parametro salaPuerta no valido.");	    	
	    
	    if (Integer.parseInt(vCampos.get(4)) > 0)
		    profPuerta = Integer.parseInt(vCampos.get(4));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Parametro alturaPuerta no valido."); 

	    if (maxF*maxC < 15 || maxF > 10 || maxC > 10)
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Tamano laberinto no valido."); 
	    
	    if (idSalaPuerta > maxF*maxC -1)
	    	throw new MiExcepcion("Excepcion capturada al cargar el Laberinto. Sala Puerta fuera de limites."); 
	    	
	    lab = Laberinto.getInstancia(maxF, maxC, idSalaPuerta, profPuerta);
		volcar = new VolcarFichero();
	    volcar.volcarLaberinto(pw);
	   
	    //LABERINTO#4#4#49#4#   *ancho*alto*salapuerta*profundidad 
	}
	/**
	 *  metodo que crea una instancia de la clase Bender
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws MiExcepcion 
	 */
	private void crearBender(int numCampos, List<String> vCampos) throws MiExcepcion{
	    System.out.println("Creado Bender: " + vCampos.get(0) + "\n");

	    int turno;
	    
	    if (Integer.parseInt(vCampos.get(3)) >= 0 && Integer.parseInt(vCampos.get(3)) <= lab.getTurnoMax())
	    	turno = Integer.parseInt(vCampos.get(3));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar un Robot. Parametro turno no valido.");
	    
	    char cad;
	    cad = vCampos.get(2).charAt(0);

	    Bender bender = new Bender(vCampos.get(1), cad, 0 , 0, turno);
	
		lab.insertarRobot(0, 0, bender);
	
	}
	/**
	 *  metodo que crea una instancia de la clase Sonny
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws MiExcepcion 
	 */
	private void crearSonny(int numCampos, List<String> vCampos) throws MiExcepcion{
	    System.out.println("Creado Sonny: " + vCampos.get(0) + "\n");
	   
	    int turno;
	    
	    if (Integer.parseInt(vCampos.get(3)) >= 0 && Integer.parseInt(vCampos.get(3)) <= lab.getTurnoMax())
	    	turno = Integer.parseInt(vCampos.get(3));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar un Robot. Parametro turno no valido.");
	    
	    char cad;
	    cad = vCampos.get(2).charAt(0);
	   		
		Sonny sonny = new Sonny(vCampos.get(1), cad, 0, 0,turno);
		lab.insertarRobot(0, 0, sonny);
	 
	}	
	/**
	 *  metodo que crea una instancia de la clase Spirit
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws MiExcepcion 
	 */
	private void crearSpirit(int numCampos, List<String> vCampos) throws MiExcepcion{
	    System.out.println("Creado Spirit: " + vCampos.get(0) + "\n");
	    
	    int turno;
	    
	    if (Integer.parseInt(vCampos.get(3)) >= 0 && Integer.parseInt(vCampos.get(3)) <= lab.getTurnoMax())
	    	turno = Integer.parseInt(vCampos.get(3));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar un Robot. Parametro turno no valido.");
	    
	    char cad;
	    cad = vCampos.get(2).charAt(0);
	    		
		Spirit spirit = new Spirit(vCampos.get(1), cad, lab.getMaxFilas() -1, 0, turno);
		
		lab.insertarRobot(lab.getMaxFilas() -1, 0, spirit);
	
	}	
	/**
	 *  metodo que crea una instancia de la clase Asimo
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws MiExcepcion 
	 */
	private void crearAsimo(int numCampos, List<String> vCampos) throws MiExcepcion{
	    System.out.println("Creado Asimo: " + vCampos.get(0) + "\n");
	    
	    int turno;
	    
	    if (Integer.parseInt(vCampos.get(3)) >= 0 && Integer.parseInt(vCampos.get(3)) <= lab.getTurnoMax())
	    	turno = Integer.parseInt(vCampos.get(3));
	    else
	    	throw new MiExcepcion("Excepcion capturada al cargar un Robot. Parametro turno no valido.");
	    
	    char cad;
	    cad = vCampos.get(2).charAt(0);
	    		
		Llave llaveroAsimo[] ={};
		llaveroAsimo = lab.llaveroGenerico ();
		RobotDropeador asimo = new RobotDropeador(vCampos.get(1),cad ,llaveroAsimo, lab.getMaxFilas()-1,
				lab.getMaxColumnas()-1, turno);
	    
		lab.insertarRobot(lab.getMaxFilas()-1, lab.getMaxColumnas()-1, asimo);
	}	
}
