/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica.paginacion;

/**
 *
 * @author Cristian C4
 */
public class Pagina {
    private int tamanoParticionKib;
    private int numeroPagina;
    private boolean estaAsignada;

    public Pagina(int tamanoParticionKib, int numeroPagina) {
        this.tamanoParticionKib = tamanoParticionKib;
        this.numeroPagina = numeroPagina;
        this.estaAsignada = false;
    }
    
    public int getTamanoParticionKib() {
        return tamanoParticionKib;
    }

    public void setTamanoParticionKib(int tamanoParticionKib) {
        this.tamanoParticionKib = tamanoParticionKib;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }    

    public boolean isEstaAsignada() {
        return estaAsignada;
    }

    public void setEstaAsignada(boolean estaAsignada) {
        this.estaAsignada = estaAsignada;
    }
    
}
