//Conexion a la base de datos

package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Conexion {
	
 public static Connection getConnection() {
	 
	Connection connec = null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3308/hospital";
		String usuario = "root";
		String contraseña = "";
		System.out.print("Coneccion establecida");
		connec = DriverManager.getConnection(url, usuario, contraseña);
		
	} catch (ClassNotFoundException e) {
		
		System.out.print("Error al cargar el controlador");
		e.printStackTrace();
	}
	
	catch(SQLException e) {
		JOptionPane.showMessageDialog(null, "Coneccion no establecida" );
		e.printStackTrace();
	}
	
	return connec;

}
 	
}

  
		

	

