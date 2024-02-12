package cuatroRayaCliente.cuatroRayaCliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ClienteScreen {
    String user;
    String pass;
	Escribir escribe;
    Leer lee;
    static String HOST = "localhost";
    static int Puerto = 2069;
	static Socket canal;
	JFrame frame;
    JPanel cardPanel;
    CardLayout ventanas;
    JPanel panelActual;
    int tamaño;
    String[] datos=new String[1];
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteScreen window = new ClienteScreen();
					window.frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClienteScreen() {
		datos[0]="1";
		initialize();
	}
	
	/**
	 * Create my personal panel
	 */
	private class FondoPanel extends JPanel {
        private Image imagenFondo;

        public FondoPanel(String rutaImagen) {
            this.imagenFondo = new ImageIcon(rutaImagen).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            canal = new Socket(HOST, Puerto);
            escribe = new Escribir(HOST, Puerto, canal);
            lee = new Leer(HOST, Puerto, canal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventanas = new CardLayout();
        cardPanel = new JPanel(ventanas);
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);

        cardPanel.add(servidorPanel(), "servidor");
        cardPanel.add(signUpPanel(""), "signUpB");
        cardPanel.add(signUpPanel("Error en contraseña o usuario"), "signUpM");
        cardPanel.add(signInPanel(""), "signInB");
        cardPanel.add(signInPanel("Usuario ya existente o contraseña vacia"), "signInM");
        cardPanel.add(menuPanel(), "menu");
        cardPanel.add(jugadoresPanel(), "buscar");
        cardPanel.add(jugarPanel(), "jugar");
        cardPanel.add(partidasPanel(), "continuar");
        cardPanel.add(historialPanel(), "historial");
        cardPanel.add(visualizarPanel(), "visualizar");
        cardPanel.add(configPanel(""), "configB");
        cardPanel.add(configPanel("Usuario ya existente o contraseña vacia"), "configM");

        ventanas.show(cardPanel, "servidor"); // Mostrar el panel del servidor al inicio
    }
	
	private JPanel servidorPanel() {
        FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
        fondo.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblNewLabel = new JLabel("Servidor");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        fondo.add(panel_1);

        JLabel lblNewLabel_1 = new JLabel("Host");
        panel_1.add(lblNewLabel_1);

        JTextField host = new JTextField("localhost");
        panel_1.add(host);
        host.setColumns(10);

        JPanel panel_2 = new JPanel();
        fondo.add(panel_2);

        JLabel lblNewLabel_2 = new JLabel("Puerto");
        panel_2.add(lblNewLabel_2);

        JTextField puerto = new JTextField("2069");
        panel_2.add(puerto);
        puerto.setColumns(10);

        JPanel panel_3 = new JPanel();
        fondo.add(panel_3);

        JButton btnNewButton = new JButton("Confirmar");
        panel_3.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conecta(host.getText(), Integer.parseInt(puerto.getText()));
            }
        });
        return fondo;
    }

    private void conecta(String host, int puerto) {
        HOST = host;
        Puerto = puerto;
        try {
            canal = new Socket(HOST, Puerto);
            System.out.println("Conexion establecida " + host + " " + puerto);
            ventanas.show(cardPanel, "signUpB"); // Cambiar al panel de menú después de la conexión exitosa
        } catch (Exception ex) {
            System.err.println("No se ha podido establecer conexión.");
            System.err.println(ex.toString());
            ventanas.show(cardPanel, "servidor"); // Permanecer en el panel de servidor si la conexión falla
        }
    }
	
	private JPanel signUpPanel(String intento) {
		FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
		fondo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Accede a tu cuenta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.add(lblNewLabel);
		
		var panel_1 = new JPanel();
		fondo.add(panel_1);
		
		var lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(lblNewLabel_1);
		
		var usuario = new JTextField();
		panel_1.add(usuario);
		usuario.setColumns(10);
		
		var panel_2 = new JPanel();
		fondo.add(panel_2);
		
		var lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(lblNewLabel_2);
		
		var contraseña = new JTextField();
		contraseña.setColumns(10);
		panel_2.add(contraseña);
		
		var panel_3 = new JPanel();
		fondo.add(panel_3);
		
		var btnNewButton = new JButton("Sign In");//nuevo usuario
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "signInB");
            }
        });
		panel_3.add(btnNewButton);
		
		var btnNewButton_1 = new JButton("Sign Up");//ya tiene cuenta
		panel_3.add(btnNewButton_1);
		
		AtomicBoolean correcto=new AtomicBoolean(false);
		JLabel incorrecto = new JLabel(intento);
		incorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.add(incorrecto);
		
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//comprobar si tiene cuenta
            	escribe.signUp(usuario.getText(), contraseña.getText());
            	correcto.set(lee.confirma());
            	if (!correcto.get()) {
                    ventanas.show(cardPanel, "signUpM");
                }else {
                	user=usuario.getText();
                	pass=contraseña.getText();
                	ventanas.show(cardPanel, "menu");
                	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });
		return fondo;
	}
	
	private JPanel signInPanel(String intento) {
		FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
        fondo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Crea tu cuenta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.add(lblNewLabel);
		
		var panel_1 = new JPanel();
		fondo.add(panel_1);
		
		var lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(lblNewLabel_1);
		
		var usuario = new JTextField();
		panel_1.add(usuario);
		usuario.setColumns(10);
		
		var panel_2 = new JPanel();
		fondo.add(panel_2);
		
		var lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(lblNewLabel_2);
		
		var contraseña = new JTextField();
		contraseña.setColumns(10);
		panel_2.add(contraseña);
		
		var panel_3 = new JPanel();
		fondo.add(panel_3);
		
		var atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "signUpB");
            }
        });
		panel_3.add(atras);
		
		var btnNewButton = new JButton("Confirmar");
		panel_3.add(btnNewButton);
		
		AtomicBoolean correcto=new AtomicBoolean(false);
		JLabel incorrecto = new JLabel(intento);
		incorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.add(incorrecto);
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//comprobar si tiene cuenta
            	escribe.signIn(usuario.getText(), contraseña.getText());
            	correcto.set(lee.confirma());
            	if (!correcto.get()) {
                    ventanas.show(cardPanel, "signInM");
                }else {
                	ventanas.show(cardPanel, "signUpB");
                }
            }
        });
		
		JLabel enUso = new JLabel("");
		enUso.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.add(enUso);
		
		return fondo;
    }
	
	private JPanel menuPanel() {
		FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo2.jpg");
		fondo.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel panel = new JPanel();
        fondo.add(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
        
        Component horizontalGlue = Box.createHorizontalGlue();
        panel_1.add(horizontalGlue);
        
        JButton buscar = new JButton("Buscar");
        panel_1.add(buscar);
        
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "buscar");
            }
        });
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        panel_1.add(horizontalGlue_1);
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        panel_2.add(horizontalGlue_2);
        
        JButton Continuar = new JButton("Continuar");
        panel_2.add(Continuar);
        
        Continuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "continuar");
            }
        });
        
        Component horizontalGlue_3 = Box.createHorizontalGlue();
        panel_2.add(horizontalGlue_3);
        
        JPanel panel_3 = new JPanel();
        panel.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
        
        Component horizontalGlue_4 = Box.createHorizontalGlue();
        panel_3.add(horizontalGlue_4);
        
        JButton historial = new JButton("Historial");
        panel_3.add(historial);
        
        Component horizontalGlue_5 = Box.createHorizontalGlue();
        panel_3.add(horizontalGlue_5);
        
        historial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "historial");
            }
        });
        
        JPanel panel_4 = new JPanel();
        panel.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        Component horizontalGlue_6 = Box.createHorizontalGlue();
        panel_4.add(horizontalGlue_6);
        
        JButton configuracion = new JButton("Configuracion");
        panel_4.add(configuracion);
        
        Component horizontalGlue_7 = Box.createHorizontalGlue();
        panel_4.add(horizontalGlue_7);
        
        configuracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "configB");
            }
        });
        
        FondoPanel imagen = new FondoPanel("src/main/resources/images/fondo2.jpg");
        fondo.add(imagen);
        
        return fondo;
    }
	
	private JPanel jugadoresPanel() {
	    FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
	    fondo.setLayout(new BorderLayout());

	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    menuBar.add(salir);
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ventanas.show(cardPanel, "menu");
	        }
	    });

	    JButton refrescar = new JButton("Refrescar");
	    menuBar.add(refrescar);

	    List<String> usuarios = new ArrayList<>();
	    escribe.getActiveUsers();
	    usuarios.addAll(Arrays.asList(lee.users()));

	    JPanel usuariosPanel = new JPanel();
	    usuariosPanel.setLayout(new BoxLayout(usuariosPanel, BoxLayout.Y_AXIS));
	    updateUsuariosPanel(usuarios, usuariosPanel);

	    JScrollPane scrollPane = new JScrollPane(usuariosPanel);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    fondo.add(scrollPane, BorderLayout.CENTER);

	    refrescar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            escribe.getActiveUsers();
	            usuarios.clear();
	            usuarios.addAll(Arrays.asList(lee.users()));
	            updateUsuariosPanel(usuarios, usuariosPanel);
	            ventanas.show(cardPanel, "buscar");
	        }
	    });

	    return fondo;
	}

	private void updateUsuariosPanel(List<String> usuarios, JPanel usuariosPanel) {
	    usuariosPanel.removeAll();

	    if (!usuarios.isEmpty() && !usuarios.get(0).equals("null")) {
	        for (String user : usuarios) {
	            JPanel panel = new JPanel();

	            JLabel lblNewLabel = new JLabel(user);
	            panel.add(lblNewLabel);

	            JButton desafiar = new JButton("desafiar");
	            desafiar.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	            ventanas.show(cardPanel, "jugar");
	    	            escribe.desafiar(user);
	    	            tamaño = lee.tamaño();    
	    	        }
	    	    });
	            panel.add(desafiar);

	            usuariosPanel.add(panel);
	        }
	    } else {
	        JPanel panel = new JPanel();

	        JLabel lblNewLabel = new JLabel("En estos momentos no hay jugadores");
	        panel.add(lblNewLabel);

	        usuariosPanel.add(panel);
	    }

	    usuariosPanel.revalidate();
	    usuariosPanel.repaint();
	}
	
	private JPanel jugarPanel() {
	    FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
	    fondo.setLayout(new BorderLayout());
	    
	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ventanas.show(cardPanel, "menu");
	        }
	    });
	    menuBar.add(salir);

	    JButton btnRendirse = new JButton("Rendirse");
	    btnRendirse.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            escribe.dimitir();
	        }
	    });
	    menuBar.add(btnRendirse);

	    JButton btnRefrescar = new JButton("Refrescar");
	    menuBar.add(btnRefrescar);

	    JPanel contenedor = new JPanel();
	    fondo.add(contenedor);
	    contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));

	    Component horizontalStrut = Box.createHorizontalStrut((int) (frame.getWidth() * 0.05));
	    contenedor.add(horizontalStrut);
	//------------------------------------------------Tablero
	    JPanel tableroPanel = new JPanel();
	    tableroPanel.setLayout(new GridLayout(1, tamaño)); // Grid horizontal
	    JPanel[][] matrizPaneles = new JPanel[tamaño][tamaño];
	    System.out.println("El tamaño de la matriz es: "+matrizPaneles.length);
	    contenedor.add(tableroPanel);
	//------------------------------------------------
	    Component horizontalStrut_1 = Box.createHorizontalStrut(22);
	    contenedor.add(horizontalStrut_1);

	    // Llama a la función updateTablero inicialmente para mostrar el tablero vacío
	    updateTablero(tableroPanel, matrizPaneles);

	    btnRefrescar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Llama a la función updateTablero para actualizar el tablero con las jugadas
	            updateTablero(tableroPanel, matrizPaneles);
	        }
	    });

	    return fondo;
	}

	private void updateTablero(JPanel tablero, JPanel[][] matrizPaneles) {
	    escribe.refrescarPartida();
	    String[] turnos = lee.cadenas(); // usuario&columna&fila
	    matrizPaneles = new JPanel[tamaño][tamaño];
	    // Limpiar el tablero antes de actualizarlo
	    tablero.removeAll();

	    for (int i = 0; i < tamaño; i++) {
	        JPanel columna = new JPanel();
	        columna.setLayout(new GridLayout(tamaño, 1)); // Grid vertical
	        for (int j = 0; j < tamaño; j++) {
	            JPanel panel = new JPanel();
	            panel.setBackground(Color.WHITE); // Panel blanco por defecto
	            panel.addMouseListener(new PanelClickListener(i, j, matrizPaneles, tamaño));
	            matrizPaneles[i][j] = panel;
	            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	            columna.add(panel);
	        }
	        tablero.add(columna);
	    }

	    // Si hay turnos, actualizar el tablero con las jugadas
	    if (!turnos[0].equals("null")) {
	        for (String jugada : turnos) {
	            String[] infoJugada = jugada.split("&");
	            int columna = Integer.parseInt(infoJugada[1]);
	            int fila = Integer.parseInt(infoJugada[2]);
	            String jugador = infoJugada[0];

	            // Actualizar el estado de la casilla en el tablero
	            JPanel panel = new JPanel();
	            if (jugador.equals(this.user)) {
	                panel.setBackground(Color.RED);
	            } else {
	                panel.setBackground(Color.YELLOW);
	            }
	            panel.addMouseListener(new PanelClickListener(fila, columna, matrizPaneles, tamaño));
	            matrizPaneles[fila][columna] = panel;
	        }
	    }

	    // Volver a validar y repintar el contenedor
	    tablero.revalidate();
	    tablero.repaint();
	}

    // Clase interna para manejar clics en los paneles
    private class PanelClickListener extends MouseAdapter {
        private int columna;
        private int fila;
        private JPanel[][]matrizPaneles;
        private int tamaño;

        public PanelClickListener(int columna, int fila, JPanel[][]matrizPaneles, int tamaño) {
            this.columna = columna;
            this.fila = fila;
            this.matrizPaneles=matrizPaneles;
            this.tamaño=tamaño;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            cambiarColorPanel();
        }

        private void cambiarColorPanel() {
        	Boolean ocupado=true;
            for (int i = tamaño - 1; i >= 0; i--) {
                if (matrizPaneles[columna][i].getBackground().equals(Color.WHITE)) {//Si ve que esta blanco le pide al server que coloque
                	escribe.turno(columna, i);
                	ocupado=lee.confirma();
                	
                	if(!ocupado) {
                		matrizPaneles[columna][i].setBackground(Color.RED);// Pedira al server un true o false si le regresa true cambia
                	}
                    break;
                }
            }
        }
    }
	
	private JPanel partidasPanel() {
	    FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
	    fondo.setLayout(new BorderLayout());

	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    menuBar.add(salir);
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ventanas.show(cardPanel, "menu");
	        }
	    });

	    JButton refrescar = new JButton("Refrescar");
	    menuBar.add(refrescar);

	    List<String> partidas = new ArrayList<>();
	    escribe.getPartidas();
	    partidas.addAll(Arrays.asList(lee.cadenas()));

	    // Crear un panel contenedor para los paneles de desplazamiento
	    JPanel panelContenedor = new JPanel(new GridLayout(1, 2));

	    // Panel y JScrollPane para la primera sección de partidas
	    JPanel colocarPanel = new JPanel();
	    colocarPanel.setLayout(new BoxLayout(colocarPanel, BoxLayout.Y_AXIS));
	    JScrollPane scrollColocarPane = new JScrollPane(colocarPanel);
	    scrollColocarPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panelContenedor.add(scrollColocarPane);
	    

	    // Panel y JScrollPane para la segunda sección de partidas
	    JPanel esperarPanel = new JPanel();
	    esperarPanel.setLayout(new BoxLayout(esperarPanel, BoxLayout.Y_AXIS));
	    JScrollPane scrollEsperarPane = new JScrollPane(esperarPanel);
	    scrollEsperarPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panelContenedor.add(scrollEsperarPane);
	    

	    fondo.add(panelContenedor, BorderLayout.CENTER);

	    updatePartidasPanel(partidas, colocarPanel, esperarPanel);

	    refrescar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            escribe.getPartidas();
	            partidas.clear();
	            partidas.addAll(Arrays.asList(lee.cadenas()));
	            updatePartidasPanel(partidas, colocarPanel, esperarPanel);
	            ventanas.show(cardPanel, "continuar");
	        }
	    });

	    return fondo;
	}

	private void updatePartidasPanel(List<String> partidas, JPanel colocarPanel, JPanel esperarPanel) {
	    colocarPanel.removeAll();
	    esperarPanel.removeAll();
	    
	    JLabel coloca = new JLabel("Te toca colocar");
	    coloca.setHorizontalAlignment(SwingConstants.CENTER);
	    JPanel panelPartida1 = new JPanel();
	    panelPartida1.add(coloca);
	    colocarPanel.add(panelPartida1);
	    
	    JLabel espera = new JLabel("Tienes que esperar");
	    espera.setHorizontalAlignment(SwingConstants.CENTER);
	    JPanel panelPartida2 = new JPanel();
	    panelPartida2.add(espera);
	    esperarPanel.add(panelPartida2);
	    
	    for (String partida : partidas) {
	        String[] datosPartida = partida.split("&");
	        
	        if (!datosPartida[0].equals("null")) {
		        String partidaID = datosPartida[0];
		        String usuario2 = datosPartida[1];
		        String estadoMovimiento = datosPartida[2];
		        String estadoConexion = datosPartida[3];
	
		        JPanel panelPartida = new JPanel();
		        JLabel labelUsuario = new JLabel(usuario2);
	
		        if (estadoConexion.equals("conectado")) {
		            labelUsuario.setForeground(Color.GREEN);
		        } else if (estadoConexion.equals("desconectado")) {
		            labelUsuario.setForeground(Color.RED);
		        }
	
		        panelPartida.add(labelUsuario);
	
		        JButton btnRendirse = new JButton("Rendirse");
		        btnRendirse.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	escribe.dimitir(Integer.parseInt(partidaID));
		            }
		        });
		        panelPartida.add(btnRendirse);
	
		        JButton btnContinuar = new JButton("Continuar");
		        btnContinuar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                escribe.continuaPartida(Integer.parseInt(partidaID));
		                String[] entrada=lee.cadenas();
		                tamaño = Integer.parseInt(entrada[0]);		                
		                datos = new String[entrada.length - 1];
						// Copiamos los elementos relevantes a la nueva matriz
						for (int i = 1; i < entrada.length; i++) {
							datos[i - 1] = entrada[i];
						}
						ventanas.show(cardPanel, "jugar");
		            }
		        });
		        panelPartida.add(btnContinuar);
	
		        if (estadoMovimiento.equals("quieto")) {
		            esperarPanel.add(panelPartida);
		        } else if (estadoMovimiento.equals("mueve")) {
		            colocarPanel.add(panelPartida);
		        }
	        }
	    }

	    colocarPanel.revalidate();
	    colocarPanel.repaint();

	    esperarPanel.revalidate();
	    esperarPanel.repaint();
	}

	private JPanel historialPanel() {
	    FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
	    fondo.setLayout(new BorderLayout());

	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    menuBar.add(salir);
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ventanas.show(cardPanel, "menu");
	        }
	    });

	    JButton refrescar = new JButton("Refrescar");
	    menuBar.add(refrescar);

	    List<String> partidas = new ArrayList<>();
	    escribe.getPartidasAcabadas();
	    partidas.addAll(Arrays.asList(lee.cadenas()));

	    JPanel partidasPanel = new JPanel();
	    partidasPanel.setLayout(new BoxLayout(partidasPanel, BoxLayout.Y_AXIS));
	    updateHistorialPanel(partidas, partidasPanel);

	    JScrollPane scrollPane = new JScrollPane(partidasPanel);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    fondo.add(scrollPane, BorderLayout.CENTER);

	    refrescar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	escribe.getPartidasAcabadas();
	            partidas.clear();
	    	    partidas.addAll(Arrays.asList(lee.cadenas()));
	            updateHistorialPanel(partidas, partidasPanel);
	            ventanas.show(cardPanel, "historial");
	        }
	    });

	    return fondo;
	}

	private void updateHistorialPanel(List<String> partidas, JPanel partidasPanel) {
	    partidasPanel.removeAll();
	    
	    for (String partida : partidas) {
	        String[] datosPartida = partida.split("&");//partidaId&usuario2&(ultimo usuario)&(gano o dimitio)_...
	        if (!datosPartida[0].equals("null")) {
		        String partidaID = datosPartida[0];
		        String usuario2 = datosPartida[1];
		        String ultimoUsuario = datosPartida[2];
		        String resultado = datosPartida[3];
	
		        JPanel panelPartida = new JPanel();
		        JLabel labelUsuario = new JLabel("El contrincante fue "+usuario2);
		        panelPartida.add(labelUsuario);
		        
		        JLabel labelGanador = new JLabel("y el usuario "+ultimoUsuario+" "+resultado);
		        panelPartida.add(labelGanador);
		        
		        JButton btnContinuar = new JButton("Visualizar");
		        btnContinuar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	ventanas.show(cardPanel, "visualizar");
		            	escribe.replay(Integer.parseInt(partidaID));
		            	datos = lee.cadenas(); // tamaño_usuario&columna&fila_usuario&columna&fila...
		            }
		        });
		        panelPartida.add(btnContinuar);
		        partidasPanel.add(panelPartida);
	        }else {
		        JPanel panel = new JPanel();
	
		        JLabel lblNewLabel = new JLabel("Aun no has jugado");
		        panel.add(lblNewLabel);
	
		        partidasPanel.add(panel);
	        }
	        
	    } 

	    partidasPanel.revalidate();
	    partidasPanel.repaint();
	}
	
	private JPanel visualizarPanel() {
	    FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
	    fondo.setLayout(new BorderLayout());
	    
	    int tamaño = Integer.parseInt(datos[0]);
	    final int[] index = {0}; // Declaración de index como un array final de un elemento
//	    Al declarar index como un array, podemos acceder y modificar su valor dentro de las clases internas anónimas, de otras formas da error

	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ventanas.show(cardPanel, "menu");
	        }
	    });
	    menuBar.add(salir);

	    JButton btnAtras = new JButton("Atras");
	    menuBar.add(btnAtras);

	    JButton btnSiguiente = new JButton("Siguiente");
	    menuBar.add(btnSiguiente);

	    JLabel labelResultado;
	    String[] ultimaJugada = datos[datos.length - 1].split("&");
	    if (ultimaJugada[0].equals(this.user)) {
	        labelResultado = new JLabel("¡Ganaste la partida!");
	    } else {
	        labelResultado = new JLabel("Perdiste contra " + ultimaJugada[0]);
	    }
	    menuBar.add(labelResultado);

	    JPanel contenedor = new JPanel();
	    fondo.add(contenedor);
	    contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));

	    Component horizontalStrut = Box.createHorizontalStrut((int) (frame.getWidth() * 0.05));
	    contenedor.add(horizontalStrut);
	//------------------------------------------------Tablero
	    JPanel tableroPanel = new JPanel();
	    tableroPanel.setLayout(new GridLayout(tamaño, tamaño)); // Grid horizontal
	    JPanel[][] matrizPaneles = new JPanel[tamaño][tamaño];
	    contenedor.add(tableroPanel);
	//------------------------------------------------
	    Component horizontalStrut_1 = Box.createHorizontalStrut(22);
	    contenedor.add(horizontalStrut_1);

	    // Llama a la función inicialmente para mostrar el tablero vacío
	    updateVisual(tableroPanel, matrizPaneles, tamaño, index[0]);

	    btnSiguiente.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (index[0] < datos.length - 1) {
	                index[0] += 1;
	                updateVisual(tableroPanel, matrizPaneles, tamaño, index[0]);
	            }
	        }
	    });

	    btnAtras.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (index[0] > 0) {
	                index[0] -= 1;
	                updateVisual(tableroPanel, matrizPaneles, tamaño, index[0]);
	            }
	        }
	    });

	    return fondo;
	}

	private void updateVisual(JPanel tablero, JPanel[][] matrizPaneles, int tamaño, int index) {
	    // Limpiar el tablero antes de actualizarlo
	    tablero.removeAll();

	    for (int i = 0; i < tamaño; i++) {
	        JPanel columna = new JPanel();
	        columna.setLayout(new GridLayout(tamaño, 1)); // Grid vertical
	        for (int j = 0; j < tamaño; j++) {
	            JPanel panel = new JPanel();
	            panel.setBackground(Color.WHITE); // Panel blanco por defecto
	            panel.addMouseListener(new PanelClickListener(i, j, matrizPaneles, tamaño));
	            matrizPaneles[i][j] = panel;
	            columna.add(panel);
	        }
	        tablero.add(columna);
	    }

	    // Si hay turnos, actualizar el tablero con las jugadas
	    if (!datos[0].isEmpty()) {
	        for (int i = 1; i <= index; i++) {
	            String[] infoJugada = datos[i].split("&");
	            int columna = Integer.parseInt(infoJugada[1]);
	            int fila = Integer.parseInt(infoJugada[2]);
	            String jugador = infoJugada[0];

	            // Actualizar el estado de la casilla en el tablero
	            JPanel panel = new JPanel();
	            if (jugador.equals(this.user)) {
	                panel.setBackground(Color.RED);
	            } else {
	                panel.setBackground(Color.YELLOW);
	            }
	            panel.addMouseListener(new PanelClickListener(fila, columna, matrizPaneles, tamaño));
	            matrizPaneles[fila][columna] = panel;
	        }
	    }

	    // Volver a validar y repintar el contenedor
	    tablero.revalidate();
	    tablero.repaint();
	}
	

	private JPanel configPanel(String intento) {
		FondoPanel fondo = new FondoPanel("src/main/resources/images/fondo1.jpg");
		fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		fondo.add(verticalGlue);
		
		JPanel panel = new JPanel();
		fondo.add(panel);
		
		JLabel lblNewLabel = new JLabel("Cambiar nombre:");
		panel.add(lblNewLabel);
		
		var usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setText(user);
		panel.add(usuario);
		
		JPanel panel_1 = new JPanel();
		fondo.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Cambiar contraseña:");
		panel_1.add(lblNewLabel_1);
		
		var contraseña = new JTextField();
		contraseña.setColumns(10);
		contraseña.setText(pass);
		panel_1.add(contraseña);
		
		JPanel panel_2 = new JPanel();
		fondo.add(panel_2);
		
		JButton volver = new JButton("Volver");
		panel_2.add(volver);
		volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanas.show(cardPanel, "menu");
            }
        });
		
		JButton guardar = new JButton("Guardar");
		panel_2.add(guardar);
		
		AtomicBoolean correcto=new AtomicBoolean(false);
		JLabel incorrecto = new JLabel(intento);
		incorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(incorrecto);
		
		guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	escribe.updateUser(user, usuario.getText(), contraseña.getText());
            	correcto.set(lee.confirma());
            	if (!correcto.get()) {
                    ventanas.show(cardPanel, "configM");
                }else {
                	user=usuario.getText();
                	pass=contraseña.getText();
                	ventanas.show(cardPanel, "menu");
                }
                
            }
        });
		
		return fondo;
	}
}
