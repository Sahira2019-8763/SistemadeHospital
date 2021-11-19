//Clase Usuario

package Gestiones;

public class Usuario {

	private String Usuario;
	private String Clave;

	public Usuario(String usuario, String clave) {
		
		Usuario = usuario;
		Clave = clave;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

}
