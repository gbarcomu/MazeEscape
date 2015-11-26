import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Laberinto.Laberinto;
import Laberinto.Sala;
import Otros.Llave;
import Robot.Bender;
import Robot.Sonny;


public class SalaTest {

	private Sala sala;
	private Bender b;
	private Llave llave = new Llave (1);
	private Laberinto lab;
	
	//TODO: @BeforeClass
	@Before
	
	public void inicializar () {
		
		lab = Laberinto.getInstancia(4, 4, 15, 3);
		b = new Bender ("RobPrueba",'B', 0, 0, 0);
		sala = new Sala ();
	//	sala = lab.devolverSala(0, 0);
		sala.dejarLlaveSala(llave);
		lab.insertarRobot(0, 0, b);
		
		
	}
	
	@Test
	public void testRobotEntra() {		
		
		sala.robotEntra(b);		
		assertTrue (sala.hayRobot());
		
		sala.robotSale();
	    assertFalse(sala.hayRobot());
	}

	@Test
	public void testRepartirLlavesOrdenSala() {
	
		sala.dejarLlaveSala(llave);
		assertFalse (sala.llaveroVacio());
		assertTrue (!sala.llaveroVacio());
	}

	@Test
	public void testRecogerLlaveSala() {
		
		sala = lab.devolverSala(0, 0);
		sala.dejarLlaveSala(llave);
		assertFalse (sala.llaveroVacio());
		sala.recogerLlaveSala();
		assertTrue (sala.llaveroVacio());	
	}


}
