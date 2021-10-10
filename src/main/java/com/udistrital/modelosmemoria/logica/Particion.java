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
    
    private final int totalSpaceKiB;
    private final int startPositionY;
    private final int finishPositionY;
    private int freeSpaceKiB;
    private List<Proceso> procesos;

    public Particion(int previousEndPosPartition, int totalSpaceKiB) {
        this.totalSpaceKiB = totalSpaceKiB;
        this.startPositionY = previousEndPosPartition;
        this.finishPositionY = startPositionY + getTotalSpaceInPixels();
        this.freeSpaceKiB = totalSpaceKiB;
        procesos = new ArrayList<>();
    }
    
    public int getTotalSpaceInPixels(){
        return totalSpaceKiB / UtilProcess.onePxInKiB;
    }
    public int getFreeSpaceInPixels(){
        return freeSpaceKiB / UtilProcess.onePxInKiB;
    }
    public int getTotalSpaceKiB() {
        return totalSpaceKiB;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public int getFinishPositionY() {
        return finishPositionY;
    }

    public int getFreeSpaceKiB() {
        return freeSpaceKiB;
    }

    public void setFreeSpaceKiB(int freeSpaceKiB) {
        this.freeSpaceKiB = freeSpaceKiB;
    }

    public List<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<Proceso> procesos) {
        this.procesos = procesos;
    }
    
}
