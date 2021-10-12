/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion.paginacion;

import com.udistrital.modelosmemoria.presentacion.estatica.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Cristian C4
 */
public class ControladorPaginacion implements ActionListener{

    private final VistaPaginacion miVentana;
    private ModeloPaginacion modelo;
    
    public ControladorPaginacion(VistaPaginacion miVista){
        miVentana = miVista;
        modelo = miVentana.getMiModelo();
    }
     public ModeloPaginacion getModelo() {
        return modelo;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // get modelo y hacer accion
        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();            

            if (boton == miVentana.getBtnCrearParticion()) {
                getModelo().crearMarcos();
            }
            if (boton == miVentana.getBtnCrearProceso()) {
                getModelo().crearProceso();
                
            }
            
            if (boton == miVentana.getBtnBorrarProceso()) {
                getModelo().borrarRegistroTabPag();
            }
        }
    }
    
}
