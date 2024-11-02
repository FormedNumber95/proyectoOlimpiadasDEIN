package model;

import java.util.Objects;

/**
 * The Class ModeloEvento.
 */
public class ModeloEvento {
	
	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/** The id olimpiada. */
	private int idOlimpiada;
	
	/** The id deporte. */
	private int id_deporte;
	
	/**
	 * Instantiates a new modelo evento.
	 *
	 * @param nombre the nombre
	 * @param idOlimpiada the id olimpiada
	 * @param id_deporte the id deporte
	 */
	public ModeloEvento(String nombre, int idOlimpiada, int id_deporte) {
		super();
		this.nombre = nombre;
		this.idOlimpiada = idOlimpiada;
		this.id_deporte = id_deporte;
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
		return Objects.hash(idOlimpiada, id_deporte, nombre);
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
		ModeloEvento other = (ModeloEvento) obj;
		return idOlimpiada == other.idOlimpiada && id_deporte == other.id_deporte
				&& Objects.equals(nombre, other.nombre);
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
	 * Gets the id olimpiada.
	 *
	 * @return the id olimpiada
	 */
	public int getIdOlimpiada() {
		return idOlimpiada;
	}

	/**
	 * Gets the id deporte.
	 *
	 * @return the id deporte
	 */
	public int getId_deporte() {
		return id_deporte;
	}
	
}
