package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloDeporte;

/**
 * The Class DaoDeporte.
 */
public class DaoDeporte {
	
	/** The con. */
	private static Connection con;
	
	/**
	 * Aniadir.
	 *
	 * @param nombreDeporte the nombre deporte
	 */
	public static void aniadir(String nombreDeporte) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Deporte (nombre) VALUES (?)";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(insert);
			pstmt.setString(1, nombreDeporte);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 */
	public static void modificar(int id,String nombre) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Deporte SET nombre=? WHERE id_deporte=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombre);
			pstmt.setInt(2,id);
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
	public static void eliminar (int id) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM Deporte WHERE id_deporte=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista deprotes.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloDeporte> listaDeprotes(){
		ObservableList<ModeloDeporte>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_deporte,nombre FROM Deporte";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloDeporte deporte=new ModeloDeporte(rs.getString("nombre"));
				deporte.setId(rs.getInt("id_deporte"));
				lst.add(deporte);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
