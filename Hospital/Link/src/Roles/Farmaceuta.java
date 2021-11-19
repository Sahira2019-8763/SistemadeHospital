//Farmaceuta

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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ventanas.Conexion;
import ventanas.Ventana_Principal;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Farmaceuta extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	DefaultTableModel modelo;
	private JPanel contentPane;
	public JLabel lblFarmaceuta;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JLabel lblNewLabel_1;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_2;
	private JTextField txtNombre;
	private JButton btnSalir;
	private JLabel lblNewLabel_3;
	private JLabel medicina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Farmaceuta frame = new Farmaceuta();
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
	public Farmaceuta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 604, 471);
		contentPane.add(panel);
		panel.setLayout(null);
	    
	    medicina = new JLabel("");
	    medicina.setBounds(456, 115, 125, 130);
	    panel.add(medicina);
	    ImageIcon imgs4 = new ImageIcon(getClass().getResource("/iconos/medicamento (1).png"));
		ImageIcon imag4 = new ImageIcon(imgs4.getImage().getScaledInstance(medicina.getWidth(), medicina.getHeight(), Image.SCALE_DEFAULT));
		medicina.setIcon(imag4);
		
	    lblFarmaceuta = new JLabel("");
	    lblFarmaceuta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFarmaceuta.setBounds(357, 11, 237, 25);
		panel.add(lblFarmaceuta);
		
		lblNewLabel = new JLabel("Suministro de Medicamentos ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(75, 66, 434, 38);
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 275, 547, 185);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=txtNombre.getText();
				int codigo= Integer.parseInt(txtCodigo.getText());
				
				try {
			cne = cn.getConnection();
			st = cne.createStatement();
			
			st.executeUpdate("Insert into medicamentos (Codigo, Nombre) values ("+codigo+", '"+nombre+"')");
			
			JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
		     limpiarcajas();
			limpiarTabla();
			
			} catch (Exception p){
		
				//JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
		
			}
				listar();
			}
		});
	
		
		btnGuardar.setBounds(309, 139, 89, 23);
		panel.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Eliminar();
				listar();
				
			}
		});
		btnEliminar.setBounds(309, 209, 89, 23);
		panel.add(btnEliminar);
		
		lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(36, 142, 61, 14);
		panel.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(133, 140, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(36, 210, 61, 19);
		panel.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 207, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnSalir = new JButton("Cerrar Secci\u00F3n");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal vp = new Ventana_Principal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(0, 0, 116, 36);
		panel.add(btnSalir);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Farmaceuta.class.getResource("/iconos/texturaFarma.jpg")));
		lblNewLabel_3.setBounds(0, 0, 604, 471);
		panel.add(lblNewLabel_3);
	}



public void listar() {
	
	try {
		
		cne = cn.getConnection();
		st = cne.createStatement();
		rs = st.executeQuery("Select * from medicamentos");
		Object[] paci = new Object[2];
		modelo = (DefaultTableModel) table.getModel();
		
		while(rs.next()){
	
			
			paci[0] =rs.getInt("Codigo");
			paci[1]= rs.getString("Nombre");
			
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
		
	txtNombre.setText("");
	txtCodigo.setText("");
	
    
}

void Eliminar() {
	
	int codigo =Integer.parseInt(txtCodigo.getText());
	
	if(txtCodigo.getText()=="") {
		
			JOptionPane.showMessageDialog(null, "Escriba el codigo, por favor");
	}
	else {
		try {
			
		cne = cn.getConnection();
		st = cne.createStatement();
		st.executeUpdate("Delete from medicamentos Where Codigo = "+codigo);
		
		JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
		
		limpiarcajas();
		limpiarTabla();
		
		}catch(Exception y) {
			
		//	JOptionPane.showMessageDialog(null, "ERROR!");
		}
		
	}
}	
}
