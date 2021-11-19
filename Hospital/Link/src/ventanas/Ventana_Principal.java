//Ventana Principal, LOGIN

package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Gestiones.Gestion_Usuario;
import Gestiones.Usuario;
import Roles.Doctor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class Ventana_Principal extends JFrame {

	private JPanel contentPane;
	public  JTextField txtUsuario;
	private JPasswordField passClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Principal frame = new Ventana_Principal();
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
	public Ventana_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 381, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(34, 171, 76, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(34, 229, 103, 14);
		panel.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(142, 169, 162, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(" \u00BFDesea registrarse?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Registro regis = new Registro();
				regis.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setBounds(129, 369, 175, 20);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ingresar();
				
			}
		});
		btnNewButton.setBounds(144, 306, 99, 43);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Hospital General ");
		lblNewLabel_3.setForeground(new Color(0, 51, 102));
		lblNewLabel_3.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 32));
		lblNewLabel_3.setBounds(53, 24, 282, 43);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("M & M");
		lblNewLabel_1_1.setForeground(new Color(0, 51, 255));
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 46));
		lblNewLabel_1_1.setBounds(114, 63, 175, 73);
		panel.add(lblNewLabel_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(133, 387, 136, 2);
		panel.add(separator);
		
		passClave = new JPasswordField();
		passClave.setBounds(147, 227, 157, 20);
		panel.add(passClave);
		
		JCheckBox chckMostrar = new JCheckBox("Mostrar contrase\u00F1a");
		chckMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckMostrar.setBackground(new Color(95, 158, 160));
		chckMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckMostrar.isSelected())
				{
					passClave.setEchoChar((char)0);
				}
				else {
					passClave.setEchoChar('*');
				}
			}
		});
		chckMostrar.setBounds(114, 263, 145, 23);
		panel.add(chckMostrar);
		
		JLabel imgLogin = new JLabel("");
		imgLogin.setIcon(new ImageIcon(Ventana_Principal.class.getResource("/iconos/texturalogin.jpg")));
		imgLogin.setBounds(0, 0, 381, 418);
		panel.add(imgLogin);
	}
	
	public void ingresar() {
		
		String usuario = txtUsuario.getText();
		String clave = String.valueOf (passClave.getPassword());
		
		Gestion_Usuario gestionUsuario = new Gestion_Usuario();
		
		Usuario usuario2 = new Usuario();
		usuario2.setUsuario(usuario);
		usuario2.setClave(clave);
		
		Usuario usu = gestionUsuario.obtenerUsuario(usuario2);
		if (usu!=null) {
			
			System.out.print("Bienvenido!");
			Ventana_Inicio ini = new Ventana_Inicio();
			ini.frame.setVisible(true);
			
			
			String info = txtUsuario.getText();
			ini.txtdatos.setText(info);
			
			ini.txtdatos.setVisible(false);
			this.dispose();
			
		} else {
			
			JOptionPane.showMessageDialog(contentPane, "Datos invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
