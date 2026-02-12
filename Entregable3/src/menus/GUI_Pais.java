package menus;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao_factory.DAOFactory;
import dao_interfaces.I_DAODeportista;
import dao_interfaces.I_DAOPais;
import objetos.Deportista;
import objetos.Pais;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUI_Pais extends JFrame {

	private JPanel contentPane;
	private JButton botonNuevo;
	private JButton botonVolver;
	private JTable table;
    private JButton botonEditar;
    private JButton botonEliminar;
    private JScrollPane scroll;
    private TableModel model;
    private final Object [] nombresColumnas= {"Id", "Pais"};
    private Object[] y;
    
	public GUI_Pais() {
		Image img = new ImageIcon(getClass().getResource("/imagenes/olimpiadas.png")).getImage();
		setIconImage(img);
		setTitle("GESTOR - PAIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 600, 350);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonNuevo = new JButton("+ NUEVO");
		botonNuevo.setBounds(40, 60, 100, 30);
		contentPane.add(botonNuevo);
		botonNuevo.addActionListener(new ManejadorEventos());
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(450, 60, 100, 30);
		contentPane.add(botonVolver);
		botonVolver.addActionListener(new ManejadorEventos());
		
		botonEliminar = new JButton("ELIMINAR");
		botonEliminar.setBounds(307, 60, 100, 30);
		contentPane.add(botonEliminar);
		botonEliminar.addActionListener(new ManejadorEventos());
		
		botonEditar = new JButton("EDITAR");
		botonEditar.setBounds(170, 60, 100, 30);
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
            I_DAOPais p = DAOFactory.getPais();
            List<Pais> ld = p.listar();
            Collections.sort(ld, new Comparator<Pais>() {
                @Override
                public int compare(Pais p1, Pais p2) {
                    return new String(p1.getName()).compareTo(new String(p2.getName()));
                }
            });
            y=new Object[2];
            for (Pais x : ld) {
               y[0]= x.getId();
               y[1]= p.buscar(x.getId()).getName();
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
				GUI_Gestor gestor = new GUI_Gestor();
				gestor.setVisible(true);
				setVisible(false);
				break;
			case "+ NUEVO":
				GUI_NuevoPais nuevoP = new GUI_NuevoPais();
				setVisible(false);
				break;
			case "ELIMINAR":
				if (table.getSelectedRow() != -1) {
					int opcion = JOptionPane.showConfirmDialog(null,"Desea confirmar?", "Seleccione una opcion",JOptionPane.YES_NO_OPTION);
					switch(opcion) {
					case 0:
						Pais o_pais = new Pais();
						I_DAOPais dao_pais = DAOFactory.getPais();
						I_DAODeportista dao_deportista = DAOFactory.getDeportista();
						List<Pais> lp = dao_pais.listar();
						List<Deportista> ld = dao_deportista.listar();
						boolean condicion = true;
						
						int fila= table.getSelectedRow();
						String pais_seleccionado = (String) model.getValueAt(fila, 1);					
						o_pais=dao_pais.buscarID(pais_seleccionado);
						
						for (Deportista x : ld) {
							if (o_pais.getId() == x.getId_pais()) {
								condicion = false;
								JOptionPane.showMessageDialog(null,"El pais tiene deportistas asociados");
								break;
							}
							
						}
						
						if (condicion) {		
							dao_pais.eliminar(o_pais);
							model.removeRow(fila);
						}
						break;
					case 1:
						break;
					}
				}
				break;
			case "EDITAR":
				if (table.getSelectedRow() != -1) {
					Pais p = new Pais();
					I_DAOPais dao_pais = DAOFactory.getPais();
					int fila= table.getSelectedRow();
					String pais_seleccionado = (String) model.getValueAt(fila, 1);
					p.setName(pais_seleccionado);
					List<Pais> lp = dao_pais.listar();
					for (Pais x: lp) {
						if(x.getName().equals(pais_seleccionado)) {
							p.setId(x.getId());
							break;
						}
					}
					setVisible(false);
					GUI_NuevoPais gui_nuevoP = new GUI_NuevoPais(p);
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
