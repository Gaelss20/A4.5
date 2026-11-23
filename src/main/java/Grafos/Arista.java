package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Arista {
    private Vertice origen;
    private Vertice destino;

    public Arista(Vertice origen, Vertice destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void dibujar(Graphics2D g2) {
        g2.setColor(new Color(66, 66, 66));
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());
    }
}
// Clase Arista verificada y documentada por Dylan Fuentes
