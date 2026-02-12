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
import dao_interfaces.I_DAOAtletaEnDisciplina;
import dao_interfaces.I_DAODeportista;
import dao_interfaces.I_DAODisciplina;
import dao_interfaces.I_DAOPais;
import objetos.AtletaEnDisciplina;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GUI_NuevoDeportista extends JFrame {

	private JLabel labelNombre;
	private JLabel labelApellido;
	private JPanel contentPane;
	private JLabel labelEmail;
	private JLabel labelTelefono;
	private JLabel labelPais;
	private JLabel labelDisciplina;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEmail;
	private JTextField textTelefono;
	private JComboBox<String> comboPais;
	private JComboBox<String> comboDisciplina;
	private JLabel labelError;
	private JButton botonGuardar;
	private JButton botonVolver;
	private int ID;
	private boolean condicion = true;
	private boolean existeDeportista = false;

	
	private boolean revisarCampos(String nombreD, String apellidoD, String emailD, String telefonoD, String paisD, String disciplinaD) {
        boolean cumple=true;
        String texto = "";
        if((nombreD.equals("") || nombreD==null)||(apellidoD.equals("") || apellidoD==null)||(emailD.equals("") || emailD==null)||(telefonoD.equals("") || telefonoD==null)||(paisD.equals("") || disciplinaD.equals(""))) {
            texto=texto.concat("<html>Todos los campos son obligatorios.<br></html>");
            cumple=false;
        }
        if(!nombreD.matches("^[a-zA-Z\\sñÑ]+")||!apellidoD.matches("^[a-zA-Z\\sñÑ]+")) {
            texto=texto.concat("<html>Nombre y apellido debe contener solo letras.<br></html>");
            cumple=false;
        }
        if (!emailD.matches("^(.+)@(.+)$")){
            texto=texto.concat("<html>El email debe contener una palabra seguido de @ y .</br></html>");
            cumple=false;
        }
        if (!telefonoD.matches("[0-9]+")) {
            texto=texto.concat("El telefono debe contener solo numeros.");
            cumple=false;
        }
        labelError.setText(texto);
        return cumple;
    }

	public GUI_NuevoDeportista(Deportista d, String pais, String disciplina) {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelNombre = new JLabel("NOMBRE:");
		labelNombre.setBounds(210, 70, 70, 20);
		contentPane.add(labelNombre);		
		
		textNombre = new JTextField();
		textNombre.setBounds(350, 70, 250, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		labelApellido = new JLabel("APELLIDO:");
		labelApellido.setBounds(210, 100, 70, 20);
		contentPane.add(labelApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(350, 100, 250, 20);
		contentPane.add(textApellido);
		
		labelEmail = new JLabel("EMAIL:");
		labelEmail.setBounds(210, 130, 70, 20);
		contentPane.add(labelEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(350, 130, 250, 20);
		contentPane.add(textEmail);
		
		labelTelefono = new JLabel("TELEFONO:");
		labelTelefono.setBounds(210, 160, 70, 20);
		contentPane.add(labelTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(350, 160, 250, 20);
		contentPane.add(textTelefono);
		
		labelPais = new JLabel("PAIS");
		labelPais.setBounds(210, 190, 70, 20);
		contentPane.add(labelPais);
	
		labelError = new JLabel("");
		labelError.setBounds(210, 266, 280, 60);
		contentPane.add(labelError);
		labelError.setVisible(true);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(400, 350, 100, 30);
		contentPane.add(botonGuardar);
		botonGuardar.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(200, 350, 100, 30);
		contentPane.add(botonVolver);
		botonVolver.addActionListener(new ManejadorEventos());
		
		labelDisciplina = new JLabel("DISCIPLINA");
		labelDisciplina.setBounds(210, 220, 70, 20);
		contentPane.add(labelDisciplina);	
		
		comboDisciplina = new JComboBox<String>();
		comboDisciplina.setBounds(350, 220, 250, 20);
		comboPais = new JComboBox<String>();
		comboPais.setBounds(350, 190, 250, 20);
		llenarCombos();
		contentPane.add(comboDisciplina);
		contentPane.add(comboPais);
		
		
		////
		
		ID = d.getId();
		textNombre.setText(d.getNombre());
		textApellido.setText(d.getApellido());
		textEmail.setText(d.getMail());
		textTelefono.setText(d.getTelefono());
		comboDisciplina.setSelectedItem(disciplina);
		comboPais.setSelectedItem(pais);
		condicion = false;
		setTitle("GESTOR - EDITAR DEPORTISTA");
		setVisible(true);
		
	}
	public GUI_NuevoDeportista() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR - NUEVO DEPORTISTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelNombre = new JLabel("*NOMBRE:");
		labelNombre.setBounds(210, 70, 70, 20);
		contentPane.add(labelNombre);		
		
		textNombre = new JTextField();
		textNombre.setBounds(350, 70, 250, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		labelApellido = new JLabel("*APELLIDO:");
		labelApellido.setBounds(210, 100, 70, 20);
		contentPane.add(labelApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(350, 100, 250, 20);
		contentPane.add(textApellido);
		
		labelEmail = new JLabel("*EMAIL:");
		labelEmail.setBounds(210, 130, 70, 20);
		contentPane.add(labelEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(350, 130, 250, 20);
		contentPane.add(textEmail);
		
		labelTelefono = new JLabel("*TELEFONO:");
		labelTelefono.setBounds(210, 160, 70, 20);
		contentPane.add(labelTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(350, 160, 250, 20);
		contentPane.add(textTelefono);
		
		labelPais = new JLabel("*PAIS");
		labelPais.setBounds(210, 190, 70, 20);
		contentPane.add(labelPais);
	
		labelError = new JLabel("");
		labelError.setBounds(210, 266, 280, 60);
		contentPane.add(labelError);
		labelError.setVisible(true);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(400, 350, 100, 30);
		contentPane.add(botonGuardar);
		botonGuardar.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(200, 350, 100, 30);
		contentPane.add(botonVolver);
		botonVolver.addActionListener(new ManejadorEventos());
		
		labelDisciplina = new JLabel("*DISCIPLINA");
		labelDisciplina.setBounds(210, 220, 70, 20);
		contentPane.add(labelDisciplina);	
		
		comboDisciplina = new JComboBox<String>();
		comboDisciplina.setBounds(350, 220, 250, 20);
		comboPais = new JComboBox<String>();
		comboPais.setBounds(350, 190, 250, 20);
		comboDisciplina.addItem("");
		comboPais.addItem("");
		llenarCombos();
		contentPane.add(comboDisciplina);
		contentPane.add(comboPais);
		
	}
	
	private void llenarCombos() {
		I_DAOPais p = DAOFactory.getPais();
		I_DAODisciplina d = DAOFactory.getDisciplina();
		List<Disciplina>ld = d.listar();
        List<Pais> lp = p.listar();
        for (Pais x : lp) {
            comboPais.addItem(x.getName());        
         }
        for (Disciplina y : ld) {
        	 comboDisciplina.addItem(y.getNombre());
        }

    }
    
	class ManejadorEventos implements ActionListener{
		private void botones (String val) throws SQLException{
			switch(val) {
			case "VOLVER":
				setVisible(false);
				GUI_Deportista deportista = new GUI_Deportista();
				break;
			case "GUARDAR":
                int i;
                String nombreD = textNombre.getText();
                String apellidoD = textApellido.getText();
                String emailD = textEmail.getText();
                String telefonoD = textTelefono.getText();
                String paisD = (String) comboPais.getSelectedItem();
                String disciplinaD = (String) comboDisciplina.getSelectedItem();
                
                if(revisarCampos(nombreD,apellidoD,emailD,telefonoD,paisD,disciplinaD)) {
                	if (!condicion) {
                		Deportista o_d = new Deportista();
                		Pais o_p = new Pais();
                		Disciplina o_dis=new Disciplina();
                		AtletaEnDisciplina o_aed=new AtletaEnDisciplina();
                		o_d.setNombre(nombreD);
                		o_d.setApellido(apellidoD);
                		o_d.setMail(emailD);
                		o_d.setTelefono(telefonoD);
                		o_d.setId(ID);
                		String pais_combo = String.valueOf(comboPais.getSelectedItem());
                		String disci_combo = String.valueOf(comboDisciplina.getSelectedItem());  
                		I_DAODeportista dao_deportista = DAOFactory.getDeportista();
                		I_DAOPais dao_pais = DAOFactory.getPais();                  
 	                   	I_DAODisciplina dao_disciplina = DAOFactory.getDisciplina();   
 	                   	I_DAOAtletaEnDisciplina dao_aed = DAOFactory.getAtletaEnDisciplina();
 	                   	o_p=dao_pais.buscarID(pais_combo);
 	                   	o_d.setId_pais(o_p.getId());
 	                   	o_dis=dao_disciplina.buscarID(disci_combo);
 	                   	o_aed.setIdDisciplina(o_dis.getId());
 	                   	o_aed.setIdDeportista(ID);
 	                   	dao_aed.modificar(o_aed);
 	                   	dao_deportista.modificar(o_d);
 	                   	deportista = new GUI_Deportista();
 	                   	setVisible(false);
 	                   	
                	}
                	else {
                		I_DAODeportista ddep = DAOFactory.getDeportista();                  
                		List<Deportista> ld = ddep.listar();
                		setVisible(false);
	                	for (Deportista x : ld) {
							if (x.getNombre().equals(textNombre.getText()) && (x.getApellido().equals(textApellido.getText()))) {
								existeDeportista = true;
								JOptionPane.showMessageDialog(null,"El deportista ya tiene una disciplina");
								deportista = new GUI_Deportista(); 
								break;
							}
								
						}                		
                		if(!existeDeportista){
                  
	                   String p_selecc;
	                   String d_selecc;
	                   
	                   Deportista dep = new Deportista();
	                   Disciplina disci = new Disciplina();
	                   AtletaEnDisciplina aed = new AtletaEnDisciplina();
	                   Pais pais = new Pais();
	                   
	                   I_DAOPais dpais = DAOFactory.getPais();                  
	                   I_DAODisciplina ddisciplina = DAOFactory.getDisciplina();   
	                   I_DAOAtletaEnDisciplina daed = DAOFactory.getAtletaEnDisciplina();
	                   
	                   i=ddep.pedirId();
	                   dep.setNombre(nombreD);
	                   dep.setApellido(apellidoD);
	                   dep.setMail(emailD);
	                   dep.setTelefono(telefonoD);
	                   dep.setId(i);   
	                   
	                   p_selecc=String.valueOf(comboPais.getSelectedItem());
	                   d_selecc=String.valueOf(comboDisciplina.getSelectedItem());  
	                   
	                   pais = dpais.buscarID(p_selecc);
	                   
	                   dep.setId_pais(pais.getId());
	                   
	                   disci = ddisciplina.buscarID(d_selecc);
	                   
	                   aed.setIdDisciplina(disci.getId());
	                   aed.setIdDeportista(i);
	                   
	                   ddep.cargar(dep);
	                   daed.cargar(aed);		
	                   
	                   deportista = new GUI_Deportista();          
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
