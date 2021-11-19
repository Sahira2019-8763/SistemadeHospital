//Emergencia

package Departamentos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Roles.Enfermera;
import Roles.Ingresar_Paciente;
import ventanas.Conexion;

import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Emergencia extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	
	
	private JPanel contentPane;
	private JTextField txtNombreP;
	private JTextField txtCedulaP;
	 ButtonGroup gupo1;
	 ButtonGroup gupo2;
	private JTextField txtApellidoP;
	JTextArea txtMotivoP;
	JComboBox comboBox;
	JSpinner spinner;
	JRadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9;
	private JTextField otro;
	private String seguros;
	private String estados;
	public static JLabel lblenfer;
	private JTextField txtTipo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emergencia frame = new Emergencia();
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
	public Emergencia() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 445, 701);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMERGENCIA");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 30));
		lblNewLabel.setBounds(77, 0, 221, 65);
		panel.add(lblNewLabel);
		
		JLabel ic1 = new JLabel("");
		ic1.setBounds(293, 11, 68, 47);
		panel.add(ic1);
		ImageIcon imgs1 = new ImageIcon(getClass().getResource("/iconos/farmacia.png"));
		ImageIcon imag1 = new ImageIcon(imgs1.getImage().getScaledInstance(ic1.getWidth(), ic1.getHeight(), Image.SCALE_DEFAULT));
		ic1.setIcon(imag1);
		
		JLabel lblNewLabel_1 = new JLabel("Datos del paciente");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(150, 69, 274, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 117, 61, 15);
		panel.add(lblNewLabel_2);
		
		txtNombreP = new JTextField();
		txtNombreP.setBounds(64, 115, 101, 20);
		panel.add(txtNombreP);
		txtNombreP.setColumns(10);
		
		JLabel lbb = new JLabel("Apellido");
		lbb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbb.setBounds(10, 155, 61, 15);
		panel.add(lbb);
		
		txtApellidoP = new JTextField();
		txtApellidoP.setBounds(64, 153, 101, 20);
		panel.add(txtApellidoP);
		txtApellidoP.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Sexo");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 195, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Edad");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 231, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Motivo ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(293, 117, 74, 14);
		panel.add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 134, 155, 73);
		panel.add(scrollPane);
		
	    txtMotivoP = new JTextArea();
		scrollPane.setViewportView(txtMotivoP);
		
		JLabel lblNewLabel_6 = new JLabel("Estado de emergencia");
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setBackground(Color.BLUE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(150, 458, 155, 28);
		panel.add(lblNewLabel_6);
		
		 rb7 = new JRadioButton("URGENTE");
		 rb7.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		estados = "Urgente";
		 	}
		 });
		rb7.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb7.setBounds(18, 600, 109, 23);
		panel.add(rb7);
		
		 rb8 = new JRadioButton("INTERMEDIO");
		 rb8.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		estados = "Intermedio";
		 	}
		 });
		rb8.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb8.setBounds(150, 600, 109, 23);
		panel.add(rb8);
		
		 rb9 = new JRadioButton("NO URGENTE");
		 rb9.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		estados = "No Urgente";
		 	}
		 });
		rb9.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb9.setBounds(293, 600, 109, 23);
		panel.add(rb9);
		
		JLabel bt1 = new JLabel("");
		bt1.setBounds(10, 517, 74, 65);
		panel.add(bt1);
		ImageIcon imgs2 = new ImageIcon(getClass().getResource("/iconos/boton rojo.png"));
		ImageIcon imag2 = new ImageIcon(imgs2.getImage().getScaledInstance(bt1.getWidth(), bt1.getHeight(), Image.SCALE_DEFAULT));
		bt1.setIcon(imag2);
		JLabel bt2 = new JLabel("");
		bt2.setBounds(170, 517, 74, 65);
		panel.add(bt2);
		ImageIcon imgs3 = new ImageIcon(getClass().getResource("/iconos/boton amarillo.png"));
		ImageIcon imag3 = new ImageIcon(imgs3.getImage().getScaledInstance(bt2.getWidth(), bt2.getHeight(), Image.SCALE_DEFAULT));
		bt2.setIcon(imag3);
		JLabel bt3 = new JLabel("");
		bt3.setBounds(317, 517, 74, 65);
		panel.add(bt3);
		ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/boton verde.png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(bt3.getWidth(), bt3.getHeight(), Image.SCALE_DEFAULT));
		bt3.setIcon(imag4);
		
		JButton btAgregar = new JButton("Registrar Paciente");
		btAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				datosEmergencia();
			}
		});
		btAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btAgregar.setBounds(150, 639, 145, 28);
		panel.add(btAgregar);
		
		JLabel lblNewLabel_7 = new JLabel("Cedula");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 266, 46, 14);
		panel.add(lblNewLabel_7);
		
		txtCedulaP = new JTextField();
		txtCedulaP.setBounds(64, 264, 101, 20);
		panel.add(txtCedulaP);
		txtCedulaP.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Tipo de seguro:");
		lblNewLabel_8.setForeground(Color.BLUE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(170, 308, 109, 14);
		panel.add(lblNewLabel_8);
		
		 rb1 = new JRadioButton("Senasa");
		 rb1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros="Senasa";
		 	}
		 });
		rb1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb1.setBounds(10, 353, 74, 23);
		panel.add(rb1);
		
		 rb2 = new JRadioButton("Humano");
		 rb2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = "Humano";
		 	}
		 });
		rb2.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb2.setBounds(109, 353, 86, 23);
		panel.add(rb2);
		
		 rb3 = new JRadioButton("Universal");
		 rb3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = "Universal";
		 	}
		 });
		rb3.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb3.setBounds(238, 353, 89, 23);
		panel.add(rb3);
		
		 rb4 = new JRadioButton("SEMMA");
		 rb4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = "SEMMA";
		 	}
		 });
		rb4.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb4.setBounds(350, 353, 74, 23);
		panel.add(rb4);
		
		 rb5 = new JRadioButton("Otro");
		 rb5.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = otro.getText();
		 	}
		 });
		rb5.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb5.setBounds(18, 407, 61, 23);
		panel.add(rb5);
		
		JRadioButton rb6 = new JRadioButton("No tiene");
		rb6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguros = "Ninguno";
			}
		});
		rb6.setFont(new Font("Tahoma", Font.BOLD, 12));
		rb6.setBounds(284, 407, 109, 23);
		panel.add(rb6);
		
		gupo1=new ButtonGroup();
		gupo1.add(rb1);
		gupo1.add(rb2);
		gupo1.add(rb3);
		gupo1.add(rb4);
		gupo1.add(rb5);
		gupo1.add(rb6);
		
		gupo2=new ButtonGroup();
		gupo2.add(rb7);
		gupo2.add(rb8);
		gupo2.add(rb9);
		

		otro = new JTextField();
		otro.setBounds(109, 409, 86, 20);
		panel.add(otro);
		otro.setColumns(10);
		
	    spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String n;
				int ne;
				
				n= spinner.getValue().toString();
				ne=Integer.parseInt(n);
				txtCedulaP.setEnabled(false);
				if (ne>=18) {
				
					txtCedulaP.setEnabled(true);
			}
				
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(64, 229, 101, 20);
		panel.add(spinner);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Femenino", "Masculino"}));
		comboBox.setBounds(64, 193, 101, 20);
		panel.add(comboBox);
		
		JButton btAtras = new JButton("ATRAS");
		btAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		btAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Enfermera enfer= new Enfermera();
				enfer.setVisible(true);
				
				String nom = lblenfer.getText();
				enfer.lblEnfermera.setText(nom);
				dispose();
			}
		});
		btAtras.setBounds(18, 643, 83, 23);
		panel.add(btAtras);
		
		lblenfer = new JLabel("");
		lblenfer.setBounds(315, 285, 46, -7);
		panel.add(lblenfer);
		
		JLabel lblNewLabel_9 = new JLabel("Tipo:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(233, 246, 46, 14);
		panel.add(lblNewLabel_9);
		
		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		txtTipo.setBounds(281, 244, 110, 20);
		panel.add(txtTipo);
		txtTipo.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar_Paciente ip = new Ingresar_Paciente();
				ip.setVisible(true);
				
				try {
					cne = cn.getConnection();
					st = cne.createStatement();
					
					rs = st.executeQuery("Select Nombre, Apellido, Cedula, Edad, Sexo, DoctorAsignado, EstadoIngreso, IDHabitacion, IDSeguro, Motivo from ingresos ");
					
					Object[] pa = new Object[10];
					
					ip.modelo = (DefaultTableModel) ip.table.getModel();
					
					while(rs.next()) {
						
						pa[0] =rs.getString("Nombre");
						pa[1]= rs.getString("Apellido");
						pa[2]= rs.getString("Cedula");
						pa[3]= rs.getInt("Edad");
						pa[4] =rs.getString("Sexo");
						pa[5]= rs.getString("DoctorAsignado");
						pa[6]= rs.getString("EstadoIngreso");
						pa[7]= rs.getInt("IDHabitacion");
						pa[8]= rs.getInt("IDSeguro");
						pa[9] = rs.getString("Motivo");
						
						ip.modelo.addRow(pa);
						
					}
					
					ip.table.setModel(ip.modelo);
					
					}catch(Exception i) {
						
						JOptionPane.showMessageDialog(null, "Error..");
						
					}
				
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(333, 642, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(Emergencia.class.getResource("/iconos/texturaemergency.jpg")));
		lblNewLabel_10.setBounds(0, 0, 445, 690);
		panel.add(lblNewLabel_10);
		
		

	
}

	public void datosEmergencia() {
		
		txtTipo.setText("Emergencia");
		
		try {
			
			String nombre=txtNombreP.getText();
			String apellido=txtApellidoP.getText();
			String sexo= comboBox.getSelectedItem().toString();
			int edad = Integer.parseInt(spinner.getValue().toString());
			String cedula= txtCedulaP.getText();
			String motivo= txtMotivoP.getText();
			String tipo = txtTipo.getText();
			
		cne = cn.getConnection();
		st = cne.createStatement();
		
		st.executeUpdate("Insert into paciente (Nombre, Apellido, Sexo, Edad, Cedula, Motivo, TipoSeguro, EstadoUrgencia, Tipo) values ('"+nombre+"', '"+apellido+"', '"+sexo+"', "+edad+", '"+cedula+"', '"+motivo+"', '"+seguros+"', '"+estados+"', '"+tipo+"')");
		
		
		
		JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
		
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
	}
	
		txtNombreP.setText("");
		txtApellidoP.setText("");
		comboBox.setSelectedIndex(0);
		spinner.setValue(1);
		txtCedulaP.setText("");
		txtMotivoP.setText("");
		
		
	}
	}