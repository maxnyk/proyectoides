package proyectoides;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Libros{
private static JFrame ventanalibros;
private String isbn;
private String titulo;
private String autor;
private String editorial;
private int npag;
private String tem;
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getAutor() {
	return autor;
}
public void setAutor(String autor) {
	this.autor = autor;
}
public String getEditorial() {
	return editorial;
}
public void setEditorial(String editorial) {
	this.editorial = editorial;
}
public int getNpag() {
	return npag;
}
public void setNpag(int npag) {
	this.npag = npag;
}
public String getTem() {
	return tem;
}
public void setTem(String tem) {
	this.tem = tem;
}
public Libros(String tipo, String oldisbn) {
	ventanalibros = new JFrame();
    ventanalibros.setTitle("Interfaz Libro");
    ventanalibros.setSize(400, 400); 
    ventanalibros.setLayout(null);
    		
    JTextField ISBN = new JTextField(20);
    JTextField titulo = new JTextField(20);
    JTextField autor = new JTextField(20);
    JTextField edi = new JTextField(20);
    JTextField pag = new JTextField(20);
    JTextField tem = new JTextField(20);
    
    JLabel label1=new JLabel("ISBN:");
    JLabel label2=new JLabel("Título:");
    JLabel label3=new JLabel("Autor:");
    JLabel label4=new JLabel("Editorial:");
    JLabel label5=new JLabel("Nº de páginas:");
    JLabel label6=new JLabel("Temática:");

    
    JButton botonRegistro = new JButton("Confirmar");
    
    ventanalibros.add(label1);
    ventanalibros.add(ISBN);
    ventanalibros.add(label2);
    ventanalibros.add(titulo);
    ventanalibros.add(label3);
    ventanalibros.add(autor);
    ventanalibros.add(label4);
    ventanalibros.add(edi);
    ventanalibros.add(pag);
    ventanalibros.add(label5);
    ventanalibros.add(tem);
    ventanalibros.add(label6);
    ventanalibros.add(botonRegistro);
    
    label1.setBounds(25, 25, 100, 25);
    label2.setBounds(25, 75, 100, 25);
    label3.setBounds(25, 125, 100, 25);
    label4.setBounds(25, 175, 150, 25);
    label5.setBounds(25, 225, 100, 25);
    label6.setBounds(25, 275, 100, 25);
    ISBN.setBounds(175, 25, 200, 25);
    titulo.setBounds(175, 75, 200, 25);
    autor.setBounds(175, 125, 200, 25);
    edi.setBounds(175, 175, 200, 25);
    pag.setBounds(175, 225, 200, 25);
    tem.setBounds(175, 275, 200, 25);
    
    
    botonRegistro.setBounds(100, 325, 200, 25);
	ventanalibros.setVisible(true);
    
    botonRegistro.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        String tit=titulo.getText();
       	 String isb=ISBN.getText();
       	 String aut=autor.getText();
       	 String edit=edi.getText();
       	 String npag=pag.getText();
       	int numpag=0;
       	 String temat=tem.getText();
       	 
       	if(!npag.isEmpty()) {
	       	if (esEntero(npag)) {
	       	    numpag = Integer.parseInt(npag);
	       	    
	       	} else {
	       		JOptionPane.showMessageDialog(null, "El número de páginas no es un número entero válido");
	       	    return;
	       	}
       	}
       	if(!isb.isEmpty()) {
		       	if (validarISBN(isb)) {
		       	} else {
		       		JOptionPane.showMessageDialog(null, "El formato del ISBN no es válido");
		       	    return;
		       	}
       	}
       	try {				
				if (tipo.equals("mod")) {
					actualizar(oldisbn, tit, isb, aut, edit, numpag, temat);	
				}
				if (tipo.equals("consulta")) {
					consultas.consulta2(isb, tit, aut, edit, temat, numpag);					
				}
				if (!tipo.equals("mod")&&!tipo.equals("consulta")) {
					if (tit.isEmpty() || isb.isEmpty() || aut.isEmpty() || edit.isEmpty() || npag.isEmpty()) {
			       	    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
			       	    return; 
			       	}
					registrar(tit, isb, aut, edit, numpag, temat);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    });
}
public static void registrar(String tit, String isbn, String aut,String edit,int numpag,String temat) throws SQLException {
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String username = "root";
    String password = "1345";
    connectbd connector = new connectbd(url, username, password);
    Connection connection = null;
    try {
    	connection = connector.getConnection();
    	String sql = "INSERT INTO libros (titulo, isbn, autor, editorial, npag, tem) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, tit);
        statement.setString(2, isbn);
        statement.setString(3, aut);
        statement.setString(4, edit);
        statement.setInt(5, numpag);
        statement.setString(6, temat);

        int filasInsertadas = statement.executeUpdate();
        System.out.println("Se insertaron " + filasInsertadas + " registros");
        if (filasInsertadas>0) {
        	JOptionPane.showMessageDialog(null, "Registro completado");
        	ventanalibros.dispose();
        }
       
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	usuariocombinado.mostrar("libros");
        connector.closeConnection(connection);
    }
}
public static void eliminar(String isbn) throws SQLException{
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String username = "root";
    String password = "1345";
    connectbd connector = new connectbd(url, username, password);
    Connection connection = null;
    try {
    	connection = connector.getConnection();
    	String sql = "DELETE FROM libros WHERE isbn=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, isbn);
        int filasAfectadas = statement.executeUpdate();
        System.out.println("Se eliminaron " + filasAfectadas + " registros");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	usuariocombinado.mostrar("libros");
        connector.closeConnection(connection);
    }
}
public static void actualizar(String oldisbn, String tit, String isbn, String aut,String edit,int numpag,String temat) throws SQLException{
	String url = "jdbc:mysql://localhost:3306/biblioteca";
    String username = "root";
    String password = "1345";
    connectbd connector = new connectbd(url, username, password);
    Connection connection = null;
    try {
    	connection = connector.getConnection();
    	// Construir la sentencia SQL dinámicamente
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE libros SET ");
	    // Verificar y agregar los atributos a actualizar
        if (!isbn.isEmpty()) {
        	sqlBuilder.append("isbn = ?, ");
        }
        if (!tit.isEmpty()) {
            sqlBuilder.append("titulo = ?, ");
        }
        if (!aut.isEmpty()) {
            sqlBuilder.append("autor = ?, ");
        }
        if (!edit.isEmpty()) {
            sqlBuilder.append("editorial = ?, ");
        }
        if (numpag!=0) {
            sqlBuilder.append("npag = ?, ");
        }
        if (!temat.isEmpty()) {
            sqlBuilder.append("tem = ?, ");
        }
	
	    // Eliminar la última coma y completar la cláusula WHERE
	    sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
	    sqlBuilder.append(" WHERE isbn = ?");
	
	    String sql = sqlBuilder.toString();
	    PreparedStatement statement = connection.prepareStatement(sql);
	
	    // Asignar los nuevos valores a los campos correspondientes
	    int parameterIndex = 1;
	    if (!isbn.isEmpty()) {
            statement.setString(parameterIndex++, isbn);
        }
	    if (!tit.isEmpty()) {
            statement.setString(parameterIndex++, tit);
        }
        if (!aut.isEmpty()) {
            statement.setString(parameterIndex++, aut);
        }
        if (!edit.isEmpty()) {
        	statement.setString(parameterIndex++, edit);
        }
        if (numpag!=0) {
            statement.setInt(parameterIndex++, numpag);
        }
        if (!temat.isEmpty()) {
            statement.setString(parameterIndex++, temat);
        }
	    statement.setString(parameterIndex, oldisbn);
	
	    int registrosAfectados = statement.executeUpdate();
	    System.out.println("Registros afectados: " + registrosAfectados);
	    if (registrosAfectados>0) {
        	JOptionPane.showMessageDialog(null, "Modificación completada");
        	ventanalibros.dispose();
        }
	} catch (SQLException e) {
		e.printStackTrace();
    }finally {
    	usuariocombinado.mostrar("libros");
        connector.closeConnection(connection);
    }
}
public boolean esEntero(String valor) {
    try {
        Integer.parseInt(valor);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
public boolean validarISBN(String isbn) {
    // Eliminar los guiones del ISBN (si los hay)
    String isbnSinGuiones = isbn.replaceAll("-", "");

    // Verificar el formato del ISBN
    if (isbnSinGuiones.matches("\\d{9}[\\d|X]")) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbnSinGuiones.charAt(i));
            sum += (10 - i) * digit;
        }

        char lastChar = isbnSinGuiones.charAt(9);
        if (lastChar == 'X') {
            sum += 10;
        } else {
            int lastDigit = Character.getNumericValue(lastChar);
            sum += lastDigit;
        }

        return sum % 11 == 0;
    }

    return false;
}

public static void main(String[] args) {
	
}
}