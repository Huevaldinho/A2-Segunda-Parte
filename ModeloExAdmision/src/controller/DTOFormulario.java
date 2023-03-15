/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.DAO.SingletonDAO;
import model.Carrera;
import model.DireccionPCD;

/**
 *
 * @author ersolano
 */
public class DTOFormulario {
    private int idSolic;
    private String nombreSolic;
    private String correoSolic;
    private String celularSolic;
    private String colegioSolic;
    private DireccionPCD dirSolic;   //provincia-canton-distrito
    private String detalleDireccion;
    private Carrera carreraSolic;

    public DTOFormulario() {
    }

    public DTOFormulario(int idSolic, String nombreSolic, 
                         String correoSolic, String celularSolic, 
                         String colegioSolic, DireccionPCD dirSolic, 
                         String detalleDir, String carreraSolic, String sedeSolic) {
        this.idSolic = idSolic;
        this.nombreSolic = nombreSolic;
        this.correoSolic = correoSolic;
        this.celularSolic = celularSolic;
        this.colegioSolic = colegioSolic;
        this.dirSolic = dirSolic;
        this.detalleDireccion = detalleDir;
        this.carreraSolic = SingletonDAO.getInstance().consultarUnaCarrera(carreraSolic, sedeSolic);
    }

    public int getIdSolic() {
        return idSolic;
    }

    public void setIdSolic(int idSolic) {
        this.idSolic = idSolic;
    }

    public String getNombreSolic() {
        return nombreSolic;
    }

    public void setNombreSolic(String nombreSolic) {
        this.nombreSolic = nombreSolic;
    }

    public void setDetalleDireccion(String detalleDireccion) {
        this.detalleDireccion = detalleDireccion;
    }
    
    public String getDetalleDireccion() {
        return detalleDireccion;
    }
    
    public String getCorreoSolic() {
        return correoSolic;
    }

    public void setCorreoSolic(String correoSolic) {
        this.correoSolic = correoSolic;
    }

    public String getCelularSolic() {
        return celularSolic;
    }

    public void setCelularSolic(String celularSolic) {
        this.celularSolic = celularSolic;
    }

    public String getColegioSolic() {
        return colegioSolic;
    }

    public void setColegioSolic(String colegioSolic) {
        this.colegioSolic = colegioSolic;
    }

    public DireccionPCD getDirSolic() {
        return dirSolic;
    }

    public void setDirSolic(DireccionPCD dirSolic) {
        this.dirSolic = dirSolic;
    }

    public Carrera getCarreraSolic() {
        return carreraSolic;
    }

    public void setCarreraSolic(Carrera carreraSolic) {
        this.carreraSolic = carreraSolic;
    }
    
}
