//Ventana Inicio de los roles

package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Roles.Conserje;
import Roles.Doctor;
import Roles.Enfermera;
import Roles.Farmaceuta;
import Roles.Secretaria;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana_Inicio {

	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;

	public JFrame frame;
	public static JTextField txtdatos;
	ResultSetMetaData ResMetDa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Inicio window = new Ventana_Inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana_Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 674, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 658, 480);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Enfermero/a");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(256, 275, 119, 33);
		panel.add(lblNewLabel_3);

		JButton btnEnfermero = new JButton("");
		btnEnfermero.setBounds(238, 162, 154, 107);
		panel.add(btnEnfermero);
		btnEnfermero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int code = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el codigo de acceso"));
 
				if (code == 8910) {

					String datos = txtdatos.getText();

					try {
						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta2 = ("Select Cargo from login Where Usuario = '" + datos + "'");
						rs = st.executeQuery(consulta2);
						String noms = "";
						String enfe = "Enfermero";
						String enfe2 = "Enfermera";

						while (rs.next()) {

							noms = rs.getString("Cargo");

						}


						if (noms.equals(enfe) || noms.equals(enfe2)) {

							Enfermera enf = new Enfermera();
							enf.setVisible(true);
							frame.dispose();

							try {
								

								cne = cn.getConnection();
								st = cne.createStatement();
								String consulta = ("Select Concat_ws ( ' ', Cargo,  Nombre , Apellido) AS nombre from login Where Usuario = '"
										+ datos + "'");
								rs = st.executeQuery(consulta);
								while (rs.next()) {
									String nom = rs.getString("nombre");
									enf.lblEnfermera.setText(nom);

								}
								
								try {
									cne = cn.getConnection();
									st = cne.createStatement();
									
									String consulta3 = ("Select Apellido from login where Usuario = '"+datos+"'");
									rs = st.executeQuery(consulta3);
									
									while (rs.next()) {
										
										String ape = rs.getString("Apellido");
										enf.txtape.setText(ape);

									}
									
								}catch(SQLException r) {
									
									r.printStackTrace();
								}		

							} catch (SQLException i) {

								i.printStackTrace();
							}

						} else {

							JOptionPane.showMessageDialog(null, "Usted no pertenece a esta area");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				} else {

					JOptionPane.showMessageDialog(null, "Codigo incorrecto");

				}

				

			}

		});

		// BOTON ENFERMERO
		btnEnfermero.setIcon(sIcono("/iconos/enfermera.png", btnEnfermero));
		btnEnfermero.setPressedIcon(Icono("/iconos/enfermera.png", btnEnfermero, 50, 50));
		transp(btnEnfermero);

		btnEnfermero.setIcon(sIcono("/iconos/enfermera.png", btnEnfermero));
		btnEnfermero.setPressedIcon(Icono("/iconos/enfermera.png", btnEnfermero, 50, 50));
		transp(btnEnfermero);

		JButton btnSecretario = new JButton("");
		btnSecretario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int code = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el codigo de acceso"));

				if (code == 5678) {

					String datos = txtdatos.getText();
					
					try {
						
						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta2 = ("Select Cargo from login Where Usuario = '" + datos + "'");
						rs = st.executeQuery(consulta2);
						String noms = "";
						String secr = "Secretario";
						String secre = "Secretaria";

						while (rs.next()) {

							noms = rs.getString("Cargo");

						}

						if (noms.equals(secr) || noms.equals(secre)) {
							Secretaria sec = new Secretaria();
							sec.setVisible(true);
							frame.dispose();

					try {

						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta = ("Select Concat_ws ( ' ', Cargo,  Nombre , Apellido) AS nombre from login Where Usuario = '"
								+ datos + "'");
						rs = st.executeQuery(consulta);
						while (rs.next()) {

							String nom = rs.getString("nombre");
							sec.lblSecretaria.setText(nom);

						}
					

					} catch (SQLException o) {

						o.printStackTrace();
					}
						} else {

							JOptionPane.showMessageDialog(null, "Usted no pertenece a esta area");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
									
				} else {

					JOptionPane.showMessageDialog(null, "Codigo incorrecto");

				}
				
			
			}
		});
		btnSecretario.setBounds(447, 166, 154, 103);
		panel.add(btnSecretario);

		// BOTON SECRETARIO
		btnSecretario.setIcon(sIcono("/iconos/secretaria.png", btnSecretario));
		btnSecretario.setPressedIcon(Icono("/iconos/secretaria.png", btnSecretario, 50, 50));
		transp(btnSecretario);

		btnSecretario.setIcon(sIcono("/iconos/secretaria.png", btnSecretario));
		btnSecretario.setPressedIcon(Icono("/iconos/secretaria.png", btnSecretario, 50, 50));
		transp(btnSecretario);

		JLabel lblNewLabel_4 = new JLabel("Secretario/a");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(496, 284, 105, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("Doctor/a");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(64, 284, 76, 24);
		panel.add(lblNewLabel_2);

		JButton btnDoctor = new JButton("");
		btnDoctor.setBounds(24, 162, 154, 107);
		panel.add(btnDoctor);
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  
				txtdatos.setVisible(false);
				int code = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el codigo de acceso"));
                 
				if (code == 1234) {
					String datos = txtdatos.getText();
					
					try {
						
						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta2 = ("Select Cargo from login Where Usuario = '" + datos + "'");
						rs = st.executeQuery(consulta2);
						String noms = "";
						String doc = "Doctor";
						String doct = "Doctora";

						while (rs.next()) {

							noms = rs.getString("Cargo");

						}

						if (noms.equals(doc) || noms.equals(doct)) {
							Doctor dr = new Doctor();
							dr.setVisible(true);
							frame.dispose();
							
					try {

						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta = ("Select Concat_ws ( ' ', Cargo,  Nombre , Apellido) AS nombre from login Where Usuario = '"
								+ datos + "'");
						rs = st.executeQuery(consulta);
						
						while (rs.next()) {

							String nom = rs.getString("nombre");
							dr.lblDoctor.setText(nom);

						}
						
					try {
						cne = cn.getConnection();
						st = cne.createStatement();
						
						String consulta3 = ("Select Apellido from login where Usuario = '"+datos+"'");
						rs = st.executeQuery(consulta3);
						
						while (rs.next()) {
							
							String ape = rs.getString("Apellido");
							dr.txtape.setText(ape);

						}
						
					}catch(SQLException r) {
						
						r.printStackTrace();
					}	
					  

					} catch (SQLException e) {

						e.printStackTrace();
					}
						} else {
							
							JOptionPane.showMessageDialog(null, "Usted no pertenece a esta area");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
									
				} else {
					
					JOptionPane.showMessageDialog(null, "Codigo incorrecto");
				}
				
				
			}
		});
		btnDoctor.setMargin(new Insets(2, 8, 2, 10));
		btnDoctor.setInheritsPopupMenu(true);
		btnDoctor.setIgnoreRepaint(true);
		btnDoctor.setMinimumSize(new Dimension(40, 4));
		btnDoctor.setMaximumSize(new Dimension(25, 17));
		btnDoctor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDoctor.setToolTipText("Este es el boton para el doctor");

		// BOTON DOCTOR
		btnDoctor.setIcon(sIcono("/iconos/doctor.png", btnDoctor));
		btnDoctor.setPressedIcon(Icono("/iconos/doctor.png", btnDoctor, 50, 50));
		transp(btnDoctor);

		btnDoctor.setIcon(sIcono("/iconos/doctor.png", btnDoctor));
		btnDoctor.setPressedIcon(Icono("/iconos/doctor.png", btnDoctor, 50, 50));
		transp(btnDoctor);

		JLabel lblNewLabel_5 = new JLabel("Farmaceuta");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(160, 443, 76, 26);
		panel.add(lblNewLabel_5);

		JButton btnFarmaceuta = new JButton("");
		btnFarmaceuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int code = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el codigo de acceso"));

				if (code == 1112) {

					String datos = txtdatos.getText();
					
					try {
						
						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta2 = ("Select Cargo from login Where Usuario = '" + datos + "'");
						rs = st.executeQuery(consulta2);
						String noms = "";
						String far = "Farmaceuta";

						while (rs.next()) {

							noms = rs.getString("Cargo");

						}

						if (noms.equals(far)) {
							Farmaceuta farm = new Farmaceuta();
							farm.setVisible(true);
							frame.dispose();
							
					try {

						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta = ("Select Concat_ws ( ' ', Cargo,  Nombre , Apellido) AS nombre from login Where Usuario = '"
								+ datos + "'");
						rs = st.executeQuery(consulta);
						while (rs.next()) {

							String nom = rs.getString("nombre");
							farm.lblFarmaceuta.setText(nom);
							farm.listar();

						}
					

					} catch (SQLException a) {

						a.printStackTrace();
					}
						} else {

							JOptionPane.showMessageDialog(null, "Usted no pertenece a esta area");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
									
				} else {

					JOptionPane.showMessageDialog(null, "Codigo incorrecto");

				}
				
				
				
			}
		});
		btnFarmaceuta.setBounds(124, 340, 134, 92);
		panel.add(btnFarmaceuta);

		// BOTON Farmaceuta

		btnFarmaceuta.setIcon(sIcono("/iconos/vendedor.png", btnFarmaceuta));
		btnFarmaceuta.setPressedIcon(Icono("/iconos/vendedor.png", btnFarmaceuta, 50, 50));
		transp(btnFarmaceuta);

		btnFarmaceuta.setIcon(sIcono("/iconos/vendedor.png", btnFarmaceuta));
		btnFarmaceuta.setPressedIcon(Icono("/iconos/vendedor.png", btnFarmaceuta, 50, 50));
		transp(btnFarmaceuta);

		JButton btnConserje = new JButton("");
		btnConserje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int code = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el codigo de acceso"));

				if (code == 1314) {

					String datos = txtdatos.getText();
					
					try {
						
						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta2 = ("Select Cargo from login Where Usuario = '" + datos + "'");
						rs = st.executeQuery(consulta2);
						String noms = "";
						String doc = "Gerente Dep Limpieza";

						while (rs.next()) {

							noms = rs.getString("Cargo");

						}

						if (noms.equals(doc)) {
							Conserje con = new Conserje();
							con.setVisible(true);
							frame.dispose();
							
					try {

						cne = cn.getConnection();
						st = cne.createStatement();
						String consulta = ("Select Concat_ws ( ' ', Cargo,  Nombre , Apellido) AS nombre from login Where Usuario = '"
								+ datos + "'");
						rs = st.executeQuery(consulta);
						while (rs.next()) {

							String nom = rs.getString("nombre");
							con.lblGerente.setText(nom);

						}
					

					} catch (SQLException u) {

						u.printStackTrace();
					}
						} else {

							JOptionPane.showMessageDialog(null, "Usted no pertenece a esta area");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
									
				} else {

					JOptionPane.showMessageDialog(null, "Codigo incorrecto");

				}
				
			}
		});
		btnConserje.setBounds(369, 338, 154, 94);
		panel.add(btnConserje);

		// BOTON CONSERJE

		btnConserje.setIcon(sIcono("/iconos/cubeta.png", btnConserje));
		btnConserje.setPressedIcon(Icono("/iconos/cubeta.png", btnConserje, 50, 50));
		transp(btnConserje);

		btnConserje.setIcon(sIcono("/iconos/cubeta.png", btnConserje));
		btnConserje.setPressedIcon(Icono("/iconos/cubeta.png", btnConserje, 50, 50));
		transp(btnConserje);

		JLabel lblNewLab = new JLabel("Gerente de Dep. Limpieza");
		lblNewLab.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLab.setBounds(364, 443, 186, 26);
		panel.add(lblNewLab);

		JLabel lblNewLabel = new JLabel("Hospital General ");
		lblNewLabel.setBounds(178, 11, 282, 43);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 32));

		JLabel lblNewLabel_1 = new JLabel("M & M");
		lblNewLabel_1.setBounds(230, 55, 175, 73);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 46));

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(559, 11, 89, 33);
		panel.add(btnSalir);

		txtdatos = new JTextField();
		txtdatos.setEditable(false);
		txtdatos.setEnabled(false);
		txtdatos.setBounds(593, 11, 8, 4);
		panel.add(txtdatos);
		txtdatos.setColumns(10);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 658, 480);
		panel.add(lblFondo);

		ImageIcon imgs1 = new ImageIcon(getClass().getResource("/iconos/texturainicio.jpg"));
		ImageIcon imag1 = new ImageIcon(
				imgs1.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(imag1);
	}

	public Icon sIcono(String URL, JButton boton) {
		ImageIcon icono1 = new ImageIcon(getClass().getResource(URL));

		int ancho1 = boton.getWidth();
		int alto1 = boton.getHeight();
		ImageIcon icon1 = new ImageIcon(icono1.getImage().getScaledInstance(ancho1, alto1, Image.SCALE_DEFAULT));
		return icon1;
	}

	public Icon Icono(String URL, JButton boton, int ancho, int altura) {
		ImageIcon icono1 = new ImageIcon(getClass().getResource(URL));

		int w = boton.getWidth() - ancho;
		int h = boton.getHeight() - altura;
		ImageIcon icon1 = new ImageIcon(icono1.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		return icon1;
	}

	public void transp(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
}
