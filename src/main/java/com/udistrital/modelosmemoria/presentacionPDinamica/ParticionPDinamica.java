/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacionPDinamica;

/**
 *
 * @author Admin
 */
public class ParticionPDinamica {
    private ProcesoPDinamica proceso;
    private int tamaño;
    private String direccionInicialHexa;
    private String direccionFinalHexa;
    private int inicio;
    private int fin;
    private String Estado;

    public ProcesoPDinamica getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoPDinamica proceso) {
        this.proceso = proceso;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getDireccionInicialHexa() {
        return direccionInicialHexa;
    }

    public void setDireccionInicialHexa(String direccionInicialHexa) {
        this.direccionInicialHexa = direccionInicialHexa;
    }

    public String getDireccionFinalHexa() {
        return direccionFinalHexa;
    }

    public void setDireccionFinalHexa(String direccionFinalHexa) {
        this.direccionFinalHexa = direccionFinalHexa;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

   
    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
