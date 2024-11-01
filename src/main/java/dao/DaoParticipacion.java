package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloParticipacion;


public class DaoParticipacion {
	
	private static Connection con;
	
	public static void aniadir(int idDeportista,int idEvento,int idEquipo,int edad,String medalla) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Participacion (id_deportista,id_evento,id_equipo,edad,medalla) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(insert);
			pstmt.setInt(1,idDeportista);
			pstmt.setInt(2, idEvento);
			pstmt.setInt(3,idEquipo);
			pstmt.setInt(4, edad);
			pstmt.setString(5, medalla);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificar(int idDeportista,int idEvento,int idEquipo,int edad,String medalla) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Participacion SET id_equipo=?,edad=?,medalla=? WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(update);
			pstmt.setInt(1,idEquipo);
			pstmt.setInt(2, edad);
			pstmt.setString(3, medalla);
			pstmt.setInt(4,idDeportista);
			pstmt.setInt(5, idEvento);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminar(int idDeportista,int idEvento) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM Participacion WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,idDeportista);
			pstmt.setInt(2,idEvento);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ObservableList<ModeloParticipacion>listaParticipaciones(int idDeportista){
		ObservableList<ModeloParticipacion>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_deportista,id_evento,id_equipo,edad,medalla FROM Participacion WHERE id_deportista=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			pstmt.setInt(1,idDeportista);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloParticipacion participacion=new ModeloParticipacion(DaoEvento.modelo(rs.getInt("id_evento")),rs.getInt("id_deportista"),DaoEquipo.crearModelo(rs.getInt("id_evento")),rs.getInt("edad"),rs.getString("medalla"));
				lst.add(participacion);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
