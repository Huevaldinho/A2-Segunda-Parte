/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controlador;
import controller.DAO.SingletonDAO;
import controller.DTOFormulario;
import controller.IParametros;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import model.Carrera;
import model.Configuracion;
import model.DireccionPCD;

/**
 *
 * @author ersolano
 */
public class ModeloExAdmision {

    /**
     * @param args the command line arguments
     */
    public static Controlador elCtrl = new Controlador();
    public static boolean estadoSolicitantesDeterminado = false;

    public static void demoFormulario() {
        int idSolic = 1000;
        String nombreSolic = "Solicitante 1000";
        String correoSolic = "correo1000@gmail.com";
        String celularSolic = "8123-4567";
        String colegioSolic = "Colegio del Solicitante 1000";
        DireccionPCD dirSolic = SingletonDAO.getInstance().getPCD(4);
        String detalleDir = "Cerquita del Morazán";
        String carreraSolic = "IC";
        String sedeSolic = "SJ";

        DTOFormulario elDTO = new DTOFormulario(idSolic, nombreSolic,
                correoSolic, celularSolic, colegioSolic,
                dirSolic, detalleDir, carreraSolic, sedeSolic);

        boolean resultado = elCtrl.registrarFormulario(elDTO);
        System.out.println(resultado ? "Formulario registrado y es el numero: "
                + elCtrl.getFormulario(idSolic) : "No pudo registrar el formulario");
    }

    public static void demoCarreras() {
        System.out.println("Visualizar todas las carreras de la institucion");
        System.out.println(elCtrl.getCarreras());

        String unaSede = "SJ";
        String unaCarrera = "IC";
        int nuevoPuntajeCarrera = 600;
        int nuevaAdmisionCarrera = 100;

        System.out.println("Modificar puntaje de admision a "
                + nuevoPuntajeCarrera + " de una carrera particular "
                + unaSede + "-" + unaCarrera);
        boolean resultado = elCtrl.editarPuntajeMinimoAdmision(unaCarrera, unaSede, nuevoPuntajeCarrera);
        System.out.println(resultado ? "Puntaje minimo modificado" : "No encontro la carrera para cambio de puntaje");

        System.out.println("Modificar capacidad de admision a "
                + nuevaAdmisionCarrera + " de una carrera particular "
                + unaSede + "-" + unaCarrera);
        resultado = elCtrl.editarCapacidadAdmision(unaCarrera, unaSede, nuevaAdmisionCarrera);
        System.out.println(resultado ? "Puntaje minimo modificado" : "No encontro la carrera para cambio de capacidad de admision");

        System.out.println("visualizar las carreras de la sede " + unaSede);
        System.out.println(elCtrl.getCarrerasPorSede(unaSede));

    }

    public static void demoConfiguracion() {
        int nuevoPuntaje = 900;
        System.out.println("Obteniendo Puntaje General de Admisión actual"
                + elCtrl.getPuntajeGeneralAdmision());

        System.out.println("Editando Puntaje General de Admisión ");
        elCtrl.editarPuntajeGeneralAdmision(nuevoPuntaje);

        System.out.println("Obteniendo Puntaje General de Admisión: "
                + elCtrl.getPuntajeGeneralAdmision());

        System.out.println("Obteniendo parámetros de forma generica");
        System.out.println(Configuracion.getInstance().getParam(IParametros.MAXIMO_PUNTAJE, Integer.class));
        System.out.println(Configuracion.getInstance().getParam(IParametros.ADMIN_USR, String.class));
        System.out.println(Configuracion.getInstance().getParam(IParametros.ADMIN_PWD, String.class));
        System.out.println(Configuracion.getInstance().getParam("INTENTOS", Integer.class));
        System.out.println(Configuracion.getInstance().getParam("CONSECUTIVO_FORM", Integer.class));

        System.out.println("Guardando Configuración...");
        elCtrl.guardarConfiguracion();
        Configuracion.getInstance().guardarProperties();
    }

    //Ejercicio 5. Generar y notificar citas.
    public static void definirCitasYNotificar() {
        System.out.println("\n\n ------- Ejercicio 5. Generar y notificar citas de examen." + "\n");
        elCtrl.generarCitas();
        elCtrl.notificarCitas();
    }

    //Ejercicio 6. Simular aplicacion examen.
    public static void simularAplicacionExamen() {
        System.out.println("\n\n ------- Ejercicio 6. Simulacion de aplicacion de examen.\n");
        elCtrl.simulacionAplicacionExamen();
    }

