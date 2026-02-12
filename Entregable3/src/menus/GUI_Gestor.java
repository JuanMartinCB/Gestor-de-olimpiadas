package menus;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.Fondo;
import dao_IMPL.Conexion_IMPL;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI_Gestor extends JFrame {
	
	private JButton botonDeportista;
	private JButton botonDisciplina;
	private JButton botonPais;
	private JButton botonConfig;	
	private Fondo contentPane;

	public static void main(String[] args) {
			GUI_Gestor frame = new GUI_Gestor();
			frame.setVisible(true);
}

	public GUI_Gestor() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR DE OLIMPIADAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 600, 350);
		contentPane = new Fondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonDeportista = new JButton("Deportista");
		botonDeportista.setHorizontalTextPosition(SwingConstants.CENTER);
		botonDeportista.setVerticalTextPosition(SwingConstants.BOTTOM);
		botonDeportista.setIcon(new ImageIcon(getClass().getResource("/imagenes/deportista.png")));
		botonDeportista.addActionListener(new ManejadorEventos());
		botonDeportista.setBounds(34, 50, 100, 50);
		contentPane.add(botonDeportista);
		
		botonPais = new JButton("Pais");
		botonPais.setHorizontalTextPosition(SwingConstants.CENTER);
		botonPais.setVerticalTextPosition(SwingConstants.BOTTOM);
		botonPais.setIcon(new ImageIcon(getClass().getResource("/imagenes/paises.png")));
		botonPais.addActionListener(new ManejadorEventos());
		botonPais.setBounds(242, 50, 100, 50);
		contentPane.add(botonPais);
		
		//no tiene uso definido
		botonDisciplina = new JButton("Disciplina");
		botonDisciplina.setHorizontalTextPosition(SwingConstants.CENTER);
		botonDisciplina.setVerticalTextPosition(SwingConstants.BOTTOM);
		botonDisciplina.setIcon(new ImageIcon(getClass().getResource("/imagenes/disciplina.png")));
		botonDisciplina.setBounds(450, 50, 100, 50);
		contentPane.add(botonDisciplina);
		
		JButton sinDefinir1 = new JButton("Sin definir");
		sinDefinir1.setBounds(34, 250, 100, 50);
		contentPane.add(sinDefinir1);
		
		JButton sinDefinir2 = new JButton("Sin definir");
		sinDefinir2.setBounds(242, 150, 100, 50);
		contentPane.add(sinDefinir2);
		
		JButton sinDefinir3 = new JButton("Sin definir");
		sinDefinir3.setBounds(242, 250, 100, 50);
		contentPane.add(sinDefinir3);
		
		JButton sinDefinir4 = new JButton("Sin definir");
		sinDefinir4.setBounds(34, 150, 100, 50);
		contentPane.add(sinDefinir4);
		
		JButton sinDefinir5 = new JButton("Sin definir");
		sinDefinir5.setBounds(450, 150, 100, 50);
		contentPane.add(sinDefinir5);
		
		JButton sinDefinir6 = new JButton("Sin definir");
		sinDefinir6.setBounds(450, 250, 100, 50);
		contentPane.add(sinDefinir6);
		
		botonConfig = new JButton("");
		botonConfig.setIcon(new ImageIcon(getClass().getResource("/imagenes/config.png")));
		botonConfig.addActionListener(new ManejadorEventos());
		botonConfig.setBounds(545, 0, 39, 39);
		contentPane.add(botonConfig);
				
	}
	
	class ManejadorEventos implements ActionListener{
		private void botones (String val) {			
				switch(val) {
				case "Deportista":
					if (Conexion_IMPL.getCondicion()) {
						setVisible(false);
						GUI_Deportista deportista = new GUI_Deportista();
					}
					else {
						JOptionPane.showMessageDialog(null,"Debe conectarse a la base de datos");
					}
					break;
				case "":
					setVisible(false);
					GUI_Config config = new GUI_Config();
					break;
				case "Pais":
					if (Conexion_IMPL.getCondicion()) {
						setVisible(false);
						GUI_Pais pais = new GUI_Pais();
					}
					else {
						JOptionPane.showMessageDialog(null,"Debe conectarse a la base de datos");
					}
					break;
				}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String val = e.getActionCommand();
			botones(val);
		}
	}
}
