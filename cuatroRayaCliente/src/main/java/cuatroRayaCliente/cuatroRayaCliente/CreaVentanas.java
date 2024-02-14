package cuatroRayaCliente.cuatroRayaCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class CreaVentanas {

    private JFrame frame;
    private int tamaño = 7; // Variable para la longitud de la matriz

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreaVentanas window = new CreaVentanas();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreaVentanas() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300); // Ajusta el tamaño según tus necesidades
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel fondo = new JPanel();
        frame.getContentPane().add(fondo);
        fondo.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
	    fondo.add(menuBar, BorderLayout.NORTH);

	    JButton salir = new JButton("Salir");
	    salir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
//	            ventanas.show(cardPanel, "menu");
	        }
	    });
	    menuBar.add(salir);
	    
	    JButton btnRendirse = new JButton("Rendirse");
        btnRendirse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	escribe.dimitir(Integer.parseInt(partidaID));
            }
        });
        menuBar.add(btnRendirse);

    }
}
