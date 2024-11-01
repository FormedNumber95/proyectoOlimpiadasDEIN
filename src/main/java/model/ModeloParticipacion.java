package model;

import java.util.Objects;

public class ModeloParticipacion {
	
	private int idEvento;
	private int idDeportista;
	private int idEquipo;
	private int edad;
	private String medalla;
	
	public ModeloParticipacion(int idEvento, int idDeportista, int idEquipo, int edad, String medalla) {
		super();
		this.idEvento = idEvento;
		this.idDeportista = idDeportista;
		this.idEquipo = idEquipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, idDeportista, idEquipo, idEvento, medalla);
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
		return edad == other.edad && idDeportista == other.idDeportista && idEquipo == other.idEquipo
				&& idEvento == other.idEvento && Objects.equals(medalla, other.medalla);
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getIdDeportista() {
		return idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public int getEdad() {
		return edad;
	}

	public String getMedalla() {
		return medalla;
	}
	
}
