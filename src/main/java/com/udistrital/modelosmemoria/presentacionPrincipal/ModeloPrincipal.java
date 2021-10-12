/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacionPrincipal;

import com.udistrital.modelosmemoria.presentacion.Modelo;
import com.udistrital.modelosmemoria.presentacionPDinamica.ModeloPDinamica;
import java.util.ArrayList;

/**
 *
 * @author linkyava
 */
public class ModeloPrincipal {
    
    Modelo miApp;
    ModeloPDinamica modeloPDinamica;
    private VistaPrincipal miVentana;


    public VistaPrincipal getVentana() {
        if (miVentana == null) {
            miVentana = new VistaPrincipal(this);
        }
        return miVentana;
    }
    
    
    public void abrirVentanaEstatica(){
        miApp = new Modelo();
        miApp.iniciar();
    }
    
    public void abrirVentanaPDinamica(){
        modeloPDinamica = new ModeloPDinamica();
        modeloPDinamica.iniciar();
    }
    
   public void iniciar(){
        getVentana().setSize(400, 400);
        getVentana().setVisible(true);
    }
    
}
