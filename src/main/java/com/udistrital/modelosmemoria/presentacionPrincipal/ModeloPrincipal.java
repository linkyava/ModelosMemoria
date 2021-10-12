/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacionPrincipal;

import com.udistrital.modelosmemoria.presentacion.estatica.Modelo;
import com.udistrital.modelosmemoria.presentacion.paginacion.ModeloPaginacion;
import com.udistrital.modelosmemoria.presentacion.segmentacion.ModeloSegmentacion;
import com.udistrital.modelosmemoria.presentacionPDinamica.ModeloPDinamica;
import java.util.ArrayList;

/**
 *
 * @author linkyava
 */
public class ModeloPrincipal {
    
    Modelo miApp;
    ModeloPaginacion modeloPaginacion;
    ModeloSegmentacion modeloVarSeg;
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
    
    public void abrirVentanaVariable(){
        modeloVarSeg = new ModeloSegmentacion();
        modeloVarSeg.setEsEditablePartcion(false);
        modeloVarSeg.iniciar();
    }
    
     public void abrirVentanaSegmentacion(){
        modeloVarSeg = new ModeloSegmentacion();
        modeloVarSeg.setEsEditablePartcion(true);
        modeloVarSeg.iniciar();
    }
    
    public void abrirVentanaPaginacion(){
        modeloPaginacion = new ModeloPaginacion();
        modeloPaginacion.iniciar();
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