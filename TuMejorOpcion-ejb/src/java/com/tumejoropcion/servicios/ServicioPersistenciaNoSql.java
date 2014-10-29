package com.tumejoropcion.servicios;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tumejoropcion.bos.Administrador;
import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.bos.Usuario;
import com.tumejoropcion.bos.UsuarioTienda;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 * Implementación de los servicios de persistencia
 */
@Stateless
@EJB(beanInterface = IServicioPersistenciaLocal.class, beanName = "ServicioPersistenciaNoSql", name = "servicioPersistenciaNoSql")

public class ServicioPersistenciaNoSql extends MongoConfig implements IServicioPersistenciaLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private static ServicioPersistenciaNoSql yo = null;

    //-------------------------------
    // Constructor
    //-------------------------------
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaNoSql() {

    }
    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     *
     * @param obj Objeto que representa la instancia de la entidad que se quiere
     * crear.
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        DBCollection coll = null;
        BasicDBObject doc = null;
        if (obj instanceof Bono) {
            Bono bono = (Bono) obj;
            coll = db.getCollection("Bono");
            doc = new BasicDBObject();
            doc.append("_id", bono.getCodigo());
            doc.append("valor", bono.darValor());

        } else if (obj instanceof Tienda) {
            Tienda mue = (Tienda) obj;
            coll = db.getCollection("Tienda");
            doc = new BasicDBObject();
            doc.append("nombre", mue.getNombre());

        } else if (obj instanceof UsuarioTienda) {
            UsuarioTienda mue = (UsuarioTienda) obj;
            coll = db.getCollection("UsuarioTienda");
            doc = new BasicDBObject();
            doc.append("nombre", mue.getNombre());
            // doc.append("id", mue.getId());
            doc.append("contrasenia", mue.getContrasenia());

        } else if (obj instanceof Usuario) {
            Usuario mue = (Usuario) obj;
            coll = db.getCollection("Usuario");
            doc = new BasicDBObject();
            doc.append("nombre", mue.getNombre());
            doc.append("_id", mue.getId());
            doc.append("email", mue.getEmail());

        } else if (obj instanceof Administrador) {
            Administrador mue = (Administrador) obj;
            coll = db.getCollection("Administrador");
            doc = new BasicDBObject();
            doc.append("nombre", mue.getNombre());
            // doc.append("id", mue.getId());
            doc.append("contrasenia", mue.getContrasenia());

        }
        coll.insert(doc);
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     *
     * @param obj Objeto que representa la instancia de la entidad que se quiere
     * modificar.
     */
    @Override
    public void update(Object obj) {
        DBCollection coll = null;
        BasicDBObject doc = null;
        BasicDBObject searchQuery = null;
        if (obj instanceof UsuarioTienda) {
            UsuarioTienda utienda = (UsuarioTienda) obj;
            coll = db.getCollection("UsuarioTienda");
            doc = new BasicDBObject();
            doc.append("$set", new BasicDBObject().append("nombre", utienda.getNombre()));
            doc.append("$set", new BasicDBObject().append("contraseña", utienda.getContrasenia()));
            searchQuery = new BasicDBObject().append("_id", utienda.getId());
        } else if (obj instanceof Usuario) {
            Usuario user = (Usuario) obj;
            coll = db.getCollection("Usuario");
            doc = new BasicDBObject();
            doc.append("$set", new BasicDBObject().append("nombre", user.getNombre()));
            searchQuery = new BasicDBObject().append("_id", user.getId());
        }
        coll.update(searchQuery, doc);
    }

    /**
     * Retorna la lista de todos los elementos de una clase dada que se
     * encuentran en el sistema.
     *
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se
     * encuentran en el sistema.
     */
    @Override
    public ArrayList<Object> findAll(Class c) {
        ArrayList lista = new ArrayList();
        
        if (c.equals(Tienda.class)) {
            DBCollection coll = db.getCollection("Tienda");
            BasicDBObject query = new BasicDBObject();
            DBCursor cursor = coll.find(query);
            while (cursor.hasNext()) {
                DBObject dBObject = cursor.next();
                Tienda tienda = new Tienda();
                tienda.setNombre((String) dBObject.get("nombre"));
                tienda.setIdentificador(dBObject.get("_id").toString());
                lista.add(tienda);
            }
            cursor.close();

        } else if (c.equals(Usuario.class)) {
            DBCollection coll = db.getCollection("Usuario");
            BasicDBObject query = new BasicDBObject();
            DBCursor cursor = coll.find(query);
            while (cursor.hasNext()) {
                DBObject dBObject = cursor.next();
                Usuario usuario = new Usuario();
                usuario.setNombre((String) dBObject.get("nombre"));
                usuario.setId((int) dBObject.get("_id"));
                usuario.setEmail((String) dBObject.get("email"));
                lista.add(usuario);
            }
            cursor.close();

        }else if (c.equals(UsuarioTienda.class)) {
            DBCollection coll = db.getCollection("UsuarioTienda");
            BasicDBObject query = new BasicDBObject();
            DBCursor cursor = coll.find(query);
            while (cursor.hasNext()) {
                DBObject dBObject = cursor.next();
                UsuarioTienda usuario = new UsuarioTienda();
                usuario.setContrasenia((String) dBObject.get("contrasenia"));
                usuario.setNombre((String) dBObject.get("nombre"));
                usuario.setId(dBObject.get("_id").toString());
                lista.add(usuario);
            }
            cursor.close();

        }else if (c.equals(Administrador.class)) {
            DBCollection coll = db.getCollection("Administrador");
            BasicDBObject query = new BasicDBObject();
            DBCursor cursor = coll.find(query);
            while (cursor.hasNext()) {
                DBObject dBObject = cursor.next();
                Administrador usuario = new Administrador();
                usuario.setNombre((String) dBObject.get("nombre"));
                usuario.setContrasenia((String) dBObject.get("contrasenia"));
                usuario.setId(dBObject.get("_id").toString());
                lista.add(usuario);
            }
            cursor.close();

        }
        return lista;

    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de
     * la entidadi.
     *
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id) {
        Object cosa= null;
        if (c.equals(UsuarioTienda.class)) {
            ArrayList a = findAll(c);
            for (int i = 0; i < a.size(); i++) {

                UsuarioTienda usu = (UsuarioTienda) a.get(i);
                if (usu.getId().equalsIgnoreCase((String) id)) {
                    cosa = usu;
                }
             }  
        }else if (c.equals(Usuario.class)) {
            ArrayList a = findAll(c);
            for (int i = 0; i < a.size(); i++) {

                Usuario usu = (Usuario) a.get(i);
                if (usu.getId()== id) {
                    cosa = usu;
                }
             }  
        }else if (c.equals(Administrador.class)) {
            ArrayList a = findAll(c);
            for (int i = 0; i < a.size(); i++) {

                Administrador usu = (Administrador) a.get(i);
                if (usu.getId().equalsIgnoreCase((String) id)) {
                    cosa = usu;
                }
             }  
        }else if (c.equals(UsuarioTienda.class)) {
            ArrayList a = findAll(c);
            for (int i = 0; i < a.size(); i++) {

                UsuarioTienda usu = (UsuarioTienda) a.get(i);
                if (usu.getId().equalsIgnoreCase((String) id)) {
                    cosa = usu;
                }
             }  
        }
        return cosa;
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
