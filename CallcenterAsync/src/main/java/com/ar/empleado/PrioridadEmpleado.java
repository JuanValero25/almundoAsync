package com.ar.empleado;

// TODO: Auto-generated Javadoc
/**
 * The Enum PrioridadEmpleado.
 * @author Juan valero
 */
public enum PrioridadEmpleado {
	

	OPERADOR(1),
	SUPERVISOR(2),
	DIRECTOR(3);
	private int prioridad;
	
	/**
	 * Instantiates a new prioridad empleado.
	 *
	 * @param prioridad the prioridad
	 */
	PrioridadEmpleado(int prioridad){
		this.prioridad=prioridad;
	}
	
	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public int getPrioridad(){
		return prioridad;
	}

}
