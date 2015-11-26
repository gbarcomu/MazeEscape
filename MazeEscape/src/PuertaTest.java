import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Laberinto.Puerta;
import Otros.Llave;



public class PuertaTest {
	
	private Puerta puertaPrueba;
	Llave[] datos = {new Llave(1), new Llave(3), new Llave(5), new Llave(7), new Llave(9), new Llave(11),
		   new Llave(13), new Llave(15), new Llave(17), new Llave(19), new Llave(21), new Llave(23),
		   new Llave(25), new Llave(27), new Llave(29)};
	Llave llaveAux;
	
	@Before
	
	public void Inicializar() {
		
		puertaPrueba = new Puerta ();
		puertaPrueba.configurarPuerta(datos, 15, 5, 14);
	}
		   
		   
	@Test
	public void testConfigurarPuerta() {
		
		assertTrue(puertaPrueba.esPuertaConfigurada());
		assertFalse(puertaPrueba.esPuertaAbierta());
		assertEquals(puertaPrueba.getNodosMinimos(), 14);
		assertEquals(puertaPrueba.getProfundidad(), 5);
	}


	
	@Test
	public void testProbarLlave() {
		
		llaveAux = new Llave(1);
		
		assertFalse (puertaPrueba.esPuertaAbierta());
		puertaPrueba.probarLlave(llaveAux);
		assertTrue (puertaPrueba.esPuertaAbierta());		
	}
}
