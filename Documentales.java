package proyectoides;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Documentales extends JFrame {
private int duracion;
private String titulo;
private String productora;
private String premios;
private String docsrel;
public int getDuracion() {
	return duracion;
}
public void setDuracion(int duracion) {
	this.duracion = duracion;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getProductora() {
	return productora;
}
public void setProductora(String productora) {
	this.productora = productora;
}
public String getPremios() {
	return premios;
}
public void setPremios(String premios) {
	this.premios = premios;
}
public String getDocsrel() {
	return docsrel;
}
public void setDocsrel(String docsrel) {
	this.docsrel = docsrel;
}
public Documentales() {
	setTitle("Interfaz documentales");
    setSize(400, 350); 
    setLayout(null);
    		
    JTextField titulo3 = new JTextField(20);
    JTextField produ = new JTextField(20);
    JTextField dur2	 = new JTextField(20);
    JTextField docsrel = new JTextField(20);
    JTextField premios2 = new JTextField(20);
    
    JLabel label111=new JLabel("Título:");
    JLabel label222=new JLabel("Productora:");
    JLabel label333=new JLabel("Duración:");
    String label4Text = "<html>Documentales<br>relacionados:</html>";
    JLabel label444=new JLabel(label4Text);
    JLabel label555=new JLabel("Premios:");
    
    JButton altadoc = new JButton("Confirmar");
    
    add(label111);
    add(titulo3);
    add(label222);
    add(produ);
    add(label333);
    add(dur2);
    add(label444);
    add(docsrel);
    add(premios2);
    add(label555);
    add(altadoc);

    label111.setBounds(25, 25, 100, 25);
    label222.setBounds(25, 75, 100, 25);
    label333.setBounds(25, 125, 100, 25);
    label555.setBounds(25, 175, 100, 25);
    label444.setBounds(25, 225, 100, 30);
    titulo3.setBounds(175, 25, 200, 25);
    produ.setBounds(175, 75, 200, 25);
    dur2.setBounds(175, 125, 200, 25);
    premios2.setBounds(175, 175, 200, 25);
    docsrel.setBounds(175, 225, 200, 25);
    altadoc.setBounds(100, 275, 200, 25);
    
    setVisible(true);
    altadoc.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	String titulo=titulo3.getText();
        	String productora=produ.getText();
        	String duracion=dur2.getText();
        	String docrelacion=docsrel.getText();
        	String premios=premios2.getText();
        	int dur = Integer.parseInt(duracion);
        	try {
				dardealta(titulo, productora, dur, docrelacion, premios);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    });

}
public static void mostrar() throws SQLException {
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String usuario = "root";
    String contraseña = "1345";
    String consultaSql = "SELECT * FROM documentales";
    connectbd connector = new connectbd(url, usuario, contraseña);
    Connection connection = null;

    try {
        // Obtener metadatos de la consulta para construir el modelo de la tabla
    	connection = connector.getConnection();
    	Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(consultaSql);
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
        //usuariocombinado.table.setModel(tableModel);

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        connector.closeConnection(connection);
    }
}
public static void dardealta(String tit, String prod, int dur, String docsrel, String prem) throws SQLException {
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String username = "root";
    String password = "1345";
    connectbd connector = new connectbd(url, username, password);
    Connection connection = null;
    try {
    	connection = connector.getConnection();
    	String sql = "INSERT INTO documentales (titulo, productora, duracion, docsrel, premios) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, tit);
        statement.setString(2, prod);
        statement.setInt(3, dur);
        statement.setString(4, docsrel);
        statement.setString(5, prem);

        int filasInsertadas = statement.executeUpdate();
        System.out.println("Se insertaron " + filasInsertadas + " registros");
        JOptionPane.showMessageDialog(null, "Registro completado");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	mostrar();
        connector.closeConnection(connection);
    }
}
public static void eliminar(String tit) throws SQLException{
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String username = "root";
    String password = "1345";
    connectbd connector = new connectbd(url, username, password);
    Connection connection = null;
    try {
    	connection = connector.getConnection();
    	String sql = "DELETE FROM documentales WHERE titulo=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tit);
        int filasAfectadas = statement.executeUpdate();
        System.out.println("Se eliminaron " + filasAfectadas + " registros");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	 mostrar();
        connector.closeConnection(connection);
    }
}

}