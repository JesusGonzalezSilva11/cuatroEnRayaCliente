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
        
        JPanel contenedor = new JPanel();
        fondo.add(contenedor);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));

        Component horizontalStrut = Box.createHorizontalStrut((int) (frame.getWidth() * 0.05));
        contenedor.add(horizontalStrut);
//------------------------------------------------Tablero
        JPanel tableroPanel = new JPanel();
        tableroPanel.setLayout(new GridLayout(1, tamaño)); // Grid horizontal
        JPanel[][]matrizPaneles = new JPanel[tamaño][tamaño];
        crearTablero(tableroPanel,matrizPaneles,tamaño);
        contenedor.add(tableroPanel);
//------------------------------------------------
        Component horizontalStrut_1 = Box.createHorizontalStrut(22);
        contenedor.add(horizontalStrut_1);
    }

    private void crearTablero(JPanel tablero,JPanel[][]matrizPaneles,int tamaño) {
        for (int i = 0; i < tamaño; i++) {
            JPanel columna = new JPanel();
            columna.setLayout(new GridLayout(tamaño, 1)); // Grid vertical
            for (int j = 0; j < tamaño; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE); // Panel blanco por defecto
                panel.addMouseListener(new PanelClickListener(i, j));
                matrizPaneles[i][j] = panel;
                columna.add(panel);
            }
            tablero.add(columna);
        }
    }

    // Clase interna para manejar clics en los paneles
    public class PanelClickListener extends MouseAdapter {
        private int columna;
        private int fila;

        public PanelClickListener(int columna, int fila) {
            this.columna = columna;
            this.fila = fila;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            cambiarColorPanel();
        }

        private void cambiarColorPanel() {
            for (int i = tamaño - 1; i >= 0; i--) {
//                if (matrizPaneles[columna][i].getBackground().equals(Color.WHITE)) {//Si ve que esta blanco le pide al server que coloque
///*cambia esta linea merluzo*/	matrizPaneles[columna][i].setBackground(Color.RED); // Pedira al server un true o false si le regresa true cambia
//                    break;
//                }
            }
        }

    }
}
