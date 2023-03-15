/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ersolano
 */
public class DireccionPCD {
    private String provincia;
    private String canton;
    private String distrito;

    public DireccionPCD(String provincia, String canton, String distrito) {
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCanton() {
        return canton;
    }

    public String getDistrito() {
        return distrito;
    }

    @Override
    public String toString() {
        return "DireccionPCD:" + provincia + "-" + canton + "-" + distrito + ' ';
    }
    
    
}
