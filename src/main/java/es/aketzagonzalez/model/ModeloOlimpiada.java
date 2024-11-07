package es.aketzagonzalez.model;

import java.util.Objects;

/**
 * The Class ModeloOlimpiada.
 */
public class ModeloOlimpiada {
	
	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/** The anio. */
	private int anio;
	
	/** The temporada. */
	private String temporada;
	
	/** The ciudad. */
	private String ciudad;
	
	/**
	 * Instantiates a new modelo olimpiada.
	 *
	 * @param nombre the nombre
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 */
	public ModeloOlimpiada(String nombre, int anio, String temporada, String ciudad) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
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
		return Objects.hash(anio, ciudad, nombre, temporada);
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
		ModeloOlimpiada other = (ModeloOlimpiada) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(temporada, other.temporada);
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
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Gets the temporada.
	 *
	 * @return the temporada
	 */
	public String getTemporada() {
		return temporada;
	}

	/**
	 * Gets the ciudad.
	 *
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	
}
