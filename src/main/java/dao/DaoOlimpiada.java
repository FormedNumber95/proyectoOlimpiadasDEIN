package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloOlimpiada;

/**
 * The Class DaoOlimpiada.
 */
public class DaoOlimpiada {

	/** The con. */
	private static Connection con;
	
	/**
	 * Aniadir.
	 *
	 * @param nombre the nombre
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 */
	public static void aniadir(String nombre, int anio,String temporada,String ciudad) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Olimpiada (nombre,anio,temporada,ciudad) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(insert);
			pstmt.setString(1,nombre);
			pstmt.setInt(2, anio);
			pstmt.setString(3,temporada);
			pstmt.setString(4,ciudad);
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
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 */
	public static void modificar(int id,String nombre, int anio,String temporada,String ciudad) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Olimpiada SET nombre=?,anio=?,temporada=?,ciudad=? WHERE id_olimpiada=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombre);
			pstmt.setInt(2, anio);
			pstmt.setString(3,temporada);
			pstmt.setString(4,ciudad);
			pstmt.setInt(5,id);
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
		String delete="DELETE FROM Olimpiada WHERE id_olimpiada=?";
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
	 * Lista olimpiadas.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloOlimpiada> listaOlimpiadas() {
		ObservableList<ModeloOlimpiada>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_olimpiada,nombre,anio,temporada,ciudad FROM Olimpiada";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloOlimpiada olimpiada=new ModeloOlimpiada(rs.getString("nombre"),rs.getInt("anio"),rs.getString("temporada"),rs.getString("ciudad"));
				olimpiada.setId(rs.getInt("id_olimpiada"));
				lst.add(olimpiada);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
