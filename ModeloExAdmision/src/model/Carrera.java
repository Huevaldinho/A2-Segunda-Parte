/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ersolano
 */
public class Carrera {
    private String codigo;
    private String nombre;
    private Sede  sede;
    private TGrado  grado;
    private int maxAdmision;
    private int puntajeMinimo;

    public Carrera(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Carrera(String codigo, String nombre, Sede sede, TGrado grado, int maxAdmision, int puntajeMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sede = sede;
        this.grado = grado;
        this.maxAdmision = maxAdmision;
        this.puntajeMinimo = puntajeMinimo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public TGrado getGrado() {
        return grado;
    }

    public void setGrado(TGrado grado) {
        this.grado = grado;
    }

    public int getMaxAdmision() {
        return maxAdmision;
    }

    public void setMaxAdmision(int maxAdmision) {
        this.maxAdmision = maxAdmision;
    }

    public int getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(int puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    @Override
    public String toString() {
        return  "Carrera:" + "codigo=" + codigo + ", nombre=" + nombre + 
                ", sede=" + sede + ", grado=" + grado + 
                ", maxAdmision=" + maxAdmision + 
                ", puntajeMinimo=" + puntajeMinimo + '\n';
    }
    
    
}
