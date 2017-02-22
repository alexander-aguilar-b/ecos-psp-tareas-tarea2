/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Programa PSP2 encargado de realizar el conteo de lineas de codigo en archivos .java a a partir de una ruta especificada
/* Notas especiales:
/******************************************************************/

package co.edu.uniandes.ecos.psp;

/**
 * Programa PSP2 encargado de realizar el conteo de lineas de codigo en archivos .java a a partir de una ruta especificada
 * Created by edgaguil on 21/02/2017.
 */
public class PSPProgram2
{
    public static void main(String[] args)
    {
        ControladorLOC controladorLOC = new ControladorLOC();
        controladorLOC.obtenerLOCDirectorio();
    }
}
