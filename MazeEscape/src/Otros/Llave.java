package Otros;

import java.util.Comparator;
/**
 * Implementacion de la clase Llave.
 * @version 5.0
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte
 * Asignatura Desarrollo de Programas<br/>
 * Curso 14/15
 * Nombre grupo: IngenierosEnProcesion
 * Numero de entrega: Enero
 */
public class Llave {
	/**Entero que contiene el identificador de cada llave*/
	private Integer idLlave;
	/**Constructor por defecto de la clase llave. <br>
	 * PRE: - <br>
	 * POST: Inicializa la llave a 0.<br>
	 * COMP:O(1)
	 * */
	public Llave () {
		
		idLlave = new Integer (0);
	}
	/** Constructor parametrizado de la clase llave.<br>
	 * PRE: id es un entero valido. <br>
	 * POST: Inicializa el idLlave al valor pasado por parametro.<br>
	 * COMP: O(1)*/
	public Llave (int id) {
		
		this.idLlave = new Integer(id);
	}
	/**Metodo que asigna el identificador al atributo.<br>
	 * PRE: id es un entero valido.<br>
	 * POST: El atributo idLlave toma el valor dado.<br>
	 * COMP: O(1)
	 * 
	 * @param idLlave
	 */
	public void setIdLlave (Integer id) {
		
		this.idLlave = id;
	}
	/** 
	 * Metodo que obtiene el identificador de la llave.<br>
	 * PRE : .<br>
	 * POST: Se devuelve un entero con el identificador de la llave.<br>
	 * COMP: O(1)
	 * @param idLlave
	 */
	public Integer getIdLlave () {
		
		return this.idLlave;
	}
	/** Metodo encargado de comparar dos idLlave.<br>
	 * PRE: datoRaiz es una Llave valida<br>
	 * POST: devuelve un entero con la comparacion.<br>
	 * COMP: O(1)<br>
	 * @return int*/
	public int compareTo (Llave datoRaiz) {

		return this.idLlave-datoRaiz.getIdLlave();
	}
	/** Metodo que devuelve si dos datos son iguales.<br>.
	 * PRE: datoRaiz es un dato valido-<br>
	 * POST: devuelve true si los objetos son iguales.<br>
	 * COMP: O(1)<br>
	 * @param dato raiz
	 * @return devuelve true si los objetos son iguales y false en caso contrario.*/
	public boolean equals (Object datoRaiz) {
		
		if (this == datoRaiz) {
			
			return true;
		}
		
		if (!(datoRaiz instanceof Llave)) {
			
			return false;
		}
		
		Llave aux = (Llave) datoRaiz;
		return (this.idLlave.equals(aux.getIdLlave()));
	}
	
}

class ComparaLlaves implements Comparator<Llave>{
	 
    @Override
    public int compare(Llave ll1, Llave ll2) {
        if(ll1.getIdLlave() < ll2.getIdLlave()){
            return 1;
        } else {
            return -1;
        }
    }
}
