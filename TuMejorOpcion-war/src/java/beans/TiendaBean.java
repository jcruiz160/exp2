/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ tiendaBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Tiendas de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package beans;

import com.tumejoropcion.bos.Bono;
import com.tumejoropcion.bos.Tienda;
import com.tumejoropcion.exception.OperacionInvalidaException;
import com.tumejoropcion.servicios.IServicioBonosMockLocal;
import com.tumejoropcion.servicios.IServicioPersistenciaMockLocal;
import com.tumejoropcion.servicios.IServicioTiendaMockLocal;
import com.tumejoropcion.servicios.ServicioBonosMock;
import com.tumejoropcion.servicios.ServicioPersistenciaMock;
import com.tumejoropcion.servicios.ServicioTiendaMock;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * Managed bean encargado de la lista de tiendas en el sistema
 * @author Juan Sebastián Urrego
 */
public class TiendaBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Representa una nueva tienda a ingresar
     */
    private Tienda tienda;

    /**
     * Relación con la interfaz que provee los servicios necesarios del catálogo.
     */
    private IServicioTiendaMockLocal servicio;
    
    private IServicioBonosMockLocal contactoBono;
    


    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase principal
     */
    public TiendaBean()
    {
        tienda=new Tienda();
        servicio=new ServicioTiendaMock();
        contactoBono = new ServicioBonosMock();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el objeto tienda
     * @return tienda Objeto tienda
     */
    public Tienda getTienda()
    {
        return tienda;
    }

    /**
     * Modifica el objeto tienda
     * @param tienda Nuevo tienda
     */
    public void setTienda(Tienda tienda)
    {
        this.tienda = tienda;
    }

    /**
     * Devuelve una lista con todos los tiendas del sistema
     * @return tiendas Tiendas del sistema
     */
    public List<Tienda> getTiendas()
    {

        return servicio.getTiendas();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un nuevo tienda al sistema
     */
    public void agregarTienda() throws OperacionInvalidaException
    {
        servicio.agregarTienda(tienda);
        tienda=new Tienda();
    }

    /**
     * Elimina un tienda del sistema
     * @param evento Evento que tiene como parámetro el ID del tienda
     */
    public void eliminarTienda(ActionEvent evento) throws OperacionInvalidaException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();

        servicio.eliminarTienda(tienda.darIdentificador());
    }
    
    
              
      /**
       * verifica si el bono está activo y/o vigente
       * @param codBono
       * @return true si está activo, false si ya se venció el bono
       */
        public boolean verficarSiBonoEstaActivo(String codBono){
         Date hoy=new Date();
         for(int i =0; i< tienda.darBonos().size();i++){
         Bono actual= tienda.darBonos().get(i);
         if(actual.darCodigo()==(codBono)){
             if(actual.darFechaVencimiento().after(hoy)){
                    actual.caduco();
                 return false;   
             }else
                 return actual.estaVigente();
            }
           }
           return false;
        }
        /**
         * Eliminar un bono desde una tienda
         * @param codigo 
         */
        public void eliminarBono(String codigo){
           for(int i =0; i< tienda.darBonos().size();i++){
               Bono actual= tienda.darBonos().get(i);
                if(actual.darCodigo()==(codigo)){
                    tienda.darBonos().remove(actual);
                }
           }
        }
        
        /**
         * agregar bono a una tienda con un monto de dinero dado.
         * @param valor 
         */
        public void agregarBono(int valor) throws OperacionInvalidaException{
            Bono b = new Bono( valor, "zara",new Date(System.currentTimeMillis()),"userDefault");
            contactoBono.agregarBono(b);
                       
        }
      
    }
