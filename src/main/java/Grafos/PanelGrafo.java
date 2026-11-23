package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelGrafo extends JPanel {
    private List<Vertice> vertices = new ArrayList<>();
    private List<Arista> aristas = new ArrayList<>();
    private Vertice verticeSeleccionado = null;
    private Vertice origenArista = null;
    private int offsetX;
    private int offsetY;
    private int contador = 1;

    public PanelGrafo() {
        setBackground(Color.WHITE);

        MouseAdapter controladorMouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point punto = e.getPoint();
                Vertice clickeado = buscarVertice(punto);

                if (clickeado != null) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        if (origenArista == null) {
                            origenArista = clickeado;
                        } else if (origenArista != clickeado) {
                            aristas.add(new Arista(origenArista, clickeado));
                            origenArista = null;
                            repaint();
                        }
                    } else if (e.getClickCount() == 2) {
                        String nuevoNombre = JOptionPane.showInputDialog(
                                PanelGrafo.this,
                                "Nombre del vértice:",
                                clickeado.getNombre()
                        );
                        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                            clickeado.setNombre(nuevoNombre.trim());
                            repaint();
                        }
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        verticeSeleccionado = clickeado;
                        offsetX = punto.x - clickeado.getX();
                        offsetY = punto.y - clickeado.getY();
                        repaint();
                    }
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    origenArista = null;
                    String nombre = JOptionPane.showInputDialog(
                            PanelGrafo.this,
                            "Ingresa el nombre del vértice:",
                            "V" + contador
                    );
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        vertices.add(new Vertice(nombre.trim(), punto.x, punto.y));
                        contador++;
                        repaint();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (verticeSeleccionado != null) {
                    int radio = verticeSeleccionado.getRadio();
                    int nuevoX = Math.max(radio, Math.min(getWidth() - radio, e.getX() - offsetX));
                    int nuevoY = Math.max(radio, Math.min(getHeight() - radio, e.getY() - offsetY));
                    verticeSeleccionado.setPosicion(nuevoX, nuevoY);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                verticeSeleccionado = null;
                repaint();
            }
        };

        addMouseListener(controladorMouse);
        addMouseMotionListener(controladorMouse);
    }

    public void limpiar() {
        vertices.clear();
        aristas.clear();
        verticeSeleccionado = null;
        origenArista = null;
        contador = 1;
        repaint();
    }

    private Vertice buscarVertice(Point p) {
        for (int i = vertices.size() - 1; i >= 0; i--) {
            if (vertices.get(i).contienePunto(p)) {
                return vertices.get(i);
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Arista a : aristas) {
            a.dibujar(g2);
        }

        for (Vertice v : vertices) {
            v.dibujar(g2, v == verticeSeleccionado || v == origenArista);
        }
    }
}
// Clase PanelGrafo verificada y documentada por Dylan Fuentes
