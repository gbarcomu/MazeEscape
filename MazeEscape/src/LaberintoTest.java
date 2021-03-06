import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Laberinto.Laberinto;
import Otros.Direcciones;
import Otros.Llave;
import Robot.Bender;


public class LaberintoTest {

	Laberinto lab;
	Bender b;
	Direcciones[] dir = {Direcciones.N};
	Llave[] llavero;
	
	@Before
	
	public void inicializar () {
		
		lab = Laberinto.getInstancia(4, 4, 15, 3);
		b = new Bender ("RobPrueba", 'B', 0, 0, 0);
		llavero = new Llave[15]; 
	}
	
	@Test
	public void testInsertarRobot() {
		lab.insertarRobot(0, 0, b);
		assertTrue (lab.devolverSala(0, 0).hayRobot());
	}

	@Test
	public void testLlaveroGenerico() {
		llavero = lab.llaveroGenerico();
		assertTrue (llavero[0].getIdLlave() == 1);
		assertFalse (llavero[1].getIdLlave() == 2);		
	}
}
