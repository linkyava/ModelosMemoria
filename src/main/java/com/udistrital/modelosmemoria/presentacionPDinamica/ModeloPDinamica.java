/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacionPDinamica;

import com.udistrital.modelosmemoria.logica.util.UtilPDinamica;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author Admin
 */
public class ModeloPDinamica {

    private VistaPDinamica vistaPDinamica;
    private int cont = 0;
    private boolean primer = false;
    private boolean siguiente = false;
    private boolean mejor = false;

    public VistaPDinamica getVentanapDinamica() {
        if (vistaPDinamica == null) {
            vistaPDinamica = new VistaPDinamica(this);
        }
        return vistaPDinamica;
    }

    public void iniciar() {
        getVentanapDinamica().setSize(700, 400);
        getVentanapDinamica().setVisible(true);
    }

    public void crearParticion() {
        int tamañoParticion = 0;
        String seleccionProceso = (String) getVentanapDinamica().getComboProceso().getSelectedItem();
        primer = getVentanapDinamica().getRadio1().isSelected();
        siguiente = getVentanapDinamica().getRadio2().isSelected();
        mejor = getVentanapDinamica().getRadio3().isSelected();
        if ("64K".equals(seleccionProceso)) {
            tamañoParticion = 2;
        }
        if ("128K".equals(seleccionProceso)) {
            tamañoParticion = 4;
        }
        if ("256K".equals(seleccionProceso)) {
            tamañoParticion = 8;
        }
        if ("512K".equals(seleccionProceso)) {
            tamañoParticion = 16;
        }
        if ("1024K".equals(seleccionProceso)) {
            tamañoParticion = 32;
        }
        if ("2048K".equals(seleccionProceso)) {
            tamañoParticion = 64;
        }
        if ("4096K".equals(seleccionProceso)) {
            tamañoParticion = 128;
        }
        cont++;
        int espaciodisponible = getVentanapDinamica().getObjMemoria().getMemoriadisponible();
        if (espaciodisponible - tamañoParticion >= 0) {
            ParticionPDinamica particion = new ParticionPDinamica();
            ProcesoPDinamica proceso = new ProcesoPDinamica();
            proceso.setNombre(cont + "");
            proceso.setTamaño(tamañoParticion);
            particion.setProceso(proceso);
            particion.setTamaño(tamañoParticion);
            particion.setInicio(espaciodisponible - tamañoParticion);
            vistaPDinamica.getObjMemoria().agregarParticion(particion);
            getVentanapDinamica().getObjMemoria().setMemoriadisponible(espaciodisponible - tamañoParticion);
        } else {
            ParticionPDinamica particion = new ParticionPDinamica();
            ProcesoPDinamica proceso = new ProcesoPDinamica();
            proceso.setNombre(cont + "");
            proceso.setTamaño(tamañoParticion);
            particion.setProceso(proceso);
            particion.setTamaño(tamañoParticion);
            boolean valida = validarParticiones(particion);
            if (!valida) {
                retirarProceso();
                validarParticiones(particion);
            }

        }
    }

