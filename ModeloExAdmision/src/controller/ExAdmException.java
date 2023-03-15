/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author ersolano
 */
public class ExAdmException extends Exception{
    
    private String codError;
    private static Properties errores = new Properties();
    
    public ExAdmException(String codError) {
        this.codError = codError;
    }
    
    public String decodificaError(){
        try {
            errores.load(new FileReader("src\\param\\EXADM_error.properties"));
            String mensaje= errores.getProperty(codError);
            if (!mensaje.isEmpty())
                return mensaje;
            else
                return errores.getProperty("ERROR_FILE");
        } catch (IOException ex) {
            return "";
        }
    }
}
