/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacion.segmentacion;

import com.udistrital.modelosmemoria.logica.ParticionMemoria;
import com.udistrital.modelosmemoria.logica.ProcesoMemoria;
import com.udistrital.modelosmemoria.logica.util.ConvertDecimalToHexadecimal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ToolTipManager;

/**
 *
 * @author linkyava
 */
public class Memoria extends javax.swing.JPanel {
    
    private static final int MAX_CAPACITY = 14680064;
    private static final int MEM_DISP_DISPLAY = 784;
    private List<ParticionMemoria> particiones;
    private List<Shape> listShape;
    private HashMap<String, ParticionMemoria> mapParticiones;
    private int cantParticiones;
    private int memWidth;
    private int memHeigth;
    private int memX;
    private int memY;
    private boolean esVariable;
    private List<ProcesoMemoria> procesos;
    private boolean repaintProcess;
    
    public Memoria() {
        super();
        initGUI();
    }

    private void initGUI() {
       inicializarVariables();
       this.setLayout(null);
       this.setPreferredSize(new java.awt.Dimension(280, 900));
    }
    
    /**
     * 
     * @param g 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rectangle = null;
        
        g.setColor(Color.BLACK);
        g.drawRect(memX, memY, memWidth, memHeigth);
        /**
         * Draw S.O always 2MB
         */
        g.setColor(Color.red);
        g.fillRect(memX, MEM_DISP_DISPLAY, memWidth, 112);
        
        g.setColor(Color.WHITE);
        g.drawString("S.O", 120, 840);
        /**
         * 
         */
        
        if(!esVariable){
            if(cantParticiones > 0){
                generarPintarParticiones(g);

                if(listShape!=null && listShape.size()>0){
                  for (Shape shape : listShape) {
                    g2d.draw(shape);
                  }
                }
            }
        }else{
            
            generarPtarParticionesVariables(g);

            if(listShape!=null && listShape.size()>0){
              for (Shape shape : listShape) {
                g2d.draw(shape);
              }
            }
        }
       
