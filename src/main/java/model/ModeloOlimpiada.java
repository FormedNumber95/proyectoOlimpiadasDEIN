package model;

import java.util.Objects;

public class ModeloOlimpiada {
	
	private int id;
	private String nombre;
	private int anio;
	private String temporada;
	private String ciudad;
	
	public ModeloOlimpiada(String nombre, int anio, String temporada, String ciudad) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, nombre, temporada);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}
	
}
