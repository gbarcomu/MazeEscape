package EstrucurasDeDatos;
import java.util.LinkedList;
import Otros.Llave;

/**
 * Implementacion de arbol binario de busqueda.<br>
 * @version 5.0 <br>
 * @authores Guillermo Barco Munoz y Antonio Manuel Fuentes Duarte <br>
 * Asignatura Desarrollo de Programas <br>
 * Curso 14/15 <br>
 * Nombre grupo: IngenierosEnProcesion <br>
 * Numero de entrega: Enero <br>
 */
public class Arbol {

	/** Dato almacenado en cada nodo del arbol. */
	private Llave datoRaiz;
	
	/** Atributo que indica si el arbol esta vacio.. */
	boolean esVacio;
	
	/** Hijo izquierdo del nodo actual */
	private Arbol hIzq;
	
	/** Hijo derecho del nodo actual */
	private Arbol hDer;
	
	/**
	 * Constructor por defecto de la clase arbol.<br>
	 * PRE: -<br>
	 * POST: Inicializa el arbol.<br>
	 * COMP: O(1) 
	 */
	public Arbol() {
		
	    this.esVacio = true;
	    this.hIzq = null;
	    this.hDer = null;
	}

	/**
	 * Constructor parametrizado de la clase arbol.<br>
	 * PRE: hIzq es un arbol valido, datoRaiz es un T valido, hDer es un arbol valido.<br>
	 * POST: Inicializa el arbol.<br>
	 * COMP: O(1)
	 * 
	 * @param hIzq
	 * @param datoRaiz
	 * @param hDer
	 */
	public Arbol (Arbol hIzq, Llave datoRaiz, Arbol hDer) {
		
	    this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}
	
	/**
	 * Metodo que devuelve el hijo izquierdo del Arbol.<br>
	 * PRE: -<br>
	 * POST: Devuelve el hijo izquierdo del Arbol.<br>
	 * COMP: O(1)
	 *
	 * @return Arbol<T>
	 */
	public Arbol getHijoIzq() {
		
		return hIzq;
	}
	
	/**
	 * Metodo que devuelve el hijo derecho del Arbol.<br>
	 * PRE: -<br>
	 * POST: Devuelve el hijo derecho del Arbol.<br>
	 * COMP: O(1) <br>
	 * @return Arbol<T>
	 */
	public Arbol getHijoDer() {
		
		return hDer;
	}
	
	/**
	 * Metodo que devuelve la raiz del Arbol.<br>
	 * PRE: -<br>
	 * POST: Devuelve la raiz del Arbol.<br>
	 * COMP: O(1) <br>
	 *
	 * @return T
	 */
	public Llave getRaiz() {
		
		return datoRaiz;
	}
	
	/**
	 * Metodo que comprueba si el Arbol esta vacio.<br>
	 * PRE: -<br>
	 * POST: Devuelve true si el Arbol esta vacio o false en caso contrario.<br>
	 * COMP: O(1)
	 * 
	 * @return boolean
	 */
	public boolean vacio() {
		
		return esVacio;
	}
	
	/**
	 * Metodo que inserta un nuevo T en el Arbol.<br>
	 * PRE: dato es un T valido.<br>
	 * POST: Devuelve true si el dato se ha insertado corectamente o false en caso contrario.<br>
	 * COMP: O(1)
	 *
	 * @param dato
	 * @return boolean
	 */
	public boolean insertar(Llave dato) {
		
	    boolean resultado=true;
	    
	    if (vacio()) {
	    	
	        datoRaiz = dato;
			esVacio = false;
		}
	    
	    else {
	    	
	        if (!(this.datoRaiz.equals(dato))) {
	        	
	            Arbol aux;
	            
	            if (dato.compareTo(this.datoRaiz) < 0) { //dato < datoRaiz
	            	
	                if ((aux = getHijoIzq()) == null)
	                    hIzq = aux = new Arbol();
	            }
	            
	            else {									//dato > datoRaiz
	            	
	                if ((aux = getHijoDer()) == null)
	                	
	                    hDer = aux = new Arbol();
	            }
	            resultado = aux.insertar(dato);
	        }
	        else{
	        	
	            resultado = false;
	        }
	    }
	    return resultado;
	}
	
	/**
	 * Metodo que comprueba si un dato se encuentra almacenado en el Arbol.<br>
	 * PRE: dato es un T valido.<br>
	 * POST: Devuelve true si el dato se encuentra en el Arbol o false en caso contrario.<br>
	 * COMP: O(1)
	 *
	 * @param dato
	 * @return boolean
	 */
	public boolean pertenece(Llave dato) {
		
	    Arbol aux = null;
	    boolean encontrado = false;
	    
	    if (!vacio()) {
	    	
	        if (this.datoRaiz.equals(dato)){
	        	
	            encontrado = true;
	        }
	        
	        else {
	        	
	            if (dato.compareTo(this.datoRaiz) < 0) {	//dato < datoRaiz
	            	
	                aux = getHijoIzq();
	            }
	            else{									//dato > datoRaiz
	            	
	                aux = getHijoDer();
	            }
	            if (aux != null) {
	                encontrado = aux.pertenece(dato);
	            }
	        }
	    }
	    return encontrado;
	}
	
