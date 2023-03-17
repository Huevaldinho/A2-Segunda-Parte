/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.DAO.SingletonDAO;
import model.CentroAplicacion;
import model.FormularioSolicitante;
import java.util.Calendar;
import java.util.ArrayList;

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
     * Ejercicio 5. Definir y notificar citas
     *
     * @return ArrayList con los formularios que contienen las citas de examen
     * generadas | null si no se logra generar.
     */
    public ArrayList<FormularioSolicitante> definirCitasYNotificar() {
        if (SingletonDAO.getInstance().generarCitas()) {
            return SingletonDAO.getInstance().notificarCitas();
        }
        //No logro generar citas.
        return null;
    }

    /**
     * Metodo para registrar la cita de un examen de admision para
     *
     * @param numero int: ?
     * @param fechaExamen LocalDateTime: Fecha del examen.
     * @param lugar CentroAplicacion: Lugar donde se realiza el examen.
     * @return true si logra registrar la cita | false si no lo logra.
     */
    public boolean registrarCitaExamen(int numero, Calendar fechaExamen,
            CentroAplicacion lugar) {
        boolean resultadoRegistro = false;

        return resultadoRegistro;

    }

    /**
     *
     */
    public void simularAplicacionExamen() {
    }
}