       if(repaintProcess){
          ubicarProcesosEnLaMemoria(g);
       }
       
    
    }
    
    public void ubicarProcesosEnLaMemoria(Graphics g) {
       List<ParticionMemoria> particionesRepaint = new ArrayList<ParticionMemoria>();
       particionesRepaint.addAll(particiones);
       int totalPart;
       
        if (procesos != null && !procesos.isEmpty()) {
            
          for (ProcesoMemoria procesoMem : procesos) {

                for (ParticionMemoria particion : particionesRepaint) {

                    if (!procesoMem.isUbicado() && !particion.isLlena() && particion.getLibreKbs() > 0 && procesoMem.getPesoTotal() < particion.getLibreKbs()) {
                        totalPart = particion.getyAnt() - particion.getyPrint();
                        g.setColor(procesoMem.getColor());
                        g.fillRect(memX,  particion.getyPrint(), memWidth, totalPart);
                        particion.setLibreKbs(particion.getCapacidadKbs() - procesoMem.getPesoTotal());
                        g.setColor(Color.WHITE);
                        g.drawLine(memX, particion.getyPrint(), memWidth, particion.getyPrint());
                        g.drawString("Proceso PID: "+procesoMem.getPID(),memX + 80, (particion.getyPrint()+(totalPart/2)));
                        particion.setLlena(true);
                        break;
                    }

                }
            }
        }
    }
    
    /**
     * 
     * @param e
     * @return 
     */
    @Override
     public String getToolTipText(MouseEvent e) { 
         
         for (ParticionMemoria particion: particiones) {
             
             if(particion.getyAnt()>= e.getY() && particion.getyPrint()<= e.getY()){       
                 return particion.toString();
             }
         }
         
         return "";
     }
        
    /**
     * Calcula los tamaños para pintar la memoria graficamente
     * calcula las direcciones iniciales y finales.
     * @param g 
     */
    public void generarPintarParticiones(Graphics g){
        int posTempY = MEM_DISP_DISPLAY;
        int memFinal = MAX_CAPACITY;
        int memInicial = 0;
        int antPosY = 0;
        int tamPrintParticion = 0;
        int tamMemParticion = 0;
        
        particiones = new ArrayList<ParticionMemoria>();
        listShape = new ArrayList<>();
        mapParticiones = new HashMap<String, ParticionMemoria>();
        
        g.setColor(Color.BLACK);
        
        tamPrintParticion = calcularTamDibujoParticion();
        tamMemParticion = calcularTamMemoriaParticion();
        
        boolean calcularTamEquivalente = esModuloDeCantParticiones();
        
        for(int  i = 1; i <= cantParticiones; i ++){
            
           ParticionMemoria particion = new ParticionMemoria();
           particion.setCapacidadKbs(tamMemParticion/1024);
           particion.setLibreKbs(particion.getCapacidadKbs());
           
           antPosY = posTempY;
           posTempY  =  posTempY - tamPrintParticion;
           
           if(i==cantParticiones && memY != posTempY){
               particion.setyPrint(memY);
           }else{
               if(calcularTamEquivalente && esModuloDeDos(i)){
                   posTempY = posTempY - 1;
               }
               particion.setyPrint(posTempY);
           }
           
           memInicial = memFinal - 1;
           memFinal = memInicial - tamMemParticion;
           
           particion.setDireccionInicialByte(memInicial);
           particion.setDireccionFinalByte(memFinal);
           particion.setDireccionInicialHexa(ConvertDecimalToHexadecimal.decimalToHexadecimal(memInicial));
           particion.setDireccionFinalHexa(ConvertDecimalToHexadecimal.decimalToHexadecimal(memFinal));
           particion.setyAnt(antPosY - 1);
           
           Rectangle rectangle = new Rectangle(memX, particion.getyPrint() , memWidth, particion.getyPrint());
           
           particiones.add(particion);
           listShape.add(rectangle);
           mapParticiones.put(rectangle.toString(), particion);
           
           System.out.println(particion.getyAnt()  + " - " + particion.getyPrint() + " - " +particion.getDireccionInicialHexa() + " - "+ particion.getDireccionFinalHexa());
        }
        
        ToolTipManager.sharedInstance().registerComponent(this);
        
    }
    
    /**
     * Generar particiones variables 
     * @param g 
     */
    public void generarPtarParticionesVariables(Graphics g){
        int posTempY = MEM_DISP_DISPLAY;
        int memFinal = MAX_CAPACITY;
        int memInicial = 0;
        int antPosY = 0;
        int tamPrintParticion = 0;
        int tamMemParticion = 0;
        boolean calcularTamEquivalente = false;
        cantParticiones = 37;
        particiones = new ArrayList<ParticionMemoria>();
        listShape = new ArrayList<>();
        mapParticiones = new HashMap<String, ParticionMemoria>();
        
        g.setColor(Color.BLACK);
        
       
        
        for(int  i = 1; i <= cantParticiones; i ++){
            
           tamPrintParticion = calcularTamDibujoPartVariable(i);
           tamMemParticion = calcularTamMemoriaPartVariable(i);
           calcularTamEquivalente = esModuloDeCantParticionesVariable(i);
            
           ParticionMemoria particion = new ParticionMemoria();
           particion.setCapacidadKbs(tamMemParticion/1024);
           particion.setLibreKbs(particion.getCapacidadKbs());
           
           antPosY = posTempY;
           posTempY  =  posTempY - tamPrintParticion;
           
           if(i==cantParticiones && memY != posTempY){
               particion.setyPrint(memY);
           }else{
               if(calcularTamEquivalente && esModuloDeDos(i)){
                   posTempY = posTempY - 1;
               }
               particion.setyPrint(posTempY);
           }
           
           memInicial = memFinal - 1;
           memFinal = memInicial - tamMemParticion;
           
           particion.setDireccionInicialByte(memInicial);
           particion.setDireccionFinalByte(memFinal);
           particion.setDireccionInicialHexa(ConvertDecimalToHexadecimal.decimalToHexadecimal(memInicial));
           particion.setDireccionFinalHexa(ConvertDecimalToHexadecimal.decimalToHexadecimal(memFinal));
           particion.setyAnt(antPosY - 1);
           
           Rectangle rectangle = new Rectangle(memX, particion.getyPrint() , memWidth, particion.getyPrint());
           
           particiones.add(particion);
           listShape.add(rectangle);
           mapParticiones.put(rectangle.toString(), particion);
           
           System.out.println(particion.getyAnt()  + " - " + particion.getyPrint() + " - " +particion.getDireccionInicialHexa() + " - "+ particion.getDireccionFinalHexa());
        }
        
        ToolTipManager.sharedInstance().registerComponent(this);
    }
    
    /**
     * 
     * @param i
     * @return 
     */
    public int calcularTamDibujoPartVariable(int i){
        int tamDibujo = 0;
        
        if(i>=1 && i<=5){
            tamDibujo = MEM_DISP_DISPLAY/14;
        }else if(i>=6 && i<=13){
            tamDibujo = MEM_DISP_DISPLAY/28;
        }else if(i>=14 && i<=29){
            tamDibujo = MEM_DISP_DISPLAY/56;
        }else if(i>=30 && i<=37){
            tamDibujo = MEM_DISP_DISPLAY/112;
        }
        
        return tamDibujo;
        
    }
    
    /**
     * 
     * @param i
     * @return 
     */
    public int calcularTamMemoriaPartVariable(int i){
        int tamMemoria = 0;
        
        if(i>=1 && i<=5){
            tamMemoria = MAX_CAPACITY/14;
        }else if(i>=6 && i<=13){
            tamMemoria = MAX_CAPACITY/28;
        }else if(i>=14 && i<=29){
            tamMemoria = MAX_CAPACITY/56;
        }else if(i>=30 && i<=37){
            tamMemoria = MAX_CAPACITY/112;
        }
         
        return tamMemoria;
    }
    
    public boolean esModuloDeCantParticionesVariable(int i){
        boolean esModulo = false;
        
        if(i>=1 && i<=5){
            esModulo = (MEM_DISP_DISPLAY  % 14) != 0;
        }else if(i>=6 && i<=13){
            esModulo = (MEM_DISP_DISPLAY  % 28) != 0;
        }else if(i>=14 && i<=29){
            esModulo = (MEM_DISP_DISPLAY  % 56) != 0;
        }else if(i>=30 && i<=37){
            esModulo = (MEM_DISP_DISPLAY  % 112) != 0;
        }
        
        return esModulo;
    }
    
    
    public int calcularTamDibujoParticion(){
        return MEM_DISP_DISPLAY / cantParticiones;
    }
    
    public boolean esModuloDeCantParticiones(){
        return (MEM_DISP_DISPLAY  % cantParticiones) != 0;
    }
    
    public boolean esModuloDeDos(int val){
        return (val % 2) == 0;
    }
        
    public int calcularTamMemoriaParticion(){
        return MAX_CAPACITY / cantParticiones;
    }
    
    public void inicializarVariables(){
        memHeigth = 896;
        memWidth = 250;
        memX = 0;
        memY = 0;
       
    }
    
    
   public void dibujarProceso(){
        System.out.println("Dibujando Procesos");
        repaintProcess = true;
        this.repaint();
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

    public int getCantParticiones() {
        return cantParticiones;
    }

    public void setCantParticiones(int cantParticiones) {
        this.cantParticiones = cantParticiones;
    }

    public List<ParticionMemoria> getParticiones() {
        return particiones;
    }

    public void setParticiones(List<ParticionMemoria> particiones) {
        this.particiones = particiones;
    }

    public boolean isEsVariable() {
        return esVariable;
    }

    public void setEsVariable(boolean esVariable) {
        this.esVariable = esVariable;
    }

    public List<ProcesoMemoria> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcesoMemoria> procesos) {
        this.procesos = procesos;
    }

    public boolean isRepaintProcess() {
        return repaintProcess;
    }

    
}
