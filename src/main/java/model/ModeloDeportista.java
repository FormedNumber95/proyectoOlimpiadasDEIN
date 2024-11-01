package model;

import java.io.InputStream;
import java.util.Objects;

public class ModeloDeportista {
	
	private int id;
	private String nombre;
	private String sexo;
	private int peso;
	private int altura;
	private InputStream foto;
	
	public ModeloDeportista(String nombre, String sexo, int peso, int altura, InputStream foto) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.foto = foto;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(altura, nombre, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloDeportista other = (ModeloDeportista) obj;
		return altura == other.altura && Objects.equals(nombre, other.nombre) && peso == other.peso
				&& Objects.equals(sexo, other.sexo);
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

	public String getSexo() {
		return sexo;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
	}

	public InputStream getFoto() {
		return foto;
	}
	
}
