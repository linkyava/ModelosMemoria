/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica.paginacion;

import com.udistrital.modelosmemoria.logica.util.UtilProcess;
import java.util.List;

/**
 *
 * @author Cristian C4
 */
public class ProcesoPaginacion {
    
    private int tamanoProcesoKiB;
    private String nombreProceso;
    private List<Pagina> paginas;
    

    public ProcesoPaginacion(int tamanoProcesoKiB, String nombreProceso) {
        this.tamanoProcesoKiB = tamanoProcesoKiB;
        this.nombreProceso = nombreProceso;
    }
    
     public int getTotalSpaceInPixels(){
        return tamanoProcesoKiB / UtilProcess.onePxInKiB;
    }
    
    public int getTamanoProcesoKiB() {
        return tamanoProcesoKiB;
    }

    public void setTamanoProcesoKiB(int tamanoProcesoKiB) {
        this.tamanoProcesoKiB = tamanoProcesoKiB;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public List<Pagina> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<Pagina> paginas) {
        this.paginas = paginas;
    }
    
}
