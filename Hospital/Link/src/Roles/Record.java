package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ventanas.Conexion;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Record extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField txtID;
	private JLabel lbld, lble ;
	public static JLabel lbldoc;
	public static JTextField txtdoct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Record frame = new Record();
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
	public Record() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 513, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblrecord = new JLabel("New label");
		
		lblrecord.setBounds(368, 164, 121, 104);
		panel.add(lblrecord);
		
		ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/dosier.png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(lblrecord.getWidth(), lblrecord.getHeight(), Image.SCALE_DEFAULT));
		lblrecord.setIcon(imag4);

		
		JLabel lblNewLabel = new JLabel("Record de Pacientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(94, 26, 292, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID Paciente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(24, 111, 85, 17);
		panel.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setBounds(123, 109, 86, 20);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Paciente:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(34, 188, 75, 14);
		panel.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 151, 478, 2);
		panel.add(separator);
		
		JLabel lblNombre = new JLabel("");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setForeground(new Color(255, 0, 0));
		lblNombre.setBounds(131, 184, 215, 25);
		panel.add(lblNombre);
		
		JLabel lblNewLabel_4 = new JLabel("Con Historial Medico:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(33, 248, 227, 17);
		panel.add(lblNewLabel_4);
		
		JLabel lblm = new JLabel("");
		lblm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblm.setBounds(140, 310, 255, 25);
		panel.add(lblm);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    
				int id = Integer.parseInt(txtID.getText());
			
				try {

					cne = cn.getConnection();
					st = cne.createStatement();
					String consulta = ("Select Concat_ws ( ' ', Nombre , Apellido) AS nombre from paciente Where ID = '"
							+ id + "'");
					rs = st.executeQuery(consulta);
					
					while (rs.next()) {

						String nom = rs.getString("nombre");
					    lblNombre.setText(nom);
					}
				}catch(SQLException y) {
						y.printStackTrace();
					}
					
					try {
						cne = cn.getConnection();
						st = cne.createStatement();
						
						String consulta2 = ("Select  Motivo from paciente Where ID = '"
								+ id + "'");
						rs = st.executeQuery(consulta2);
						
						while (rs.next()) {
							
							String m = rs.getString("Motivo");
							lblm.setText(m);

						}
	                     }catch(SQLException r) {
						
					 	  r.printStackTrace();
					    }	
					
						try {
							cne = cn.getConnection();
							st = cne.createStatement();
							
							String consulta3 = ("Select Diagnostico from paciente Where ID = '"
									+ id + "'");
							rs = st.executeQuery(consulta3);
							
							while (rs.next()) {
								
								String d = rs.getString("Diagnostico");
								lbld.setText(d);
							}
						}catch(SQLException i) {
								i.printStackTrace();
							}
						
					try {
						cne = cn.getConnection();
						st = cne.createStatement();
						
						String consulta4 = ("Select EstadoUrgencia from paciente Where ID = '"
								+ id + "'");
						rs = st.executeQuery(consulta4);
						
						while (rs.next()) {
							
							String ble = rs.getString("EstadoUrgencia");
							lble.setText(ble);

						}
					
				}catch(SQLException k) {
					
					k.printStackTrace();
			
			}
			
			}
		});
		btnBuscar.setBounds(264, 108, 89, 23);
		panel.add(btnBuscar);
		
		JLabel lblNewLabel_3 = new JLabel("Motivos:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(45, 310, 62, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Diagnostico:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(45, 374, 85, 21);
		panel.add(lblNewLabel_5);
		
		lbld = new JLabel("");
		lbld.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbld.setBounds(140, 374, 222, 25);
		panel.add(lbld);
		
		JLabel lblNewLabel_7 = new JLabel("Estado de Urgencia:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(45, 431, 132, 17);
		panel.add(lblNewLabel_7);
		
		lble = new JLabel("");
		lble.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lble.setBounds(187, 428, 215, 20);
		panel.add(lble);
		
		txtdoct = new JTextField();
		txtdoct.setEnabled(false);
		txtdoct.setEditable(false);
		txtdoct.setBounds(10, 67, 0, 2);
		panel.add(txtdoct);
		txtdoct.setColumns(10);
		
		lbldoc = new JLabel("");
		lbldoc.setBounds(10, 26, 46, -9);
		panel.add(lbldoc);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ape = txtdoct.getText();
				try {
					
					cne = cn.getConnection();
					st = cne.createStatement();
					String consulta2 = ("Select Cargo from login Where Apellido = '" + ape + "'");
					rs = st.executeQuery(consulta2);
					String noms = "";
					String doc = "Doctor";
					String doct = "Doctora";
					String enf = "Enfermero";
					String enfer = "Enfermera";

					while (rs.next()) {

						noms = rs.getString("Cargo");
					}

				if (noms.equals(doc) || noms.equals(doct)) {
				Doctor dr = new Doctor();
				dr.setVisible(true);
				
				String docs = txtdoct.getText();
				dr.txtape.setText(docs);
				
				String nom = lbldoc.getText();
				dr.lblDoctor.setText(nom);
				
				dispose();
				}
				
				else if(noms.equals(enf) || noms.equals(enfer)) {
					Enfermera en = new Enfermera();
					en.setVisible(true);
					
					String docs = txtdoct.getText();
					en.txtape.setText(docs);
					
					String nom = lbldoc.getText();
					en.lblEnfermera.setText(nom);
					
					dispose();
					
					
				}
			}catch(SQLException x) {
				x.printStackTrace();
			}
			}
			
		});
		btnVolver.setBounds(428, 11, 75, 32);
		panel.add(btnVolver);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Record.class.getResource("/iconos/recordPac.jpg")));
		lblNewLabel_6.setBounds(0, 0, 524, 478);
		panel.add(lblNewLabel_6);
	}
}
