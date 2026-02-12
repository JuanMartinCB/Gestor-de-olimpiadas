package menus;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao_factory.DAOFactory;
import dao_interfaces.I_DAOPais;
import objetos.Pais;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUI_NuevoPais extends JFrame {

	private JPanel contentPane;
	private JTextField textNombrePais;
	private JLabel labelNombre;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JLabel labelError;
	private Pais pais_editar;
	private boolean condicionEditar=true;
	
	
	private boolean revisarCampos(String nombreP) {
        boolean cumple=true;
        String texto = "";
        if((nombreP.equals("") || nombreP==null)) {
            texto=texto.concat("<html>El campo es obligatorio.<br></html>");
            cumple=false;
        }
        if(!nombreP.matches("^[a-zA-Z\\sñÑ]+")) {
            texto=texto.concat("El nombre debe contener solo letras.");
            cumple=false;
        }
        
        labelError.setText(texto);
        return cumple;
    }
	

	public GUI_NuevoPais() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR - NUEVO PAIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelNombre = new JLabel("NOMBRE:");
		labelNombre.setBounds(179, 118, 70, 15);
		contentPane.add(labelNombre);
		
		textNombrePais = new JTextField();
		textNombrePais.setBounds(265, 115, 250, 20);
		contentPane.add(textNombrePais);
		textNombrePais.setColumns(10);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(300, 225, 100, 30);
		contentPane.add(botonGuardar);
		botonGuardar.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(150, 225, 100, 30);
		contentPane.add(botonVolver);
		botonVolver.addActionListener(new ManejadorEventos());
		
		labelError = new JLabel("");
		labelError.setBounds(265, 50, 250, 50);
		contentPane.add(labelError);
	}
	
	public GUI_NuevoPais(Pais p) {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR - EDITAR DEPORTISTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelNombre = new JLabel("NOMBRE:");
		labelNombre.setBounds(179, 118, 70, 15);
		contentPane.add(labelNombre);
		
		textNombrePais = new JTextField();
		textNombrePais.setBounds(267, 115, 250, 20);
		contentPane.add(textNombrePais);
		textNombrePais.setColumns(10);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(300, 225, 100, 30);
		contentPane.add(botonGuardar);
		botonGuardar.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(150, 225, 100, 30);
		contentPane.add(botonVolver);
		botonVolver.addActionListener(new ManejadorEventos());
		
		labelError = new JLabel("");
		labelError.setBounds(265, 50, 250, 50);
		contentPane.add(labelError);
		
		/////
		
		pais_editar=p;
		condicionEditar=false;
		textNombrePais.setText(pais_editar.getName());
	}

	class ManejadorEventos implements ActionListener{
		private void botones (String val) throws SQLException{
            GUI_Pais pais;
            switch(val) {
            case "VOLVER":
                setVisible(false);
                pais = new GUI_Pais();
                break;
            case "GUARDAR":
            	boolean condicion = true;
            	I_DAOPais dao_pais = DAOFactory.getPais();
            	List<Pais> lp = dao_pais.listar();
                int i;
                String nombreP = textNombrePais.getText();               
                if(revisarCampos(nombreP)) {                
                	if (!condicionEditar) {
                		pais_editar.setName(textNombrePais.getText());
        				dao_pais = DAOFactory.getPais();
        				dao_pais.modificar(pais_editar);
        				setVisible(false);
        				pais = new GUI_Pais();
                	}
                	else {
		                for (Pais x : lp) {
							if (x.getName().equals(nombreP)) {
								condicion = false;
								JOptionPane.showMessageDialog(null,"El pais ya existe");
								break;
							}
							
						} 
		                if (condicion) {
			                   setVisible(false);
			                   Pais p = new Pais();
			                   I_DAOPais dp = DAOFactory.getPais();
			                   p.setName(nombreP);
			                   i=dp.pedirId();
			                   p.setId(i);
			                   dp.cargar(p);
			                   pais = new GUI_Pais();
							}
		                }
		                break;
                	}
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
	
