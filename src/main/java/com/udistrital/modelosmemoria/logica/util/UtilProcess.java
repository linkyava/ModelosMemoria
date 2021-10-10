/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica.util;

/**
 *
 * @author Cristian C4
 */
public class UtilProcess {

    public static final int posXStart = 10;
    public static int posYStart = 10;
    public static int widthModel = 150;
    public static int oneMib = 16;
    public static int onePxInKiB = 16;
    public static int totalMemorySpacePx = 1024;
    public static int totalMemorySpaceKiB = 14336;
    
    public static int getKiBItemJCBX(String item){
        switch (item){
            case "128 KiB":
                return 128;
            case "192 KiB":
                return 192;
            case "256 KiB":
                return 256;
            case "512 KiB":
                return 512;
            case "1 MiB":
                return 1024;
            case "2 MiB":
                return 2048;
            case "3.5 MiB":
                return 3584;
            case "7 MiB":
                return 7168;
            case "14 MiB":
                return 14336;
            default: 
                return 14336;
        }
    }
}
