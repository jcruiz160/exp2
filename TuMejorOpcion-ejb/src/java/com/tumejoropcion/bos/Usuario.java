/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tumejoropcion.bos;

import facebook4j.Friend;
import facebook4j.Like;
import facebook4j.ResponseList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class Usuario {
    
    private String id;
    private String email;
    private String nombre;
    private ArrayList bonos;
    private List<Like> likes;
    private List<Friend> friends;
    
    public Usuario(){
        
    }
    
    public Usuario(String nEmail){
        email = nEmail;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String nEmail){
        email = nEmail;
    }
    
    public void setFriends(ResponseList<Friend> amigos){
        friends = amigos;
    }
    
    public List getFriends(){
        return friends;
    }
    
    public void setLikes(ResponseList<Like> nLikes){
        likes = nLikes;
    }
    
    public List getLikes(){
        return likes;
    }
    
    public String darNombre(){
        return nombre;
    }
    
    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ArrayList darBonos() {
        return bonos;
    }
    
    public void agregarBono(Bono bono) {
        bonos.add(bono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList getBonos() {
        return bonos;
    }

    public void setBonos(ArrayList bonos) {
        this.bonos = bonos;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
