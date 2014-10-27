
package com.tumejoropcion.servicios;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.servicios.IServicioBonosMockRemote;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.naming.InitialContext;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase encargada de realizar pruebas unitarias
 */
public class ServicioBonoMockTest
{
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de vendedores en el sistema
     */
    private IServicioBonosMockRemote servicio;
    
    private Bono  b;
    //-----------------------------------------------------------
    // Métodos de inicialización y terminación
    //-----------------------------------------------------------

    /**
     * Método que se ejecuta antes de comenzar la prueba unitaria
     * Se encarga de inicializar todo lo necesario para la prueba
     */
    @Before
    public void setUp() throws Exception
    {
            GregorianCalendar c = new GregorianCalendar(2,12,2014);
            Date y = c.getTime();
            b = new Bono( 12000, "Lec Lee", y,"userDefault");
        try
        {
            Properties env = new Properties();
            env.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            env.put("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext contexto;
            contexto = new InitialContext(env);
            servicio = (IServicioBonosMockRemote) contexto.lookup("com.tumejoropcion.servicios.IServicioBonosMockRemote");
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    //-----------------------------------------------------------
    // Métodos de prueba
    //-----------------------------------------------------------
    
    /**
     * Método de prueba para agregar un vendedor al sistema
     */
    @Test
    public void testAgregarBono() throws Exception
    {
        Bono bono = null;
        servicio.agregarBono(bono);
    }

    /**
     * Método de prueba para eliminar un vendedor al sistema
     */
    @Test
    public void testEliminarBono() throws Exception
    {
        servicio.eliminarBono(b.darCodigo());
    }

}