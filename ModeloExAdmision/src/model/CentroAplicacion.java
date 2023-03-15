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
public class CentroAplicacion {
    
    private int id;
    private String nombre;
    private DireccionPCD direccionPCD;
    private String detalle;

    public CentroAplicacion(int id, String nombre, DireccionPCD direccionPCD, String detalle) {
        this.id = id;
        this.nombre = nombre;
        this.direccionPCD = direccionPCD;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DireccionPCD getDireccionPCD() {
        return direccionPCD;
    }

    public void setDireccionPCD(DireccionPCD direccionPCD) {
        this.direccionPCD = direccionPCD;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return  "CentroAplicacion:" + "Id=" + id + ", nombre=" + nombre + 
                ", direccionPCD=" + direccionPCD + ", detalle=" + detalle + '\n';
    }
    
    
}
