/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacionPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author linkyava
 */
public class ControladorPrincipal implements ActionListener{
    
    private final VistaPrincipal miVentana;
    private ModeloPrincipal modelo;
    
    public ControladorPrincipal(VistaPrincipal miVista){
        miVentana = miVista;
        modelo = miVentana.getMiModelo();
    }
    
    public ModeloPrincipal getModeloPrincipal() {
        return modelo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JMenuItem){
            JMenuItem menuItem = (JMenuItem) e.getSource();            

            if (menuItem == miVentana.getjMenuEstaticaF()) {
                
                modelo.abrirVentanaEstatica();
                
            }
            
            if (menuItem == miVentana.getjMenuEstaticaV()) {
                
                modelo.abrirVentanaVariable();
            }
            
            if (menuItem == miVentana.getjMenuDinamicaS()) {

            }
            
            if (menuItem == miVentana.getjMenuDinamicaC()) {

            }
            
            if (menuItem == miVentana.getjMenuSegmentacion()) {
                 modelo.abrirVentanaSegmentacion();
            }
            
            if (menuItem == miVentana.getjMenuPaginacion()) {
                modelo.abrirVentanaPaginacion();
            }
        }
    }
    
}