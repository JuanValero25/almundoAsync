import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.ar.dispacher.Dispacher;
import com.ar.empleado.Director;
import com.ar.empleado.Empleado;
import com.ar.empleado.Llamada;
import com.ar.empleado.Operador;
import com.ar.empleado.Supervisor;

/**
 * The Class TestUnitario.
 * 
 * @author Juan valero
 */
public class TestUnitario {

	/**
	 * Diez llamadas concurrentes.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test
	public void diezLlamadasConcurrentes() throws InterruptedException {
		System.out.println("##### TEST 1,  10 llamadas concurrentes 2 operadores , 1 supervisor, 1 director  ######");
		BlockingQueue<Empleado> empleados = new PriorityBlockingQueue<Empleado>();
		Empleado empOperador1 = new Operador("Juan Operador");
		Empleado empOperador2 = new Operador("Jose Operador");
		Empleado empSupervisor = new Supervisor("Alejandra Supervisora");
		Empleado empDirecto = new Director("Carlos Director");

		empleados.put(empSupervisor);
		empleados.put(empDirecto);
		empleados.put(empOperador1);
		empleados.put(empOperador2);

		List<Llamada> llamadasConcurrentes = new ArrayList<Llamada>();

		for (int i = 0; i < 10; i++) {
			llamadasConcurrentes.add(new Llamada());
		}

		Dispacher dispacher = Dispacher.getIstance();
		dispacher.setEmpleadosDisponibles(empleados);

		for (Llamada llamada : llamadasConcurrentes) {
			dispacher.dispatchCall(llamada);

		}

		sleep(25);

		Assert.assertEquals(0, dispacher.cantidadDeLLamadasRestantes());
	}
	
	@Test
	public void muchosEmpleadosMuchasLLamadas() throws InterruptedException {
		System.out.println("##### TEST 2 muchos empleados 30  llamadas 10 operadores, 1 supervisor, 1 director ######");
		
		BlockingQueue<Empleado> empleados = new PriorityBlockingQueue<Empleado>();
		Empleado empOperador1 = new Operador("Juan Operador");
		Empleado empOperador2 = new Operador("Jose Operador");
		Empleado empOperador3 = new Operador("Leticia Operador");
		Empleado empOperador4 = new Operador("Matias Operador");
		Empleado empOperador5 = new Operador("Vanesa Operador");
		Empleado empOperador6 = new Operador("Carlos Operador");
		Empleado empOperador7 = new Operador("Gensis Operador");
		Empleado empOperador8 = new Operador("Laura Operador");
		Empleado empOperador9 = new Operador("Linux Operador");
		Empleado empOperador10 = new Operador("Windows Operador");
		Empleado empSupervisor = new Supervisor("Alejandra Supervisora");
		Empleado empDirecto = new Director("Carlos Director");

		empleados.put(empSupervisor);
		empleados.put(empDirecto);
		empleados.put(empOperador1);
		empleados.put(empOperador2);
		empleados.put(empOperador3);
		empleados.put(empOperador4);
		empleados.put(empOperador5);
		empleados.put(empOperador6);
		empleados.put(empOperador7);
		empleados.put(empOperador8);
		empleados.put(empOperador9);
		empleados.put(empOperador10);

		List<Llamada> llamadasConcurrentes = new ArrayList<Llamada>();

		for (int i = 0; i < 30; i++) {
			llamadasConcurrentes.add(new Llamada());
		}

		Dispacher dispacher = Dispacher.getIstance();
		dispacher.setEmpleadosDisponibles(empleados);

		for (Llamada llamada : llamadasConcurrentes) {
			dispacher.dispatchCall(llamada);

		}

		sleep(25);

		Assert.assertEquals(0, dispacher.cantidadDeLLamadasRestantes());
	}

	/**
	 * Sleep.
	 * duerme el hilo principal tantos segundos. el dispacher es un hilo aparte da tiempo para que el hilo dispacher a que termine sus tareas
	 * @param seconds
	 *           
	 */
	private void sleep(int seconds) {

		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
