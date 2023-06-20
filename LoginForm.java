package proyectoides;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


public class LoginForm extends JFrame implements ActionListener{
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private static boolean cerrar;
    public LoginForm() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Nombre de usuario:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Iniciar sesión");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        
        

    }
    public static void authenticate(String username, String password) {
        // Verificar las credenciales en la base de datos y obtener el tipo de usuario
        String tipousu = getUserType(username, password);
        
        if (tipousu.equals("1")) {
        	// Ejecutar interfaz de Admin
            usuariocombinado.Admin();
            cerrar=true;
        } else if (tipousu.equals("3")) {
        	// Ejecutar interfaz de Socio
        	usuariocombinado.Socio();  
        	cerrar=true;
        } else if (tipousu.equals("2")) {
            // Ejecutar interfaz de Gestor
        	usuariocombinado.Gestor();
        	cerrar=true;
        } else {
            // Credenciales inválidas o tipo de usuario desconocido
        	JOptionPane.showMessageDialog(null, "Credenciales inválidas o tipo de usuario desconocido", "Error", JOptionPane.ERROR_MESSAGE);
        	cerrar=false;
        	
        }
    }
       public static String getUserType(String username, String password) {
    	   	String url = "jdbc:mysql://localhost:3306/biblioteca";
    	    String user = "root";
    	    String pass = "1345";
    	    connectbd connector = new connectbd(url, user, pass);
    	    Connection connection = null;
            try {
            	connection = connector.getConnection();
                String query = "SELECT codigogrupo FROM usuarios WHERE username = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("codigogrupo");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ""; // Credenciales inválidas o usuario no encontrado
       }
       public static void main(String[] args) {
    	   LoginForm loginForm = new LoginForm();
           loginForm.setVisible(true);
       }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            authenticate(username, password); 
            if (cerrar) {
            dispose();
            }
        }
	}

        
    }

    


