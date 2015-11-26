package Otros;

import java.util.Comparator;
/**
 * Implementacin de la clase ComparaLlave.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class ComparaLlave implements Comparator<Llave>{
	/** Metodo que compara dos llaves. <br>
	 * PRE: ll1 , ll2 son llaves validas. <br>
	 * POST: deve devovler el resultado en un entero. <br>
	 * COMP: O(1)<br>
	 * @param ll1, ll2 <br>
	 * @return devuelve 1 o -1 en funcion del identificador que sea mayor.
	 * */
    @Override
    public int compare(Llave ll1, Llave ll2) {
        if(ll1.getIdLlave() < ll2.getIdLlave()){
            return -1;
        } else {
            return 1;
        }
    }
}