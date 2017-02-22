/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Controlador encargado de orquestar  el flujo del programa.
/* Notas especiales:
/******************************************************************/

package co.edu.uniandes.ecos.psp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controlador de la aplicación
 * Created by edgaguil on 21/02/2017.
 */
public class ControladorLOC
{
    // Objeto totalizador de lineas de codigo en el directorio raiz
    private TotalizadorLOC totalizadorLOC;

    //Objeto vista de entrada. Encargado de solicitar la ruta del directorio raiz
    private VistaEntrada vistaEntrada;

    //Objeto enjcargado de mostrar el resultado del conteo
    private VistaSalida vistaSalida;

    //Constructor de la clase
    public ControladorLOC()
    {
        vistaEntrada = new VistaEntrada();
        vistaSalida = new VistaSalida();
    }

    /**
     * Metod encargado de obtener el conteo de lineas de codigo en el directorio especificado
     */
    public void obtenerLOCDirectorio()
    {
        try
        {
            String rutaDirectorio = vistaEntrada.obtenerRutaDirectorio();

            File f = new File(rutaDirectorio);

            if (f.exists()) {
                totalizadorLOC = new TotalizadorLOC(rutaDirectorio);
                ModeloResultado modeloResultado = totalizadorLOC.obtenerLOCDirectorio();
                vistaSalida.mostrarResultados(modeloResultado);
            }
            else
            {
                throw new Exception("Por favor verifique la ruta ingresada");
            }
        }
        catch (Exception ex)
        {
            vistaSalida.mostrarMensajeError(ex);
        }
    }
}
