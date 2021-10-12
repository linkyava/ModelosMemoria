/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacion.paginacion;

import com.udistrital.modelosmemoria.logica.Particion;
import com.udistrital.modelosmemoria.logica.paginacion.Marcos;
import com.udistrital.modelosmemoria.logica.paginacion.Pagina;
import com.udistrital.modelosmemoria.logica.paginacion.ProcesoPaginacion;
import com.udistrital.modelosmemoria.logica.paginacion.TablaPaginacion;
import com.udistrital.modelosmemoria.logica.util.UtilProcess;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian C4
 */
public class ModeloPaginacion {

    private VistaPaginacion miVentana;

    private Particion soParticion;

    private List<Marcos> marcos;
    private List<ProcesoPaginacion> procesos;
    private List<TablaPaginacion> tablaAtenidos;
    private List<ProcesoPaginacion> tablaEspera;

    private DefaultTableModel model;
    private int espacioCadaMarco;
    private int numeroMarcos;

    public VistaPaginacion getVentana() {
        procesos = new ArrayList<>();
        if (miVentana == null) {
            miVentana = new VistaPaginacion(this);
        }
        return miVentana;
    }

    public void iniciar() {
        getVentana().setSize(700, 400);
        getVentana().setVisible(true);
        tablaEspera = new ArrayList<>();
        tablaAtenidos = new ArrayList<>();
        //procesos = new ArrayList<>();
    }

