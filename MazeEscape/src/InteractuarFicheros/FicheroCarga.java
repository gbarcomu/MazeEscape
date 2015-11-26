package InteractuarFicheros;
/**
 * Clase creada para ser usada en la utilidad cargador
 * estudiada previamente en sesion practica "Excepciones"
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Otros.MiExcepcion;

public class FicheroCarga {
	/**  
	atributo de la clase que indica el numero maximo de campos que se pueden leer
	*/
	public static final int MAXCAMPOS  = 12;

	/**  
	buffer para almacenar el flujo de entrada
	*/
	private static BufferedReader bufferIn;
	/**
	 * Metodo para procesar el fichero. Sin excepciones
	 * @return codigoError con el error que se ha producido
	 * @throws MiExcepcion 
	 * @throws Exception. Puede lanzar una excepcion en la apertura del buffer de lectura
	 */
	 public static void procesarFichero(String nombreFichero, Cargador cargador) throws  FileNotFoundException, IOException, MiExcepcion {
		 //**String vCampos[]=new String[MAXCAMPOS];
		 List<String> vCampos = new ArrayList<String>();
	     String S=new String();
	     int numCampos=0;

	     System.out.println( "Procensando el fichero..." );
	     bufferIn = new BufferedReader(new FileReader(nombreFichero));//creacion del filtro asociado al flujo de datos

         while((S=bufferIn.readLine())!= null) {
	     	 System.out.println( "S: "+S );
  	 		 if (!S.startsWith("--"))  {
  	 			 vCampos.clear();
  	 			 numCampos = trocearLinea(S, vCampos);
  	 			 cargador.crear(vCampos.get(0), numCampos, vCampos);
	 		}
	     }
	     bufferIn.close();	     //cerramos el filtro
	 }

	 /**
	  * Metodo para trocear cada linea y separarla por campos
	  * @param S cadena con la linea completa leida
	  * @param vCampos. Array de String que contiene en cada posicion un campo distinto
	  * @return numCampos. Numero campos encontrados
	 */
	 private static int trocearLinea(String S,List<String> vCampos) {
		 boolean eol = false;
		 int pos=0,posini=0, numCampos=0;

   	     while (!eol)
   	     {
	    			pos = S.indexOf("#");
	     	    	if(pos!=-1) {
	     	    		vCampos.add(new String(S.substring(posini,pos)));
	     	    		//**vCampos[numCampos] = new String(S.substring(posini,pos));
	     	    		S=S.substring(pos+1, S.length());
	     	    		numCampos++;
	     	    	}
	     	    	else eol = true;
		  }
		  return numCampos;
	 }

}