package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloEvento;

/**
 * The Class DaoEvento.
 */
public class DaoEvento {
	
	/** The con. */
	private static Connection con;
	
	/**
	 * Aniadir.
	 *
	 * @param nombreEvento the nombre evento
	 * @param idOlimpiada the id olimpiada
	 * @param idDeporte the id deporte
	 */
	public static void aniadir(String nombreEvento,int idOlimpiada,int idDeporte) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Evento (nombre,id_olimpiada,id_deporte) VALUES (?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(insert);
			pstmt.setString(1,nombreEvento);
			pstmt.setInt(2, idOlimpiada);
			pstmt.setInt(3, idDeporte);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar.
	 *
	 * @param id the id
	 * @param nombreEvento the nombre evento
	 * @param idOlimpiada the id olimpiada
	 * @param idDeporte the id deporte
	 */
	public static void modificar(int id,String nombreEvento,int idOlimpiada,int idDeporte) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Evento SET nombre=?,id_olimpiada=?,id_deporte=? WHERE id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombreEvento);
			pstmt.setInt(2,idOlimpiada);
			pstmt.setInt(3,idDeporte);
			pstmt.setInt(4,id);
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
		String delete="DELETE FROM Evento WHERE id_evento=?";
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
	 * Lista eventos.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloEvento> listaEventos(){
		ObservableList<ModeloEvento>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_evento,nombre,id_olimpiada,id_deporte FROM Evento";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloEvento evento=new ModeloEvento(rs.getString("nombre"),rs.getInt("id_olimpiada"),rs.getInt("idDeporte"));
				evento.setId(rs.getInt("id_evento"));
				lst.add(evento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Modelo.
	 *
	 * @param id the id
	 * @return the modelo evento
	 */
	public static ModeloEvento modelo(int id) {
		con=ConexionBBDD.getConnection();
		String select="SELECT id_evento,nombre,id_olimpiada,id_deporte FROM Evento WHERE id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(select);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ModeloEvento evento=new ModeloEvento(rs.getString("nombre"),rs.getInt("id_olimpiada"),rs.getInt("idDeporte"));
				evento.setId(rs.getInt("id_evento"));
				return evento;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
