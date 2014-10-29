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
    public String identificador;
    
    /**
     * Inicializa la tienda.
     */
    public Tienda() {
       bonos = new ArrayList<Bono>(); 
    }
    
    public Tienda(String nombre, String identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
        bonos = new ArrayList<Bono>();
        //GregorianCalendar c=new GregorianCalendar(12,12,2014);
        //Date k= c.getTime();
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
     * Asigna un nuevo nombre a la tienda por uno que entra por parámetro.
     * @param nombre El nuevo nombre de la tienda. nombre != null & nombre != ""
     */
    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
}
