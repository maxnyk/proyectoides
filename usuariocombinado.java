package proyectoides;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//De acuerdo con el metodo llamado se abre una interfaz u otra
public class usuariocombinado {
	private static JTable table;
    public static JScrollPane scrollPane;
	public static void Admin () {
		JFrame admin=new JFrame();
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.setTitle("Interfaz Administrador");
		admin.setSize(900, 400); 
	    JMenuBar barraMenu = new JMenuBar(); 
	    
	    JMenu menuUsuario = new JMenu("Usuario");
	    JMenuItem Alta = new JMenuItem("Alta");
	    JMenuItem Baja = new JMenuItem("Baja");
	    JMenuItem Mod = new JMenuItem("Modificación");
	    menuUsuario.add(Alta);
	    menuUsuario.add(Baja);
	    menuUsuario.add(Mod);
	
	    JMenu menuConsultas = new JMenu("Consultas");
	    JMenuItem ejecutar = new JMenuItem("Ejecutar");
	    menuConsultas.add(ejecutar);
	    
	    JMenu menuMod = new JMenu("Gestión de documentos");
	    JMenu libros = new JMenu("Libros");
	    JMenuItem altalib = new JMenuItem("Alta");
	    JMenuItem bajalib = new JMenuItem("Baja");
	    JMenuItem modlib = new JMenuItem("Modificación");
	    JMenu peliculas = new JMenu("Películas");
	    JMenuItem altadocs = new JMenuItem("Alta");
	    JMenuItem bajadocs = new JMenuItem("Baja");
	    JMenuItem moddocs = new JMenuItem("Modificación");
	    JMenu documentales = new JMenu("Documentales");
	    JMenuItem altapeli = new JMenuItem("Alta");
	    JMenuItem bajapeli = new JMenuItem("Baja");
	    JMenuItem modpeli = new JMenuItem("Modificación");
	    JMenu musica = new JMenu("Música");
	    JMenuItem altamus = new JMenuItem("Alta");
	    JMenuItem bajamus = new JMenuItem("Baja");
	    JMenuItem modmus = new JMenuItem("Modificación");
	    menuMod.add(libros);
	    libros.add(altalib);
	    libros.add(bajalib);
	    libros.add(modlib);
	    menuMod.add(peliculas);
	    peliculas.add(altapeli);
	    peliculas.add(bajapeli);
	    peliculas.add(modpeli);
	    menuMod.add(documentales);
	    documentales.add(altadocs);
	    documentales.add(bajadocs);
	    documentales.add(moddocs);
	    musica.add(altamus);
	    musica.add(bajamus);
	    musica.add(modmus);
	    menuMod.add(musica);
	    
	    barraMenu.add(menuUsuario);
	    barraMenu.add(menuConsultas);
	    barraMenu.add(menuMod);
	
	    admin.setJMenuBar(barraMenu);
	    
	    admin.setVisible(true);
	    
	    // Crear la tabla
        table = new JTable();

        // Crear el panel de desplazamiento y agregar la tabla
        scrollPane = new JScrollPane(table);
        
        admin.add(scrollPane);
        menuUsuario.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	
	        	try {
					mostrar("usuarios");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        public void menuDeselected(MenuEvent e) {
                
            }
            public void menuCanceled(MenuEvent e) {
               
            }
	    });
        libros.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	try {
	        		mostrar("libros");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        public void menuDeselected(MenuEvent e) {
                
            }
            public void menuCanceled(MenuEvent e) {
               
            }
	    });
        documentales.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	/*try {
					Documentales.mostrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
	        }
	        public void menuDeselected(MenuEvent e) {
                
            }
            public void menuCanceled(MenuEvent e) {
               
            }
	    });
	    
	    Alta.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	usuarios registroInt = new usuarios("admin","1");
	        	
	        }
	    });
	    Baja.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String user = JOptionPane.showInputDialog(null, "Ingrese el username del usuario a eliminar:");
	        	try {
					usuarios.eliminar(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    });
	    Mod.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		String user = JOptionPane.showInputDialog(null, "Ingrese el username del cliente a modificar:");
	        		if (user != null && !user.isEmpty()) {
	        			usuarios registroInt = new usuarios("mod",user);
	        		}
		         
	        }
	    });
	    ejecutar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	consultas consulta = new consultas("admin");
	        }
	    });
	    altalib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Libros intl = new Libros("default","0");
	        	
	        }
	    });
	    bajalib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String user = JOptionPane.showInputDialog(null, "Ingrese el isbn del libro a eliminar:");
	        	try {
					Libros.eliminar(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    });
	    modlib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		String user = JOptionPane.showInputDialog(null, "Ingrese el isbn del libro a modificar:");
	        		if (user != null && !user.isEmpty()) {
	        			Libros registroInt = new Libros("mod",user);
	        		}
		         
	        }
	    });
	    peliculas.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Peliculas intp = new Peliculas();
	        	intp.setVisible(true);
	        }
	    });
	    documentales.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Documentales intd = new Documentales();
	        	intd.setVisible(true);
	        }
	    });
	    musica.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Musica intm = new Musica();
	        	intm.setVisible(true);
	        }
	    });

}
	public static void Gestor() {
		JFrame gestor=new JFrame();
		gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gestor.setTitle("Interfaz Gestor");
		gestor.setSize(900, 400); 
	    JMenuBar barraMenu = new JMenuBar(); 
	
	    JMenu menuConsultas = new JMenu("Consultas");
	    JMenuItem ejecutar = new JMenuItem("Ejecutar");
	    menuConsultas.add(ejecutar);
	    
	    JMenu menuMod = new JMenu("Gestión de documentos");
	    JMenu libros = new JMenu("Libros");
	    JMenuItem altalib = new JMenuItem("Alta");
	    JMenuItem bajalib = new JMenuItem("Baja");
	    JMenuItem modlib = new JMenuItem("Modificación");
	    JMenu peliculas = new JMenu("Películas");
	    JMenuItem altadocs = new JMenuItem("Alta");
	    JMenuItem bajadocs = new JMenuItem("Baja");
	    JMenuItem moddocs = new JMenuItem("Modificación");
	    JMenu documentales = new JMenu("Documentales");
	    JMenuItem altapeli = new JMenuItem("Alta");
	    JMenuItem bajapeli = new JMenuItem("Baja");
	    JMenuItem modpeli = new JMenuItem("Modificación");
	    JMenu musica = new JMenu("Música");
	    JMenuItem altamus = new JMenuItem("Alta");
	    JMenuItem bajamus = new JMenuItem("Baja");
	    JMenuItem modmus = new JMenuItem("Modificación");
	    menuMod.add(libros);
	    libros.add(altalib);
	    libros.add(bajalib);
	    libros.add(modlib);
	    menuMod.add(peliculas);
	    peliculas.add(altapeli);
	    peliculas.add(bajapeli);
	    peliculas.add(modpeli);
	    menuMod.add(documentales);
	    documentales.add(altadocs);
	    documentales.add(bajadocs);
	    documentales.add(moddocs);
	    musica.add(altamus);
	    musica.add(bajamus);
	    musica.add(modmus);
	    menuMod.add(musica);
	    
	    barraMenu.add(menuConsultas);
	    barraMenu.add(menuMod);
	
	    gestor.setJMenuBar(barraMenu);
	    
	    gestor.setVisible(true);
	    
	    // Crear la tabla
        table = new JTable();

        // Crear el panel de desplazamiento y agregar la tabla
        scrollPane = new JScrollPane(table);
        
        gestor.add(scrollPane);
        
        libros.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	try {
	        		mostrar("libros");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        public void menuDeselected(MenuEvent e) {
                
            }
            public void menuCanceled(MenuEvent e) {
               
            }
	    });
        documentales.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	
	        }
	        public void menuDeselected(MenuEvent e) {
                
            }
            public void menuCanceled(MenuEvent e) {
               
            }
	    });
	    
	    ejecutar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	consultas consulta = new consultas("gestor");
	        }
	    });
	    altalib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Libros intl = new Libros("default","0");
	        	
	        }
	    });
	    bajalib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String user = JOptionPane.showInputDialog(null, "Ingrese el isbn del libro a eliminar:");
	        	try {
					Libros.eliminar(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    });
	    modlib.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		String user = JOptionPane.showInputDialog(null, "Ingrese el isbn del libro a modificar:");
	        		Libros registroInt = new Libros("mod",user);
		         
	        }
	    });
	    peliculas.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Peliculas intp = new Peliculas();
	        	intp.setVisible(true);
	        }
	    });
	    documentales.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Documentales intd = new Documentales();
	        	intd.setVisible(true);
	        }
	    });
	    musica.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Musica intm = new Musica();
	        	intm.setVisible(true);
	        }
	    });

    }
	public static void Socio() {
		JFrame socio=new JFrame();
		socio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		socio.setTitle("Interfaz Socio");
		socio.setSize(900, 400); 
        JMenuBar barraMenu = new JMenuBar(); 
        
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem ejecutar = new JMenuItem("Ejecutar");
        menuConsultas.add(ejecutar);
        
        JMenu reservas = new JMenu("Reservas");
        JMenuItem espera = new JMenuItem("Lista de espera");
        JMenuItem efectuar = new JMenuItem("Efectuar reserva");
        
        JMenu Historial = new JMenu("Historial");
        JMenuItem Consultar = new JMenuItem("Consultar");
      
        reservas.add(espera);
        reservas.add(efectuar);

        
        barraMenu.add(menuConsultas);
        barraMenu.add(reservas);
        barraMenu.add(Historial);
        
        Historial.add(Consultar);

        socio.setJMenuBar(barraMenu);
        
        socio.setVisible(true);
        
        ejecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        
        espera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        
        efectuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        
        Consultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame historial= new JFrame();
            	historial.setTitle("Historial");
            	historial.setSize(900,400);
            	historial.setVisible(true);
            	
            	JButton opinar=new JButton("Opinar");
            	opinar.setBounds(100, 275, 200, 25);
            	historial.add(opinar);
            	
            	opinar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	
                    }
                });
            }
        });
        
	}
	public static void mostrar(String tabla) throws SQLException {
    	String url = "jdbc:mysql://localhost:3306/biblioteca";
        String usuario = "root";
        String contraseña = "1345";
        connectbd connector = new connectbd(url, usuario, contraseña);
	    Connection connection = null;

        try {
            // Obtener metadatos de la consulta para construir el modelo de la tabla
        	connection = connector.getConnection();
        	String consultaSql = "SELECT * FROM "+tabla;
          
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