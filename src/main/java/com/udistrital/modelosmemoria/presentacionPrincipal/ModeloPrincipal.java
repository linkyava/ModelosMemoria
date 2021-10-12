/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacionPrincipal;

import com.udistrital.modelosmemoria.presentacion.estatica.Modelo;
import com.udistrital.modelosmemoria.presentacion.paginacion.ModeloPaginacion;
import java.util.ArrayList;

/**
 *
 * @author linkyava
 */
public class ModeloPrincipal {

    Modelo miApp;
    ModeloPaginacion modeloPaginacion;
    private VistaPrincipal miVentana;

    public VistaPrincipal getVentana() {
        if (miVentana == null) {
            miVentana = new VistaPrincipal(this);
        }
        return miVentana;
    }

    public void abrirVentanaEstatica() {
        miApp = new Modelo();
        miApp.iniciar();
    }
    
    public void abrirVentanaPaginacion(){
        modeloPaginacion = new ModeloPaginacion();
        modeloPaginacion.iniciar();
    }
    
    public void iniciar() {
        getVentana().setSize(400, 400);
        getVentana().setVisible(true);
    }

}
