/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.logica;

import java.util.List;

/**
 *
 * @author linkyava
 */
public class ParticionMemoria {
    
    private int numeroParticion;
    private String direccionInicialHexa;
    private String direccionFinalHexa;
    private int direccionInicialByte;
    private int direccionFinalByte;
    private int xPrint;
    private int yPrint;
    private int yAnt;
    private int capacidadKbs;
    private int libreKbs;
    private List<Segmento> segmentos;
    private boolean llena;
    
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

    public int getDireccionInicialByte() {
        return direccionInicialByte;
    }

    public void setDireccionInicialByte(int direccionInicialByte) {
        this.direccionInicialByte = direccionInicialByte;
    }

    public int getDireccionFinalByte() {
        return direccionFinalByte;
    }

    public void setDireccionFinalByte(int direccionFinalByte) {
        this.direccionFinalByte = direccionFinalByte;
    }

    public List<Segmento> getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(List<Segmento> segmentos) {
        this.segmentos = segmentos;
    }

    public int getxPrint() {
        return xPrint;
    }

    public void setxPrint(int xPrint) {
        this.xPrint = xPrint;
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

    public int getCapacidadKbs() {
        return capacidadKbs;
    }

    public void setCapacidadKbs(int capacidadKbs) {
        this.capacidadKbs = capacidadKbs;
    }

    public int getLibreKbs() {
        return libreKbs;
    }

    public void setLibreKbs(int libreKbs) {
        this.libreKbs = libreKbs;
    }

    public boolean isLlena() {
        return llena;
    }

    public void setLlena(boolean llena) {
        this.llena = llena;
    }

    public int getNumeroParticion() {
        return numeroParticion;
    }

    public void setNumeroParticion(int numeroParticion) {
        this.numeroParticion = numeroParticion;
    }
    
    @Override
    public String toString() {
        return "ParticionMemoria{" + "direccionInicialHexa = " + direccionInicialHexa + " direccionFinalHexa = " + direccionFinalHexa + '}';
    }
    
    
    
}
