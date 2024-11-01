package model;

import java.util.Objects;

public class ModeloEvento {
	
	private int id;
	private String nombre;
	private int idOlimpiada;
	private int id_deporte;
	
	public ModeloEvento(String nombre, int idOlimpiada, int id_deporte) {
		super();
		this.nombre = nombre;
		this.idOlimpiada = idOlimpiada;
		this.id_deporte = id_deporte;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOlimpiada, id_deporte, nombre);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getIdOlimpiada() {
		return idOlimpiada;
	}

	public int getId_deporte() {
		return id_deporte;
	}
	
}
