
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.List;
import java.util.UUID;
import javax.ejb.Remote;

/**
 * Contrato funcional de los servicios de administración de los vendedores del sistema
 * @author Juan Sebastián Urrego
 */
@Remote
public interface IServicioBonosMockRemote
{
    /**
     * Agrega un bono al sistema
     * @param bono Nuevo bono
     * @throws com.tumejoropcion.exception.OperacionInvalidaException
     */
    public void agregarBono(Bono bono)throws OperacionInvalidaException;

    /**
     * Elimina un bono del sistema
     * @param codigo Número de identificación único del bono
     * @throws com.tumejoropcion.exception.OperacionInvalidaException
     */
    public void eliminarBono(String codigo)throws OperacionInvalidaException;

    /**
     * Devuelve todas los bonos del sistema
     * @return bonos Bonos del sistema
     */
    public List<Bono> getBono();
    
     /**
     * Redimir bono con codigo dado
     * @param cod
     * @return treu si se pudo redimir
     */
    public boolean redimirBono(String cod);
}
