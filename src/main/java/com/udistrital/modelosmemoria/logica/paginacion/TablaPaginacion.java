/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.logica.paginacion;

import java.util.List;

/**
 *
 * @author Cristian C4
 */
public class TablaPaginacion {
    
    private Marcos marco;
    private ProcesoPaginacion proceso;
    private Pagina pagina;

    public TablaPaginacion(Marcos marco, ProcesoPaginacion proceso, Pagina pagina) {
        this.marco = marco;
        this.proceso = proceso;
        this.pagina = pagina;
    }
    
    public Marcos getMarco() {
        return marco;
    }

    public void setMarco(Marcos marco) {
        this.marco = marco;
    }

    public ProcesoPaginacion getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoPaginacion proceso) {
        this.proceso = proceso;
    }

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }
    
    
}
