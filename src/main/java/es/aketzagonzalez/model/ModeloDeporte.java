package es.aketzagonzalez.model;

import java.util.Objects;

/**
 * The Class ModeloDeporte.
 */
public class ModeloDeporte {

	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Instantiates a new modelo deporte.
	 *
	 * @param nombre the nombre
	 */
	public ModeloDeporte(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombre;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloDeporte other = (ModeloDeporte) obj;
		return Objects.equals(nombre, other.nombre);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
}
