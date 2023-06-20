package proyectoides;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Base de datos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(900, 400);
        ventana.setLayout(new FlowLayout());

        JPanel panel = new JPanel();

        JLabel etiquetaNombre = new JLabel("Entrar a la base de datos:");

   
        JButton botonRegistro= new JButton("Registrarse");
        JButton botonaut= new JButton("Autentificar");
        JButton botoninvi= new JButton("Invittado");
        

        
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	usuarios registroInt = new usuarios("cliente","1");
            
            }
        });
        botonaut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LoginForm log = new LoginForm();
            	log.setVisible(true);
                
            }
        });
        botoninvi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultas invitado = new consultas("invitado");
                
            }
        });

        panel.add(etiquetaNombre);
       
      
        
        panel.add(botonaut);
        panel.add(botonRegistro);
        panel.add(botoninvi);
        
        ventana.add(panel);

        ventana.setVisible(true);       
    }
}