    public void dibujarTablaParticion(Graphics g) {
        g.setColor(Color.BLACK);//pox, poy, w, h
        g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart, UtilProcess.widthModel, UtilProcess.totalMemorySpaceKiB + 2048);// 16MiB -- 256 px
    }

    public void crearSistemaOperativo(Graphics g) {
        soParticion = new Particion(UtilProcess.posYStart, 2048);
        g.setColor(Color.RED);
        g.fillRect(UtilProcess.posXStart, UtilProcess.posYStart, UtilProcess.widthModel, soParticion.getTotalSpaceInPixels());
        g.setColor(Color.black);
        g.drawString("S.O", 70, 24);
        g.drawLine(UtilProcess.posXStart, UtilProcess.posYStart + soParticion.getTotalSpaceInPixels(), UtilProcess.posXStart + 150, UtilProcess.posYStart + soParticion.getTotalSpaceInPixels());
    }

    public void dibujarMarcos(Graphics g) {
        System.out.println("Dibujando Marcos en Memoria Fisica");
        //Graphics g = getVentana().getMemoria().getGraphicos();
        g.clearRect(UtilProcess.posXStart + 1, UtilProcess.posYStart + soParticion.getTotalSpaceInPixels(), UtilProcess.widthModel - 1, (UtilProcess.posXStart + UtilProcess.totalMemorySpacePx));
        g.setColor(Color.BLACK);
        for (int i = 0; i < marcos.size(); i++) {
            Marcos m = marcos.get(i);
            System.out.println("Particion - " + i + " : " + m.getInicioPosMarco() + " , " + m.getFinPosMarco());
            g.drawRect(UtilProcess.posXStart, m.getInicioPosMarco(), UtilProcess.widthModel, m.getTamanoMarco());
            //g.drawRect(UtilProcess.posXStart, UtilProcess.posYStart,UtilProcess.widthModel,16*UtilProcess.oneMib);
        }
    }

    public void crearMarcos() {
        int espacioDisponiblePx = UtilProcess.totalMemorySpacePx - soParticion.getTotalSpaceInPixels();
        int inicioParticion = soParticion.getFinishPositionY();
        System.out.println("El espacio disponible es de : " + espacioDisponiblePx + "px -> "
                + espacioDisponiblePx / UtilProcess.oneMib + "MiB -> "
                + espacioDisponiblePx * UtilProcess.onePxInKiB + "KiB");
        marcos = new ArrayList();
        System.out.println("Creando Marcos");
        String itemEspacioMarco = String.valueOf(miVentana.getjCBTamanoParticion().getSelectedItem());
        espacioCadaMarco = UtilProcess.getKiBItemJCBX(itemEspacioMarco);// en KiB
        numeroMarcos = UtilProcess.totalMemorySpaceKiB / espacioCadaMarco;
        System.out.println("El numero de marco es : " + numeroMarcos + ", cada particion es de " + espacioCadaMarco + "KiB");
        for (int i = 0; i < numeroMarcos; i++) {
            System.out.println("Inicio de cada Particion" + inicioParticion);
            marcos.add(new Marcos(i, inicioParticion, espacioCadaMarco));
            inicioParticion = inicioParticion + (espacioCadaMarco / UtilProcess.onePxInKiB); // Divide en 64 para dar en pixeles
        }
    }
    //Deberia dibujar el proceso con la lista de la clase  

    public void dibujarProceso(TablaPaginacion p, Graphics g) {
        System.out.println("Dibujando Procesos");
        //Graphics g = getVentana().getMemoria().getGraphicos();
        g.setColor(Color.BLUE);
        if (p.getProceso().getTotalSpaceInPixels() < p.getMarco().getTotalSpaceInPixels()) {
            g.fillRect(UtilProcess.posXStart + 1, p.getMarco().getInicioPosMarco() + 1, UtilProcess.widthModel, p.getProceso().getTotalSpaceInPixels() - 1);//x y w h
        } else {
            g.fillRect(UtilProcess.posXStart + 1, p.getMarco().getInicioPosMarco() + 1, UtilProcess.widthModel, p.getMarco().getTotalSpaceInPixels() - 1);//x y w h
        }

        g.setColor(Color.BLACK);
        g.drawLine(UtilProcess.posXStart, p.getMarco().getFinPosMarco(), UtilProcess.posXStart + UtilProcess.widthModel, p.getMarco().getFinPosMarco());
        g.setColor(Color.WHITE);
        g.drawString("Proceso : " + p.getProceso().getNombreProceso() + " Pagina : " + p.getPagina().getNumeroPagina(), UtilProcess.posXStart + 5, p.getMarco().getInicioPosMarco() + 10);
    }

    public void crearProceso() {
        String nombreProcesos = getVentana().getTxtNombre().getText();
        int tamanoProceso = UtilProcess.getKiBItemJCBX(getVentana().getjCBTamanoProceso().getSelectedItem().toString());
        ProcesoPaginacion proceso = new ProcesoPaginacion(tamanoProceso, nombreProcesos);
        System.out.println("Creando proceso de Tamaño ->" + tamanoProceso);
        crearPaginasProceso(proceso);
        asignarProcesoMemoria(proceso);
        procesos.add(proceso);
    }

    public void crearPaginasProceso(ProcesoPaginacion p) {
        //numeroMarcos*
        int numeroPaginas = (int) Math.ceil((double) p.getTamanoProcesoKiB() / (double) espacioCadaMarco);
        System.out.println("Creando paginas por proceso : " + numeroPaginas + " paginas");
        List<Pagina> paginas = new ArrayList<>();
        for (int i = 0; i < numeroPaginas; i++) {
            paginas.add(new Pagina(espacioCadaMarco, i));
        }
        p.setPaginas(paginas);
    }

    // pone el proceso que se crea dentro del arreglo de particiones
    public void llenarTablaPaginacionAtendida(TablaPaginacion t) {
        model = (DefaultTableModel) miVentana.getjTable2().getModel();
        Vector row = new Vector();
        row.add(t.getProceso().getNombreProceso());
        row.add(t.getPagina().getNumeroPagina());
        row.add(t.getMarco().getNumeroMarco());
        model.addRow(row);
    }

    public void asignarProcesoMemoria(ProcesoPaginacion p) {
        //asignar el proceso entrante
        if (verificarEspacioDisponible(p.getTamanoProcesoKiB())) {
            for (int i = 0; i < marcos.size(); i++) {
                if (!marcos.get(i).getEstaOcupado()) {
                    for (int j = i; j < p.getPaginas().size() + i; j++) {
                        if (!marcos.get(j).getEstaOcupado()) {
                            marcos.get(j).setEstaOcupado(true);
                            TablaPaginacion tabPag = new TablaPaginacion(marcos.get(j), p, p.getPaginas().get(j - i));
                            tablaAtenidos.add(tabPag);
                            llenarTablaPaginacionAtendida(tabPag);
                        }else {
                            i++;
                        }
                    }
                    break;
                }
            }
        } else {
            tablaEspera.add(p);
            JOptionPane.showMessageDialog(miVentana, "El proceso no puede ser atendido, se pondra en espera", "Proceso en espera", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Verifica si la suma de los marcos disponibles pueden almacenar el proceso
     *
     * @return true si se puede almacenar false de lo contrario
     */
    public boolean verificarEspacioDisponible(int tamanoProcesos) {
        int espacioDisponibleMemoria = 0;
        for (int i = 0; i < marcos.size(); i++) {
            if (!marcos.get(i).getEstaOcupado()) {
                espacioDisponibleMemoria += marcos.get(i).getTamanoMarco();
            }
        }
        if (espacioDisponibleMemoria >= tamanoProcesos) {
            return true;
        }
        return false;
    }

    public void borrarColumnaTabla() {
        model.removeRow(miVentana.getjTable2().getSelectedRow());
    }

    public void borrarRegistroTabPag() {
        String nombreProcesoSeleccionado = model.getValueAt(miVentana.getjTable2().getSelectedRow(), 0).toString();
        System.out.println("Proceso a eliminar: " + nombreProcesoSeleccionado);
        for (int i = 0; i < tablaAtenidos.size(); i++) {
            if (nombreProcesoSeleccionado.equals(tablaAtenidos.get(i).getProceso().getNombreProceso())) {
                tablaAtenidos.get(i).getMarco().setEstaOcupado(false);
                tablaAtenidos.remove(i);
                eliminarDibujoProceso(tablaAtenidos.get(i));
                borrarColumnaTabla();
            }
        }
        borrarListaProcesos(nombreProcesoSeleccionado);
    }
    
    public void borrarListaProcesos(String nombreProceso){
        for (int i = 0; i < procesos.size(); i++) {
            ProcesoPaginacion proceso = procesos.get(i);
            if(procesos.get(i).getNombreProceso().equals(nombreProceso)){
                procesos.remove(procesos.get(i));
            }
                
        }
    }
    
    public void eliminarDibujoProceso(TablaPaginacion p) {
        System.out.println("Dibujando Procesos");
        Graphics g = getVentana().getMemoria().getGraphics();
        g.setColor(Color.BLUE);
        g.clearRect(UtilProcess.posXStart + 1, p.getMarco().getInicioPosMarco() + 1, UtilProcess.widthModel - 1, p.getMarco().getTotalSpaceInPixels() - 1);//x y w h    
    }

    public List<Marcos> getMarcos() {
        return marcos;
    }

    public void setMarcos(List<Marcos> marcos) {
        this.marcos = marcos;
    }

    public List<ProcesoPaginacion> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcesoPaginacion> procesos) {
        this.procesos = procesos;
    }

    public List<TablaPaginacion> getTablaAtenidos() {
        return tablaAtenidos;
    }

    public List<ProcesoPaginacion> getTablaEspera() {
        return tablaEspera;
    }

}
