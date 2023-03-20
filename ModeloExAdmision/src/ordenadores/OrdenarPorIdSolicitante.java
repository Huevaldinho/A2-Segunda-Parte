/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenadores;

import java.util.Comparator;
import model.FormularioSolicitante;

/**
 *
 * @author felip
 */
public class OrdenarPorIdSolicitante implements Comparator<FormularioSolicitante> {

    @Override
    public int compare(FormularioSolicitante obj1, FormularioSolicitante obj2) {
        return Integer.compare(obj1.getIdSolic(), obj2.getIdSolic());
    }

}
