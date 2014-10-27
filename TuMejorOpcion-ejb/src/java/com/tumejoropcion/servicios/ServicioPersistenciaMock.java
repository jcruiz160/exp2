
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.bos.Usuario;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Singleton;

/**
 * Implementación de los servicios de persistencia
 */
@Singleton
public class ServicioPersistenciaMock implements IServicioPersistenciaMockRemote, IServicioPersistenciaMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private static ServicioPersistenciaMock yo = null;
    /**
     * Lista con los vendedores del sistema
     */
    private ArrayList<Bono> bonos;

    /**
     * Lista con los muebles del sistema
     */
    private ArrayList<Tienda> tiendas;

    /**
     * Lista con los usuarios del sistema
     */
    private ArrayList<Usuario> usuarios;

      //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock()
    {
        if (tiendas == null )
        {
            bonos = new ArrayList<Bono>();
            
            GregorianCalendar c = new GregorianCalendar(2,12,2014);
            Date y = c.getTime();
            
           
            
            tiendas = new ArrayList<Tienda>();

            tiendas.add(new Tienda("Zara", 1));
            tiendas.add(new Tienda("Arturo Calle",2));
            tiendas.add(new Tienda("Fuera de Serie",3));
            tiendas.add(new Tienda("Bkul", 4));
            tiendas.add(new Tienda("Lec Lee", 5));
            tiendas.add(new Tienda("Studio F",6));
            tiendas.add(new Tienda("Pronto", 7));
            tiendas.add(new Tienda("Armi", 8));

            usuarios = new ArrayList<Usuario>();

            usuarios.add(new Usuario("admin"));
            usuarios.add(new Usuario("client"));
            
            for(int i = 0; i < tiendas.size(); i++) {
                Tienda t = tiendas.get(i);
               // t.agregarBonosConReferenciaYo(bonos);
            }
            
        }
    }

    /**
     *
     * @return
     */
    public static ServicioPersistenciaMock darInstancia(){
        if(yo==null){
            yo = new ServicioPersistenciaMock();
        }
        return yo;
    }
    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    
    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException
    {
        if (obj instanceof Tienda)
        {
            Tienda t = (Tienda) obj;
            t.cambiarIdentificador(tiendas.size() + 1);
            tiendas.add(t);
        }
        else if (obj instanceof Bono)
        {

            Bono b = (Bono) obj;
            bonos.add(b);
        } 
        else if (obj instanceof Usuario)
        {
            Usuario u = (Usuario) obj;
            for (Usuario us : usuarios)
            {
                if (us.getEmail().equals(u.getEmail()))
                {
                    throw new OperacionInvalidaException("El usuario '" + u.getEmail() + "' ya ha sido registrado en el sistema");
                }
                
            }
            usuarios.add(u);
        }
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
     */
    @Override
    public void update(Object obj)
    {
        if (obj instanceof Tienda)
        {
            Tienda editar = (Tienda) obj;
            Tienda tienda;
            for (int i = 0; i < tiendas.size(); i++)
            {
                tienda = tiendas.get(i);
                if (tienda.darIdentificador() == editar.darIdentificador())
                {
                    tiendas.set(i, editar);
                    break;
                }

            }

        }
        else if (obj instanceof Bono)
        {
            Bono editar = (Bono) obj;
            Bono bono;
            for (int i = 0; i < bonos.size(); i++)
            {
                bono = bonos.get(i);
                if (bono.darCodigo() == editar.darCodigo())
                {
                    bonos.set(i, editar);
                    break;
                }
            }
        } 
        else if (obj instanceof Usuario)
        {

            Usuario editar = (Usuario) obj;
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++)
            {
                usuario = usuarios.get(i);
                if (usuario.getEmail().equals(editar.getEmail()))
                {
                    usuarios.set(i, editar);
                    break;
                }
            }
        }
    }

    /**
     * Permite borrar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere borrar.
     * @throws com.tumejoropcion.exception.OperacionInvalidaException
     */
    @Override
   public void delete(Object obj) throws OperacionInvalidaException
    {
        if (obj instanceof Tienda)
        {
            Tienda tiendaABorrar = (Tienda) obj;

            for (int e = 0; e < tiendas.size(); e++)
            {
                Tienda ven = (Tienda) tiendas.get(e);
                if (ven.darIdentificador()== tiendaABorrar.darIdentificador())
                {
                    tiendas.remove(e);
                    break;
                }
            }

        } 
        else if (obj instanceof Bono)
        {
            Bono bono;
            Bono eliminar = (Bono) obj;
            for (int i = 0; i < bonos.size(); i++)
            {
                bono = bonos.get(i);
                if (eliminar.darCodigo() == bono.darCodigo())
                {
                    bonos.remove(i);
                    break;
                }

            }

        } 
    }

    /**
     * Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
     */
    @Override
    public List findAll(Class c)
    {
        if (c.equals(Bono.class))
        {
            return bonos;
        } 
        else if (c.equals(Tienda.class))
        {
            return tiendas;
        } 
        else if (c.equals(Usuario.class))
        {
            return usuarios;
        } 
        else
        {
            return null;
        }
    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidadi.
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id)
    {
        if (c.equals(Tienda.class))
        {
            for (Object t : findAll(c))
            {
                Tienda tien = (Tienda) t;
                if (tien.darIdentificador() == (int)id)
                {
                    return tien;
                }
            }
        } 
        else if (c.equals(Bono.class))
        {
            for (Object b : findAll(c))
            {
                Bono bon = (Bono) b;
                if (((String) id).equals(bon.darCodigo()))
                {
                    return bon;
                }
            }
        } 
        else if (c.equals(Usuario.class))
        {
            for (Object v : findAll(c))
            {
                Usuario mue = (Usuario) v;
                if (mue.getEmail().equals(id))
                {
                    return mue;
                }
            }
        }
        return null;
    }

    public ArrayList<Bono> darBonosDeUsuario(String idComprador){
        System.out.println("entro");
        System.out.println(" el id es " +idComprador);
        ArrayList<Bono> respuesta=new ArrayList();
        if(bonos.size()!=0){
            System.out.println("entro al if");
        for (int i = 0; i < bonos.size(); i++) {
            
            Bono actual= bonos.get(i);
            System.out.println("getio el bono de  "+ actual.darIdComprador());
            
            if(actual.darIdComprador().equals(idComprador)){
                respuesta.add(actual);
            }          
        }}
        System.out.println("va a decir algo");
        System.out.println("el arraylist de bonos del us "+respuesta.size());
        return respuesta;
        
    }
    
    public ArrayList darUsuariosPorBonos(){
        ArrayList<String> u2= new ArrayList();
        for(int i = 0; i<bonos.size();i++)
        {
            String actual= bonos.get(i).darIdComprador();
            boolean esta= false;
            for(int j = 0 ; j< u2.size() ;j++)
            {
                String id= u2.get(j);
                if(id.equals(actual)){
                    esta=true;
                }
            }
            if(!esta)u2.add(actual);
       
        }
        return u2;
    }
}
