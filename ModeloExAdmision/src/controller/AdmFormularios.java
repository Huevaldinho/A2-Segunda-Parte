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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
     * Ejercicio 7. Proceso de determinación de nuevos estados de los
     * solicitantes de acuerdo con su selección de carrera, el resultado
     * obtenido y las capacidades de la carrera de interés
     */
    public void determinacionEstadoSolicitante() {
        ArrayList<FormularioSolicitante> formulariosCandidatos = SingletonDAO.getInstance().getFormularios(TEstadoSolicitante.CANDIDATO);
        for (FormularioSolicitante formulario : formulariosCandidatos) {
            int maxAdmisionCarreraSolicitante = formulario.getCarreraSolic().getMaxAdmision();
            int minPuntajeCarreraSolicitante = formulario.getCarreraSolic().getPuntajeMinimo();

            if (formulario.getDetalleExamen().getPuntajeObtenido() > minPuntajeCarreraSolicitante) { //tiene la nota para entrar
                if (maxAdmisionCarreraSolicitante > 0) { //todavía hay campo y tiene la nota
                    formulario.setEstado(TEstadoSolicitante.ADMITIDO); //está admitido
                    formulario.getCarreraSolic().setMaxAdmision(maxAdmisionCarreraSolicitante - 1); //se le resta 1 a la máxima admisión
                } else { //no hay campo
                    formulario.setEstado(TEstadoSolicitante.LISTA_ESPERA); //está en espera
                }
            } else { // no tiene la nota para entrar
                formulario.setEstado(TEstadoSolicitante.RECHAZADO); //está rechazado
            }
        }
    }

    /**
     * Ejercicio 8. Visualización de resultados del proceso de admisión para un
     * solicitante. Disponible sólo cuando ya ha sido aplicada la prueba
     *
     * @param idSolicitante
     * @return información para el solicitante requerido
     */
    public String visualizacionResultadosSolicitante(int idSolicitante) {
        ArrayList<FormularioSolicitante> formularios = SingletonDAO.getInstance().getFormularios(null); //todos los solicitantes

        for (FormularioSolicitante formulario : formularios) {
            if (formulario.getIdSolic() == idSolicitante) {
                return "| Nombre del solicitante: " + formulario.getNombreSolic()
                        + "\n| Carrera a la que aspira: " + formulario.getCarreraSolic().getNombre()
                        + "\n| Puntaje obtenido en la prueba: " + formulario.getDetalleExamen().getPuntajeObtenido()
                        + "\n| Estado: " + formulario.getEstado();
            }
        }
        return "El idSolicitante ingresado no existe.";
    }

    /**
     * Ejercicio 9. Visualización de resultados de la prueba por carrera
     * mostrando los resultados por identificación de solicitante.
     *
     * @param codigoCarrera
     * @return resultados totales para la carrera.
     */
    public String visualizacionResultadosPorCarrera(String codigoCarrera) {
        ArrayList<FormularioSolicitante> formularios = SingletonDAO.getInstance().getFormularios(null); //todos los solicitantes
        String informacionSolicitantes = "";
        Collections.sort(formularios, new ordenadores.OrdenarPorIdSolicitante());

        System.out.println("Resultados por carrera: " + codigoCarrera);
        for (FormularioSolicitante formulario : formularios) {
            if (formulario.getCarreraSolic().getCodigo().equals(codigoCarrera)) { //si encuentra la carrera en ese solicitante
                informacionSolicitantes += "\n| Identificación del solicitante: " + formulario.getIdSolic()
                        + "\n| Estado: " + formulario.getEstado() + "\n";
            }
        }
        return informacionSolicitantes.equals("") ? "No se han encontrado solicitantes en esa carrera o el código ingresado es inválido." : informacionSolicitantes;

    }

    /**
     * Ejercicio 10.Visualización de los resultados para cada carrera-sede de la
     * institución. En orden de estado ordenados desc por puntaje.
     *
     * @return resultado de visualizacion.
     */
    public String visualizarResultadosPorCarreraOrdenadosPorEstado() {
        ArrayList<FormularioSolicitante> formularios = SingletonDAO.getInstance().getFormularios(null); //todos los solicitantes
        if (formularios.isEmpty()) {
            return "";
        }
        Set<String> set = new HashSet<>();
        for (FormularioSolicitante formulario : formularios) {
            set.add(formulario.getCarreraSolic().getCodigo() + "-" + formulario.getCarreraSolic().getSede().getCodigo());
        }

        Collections.sort(formularios, new ordenadores.OrdenarPorPuntaje());//Ordenar por puntaje.
        String carreraSedeFormularioActual, resultado = "";
        for (String carreraSede : set) {
            resultado += "\n" + carreraSede + "\n";
            for (FormularioSolicitante formulario : formularios) {
                carreraSedeFormularioActual = formulario.getCarreraSolic().getCodigo() + "-" + formulario.getCarreraSolic().getSede().getCodigo();
                if (carreraSedeFormularioActual.equals(carreraSede)) {
                    carreraSedeFormularioActual = "Formulario: " + formulario.getNumero()
                            + "\t Estado: " + formulario.getEstado() + "\t Puntaje Obtenido: "
                            + formulario.getDetalleExamen().getPuntajeObtenido();
                    resultado += carreraSedeFormularioActual + "\n";
                }
            }
        }
        return resultado;
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
