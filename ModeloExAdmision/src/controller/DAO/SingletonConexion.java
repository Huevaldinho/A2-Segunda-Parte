/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

/**
 *
 * @author ersolano
 */
public class SingletonConexion {
    
    private static SingletonConexion instance;

    
    private SingletonConexion() {
    }
    
    public static SingletonConexion getInstance(){
        if (instance == null)
            return new SingletonConexion();
        return instance;
    }
    
    // aquí estarian las operaciones que brindan servicios de conexion y desconexion a la persistencia
    
    
}
