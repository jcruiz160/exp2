
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Local;


/**
 * Contrato funcional de los servicios de administración de las tiendas del sistema
 */
@Local
public interface IServicioTiendaMockLocal
{
    /**
     * Agrega una tienda al sistema
     * @param tienda Nueva tienda
     * @throws com.tumejoropcion.exception.OperacionInvalidaException
     */
    public void agregarTienda(Tienda tienda)throws OperacionInvalidaException;

    /**
     * Elimina una tienda del sistema
     * @param id Identificador único de la tienda
     * @throws com.tumejoropcion.exception.OperacionInvalidaException
     */
    public void eliminarTienda(int id)throws OperacionInvalidaException;

    /**
     * Devuelve todas las tiendas del sistema
     * @return tiendas del sistema
     */
    public List<Tienda> getTiendas();
}
