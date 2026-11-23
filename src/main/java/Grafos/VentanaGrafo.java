package grafos;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaGrafo extends JFrame {
    public VentanaGrafo() {
        setTitle("Editor de Grafo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        PanelGrafo panelGrafo = new PanelGrafo();
        add(panelGrafo, BorderLayout.CENTER);

        JPanel barraSuperior = new JPanel();
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> panelGrafo.limpiar());

        JLabel instruccion = new JLabel(
                "Clic izq: crear/mover | Doble clic: renombrar | Clic der en 2 nodos: unir arista"
        );

        barraSuperior.add(btnLimpiar);
        barraSuperior.add(instruccion);
        add(barraSuperior, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaGrafo().setVisible(true));
    }
}