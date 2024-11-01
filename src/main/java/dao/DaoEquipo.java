package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloEquipo;


public class DaoEquipo {
	
	private static Connection con;
	
	public static void aniadir(String nombre, String iniciales) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Equipo (nombre,iniciales) VALUES (?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(insert);
			pstmt.setString(1,nombre);
			pstmt.setString(2,iniciales);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificar(int id,String nombre,String iniciales) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Equipo SET nombre=?,iniciales=? WHERE id_equipo=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombre);
			pstmt.setString(2,iniciales);
			pstmt.setInt(3,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminar(int id) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM Equipo WHERE id_equipo=?";
		try {
			PreparedStatement pstmt;
			pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ObservableList<ModeloEquipo> listaEquipos(){
		ObservableList<ModeloEquipo>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id_equipo,nombre,iniciales FROM Equipo";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ModeloEquipo equipo=new ModeloEquipo(rs.getString("nombre"),rs.getString("iniciales"));
				equipo.setId(rs.getInt("id_equipo"));
				lst.add(equipo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static ModeloEquipo crearModelo(int id) {
		con=ConexionBBDD.getConnection();
		String select="SELECT id_equipo,nombre,iniciales FROM Equipo WHERE id_equipo=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ModeloEquipo equipo=new ModeloEquipo(rs.getString("nombre"),rs.getString("iniciales"));
				equipo.setId(rs.getInt("id_equipo"));
				return equipo;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
