package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Vertice {
    private String nombre;
    private int x;
    private int y;
    private int radio = 22;

    public Vertice(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadio() {
        return radio;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contienePunto(Point p) {
        double dx = p.x - x;
        double dy = p.y - y;
        return (dx * dx + dy * dy) <= (radio * radio);
    }

    public void dibujar(Graphics2D g2, boolean seleccionado) {
        g2.setColor(seleccionado ? new Color(255, 224, 130) : new Color(144, 202, 249));
        g2.fillOval(x - radio, y - radio, radio * 2, radio * 2);

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2f));
        g2.drawOval(x - radio, y - radio, radio * 2, radio * 2);

        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int textoAncho = fm.stringWidth(nombre);
        int textoAlto = fm.getAscent();
        g2.drawString(nombre, x - textoAncho / 2, y + textoAlto / 4);
    }
}