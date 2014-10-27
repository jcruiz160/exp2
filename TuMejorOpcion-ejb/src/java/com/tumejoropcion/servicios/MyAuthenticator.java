/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tumejoropcion.servicios;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author estudiante
 */
class MyAuthenticator extends Authenticator {

    public PasswordAuthentication getPasswordAuthentication() {
        String username = "gapicamargo@gmail.com";
        String password = "lafantasia94";
        return new PasswordAuthentication(username, password);

    }
}
