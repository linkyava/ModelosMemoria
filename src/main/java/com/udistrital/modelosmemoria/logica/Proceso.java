/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica;
import com.udistrital.modelosmemoria.logica.util.UtilProcess;
/**
 *
 * @author Cristian C4
 */
public class Proceso {
    
    private int totalSpaceKiB;
    private String nameProcess;
    private int startPosProcess;
    private int finishPosProcess;

    public Proceso(int totalSpaceKiB, String nameProcess, int previousEndPosProcess) {
        this.totalSpaceKiB = totalSpaceKiB;
        this.nameProcess = nameProcess;
        this.startPosProcess = previousEndPosProcess;
        this.finishPosProcess = getTotalSpaceInPixels()+startPosProcess;
    }
    
    public int getTotalSpaceInPixels(){
        return totalSpaceKiB / UtilProcess.onePxInKiB;
    }

    public int getTotalSpaceKiB() {
        return totalSpaceKiB;
    }

    public String getNameProcess() {
        return nameProcess;
    }

    public int getStartPosProcess() {
        return startPosProcess;
    }

    public int getFinishPosProcess() {
        return finishPosProcess;
    }
    
}
