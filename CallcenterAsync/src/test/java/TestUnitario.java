import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.ar.dispacher.Dispacher;
import com.ar.empleado.Director;
import com.ar.empleado.Empleado;
import com.ar.empleado.Llamada;
import com.ar.empleado.Operador;
import com.ar.empleado.Supervisor;


/**
 * The Class TestUnitario.
 *  @author Juan valero
 */
public class TestUnitario {
	
	/**
	 * Diez llamadas concurrentes.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void diezLlamadasConcurrentes() throws InterruptedException 
	{
		BlockingQueue<Empleado> empleados =new PriorityBlockingQueue<Empleado>();
		Empleado empOperador1=new Operador("Juan Operador");
		Empleado empOperador2=new Operador("Jose Operador");
		Empleado empSupervisor=new Supervisor("Alejandra Supervisora");
		Empleado empDirecto=new Director("Carlos Director");
		
		empleados.put(empSupervisor);
		empleados.put(empDirecto);
		empleados.put(empOperador1);
		empleados.put(empOperador2);

		
		List<Llamada> llamadasConcurrentes=new ArrayList<Llamada>();
		
		for(int i=0;i<10;i++) 
		{
			llamadasConcurrentes.add(new Llamada());
		}
		
		Dispacher dispacher=Dispacher.getIstance();
		dispacher.setEmpleadosDisponibles(empleados);
		//dispacher.start();
		
		for(Llamada llamada:llamadasConcurrentes) 
		{
			dispacher.llamadaEntrante(llamada);
			
		}
	
		sleep(25);
	}
	
	/**
	 * Sleep.
	 *
	 * @param seconds the seconds
	 */
	private void sleep(int seconds) {

	    try {
	        TimeUnit.SECONDS.sleep(seconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
