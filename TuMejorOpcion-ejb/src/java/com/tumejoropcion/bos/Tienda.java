/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tumejoropcion.bos;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * @author estudiante
 */
public class Tienda {
    
     /**
      * Nombre de la tienda.
      */
    private String nombre;
    
    /**
     * Lista de onos que tiene la tienda.
     */
    private ArrayList<Bono> bonos;
    
    /**
     * Identificador único de la tienda
     */
    public int identificador;
    
    /**
     * Inicializa la tienda.
     */
    public Tienda() {
        
    }
    
    public Tienda(String nombre, int identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
        bonos = new ArrayList<Bono>();
        GregorianCalendar c=new GregorianCalendar(12,12,2014);
        Date k= c.getTime();
        //Bono x= new Bono(1, 50000, "Hola",k );
    }
    
    /**
     * Da el nombre de la tienda.
     * @return el nombre de la tienda.
     */
    public String darNombre() {
        return nombre;
    }
    
    /**
     * Retorna la lista de bonos que tiene la tienda.
     * @return lista de los bonos.
     */
    public ArrayList<Bono> darBonos() {
        return bonos;
    }
    
    /**
     * Muestra el indentificador de la tienda
     * @return El identificador de la tienda
     */
    public int darIdentificador() {
        return identificador;
    }
    
    /**
     * Asigna un nuevo nombre a la tienda por uno que entra por parámetro.
     * @param nombre El nuevo nombre de la tienda. nombre != null & nombre != ""
     */
    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Asigna el nuevo identificador de la tienda por uno nuevo que entra por parámetro
     * @param id El nuevo identificador de la tienda.
     */
    public void cambiarIdentificador(int id) {
        identificador = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Bono> getBonos() {
        return bonos;
    }

    public void setBonos(ArrayList<Bono> bonos) {
        this.bonos = bonos;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
}
