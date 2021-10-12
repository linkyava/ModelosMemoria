/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion.estatica;

import com.udistrital.modelosmemoria.logica.Proceso;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Cristian C4
 */
public class PanelMemoria extends JPanel implements Scrollable {
    
    private Modelo miModelo;
    
    public PanelMemoria(Modelo m) {
        this.miModelo = m;
        this.setBackground(Color.white);
    }

    
    public Modelo getMiModelo() {
        return miModelo;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        getMiModelo().crearSistemaOperativo(g2d);
        getMiModelo().dibujarTablaParticion(g2d);
        dibujarParticiones(g2d);
        dibujarProcesos(g2d);
        g2d.dispose();
    }

    public void dibujarParticiones(Graphics2D g2d){
        if(getMiModelo().getParticiones() != null){
            getMiModelo().dibujarParticiones(g2d);
        }
    }
    
    public void dibujarProcesos(Graphics2D g2d){
        if(getMiModelo().getProcesos() != null){
            List<Proceso> p = getMiModelo().getProcesos();
            for (int i = 0; i < p.size(); i++) {
                //dibujar procesos en el arreglo
                getMiModelo().dibujarProceso(p.get(i), g2d);
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 1034);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(200, 1034);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(200, 1034);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width
                <= getParent().getSize().width;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return getPreferredSize().height
                <= getParent().getSize().height;
    }

}