	/**
	 * Metodo que borra un dato del Arbol.<br>
	 * PRE: dato es un T valido.<br>
	 * POST: Borra el elemento del Arbol.<br>
	 * COMP: O(n)
	 *
	 * @param dato
	 */
	public void borrar(Llave dato) {
		
	    if (!vacio()) {
	    	
	        if (dato.compareTo(this.datoRaiz) <0) {			//dato<datoRaiz
	        	
	        	if(hIzq != null){
	        		
					hIzq = hIzq.borrarOrden(dato);
	        	}
			}	
	        
	        else {
	        	
	           if (dato.compareTo(this.datoRaiz) > 0) {		//dato>datoRaiz 
	            	
	            	if(hDer != null) {
	            		
	            		hDer = hDer.borrarOrden(dato);
	            	}
				}
	            else {//En este caso el dato es datoRaiz
	            
	                if (hIzq == null && hDer == null) {
	                
	                    esVacio = true;
	                }
	                
	                else{
	                	
	                    borrarOrden(dato);
	                }
	            }
	        }
	    }
	}
	

	/**
	 * Metodo que borra un dato del Arbol (usado por el metodo anterior).<br>
	 * PRE: dato es un T valido.<br>
	 * POST: Devuelve el Arbol con el elemento borrado.<br>
	 * COMP: O(n)
	 *
	 * @param dato
	 * @return Arbol<T>
	 */
	private Arbol borrarOrden(Llave dato) {
	
		Llave datoaux;
	    Arbol retorno = this;
	    Arbol aborrar, candidato, antecesor;

	    if (!vacio()) {
	    	
	        if (dato.compareTo(this.datoRaiz) < 0) {		// dato<datoRaiz
	        	
	        	if(hIzq != null){
	        		
	    	        hIzq = hIzq.borrarOrden(dato);
	        	}

	        }
	        
			else{
				
	            if (dato.compareTo(this.datoRaiz) > 0) {	// dato>datoRaiz
	            	
	            		if(hDer != null){
	            			
	 	    	           hDer = hDer.borrarOrden(dato);	
	            		}

	            }
	            
				else {
					
				    aborrar = this;
				    
	                if ((hDer == null) && (hIzq == null)) { /*si es hoja*/
	                	
	                   retorno=null;
	                }
	                
	                else {
	                	
	                   if (hDer == null) { /*Solo hijo izquierdo*/
	                	   
	                        aborrar = hIzq;
	                        datoaux = this.datoRaiz;
	                        datoRaiz = hIzq.getRaiz();
	                        hIzq.datoRaiz = datoaux;
	                        hIzq = hIzq.getHijoIzq();
	                        hDer = aborrar.getHijoDer();

	                        retorno=this;
	                    }
	                    else{
	                    	
	                        if (hIzq == null) { /*Solo hijo derecho*/
	                        	
	                            aborrar = hDer;
	                            datoaux = datoRaiz;
	                            datoRaiz = hDer.getRaiz();
	                            hDer.datoRaiz = datoaux;
	                            hDer = hDer.getHijoDer();
	                            hIzq = aborrar.getHijoIzq();

	                            retorno=this;
	                        }
	                        
	                        else { /* Tiene dos hijos */
	                        	
	                            candidato = this.getHijoIzq();
	                            antecesor = this;
	                            
	                            while (candidato.getHijoDer() != null) {
	                                antecesor = candidato;
	                                candidato = candidato.getHijoDer();
	                            }

	                            /*Intercambio de datos de candidato*/
	                            datoaux = datoRaiz;
	                            datoRaiz = candidato.getRaiz();
	                            candidato.datoRaiz=datoaux;
	                            aborrar = candidato;
	                            if (antecesor == this){
	                                hIzq = candidato.getHijoIzq();
	                            }
	                            
	                            else{
	                                antecesor.hDer = candidato.getHijoIzq();
	                            }
	                        } //Eliminar solo ese nodo, no todo el subarbol
	                        
	                    aborrar.hIzq=null;
	                    aborrar.hDer=null;
	                    }
	                }
				}
			}
	    }
	    return retorno;
	}
	
	
	/**
	 * Metodo que realiza un recorrido en inOrden del Arbol.<br>
	 * PRE: -<br>
	 * POST: Realiza un recorrido en inOrden guardando en una linkedList la raiz del Arbol.<br>
	 * COMP: O(n) <br>
	 * @return LinkedList
	 */
	public LinkedList<Llave> inOrden(LinkedList<Llave> llaveroArbol) {
		

		
	    Arbol aux = null;
	    
	    if (!vacio()) {
	    	   	
	        if ((aux = getHijoIzq()) != null) {
	        	
	           aux.inOrden(llaveroArbol);
	        }    
	        
	        llaveroArbol.add(this.datoRaiz);
	     	        
	        if ((aux = getHijoDer()) != null) {
	            
	        	aux.inOrden(llaveroArbol);
	        }    
	    }
	    return llaveroArbol;	
	}
	/**
	 * Metodo que envuelve a la funcion inOrden para que puede ser llamada recursivamente. <br>
	 * PRE: -<br>
	 * POST: Devuelve una linkedList con las llaves que hay en el arbol. <br>
	 * @return LinkedList
	 * */
	public LinkedList<Llave> wrapper () {
		
		LinkedList<Llave> llaveroArbol= new LinkedList<Llave>();
		
		inOrden (llaveroArbol);
		
		return llaveroArbol;
	}
	/**
	 * Metodo que realiza un recorrido en preOrden del Arbol.<br>
	 * PRE: -<br>
	 * POST: Realiza un recorrido en preOrden mostrando por pantalla el contenido del Arbol.<br>
	 * COMP: O(n) 
	 */
	public void preOrden() {
	    
		Arbol aux = null;
	   
		if (!vacio()) {

	        System.out.println(this.datoRaiz.getIdLlave());
	        
	    	if ((aux = getHijoIzq()) != null) {
	    		
	            aux.preOrden();
	        }    	  
	        
	        if ((aux = getHijoDer()) != null){
	        	
	            aux.preOrden();
	        }    
	    }
	}
	/**
	 * Metodo que realiza un recorrido en ppstOrden del Arbol.<br>
	 * PRE: -<br>
	 * POST: Realiza un recorrido en postOrden mostrando por pantalla el contenido del Arbol.<br>
	 * COMP: O(n) 
	 */
	public void postOrden() {
	   
		Arbol aux = null;
	    
		if (!vacio()) {

	    	if ((aux = getHijoIzq()) != null) {
	            
	    		aux.postOrden();
	        }    	  
	        
	        if ((aux = getHijoDer()) != null){
	           
	        	aux.postOrden();
	        } 
	        
	        System.out.println(this.datoRaiz.getIdLlave());
	    }
	}
	/**
	 * Metodo que devuelve la profundidad del Arbol.<br>
	 * PRE: -<br>
	 * POST: Devuelve la profundidad del Arbol.<br>
	 * COMP: O(N)
	 * @return int
	 */
	public int profundidadArbol() {
		
		int profundidad = 0, contDer = 0, contIzq = 0;
		Arbol aux;
		
		if (!vacio()) {
			
			profundidad++;
			
			if ((aux = getHijoIzq())!= null) {
				
				contIzq = aux.profundidadArbol();
			}
			
			if ((aux = getHijoDer())!= null) {
				
				contDer = aux.profundidadArbol();
			}
			
			if (contIzq > contDer) {
				
				profundidad += contIzq;
			}
			
			else {
				
				profundidad += contDer;
			}
			
			return profundidad;
			
		}
		
		return 0;
	}
	/**
	 * Metodo que comprueba si un nodo del Arbol es nodo hoja.<br>
	 * PRE: dato es una llave valida.<br>
	 * POST: Devuelve true si es nodo hoja o false en caso contrario.<br>
	 * COMP: O(1)
	 * @param dato
	 * @return boolean
	 */
	public boolean nodoEsHoja(Llave dato) {
		
		boolean encontrado = false;
		Arbol aux;
		
		if (!vacio()){
			
			if(datoRaiz == dato) {
								
				if(hIzq == null && hDer == null){
					
					encontrado = true;
				}
			}
			
			else {
				
				if(dato.compareTo(this.datoRaiz)<0){
					
					if((aux = getHijoIzq()) != null) {
						
						encontrado = (encontrado || aux.nodoEsHoja(dato));
					}
				}

				else{
					
					if((aux = getHijoDer()) != null) {
						
						encontrado = (encontrado || aux.nodoEsHoja(dato));
					}
				}

			}
		}
		
		return encontrado;
	}
	/**
	 * Metodo que devuelve el numero de nodos que hay en el Arbol.<br>
	 * PRE: - <br>
	 * POST: Devuelve el numero de nodos del Arbol.<br>
	 * COMP: O(n)
	 * @return int 
	 */
	public int numeroNodosInterno() {
		
	    Arbol aux = null;
	    int contador = 0;
	    
	    if (!vacio()) {

	    	contador++;
	    	
	    	if ((aux = getHijoIzq()) != null) {
	            
		        contador += aux.numeroNodosInterno();
	        }    	  
	        
	        if ((aux = getHijoDer()) != null) {            

		        contador += aux.numeroNodosInterno();
	        } 
	        

	    }
	    
	    return contador;
	}
	/**
	 * Metodo que devuelve el numero de nodos hoja que hay en el Arbol.<br>
	 * PRE: -.<br>
	 * POST: Devuelve el numero de nodos hoja del Arbol.<br>
	 * COMP: O(n)
	 * @return int
	 */
	public int numeroNodosHoja() {
		
	    Arbol aux = null;
	    int contador = 0;
	    
	    if (!vacio()) {

	    	if(hIzq == null && hDer == null){
	    		
		    	contador++;
	    	}

	    	
	    	if ((aux = getHijoIzq()) != null) {
	            
		        contador += aux.numeroNodosHoja();
	        }    	  
	        
	        if ((aux=getHijoDer())!=null){            

		        contador += aux.numeroNodosHoja();
	        } 
	        

	    }
	    
	    return contador;
	}
}
