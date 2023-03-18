/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author felip
 */
public class UtilitarioComunicacion {

    public UtilitarioComunicacion() {
    }

    /**
     * Metodo creado para comuniucarse con los solicitantes. El emisor deberia
     * ser el TEC y el destinatario el solicitante, mostrar casi toda la info
     * del formulario y el asunto es la cita como tal.
     *
     * @param emisor Quien envia el mensaje.
     * @param destinatario Quien recibe el mensaje.
     * @param asunto Mensaje a enviar.
     */
    public void enviarCorreo(String emisor, String destinatario, String asunto) {
        //Imprimir todas las citas generadas
        System.out.println(emisor + "\n" + destinatario + "\n" + asunto);

    }

}
