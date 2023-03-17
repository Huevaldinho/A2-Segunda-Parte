/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    /**
     * Metodo para formatear un objeto de tipo Calendar a String. Retorna fecha
     * en formato dd-mm-yyyy hh:mm
     *
     * @param fechaHora Calendar: Fecha a formatear.
     * @return Fecha formateada a string | null si la fecha entrante es null.
     */
    public String formatearFechaHora(Calendar fechaHora) {
        if (fechaHora == null) {
            return null;
        }
        // Crear un objeto SimpleDateFormat con el formato deseado
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        // Formatear la fecha y hora utilizando el objeto SimpleDateFormat
        String fechaHoraFormateada = sdf.format(fechaHora.getTime());

        // Devolver la fecha y hora formateada
        return fechaHoraFormateada;
    }

    @Override
    public String toString() {
        return "DatosExamen:\n" + "CitaExamen= " + formatearFechaHora(citaExamen)
                + "\nLugarExamen=" + lugarExamen
                + "\nPuntajeObtenido=" + puntajeObtenido + '\n';
    }

}
