/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion.estatica;

import com.udistrital.modelosmemoria.logica.Particion;
import com.udistrital.modelosmemoria.logica.Proceso;
import com.udistrital.modelosmemoria.logica.util.UtilProcess;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian C4
 */
public class Modelo {

    private Vista miVentana;
    
    private Particion soParticion;
    private Proceso soProceso;
    
    private List<Particion> particiones; // Particiones para dibujar
    private List<Proceso> procesos; // Procesos para dibujar
    
    private DefaultTableModel model; 
    
    public Vista getVentana() {
        if (miVentana == null) {
            miVentana = new Vista(this);
        }
        return miVentana;
    }
    
    public void iniciar(){
        getVentana().setSize(700, 400);
        getVentana().setVisible(true);
        //procesos = new ArrayList<>();
    }
    
    public void dibujarTablaParticion(Graphics g){
        g.setColor (Color.BLACK);//pox, poy, w, h
        g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel,UtilProcess.totalMemorySpaceKiB+2048);// 16MiB -- 256 px
    }
    
    public void crearSistemaOperativo(Graphics g){
        soParticion = new Particion(UtilProcess.posYStart, 2048);
        g.setColor (Color.RED);
        g.fillRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel , soParticion.getTotalSpaceInPixels());
        g.setColor(Color.black);
        g.drawString("S.O", 70, 24);
        g.drawLine(UtilProcess.posXStart, UtilProcess.posYStart+soParticion.getTotalSpaceInPixels(), UtilProcess.posXStart+150, UtilProcess.posYStart+soParticion.getTotalSpaceInPixels());
    }
    
    public void dibujarParticiones(Graphics g){
        System.out.println("Dibujando Particiones");
        //Graphics g = getVentana().getMemoria().getGraphicos();
        g.clearRect(UtilProcess.posXStart+1, UtilProcess.posYStart+soParticion.getTotalSpaceInPixels(),UtilProcess.widthModel-1,(UtilProcess.posXStart+UtilProcess.totalMemorySpacePx));
        g.setColor (Color.BLACK);
        for (int i = 0; i < particiones.size(); i++) {
            Particion p = particiones.get(i);
            System.out.println("Particion - "+ i + " : "+p.getStartPositionY()+" , "+p.getFinishPositionY());
            g.drawRect(UtilProcess.posXStart, p.getStartPositionY(), UtilProcess.widthModel, p.getTotalSpaceInPixels());
            //g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel,16*UtilProcess.oneMib);
        }
    }
        
    public void crearParticiones(){ 
        int espacioDisponiblePx = UtilProcess.totalMemorySpacePx - soParticion.getTotalSpaceInPixels();
        int inicioParticion = soParticion.getFinishPositionY();
        System.out.println("El espacio disponible es de : "+espacioDisponiblePx+ "px -> "
                +espacioDisponiblePx/UtilProcess.oneMib +"MiB -> "+
                espacioDisponiblePx*UtilProcess.onePxInKiB+"KiB");
        particiones = new ArrayList();
        procesos = new ArrayList<>();
        System.out.println("Entro al modelo crear particiones");        
        String itemEspacioParticion = String.valueOf(miVentana.getjCBTamanoParticion().getSelectedItem());
        int espacioCadaParticion = UtilProcess.getKiBItemJCBX(itemEspacioParticion);// en KiB
        int numeroParticiones = UtilProcess.totalMemorySpaceKiB/ espacioCadaParticion;
        System.out.println("El numero de particiones es : "+numeroParticiones+ ", cada particion es de "+ espacioCadaParticion+ "KiB");
        for (int i = 0; i < numeroParticiones; i++) {
            System.out.println("Inicio de cada Particion" + inicioParticion);
            particiones.add(new Particion(inicioParticion, espacioCadaParticion));
            inicioParticion = inicioParticion+(espacioCadaParticion/UtilProcess.onePxInKiB); // Divide en 64 para dar en pixeles
        }
    }
   //Deberia dibujar el proceso con la lista de la clase  
    public void dibujarProceso(Proceso p, Graphics g){
        System.out.println("Dibujando Procesos");
        //Graphics g = getVentana().getMemoria().getGraphicos();
        g.setColor (Color.BLUE);
        g.fillRect(UtilProcess.posXStart+1, p.getStartPosProcess()+1, UtilProcess.widthModel, p.getTotalSpaceInPixels()-1);//x y w h
        g.setColor(Color.BLACK);
        g.drawLine(UtilProcess.posXStart, p.getFinishPosProcess(), UtilProcess.posXStart+UtilProcess.widthModel, p.getFinishPosProcess());
        g.setColor(Color.WHITE);
        g.drawString("Proceso : "+p.getNameProcess(),UtilProcess.posXStart+5 , p.getStartPosProcess()+10);
    }
    
    public void eliminarDibujoProceso(Proceso p){
        procesos.remove(p);
        System.out.println("Dibujando Procesos");
        Graphics g = getVentana().getMemoria().getGraphics();
        g.setColor (Color.BLUE);
        g.clearRect(UtilProcess.posXStart+1, p.getStartPosProcess()+1, UtilProcess.widthModel-1, p.getTotalSpaceInPixels()-1);//x y w h    
    }
    
    // pone el proceso que se crea dentro del arreglo de particiones
    public String putProcessInMemory(){
        String itemProcesosMemoria = String.valueOf(miVentana.getjCBTamanoProceso().getSelectedItem());
        int memory = UtilProcess.getKiBItemJCBX(itemProcesosMemoria);
        String nameProcess = miVentana.getTxtNombre().getText();
        for (int i = 0; i < particiones.size(); i++) {
            if (verifyPartitionCapacity(i, memory, nameProcess)){
                List<Proceso> procesosParticion = particiones.get(i).getProcesos();
                //dibujarProceso(procesosParticion.get(procesosParticion.size()-1));
                llenarTablaProcesos(procesos.get(procesos.size()-1));
                return "Proceso agregado a la memoria";
            }
        }
        return null;
    }
    public void llenarTablaProcesos(Proceso p){
        model = (DefaultTableModel) miVentana.getjTable2().getModel();
        Vector row = new Vector();    
        row.add(p.getNameProcess());
        row.add((p.getTotalSpaceKiB())+ "KiB");
        model.addRow(row);
    }
    public void borrarColumnaTabla(){
        model.removeRow(miVentana.getjTable2().getSelectedRow());
    }
    
    public boolean verifyPartitionCapacity(int numPartition, int memoryProcess, String nameProcess){
        List<Proceso> procesosParticion = new ArrayList<>();
        int freeSpacePartition = particiones.get(numPartition).getFreeSpaceKiB();
        if( freeSpacePartition >= memoryProcess ){
           int previousAddrs;
           procesosParticion = particiones.get(numPartition).getProcesos();
           if(procesosParticion.isEmpty()){
               previousAddrs = particiones.get(numPartition).getStartPositionY();
           }else{
               previousAddrs = procesosParticion.get(procesosParticion.size()-1).getFinishPosProcess();
           }
           // registrar ese proceso en esa particion
           Proceso nuevoProceso = new Proceso(memoryProcess, nameProcess, previousAddrs);
           procesosParticion.add(nuevoProceso);
           procesos.add(nuevoProceso);
           particiones.get(numPartition).setFreeSpaceKiB(freeSpacePartition-nuevoProceso.getTotalSpaceKiB());
           particiones.get(numPartition).setProcesos(procesosParticion);
           return true;
       }else{
           System.out.println("El proceso no cabe en esta partición");
           return false;
       }
    }
    
    public void borrarProceso(){
        String nombreProcesoSeleccionado = model.getValueAt(miVentana.getjTable2().getSelectedRow(), 0).toString();//fila - columna
        System.out.println("Proceso a eliminar: "+nombreProcesoSeleccionado);
        for (Particion particion : particiones) {
            for (int i = 0; i < particion.getProcesos().size(); i++) {
                if( nombreProcesoSeleccionado.equals(particion.getProcesos().get(i).getNameProcess())){
                    int freeSpacePartition = particion.getFreeSpaceKiB();
                    particion.setFreeSpaceKiB(freeSpacePartition+particion.getProcesos().get(i).getTotalSpaceKiB());
                    eliminarDibujoProceso(particion.getProcesos().get(i));
                    particion.getProcesos().remove(i);   
                    borrarColumnaTabla();
                }
            }
        }
    }

    public List<Particion> getParticiones() {
        return particiones;
    }

    public List<Proceso> getProcesos() {
        return procesos;
    }
    
}
