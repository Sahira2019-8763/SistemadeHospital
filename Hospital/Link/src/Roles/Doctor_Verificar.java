//Doctor Verificar

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ventanas.Conexion;

import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Doctor_Verificar extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	DefaultTableModel modelo;

	private JPanel contentPane;
	private JTextField txtNombre;
	public JTable tablapaci;
	private JTextArea textArea;
	public static JTextField txtdoc;
	public static JLabel lblcdoc;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Verificar frame = new Doctor_Verificar();
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
	public Doctor_Verificar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 724, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(175, 153, 86, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID Paciente:");
		lblNewLabel_4.setBounds(35, 156, 75, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Nombre del Paciente:");
		lblNewLabel.setBounds(35, 120, 129, 14);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = txtNombre.getText();
				Buscar(nombre);
			}
		});
		txtNombre.setBounds(174, 117, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Diagnostico");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(387, 117, 108, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Verificar Consulta de Paciente");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_3.setBounds(188, 27, 374, 29);
		panel.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(387, 150, 206, 129);
		panel.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnGuardar = new JButton("Guardar Diagnostico");
		btnGuardar.setToolTipText("Ingrese el ID de Paciente para poder ingresar el diagnostico");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inf = textArea.getText();
				 int id = Integer.parseInt(txtId.getText());
				
				try {
					cne = cn.getConnection();
					st = cne.createStatement();
					
					st.executeUpdate("Update paciente set Diagnostico = '"+inf+"' Where ID = "+id+" ");
					JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
					
				}catch(Exception i) {
					
					JOptionPane.showMessageDialog(null, "Error, Ingrese la matricula del estudiante a buscar");
					
				}	
		}
		});
		btnGuardar.setBounds(106, 221, 214, 39);
		panel.add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 308, 694, 185);
		panel.add(scrollPane);
		
		tablapaci = new JTable();
		tablapaci.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Sexo", "Edad", "Motivo", "TipoSeguro", "Cedula", "Diagnostico", "DoctorAsignado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablapaci.getColumnModel().getColumn(9).setPreferredWidth(101);
		scrollPane.setViewportView(tablapaci);
		
		txtdoc = new JTextField();
		txtdoc.setEditable(false);
		txtdoc.setEnabled(false);
		txtdoc.setBounds(507, 55, 86, -2);
		panel.add(txtdoc);
		txtdoc.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Doctor dr = new Doctor();
				dr.setVisible(true);
				
				String doc = txtdoc.getText();
				dr.txtape.setText(doc);
				
				String nom = lblcdoc.getText();
				dr.lblDoctor.setText(nom);
				
				dispose();
			}
		});
		btnVolver.setBounds(615, 11, 89, 29);
		panel.add(btnVolver);
		
		lblcdoc = new JLabel("");
		lblcdoc.setBounds(502, 99, 46, -2);
		panel.add(lblcdoc);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Doctor_Verificar.class.getResource("/iconos/doctorVerific.jpg")));
		lblNewLabel_1.setBounds(0, 0, 724, 504);
		panel.add(lblNewLabel_1);
	}
	
     void Buscar(String texto) {
    	 
    	 String doct = txtdoc.getText();    	
		try {
			cne = cn.getConnection();
			st = cne.createStatement();
			
			String [] titulo={"ID", "Nombre", "Apellido", "Sexo", "Edad", "Cedula", "TipoSeguro", "DoctorAsignado"};
			
			rs = st.executeQuery("Select ID, Nombre, Apellido, Sexo, Edad, Cedula, TipoSeguro, DoctorAsignado from paciente Where Nombre like '%"+texto+"%' and DoctorAsignado = '"+doct+"' and Tipo = 'Consulta'");
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
			
			tablapaci.setModel(modelo);
			rs.close();
			st.close();
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
    }
}
