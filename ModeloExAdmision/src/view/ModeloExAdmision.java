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
    
    
    public static void demoFormulario(){
    int idSolic = 1000;
    String nombreSolic ="Solicitante 1000";
    String correoSolic ="correo1000@gmail.com";
    String celularSolic ="8123-4567";
    String colegioSolic ="Colegio del Solicitante 1000";
    DireccionPCD dirSolic = SingletonDAO.getInstance().getPCD(4); 
    String detalleDir = "Cerquita del Morazán";
    String carreraSolic = "IC";
    String sedeSolic = "SJ";
    
    DTOFormulario elDTO = new  DTOFormulario(idSolic, nombreSolic, 
                                correoSolic, celularSolic, colegioSolic, 
                                dirSolic, detalleDir, carreraSolic, sedeSolic);
    
    boolean resultado = elCtrl.registrarFormulario(elDTO);
    System.out.println( resultado   ? "Formulario registrado y es el numero: "+
            elCtrl.getFormulario(idSolic): "No pudo registrar el formulario");
    }
    
    public static void demoCarreras(){
        System.out.println("Visualizar todas las carreras de la institucion");
        System.out.println(elCtrl.getCarreras());
        
        String unaSede = "SJ";
        String unaCarrera = "IC";
        int nuevoPuntajeCarrera= 600;
        int nuevaAdmisionCarrera = 100;
        
        System.out.println("Modificar puntaje de admision a " + 
                            nuevoPuntajeCarrera +" de una carrera particular " + 
                           unaSede+"-"+unaCarrera );
        boolean resultado = elCtrl.editarPuntajeMinimoAdmision(unaCarrera, unaSede, nuevoPuntajeCarrera);
        System.out.println( resultado ? "Puntaje minimo modificado" : "No encontro la carrera para cambio de puntaje");
                
        System.out.println("Modificar capacidad de admision a " +
                            nuevaAdmisionCarrera +" de una carrera particular " + 
                           unaSede+"-"+unaCarrera );
        resultado = elCtrl.editarCapacidadAdmision(unaCarrera, unaSede, nuevaAdmisionCarrera);
        System.out.println( resultado ? "Puntaje minimo modificado" : "No encontro la carrera para cambio de capacidad de admision");

        System.out.println("visualizar las carreras de la sede "+ unaSede);
        System.out.println(elCtrl.getCarrerasPorSede(unaSede));
        
    }
    
    public static void demoConfiguracion(){
        int nuevoPuntaje = 900;
         System.out.println("Obteniendo Puntaje General de Admisión actual" +
                            elCtrl.getPuntajeGeneralAdmision()); 
         
         System.out.println("Editando Puntaje General de Admisión ");
        elCtrl.editarPuntajeGeneralAdmision( nuevoPuntaje );
        
        System.out.println("Obteniendo Puntaje General de Admisión " +
                            elCtrl.getPuntajeGeneralAdmision());        
        
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
    
    public static void main(String[] args) {
        System.out.println("En demoConfiguracion");
        demoConfiguracion();
    
        System.out.println("En demoCarreras");
        demoCarreras();
        
        System.out.println("En demoFormulario");
        demoFormulario();
       
     }
    
}