    //Ejercicio 7. Determinación de nuevos estados de los solicitantes
    public static void determinarEstadoSolicitantes() {
        System.out.println("\n\n ------- Ejercicio 7. Determinación de nuevos estados de los solicitantes.");
        elCtrl.determinarEstadoSolicitantes();
        estadoSolicitantesDeterminado = true;
    }

    //Ejercicio 8. Visualización de resultados del proceso de admisión para un solicitante.
    public static void visualizarResultadosParaSolicitante(int idSolicitante) {
        System.out.println("\n\n ------- Ejercicio 8. Visualización de resultados del proceso de admisión para un solicitante.");
        String resultados = elCtrl.visualizarResultadosParaSolicitante(idSolicitante);
        System.out.println(resultados);
    }

    //Ejercicio 9. Visualización de resultados de la prueba por carrera.
    public static void visualizarResultadosPorCarrera(String codigoCarrera) {
        System.out.println("\n\n ------- Ejercicio 9. Visualización de resultados de la prueba por carrera.");
        String resultados = elCtrl.visualizarResultadosPorCarrera(codigoCarrera);
        System.out.println(resultados);
    }

    //Ejercicio 10. Visualización de resultados de la prueba por carrera en orden de estado.
    public static void visualizarResultadosPorCarreraOrdenadosPorEstado() {
        /*
        10.  Visualización de los resultados para cada carrera-sede de
     * la institución. En orden de estado ordenados desc por puntaje.
         */
        System.out.println("\n\n ------- Ejercicio 10. Visualización de los resultados para cada carrera-sede de\n"
                + "     * la institución. En orden de estado ordenados desc por puntaje.");

        String resultados = elCtrl.visualizarResultadosPorCarreraOrdenadosPorEstado();
        System.out.println(resultados);
    }

    //Generar formularios aleatorios.
    public static void generarFormulariosAleatorios() {
        int cantidadFormulariosAgenerar = generarNumeroAleatorio(50, 100);
        int idSolic;
        String nombreSolic;
        String correoSolic;
        String celularSolic;
        String colegioSolic;
        DireccionPCD dirSolic = SingletonDAO.getInstance().getPCD(4);
        String detalleDir;
        String carreraSolic;
        String sedeSolic;
        List<Carrera> carreras = elCtrl.getCarreras();

        int item;
        for (int i = 1; i <= cantidadFormulariosAgenerar; i++) {
            idSolic = i;
            nombreSolic = "Solicitante " + idSolic;
            correoSolic = nombreSolic + "@gmail.com";
            colegioSolic = "Colegio del " + nombreSolic;
            detalleDir = "Direccion del " + nombreSolic;
            celularSolic = "Telefono Solicitante: " + nombreSolic;
            item = generarNumeroAleatorio(0, carreras.size() - 1);
            carreraSolic = carreras.get(item).getCodigo();
            sedeSolic = carreras.get(item).getSede().getCodigo();
            DTOFormulario elDTO = new DTOFormulario(idSolic, nombreSolic,
                    correoSolic, celularSolic, colegioSolic,
                    dirSolic, detalleDir, carreraSolic, sedeSolic);

            boolean resultado = elCtrl.registrarFormulario(elDTO);
            System.out.println(resultado ? "Formulario registrado y es el numero: "
                    + elCtrl.getFormulario(idSolic) : "No pudo registrar el formulario");
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
    public static int generarNumeroAleatorio(int minimo, int maximo) {
        // Crear un objeto Random
        Random random = new Random();
        // Generar un número aleatorio entre el valor mínimo y el valor máximo (ambos incluidos)
        int numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
        // Devolver el número aleatorio generado
        return numeroAleatorio;
    }

    public static void main(String[] args) {
        System.out.println("En demoConfiguracion");
        demoConfiguracion();

        System.out.println("En demoCarreras");
        demoCarreras();

        System.out.println("En demoFormulario");
        demoFormulario();

        //Pruebas
        generarFormulariosAleatorios();

        //Ejercicio 5. Generar y notificar citas de examen.
        definirCitasYNotificar();
        //Ejercicio 6. Aplicacion del examen de admision.
        simularAplicacionExamen();

        //Ejercicio 7. Determinación de nuevos estados de los solicitantes
        determinarEstadoSolicitantes();

        //Ejercicio 8. Visualización de resultados del proceso de admisión para un solicitante.
        int idSolicitante = 45;
        visualizarResultadosParaSolicitante(idSolicitante);

        //Ejercicio 9. Visualización de resultados de la prueba por carrera. Mostrando por identificación del estudiante.
        String codigoCarrera = "IC";
        visualizarResultadosPorCarrera(codigoCarrera);

        //Ejercicio 10. Visualización de resultados de la prueba por carrera en orden de estado.
        visualizarResultadosPorCarreraOrdenadosPorEstado();
    }

}
