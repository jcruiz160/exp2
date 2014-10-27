package servlets;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.servicios.IServicioBonosMockLocal;
import com.tumejoropcion.servicios.ServicioBonosMock;
import com.tumejoropcion.servicios.ServicioPersistenciaMock;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class redimirBono extends HttpServlet {
    

    private static final long serialVersionUID = -7453606094644144082L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
long ahora= System.currentTimeMillis();
        System.out.println("entró al servlet");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String codigo = request.getParameter("message1");

        try {
            Bono lb = (Bono) ServicioPersistenciaMock.darInstancia().findById(Bono.class, codigo);

            boolean resp = lb.redimido();
            if (!resp) {
                resp = true;
            } else {
                resp = false;
            }

            facebook.postStatusMessage("se pudo redimir el bono?: " + resp + " con codigo: " + codigo);

        } catch (FacebookException e) {
            System.out.println("perdidas");
            throw new ServletException(e);

        }
        response.sendRedirect(request.getContextPath() + "/");
        long luego= System.currentTimeMillis();
        long difference = luego-ahora;
       // System.out.println("en redimir un bono se demora"+ difference);
      List bonos =  ServicioPersistenciaMock.darInstancia().findAll(Bono.class);
       for (int i = 0; i < bonos.size(); i++){
                Bono object =(Bono) bonos.get(i);
               // System.out.println(i+"   "+object.darIdComprador());
            }
        
    }
     
}
