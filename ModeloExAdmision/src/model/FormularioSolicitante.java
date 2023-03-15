/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ersolano
 */
public class FormularioSolicitante {
    private static int proxFormulario = 
            (int) (Configuracion.getInstance().getParam("CONSECUTIVO_FORM", Integer.class));
     
    private int numero;
    private Calendar fecha;
    private int idSolic;
    private String nombreSolic;
    private String correoSolic;
    private String celularSolic;
    private String colegioSolic;
    private String detalleDirSolic;
    private DireccionPCD dirSolicPCD; 

    private Carrera carreraSolic;
    private TEstadoSolicitante estado;
    private DatosExamen detalleExamen;

    public FormularioSolicitante() {
        this.numero = getProximo();
        this.fecha = Calendar.getInstance();
        //aquí se crean los espacios que deberían llenarse por aparte
        this.estado = TEstadoSolicitante.SOLICITANTE;
        this.detalleExamen = new DatosExamen();    
    }

    public FormularioSolicitante(int idSolic, String nombreSolic, 
                                 String celSolic, String correoSolic, 
                                 String detalleResidencia, String colegioSolic, 
                                 DireccionPCD direccionPCD, Carrera carreraSolic) {
        this.numero = getProximo();
        this.fecha = Calendar.getInstance();
        
        this.idSolic = idSolic;
        this.nombreSolic = nombreSolic;
        this.celularSolic = celSolic;
        this.correoSolic = correoSolic;
        this.colegioSolic = colegioSolic;
        this.detalleDirSolic = detalleResidencia;
        this.dirSolicPCD = direccionPCD;
        this.carreraSolic = carreraSolic;
        
        this.estado = TEstadoSolicitante.SOLICITANTE;
        this.detalleExamen = new DatosExamen();
    }
    
    
    private int getProximo(){
        int proximo = proxFormulario++;
        Configuracion.getInstance().setParam("CONSECUTIVO_FORM", proxFormulario);
        Configuracion.getInstance().guardarProperties();
        return proximo;
    }    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getIdSolic() {
        return idSolic;
    }

    public void setIdSolic(int idSolic) {
        this.idSolic = idSolic;
    }

    public String getNombreSolic() {
        return nombreSolic;
    }

    public void setNombreSolic(String nombreSolic) {
        this.nombreSolic = nombreSolic;
    }

    public String getCorreoSolic() {
        return correoSolic;
    }

    public void setCorreoSolic(String correoSolic) {
        this.correoSolic = correoSolic;
    }

    public String getCelularSolic() {
        return celularSolic;
    }

    public void setCelularSolic(String celularSolic) {
        this.celularSolic = celularSolic;
    }

    public String getColegioSolic() {
        return colegioSolic;
    }

    public void setColegioSolic(String colegioSolic) {
        this.colegioSolic = colegioSolic;
    }

    public String getDetalleDirSolic() {
        return detalleDirSolic;
    }

    public void setDetalleDirSolic(String detalleDirSolic) {
        this.detalleDirSolic = detalleDirSolic;
    }

    public void setDirSolicPCD(DireccionPCD dirSolicPCD) {
        this.dirSolicPCD = dirSolicPCD;
    }

    public DireccionPCD getDirSolicPCD() {
        return dirSolicPCD;
    }

    
    public Carrera getCarreraSolic() {
        return carreraSolic;
    }

    public void setCarreraSolic(Carrera carreraSolic) {
        this.carreraSolic = carreraSolic;
    }

    public TEstadoSolicitante getEstado() {
        return estado;
    }

    public void setEstado(TEstadoSolicitante estado) {
        this.estado = estado;
    }

    public DatosExamen getDetalleExamen() {
        return detalleExamen;
    }

    public void setDetalleExamen(DatosExamen detalleExamen) {
        this.detalleExamen = detalleExamen;
    }

    private String formatoFecha(Calendar fecha){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(fecha.getTime());
    }
    
    @Override
    public String toString() {
        return "FormularioSolicitante:\n " + 
                "numero=" + numero + "\n" +
                "fecha=" + formatoFecha(fecha) + "\n" + 
                "idSolic=" + idSolic + "\n" + 
                "nombreSolic=" + nombreSolic + "\n" + 
                "correoSolic=" + correoSolic +  "\n" +
                "celularSolic=" + celularSolic +  "\n" +
                "colegioSolic=" + colegioSolic +  "\n" +
                "dirSolic=" + dirSolicPCD + "\n" +
                "detalleDirSolic=" + detalleDirSolic + "\n" + 
                "carreraSolic=" + carreraSolic.getCodigo()+"-"+
                                  carreraSolic.getSede().getCodigo() +  "\n" +
                "estado=" + estado +  "\n" +
                "detalleExamen=" + detalleExamen + '\n';
    }
    
    
    
    
}
