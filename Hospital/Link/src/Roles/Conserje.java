//Conserje

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ventanas.Conexion;
import ventanas.Ventana_Principal;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Conserje extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	PreparedStatement stm;
	ResultSetMetaData ResMetDa;	
	DefaultTableModel modelo;

	private JPanel contentPane;
	public JLabel lblGerente;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtID;
	private JLabel lblNewLabel_2;
	private JTextField txtNombre;
	private JLabel lblNewLabel_3;
	private JTextField txtApellido;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtAreaAsignada;
	private JTable table;
	private JComboBox cmbSexo;
	private JButton Buscar;
	private JLabel lbl;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conserje frame = new Conserje();
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
	public Conserje() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 722, 470);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblGerente = new JLabel("");
		lblGerente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGerente.setBounds(495, 11, 217, 21);
		panel.add(lblGerente);
		
		
		lblNewLabel = new JLabel("Departamento de Limpieza");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(194, 43, 345, 54);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(46, 142, 52, 14);
		panel.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setBounds(135, 139, 86, 20);
		panel.add(txtID);
		txtID.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(46, 205, 64, 14);
		panel.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(135, 202, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(46, 256, 64, 14);
		panel.add(lblNewLabel_3);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(135, 253, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(291, 145, 46, 14);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Area Asignada:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(291, 205, 110, 14);
		panel.add(lblNewLabel_5);
		
		txtAreaAsignada = new JTextField();
		txtAreaAsignada.setBounds(412, 202, 86, 20);
		panel.add(txtAreaAsignada);
		txtAreaAsignada.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 318, 568, 141);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if(fila==-1) {
					JOptionPane.showMessageDialog(null, "Registro no seleccionado");
					
				}else {
				
					int id = Integer.parseInt((String)table.getValueAt(fila, 0).toString());
					String nombre= (String)table.getValueAt(fila, 1);
					String apellido= (String)table.getValueAt(fila, 2);
					String sexo= (String)table.getValueAt(fila, 3);
					String area= (String)table.getValueAt(fila, 4);
				
	                txtID.setText(""+id);
					txtNombre.setText(nombre);
					txtApellido.setText(apellido);
					cmbSexo.setSelectedItem(sexo);
					txtAreaAsignada.setText(area);
					
			}
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Sexo", "AreaAsignada"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(91);
		scrollPane.setViewportView(table);
		
		JButton Guardar = new JButton("Guardar");
		Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		       
				try {
					
					String nombre=txtNombre.getText();
					String apellido=txtApellido.getText();
					String sexo= cmbSexo.getSelectedItem().toString();
					int id =Integer.parseInt(txtID.getText());
					String area= txtAreaAsignada.getText();	
					
				cne = cn.getConnection();
				st = cne.createStatement();
				
				st.executeUpdate("Insert into conserje (ID, Nombre, Apellido, Sexo, AreaEncargada) values ("+id+", '"+nombre+"', '"+apellido+"', '"+sexo+"', '"+area+"')");     
				
				JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
				limpiarcajas();
				limpiarTabla();
				
				}catch(Exception v) {
				
			}
				listar();
			}
			
			
		});
		Guardar.setBounds(587, 162, 89, 23);
		panel.add(Guardar);
		
		JButton Eliminar = new JButton("Eliminar");
		Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar();
			}
		});
		Eliminar.setBounds(587, 202, 89, 23);
		panel.add(Eliminar);
		
		cmbSexo = new JComboBox();
		cmbSexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Femenino", "Masculino"}));
		cmbSexo.setBounds(398, 140, 98, 23);
		panel.add(cmbSexo);
		
		Buscar = new JButton("Ver Todo");
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listar();
				
			}
		});
		Buscar.setBounds(587, 236, 89, 23);
		panel.add(Buscar);
		
		JButton btnSalir = new JButton("Cerrar Secci\u00F3n");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ventana_Principal vp = new Ventana_Principal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(0, 0, 155, 39);
		panel.add(btnSalir);
		
		lbl = new JLabel("");
	
		lbl.setBounds(558, 43, 130, 97);
		panel.add(lbl);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Conserje.class.getResource("/iconos/texturacons.jpg")));
		
		lblNewLabel_7.setBounds(0, 0, 722, 470);
		panel.add(lblNewLabel_7);
		ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/equipo-de-limpieza.png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imag4);

	}
	
	void listar() {
		try {
			
			cne = cn.getConnection();
			st = cne.createStatement();
			rs = st.executeQuery("Select ID, Nombre, Apellido, Sexo, AreaEncargada from conserje");
			Object[] paci = new Object[5];
			modelo = (DefaultTableModel) table.getModel();
			
			while(rs.next()){
		
				paci[0] = rs.getInt("ID");
				paci[1] =rs.getString("Nombre");
				paci[2]= rs.getString("Apellido");
				paci[3]= rs.getString("Sexo");
				paci[4] =rs.getString("AreaEncargada");
				
				modelo.addRow(paci);
				
			}
			
			table.setModel(modelo);
			
			
			}catch(Exception e) {
				//JOptionPane.showMessageDialog(null, "Error");
			}
    }
		
		void limpiarTabla() {
			for(int i= 0; i<=table.getRowCount(); i++ ) {
				modelo.removeRow(i);
				i=i-1;
			}
		}
			void limpiarcajas() {
				
				txtNombre.setText("");
				txtApellido.setText("");
				txtID.setText("");
				txtAreaAsignada.setText("");
				cmbSexo.setSelectedIndex(0);
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
					st.executeUpdate("Delete from conserje Where ID = "+id);
					
					JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
					
					limpiarcajas();
					limpiarTabla();
					
					
					}catch(Exception y) {
						
						
					}
					listar();
				}
			}			
	}

