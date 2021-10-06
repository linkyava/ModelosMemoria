/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.main;

import com.udistrital.modelosmemoria.presentacionPrincipal.ModeloPrincipal;

/**
 *
 * @author Cristian C4
 */
public class Launcher {
    
    private ModeloPrincipal miApp;
    
    public Launcher(){
        miApp = new ModeloPrincipal();
        miApp.iniciar();
    }
    
    public static void main(String[] args) {
        new Launcher();
    }
    
}
