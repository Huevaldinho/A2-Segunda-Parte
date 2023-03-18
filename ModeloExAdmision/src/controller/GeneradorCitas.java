/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import model.CentroAplicacion;
import model.DatosExamen;
import model.FormularioSolicitante;
import model.TEstadoSolicitante;

/**
 *
 * @author Grupo de trabajo.
 */
public class GeneradorCitas {

    private UtilitarioComunicacion comunicador = new UtilitarioComunicacion();
    private AdmFormularios admFormulario;

    public GeneradorCitas() {

    }

    public GeneradorCitas(AdmFormularios admFormularioIn) {
        this.admFormulario = admFormularioIn;
    }

    /**
     * Ejercicio 5. Asignar citas a solicitantes. Metodo para asignar citas a
     * los solicitantes.
     *
     */
    public void asignarCitasASolicitantes() {
        int anhoActual = Year.now().getValue();
        ArrayList<FormularioSolicitante> formulariosSolicitantes = admFormulario.getFormularios(TEstadoSolicitante.SOLICITANTE);
        FormularioSolicitante formularioActual;
        for (int i = 0; i < formulariosSolicitantes.size(); i++) {
            formularioActual = formulariosSolicitantes.get(i);
            if (formularioActual.getFecha().get(Calendar.YEAR) >= anhoActual
                    && formularioActual.getDetalleExamen().getCitaExamen() == null) {
                admFormulario.registrarCitaExamen(
                        formularioActual.getNumero(),
                        generarFechaAleatoria(),
                        generarCentroAplicacionAleatorio()
                );
            }
        }
    }

    /**
     * Metodo para notificar a los solicitantes su fecha y lugar de aplicacion
     * del examen de admision.
     */
    public void notificarFormulario() {
        int anhoActual = Year.now().getValue();
        ArrayList<FormularioSolicitante> tablaFormularios = admFormulario.getFormularios(TEstadoSolicitante.SOLICITANTE);
        String emisor = "Instituto Tecnológico de Costa Rica le informa a: ";
        String destinatario;
        String asunto;

        for (int i = 0; i < tablaFormularios.size(); i++) {
            FormularioSolicitante actualForm = tablaFormularios.get(i);
            if (actualForm.getFecha().get(Calendar.YEAR) >= anhoActual
                    && actualForm.getDetalleExamen().getCitaExamen() != null) {
                destinatario = String.valueOf(tablaFormularios.get(i).getNombreSolic())
                        + " Numero de formulario: " + String.valueOf(actualForm.getNumero());

                asunto = "Informacion sobre cita del examen de admision:" + String.valueOf(actualForm.getDetalleExamen());

                comunicador.enviarCorreo(emisor, destinatario, asunto);
            }
        }
    }

    /**
     * Metodo para generar una fecha random en los proximos 6 meses.
     *
     * @return Calendar: Fecha random en los proximos 6 meses.
     */
    public Calendar generarFechaAleatoria() {
        // Obtener la fecha actual en un objeto Calendar
        Calendar calendar = Calendar.getInstance();
        // Generar un número aleatorio entre 1 y 180 (que representa el número de días dentro de los próximos 6 meses)
        Random random = new Random();
        int dias = random.nextInt(180) + 1;
        // Añadir el número de días generados al objeto Calendar de la fecha actual
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        // Devolver el objeto Calendar resultante
        return calendar;
    }

    /**
     * Metodo para generar un centro de aplicacion aleatorio.
     *
     * @return CentroAplicacion: Aleatorio.
     */
    public CentroAplicacion generarCentroAplicacionAleatorio() {
        ArrayList<CentroAplicacion> tablaCentros = admFormulario.getCentrosAplicacion();
        if (tablaCentros.isEmpty()) {
            return null;
        }
        return tablaCentros.get(generarNumeroAleatorio(0, tablaCentros.size() - 1));
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
