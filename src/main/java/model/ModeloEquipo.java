package model;

import java.util.Objects;

public class ModeloEquipo {
	
	private int id;
	private String nombre;
	private String iniciales;
	
	public ModeloEquipo(String nombre, String iniciales) {
		super();
		this.nombre = nombre;
		this.iniciales = iniciales;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iniciales, nombre);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIniciales() {
		return iniciales;
	}
	
}
