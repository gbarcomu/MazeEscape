import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Laberinto.Laberinto;
import Laberinto.Puerta;
import Laberinto.Sala;
import Laberinto.SalaConPuerta;
import Otros.Direcciones;
import Otros.Llave;
import Robot.RobotDropeador;


public class RobotDropeadorTest {

	RobotDropeador As; 
	Llave llaveAux; 
	Direcciones[] dir = {Direcciones.N};
	SalaConPuerta puertaAux; 
	Laberinto lab;
	Sala salaAux;
	Llave[] datos = {new Llave(1), new Llave(3), new Llave(5), new Llave(7), new Llave(9), new Llave(11),
			   new Llave(13), new Llave(15), new Llave(17), new Llave(19), new Llave(21), new Llave(23),
			   new Llave(25), new Llave(27), new Llave(29)};
	Puerta p;
	
	@Before 
	public void inicializar() {
		
		lab = Laberinto.getInstancia(4, 4, 15, 3);		
		As = new RobotDropeador("Asimo", 'A', datos, 3, 3, 0);
		llaveAux = new Llave(1);		
		salaAux = new Sala();
		p = new Puerta();
		lab.insertarRobot(lab.getMaxFilas()-1, lab.getMaxColumnas()-1, As);
	}
	@Test
	public void testRobotHaceSusCosas() {
		
		//interactua con la puerta
		p.configurarPuerta(datos, 15, lab.getProfundidadPuerta(), lab.getNodosPuerta());
		assertTrue(p.esPuertaConfigurada());
		assertFalse(p.esPuertaAbierta());
		
		//interactuar llave
		
		salaAux.dejarLlaveSala(llaveAux);
		assertFalse(salaAux.llaveroVacio());
	}

}


