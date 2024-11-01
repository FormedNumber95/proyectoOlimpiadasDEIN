package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloEvento;


public class DaoEvento {
	
	private static Connection con;
	
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
	
}
