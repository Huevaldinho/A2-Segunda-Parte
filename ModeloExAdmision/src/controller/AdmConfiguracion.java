/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Configuracion;

/**
 *
 * @author ersolano
 */
public class AdmConfiguracion {

    public AdmConfiguracion() {
    }
    
    public boolean editarPuntajeAdmision(int valor){
        Configuracion.getInstance().setMaximoPuntaje(valor);
        return true;
    }
    
    public int getPuntajeAdmision(){
        return Configuracion.getInstance().getMaximoPuntaje();
    }

    public boolean guardarConfiguracion() {
        return Configuracion.getInstance().guardarProperties();
    }
    
}
