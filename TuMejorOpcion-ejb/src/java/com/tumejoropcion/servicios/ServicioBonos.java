package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.exception.OperacionInvalidaException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.tumejoropcion.servicios.ServicioPersistenciaNoSql;

/**
 * Implementación de los servicios de administración de un vendedor en el
 * sistema
 *
 * @author Juan Sebastián Urrego
 */
@Singleton
public class ServicioBonos implements IServicioBonosMockLocal {

    private static Properties prop = new Properties();
    private static InputStream input = null;

    static {
        try {
            input
                    = ServicioBonos.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
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
    public ServicioBonos() {
        persistencia = new ServicioPersistenciaNoSql(); //(IServicioPersistenciaLocal) new InitialContext().lookup(prop.getProperty("jndi_persistenciaMongo"));

    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Agrega un bono al sistema
     *
     * @param a Elbono a agregar
     */
    @Override
    public void agregarBono(Bono a) throws OperacionInvalidaException {
        try {
            persistencia.create(a);
        } catch (OperacionInvalidaException ex) {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    /**
     * Elimina un bono del sistema
     *
     * @param codigo El codigo único del bono
     * @throws OperacionInvalidaException
     */
    @Override
    public void eliminarBono(String codigo) throws OperacionInvalidaException {
        Bono b = (Bono) persistencia.findById(Bono.class, codigo);
        try {
            persistencia.delete(b);
        } catch (OperacionInvalidaException ex) {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    @Override
    public List<Bono> getBonos() {
        return persistencia.findAll(Bono.class);
    }

    @Override
    public boolean redimirBono(String cod) {
        Bono este = (Bono) persistencia.findById(Bono.class, cod);
        boolean a = este.redimir();
        persistencia.update(este);
        return a;
        }
       
    

    @Override
    public Bono buscarBono(String codigo) {
        return (Bono) persistencia.findById(Bono.class, codigo);
    }

}
