package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * The Class ModeloDeportista.
 */
public class ModeloDeportista {
	
	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/** The sexo. */
	private String sexo;
	
	/** The peso. */
	private int peso;
	
	/** The altura. */
	private int altura;
	
	/** The foto. */
	private byte[] foto;
	
	/**
	 * Instantiates a new modelo deportista.
	 *
	 * @param nombre the nombre
	 * @param sexo the sexo
	 * @param peso the peso
	 * @param altura the altura
	 * @param foto the foto
	 */
	public ModeloDeportista(String nombre, String sexo, int peso, int altura, InputStream foto) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		fijarFoto(foto);
	}

	private void fijarFoto(InputStream foto) {
		if(foto!=null) {
			try {
				this.foto=foto.readAllBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public InputStream getFotoStream() {
		if(foto==null) {
			return null;
		}
		return new ByteArrayInputStream(foto);
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
		return Objects.hash(altura, nombre, peso, sexo);
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
		ModeloDeportista other = (ModeloDeportista) obj;
		return altura == other.altura && Objects.equals(nombre, other.nombre) && peso == other.peso
				&& Objects.equals(sexo, other.sexo);
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
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * Gets the altura.
	 *
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}
	
}
