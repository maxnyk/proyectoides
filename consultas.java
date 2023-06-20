package proyectoides;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class consultas {
	private static JFrame ventana;
	private static JTable table;
    private static JScrollPane scrollPane;
    private static JPanel panel;
	public consultas (String user) {
	ventana = new JFrame("Consultas");
    ventana.setSize(900, 400);
   

    panel = new JPanel();

    JLabel label = new JLabel("Buscar entre:");

    JButton users= new JButton("Usuarios");
    JButton libros= new JButton("Libros");
    JButton pelis= new JButton("Películas");
    JButton docs= new JButton("Documentales");
    JButton mus= new JButton("Musica");
   
    

    
    users.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	usuarios consulta = new usuarios("consulta","1");
        
        }
    });
    libros.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	Libros consulta = new Libros("consulta","asd");
            
        }
    });
    pelis.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
  
            
        }
    });

    panel.add(label);
   
  
    if (user.equals("admin")) {
    	panel.add(users);
    }
    panel.add(libros);
    panel.add(pelis);
    panel.add(docs);
    panel.add(mus);
    
    ventana.add(panel);

    ventana.setVisible(true); 
 
    
    
	}
	public static void consulta(String user, String pass, String nombre, String apellidos, String dni, String fn, String email, int codgrupo) throws SQLException{
		JFrame consulta  = new JFrame("Consultas");
		consulta.setSize(900, 400);
		consulta.setVisible(true); 
		table = new JTable();
		scrollPane = new JScrollPane(table);
		consulta.add(scrollPane);
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "SELECT * FROM usuarios WHERE 1=1";
	    	if (user != null && !user.isEmpty()) {
	    		sql += " AND username LIKE '%" + user + "%'";
	        }
	        if (pass != null && !pass.isEmpty()) {
	        	sql += " AND password LIKE '%" + pass + "%'";
	        }
	        if (nombre != null && !nombre.isEmpty()) {
	        	sql += " AND nombre = '" + nombre + "'";
	        }
	        if (apellidos != null && !apellidos.isEmpty()) {
	        	sql += " AND apellidos LIKE '%" + apellidos + "%'";
	        }
	        if (dni != null && !dni.isEmpty()) {
	        	sql += " AND dni LIKE '%" + dni + "%'";
	        }
	        if (fn != null && !fn.isEmpty()) {	        	
	        	sql += " AND fechanacimiento = '" + fn + "'";
	        }
	        if (email != null && !email.isEmpty()) {
	        	sql += " AND email = '" + email + "'";
	        }
	        if (codgrupo != 0) {
	        	sql += " AND codigogrupo = '" + codgrupo + "'";
	        }
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }

            // Crear el modelo de la tabla
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Agregar las filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }

            // Asignar el modelo a la tabla
            table.setModel(tableModel);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        connector.closeConnection(connection);
	    }
    }
    
	public static void consulta2(String isbn, String titulo, String autor, String editorial, String tem, int npag) throws SQLException{
		JFrame consulta  = new JFrame("Consultas");
		consulta.setSize(900, 400);
		consulta.setVisible(true); 
		table = new JTable();
		scrollPane = new JScrollPane(table);
		consulta.add(scrollPane);
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "SELECT * FROM libros WHERE 1=1";
	    	if (isbn != null && !isbn.isEmpty()) {
	    		sql += " AND isbn LIKE '%" + isbn + "%'";
	        }
	        if (titulo != null && !titulo.isEmpty()) {
	        	sql += " AND titulo LIKE '%" + titulo + "%'";
	        }
	        if (autor != null && !autor.isEmpty()) {
	        	sql += " AND autor = '" + autor + "'";
	        }
	        if (editorial != null && !editorial.isEmpty()) {
	        	sql += " AND editorial LIKE '%" + editorial + "%'";
	        }
	        if (tem != null && !tem.isEmpty()) {
	        	sql += " AND tem LIKE '%" + tem + "%'";
	        }
	        if (npag != 0) {
	        	sql += " AND npag = '" + npag + "'";
	        }
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }

            // Crear el modelo de la tabla
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Agregar las filas al modelo
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }

            // Asignar el modelo a la tabla
            table.setModel(tableModel);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        connector.closeConnection(connection);
	    }
    }
	public static void main(String[] args) {		

    }
}

