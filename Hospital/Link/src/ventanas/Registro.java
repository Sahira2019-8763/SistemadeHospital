//Registro Usuario

package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Registro extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCargo;
	private JTextField txtCorreo;
	private JPasswordField passContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 325, 486);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(33, 102, 63, 17);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(33, 171, 63, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(131, 99, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(131, 165, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(131, 228, 86, 20);
		panel.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(131, 291, 86, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cargo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(33, 231, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(33, 294, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contrase\u00F1a");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(33, 343, 88, 17);
		panel.add(lblNewLabel_4);
		
		passContraseña = new JPasswordField();
		passContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		passContraseña.setBounds(131, 340, 86, 20);
		panel.add(passContraseña);
		
		JCheckBox chckMostrar = new JCheckBox("Mostrar Contrase\u00F1a");
		chckMostrar.setBackground(new Color(95, 158, 160));
		chckMostrar.setForeground(new Color(0, 0, 0));
		chckMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckMostrar.isSelected())
				{
					passContraseña.setEchoChar((char)0);
				}
				else {
					passContraseña.setEchoChar('*');
				}
			}
		});
		chckMostrar.setBounds(102, 384, 130, 23);
		panel.add(chckMostrar);
		
		JLabel lblNewLabel_5 = new JLabel("Formulario de Registro");
		lblNewLabel_5.setFont(new Font("Book Antiqua", Font.BOLD, 19));
		lblNewLabel_5.setBounds(58, 42, 241, 32);
		panel.add(lblNewLabel_5);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String cargo = txtCargo.getText();
					String correo = txtCorreo.getText();
					String clave = String.valueOf(passContraseña.getPassword());
					
				
					
				cne = cn.getConnection();
				st = cne.createStatement();
				
				st.executeUpdate("insert into login ( Usuario, Clave, Nombre, Apellido, Cargo)"
				+ " values('"+correo+"', '"+clave+"', '"+nombre+"', '"+apellido+"', '"+cargo+"')");
				
				JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
				
				limpiarcajas();
				
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
				
			}
			}
		});
		btnRegistro.setBounds(102, 424, 130, 39);
		panel.add(btnRegistro);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ventana_Principal ven = new Ventana_Principal();
				ven.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(0, 0, 68, 23);
		panel.add(btnVolver);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Registro.class.getResource("/iconos/texturaregistro.jpg")));
		lblNewLabel_6.setBounds(0, 0, 325, 486);
		panel.add(lblNewLabel_6);
		
	}
	
	void limpiarcajas() {
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtCargo.setText("");
		txtCorreo.setText("");
		passContraseña.setText("");
	}
}
