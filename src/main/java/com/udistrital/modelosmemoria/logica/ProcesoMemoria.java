package com.udistrital.modelosmemoria.logica;

import java.awt.Color;

public class ProcesoMemoria {
	
    private int PID;
    private int pesoDatos;
    private int pesoCodigo;
    private int pesoPila;
    private int pesoTotal;
    private boolean ubicado;
    
    private Color color;

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPesoDatos() {
        return pesoDatos;
    }

    public void setPesoDatos(int pesoDatos) {
        this.pesoDatos = pesoDatos;
    }

    public int getPesoCodigo() {
        return pesoCodigo;
    }

    public void setPesoCodigo(int pesoCodigo) {
        this.pesoCodigo = pesoCodigo;
    }

    public int getPesoPila() {
        return pesoPila;
    }

    public void setPesoPila(int pesoPila) {
        this.pesoPila = pesoPila;
    }

    public int getPesoTotal() {
        return pesoPila + pesoDatos + pesoCodigo;
    }

    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public boolean isUbicado() {
        return ubicado;
    }

    public void setUbicado(boolean ubicado) {
        this.ubicado = ubicado;
    }
    
    
    
	
}
