/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ BonoBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package beans;
 
 
import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.exception.OperacionInvalidaException;
import com.tumejoropcion.servicios.IServicioBonosMockLocal;
import com.tumejoropcion.servicios.ServicioBonosMock;
import com.tumejoropcion.servicios.ServicioPersistenciaMock;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
/**
 * Managed Bean encargado de la administración de vendedores en el sistema
 * @author Juan Sebastián Urrego
 */
public class BonoBean implements Serializable
{
 
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
 
    /**
     * Relación con la interfaz que provee los servicios necesarios del vendedor
     */
    
    private IServicioBonosMockLocal servicio;
 
    /**
     * Representa un nuevo bono
     */
    private Bono bono;
 
    

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public BonoBean()
    {
        bono=new Bono();
        servicio=new ServicioBonosMock();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el objeto de bono actual
     * @return bono Bono Actual
     */
    public Bono getBono()
    {
        return bono;
    }

    /**
     * Modifica al Bono actual
     * @param nuevo nuevo bono
     */
    public void setBono(Bono nuevo)
    {
        this.bono = nuevo;
    }

    /**
     * Devuelve todos los bonos del sistema
     * @return bonos Lista con todos los bonosdel sistema
     */
    public List<Bono> getBonos()
    {

        return servicio.getBono();
    }


    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un nuevo bono al sistema
     */
    public void agregarBono() throws OperacionInvalidaException
    {
        servicio.agregarBono(bono);
        bono=new Bono();
    }

   
    /**
     * Elimina un vendedor del sistema
     * @param evento Evento que contiene como parámetro el ID del vendedor
     */
    public void eliminarBono(ActionEvent evento) 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        
        try {
            servicio.eliminarBono(bono.darCodigo());
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(BonoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
}
