//Enfermera

package Roles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Departamentos.Emergencia;
import ventanas.Conexion;
import ventanas.Ventana_Principal;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;

public class Enfermera extends JFrame {
	
	Conexion cn = new Conexion();
	Connection cne;
	Statement st;
	ResultSet rs;
	DefaultTableModel modelo;

	private JPanel contentPane;
	public JLabel lblEnfermera;
	public static JTextField txtape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enfermera frame = new Enfermera();
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
	public Enfermera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 453, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblEnfermera = new JLabel("");
		lblEnfermera.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnfermera.setBounds(10, 120, 433, 39);
		panel.add(lblEnfermera);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 453, 21);
		panel.add(menuBar);
		
		JMenu Men1 = new JMenu("Opciones");
		menuBar.add(Men1);
		
		JMenuItem Men2 = new JMenuItem("Emergencia");
		Men2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Emergencia em=new Emergencia();
			em.setVisible(true);
			
			String nom = lblEnfermera.getText();
			em.lblenfer.setText(nom);
			
			dispose();
				
			}
		});
		Men1.add(Men2);
		
		JMenuItem Men3 = new JMenuItem("Record Pacientes");
		Men3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record rd = new Record();
				rd.setVisible(true);
				
				String ape = txtape.getText();
				rd.txtdoct.setText(ape);
				
				String nom = lblEnfermera.getText();
				rd.lbldoc.setText(nom);
				
				dispose();
			}
		});
		Men1.add(Men3);
		
		JMenu mnNewMenu_1 = new JMenu("M\u00E1s");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cerrar Secci\u00F3n");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal vp = new Ventana_Principal();
				vp.setVisible(true);
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		txtape = new JTextField();
		txtape.setBounds(20, 147, 86, -11);
		panel.add(txtape);
		txtape.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Enfermera.class.getResource("/iconos/texturaenfer.jpg")));
		lblNewLabel.setBounds(0, 0, 453, 305);
		panel.add(lblNewLabel);
	
		}

	}


