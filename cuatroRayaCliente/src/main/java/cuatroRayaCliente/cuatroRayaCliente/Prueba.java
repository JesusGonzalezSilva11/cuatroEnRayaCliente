package cuatroRayaCliente.cuatroRayaCliente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;

public class Prueba {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba window = new Prueba();
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
	public Prueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setBounds(100, 100, 450, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel fondo = new JPanel();
	    frame.getContentPane().add(fondo, BorderLayout.CENTER);
	    fondo.setLayout(new BorderLayout()); // Cambiar el layout a BorderLayout
	    
	    JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH); // Agregar la barra de menú en la parte superior
	    
	    JButton salir = new JButton("Salir");
	    menuBar.add(salir);
	    
	    ArrayList<String> usuarios = new ArrayList<String>();
	    for (int i = 0; i < 20; i++) {
	        usuarios.add("usuario" + i);
	    }
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new GridLayout(1, 2)); // GridLayout con una fila y dos columnas
	    
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	    
	    for (String user : usuarios) {
	        JPanel panel = new JPanel();
	        
	        JLabel lblNewLabel = new JLabel(user);
	        panel.add(lblNewLabel);
	        
	        JButton btnNewButton_1 = new JButton("desafiar");
	        panel.add(btnNewButton_1);
	        
	        panel1.add(panel);
	    }
	    
	    JScrollPane scrollPane1 = new JScrollPane(panel1);
	    scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    
	    for (String user : usuarios) {
	        JPanel panel = new JPanel();
	        
	        JLabel lblNewLabel = new JLabel(user);
	        panel.add(lblNewLabel);
	        
	        JButton btnNewButton_1 = new JButton("desafiar");
	        panel.add(btnNewButton_1);
	        
	        panel2.add(panel);
	    }
	    
	    JScrollPane scrollPane2 = new JScrollPane(panel2);
	    scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    panelPrincipal.add(scrollPane1);
	    panelPrincipal.add(scrollPane2);
	    
	    fondo.add(panelPrincipal, BorderLayout.CENTER); // Agregar el panel principal al centro del BorderLayout
	}






}
