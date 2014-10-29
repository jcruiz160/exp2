
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Remote;

/**
 * Contrato funcional de los servicios de administración de las tiendas del sistema
 */
@Remote
public interface IServicioTiendaMockRemote
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
    public void eliminarTienda(String id)throws OperacionInvalidaException;

    /**
     * Devuelve todas las tiendas del sistema
     * @return tiendas Tiendas del sistema
     */
    public List<Tienda> getTiendas();
}
