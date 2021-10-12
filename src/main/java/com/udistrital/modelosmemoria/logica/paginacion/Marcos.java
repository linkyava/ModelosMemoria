/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica.paginacion;

import com.udistrital.modelosmemoria.logica.util.UtilProcess;
import java.util.ArrayList;

/**
 *
 * @author Cristian C4
 */
public class Marcos {
    private int numeroMarco;
    private int tamanoMarco;
    private boolean estaOcupado;
    private int inicioPosMarco;
    private int finPosMarco;

    public Marcos(int numeroMarco ,int previousEndPosPartition, int totalSpaceKiB) {
        this.numeroMarco = numeroMarco;
        this.tamanoMarco = totalSpaceKiB;
        this.inicioPosMarco = previousEndPosPartition;
        this.finPosMarco = inicioPosMarco + getTotalSpaceInPixels();
        this.estaOcupado = false;
    }
    
    public int getTotalSpaceInPixels(){
        return tamanoMarco / UtilProcess.onePxInKiB;
    }
    
    public int getNumeroMarco() {
        return numeroMarco;
    }

    public void setNumeroMarco(int numeroMarco) {
        this.numeroMarco = numeroMarco;
    }

    public int getTamanoMarco() {
        return tamanoMarco;
    }

    public void setTamanoMarco(int tamanoMarco) {
        this.tamanoMarco = tamanoMarco;
    }

    public boolean getEstaOcupado() {
        return estaOcupado;
    }

    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public int getInicioPosMarco() {
        return inicioPosMarco;
    }

    public void setInicioPosMarco(int inicioPosMarco) {
        this.inicioPosMarco = inicioPosMarco;
    }

    public int getFinPosMarco() {
        return finPosMarco;
    }

    public void setFinPosMarco(int finPosMarco) {
        this.finPosMarco = finPosMarco;
    }    
}
