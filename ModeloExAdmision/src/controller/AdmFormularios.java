/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.DAO.SingletonDAO;
import java.util.ArrayList;
import model.CentroAplicacion;
import model.FormularioSolicitante;
import java.util.Calendar;
import model.TEstadoSolicitante;

/**
 *
 * @author ersolano
 */
public class AdmFormularios {

    public AdmFormularios() {
    }

    public boolean registrarFormulario(DTOFormulario elDTO) {

        // verifica que el solicitante no haya registrado otro anteriormente
        FormularioSolicitante elForm = SingletonDAO.getInstance().consultarFormulario(elDTO.getIdSolic());

        if (elForm == null) {
            elForm = new FormularioSolicitante();
            elForm.setIdSolic(elDTO.getIdSolic());
            elForm.setNombreSolic(elDTO.getNombreSolic());
            elForm.setCorreoSolic(elDTO.getCorreoSolic());
            elForm.setCelularSolic(elDTO.getCelularSolic());
            elForm.setColegioSolic(elDTO.getColegioSolic());
            elForm.setDirSolicPCD(elDTO.getDirSolic());
            elForm.setDetalleDirSolic(elDTO.getDetalleDireccion());
            elForm.setCarreraSolic(elDTO.getCarreraSolic());

            boolean res = SingletonDAO.getInstance().agregarFormulario(elForm);
            return res;
        }
        return false;
    }

    public FormularioSolicitante consultarFormulario(int idSolic) {
        return SingletonDAO.getInstance().consultarFormulario(idSolic);
    }

    /**
     *
     * @param numeroFormulario int: Numero de formulario.
     * @param fechaExamen Calendar: Fecha y hora del examen.
     * @param lugar CentroAplicacion: Lugar donde se va a realizar el examen.
     * @return true si logro registrar la cita del examen | false si no lo
     * logro.
     */
    public boolean registrarCitaExamen(int numeroFormulario, Calendar fechaExamen, CentroAplicacion lugar) {
        return SingletonDAO.getInstance().actualizarFormulario(numeroFormulario, fechaExamen, lugar);
    }

    public ArrayList<FormularioSolicitante> getFormularios(TEstadoSolicitante estado) {
        return SingletonDAO.getInstance().getFormularios(estado);
    }

    public ArrayList<CentroAplicacion> getCentrosAplicacion() {
        return SingletonDAO.getInstance().getCentrosAplicacion();
    }

    /**
     * Ejercicio 6. Simular la aplicación de examen con ausentes y candidatos.
     * Metodo para simular la aplicacion del examen de admision.
     */
    public void simularAplicacionExamen() {

    }
}
