/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.logica;

/**
 *
 * @author linkyava
 */
public class Segmento {

    private int yPrint;
    private int yAnt;
    private int tamanioKbps;
    private String parte;
    private ProcesoMemoria procesoMemoria;

    public Segmento(int yPrint, int yAnt, int tamanioKbps, String parte, ProcesoMemoria procesoMemoria) {
        this.yPrint = yPrint;
        this.yAnt = yAnt;
        this.tamanioKbps = tamanioKbps;
        this.parte = parte;
        this.procesoMemoria = procesoMemoria;
    }
    
    
    
    public int getyPrint() {
        return yPrint;
    }

    public void setyPrint(int yPrint) {
        this.yPrint = yPrint;
    }

    public int getyAnt() {
        return yAnt;
    }

    public void setyAnt(int yAnt) {
        this.yAnt = yAnt;
    }

    public int getTamanioKbps() {
        return tamanioKbps;
    }

    public void setTamanioKbps(int tamanioKbps) {
        this.tamanioKbps = tamanioKbps;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public ProcesoMemoria getProcesoMemoria() {
        return procesoMemoria;
    }

    public void setProcesoMemoria(ProcesoMemoria procesoMemoria) {
        this.procesoMemoria = procesoMemoria;
    }
}
