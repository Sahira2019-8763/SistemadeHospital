//Clase Gestion_Usuario

package Gestiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ventanas.Conexion;



public class Gestion_Usuario {

	public Usuario obtenerUsuario(Usuario usu) {

		Usuario usuario = null;
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pst = null;

		try {
			
			con = Conexion.getConnection();
			
			String sql= "select * from login where Usuario = ? and Clave = ?";
			pst =  con.prepareStatement(sql);
			
			pst.setString(1, usu.getUsuario());
			pst.setString(2, usu.getClave());
			
			rs= pst.executeQuery();
			
			
			while (rs.next()) {
			usuario = new Usuario(rs.getString(1), rs.getString(2));
			JOptionPane.showMessageDialog(null, "Datos obtenidos correctamente");
			}
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al obtener el usuario");
			
			
			
		}

		return usuario;

	}

}
