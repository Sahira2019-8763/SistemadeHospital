//Ingresar Pacientes

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Departamentos.Emergencia;
import ventanas.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class Ingresar_Paciente extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	public DefaultTableModel modelo;

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDoctor;
	private JTextField txtCedula;
	private JTextField textField_5;
	private JTextField txtHabitacion;
	public JTable table;
	private int seguros;
	ButtonGroup grupo1;
	JRadioButton rb1, rb2, rb3;
	JSpinner spinner;
	JTextArea txaMotivo;
	JComboBox cmbSexo, cmbEstado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingresar_Paciente frame = new Ingresar_Paciente();
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
	public Ingresar_Paciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 871, 668);
		contentPane.add(panel);
		
		JLabel lblSecretaria = new JLabel("");
		lblSecretaria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSecretaria.setBounds(627, 11, 234, 26);
		panel.add(lblSecretaria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 472, 851, 185);
		panel.add(scrollPane);
		
		grupo1= new ButtonGroup();
		grupo1.add(rb1);
		grupo1.add(rb2);
		grupo1.add(rb3);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int fila = table.getSelectedRow();
				
				if(fila==-1) {
					JOptionPane.showMessageDialog(null, "Registro no seleccionado");
					
				}else {
				
					String nombre= (String)table.getValueAt(fila, 0);
					String apellido= (String)table.getValueAt(fila, 1);
					String cedula= (String)table.getValueAt(fila, 2);
					int edad = Integer.parseInt((String)table.getValueAt(fila, 3).toString());
					String sexo= (String)table.getValueAt(fila, 4);
					String doctor = (String)table.getValueAt(fila, 5);
					String estado = (String)table.getValueAt(fila, 6);
					int habi = Integer.parseInt((String)table.getValueAt(fila, 7).toString());
					seguros = Integer.parseInt((String)table.getValueAt(fila, 8).toString());
					
					if(seguros == 1) {
						rb1.setSelected(true);
						rb2.setSelected(false);
						rb3.setSelected(false);
					}
					
					else if(seguros == 2) {
						rb2.setSelected(true);
						rb1.setSelected(false);
						rb3.setSelected(false);
					}
					
                    else {
						rb3.setSelected(true);
						rb2.setSelected(false);
						rb1.setSelected(false);
					}
					
					String motivo = (String)table.getValueAt(fila, 9);

					txtNombre.setText(nombre);
					txtApellido.setText(apellido);
					txtCedula.setText(cedula);
					spinner.setValue(edad);
					cmbSexo.setSelectedItem(sexo);
					txtDoctor.setText(doctor);
					cmbEstado.setSelectedItem(estado);
					txtHabitacion.setText(""+habi);
					txaMotivo.setText(motivo);
					
			}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Apellido", "Cedula", "Edad", "Sexo", "DoctorAsignado", "EstadoIngreso", "IDHabitacion", "IDSeguro", "Motivo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblIngresarPacientes = new JLabel("Ingresar Pacientes");
		lblIngresarPacientes.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblIngresarPacientes.setBounds(145, 11, 368, 42);
		panel.add(lblIngresarPacientes);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(36, 100, 81, 20);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(145, 100, 86, 20);
		panel.add(txtNombre);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(32, 155, 85, 14);
		panel.add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(145, 152, 86, 20);
		panel.add(txtApellido);
		
		JLabel lblNewLabel_3 = new JLabel("Edad:");
		lblNewLabel_3.setBounds(317, 103, 60, 20);
		panel.add(lblNewLabel_3);
		
		spinner = new JSpinner();
		spinner.setBounds(422, 100, 60, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(316, 155, 61, 14);
		panel.add(lblNewLabel_4);
		
		cmbSexo = new JComboBox();
		cmbSexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Femenino", "Masculino"}));
		cmbSexo.setBounds(422, 152, 116, 20);
		panel.add(cmbSexo);
		
		JLabel lblNewLabel_5 = new JLabel("Cedula:");
		lblNewLabel_5.setBounds(32, 208, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(SystemColor.activeCaption);
		lblNewLabel_6.setBounds(584, 0, 8, 472);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo de seguro:");
		lblNewLabel_7.setBounds(32, 270, 113, 14);
		panel.add(lblNewLabel_7);
		
		 rb2 = new JRadioButton("Senasa");
		 rb2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = 2;
		 	}
		 });
		rb2.setBounds(32, 343, 86, 23);
		panel.add(rb2);
		
		 rb3 = new JRadioButton("SEMMA");
		 rb3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = 3;
		 	}
		 });
		rb3.setBounds(36, 375, 109, 23);
		panel.add(rb3);
		
		 rb1 = new JRadioButton("Humano");
		 rb1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		seguros = 1;
		 	}
		 });
		rb1.setBounds(32, 309, 86, 23);
		panel.add(rb1);
		
		JLabel lblNewLabel_8 = new JLabel("Doctor Asignado:");
		lblNewLabel_8.setBounds(317, 208, 86, 14);
		panel.add(lblNewLabel_8);
		
		txtDoctor = new JTextField();
		txtDoctor.setColumns(10);
		txtDoctor.setBounds(422, 205, 116, 20);
		panel.add(txtDoctor);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                  try {
					
					String nombre=txtNombre.getText();
					String apellido=txtApellido.getText();
					String cedula = txtCedula.getText();
					int edad = Integer.parseInt(spinner.getValue().toString());
					String sexo= cmbSexo.getSelectedItem().toString();
					String doctor= txtDoctor.getText();
					String estado = cmbEstado.getSelectedItem().toString();
					int habi = Integer.parseInt(txtHabitacion.getText());
					String motivo = txaMotivo.getText();					
					
				cne = cn.getConnection();
				st = cne.createStatement();
				
				st.executeUpdate("Insert into ingresos (Nombre, Apellido, Cedula, Edad, Sexo, DoctorAsignado, EstadoIngreso, IDHabitacion, IDSeguro, Motivo) values ('"+nombre+"', '"+apellido+"', '"+cedula+"', "+edad+", '"+sexo+"', '"+doctor+"', '"+estado+"', "+habi+", "+seguros+", '"+motivo+"')");
				
				JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
			     limpiarcajas();
				limpiarTabla();
				
				}catch(Exception v) {
					JOptionPane.showMessageDialog(null, "ERROR!"+v);
			}
				listar();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardar.setBounds(660, 140, 125, 42);
		panel.add(btnGuardar);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre=txtNombre.getText();
				String apellido=txtApellido.getText();
				String cedula = txtCedula.getText();
				int edad = Integer.parseInt(spinner.getValue().toString());
				String sexo= cmbSexo.getSelectedItem().toString();
				String doctor= txtDoctor.getText();
				String estado = cmbEstado.getSelectedItem().toString();
				int habi = Integer.parseInt(txtHabitacion.getText());
				String motivo = txaMotivo.getText();
				
				try {
					cne = cn.getConnection();
					st = cne.createStatement();
					
					st.executeUpdate("Update ingresos set Nombre ='"+nombre+"', Apellido = '"+apellido+"', Sexo = '"+sexo+"', "
							+ "Edad = "+edad+",  Cedula = '"+cedula+"', IDSeguro = "+seguros+", DoctorAsignado = '"+doctor+"', EstadoIngreso = '"+estado+"', IDHabitacion = "+habi+", Motivo = '"+motivo+"'  Where Cedula = '"+cedula+"' ");
					
					JOptionPane.showMessageDialog(null, "Registro actualizado con exito!");
					limpiarcajas();
					limpiarTabla();
					
                }
               
                catch(Exception r) {
						
                	JOptionPane.showMessageDialog(null, "Seleccione una fila, por favor");
                	
			   }
               
                listar();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(660, 231, 125, 42);
		panel.add(btnNewButton_1);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(145, 205, 86, 20);
		panel.add(txtCedula);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(452, 267, 86, -6);
		panel.add(textField_5);
		
		JButton btnSalir = new JButton("VOLVER");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emergencia emer = new Emergencia();
				emer.setVisible(true);
				dispose();
				
			}
		});
		btnSalir.setBounds(660, 331, 125, 44);
		panel.add(btnSalir);
		
		JLabel lblNewLabel_9 = new JLabel("Estado Ingreso:");
		lblNewLabel_9.setBounds(317, 266, 86, 14);
		panel.add(lblNewLabel_9);
		
		cmbEstado = new JComboBox();
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Habitaci\u00F3n", "Intensivo"}));
		cmbEstado.setBounds(422, 263, 116, 20);
		panel.add(cmbEstado);
		
		JLabel lblNewLabel_10 = new JLabel("N\u00B0 Habitaci\u00F3n:");
		lblNewLabel_10.setBounds(317, 310, 98, 20);
		panel.add(lblNewLabel_10);
		
		txtHabitacion = new JTextField();
		txtHabitacion.setBounds(422, 310, 116, 20);
		panel.add(txtHabitacion);
		txtHabitacion.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Motivo:");
		lblNewLabel_11.setBounds(317, 379, 46, 14);
		panel.add(lblNewLabel_11);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(422, 363, 125, 39);
		panel.add(scrollPane_1);
		
		txaMotivo = new JTextArea();
		scrollPane_1.setViewportView(txaMotivo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Ingresar_Paciente.class.getResource("/iconos/ingresospacien.jpg")));
		lblNewLabel.setBounds(0, 0, 871, 668);
		panel.add(lblNewLabel);
	}
	
	void listar() {
		
		try {
			
			cne = cn.getConnection();
			st = cne.createStatement();
			rs = st.executeQuery("Select Nombre, Apellido, Cedula, Edad, Sexo, DoctorAsignado, EstadoIngreso, IDHabitacion, IDSeguro, Motivo from ingresos");
			Object[] paci = new Object[10];
			modelo = (DefaultTableModel) table.getModel();
			
			while(rs.next()){
		
				
				paci[0] =rs.getString("Nombre");
				paci[1]= rs.getString("Apellido");
				paci[2] = rs.getString("Cedula");
				paci[3]= rs.getInt("Edad");
				paci[4] =rs.getString("Sexo");
				paci[5]= rs.getString("DoctorAsignado");
				paci[6]= rs.getString("EstadoIngreso");
				paci[7]= rs.getInt("IDHabitacion");
				paci[8]= rs.getInt("IDSeguro");
				paci[9]= rs.getString("Motivo");
				
				modelo.addRow(paci);
				
			}
			
			table.setModel(modelo);
			
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
    }
	
	void limpiarTabla() {
		for(int i= 0; i<=table.getRowCount(); i++ ) {
			modelo.removeRow(i);
			i=i-1;
		}
}
	
	void limpiarcajas() {
		
		String nombre=txtNombre.getText();
		String apellido=txtApellido.getText();
		String cedula = txtCedula.getText();
		int edad = Integer.parseInt(spinner.getValue().toString());
		String sexo= cmbSexo.getSelectedItem().toString();
		String doctor= txtDoctor.getText();
		String estado = cmbEstado.getSelectedItem().toString();
		int habi = Integer.parseInt(txtHabitacion.getText());
		String motivo = txaMotivo.getText();
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtDoctor.setText("");
		spinner.setValue(1);
		cmbSexo.setSelectedIndex(0);
		cmbEstado.setSelectedIndex(0);
		txtHabitacion.setText("");
		txaMotivo.setText("");
		
		if(seguros==1) {
			rb1.setSelected(false);
			rb2.setSelected(false);
			rb3.setSelected(false);
			
		}
		
		else if(seguros==2) {
			rb1.setSelected(false);
			rb2.setSelected(false);
			rb3.setSelected(false);
		}
		
        else {
        	rb1.setSelected(false);
			rb2.setSelected(false);
			rb3.setSelected(false);
		}
        
	}
}
