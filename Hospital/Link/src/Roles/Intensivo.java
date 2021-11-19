//Intensivo

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ventanas.Conexion;
import ventanas.Ventana_Inicio;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Intensivo extends JFrame {

	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	DefaultTableModel modelo;

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable table;
	public static JTextField txtdoct;
	public static JLabel lbldoct;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intensivo frame = new Intensivo();
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
	public Intensivo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 761, 436);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(216, 139, 86, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ID del Paciente:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(52, 141, 113, 14);
		panel.add(lblNewLabel_3);
		
		JLabel inte = new JLabel("");
		inte.setBounds(574, 103, 158, 120);
		panel.add(inte);
		ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/paciente.png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(inte.getWidth(), inte.getHeight(), Image.SCALE_DEFAULT));
		inte.setIcon(imag4);

		JButton btnPacientes = new JButton("Todos los Pacientes");
		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			    listar();
			}
		});
		btnPacientes.setBounds(134, 191, 146, 32);
		panel.add(btnPacientes);

		JButton btnDe_Alta = new JButton("Mover a Habitaci\u00F3n");
		btnDe_Alta.setToolTipText("Primero Ingrese el ID del Paciente");
		btnDe_Alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String de = "Habitacion";
				try {
					cne = cn.getConnection();
					st = cne.createStatement();

					st.executeUpdate("Update ingresos set EstadoIngreso = '" + de + "' Where ID = " + id + " ");
					JOptionPane.showMessageDialog(null, "Paciente dado movido a Habitacion con éxito!");
                    limpiarTabla();
				} catch (Exception i) {

				}
				listar();
			}
		});
		btnDe_Alta.setBounds(357, 191, 124, 32);
		panel.add(btnDe_Alta);

		JLabel lblNewLabel = new JLabel("Cuidados Intensivos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(201, 29, 293, 48);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre del Paciente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(54, 110, 152, 14);
		panel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String nombre = txtNombre.getText();
				Buscar(nombre);
			}
		});
		txtNombre.setBounds(216, 108, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 255, 678, 171);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
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
		table.getColumnModel().getColumn(7).setPreferredWidth(98);
		table.getColumnModel().getColumn(8).setPreferredWidth(93);
		scrollPane.setViewportView(table);

		txtdoct = new JTextField();
		txtdoct.setEnabled(false);
		txtdoct.setEditable(false);
		txtdoct.setBounds(518, 88, 86, -10);
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
		btnVolver.setBounds(643, 29, 89, 23);
		panel.add(btnVolver);
		
		lbldoct = new JLabel("");
		lbldoct.setBounds(558, 128, 46, -16);
		panel.add(lbldoct);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Intensivo.class.getResource("/iconos/intensivo.jpg")));
		lblNewLabel_2.setBounds(0, 0, 761, 436);
		panel.add(lblNewLabel_2);
		
		
	}
	
 void Buscar(String texto) {
	    String doc = txtdoct.getText();
	    String nombre = txtNombre.getText();
	 
		try {
			cne = cn.getConnection();
			st = cne.createStatement();
			
			String [] titulo={"ID", "Nombre", "Apellido","Cedula", "Edad", "Sexo", "Diagnostico", "DoctorAsignado", "EstadoIngreso", "IDHabitacion", "TipoSeguro" };
			
			rs = st.executeQuery("Select i.ID, i.Nombre, i.Apellido,i.Edad, i.Cedula, i.Sexo, i.Diagnostico, i.DoctorAsignado, i.EstadoIngreso, i.IDHabitacion, s.TipoSeguro from ingresos i join seguro s on(s.ID = i.IDSeguro) where i.Nombre like '%"+texto+"%'  and i.DoctorAsignado = '" + doc + "' and i.EstadoIngreso = 'Intensivo' ");
			modelo = new DefaultTableModel(null, titulo);		
					  
			String[] paci = new String[11];
			
			while(rs.next()){
		
				paci[0] = rs.getString("ID");
				paci[1] = rs.getString("Nombre");
				paci[2] = rs.getString("Apellido");
				paci[3] = rs.getString("Cedula");
				paci[4] = rs.getString("Edad");
				paci[5] = rs.getString("Sexo");
				paci[6] = rs.getString("Diagnostico");
				paci[7] = rs.getString("DoctorAsignado");
				paci[8] = rs.getString("EstadoIngreso");
				paci[9] = rs.getString("IDHabitacion");
				paci[10] = rs.getString("TipoSeguro");
				modelo.addRow(paci);
				
			}
			
			table.setModel(modelo);
			rs.close();
			st.close();
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
    }
 
 void listar() {
 
	String doc = txtdoct.getText();

	try {
		cne = cn.getConnection();
		st = cne.createStatement();

		rs = st.executeQuery(
				"Select i.ID, i.Nombre, i.Apellido, i.Cedula, i.Edad, i.Sexo, i.Diagnostico, i.DoctorAsignado, i.EstadoIngreso, i.IDHabitacion, s.TipoSeguro from ingresos i join seguro s on(s.ID = i.IDSeguro) where i.DoctorAsignado = '"
						+ doc + "' and i.EstadoIngreso = 'Intensivo' ");
		Object[] ta = new Object[11];

		modelo = (DefaultTableModel) table.getModel();

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
		JOptionPane.showMessageDialog(null, "Registro buscado");

		table.setModel(modelo);

	} catch (Exception i) {

	}
 }
 
 void limpiarTabla() {
		
		for (int i = 0; i <= table.getRowCount(); i++) {
			modelo.removeRow(i);
			i =i-1;
		}
}

}