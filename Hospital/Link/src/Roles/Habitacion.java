//Habitacion

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import ventanas.Conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Habitacion extends JFrame {

	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	DefaultTableModel modelo;

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable tabla;
	public static JTextField txtdoct;
	public static JLabel lbldoct;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Habitacion frame = new Habitacion();
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
	public Habitacion() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 918, 456);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbB = new JLabel("New label");
		
		lbB.setBounds(760, 79, 114, 85);
		panel.add(lbB);
		ImageIcon imgs5 = new ImageIcon(getClass().getResource("/iconos/paciente (2).png"));
		ImageIcon imag5 = new ImageIcon(imgs5.getImage().getScaledInstance(lbB.getWidth(), lbB.getHeight(), Image.SCALE_DEFAULT));
		lbB.setIcon(imag5);
		
		JLabel lbA = new JLabel("New label");
		lbA.setBounds(41, 79, 114, 85);
		panel.add(lbA);
		
		ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/paciente (1).png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(lbA.getWidth(), lbA.getHeight(), Image.SCALE_DEFAULT));
		lbA.setIcon(imag4);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(479, 113, 71, 14);
		panel.add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setBounds(327, 111, 86, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(260, 113, 37, 14);
		panel.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = txtNombre.getText();
				Buscar(nombre);
			}
		});
		txtNombre.setBounds(578, 111, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnMover = new JButton("Mover a Intensivo");
		btnMover.setToolTipText("Primero ingrese el ID del paciente");
		btnMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = Integer.parseInt(txtID.getText());
				String in = "Intensivo";
				try {
					cne = cn.getConnection();
					st = cne.createStatement();
					
					st.executeUpdate("Update ingresos set EstadoIngreso = '"+in+"' Where ID = "+id+" ");
					
					JOptionPane.showMessageDialog(null, "Paciente movido a intensivo con éxito!");
					limpiarTabla();
				}catch(Exception i) {
					
				}
				
				Listar();
			}

		});
		btnMover.setBounds(388, 195, 162, 36);
		panel.add(btnMover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 263, 864, 182);
		panel.add(scrollPane);

		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Cedula", "Edad", "Sexo", "Diagnostico", "DoctorAsignado", "EstadoIngreso", "IDHabitacion", "TipoSeguro"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tabla);

		JButton btnlistar = new JButton("Todos los Pacientes");
		btnlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String doc = txtdoct.getText();

				try {
					cne = cn.getConnection();
					st = cne.createStatement();

					rs = st.executeQuery(
							"Select i.ID, i.Nombre, i.Apellido, i.Cedula, i.Edad, i.Sexo, i.Diagnostico, i.DoctorAsignado, i.EstadoIngreso, i.IDHabitacion, s.TipoSeguro from ingresos i join seguro s on(s.ID = i.IDSeguro) where i.DoctorAsignado = '"
									+ doc + "' and i.EstadoIngreso = 'Habitación' ");
					Object[] ta = new Object[11];

					modelo = (DefaultTableModel) tabla.getModel();

					while (rs.next()) {

						ta[0] = rs.getInt("ID");
						ta[1] = rs.getString("Nombre");
						ta[2] = rs.getString("Apellido");
						ta[3] = rs.getString("Cedula");
						ta[4] = rs.getInt("Edad");
						ta[5] = rs.getString("Sexo");
						ta[6] = rs.getString("Diagnostico");
						ta[7] = rs.getString("DoctorAsignado");
						ta[8] = rs.getString("EstadoIngreso");
						ta[9] = rs.getInt("IDHabitacion");
						ta[10] = rs.getString("TipoSeguro");

						modelo.addRow(ta);

					}

					tabla.setModel(modelo);
					JOptionPane.showMessageDialog(null, "Registro buscado");

				} catch (Exception i) {

					JOptionPane.showMessageDialog(null, "Error, Ingrese el ID del Paciente a buscar");

				}
			}
		});
		btnlistar.setToolTipText("Solo dele click al bot\u00F3n para ejecutar");
		btnlistar.setBounds(111, 195, 186, 36);
		panel.add(btnlistar);

		txtdoct = new JTextField();
		txtdoct.setEnabled(false);
		txtdoct.setEditable(false);
		txtdoct.setBounds(359, 30, 86, -10);
		panel.add(txtdoct);
		txtdoct.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor dr = new Doctor();
				dr.setVisible(true);
				
				String doc = txtdoct.getText();
				dr.txtape.setText(doc);
				
				String nom = lbldoct.getText();
				dr.lblDoctor.setText(nom);
				
				dispose();
			}
		});
		btnVolver.setBounds(772, 11, 89, 32);
		panel.add(btnVolver);
		
		JButton btnDe_Alta = new JButton("Dar de Alta");
		btnDe_Alta.setToolTipText("Primero Ingrese el ID del Paciente");
		btnDe_Alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtID.getText());
				String de = "De Alta";
				try {
					cne = cn.getConnection();
					st = cne.createStatement();
					
					st.executeUpdate("Update ingresos set EstadoIngreso = '"+de+"' Where ID = "+id+" ");
					JOptionPane.showMessageDialog(null, "Paciente dado de Alta con éxito!");
					limpiarTabla();
					
				}catch(Exception i) {
				
				}
				Listar();
			}
		});
		btnDe_Alta.setBounds(642, 195, 136, 36);
		panel.add(btnDe_Alta);
		
		JLabel lblNewLabel_1 = new JLabel("Pacientes en Habitaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setBounds(272, 33, 392, 36);
		panel.add(lblNewLabel_1);
		
		lbldoct = new JLabel("");
		lbldoct.setBounds(634, 100, 46, -20);
		panel.add(lbldoct);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Habitacion.class.getResource("/iconos/habitacion.jpg")));
		lblNewLabel_2.setBounds(0, 0, 913, 456);
		panel.add(lblNewLabel_2);
	}

	void limpiarTabla() {
		
		for (int i = 0; i <= tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i =i-1;
		}
	}
	
	public void Listar() {
		int id = Integer.parseInt(txtID.getText());
		String doc = txtdoct.getText();

		try {
			cne = cn.getConnection();
			st = cne.createStatement();
			rs = st.executeQuery(
					"Select i.ID, i.Nombre, i.Apellido, i.Cedula, i.Edad, i.Sexo, i.Diagnostico, i.DoctorAsignado, i.EstadoIngreso, i.IDHabitacion, s.TipoSeguro from ingresos i join seguro s on(s.ID = i.IDSeguro) where i.ID = "
							+ id + " and i.DoctorAsignado = '" + doc + "' ");
			Object[] ta = new Object[11];

			modelo = (DefaultTableModel) tabla.getModel();

			while (rs.next()) {

				ta[0] = rs.getInt("ID");
				ta[1] = rs.getString("Nombre");
				ta[2] = rs.getString("Apellido");
				ta[3] = rs.getString("Cedula");
				ta[4] = rs.getInt("Edad");
				ta[5] = rs.getString("Sexo");
				ta[6] = rs.getString("Diagnostico");
				ta[7] = rs.getString("DoctorAsignado");
				ta[8] = rs.getString("EstadoIngreso");
				ta[9] = rs.getInt("IDHabitacion");
				ta[10] = rs.getString("TipoSeguro");

				modelo.addRow(ta);
				JOptionPane.showMessageDialog(null, "Registro encontrado");

			}
			tabla.setModel(modelo);

		} catch (Exception i) {


		}
		
	}
	

 void Buscar(String texto) {
    	 
    	 String doct = txtdoct.getText();    	
		try {
			cne = cn.getConnection();
			st = cne.createStatement();
			
			String [] titulo={"ID", "Nombre", "Apellido", "Cedula", "Edad", "Sexo", "Diagnostico", "DoctorAsignado", "EstadoIngreso", "IDHabitacion", "TipoSeguro"};
			
			rs = st.executeQuery("Select i.ID, i.Nombre, i.Apellido, i.Cedula, i.Edad, i.Sexo, i.Diagnostico, i.DoctorAsignado, i.EstadoIngreso, i.IDHabitacion, s.TipoSeguro from ingresos i join seguro s Where Nombre like '%"+texto+"%' and DoctorAsignado = '"+doct+"' and i.EstadoIngreso = 'Habitacion' ");
			modelo = new DefaultTableModel(null, titulo);		
					  
			String[] paci = new String[11];
			
			while(rs.next()){
		
				paci[0] = rs.getString("ID");
				paci[1] =rs.getString("Nombre");
				paci[2]= rs.getString("Apellido");
				paci[3]= rs.getString("Cedula");
				paci[4] =rs.getString("Edad");
				paci[5]= rs.getString("Sexo");
				paci[6]= rs.getString("Diagnostico");
				paci[7]= rs.getString("DoctorAsignado");
				paci[8]= rs.getString("EstadoIngreso");
				paci[9]= rs.getString("IDHabitacion");
				paci[10]= rs.getString("TipoSeguro");
				
				modelo.addRow(paci);
				
			}
			
			tabla.setModel(modelo);
			rs.close();
			st.close();
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
    }	
	
}
