package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloDeportista;

/**
 * The Class DaoDeportista.
 */
public class DaoDeportista {
	
	/** The con. */
	private static Connection con;
	
	/**
	 * Aniadir.
	 *
	 * @param nombreDeportista the nombre deportista
	 * @param sexo the sexo
	 * @param peso the peso
	 * @param altura the altura
	 * @param foto the foto
	 */
	public static void aniadir(String nombreDeportista,char sexo,int peso,int altura,InputStream foto) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Deportista (nombre,sexo,peso,altura,foto) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=con.prepareStatement(insert);
			pstmt.setString(1, nombreDeportista);
			pstmt.setString(2, sexo+"");
			pstmt.setInt(3, peso);
			pstmt.setInt(4, altura);
			pstmt.setBinaryStream(5,foto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar.
	 *
	 * @param id the id
	 * @param nombreDeportista the nombre deportista
	 * @param sexo the sexo
	 * @param peso the peso
	 * @param altura the altura
	 * @param foto the foto
	 */
	public static void modificar(int id,String nombreDeportista,char sexo,int peso,int altura,InputStream foto) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Deportista SET nombre=?,sexo=?,peso=?,altura=?,foto=? WHERE id_deportista=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombreDeportista);
			pstmt.setString(2,sexo+"");
			pstmt.setInt(3, peso);
			pstmt.setInt(4,altura);
			pstmt.setBinaryStream(5,foto);
			pstmt.setInt(6,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	public static void eliminar(int id) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM Deportista WHERE id_deportista=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista deportistas.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloDeportista>listaDeportistas(){
		ObservableList<ModeloDeportista> lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_deportista,nombre,sexo,peso,altura,foto FROM Deportista";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloDeportista deportista=new ModeloDeportista(rs.getString("nombre"),rs.getString("sexo"),rs.getInt("peso"),rs.getInt("altura"),rs.getBinaryStream("foto"));
				deportista.setId(rs.getInt("id_deportista"));
				lst.add(deportista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
