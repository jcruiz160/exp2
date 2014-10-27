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

public class funcionalidadBonos extends HttpServlet {

    private static final long serialVersionUID = -7453606094644144082L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String tienda = request.getParameter("Select");
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

        String message = request.getParameter("messagecomprar");
        String usuario = request.getParameter("usuario");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        Date y = new Date();
        int valor = Integer.parseInt(message);

        Bono nuevo = new Bono(valor, tienda, y, usuario);
        try {

            System.out.println("Agregó un bono");
            //Este metodo imprime el codigo del bono
            ServicioPersistenciaMock.darInstancia().create(nuevo);

            System.out.println(tienda);
            System.out.println(y);

            //  System.out.println(ServicioPersistenciaMock.getBono().get(7).darCodigo()+"");
            System.out.println(ServicioPersistenciaMock.darInstancia().findAll(Bono.class).size());
            facebook.postStatusMessage("Compre un bono por un valor de " + message + " en la tienda " + tienda);
        } catch (FacebookException e) {
            throw new ServletException(e);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(funcionalidadBonos.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/");
       // long luego= System.currentTimeMillis();
        // long difference = luego-ahora;
        // System.out.println("en comprar un bono se demora "+ difference);
    }
}
