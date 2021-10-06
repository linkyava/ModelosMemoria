/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion;

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
    private List<Particion> particiones;
    private List<Proceso> procesos;
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
        procesos = new ArrayList<>();
    }
    
    public void dibujarTablaParticion(Graphics g){
        g.setColor (Color.BLACK);//pox, poy, w,h
        g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel,16*UtilProcess.oneMib);// 16MiB -- 256 px
    }
    
    public void crearSistemaOperativo(Graphics g){
        soParticion = new Particion(UtilProcess.posYStart, 2);// 2MiB -- 32 pxb (pos y = 50 +256 - 32)
        g.setColor (Color.RED);
        g.fillRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel , soParticion.getTotalSpaceInPixels());
        g.setColor(Color.black);
        g.drawString("S.O", 570, 74);
        g.drawLine(UtilProcess.posXStart, UtilProcess.posYStart+(2*UtilProcess.oneMib), UtilProcess.posXStart+150, UtilProcess.posYStart+(2*UtilProcess.oneMib));
    }
    
    public void dibujarParticiones(){
        System.out.println("Dibujando Particiones");
        Graphics g = getVentana().getGraphics();
        g.clearRect(UtilProcess.posXStart+1, UtilProcess.posYStart+32,UtilProcess.widthModel-1,(16*UtilProcess.oneMib));
        g.setColor (Color.BLACK);
        for (int i = 0; i < particiones.size(); i++) {
            Particion p = particiones.get(i);
            System.out.println("Particion - "+ i + " : "+p.getStartPositionY()+" , "+p.getFinishPositionY());
            g.drawRect(UtilProcess.posXStart, p.getStartPositionY(), UtilProcess.widthModel, p.getTotalSpaceInPixels());
            //g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel,16*UtilProcess.oneMib);
        }
    }
        
    public void crearParticiones(){ 
        int espacioDisponible = UtilProcess.totalMemorySpacePx - soParticion.getTotalSpaceInPixels();
        int inicioParticion = soParticion.getFinishPositionY();
        System.out.println("El espacio disponible es de : "+espacioDisponible+ "px -> "+espacioDisponible/16 +"MiB");
        particiones = new ArrayList();
        System.out.println("Entro al modelo crear particiones");        
        int numeroParticiones = Integer.parseInt(miVentana.getTxtParticiones().getText());
        int espacioCadaParticion = espacioDisponible/numeroParticiones;
        System.out.println("El numero de particiones es : "+numeroParticiones+ ", cada particion es de "+ espacioCadaParticion+ "px");
        for (int i = 0; i < numeroParticiones; i++) {
            System.out.println("Inicio de cada Particion" + inicioParticion);
            particiones.add(new Particion(inicioParticion, espacioCadaParticion/16));
            inicioParticion = inicioParticion+espacioCadaParticion;
        }
    }
    
    public void dibujarProceso(Proceso p){
        System.out.println("Dibujando Procesos");
        Graphics g = getVentana().getGraphics();
        g.setColor (Color.BLUE);
        g.fillRect(UtilProcess.posXStart+1, p.getStartPosProcess()+1, UtilProcess.widthModel, p.getTotalSpaceInPixels()-1);//x y w h
        g.setColor(Color.WHITE);
        g.drawLine(UtilProcess.posXStart, p.getFinishPosProcess(), UtilProcess.posXStart+UtilProcess.widthModel, p.getFinishPosProcess());
        g.drawString("Proceso : "+p.getNameProcess(),UtilProcess.posXStart+5 , p.getStartPosProcess()+10);
    }
    
    public void eliminarDibujoProceso(Proceso p){
        System.out.println("Dibujando Procesos");
        Graphics g = getVentana().getGraphics();
        g.setColor (Color.BLUE);
        g.clearRect(UtilProcess.posXStart+1, p.getStartPosProcess()+1, UtilProcess.widthModel-1, p.getTotalSpaceInPixels()-1);//x y w h    
    }
    
    public String putProcessInMemory(){
        int memory = Integer.parseInt(miVentana.getTxtMemoria().getText());
        String nameProcess = miVentana.getTxtNombre().getText();
        for (int i = 0; i < particiones.size(); i++) {
            if (verifyPartitionCapacity(i, memory, nameProcess)){
                procesos = particiones.get(i).getProcesos();
                dibujarProceso(procesos.get(procesos.size()-1));
                llenarTabla(procesos.get(procesos.size()-1));
                return "Proceso agregado a la memoria";
            }
        }
        return null;
    }
    public void llenarTabla(Proceso p){
        model = (DefaultTableModel) miVentana.getjTable2().getModel();
        Vector row = new Vector();    
        row.add(p.getNameProcess());
        row.add((p.getTotalSpaceMiB()*1024)+ "KiB");
        model.addRow(row);
    }
    public void borrarColumnaTabla(){
        model.removeRow(miVentana.getjTable2().getSelectedRow());
    }
    
    public boolean verifyPartitionCapacity(int numPartition, int memoryProcess, String nameProcess){
        int freeSpacePartition = particiones.get(numPartition).getFreeSpaceMiB();
        if( freeSpacePartition >= memoryProcess ){
           int previousAddrs;
           procesos = particiones.get(numPartition).getProcesos();
           if(procesos.isEmpty()){
               previousAddrs = particiones.get(numPartition).getStartPositionY();
           }else{
               previousAddrs = procesos.get(procesos.size()-1).getFinishPosProcess();
           }
           // registrar ese proceso en esa particion
           Proceso nuevoProceso = new Proceso(memoryProcess, nameProcess, previousAddrs);
           procesos.add(nuevoProceso);
           particiones.get(numPartition).setFreeSpaceMiB(freeSpacePartition-nuevoProceso.getTotalSpaceMiB());
           particiones.get(numPartition).setProcesos(procesos);
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
                    int freeSpacePartition = particion.getFreeSpaceMiB();
                    particion.setFreeSpaceMiB(freeSpacePartition+particion.getProcesos().get(i).getTotalSpaceMiB());
                    eliminarDibujoProceso(particion.getProcesos().get(i));
                    particion.getProcesos().remove(i);   
                    borrarColumnaTabla();
                }
            }
        }
    }   
}
