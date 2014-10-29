/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tumejoropcion.bos;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author estudiante
 */
public class Bono {

    /**
     * El codigo del bono.
     */
    private String codigo;

    /**
     * Indica el valor por el cual esta el bono.
     */
    private int valor;

    /**
     * Indica la tienda a la cual pertenece.
     */
    private String referencia;

    /**
     * Indica la fecha hasta cuando el bono es valido.
     */
    private Date fechaVencimiento;

    /**
     * Indica si el vono esta activo.
     */
    private boolean redimibleFecha;
    private boolean redimido;

    private String tienda;

    private String idComprador;

    /**
     * Constructor vacio
     */
    public Bono() {

    }

    /**
     * Constructor del bono
     *
     * @param codigo El codigo del bono.
     * @param valor El valor del bono.
     * @param referencia La tienda a la cual pertenece el bono.
     * @param nUsuario
     * @param fv La fecha límite de cange.
     */
    public Bono(int valor, String referencia, Date fv, String idComprador) {

        codigo = UUID.randomUUID().toString();
        System.out.println("codigo:" + codigo);
        this.valor = valor;
        this.referencia = referencia;
        this.idComprador = idComprador;
        fechaVencimiento = fv;

        redimibleFecha = true;
        redimido = false;
    }

    /**
     * Muestra el codigo del bono.
     *
     * @return El codigo del bono.
     */
    public String darCodigo() {
        return codigo;
    }

    /**
     * Muestra el valor del bono.
     *
     * @return El valor del bono.
     */
    public int darValor() {
        return valor;
    }

    /**
     * Muestra la tienda a la cual pertenece el bono.
     *
     * @return La referencia del bono.
     */
    public String darReferencia() {
        return referencia;
    }

    public String darTienda() {
        return tienda;
    }

    /**
     * Muestra la fehca límite del bono para cangear.
     *
     * @return La fehca donde deja de ser vigente.
     */
    public Date darFechaVencimiento() {
        return fechaVencimiento;
    }

    public String darIdComprador() {
        return idComprador;
    }

    /**
     * Muestra si el bono esta vigente.
     *
     * @return Si esta activo o no.
     */
    public boolean estaVigenteFecha() {
        return redimibleFecha;
    }

    /**
     * Si está redimido retorna FALSE si se puede redimir retorna TRUE
     */
    public boolean redimir() {
        if (redimido == true) {
            return false;
        } else {
            redimido=true;
            return true;
        }
    }

    public String getCodigo() {
        return codigo;
    }


    public int getValor() {
        return valor;
    }


    public String getReferencia() {
        return referencia;
    }


    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getTienda() {
        return tienda;
    }
}
