//Doctor

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ventanas.Ventana_Inicio;
import ventanas.Ventana_Principal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

public class Doctor extends JFrame {

	private JPanel contentPane;
	public JLabel lblDoctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor();
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
	
	JMenuBar barra;
	JMenu Calcular, Sumar, Restar, Multiplicar;
	JMenuItem  Sum_dos, Sum_tres, M_dos, M_tres,  R_dos, R_tres;
	public static JTextField txtape;
	
	
	public Doctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 340);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		this.setJMenuBar(barra);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 23);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Consultas");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Verificar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Doctor_Verificar dv = new Doctor_Verificar();
				dv.setVisible(true);
				
				String doc = txtape.getText();
				dv.txtdoc.setText(doc);
				
				String nom = lblDoctor.getText();
				dv.lblcdoc.setText(nom);
				
				 String doct = txtape.getText();
		           
					
					try {
						dv.cne = dv.cn.getConnection();
						dv.st = dv.cne.createStatement();
						
						dv.rs = dv.st.executeQuery("Select ID, Nombre, Apellido, Sexo, Edad, Motivo, TipoSeguro, Cedula, Diagnostico, DoctorAsignado from paciente where  DoctorAsignado = '"+doct+"' and Tipo = 'Consulta' ");
						
						Object[] pa = new Object[10];
						
						dv.modelo = (DefaultTableModel) dv.tablapaci.getModel();
						
						while(dv.rs.next()) {
							
							pa[0] =dv.rs.getInt("ID");
							pa[1] =dv.rs.getString("Nombre");
							pa[2]= dv.rs.getString("Apellido");
							pa[3]= dv.rs.getString("Sexo");
							pa[4]= dv.rs.getInt("Edad");
							pa[5] =dv.rs.getString("Motivo");
							pa[6]= dv.rs.getString("TipoSeguro");
							pa[7]= dv.rs.getString("Cedula");
							pa[8]= dv.rs.getString("Diagnostico");
							pa[9]= dv.rs.getString("DoctorAsignado");
							
							dv.modelo.addRow(pa);
							
						}
						
						JOptionPane.showMessageDialog(null, "Registro buscado");
						
						dv.tablapaci.setModel(dv.modelo);
						
						}catch(Exception i) {
							
							JOptionPane.showMessageDialog(null, "Error, Ingrese la matricula del estudiante a buscar");
							
						}
			
				
			
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Internos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Habitaci\u00F3n");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Habitacion habi = new Habitacion();
				habi.setVisible(true);
				
				String nom = lblDoctor.getText();
				habi.lbldoct.setText(nom);
				
				String doc = txtape.getText();
				habi.txtdoct.setText(doc);
				
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Intensivos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intensivo in = new Intensivo();
				in.setVisible(true);
				
				String nom = lblDoctor.getText();
				in.lbldoct.setText(nom);
				
				String doc = txtape.getText();
				in.txtdoct.setText(doc);
				
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Pacientes");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Record");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Record rd = new Record();
				rd.setVisible(true);
				
				String nom = lblDoctor.getText();
				rd.lbldoc.setText(nom);
				
				String doc = txtape.getText();
				rd.txtdoct.setText(doc);
				
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_3 = new JMenu("M\u00E1s");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cerrar Secci\u00F3n");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Principal vp = new Ventana_Principal();
				vp.setVisible(true);
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_1);
		
		lblDoctor = new JLabel("");
		lblDoctor.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblDoctor.setBounds(10, 34, 353, 57);
		panel.add(lblDoctor);
		
		ImageIcon imgs1 = new ImageIcon(getClass().getResource("/iconos/hospital.png"));
		
		txtape = new JTextField();
		txtape.setEnabled(false);
		txtape.setEditable(false);
		txtape.setBounds(26, 200, 86, -10);
		panel.add(txtape);
		txtape.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Doctor.class.getResource("/iconos/texturadoc.jpg")));
		lblNewLabel.setBounds(0, 21, 434, 319);
		panel.add(lblNewLabel);
		
        //Calcular = new JMenu("Calcular");
		
		//menuBar.add(Calcular);
	}
}
