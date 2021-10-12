/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udistrital.modelosmemoria.presentacion.segmentacion;

import com.udistrital.modelosmemoria.logica.ProcesoMemoria;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author linkyava
 */
public class ModeloSegmentacion {

    private VistaSegmentacion miVentana;
    private boolean esEditablePartcion;
    private JPanel memoria;
    private Memoria objMemoria;
    private String txtParticiones;
    private DefaultTableModel modelTabla;
    private List<ProcesoMemoria> procesos;

    private List<Color> listColors;

    public ModeloSegmentacion() {
        listColors = new ArrayList<Color>();
        listColors.add(Color.red);
        listColors.add(Color.blue);
        listColors.add(Color.black);
        listColors.add(Color.green);
        listColors.add(Color.pink);
        listColors.add(Color.gray);
        listColors.add(Color.yellow);
        listColors.add(Color.orange);
        listColors.add(Color.magenta);
    }

    public VistaSegmentacion getVentana() {
        if (miVentana == null) {
            miVentana = new VistaSegmentacion(this);
        } else {
            miVentana.setMiModelo(this);
        }
        return miVentana;
    }

    public void iniciar() {
        getVentana().setSize(1080, 900);
        getVentana().setVisible(true);
    }

    /**
     *
     * @param pTxtParticiones
     */
    public void iniciarGestionMemoria() {
        String pTxtParticiones = null;

        try {
            pTxtParticiones = this.miVentana.getTxtParticiones().getText();

            if (esEditablePartcion && (pTxtParticiones == null || pTxtParticiones.trim().length() == 0)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido para el número de particiones", "Error", JOptionPane.CANCEL_OPTION);
            } else {

                if (esEditablePartcion) {
                    objMemoria.setCantParticiones(Integer.parseInt(pTxtParticiones));
                    memoria.repaint();
                } else {
                    organizarProcesoMemoria();
                }

            }

        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, "Ingrese solo números para el número de particiones", "Error", JOptionPane.CANCEL_OPTION);
        }
    }

    /**
     * Iniciar memoria con valores por defecto
     *
     * @param pContaniner
     */
    public void iniciarMemoria(JFrame principal, Container pContaniner) {
        pContaniner.setLayout(null);
        memoria = new JPanel();
        {
            objMemoria = new Memoria();
            objMemoria.setEsVariable(!this.esEditablePartcion);
            memoria.add(objMemoria);

        }

        final JScrollPane scroll = new JScrollPane();
        scroll.setBounds(750, 70, 280, 670);
        scroll.setViewportView(memoria);
        pContaniner.add(scroll);

    }

    /**
     * Crear proceso tabla
     */
    public void crearProceso() {
        ProcesoMemoria procesoMemoria = validarProceso();

        if (procesoMemoria != null) {
            llenarTabla(procesoMemoria);
            objMemoria.setProcesos(procesos);
        }
    }

    public void organizarProcesoMemoria() {
        objMemoria.dibujarProceso();
    }

    public ProcesoMemoria validarProceso() {
        ProcesoMemoria procesoMemoria = new ProcesoMemoria();
        String textField = null;

        try {
            textField = getVentana().getTxtDatos().getText();

            if (textField != null && textField.trim().length() > 0) {
                procesoMemoria.setPesoDatos(Integer.parseInt(textField));
                getVentana().getTxtDatos().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor para datos", "Error", JOptionPane.CANCEL_OPTION);
                return null;
            }

            textField = getVentana().getTxtCodigo().getText();

            if (textField != null && textField.trim().length() > 0) {
                procesoMemoria.setPesoCodigo(Integer.parseInt(textField));
                getVentana().getTxtCodigo().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor para codigo", "Error", JOptionPane.CANCEL_OPTION);
                return null;
            }

            textField = getVentana().getTxtPila().getText();

            if (textField != null && textField.trim().length() > 0) {
                procesoMemoria.setPesoPila(Integer.parseInt(textField));
                getVentana().getTxtPila().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor para pila", "Error", JOptionPane.CANCEL_OPTION);
                return null;
            }

            return procesoMemoria;
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, "Ingrese solo valores númericos", "Error", JOptionPane.CANCEL_OPTION);
            return null;
        }
    }

    /**
     * Registra proceso en el Jtable
     *
     * @param p
     */
    public void llenarTabla(ProcesoMemoria p) {
        modelTabla = (DefaultTableModel) miVentana.getjTableProceso().getModel();
        Random rand = new Random();
        Vector row = new Vector();
        p.setPID(rand.nextInt(2000));
        p.setColor(getRandomElement());
        row.add(p.getPID());
        row.add(p.getPesoTotal() + "KiB");
        modelTabla.addRow(row);
        procesos.add(p);
    }

    /**
     * Borra proceso seleccionado del jTablw
     */
    public void borrarRowTabla() {
      if(!miVentana.getjTableProceso().getSelectionModel().isSelectionEmpty()){
        String pid = modelTabla.getValueAt(miVentana.getjTableProceso().getSelectedRow(), 0).toString();

          if (objMemoria.getProcesos() != null && objMemoria.getProcesos().size() > 0) {
              for (ProcesoMemoria proceso : objMemoria.getProcesos()) {
                  if (proceso.getPID() == Integer.valueOf(pid))
                  {
                      objMemoria.getProcesos().remove(proceso);
                  }
              }
          }

          if(objMemoria.isRepaintProcess()){
              objMemoria.dibujarProceso();
          }

          modelTabla.removeRow(miVentana.getjTableProceso().getSelectedRow());
      }
    }

    public boolean isEsEditablePartcion() {
        return esEditablePartcion;
    }

    public void setEsEditablePartcion(boolean esEditablePartcion) {
        this.esEditablePartcion = esEditablePartcion;
    }

    public List<ProcesoMemoria> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcesoMemoria> procesos) {
        this.procesos = procesos;
    }

    // Function select an element base on index
    // and return an element
    public Color getRandomElement() {
        Random rand = new Random();
        return listColors.get(rand.nextInt(listColors.size()));
    }

}
