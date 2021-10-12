/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacionPDinamica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Admin
 */
public class MemoriaPDinamica extends javax.swing.JPanel {

    private static final int MEM_DISP_DISPLAY = 448;
    private ArrayList<ParticionPDinamica> particionesOcupadas;
    private ArrayList<ParticionPDinamica> particionesDesocupadas;
    private ArrayList<ParticionPDinamica> particionesOtros;
    private int memWidth;
    private int memHeigth;
    private int memX;
    private int memY;
    private int memoriadisponible;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MemoriaPDinamica());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MemoriaPDinamica() {
        super();
        initGUI();
        particionesOcupadas = new ArrayList<>();
        particionesDesocupadas = new ArrayList<>();
        memoriadisponible = 448;
    }

    private void initGUI() {
        inicializarVariables();
        this.setPreferredSize(new java.awt.Dimension(150, 512));
    }

    public void inicializarVariables() {
        memHeigth = 512;
        memWidth = 150;
        memX = 0;
        memY = 0;
    }

    public void paint(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawRect(memX, memY, memWidth, memHeigth);

        /**
         * Draw S.O always 2MB
         */
        g.setColor(Color.RED);
        g.fillRect(memX, MEM_DISP_DISPLAY, memWidth, 64);
        g.setColor(Color.black);
        g.drawString("S.O", 70, 480);

    }

    public void agregarParticion(ParticionPDinamica particion) {
        particionesOcupadas.add(particion);
    }

    public void agregarParticionDesocupada(ParticionPDinamica particion) {
        particionesDesocupadas.add(particion);
    }

    public void actualizarParticionOcupada(List<ParticionPDinamica> listaParticion) {
        particionesOcupadas.clear();
        particionesOcupadas.addAll(listaParticion);
    }
    
    public void actualizarParticionDesOcupada(List<ParticionPDinamica> listaParticion) {
        particionesDesocupadas.clear();
        particionesDesocupadas.addAll(listaParticion);
    }

    public int getMemWidth() {
        return memWidth;
    }

    public void setMemWidth(int memWidth) {
        this.memWidth = memWidth;
    }

    public int getMemHeigth() {
        return memHeigth;
    }

    public void setMemHeigth(int memHeigth) {
        this.memHeigth = memHeigth;
    }

    public int getMemX() {
        return memX;
    }

    public void setMemX(int memX) {
        this.memX = memX;
    }

    public int getMemY() {
        return memY;
    }

    public void setMemY(int memY) {
        this.memY = memY;
    }

    public int getMemoriadisponible() {
        return memoriadisponible;
    }

    public void setMemoriadisponible(int memoriadisponible) {
        this.memoriadisponible = memoriadisponible;
    }

    public ArrayList<ParticionPDinamica> getParticionesOcupadas() {
        return particionesOcupadas;
    }

    public void setParticionesOcupadas(ArrayList<ParticionPDinamica> particionesOcupadas) {
        this.particionesOcupadas = particionesOcupadas;
    }

    public ArrayList<ParticionPDinamica> getParticionesDesocupadas() {
        return particionesDesocupadas;
    }

    public void setParticionesDesocupadas(ArrayList<ParticionPDinamica> particionesDesocupadas) {
        this.particionesDesocupadas = particionesDesocupadas;
    }

}
