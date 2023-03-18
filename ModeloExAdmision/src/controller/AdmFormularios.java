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
import java.util.Random;
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
     *
     * Se utiliza un random para determinar si el formulario es Ausente o
     * Candidato.
     *
     * @param minExamen int: Minimo valor que puede obtener un estudiante en el
     * examen.
     * @param maxExamen int: Maximo valor que puede obtener un estudiante en el
     * examen.
     */
    public void simularAplicacionExamen(int minExamen, int maxExamen) {
        ArrayList<FormularioSolicitante> formulariosSolicitantes;
        formulariosSolicitantes = SingletonDAO.getInstance().
                getFormularios(TEstadoSolicitante.SOLICITANTE);
        FormularioSolicitante formularioActual;

        for (int i = 0; i < formulariosSolicitantes.size(); i++) {
            formularioActual = formulariosSolicitantes.get(i);
            if (generarNumeroAleatorio(1, 10) <= 2) {
                //Si sale 1 o 2 no aplica examen. Cambiar a ausante.
                SingletonDAO.getInstance().
                        actualizarFormulario(formularioActual.getNumero(),
                                TEstadoSolicitante.AUSENTE);
                System.out.println(formularioActual);
                continue;
            }
            if (formularioActual.getDetalleExamen().getLugarExamen() != null
                    && formularioActual.getDetalleExamen().getCitaExamen() != null) {
                //Ya tiene asignado un lugar y fecha.
                SingletonDAO.getInstance().
                        setNotaExamenFormulario(formularioActual.getNumero(),
                                generarNumeroAleatorio(minExamen, maxExamen));
                //Cambiar a Candidato.
                SingletonDAO.getInstance().
                        actualizarFormulario(formularioActual.getNumero(),
                                TEstadoSolicitante.CANDIDATO);
                System.out.println(formularioActual);
            }
        }
    }

    /**
     * Metodo para genera aleatoriamente un numero entre el minimo y maximo
     * dado. Ambos extremos del rango estan incluidos.
     *
     * @param minimo int: Rango minimo del numero.
     * @param maximo int: Rango maximo del numero.
     * @return
     */
    public int generarNumeroAleatorio(int minimo, int maximo) {
        // Crear un objeto Random
        Random random = new Random();
        // Generar un número aleatorio entre el valor mínimo y el valor máximo (ambos incluidos)
        int numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
        // Devolver el número aleatorio generado
        return numeroAleatorio;
    }

}
