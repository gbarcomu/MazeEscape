import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Laberinto.Puerta;
import Otros.Llave;



public class SalaConPuertaTest {
	
	Llave[] datos = {new Llave(1), new Llave(3), new Llave(5), new Llave(7), new Llave(9), new Llave(11),
			   new Llave(13), new Llave(15), new Llave(17), new Llave(19), new Llave(21), new Llave(23),
			   new Llave(25), new Llave(27), new Llave(29)};

	Puerta puertaSalida;
	Llave llaveAux;
	
	@Before
	public void inicializar() {
		puertaSalida = new Puerta();
		puertaSalida.configurarPuerta(datos, 15, 4, 9);
	}
	
	@Test
	public void testProbarLlave() {
		
		llaveAux = new Llave(23);
		assertTrue (puertaSalida.probarLlave(llaveAux));
		assertFalse (puertaSalida.esPuertaAbierta());
	
	}
	
	@Test
	public void testConfigurarPuerta() {
		
				
		for (int i = 0; i < 15 && !puertaSalida.esPuertaAbierta(); i++) {
			
			puertaSalida.probarLlave(datos[i]);
		}
		assertTrue(puertaSalida.esPuertaConfigurada());
		assertTrue (puertaSalida.esPuertaAbierta());
		assertEquals(puertaSalida.getNodosMinimos(), 9);
		assertEquals(puertaSalida.getProfundidad(), 4);
	}


}


