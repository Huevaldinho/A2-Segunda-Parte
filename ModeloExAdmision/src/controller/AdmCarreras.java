/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.DAO.SingletonDAO;
import java.util.List;
import model.Carrera;

/**
 *
 * @author ersolano
 */
public class AdmCarreras {
    
    public AdmCarreras() {
    }
    
    public List<Carrera> getCarreras(){
        // simulando una posible comunicacion con el DAO..
        return SingletonDAO.getInstance().getCarreras();
    }
    
    public List<Carrera> getCarreras(String sede){
        
        // simulando una posible comunicacion con el DAO..
        return SingletonDAO.getInstance().consultarCarrerasdeUnaSede(sede);
    }
        
    public Carrera getCarrera(String codigoCarrera, String codigoSede){
        return SingletonDAO.getInstance().consultarUnaCarrera(codigoCarrera, codigoSede);
    }
    
    /**
     * Modifica la capacidad de admision para una carrera de una sede especifica
     * @param codigoCarrera:String
     * @param codigoSede:String
     * @param capacidad:int 
     * @return boolean
     */
    public boolean editarCarrera(String codigoCarrera, String codigoSede, int capacidad){
            Carrera laCarrera= getCarrera(codigoCarrera, codigoSede);
            if (laCarrera != null){
                laCarrera.setMaxAdmision(capacidad);
                SingletonDAO.getInstance().editarCarrera(laCarrera);
                return true;
            }
        return false;
    }
    
    /**
     * Modifica el puntaje minimo de admision para una carrera de una sede especifica
     * @param puntaje:int
     * @param codigoCarrera:String
     * @param codigoSede:String
     * @return boolean
     */
     public boolean editarCarrera(int puntaje, String codigoCarrera, String codigoSede){
            Carrera laCarrera= getCarrera(codigoCarrera, codigoSede);
            if (laCarrera != null){
                laCarrera.setPuntajeMinimo(puntaje);
                SingletonDAO.getInstance().editarCarrera(laCarrera);
                return true;
            }
        return false;
    }
    
    
}
