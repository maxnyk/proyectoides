package proyectoides;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Peliculas extends JFrame {
private String titulo;
private String director;
private int duracion;
private String actores;
private String premios;
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public int getDuracion() {
	return duracion;
}
public void setDuracion(int duracion) {
	this.duracion = duracion;
}
public String getActores() {
	return actores;
}
public void setActores(String actores) {
	this.actores = actores;
}
public String getPremios() {
	return premios;
}
public void setPremios(String premios) {
	this.premios = premios;
}
public Peliculas() {
	setTitle("Pelis");
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
    
    alta.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	JFrame ventanapelis = new JFrame();
            ventanapelis.setTitle("Interfaz pelis");
            ventanapelis.setSize(400, 350); 
            ventanapelis.setLayout(null);
            		
            JTextField titulo2 = new JTextField(20);
            JTextField dir = new JTextField(20);
            JTextField dur	 = new JTextField(20);
            JTextField actores = new JTextField(20);
            JTextField premios = new JTextField(20);
            
            JLabel label11=new JLabel("Título:");
            JLabel label22=new JLabel("Director:");
            JLabel label33=new JLabel("Duración:");
            JLabel label44=new JLabel("Actores:");
            JLabel label55=new JLabel("Premios:");
            
            JButton altapeli = new JButton("Confirmar");
            
            ventanapelis.add(label11);
            ventanapelis.add(titulo2);
            ventanapelis.add(label22);
            ventanapelis.add(dir);
            ventanapelis.add(label33);
            ventanapelis.add(dur);
            ventanapelis.add(label44);
            ventanapelis.add(actores);
            ventanapelis.add(premios);
            ventanapelis.add(label55);
            ventanapelis.add(altapeli);
       
            label11.setBounds(25, 25, 100, 25);
            label22.setBounds(25, 75, 100, 25);
            label33.setBounds(25, 125, 100, 25);
            label44.setBounds(25, 175, 150, 25);
            label55.setBounds(25, 225, 100, 25);
            titulo2.setBounds(175, 25, 200, 25);
            dir.setBounds(175, 75, 200, 25);
            dur.setBounds(175, 125, 200, 25);
            actores.setBounds(175, 175, 200, 25);
            premios.setBounds(175, 225, 200, 25);
            altapeli.setBounds(100, 275, 200, 25);
            
   
            ventanapelis.setVisible(true);
            
            altapeli.addActionListener(new ActionListener() {
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
	Peliculas interfaz = new Peliculas();
    interfaz.setVisible(true);
}
}