
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Singleton;

/**
 * Implementación de los servicios de administración de una tienda en el sistema
 */
@Singleton
public class ServicioTiendaMock implements IServicioTiendaMockRemote, IServicioTiendaMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
  
    private IServicioPersistenciaLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase sin argumentos
     */
    public ServicioTiendaMock()
    {   persistencia =new ServicioPersistenciaNoSql();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega una tienda al sistema
     * @param a La tienda a agregar
     */
    @Override
    public void agregarTienda(Tienda a)throws OperacionInvalidaException
    {
        try
        {
            persistencia.create(a);
        }
        catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    /**
     * Elimina una tienda del sistema
     * @param id El identificador único de la tienda 
     * @throws OperacionInvalidaException 
     */
    @Override
    public void eliminarTienda(String id)throws OperacionInvalidaException
    {
        Tienda b = (Tienda) persistencia.findById(Tienda.class, id);    
        try
        {
            persistencia.delete(b);
        } catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    @Override
    public List<Tienda> getTiendas()
    {
        return persistencia.findAll(Tienda.class);
    }

}
