//Secretaria

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ventanas.Conexion;
import ventanas.Ventana_Principal;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Secretaria extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement stm;
	ResultSetMetaData ResMetDa;	
	DefaultTableModel modelo;
	TableRowSorter trs; 

	private JPanel contentPane;
	public JLabel lblSecretaria;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_2;
	private JTextField txtApellido;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_7;
	private JTextField txtOtro;
	private JTextField txtDoctor;
	JComboBox cmbSexo;
	
	ButtonGroup gupo1;
	JComboBox comboBox;
	JSpinner spinner;
	JRadioButton rb1, rb2, rb3, rb4;
	private String seguros;
	private String estados;
	private JTextField txtCedula;
	private JTextField txtID;
	private JButton btnSalir;
	private JLabel lblNewLabel_9;
	private JTextField txtTipo;
	private JLabel lblNewLabel_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Secretaria frame = new Secretaria();
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
	public Secretaria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 871, 592);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblSecretaria = new JLabel("");
		lblSecretaria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSecretaria.setBounds(627, 11, 234, 26);
		panel.add(lblSecretaria);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 396, 851, 185);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int fila = table.getSelectedRow();
				if(fila==-1) {
					JOptionPane.showMessageDialog(null, "Registro no seleccionado");
					
				}else {
				
					int id = Integer.parseInt((String)table.getValueAt(fila, 0).toString());
					String nombre= (String)table.getValueAt(fila, 1);
					String apellido= (String)table.getValueAt(fila, 2);
					String sexo= (String)table.getValueAt(fila, 3);
					int edad = Integer.parseInt((String)table.getValueAt(fila, 4).toString());
					String cedula= (String)table.getValueAt(fila, 5);
					seguros = (String)table.getValueAt(fila, 6);
					
					if(seguros.equals("Humano")) {
						rb1.setSelected(true);
					}
					
					else if(seguros.equals("Senasa")) {
						rb2.setSelected(true);
					}
					
                    else if(seguros.equals("SEMMA")) {
						rb3.setSelected(true);
					}
                    else {
						rb4.setSelected(true);
					}
					
					String doctor= (String)table.getValueAt(fila, 7);
				
	                txtID.setText(""+id);
					txtNombre.setText(nombre);
					txtApellido.setText(apellido);
					cmbSexo.setSelectedItem(sexo);
					spinner.setValue(edad);
					txtCedula.setText(cedula);
					txtDoctor.setText(doctor);
					
			}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Sexo", "Edad", "Cedula", "TipoSeguro", "DoctorAsignado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(112);
		table.getColumnModel().getColumn(6).setPreferredWidth(91);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("Area de Consultas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(145, 11, 368, 42);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(36, 100, 81, 20);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = txtNombre.getText();
				Buscar(nombre);
			}
		});
		txtNombre.setBounds(145, 100, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(32, 155, 85, 14);
		panel.add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(145, 152, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Edad:");
		lblNewLabel_3.setBounds(317, 103, 60, 20);
		panel.add(lblNewLabel_3);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				String n;
				int ne;
				
				n= spinner.getValue().toString();
				ne=Integer.parseInt(n);
				txtCedula.setEnabled(false);
				if (ne>=18) {
				
					txtCedula.setEnabled(true);
			}
			}
		});
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
		lblNewLabel_6.setBackground(SystemColor.activeCaption);
		lblNewLabel_6.setBounds(584, 0, 8, 396);
		lblNewLabel_6.setOpaque(true);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Tipo de seguro:");
		lblNewLabel_7.setBounds(32, 270, 81, 14);
		panel.add(lblNewLabel_7);
		
		rb4 = new JRadioButton("OTRO:");
		rb4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguros = txtOtro.getText();
			}
		});
		rb4.setBounds(138, 309, 69, 23);
		panel.add(rb4);
		
		rb2 = new JRadioButton("Senasa");
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguros = "Senasa";
			}
		});
		rb2.setBounds(244, 266, 86, 23);
		panel.add(rb2);
		
		txtOtro = new JTextField();
		txtOtro.setBounds(244, 310, 86, 20);
		panel.add(txtOtro);
		txtOtro.setColumns(10);
		
		rb3 = new JRadioButton("SEMMA");
		rb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguros = "SEMMA";
			}
		});
		rb3.setBounds(32, 309, 109, 23);
		panel.add(rb3);
		
		rb1 = new JRadioButton("Humano");
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguros = "Humano";
			}
		});
		rb1.setBounds(145, 266, 86, 23);
		panel.add(rb1);
		
		gupo1=new ButtonGroup();
		gupo1.add(rb1);
		gupo1.add(rb2);
		gupo1.add(rb3);
		gupo1.add(rb4);
		
		JLabel lblNewLabel_8 = new JLabel("Doctor Asignado:");
		lblNewLabel_8.setBounds(317, 208, 86, 14);
		panel.add(lblNewLabel_8);
		
		txtDoctor = new JTextField();
		txtDoctor.setBounds(422, 205, 116, 20);
		panel.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtTipo.setText("Consulta");
				
				try {
					
					String nombre=txtNombre.getText();
					String apellido=txtApellido.getText();
					String sexo= cmbSexo.getSelectedItem().toString();
					int edad = Integer.parseInt(spinner.getValue().toString());
					String cedula= txtCedula.getText();
					String doctor= txtDoctor.getText();
					seguros = txtOtro.getText();	
					String tipo = txtTipo.getText();
					
				cne = cn.getConnection();
				st = cne.createStatement();
				
				st.executeUpdate("Insert into paciente (Nombre, Apellido, Sexo, Edad, Cedula, TipoSeguro, DoctorAsignado, Tipo) values ('"+nombre+"', '"+apellido+"', '"+sexo+"', "+edad+", '"+cedula+"',  '"+seguros+"', '"+doctor+"', '"+tipo+"')");
				
				JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
				limpiarcajas();
				limpiarTabla();
				
				}catch(Exception v) {
				
			}
				listar();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(660, 89, 125, 42);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre=txtNombre.getText();
				String apellido=txtApellido.getText();
				String sexo= cmbSexo.getSelectedItem().toString();
				int edad = Integer.parseInt(spinner.getValue().toString());
				String cedula= txtCedula.getText();
				String doctor= txtDoctor.getText();
				
			try {
						cne = cn.getConnection();
						st = cne.createStatement();
						
						st.executeUpdate("Update paciente set Nombre ='"+nombre+"', Apellido = '"+apellido+"', Sexo = '"+sexo+"', "
								+ "Edad = "+edad+",  Cedula = '"+cedula+"', TipoSeguro = '"+seguros+"', DoctorAsignado = '"+doctor+"' Where Cedula = '"+cedula+"' ");
						
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
		btnNewButton_1.setBounds(660, 153, 125, 42);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listar();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(660, 223, 125, 42);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar();
				listar();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(665, 299, 120, 42);
		panel.add(btnNewButton_3);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(145, 205, 86, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setEditable(false);
		txtID.setBounds(452, 267, 86, -6);
		panel.add(txtID);
		txtID.setColumns(10);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal vp = new Ventana_Principal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(0, 0, 89, 23);
		panel.add(btnSalir);
		
		lblNewLabel_9 = new JLabel("Tipo:");
		lblNewLabel_9.setBounds(355, 251, 46, 14);
		panel.add(lblNewLabel_9);
		
		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		txtTipo.setBounds(422, 245, 116, 20);
		panel.add(txtTipo);
		txtTipo.setColumns(10);
		
		lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(Secretaria.class.getResource("/iconos/secretariatex.jpg")));
		lblNewLabel_10.setBounds(0, 0, 871, 592);
		panel.add(lblNewLabel_10);
		
	}
  
  
    void listar() {
		try {
			
			cne = cn.getConnection();
			st = cne.createStatement();
			rs = st.executeQuery("Select ID, Nombre, Apellido, Sexo, Edad, Cedula, TipoSeguro, DoctorAsignado from paciente");
			Object[] paci = new Object[8];
			modelo = (DefaultTableModel) table.getModel();
			
			while(rs.next()){
		
				paci[0] = rs.getString("ID");
				paci[1] =rs.getString("Nombre");
				paci[2]= rs.getString("Apellido");
				paci[3]= rs.getString("Sexo");
				paci[4] =rs.getInt("Edad");
				paci[5]= rs.getString("Cedula");
				paci[6]= rs.getString("TipoSeguro");
				paci[7]= rs.getString("DoctorAsignado");
				modelo.addRow(paci);
				
			}
			
			table.setModel(modelo);
			
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
    }
    
    void Buscar(String texto) {
    	
		try {
			cne = cn.getConnection();
			st = cne.createStatement();
			
			String [] titulo={"ID", "Nombre", "Apellido", "Sexo", "Edad", "Cedula", "TipoSeguro", "DoctorAsignado"};
			
			rs = st.executeQuery("Select ID, Nombre, Apellido, Sexo, Edad, Cedula, TipoSeguro, DoctorAsignado from paciente Where Nombre like '%"+texto+"%'");
			modelo = new DefaultTableModel(null, titulo);		
					  
			String[] paci = new String[8];
			
			while(rs.next()){
		
				paci[0] = rs.getString("ID");
				paci[1] =rs.getString("Nombre");
				paci[2]= rs.getString("Apellido");
				paci[3]= rs.getString("Sexo");
				paci[4] =rs.getString("Edad");
				paci[5]= rs.getString("Cedula");
				paci[6]= rs.getString("TipoSeguro");
				paci[7]= rs.getString("DoctorAsignado");
				modelo.addRow(paci);
				
			}
			
			table.setModel(modelo);
			rs.close();
			st.close();
			
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
		
	void Eliminar() {
		
		int id =Integer.parseInt(txtID.getText());
		int fila2 = table.getSelectedRow();
		
		if(fila2==-1) {
			
				JOptionPane.showMessageDialog(null, "Selecione una fila, por favor");
		}
		else {
			try {
				
			cne = cn.getConnection();
			st = cne.createStatement();
			st.executeUpdate("Delete from paciente Where ID = "+id);
			
			JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
			
			limpiarcajas();
			limpiarTabla();
			
			
			}catch(Exception y) {
				
			}
			
		}
	}	
		
		void limpiarcajas() {
			
			txtNombre.setText("");
			txtApellido.setText("");
			txtCedula.setText("");
			txtDoctor.setText("");
			spinner.setValue(1);
			cmbSexo.setSelectedIndex(0);
			
			if(seguros.equals("Humano")) {
				rb1.setSelected(false);
			}
			
			else if(seguros.equals("Senasa")) {
				rb2.setSelected(false);
			}
			
            else if(seguros.equals("SEMMA")) {
				rb3.setSelected(false);
			}
            else {
				rb4.setSelected(false);
			}
		}
}
    
