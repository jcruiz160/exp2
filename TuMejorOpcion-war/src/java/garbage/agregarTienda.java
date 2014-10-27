package servlets;

import beans.Login;
import beans.TiendaBean;
import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.bos.Usuario;
import com.tumejoropcion.exception.OperacionInvalidaException;
import com.tumejoropcion.servicios.IServicioBonosMockLocal;
import com.tumejoropcion.servicios.ServicioBonosMock;
import com.tumejoropcion.servicios.ServicioLoginMock;
import com.tumejoropcion.servicios.ServicioPersistenciaMock;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class agregarTienda extends HttpServlet {

    private static final long serialVersionUID = -7453606094644144082L;
    private Tienda tien;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long ahora= System.currentTimeMillis();
        request.setCharacterEncoding("UTF-8");
        String message = request.getParameter("message2");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        
        String tienda = request.getParameter("Select1");
        if (tienda.equals("1")) {
            tien = new Tienda(message, 1);
        } else if (tienda.equals("2")) {
            tien = new Tienda(message, 2);
        } else if (tienda.equals("3")) {
            tien = new Tienda(message, 3);
        } else if (tienda.equals("4")) {
            tien = new Tienda(message, 4);
        } else if (tienda.equals("5")) {
            tien = new Tienda(message, 5);
        } else if (tienda.equals("6")) {
            tien = new Tienda(message, 6);
        } else if (tienda.equals("7")) {
            tien = new Tienda(message, 7);
        } else if (tienda.equals("8")) {
            tien = new Tienda(message, 8);
        }
        
        
        try {
            tien.cambiarNombre(message);
            facebook.postStatusMessage("la tienda con nombre " + tienda +" fue cambiada por el nombre: "+message);
            System.out.println(tien.darNombre());
            System.out.println(tien.darIdentificador());
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
        long luego= System.currentTimeMillis();
        long difference = luego-ahora;
        //System.out.println("en modificar uan tienda se demora"+ difference);
    }
}
