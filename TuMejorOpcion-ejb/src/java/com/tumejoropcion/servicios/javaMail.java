/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tumejoropcion.servicios;
 
import javax.mail.Message.RecipientType;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;


public class javaMail {
	public static void enviarMensaje(String destinatario, String numero)
        {
            final Email email = new Email();

email.setFromAddress("Servicio de Bonos", "tumejoropcionbonos@hotmail.com");
email.setSubject("TuMejorOpcion Te regalaron un Bono");
email.addRecipient("User",destinatario, RecipientType.TO);
email.setText("Tu amigo te ha enviado un bono de ropa. "+ numero+"\n" + "Que disfrute su bono, Gracias por usar nuestra aplicacion.");
//email.setTextHTML("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>");

// embed images and include downloadable attachments

new Mailer("smtp.live.com", 25, "tumejoropcionbonos@hotmail.com", "Arquisoft").sendMail(email);
        }
}