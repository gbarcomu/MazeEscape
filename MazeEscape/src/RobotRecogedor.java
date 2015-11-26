import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Laberinto.Laberinto;
import Laberinto.Sala;
import Laberinto.SalaConPuerta;
import Otros.Direcciones;
import Otros.Llave;
import Robot.Bender;




public class RobotRecogedor {
	
	Bender b; 
	Llave llaveAux; 
	Direcciones[] dir = {Direcciones.N};
	SalaConPuerta puertaAux; 
	Laberinto lab;
	Sala salaAux;
	
	@Before 
	public void inicializar() {
		lab = Laberinto.getInstancia(4, 4, 15, 3);
		b = new Bender ("RobPrueba", 'B', 3, 3, 0);
		llaveAux = new Llave(1);
		
		salaAux = new Sala();
	
	}
	@Test
	public void testInteractuarPuerta() {
		
		Sala salaAux = lab.devolverSala(3, 3);
		
		assertFalse(((SalaConPuerta)(salaAux)).esPuertaAbierta());		
		((SalaConPuerta)(salaAux)).ProbarLlave(llaveAux);
		assertFalse(((SalaConPuerta)(salaAux)).esPuertaAbierta());
		
	}

	@Test
	public void testRobotHaceSusCosas() {
		
		salaAux.dejarLlaveSala(llaveAux);
		assertFalse(salaAux.llaveroVacio());
		
		llaveAux = salaAux.recogerLlaveSala();
		assertTrue(salaAux.llaveroVacio());


		
	}
}


