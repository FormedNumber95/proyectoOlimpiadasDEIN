package model;

import java.util.Objects;

/**
 * The Class ModeloEquipo.
 */
public class ModeloEquipo {
	
	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/** The iniciales. */
	private String iniciales;
	
	/**
	 * Instantiates a new modelo equipo.
	 *
	 * @param nombre the nombre
	 * @param iniciales the iniciales
	 */
	public ModeloEquipo(String nombre, String iniciales) {
		super();
		this.nombre = nombre;
		this.iniciales = iniciales;
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
		return Objects.hash(iniciales, nombre);
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
		ModeloEquipo other = (ModeloEquipo) obj;
		return Objects.equals(iniciales, other.iniciales) && Objects.equals(nombre, other.nombre);
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

	/**
	 * Gets the iniciales.
	 *
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}
	
}
