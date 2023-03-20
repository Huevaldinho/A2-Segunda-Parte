/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Carrera;
import model.FormularioSolicitante;

/**
 *
 * @author ersolano
 */
public class Controlador {

    private AdmConfiguracion admConfig = new AdmConfiguracion();
    private AdmCarreras admCarreras = new AdmCarreras();
    private AdmFormularios admFormularios = new AdmFormularios();
    private GeneradorCitas generadorCitas = new GeneradorCitas(admFormularios);

    public Controlador() {
    }

    public boolean editarPuntajeGeneralAdmision(int nuevoValor) {
        return admConfig.editarPuntajeAdmision(nuevoValor);
    }

    public int getPuntajeGeneralAdmision() {
        return admConfig.getPuntajeAdmision();
    }

    public boolean guardarConfiguracion() {
        return admConfig.guardarConfiguracion();
    }

    public List<Carrera> getCarreras() {
        return admCarreras.getCarreras();
    }

    public List<Carrera> getCarrerasPorSede(String sede) {
        return admCarreras.getCarreras(sede);
    }

    public boolean editarCapacidadAdmision(String codigoCarrera, String codigoSede, int capacidad) {
        return admCarreras.editarCarrera(codigoCarrera, codigoSede, capacidad);
    }

    public boolean editarPuntajeMinimoAdmision(String codigoCarrera, String codigoSede, int puntaje) {
        return admCarreras.editarCarrera(puntaje, codigoCarrera, codigoSede);
    }

//    public Object getParam(String param, Class elTipo) {
//        if (Integer.class.equals(elTipo)) {
//            return Configuracion.getInstance().getParam(param, Integer.class);
//        }
//        if (String.class.equals(elTipo)) {
//            return Configuracion.getInstance().getParam(param, String.class);
//        }
//        if (Double.class.equals(elTipo)) {
//            return Configuracion.getInstance().getParam(param, Double.class);
//        }
//        return null;
//
//    }
    public boolean registrarFormulario(DTOFormulario elDTO) {
        // se hace cualquier otra operación que se pudiera requerir 
        return admFormularios.registrarFormulario(elDTO);
    }

    public FormularioSolicitante getFormulario(int idSolic) {
        return admFormularios.consultarFormulario(idSolic);
    }

    /*Ejercicio 5. Definir y notificar citas.*/
    /**
     * Metodo para generar y notificar las citas del examen de admision.
     *
     * En el modelo esta como si fuera private pero para efectos de la practica
     * se utiliza como public.
     */
    public void generarCitas() {
        generadorCitas.asignarCitasASolicitantes();
    }

    public void notificarCitas() {
        generadorCitas.notificarFormulario();
    }

    /**
     * Ejercicio 6. Simular aplicacion de examen.
     */
    public void simulacionAplicacionExamen() {
        int minExamen = 0;
        int maxExamen = getPuntajeGeneralAdmision();
        admFormularios.simularAplicacionExamen(minExamen, maxExamen);
    }

    /**
     * Ejercicio 7. Determinar estado de solicitantes.
     */
    public void determinarEstadoSolicitantes() {
        admFormularios.determinacionEstadoSolicitante();
    }

    /**
     * Ejercicio 8. Visualización de resultados del proceso de admisión para un
     * solicitante.
     *
     * @param idSolicitante int: id del Solicitante al desplegar los resultados
     *
     * @return resultados finales.
     */
    public String visualizarResultadosParaSolicitante(int idSolicitante) {
        return admFormularios.visualizacionResultadosSolicitante(idSolicitante);
    }

    /**
     * Ejercicio 9. Visualización de resultados de la prueba por carrera.
     *
     * @param codigoCarrera String: codigo único por Carrera
     *
     * @return resultados finales
     */
    public String visualizarResultadosPorCarrera(String codigoCarrera) {
        return admFormularios.visualizacionResultadosPorCarrera(codigoCarrera);
    }

    /**
     * Ejercicio 10. Visualización de los resultados para cada carrera-sede de
     * la institución. En orden de estado ordenados desc por puntaje.
     *
     * @return resultados de consulta.
     */
    public String visualizarResultadosPorCarreraOrdenadosPorEstado() {
        return admFormularios.visualizarResultadosPorCarreraOrdenadosPorEstado();
    }
}
