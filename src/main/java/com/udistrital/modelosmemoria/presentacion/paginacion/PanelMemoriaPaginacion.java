/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion.paginacion;

import com.udistrital.modelosmemoria.presentacion.estatica.*;
import com.udistrital.modelosmemoria.logica.Proceso;
import com.udistrital.modelosmemoria.logica.paginacion.ProcesoPaginacion;
import com.udistrital.modelosmemoria.logica.paginacion.TablaPaginacion;
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
public class PanelMemoriaPaginacion extends JPanel implements Scrollable {
    
    private ModeloPaginacion miModelo;
    
    public PanelMemoriaPaginacion(ModeloPaginacion m) {
        this.miModelo = m;
        this.setBackground(Color.white);
    }

    
    public ModeloPaginacion getMiModelo() {
        return miModelo;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        getMiModelo().crearSistemaOperativo(g2d);
        getMiModelo().dibujarTablaParticion(g2d);
        dibujarMarcos(g2d);
        dibujarProcesos(g2d);
        g2d.dispose();
    }

    public void dibujarMarcos(Graphics2D g2d){
        if(getMiModelo().getMarcos()!= null){
            getMiModelo().dibujarMarcos(g2d);
        }
    }
    
    public void dibujarProcesos(Graphics2D g2d){
        if(getMiModelo().getTablaAtenidos()!= null){
            List<TablaPaginacion> tabla = getMiModelo().getTablaAtenidos();//tabla de paginas
            for (int i = 0; i < tabla.size(); i++) {
                //dibujar procesos en el arreglo
                System.out.println("Proceso -> "+tabla.get(i).getProceso().getNombreProceso());
                System.out.println("Pagina -> "+tabla.get(i).getPagina().getNumeroPagina());
                System.out.println("Marco -> "+tabla.get(i).getMarco().getNumeroMarco()+ " ("+tabla.get(i).getMarco().getInicioPosMarco()+","+tabla.get(i).getMarco().getFinPosMarco()+")");
                getMiModelo().dibujarProceso(tabla.get(i), g2d);
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
