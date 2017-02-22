/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Vista de entrada de la aplicacion, encargada de solicitar la ruta del directorio raiz para realizar el conteo de lineas de codigo.
/* Notas especiales:
/******************************************************************/

package co.edu.uniandes.ecos.psp;

/**
 * Vista de entrada de la aplicacion, encargada de solicitar la ruta del directorio raiz para realizar el conteo de lineas de codigo
 * Created by edgaguil on 21/02/2017.
 */
public class VistaEntrada
{
    /*
    * Metodo encargado de capturar la ruta del directorio a partir de la cual se va a realizar la busqueda recursiva de archivos .java sobre los cuales se va a efectuar el conteo de LOC
    * */
    public String obtenerRutaDirectorio()
    {
        System.out.println("Programa para el Conteo de Lineas de Codigo (LOC) - PSP2");
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println("Estimado usuario, por favor ingrese la ruta del directorio que contiene los archivos .java de los cuales desea calcular el numero de lineas de codigo (LOC)");
        System.out.println();
        System.out.println("Ruta Directorio:");
        String rutaDirectorio = System.console().readLine();
        return rutaDirectorio;
    }
}

