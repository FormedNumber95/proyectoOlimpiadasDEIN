package model;

import java.util.Objects;

public class ModeloParticipacion {
	
	private ModeloEvento evento;
	private int idDeportista;
	private ModeloEquipo equipo;
	private int edad;
	private String medalla;
	
	public ModeloParticipacion(ModeloEvento evento, int idDeportista, ModeloEquipo equipo, int edad, String medalla) {
		super();
		this.evento = evento;
		this.idDeportista = idDeportista;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, equipo, evento, idDeportista, medalla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloParticipacion other = (ModeloParticipacion) obj;
		return edad == other.edad && Objects.equals(equipo, other.equipo) && Objects.equals(evento, other.evento)
				&& idDeportista == other.idDeportista && Objects.equals(medalla, other.medalla);
	}

	public ModeloEvento getEvento() {
		return evento;
	}

	public void setEvento(ModeloEvento evento) {
		this.evento = evento;
	}

	public int getIdDeportista() {
		return idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	public ModeloEquipo getEquipo() {
		return equipo;
	}

	public int getEdad() {
		return edad;
	}

	public String getMedalla() {
		return medalla;
	}
	
}
