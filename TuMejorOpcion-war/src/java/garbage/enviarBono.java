package servlets;


import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.servicios.IServicioBonosMockLocal;
import com.tumejoropcion.servicios.ServicioBonosMock;
import com.tumejoropcion.servicios.javaMail;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class enviarBono extends HttpServlet {
    private static final long serialVersionUID = -7453606094644144082L;
    private javaMail javamail;
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        javamail= new javaMail();
        System.out.println("entró al servlet");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String precioIn = request.getParameter("messageEnviar");
        String email = request.getParameter("email");
        GregorianCalendar c = new GregorianCalendar(2014, 12, 12);
        Date y = c.getTime();
         int a=Integer.parseInt(precioIn);
        String tienda = request.getParameter("Select3");
        if (tienda.equals("1")) {
            tienda = "Zara";
        } else if (tienda.equals("2")) {
            tienda = "Arturo Calle";
        } else if (tienda.equals("3")) {
            tienda = "Fuera De Serie";
        } else if (tienda.equals("4")) {
            tienda = "Bkul";
        } else if (tienda.equals("5")) {
            tienda = "Lec Lee";
        } else if (tienda.equals("6")) {
            tienda = "Studio F";
        } else if (tienda.equals("7")) {
            tienda = "Pronto";
        } else if (tienda.equals("8")) {
            tienda = "Armi";
        }
        Bono nuevo=null;
        try {
            nuevo = new Bono(a, tienda, y,facebook.getId());
        } catch (FacebookException ex) {
            Logger.getLogger(enviarBono.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(enviarBono.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println("va a entrar");
       
        javamail.enviarMensaje(email, "El valor del bono es de:"+a+ " con codigo:"+ nuevo.darCodigo());
        try {
            facebook.postStatusMessage("El valor del bono es de:"+a+ " con codigo:"+ nuevo.darCodigo());
        } catch (FacebookException ex) {
            Logger.getLogger(enviarBono.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/");
}
}
