package proyectoides;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class usuarios  {
	private static JTable table;
    public static JScrollPane scrollPane;
	private static boolean invocartabla=false;
	private static JFrame frame;
    public usuarios(String tipo,String olduser) {
    	frame = new JFrame("Registro");
    	frame.setSize(400, 550); 
    	frame.setLayout(null);
    	frame.setVisible(true);
        		
        JTextField Nombre = new JTextField(20);
        JTextField Apellidos = new JTextField(20);
        JTextField DNI = new JTextField(20);
        JTextField Fechadenacimiento = new JTextField(20);
        JTextField EMAIL = new JTextField(20);
        JTextField usuario = new JTextField(20);
        JTextField contra = new JTextField(20);
        JTextField codgrup = new JTextField(20);
        
        JLabel label1=new JLabel("Nombre:");
        JLabel label2=new JLabel("Apellidos:");
        JLabel label3=new JLabel("DNI:");
        JLabel label4=new JLabel("Fecha de nacimiento:");
        JLabel label5=new JLabel("Email");
        JLabel label6=new JLabel("Usuario:");
        JLabel label7=new JLabel("Contraseña:");
        JLabel label8=new JLabel("Código del grupo:");
        
        JButton botonRegistro = new JButton("Registrarse");
        
        frame.add(label1);
        frame.add(Nombre);
        frame.add(label2);
        frame.add(Apellidos);
        frame.add(label3);
        frame.add(DNI);
        frame.add(label4);
        frame.add(Fechadenacimiento);
        frame.add(label5);
        frame.add(EMAIL);
        frame.add(label6);
        frame.add(usuario);
        frame.add(label7);
        frame.add(contra);
        frame.add(botonRegistro);
     
        
        
        
        if(tipo.equals("admin")) {
        	frame.add(codgrup);
        	frame.add(label8);
        	label8.setBounds(25, 375, 100, 25);
        	codgrup.setBounds(175, 375, 200, 25);
        	frame.add(botonRegistro);
        	botonRegistro.setBounds(100, 425, 200, 25);
        	botonRegistro.setText("Registrar");
            
        }
        else if(tipo.equals("mod")) {
        	frame.setTitle("Modificación");
        	frame.add(codgrup);
        	frame.add(label8);
        	label8.setBounds(25, 375, 100, 25);
        	codgrup.setBounds(175, 375, 200, 25);
        	frame.add(botonRegistro);
        	botonRegistro.setBounds(100, 425, 200, 25);
        	botonRegistro.setText("Modificar");
            
        }
        else if(tipo.equals("consulta")) {
        	frame.setTitle("Consulta");
        	frame.add(codgrup);
        	frame.add(label8);
        	label8.setBounds(25, 375, 100, 25);
        	codgrup.setBounds(175, 375, 200, 25);
        	frame.add(botonRegistro);
        	botonRegistro.setBounds(100, 425, 200, 25);
        	botonRegistro.setText("Buscar");
            
        }
        else {
        	frame.setTitle("Registro");
        	botonRegistro.setBounds(100, 375, 200, 25);
        }
        
        label1.setBounds(25, 25, 100, 25);
        label2.setBounds(25, 75, 100, 25);
        label3.setBounds(25, 125, 100, 25);
        label4.setBounds(25, 175, 150, 25);
        label5.setBounds(25, 225, 100, 25);
        label6.setBounds(25, 275, 100, 25);
        label7.setBounds(25, 325, 100, 25);
        Nombre.setBounds(175, 25, 200, 25);
        Apellidos.setBounds(175, 75, 200, 25);
        DNI.setBounds(175, 125, 200, 25);
        Fechadenacimiento.setBounds(175, 175, 200, 25);
        EMAIL.setBounds(175, 225, 200, 25);
        usuario.setBounds(175, 275, 200, 25);
        contra.setBounds(175, 325, 200, 25);
       
        
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String pass=contra.getText();
            	 String nombre=Nombre.getText();
            	 String apellidos=Apellidos.getText();
            	 String dni=DNI.getText();
            	 String fn=Fechadenacimiento.getText();
            	 String email=EMAIL.getText();
            	 String user=usuario.getText();
            	 String cgr=codgrup.getText();
            	 int codgr=3;
            	 int codgrmod=0;
            	 if (tipo.equals("admin")) {   
            		 if (!cgr.isEmpty()) {
            		 codgr = Integer.parseInt(cgr);
            		 }
            		 invocartabla=true;
            	 }
            	 if (tipo.equals("mod")) {
            		 cgr=codgrup.getText();
            		 if (!cgr.isEmpty()) {
            		 codgrmod = Integer.parseInt(cgr);  
            		 }
            	 }	 
            	 if (tipo.equals("consulta")) {
            		 cgr=codgrup.getText();
            		 if (!cgr.isEmpty()) {
            		 codgrmod = Integer.parseInt(cgr);  
            		 }
            	 }	 
            	// Verificar si el username ya existe en la base de datos
            	 if (existeUsername(user)&&!tipo.equals("consulta")) {
            	     JOptionPane.showMessageDialog(null, "El username ya está registrado");
            	     return; // Detener el proceso de registro
            	 }

            	 // Verificar si el email ya está registrado en la base de datos
            	 if (existeEmail(email)&&!tipo.equals("consulta")) {
            	     JOptionPane.showMessageDialog(null, "El email ya está registrado");
            	     return; // Detener el proceso de registro
            	 }
            	try {
					
					if (tipo.equals("mod")) {
						actualizar(olduser, user, pass, nombre, apellidos, dni, fn, email, codgrmod);
					}
					if (tipo.equals("consulta")) {
						consultas.consulta(user, pass, nombre, apellidos, dni, fn, email, codgrmod);
					}
					else {
						if (user.isEmpty() || pass.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || fn.isEmpty() || email.isEmpty()) {
				       	    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
				       	    return; // Detener el proceso de registro
				       	}
					
						String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
						if (!email.matches(emailRegex)) {
						    JOptionPane.showMessageDialog(null, "Formato de email inválido");
						    return; // Detener el proceso de registro
						}

						registrar(user, pass, nombre, apellidos, dni, fn, email, codgr);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	
            }
        });
       

    }
    public static void registrar(String user, String pass, String nombre, String apellidos, String dni, String fn, String email, int codgrupo) throws SQLException {
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "INSERT INTO usuarios (username, email, password, nombre, apellidos, dni, fechanacimiento, codigogrupo) "
	        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        statement.setString(1, user);
	        statement.setString(2, email);
	        statement.setString(3, pass);
	        statement.setString(4, nombre);
	        statement.setString(5, apellidos);
	        statement.setString(6, dni);
	        String dateString = fn;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
	        statement.setDate(7, new java.sql.Date(date.getTime()));
	        statement.setInt(8, codgrupo);

	        int filasInsertadas = statement.executeUpdate();
	        System.out.println("Se insertaron " + filasInsertadas + " registros");
	        if (filasInsertadas>0) {
	        	JOptionPane.showMessageDialog(null, "Registro completado");
	        	frame.dispose();
	        }
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
            // Capturar excepciones relacionadas con el formato de fecha incorrecto
	    	JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto, use el formato yyyy-MM-dd");
	    } finally {
	    	if(invocartabla==true) {
	    	usuariocombinado.mostrar("usuarios");
	    	}
	        connector.closeConnection(connection);
	    }
    }
    public static void eliminar(String user) throws SQLException{
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "DELETE FROM usuarios WHERE username=?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, user);
	        int filasAfectadas = statement.executeUpdate();
	        System.out.println("Se eliminaron " + filasAfectadas + " registros");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	usuariocombinado.mostrar("usuarios");
	        connector.closeConnection(connection);
	    }
    }
    public static void actualizar(String olduser,String user, String pass, String nombre, String apellidos, String dni, String fn, String email, int codgrupo) throws SQLException{
        // Construir la sentencia SQL dinámicamente
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	// Construir la sentencia SQL dinámicamente
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("UPDATE usuarios SET ");
		    // Verificar y agregar los atributos a actualizar
            if (!user.isEmpty()) {
            	sqlBuilder.append("username = ?, ");
	        }
            if (!email.isEmpty()) {
                sqlBuilder.append("email = ?, ");
            }
            if (!pass.isEmpty()) {
                sqlBuilder.append("password = ?, ");
            }
            if (!nombre.isEmpty()) {
                sqlBuilder.append("nombre = ?, ");
            }
            if (!apellidos.isEmpty()) {
                sqlBuilder.append("apellidos = ?, ");
            }
            if (!dni.isEmpty()) {
                sqlBuilder.append("dni = ?, ");
            }
            if (!fn.isEmpty()) {
                sqlBuilder.append("fechanacimiento = ?, ");
            }
            if (codgrupo != 0) {
                sqlBuilder.append("codigogrupo = ?, ");
            }
		
		    // Eliminar la última coma y completar la cláusula WHERE
		    sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
		    sqlBuilder.append(" WHERE username = ?");
		
		    String sql = sqlBuilder.toString();
		    PreparedStatement statement = connection.prepareStatement(sql);
		
		    // Asignar los nuevos valores a los campos correspondientes
		    int parameterIndex = 1;
		    if (!user.isEmpty()) {
	            statement.setString(parameterIndex++, user);
	        }
		    if (!email.isEmpty()) {
	            statement.setString(parameterIndex++, email);
	        }
	        if (!pass.isEmpty()) {
	            statement.setString(parameterIndex++, pass);
	        }
	        if (!nombre.isEmpty()) {
	            statement.setString(parameterIndex++, nombre);
	        }
	        if (!apellidos.isEmpty()) {
	            statement.setString(parameterIndex++, apellidos);
	        }
	        if (!dni.isEmpty()) {
	            statement.setString(parameterIndex++, dni);
	        }
	        if (!fn.isEmpty()) {
	            String dateString = fn;
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = dateFormat.parse(dateString);
	            statement.setDate(parameterIndex++, new java.sql.Date(date.getTime()));
	        }	
		    if (codgrupo != 0) {
		        statement.setInt(parameterIndex++, codgrupo);
		    }
		    statement.setString(parameterIndex, olduser);
		
		    int registrosAfectados = statement.executeUpdate();
		    System.out.println("Registros afectados: " + registrosAfectados);
		    if (registrosAfectados>0) {
	        	JOptionPane.showMessageDialog(null, "Modificación completada");
	        	frame.dispose();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
        }catch (ParseException e) {
        // Capturar excepciones relacionadas con el formato de fecha incorrecto
    	JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto, use el formato yyyy-MM-dd");
        }finally {
	    	usuariocombinado.mostrar("usuarios");
	        connector.closeConnection(connection);
	    }
    }
    /*public static void consulta(String user, String pass, String nombre, String apellidos, String dni, String fn, String email, int codgrupo) throws SQLException{
        // Construir la sentencia SQL dinámicamente
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    try {
	    	connection = connector.getConnection();
	    	// Construir la sentencia SQL dinámicamente
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT * from usuarios WHERE");
		    // Verificar y agregar los atributos a buscar
            if (!user.isEmpty()) {
            	sqlBuilder.append("username = ? and ");
	        }
            if (!email.isEmpty()) {
                sqlBuilder.append("email = ? and ");
            }
            if (!pass.isEmpty()) {
                sqlBuilder.append("password = ? and ");
            }
            if (!nombre.isEmpty()) {
                sqlBuilder.append("nombre = ? and ");
            }
            if (!apellidos.isEmpty()) {
                sqlBuilder.append("apellidos = ? and ");
            }
            if (!dni.isEmpty()) {
                sqlBuilder.append("dni = ? and ");
            }
            if (!fn.isEmpty()) {
                sqlBuilder.append("fechanacimiento = ? and ");
            }
            if (codgrupo != 0) {
                sqlBuilder.append("codigogrupo = ? and ");
            }
            sqlBuilder.delete(sqlBuilder.length() - 5, sqlBuilder.length());
		
		    String sql = sqlBuilder.toString();
		    PreparedStatement statement = connection.prepareStatement(sql);	
		    int parameterIndex = 1;
		    if (!user.isEmpty()) {
	            statement.setString(parameterIndex++, user);
	        }
		    if (!email.isEmpty()) {
	            statement.setString(parameterIndex++, email);
	        }
	        if (!pass.isEmpty()) {
	            statement.setString(parameterIndex++, pass);
	        }
	        if (!nombre.isEmpty()) {
	            statement.setString(parameterIndex++, nombre);
	        }
	        if (!apellidos.isEmpty()) {
	            statement.setString(parameterIndex++, apellidos);
	        }
	        if (!dni.isEmpty()) {
	            statement.setString(parameterIndex++, dni);
	        }
	        if (!fn.isEmpty()) {
	            String dateString = fn;
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = dateFormat.parse(dateString);
	            statement.setDate(parameterIndex++, new java.sql.Date(date.getTime()));
	        }	
		    if (codgrupo != 0) {
		        statement.setInt(parameterIndex++, codgrupo);
		    }		    
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
		}catch (ParseException e) {
	        // Capturar excepciones relacionadas con el formato de fecha incorrecto
	    	JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto, use el formato yyyy-MM-dd");
        }finally {
	        connector.closeConnection(connection);
	    }
    }*/
    public static boolean existeUsername(String user) {
    	boolean existe = false;
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "SELECT COUNT(username) FROM usuarios WHERE username=?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, user);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            existe = (count > 0); // Si count > 0, el username existe en la base de datos
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        connector.closeConnection(connection);
	    }
	    return existe;	    
    }
    public static boolean existeEmail(String email) {
    	boolean existe = false;
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String username = "root";
        String password = "1345";
	    connectbd connector = new connectbd(url, username, password);
	    Connection connection = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = connector.getConnection();
	    	String sql = "SELECT COUNT(email) FROM usuarios WHERE email=?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, email);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            existe = (count > 0); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        connector.closeConnection(connection);
	    }
	    return existe;	    
    }
    public static void main(String[] args) {
    	
    }
}