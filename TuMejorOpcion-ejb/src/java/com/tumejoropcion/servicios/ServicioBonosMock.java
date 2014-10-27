
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 * Implementación de los servicios de administración de un vendedor en el sistema
 * @author Juan Sebastián Urrego
 */
@Singleton
public class ServicioBonosMock implements IServicioBonosMockRemote, IServicioBonosMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase sin argumentos
     */
    public ServicioBonosMock()
    { persistencia = new ServicioPersistenciaMock();
    }
    
    

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un bono al sistema
     * @param a Elbono a agregar
     */
    @Override
    public void agregarBono(Bono a) throws OperacionInvalidaException
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
     * Elimina un bono del sistema
     * @param codigo El codigo único del bono
     * @throws OperacionInvalidaException 
     */
    @Override
    public void eliminarBono(String codigo)throws OperacionInvalidaException
    {
        Bono b = (Bono) persistencia.findById(Bono.class, codigo);     
        try
        {
            persistencia.delete(b);
        } catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    @Override
    public List<Bono> getBono()
    {
        return persistencia.findAll(Bono.class);
    }
    
    @Override
    public boolean redimirBono(String cod)
    {
        Bono este =(Bono) persistencia.findById(Bono.class, cod);
        if(este.estaVigente()){
            if(!este.redimido()) {
                return true;
            }
        }
        return  false;
    }

    
    
}
