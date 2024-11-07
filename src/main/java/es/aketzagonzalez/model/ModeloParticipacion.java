package es.aketzagonzalez.model;

import java.util.Objects;

/**
 * The Class ModeloParticipacion.
 */
public class ModeloParticipacion {
	
	/** The evento. */
	private ModeloEvento evento;
	
	/** The id deportista. */
	private int idDeportista;
	
	/** The equipo. */
	private ModeloEquipo equipo;
	
	/** The edad. */
	private int edad;
	
	/** The medalla. */
	private String medalla;
	
	/**
	 * Instantiates a new modelo participacion.
	 *
	 * @param evento the evento
	 * @param idDeportista the id deportista
	 * @param equipo the equipo
	 * @param edad the edad
	 * @param medalla the medalla
	 */
	public ModeloParticipacion(ModeloEvento evento, int idDeportista, ModeloEquipo equipo, int edad, String medalla) {
		super();
		this.evento = evento;
		this.idDeportista = idDeportista;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(evento, idDeportista);
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
		ModeloParticipacion other = (ModeloParticipacion) obj;
		return Objects.equals(evento, other.evento) && idDeportista == other.idDeportista;
	}

	/**
	 * Gets the evento.
	 *
	 * @return the evento
	 */
	public ModeloEvento getEvento() {
		return evento;
	}

	/**
	 * Sets the evento.
	 *
	 * @param evento the new evento
	 */
	public void setEvento(ModeloEvento evento) {
		this.evento = evento;
	}

	/**
	 * Gets the id deportista.
	 *
	 * @return the id deportista
	 */
	public int getIdDeportista() {
		return idDeportista;
	}

	/**
	 * Sets the id deportista.
	 *
	 * @param idDeportista the new id deportista
	 */
	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	/**
	 * Gets the equipo.
	 *
	 * @return the equipo
	 */
	public ModeloEquipo getEquipo() {
		return equipo;
	}

	/**
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Gets the medalla.
	 *
	 * @return the medalla
	 */
	public String getMedalla() {
		return medalla;
	}
	
}
