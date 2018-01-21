package com.ar.empleado;

/**
 * The Class Empleado.
 *  @author Juan valero
 */
public class Empleado implements Runnable, Comparable<Empleado> {

	/** The prioridad. */
	private PrioridadEmpleado prioridad;
	
	/** The llamada. */
	private Llamada llamada;
	
	/** The nombre. */
	private String nombre;

	/**
	 * Instantiates a new empleado.
	 *
	 * @param prioridad the prioridad
	 * @param llamada the llamada
	 * @param nombre the nombre
	 */
	public Empleado(PrioridadEmpleado prioridad, Llamada llamada, String nombre) {
		super();
		this.prioridad = prioridad;
		this.llamada = llamada;
		this.nombre = nombre;
	}

	/**
	 * Instantiates a new empleado.
	 *
	 * @param prioridad the prioridad
	 */
	public Empleado(PrioridadEmpleado prioridad) {
		super();
		this.prioridad = prioridad;
	}

	/**
	 * Instantiates a new empleado.
	 *
	 * @param prioridad the prioridad
	 * @param nombre the nombre
	 */
	public Empleado(PrioridadEmpleado prioridad, String nombre) {
		super();
		this.prioridad = prioridad;
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Empleado empleado2) {
		if (this.getPrioridad().getPrioridad() < empleado2.getPrioridad().getPrioridad()) {
			return -1;
		}
		if (this.getPrioridad().getPrioridad() > empleado2.getPrioridad().getPrioridad()) {
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("static-access")
	public void run() {
		try {
			System.out.println("\nEmpleado " + this.getNombre() + " atendiendo llamada\n");
			Thread.sleep(llamada.getDuracion());
			System.out.println("\nLlamada del empleado : "+this.getNombre()+ " ### finalizada. Duracion : " + llamada.getDuracion() / 1000 + "segundos");
		} catch (InterruptedException e) {
			System.out.println("Error atendiendo llamada del empleado" + this.getNombre());
			e.printStackTrace();
		}
	}

	/**
	 * Atenderllamada.
	 *
	 * @param llamada the llamada
	 */
	public void atenderllamada(Llamada llamada) {
		this.llamada = llamada;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public PrioridadEmpleado getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad the new prioridad
	 */
	public void setPrioridad(PrioridadEmpleado prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Gets the llamada.
	 *
	 * @return the llamada
	 */
	public Llamada getLlamada() {
		return llamada;
	}

	/**
	 * Sets the llamada.
	 *
	 * @param llamada the new llamada
	 */
	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
