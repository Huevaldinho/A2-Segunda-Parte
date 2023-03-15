/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author ersolano
 */
public class DatosExamen {
    private Calendar citaExamen;
    private CentroAplicacion lugarExamen;
    private int puntajeObtenido;

    public DatosExamen() {
    }

    public Calendar getCitaExamen() {
        return citaExamen;
    }

    public void setCitaExamen(Calendar citaExamen) {
        this.citaExamen = citaExamen;
    }

    public CentroAplicacion getLugarExamen() {
        return lugarExamen;
    }

    public void setLugarExamen(CentroAplicacion lugarExamen) {
        this.lugarExamen = lugarExamen;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    @Override
    public String toString() {
        return "DatosExamen:\n" + "CitaExamen=" + citaExamen + 
               "\nLugarExamen=" + lugarExamen + 
               "\nPuntajeObtenido=" + puntajeObtenido + '\n';
    }
            
    
}
