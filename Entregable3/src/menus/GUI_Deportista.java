package menus;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao_factory.DAOFactory;
import dao_interfaces.I_DAOAtletaEnDisciplina;
import dao_interfaces.I_DAODeportista;
import dao_interfaces.I_DAODisciplina;
import dao_interfaces.I_DAOPais;
import objetos.AtletaEnDisciplina;
import objetos.Deportista;
import objetos.Disciplina;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class GUI_Deportista extends JFrame {
	
	private JButton botonNuevo;
	private JButton botonExportar;
	private JButton botonVolver;
	private JPanel contentPane;
	private JButton botonEliminar;
	private JButton botonEditar;
	private JTable table;
	private JScrollPane scroll;
	private TableModel model;

	private final Object [] nombresColumnas= {"Apellido y nombre", "Pais", "Disciplina"};
	private Object[] y;
	
	public GUI_Deportista() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setBounds(100, 100, 600, 350);
		setTitle("GESTOR - DEPORTISTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonNuevo = new JButton("+ NUEVO");
		botonNuevo.setBounds(10, 40, 100, 30);
		contentPane.add(botonNuevo);
		botonNuevo.addActionListener(new ManejadorEventos());
		
		botonExportar = new JButton("EXPORTAR CSV");
		botonExportar.setBounds(222, 40, 140, 30);
		contentPane.add(botonExportar);
		botonExportar.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(474, 40, 100, 30);
		contentPane.add(botonVolver);		
		botonVolver.addActionListener(new ManejadorEventos());
		
		botonEliminar = new JButton("ELIMINAR");
		botonEliminar.setBounds(118, 40, 100, 30);
		contentPane.add(botonEliminar);
		botonEliminar.addActionListener(new ManejadorEventos());
		
		botonEditar = new JButton("EDITAR");
		botonEditar.setBounds(366, 40, 100, 30);
		contentPane.add(botonEditar);	
		botonEditar.addActionListener(new ManejadorEventos());
	
		crearTabla();
		scroll = new JScrollPane(table);
		scroll.setBounds(40, 100, 510, 200);
		contentPane.add(scroll);		

	}
	
	private void crearTabla() {
		model = new TableModel();
		table = new JTable(model);
		table.setTableHeader(null);

	}

	class TableModel extends DefaultTableModel {
		private void cargar() {
			I_DAODeportista d = DAOFactory.getDeportista();
			I_DAOPais p = DAOFactory.getPais();
			I_DAODisciplina dp = DAOFactory.getDisciplina();
			I_DAOAtletaEnDisciplina ded = DAOFactory.getAtletaEnDisciplina();
			List<Deportista> ld = d.listar();
			y=new Object[3];
			for (Deportista x : ld) {
			   y[0]= x.getApellido() + " " + x.getNombre();
			   y[1]= p.buscar(x.getId_pais()).getName();
			   y[2]= dp.buscar(ded.buscar(x.getId()).getIdDisciplina()).getNombre();
               addRow(y);
			}
		}
		@Override
		public void setColumnIdentifiers(Object[] x) {
			super.setColumnIdentifiers(x);
		}
		@Override
		public void addRow(Object[] rowData) {
			super.addRow(rowData);
		}
		@Override
		public void removeRow(int row) {
			super.removeRow(row);
		}
	
		public TableModel() {
			setColumnIdentifiers(nombresColumnas);
			cargar();
			
		}
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Class getColumClass(int colum) {
			return this.getValueAt(0, colum).getClass();
		}
	}

	class ManejadorEventos implements ActionListener{
		private void botones (String val) throws SQLException{
			switch(val) {
			case "VOLVER":
				setVisible(false);
				GUI_Gestor gestor = new GUI_Gestor();
				gestor.setVisible(true);
				break;
			case "+ NUEVO":
				setVisible(false);
				GUI_NuevoDeportista nuevoD = new GUI_NuevoDeportista();
				break;
			case "ELIMINAR":
				if (table.getSelectedRow() != -1) {
					int opcion = JOptionPane.showConfirmDialog(null,"Desea confirmar?", "Seleccione una opcion",JOptionPane.YES_NO_OPTION);
					switch (opcion) {
					case 0:
						Deportista o_deportista = new Deportista();
						AtletaEnDisciplina o_aed = new AtletaEnDisciplina();
						Disciplina o_disciplina = new Disciplina();
						I_DAODeportista dao_deportista = DAOFactory.getDeportista();
						I_DAODisciplina dao_disciplina = DAOFactory.getDisciplina();
						I_DAOAtletaEnDisciplina dao_aed = DAOFactory.getAtletaEnDisciplina();
						int fila=table.getSelectedRow();
						String disciplina = (String) model.getValueAt(fila, 2);
						
						o_disciplina= dao_disciplina.buscarID(disciplina);											
						o_aed = dao_aed.buscarID_Deportista(o_disciplina.getId());				
						o_deportista=dao_deportista.buscar(o_aed.getIdDeportista());
						dao_aed.eliminar(o_aed);
						dao_deportista.eliminar(o_deportista);
						model.removeRow(fila);
						break;
					case 1:
						break;
					}					
				}
				break;
			case "EDITAR":
				if (table.getSelectedRow() != -1) {
					int fila = table.getSelectedRow();
					String deportista = (String) model.getValueAt(fila, 0);
					String[] nuevoStr = deportista.split("\\s+");
					String apellido = nuevoStr[0];
					I_DAOPais dao_pais = DAOFactory.getPais();
					I_DAODisciplina dao_disciplina = DAOFactory.getDisciplina();
					I_DAODeportista dao_deportista = DAOFactory.getDeportista();
					I_DAOAtletaEnDisciplina dao_aed = DAOFactory.getAtletaEnDisciplina();
					Deportista d = null;
					for (Deportista x:dao_deportista.listar()) {
						if(x.getApellido().equals(apellido)) {
							d=x;
							break;
						}
					}
					setVisible(false);
					GUI_NuevoDeportista gui_nuevoD = new GUI_NuevoDeportista(d,dao_pais.buscar(d.getId_pais()).getName(),dao_disciplina.buscar(dao_aed.buscar(d.getId()).getIdDisciplina()).getNombre());
				}
				break;
			case "EXPORTAR CSV":
				try 
		        {
		            File archivo = new File("datos.csv");
		            FileWriter excel = new FileWriter(archivo);
		             for(int i = 0; i < model.getColumnCount(); i++)
		             {
		                excel.write(model.getColumnName(i) + ",");
		             }
		             excel.write("\n");
		             for(int i=0; i< model.getRowCount(); i++) 
		             {
		                for(int j=0; j < model.getColumnCount(); j++) 
		                {
		                    String data = (String)model.getValueAt(i, j);
		                    if(data == "null")
		                    {
		                        data="";
		                    }
		                    excel.write(data+",");
		                }
		                excel.write("\n");
		            }

		        excel.close();
		            JOptionPane.showMessageDialog(null, "Archivo Creado");
		            
		        } catch (FileNotFoundException e) {
		        	e.printStackTrace();
		        	 } catch (IOException e) {
		        	e.printStackTrace();
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
