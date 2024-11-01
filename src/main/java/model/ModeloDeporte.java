package model;

import java.util.Objects;

public class ModeloDeporte {

	private int id;
	private String nombre;
	
	public ModeloDeporte(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
}
