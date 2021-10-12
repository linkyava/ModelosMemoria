package com.udistrital.modelosmemoria.logica;

import java.awt.Color;

public class PaginaProceso 
{
	int numeroPagina,IdProceso,peso;
	public Color color;

	public PaginaProceso(int numeroPagina,int IdProceso,Color color)
        {
		this.numeroPagina=numeroPagina;
		this.IdProceso=IdProceso;
		this.color=color;
	}
        
	public int getIdProceso() 
        {
		return IdProceso;
	}
	public int getNumeroPagina() 
        {
		return numeroPagina;
	}
        
	public int getPeso() 
        {
		return peso;
        }
	public void setPeso(int peso) 
        {
		this.peso = peso;
	}

	public Color getColor()
        {
		return color;
	}
	public void setColor(Color color)
        {
		this.color = color;
	}
}
