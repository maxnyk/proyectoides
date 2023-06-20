package proyectoides;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Musica extends JFrame {
private String titulo;
private String autor;
private int duracion;
private String conciertos;
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
public int getDuracion() {
	return duracion;
}
public void setDuracion(int duracion) {
	this.duracion = duracion;
}
public String getConciertos() {
	return conciertos;
}
public void setConciertos(String conciertos) {
	this.conciertos = conciertos;
}
public Musica() {
	setTitle("Música");
    setSize(500, 200); 
    setLayout(new FlowLayout());
    
    JPanel panel = new JPanel();

    JLabel Tipo = new JLabel("Acción a realizar:");

    JButton alta = new JButton("Alta");
    JButton baja = new JButton("Baja");
    JButton  mod = new JButton("Modificación");
    
    add(panel);
    
    panel.add(Tipo);
    
    panel.add(alta);
    panel.add(baja);
    panel.add(mod);
    //Al darle al boton de alta se abre otra ventana
    alta.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	JFrame ventanamus = new JFrame();
            ventanamus.setTitle("Interfaz música");
            ventanamus.setSize(400, 300); 
            ventanamus.setLayout(null);
            		
            JTextField titulo4 = new JTextField(20);
            JTextField autor2 = new JTextField(20);
            JTextField dur3	 = new JTextField(20);

            
            JLabel label1111=new JLabel("Título:");
            JLabel label2222=new JLabel("Autor:");
            JLabel label3333=new JLabel("Duración:");
            JLabel label4444=new JLabel("Conciertos:");
            
            JButton altamus = new JButton("Confirmar");
            JButton conciertos = new JButton("Añadir");
            
            ventanamus.add(label1111);
            ventanamus.add(titulo4);
            ventanamus.add(label2222);
            ventanamus.add(autor2);
            ventanamus.add(label3333);
            ventanamus.add(dur3);
            ventanamus.add(label4444);
            ventanamus.add(altamus);
            ventanamus.add(conciertos);
      
       
            label1111.setBounds(25, 25, 100, 25);
            label2222.setBounds(25, 75, 100, 25);
            label3333.setBounds(25, 125, 100, 25);
            titulo4.setBounds(175, 25, 200, 25);
            autor2.setBounds(175, 75, 200, 25);
            dur3.setBounds(175, 125, 200, 25);
            altamus.setBounds(100, 225, 200, 25);
            label4444.setBounds(25, 175, 100, 25);
            conciertos.setBounds(175, 175, 200, 25);
            
            ventanamus.setVisible(true);
            //Al darle al boton de añadir se pueden añadir los conciertos con sus respectivos datos
            conciertos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	JFrame conciertos = new JFrame();
                	conciertos.setTitle("Conciertos");
                	conciertos.setSize(400, 200);
                	conciertos.setLayout(null);
                	
                	JTextField lugar= new JTextField(20);
                	JLabel label1= new JLabel("Lugar:");
                	JTextField fecha= new JTextField(20);
                	JLabel label2= new JLabel("Fecha:");
                	JButton añadir = new JButton("Añadir");
                	
                	label1.setBounds(25, 25, 100, 25);
                    label2.setBounds(25, 75, 100, 25);
                    lugar.setBounds(175, 25, 200, 25);
                    fecha.setBounds(175, 75, 200, 25);
                    añadir.setBounds(100, 125, 200, 25);
                    
                	conciertos.add(label1);
                	conciertos.add(label2);
                	conciertos.add(lugar);
                	conciertos.add(fecha);
                	conciertos.add(añadir);
                	
                	conciertos.setVisible(true);
                	añadir.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        
                        }
                    });
                }
            });
            altamus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                }
            });
           
          
        }
        
    });
    baja.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
        }
    });
    mod.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
        }
    });
}
public static void main(String[] args) {
	Musica interfaz = new Musica();
	interfaz.setVisible(true);
}
}

