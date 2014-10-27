package servlets;

import com.tumejoropcion.servicios.ServicioPersistenciaMock;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Friendlist;
import facebook4j.ResponseList;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class verAmigos extends HttpServlet {
    private static final long serialVersionUID = 4179545353414298791L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        try {
            String  respuesta= "Tus amigos en la aplicación son: \n";
            ResponseList<Friend> friends = facebook.getFriends();
            ArrayList<String> amigos= ServicioPersistenciaMock.darInstancia().darUsuariosPorBonos();
            for(int i = 0 ; i< amigos.size(); i++){
                String actual= amigos.get(i);
                for(int j=0; j< friends.size();j++)
                {
                    String idotro=friends.get(j).getId();
                    if(actual.equals(idotro))
                    {
                        respuesta += friends.get(j).getFirstName()+"\n";
                    }
                }
            }
           facebook.postStatusMessage(respuesta);
            
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath()+ "/");
    }
    
}
