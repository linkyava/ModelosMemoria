/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica;

import com.udistrital.modelosmemoria.logica.util.UtilProcess;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian C4
 */
public class Particion {
    
    private final int totalSpaceMiB;
    private final int startPositionY;
    private final int finishPositionY;
    private int freeSpaceMiB;
    private List<Proceso> procesos;

    public Particion(int previousEndPosPartition, int totalSpaceMiB) {
        this.totalSpaceMiB = totalSpaceMiB;
        this.startPositionY = previousEndPosPartition;
        this.finishPositionY = startPositionY + getTotalSpaceInPixels();
        this.freeSpaceMiB = totalSpaceMiB;
        procesos = new ArrayList<>();
    }
    
    public int getTotalSpaceInPixels(){
        return totalSpaceMiB * UtilProcess.oneMib;
    }
    public int getFreeSpaceInPixels(){
        return freeSpaceMiB * UtilProcess.oneMib;
    }
    public int getTotalSpaceMiB() {
        return totalSpaceMiB;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public int getFinishPositionY() {
        return finishPositionY;
    }

    public int getFreeSpaceMiB() {
        return freeSpaceMiB;
    }

    public void setFreeSpaceMiB(int freeSpaceMiB) {
        this.freeSpaceMiB = freeSpaceMiB;
    }

    public List<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<Proceso> procesos) {
        this.procesos = procesos;
    }
    
}
