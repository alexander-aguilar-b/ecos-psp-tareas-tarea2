/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Vista de salida encargada de desplegar los resultados del conteo al usuario
/* Notas especiales:
/******************************************************************/
package co.edu.uniandes.ecos.psp;

/**
 * Vista de salida encargada de desplegar los resultados al usuario
 * Created by edgaguil on 21/02/2017.
 */
public class VistaSalida
{

    /**
     * Metodo que despliega los resultados de los calculos
     * @param modelo
     */
    public void mostrarResultados(ModeloResultado modelo)
    {
        System.out.println();
        System.out.println();
        System.out.println("Resultados (LOCs)");
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println("Numero de Clases            : " + modelo.getNumeroClases());
        System.out.println("Numero de LOCs en clases    : " + modelo.getNumeroLOCClases());
        System.out.println("Numero de metodos           : " + modelo.getNumeroMetodos());
        System.out.println("Numero de LOCs en metodos   : " + modelo.getNumeroLOCMetodos());
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println();
    }

    /**
     * Metodo encargado de presentar el mensaje de excepcion, en caso de que se presente alguna
     * @param ex Excepcion generada en el procesamiento
     */
    public void mostrarMensajeError(Exception ex)
    {
        System.out.print("Estimado usuario, se produjo el siguiente error al procesar la solicitud: " + ex.getMessage());
    }

}
