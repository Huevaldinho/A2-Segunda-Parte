/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ExAdmException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author ersolano
 */
public class Configuracion {
    
    private static Configuracion instance;
    
    private static Properties p = new Properties();

    private Configuracion() {
    }
    
    public static Configuracion getInstance()  {
        if (instance == null){
            try {
                instance = new Configuracion();
                instance.cargarConfig();
            } catch (ExAdmException ex) {
                Logger.getLogger("Problemas cargando archivo de parámetros de configuración ");
            }
        }
        return instance;
    }
    
    public void cargarConfig() throws ExAdmException {
        try {
            p.load(new FileReader("src\\param\\EXADM_prop.properties"));
        } catch (IOException ex) {
            throw new ExAdmException("PARAM_FILE");
        }
    }
    
    public int getMaximoPuntaje(){
        return Integer.parseInt(p.getProperty("MAXIMO_PUNTAJE"));
    }
    
    public void setMaximoPuntaje (int nuevoPuntaje){
        p.setProperty("MAXIMO_PUNTAJE", String.valueOf(nuevoPuntaje));
    }
    
    public boolean guardarProperties(){
        try {
            p.store(new FileWriter("src\\param\\EXADM_prop.properties"), "Last Update ");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    
     public Object getParam(String param, Class elTipo) {
        if (Integer.class.equals(elTipo)) {
            return Integer.parseInt(p.getProperty(param));
        }
        if (String.class.equals(elTipo)) {
            return p.getProperty(param);
        }
        if (Double.class.equals(elTipo)) {
            return Double.parseDouble(p.getProperty(param));
        }
        return null;
    }
     
     public void setParam(String param, Object valor){
        p.setProperty(param, String.valueOf(valor));
     }
}
