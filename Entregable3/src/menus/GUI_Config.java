package menus;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao_IMPL.Conexion_IMPL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GUI_Config extends JFrame {

	private JLabel labelUsuario;
	private JLabel labelpassword;
	private JButton botonVolver;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton botonGuardar;
	private JLabel labelError;
	
	public GUI_Config() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR - CONFIGURACI\u00D3N");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonVolver = new JButton("VOLVER");
		botonVolver.addActionListener(new ManejadorEventos());
		botonVolver.setBounds(65, 144, 100, 30);
		contentPane.add(botonVolver);
		
		labelUsuario = new JLabel("USUARIO BD");
		labelUsuario.setBounds(65, 30, 90, 15);
		contentPane.add(labelUsuario);
		
		labelpassword = new JLabel("PASSWORD BD");
		labelpassword.setBounds(65, 70, 90, 15);
		contentPane.add(labelpassword);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(195, 30, 120, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 70, 120, 20);
		contentPane.add(passwordField);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.addActionListener(new ManejadorEventos());
		botonGuardar.setBounds(195, 144, 100, 30);
		contentPane.add(botonGuardar);
		
		labelError = new JLabel("");
		labelError.setBounds(100, 110, 140, 20);
		contentPane.add(labelError);
				
	}
	class ManejadorEventos implements ActionListener{
		private void botones (String val) throws SQLException{
			GUI_Gestor gestor;
			switch(val) {
			case "VOLVER":
				gestor = new GUI_Gestor();
				gestor.setVisible(true);
				setVisible(false);
				break;
			case "GUARDAR":
				String usr = textUsuario.getText();
				String pwd= new String (passwordField.getPassword());
				if((usr.equals("") || usr==null) || (pwd.equals("") || pwd==null)) {
					labelError.setText("Revise los campos");
				}
				else {
					Conexion_IMPL.getConexion(usr, pwd);
					gestor = new GUI_Gestor();
					gestor.setVisible(true);
					setVisible(false);
				}
				break;
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String val = e.getActionCommand();
			try {
				botones(val);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
