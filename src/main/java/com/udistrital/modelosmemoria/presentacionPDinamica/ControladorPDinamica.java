/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacionPDinamica;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author Admin
 */
public class ControladorPDinamica implements ActionListener {

    private final VistaPDinamica vistaPDinamica;
    private ModeloPDinamica modeloPDinamica;

    public ControladorPDinamica(VistaPDinamica vista) {
        vistaPDinamica = vista;
        modeloPDinamica = vistaPDinamica.getModeloPDinamica();
    }

    public ModeloPDinamica getModelo() {
        return modeloPDinamica;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();
            if (boton == vistaPDinamica.getBtnAgregarProceso()) {
                getModelo().crearParticion();
                getModelo().dibujarParticion();
            }
            if(boton == vistaPDinamica.getBtnReinicio()){
                getModelo().reiniciarParticiones();
            }
           
        }
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();
            if (check.isSelected()) {
                getModelo().compactacion();
            }
        }
    }

}
