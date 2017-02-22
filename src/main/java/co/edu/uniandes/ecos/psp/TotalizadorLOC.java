/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Clase encargada de totalizar el conteo de LOC de cada archivo encontrado
/* Notas especiales:
/******************************************************************/
package co.edu.uniandes.ecos.psp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase encargada de totalizar el conteo de LOC de cada archivo encontrado
 * Created by edgaguil on 21/02/2017.
 */
public class TotalizadorLOC
{

    //Ruta del directorio en el cual se realiza el conteo
    private String rutaDirectorio;

    //Objeto que realiza el conteo de lineas de codigo en un solo archivo de codigo java
    private ContadorLOC contadorLOC;

    //Objeto que almacena el listado de archivos java contenidos en todos los direcorios hijos del direcotrio padre especificado
    private List<File> listadoArchivos;

    /**
     * Constructor de la clase
     * @param rutaDirectorio
     */
    public  TotalizadorLOC(String rutaDirectorio)
    {
        this.rutaDirectorio = rutaDirectorio;
        this.listadoArchivos = new ArrayList<File>();
    }

    /**
     * Metodo encargado de calcular el LOC en un directorio
     * @return Resultados de los calculos
     */
    public ModeloResultado obtenerLOCDirectorio() throws FileNotFoundException, IOException, Exception
    {
        ModeloResultado modeloResultado = new ModeloResultado();
        buscarArchivosCodigoFuente(this.rutaDirectorio);

        for (File archivo : listadoArchivos)
        {
            ContadorLOC contadorLOC = new ContadorLOC(archivo.getPath());
            ModeloResultado resultadoLOCArchivo = contadorLOC.obtenerLOCArchivo();
            modeloResultado.setNumeroClases(modeloResultado.getNumeroClases() + resultadoLOCArchivo.getNumeroClases());
            modeloResultado.setNumeroLOCClases(modeloResultado.getNumeroLOCClases() + resultadoLOCArchivo.getNumeroLOCClases());
            modeloResultado.setNumeroMetodos(modeloResultado.getNumeroMetodos() + resultadoLOCArchivo.getNumeroMetodos());
            modeloResultado.setNumeroLOCMetodos(modeloResultado.getNumeroLOCMetodos() + resultadoLOCArchivo.getNumeroLOCMetodos());
            //modeloResultado.setNumeroTotalLOC(modeloResultado.getNumeroTotalLOC() + modeloResultado.getNumeroLOCClases());
        }

        return modeloResultado;
    }

    /**
     * Metodo encargado de buscar cargar el listado de archivos java en el direcorio especificado
      * @param rutaRaiz
     */
    private void buscarArchivosCodigoFuente(String rutaRaiz)
    {
        File archivoRaiz = new File(rutaRaiz);

        File[] archivos = archivoRaiz.listFiles();

        for (File archivo : archivos)
        {
            if (archivo.isFile())
            {
                    if (archivo.getName().endsWith(".java"))
                    {
                        listadoArchivos.add(archivo);
                    }

            }
            else if (archivo.isDirectory())
            {
                buscarArchivosCodigoFuente(archivo.getPath());
            }
        }
    }
}
