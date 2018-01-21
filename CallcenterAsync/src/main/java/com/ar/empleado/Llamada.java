package com.ar.empleado;
import java.util.concurrent.ThreadLocalRandom;


/**
 * The Class Llamada.
 *  @author Juan valero
 */
public class Llamada {

	/** The Constant MIN_DURACION. */
	private static final int MIN_DURACION = 5;
	
	/** The Constant MAX_DURACION. */
	private static final int MAX_DURACION = 10;
	
	/** The duracion. */
	private int duracion;

	/**
	 * Instantiates a new llamada.
	 */
	public Llamada() {
		duracion = (ThreadLocalRandom.current().nextInt(MIN_DURACION, MAX_DURACION + 1)) * 1000;
	}

	/**
	 * Gets the duracion.
	 *
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Sets the duracion.
	 *
	 * @param duracion the new duracion
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