    public void dibujarParticion() {
        DefaultListModel modelo = new DefaultListModel();
        System.out.println("Dibujando Particion");
        Graphics g = getVentanapDinamica().getObjMemoria().getGraphics();
        g.setColor(Color.BLACK);
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesOcupadas().size(); i++) {
            ParticionPDinamica particion = getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i);
            g.drawRect(0, particion.getInicio(), UtilPDinamica.ANCHO_INICIAL, particion.getTamaño());
            if (particion.getProceso() != null) {
                modelo.addElement("Proceso "+particion.getProceso().getNombre()+"("+ TamañoKib(particion.getTamaño()+"") + ")");
                agregarProceso(particion);
            } else {
                limpiarProceso(particion);
            }
        }
        if (modelo.size() > 0) {
            getVentanapDinamica().getjList1().setModel(modelo);
        }
        if (getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size() == 0) {
            getVentanapDinamica().getCheckBox().setSelected(false);
        }

    }

    public void agregarProceso(ParticionPDinamica particion) {
        Graphics g = getVentanapDinamica().getObjMemoria().getGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(1, particion.getInicio() + 1, UtilPDinamica.ANCHO_INICIAL, particion.getTamaño() - 1);
    }

    public void limpiarProceso(ParticionPDinamica particion) {
        Graphics g = getVentanapDinamica().getObjMemoria().getGraphics();
        g.setColor(Color.white);
        g.fillRect(1, particion.getInicio() + 1, UtilPDinamica.ANCHO_INICIAL, particion.getTamaño() - 1);

    }

    public void retirarProceso() {
        int numeroProceso = getVentanapDinamica().getObjMemoria().getParticionesOcupadas().size();
        Random claseRandom = new Random();
        numeroProceso = claseRandom.nextInt(numeroProceso);
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesOcupadas().size(); i++) {
            if (i == numeroProceso) {
                ParticionPDinamica particion = getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i);
                if (particion.getProceso() != null) {
                    particion.setProceso(null);
                    getVentanapDinamica().getObjMemoria().agregarParticionDesocupada(particion);
                } else {
                    retirarProceso();
                }
            }
        }
    }

    public boolean validarParticiones(ParticionPDinamica particion) {
        boolean valida = false;
        int posicion = -1;
        ParticionPDinamica p2 = new ParticionPDinamica();
        if (getVentanapDinamica().getObjMemoria().getParticionesDesocupadas() != null && getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size() > 0) {
            if (primer) {
                posicion = primerAjuste(particion);
            }
            if (siguiente) {
                posicion = peorAjuste(particion);
            }
            if (mejor) {
                posicion = mejorAjuste(particion);
            }

            if (posicion != -1) {
                ParticionPDinamica p = getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(posicion);
                particion.setInicio((p.getTamaño() + p.getInicio()) - particion.getTamaño());
                getVentanapDinamica().getObjMemoria().agregarParticion(particion);
                p2.setInicio(p.getInicio());
                p2.setTamaño(p.getTamaño() - particion.getTamaño());
                if (p2.getTamaño() > 0) {
                    getVentanapDinamica().getObjMemoria().agregarParticion(p2);
                    getVentanapDinamica().getObjMemoria().agregarParticionDesocupada(p2);
                }
                eliminarParticion(p);
                valida = true;
            }
        }
        return valida;
    }

    public void eliminarParticion(ParticionPDinamica particion) {
        List<ParticionPDinamica> particionAux = new ArrayList<>();
        List<ParticionPDinamica> particionAux2 = new ArrayList<>();
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesOcupadas().size(); i++) {
            if (particion != getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i)) {
                particionAux.add(getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i));
            }
        }
        getVentanapDinamica().getObjMemoria().actualizarParticionOcupada(particionAux);
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size(); i++) {
            if (particion != getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(i)) {
                particionAux2.add(getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(i));
            }
        }
        getVentanapDinamica().getObjMemoria().actualizarParticionDesOcupada(particionAux2);
    }

    public void reiniciarParticiones() {
        cont = 0;
        getVentanapDinamica().inicializarMemoria();
    }

    public int mejorAjuste(ParticionPDinamica particion) {
        int memory[] = new int[getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size()];
        int posicion = -1;
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size(); i++) {
            memory[i] = getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(i).getTamaño();
        }
        int memoryOrdenada[] = memory.clone();
        memoryOrdenada = ordenamientoBurbuja(memoryOrdenada);
        for (int i = 0; i < memoryOrdenada.length; i++) {
            if (memoryOrdenada[i] >= particion.getTamaño() && memoryOrdenada[i] != -1) {
                System.out.println("Memoria asignada " + memoryOrdenada[i]);
                for (int j = 0; j < memory.length; j++) {
                    if (memoryOrdenada[i] == memory[j]) {
                        memory[j] = -1;
                        posicion = j;
                        break;
                    }
                }
                break;
            }
        }
        return posicion;
    }

    public int[] ordenamientoBurbuja(int[] temp) {
        int aux;
        for (int i = 1; i < temp.length; i++) {
            for (int j = 0; j < temp.length - 1; j++) {
                if (temp[j] > temp[j + 1]) {
                    aux = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = aux;
                }
            }
        }
        return temp;
    }

    public int primerAjuste(ParticionPDinamica particion) {
        int memory[] = new int[getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size()];
        int posicion = -1;
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size(); i++) {
            memory[i] = getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(i).getInicio();
        }
        int memoryOrdenada[] = memory.clone();
        memoryOrdenada = ordenamientoBurbuja(memoryOrdenada);
        for (int i = memoryOrdenada.length - 1; i >= 0; i--) {
            for (int j = 0; j < getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size(); j++) {
                System.out.println("Memoria asignada " + memoryOrdenada[i]);
                if (memoryOrdenada[i] == getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(j).getInicio()) {
                    if (getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(j).getTamaño() >= particion.getTamaño()) {
                        posicion = j;
                        break;
                    }
                }
            }
            if (posicion != -1) {
                break;
            }
        }

        return posicion;
    }

    public int peorAjuste(ParticionPDinamica particion) {
        int memory[] = new int[getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size()];
        int posicion = -1;
        for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size(); i++) {
            memory[i] = getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().get(i).getTamaño();
        }
        int memoryOrdenada[] = memory.clone();
        memoryOrdenada = ordenamientoBurbuja(memoryOrdenada);
        for (int i = memoryOrdenada.length - 1; i >= 0; i--) {
            if (memoryOrdenada[i] >= particion.getTamaño() && memoryOrdenada[i] != -1) {
                System.out.println("Memoria asignada " + memoryOrdenada[i]);
                for (int j = 0; j < memory.length; j++) {
                    if (memoryOrdenada[i] == memory[j]) {
                        memory[j] = -1;
                        posicion = j;
                        break;
                    }
                }
                break;
            }
        }
        return posicion;
    }

    public void compactacion() {
        if (getVentanapDinamica().getObjMemoria().getParticionesDesocupadas().size() > 0) {
            getVentanapDinamica().getObjMemoria().setMemoriadisponible(448);

            List<ParticionPDinamica> particionAux = new ArrayList<>();
            for (int i = 0; i < getVentanapDinamica().getObjMemoria().getParticionesOcupadas().size(); i++) {
                if (getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i).getProceso() != null) {
                    getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i).setInicio(getVentanapDinamica().getObjMemoria().getMemoriadisponible() - getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i).getTamaño());
                    particionAux.add(getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i));
                    getVentanapDinamica().getObjMemoria().setMemoriadisponible(getVentanapDinamica().getObjMemoria().getParticionesOcupadas().get(i).getInicio());
                }
            }
            getVentanapDinamica().getObjMemoria().actualizarParticionOcupada(particionAux);
            getVentanapDinamica().getObjMemoria().actualizarParticionDesOcupada(new ArrayList<>());
            dibujarParticion();
            int espaciodisponible = getVentanapDinamica().getObjMemoria().getMemoriadisponible();
            Graphics g = getVentanapDinamica().getObjMemoria().getGraphics();
            g.setColor(Color.white);
            g.fillRect(1, 1, UtilPDinamica.ANCHO_INICIAL, espaciodisponible-1);
        }
    }

    private String TamañoKib(String seleccion){
        String tamañoParticion="";
        if ("2".equals(seleccion)) {
            tamañoParticion = "64K";
        }
        if ("4".equals(seleccion)) {
            tamañoParticion = "128K";
        }
        if ("8".equals(seleccion)) {
            tamañoParticion = "256K";
        }
        if ("16".equals(seleccion)) {
            tamañoParticion = "512K";
        }
        if ("32".equals(seleccion)) {
            tamañoParticion = "1024K";
        }
        if ("64".equals(seleccion)) {
            tamañoParticion = "2048K";
        }
        if ("128".equals(seleccion)) {
            tamañoParticion = "4096K";
        }
        return tamañoParticion;
    }
    
}
