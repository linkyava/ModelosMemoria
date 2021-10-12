/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacion.segmentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author linkyava
 */
public class ControladoSegmentacion implements ActionListener{
    
     
    private final VistaSegmentacion miVentana;
    private ModeloSegmentacion modelo;
    
    public ControladoSegmentacion(VistaSegmentacion miVista){
        miVentana = miVista;
        modelo = miVentana.getMiModelo();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();            

            if (button == miVentana.getBtnIniciar()) {
                
                modelo.iniciarGestionMemoria();
                
            }else if(button == miVentana.getBtnCrearProceso()){
                
                modelo.crearProceso();
                
            }else if(button == miVentana.getBtnBorrar()){
                
                modelo.borrarRowTabla();
                
            }
            
            
         }
    }
    
    public void ejecutarSegmentacionMemoria(String pMetodo){
    }
}
