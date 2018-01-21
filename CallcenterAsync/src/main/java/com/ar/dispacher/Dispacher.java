package com.ar.dispacher;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.ar.empleado.Empleado;
import com.ar.empleado.Llamada;

/**
 * The Class Dispacher.
 * Singelton.
 * @author Juan valero
 */

public class Dispacher extends Thread {

	/** The empleados disponibles. */
	private volatile BlockingQueue<Empleado> empleadosDisponibles;

	/** The llamadas. */
	private volatile Queue<Llamada> llamadas;

	/** The executor. */
	private volatile ExecutorService executor;

	/** The executor liberador empl. */
	private volatile ExecutorService executorLiberadorEmpl;

	/** The dispacher singelton. */
	private static Dispacher dispacherSingelton;

	/** The Constant CANTIDAD_LLAMADAS. */
	private static final int CANTIDAD_LLAMADAS = 10;
	

	/**
	 * constructor de dispacher es un Singelton.
	 * 
	 */
	private Dispacher() {
		empleadosDisponibles = new PriorityBlockingQueue<Empleado>();
		executor = Executors.newFixedThreadPool(CANTIDAD_LLAMADAS);
		executorLiberadorEmpl = Executors.newFixedThreadPool(CANTIDAD_LLAMADAS);
		llamadas = new LinkedBlockingQueue<Llamada>();
		this.start();

	}

	/**
	 * Atender la llamada el metodo atender llamda obtiene empleado de Queue de
	 * empleadosDispoibles y le manda una llamada esto se ejecuta deforma
	 * asyncronica cada empleado seria un hilo en ejecucion y ese devuelve un Future
	 * el cual le preguntara con posterioridad cuando el future sea isDone se devuelve el empleado empleadosDispoibles
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private void atenderLLamada() {
		if (llamadas.isEmpty()) {
			//si no hay llamadas no hace nada.
			return;
		}

		if (empleadosDisponibles.isEmpty()) {
			/*
			 * cualquier mensaje o logica que se necesite si no hay empleado libres talvez
			 * como una musica de fondo
			 */
		} else {
			try {
				Empleado empleado = empleadosDisponibles.take();
				empleado.atenderllamada(llamadas.poll());
				Future future = executor.submit(empleado);
				this.liberarEmpleado(future, empleado);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * crea un hilo para preguntar cada 100 milisegundos para antender llamada
	 * verifica si hay llamada dispoinble y atiende la llamada 
	 * se verifica cada Cierto tiempo si hay llamadas  y intenta atender.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(150L);
				this.atenderLLamada();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Llamada entrante.
	 *
	 * @param llamada
	 *            the llamada
	 */
	public void llamadaEntrante(Llamada llamada) {
		llamadas.add(llamada);
	}

	/**
	 * Liberar empleado.
	 *
	 * @param future
	 *            the future
	 * @param empleado
	 *            the empleado
	 *            
	 *            se crea un Runnable anonimo . este verifica si la llamada termino y devuelve a los empleados disponibles este usa un Pool de hilos diferentes
	 *            al que atiende la llamada mas que todo para asegurar como maximo 10 llamadas simultaneas.
	 */
	private void liberarEmpleado(@SuppressWarnings("rawtypes") final Future future, Empleado empleado) {
		Runnable comprobarSiTerminoLaLlamada = () -> {
			while (true) {
				if (future.isDone()) {
					break;
				}
			}
			try {

				System.out.println("\nse Libero " + empleado.getNombre());
				this.empleadosDisponibles.put(empleado);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		};
		this.executorLiberadorEmpl.execute(comprobarSiTerminoLaLlamada);
	}

	/**
	 * Gets the istance.
	 *
	 * @return the istance
	 */
	public static Dispacher getIstance() {
		if (dispacherSingelton == null) {
			dispacherSingelton = new Dispacher();
			return dispacherSingelton;
		} else {
			return dispacherSingelton;
		}

	}
	
	public int cantidadDeLLamadasRestantes() 
	{
		return this.llamadas.size();
	}

	/**
	 * Gets the empleados disponibles.
	 *
	 * @return the empleados disponibles
	 */
	public BlockingQueue<Empleado> getEmpleadosDisponibles() {
		return empleadosDisponibles;
	}

	/**
	 * Sets the empleados disponibles.
	 *
	 * @param empleadosDisponibles
	 *            the new empleados disponibles
	 */
	public void setEmpleadosDisponibles(BlockingQueue<Empleado> empleadosDisponibles) {
		this.empleadosDisponibles = empleadosDisponibles;
	}

}
