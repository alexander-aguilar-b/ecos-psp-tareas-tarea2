/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 21/02/2017
/* Propósito: Clase encargada de realizar el conteo de LOC en un archivo java.
/* Notas especiales:
/******************************************************************/

package co.edu.uniandes.ecos.psp;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase encargada de realizar el conteo de LOC en un archivo java.
 * Created by edgaguil on 21/02/2017.
 */
public class ContadorLOC
{
    //Ruta del archivo de codigo fuente java
    private String rutaArchivo;
    //Objeto que contiene el resultado de los calculos
    private ModeloResultado modeloResultado;

    //Constructor de la clase
    public ContadorLOC(String rutaArchivo)
    {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Metodo encargado de realizar el conteo de lineas de codigo en un archivo
     * @return Resultado del conteo
     * @throws Exception
     */
    public ModeloResultado obtenerLOCArchivo() throws Exception
    {
        modeloResultado  = new ModeloResultado();

        File f = new File(this.rutaArchivo);

        if (f.exists() && !f.isDirectory())
        {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            try
            {
                String linea = br.readLine();

                while (linea != null)
                {
                    linea = linea.trim();

                    if (!esLineaComentario(linea))
                    {
                        if (!esDeclaracionClase(linea))
                        {

                            if (esDeclaracionMetodo(linea))
                            {
                                modeloResultado.setNumeroMetodos(modeloResultado.getNumeroMetodos() + 1);
                                contarLineasInternasMetodo(br, false);
                            }
                        }
                        else
                        {
                            modeloResultado.setNumeroClases(modeloResultado.getNumeroClases() + 1);
                        }

                        modeloResultado.setNumeroLOCClases(modeloResultado.getNumeroLOCClases() + 1);
                    }

                    linea = br.readLine();
                }
            }
            finally
            {
                br.close();
            }
        }
        else
        {
            throw new Exception("No existe un archivo en la ruta especificada");
        }

        return modeloResultado;
    }


    /**
     * Metodo que realiza el conteo de lieas internas en un metodo
     * @param reader Reader del archivo
     * @param validarInicioBloque Indica si de bebe validar la llave de inicio de bloque
     * @throws Exception
     */
    private void contarLineasInternasMetodo(BufferedReader reader, Boolean validarInicioBloque) throws  Exception
    {
        try
        {
            String linea = reader.readLine();

            while (linea != null)
            {
                linea = linea.trim();

                if (linea.endsWith("{"))
                {
                    if(validarInicioBloque == true)
                    {
                        contarLineasInternasMetodo(reader, true);
                    }

                    validarInicioBloque = true;
                }

                if (!esLineaComentario(linea))
                {
                    modeloResultado.setNumeroLOCMetodos(modeloResultado.getNumeroLOCMetodos() + 1);
                    modeloResultado.setNumeroLOCClases(modeloResultado.getNumeroLOCClases() + 1);
                }

                if (linea.endsWith("}"))
                {
                    break;
                }

                linea = reader.readLine();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            reader.close();
        }
    }

    /**
     * Metodo encargado de identificar si la linea de codigo corresponde a una declaracion de clase
     * @param linea Linea de texto a evaluar
     * @return Valor booleano que indica si la linea corresponde a una declaracion de clase.
     */
    private Boolean esDeclaracionClase(String linea)
    {
        Boolean esDeclaracionClase = false;

        String expresionDeclaracionClaseDefault = "^class\\s+\\w+\\s*.*";
        String expresionDeclaracionClasePublico = "^public\\s+class\\s+\\w+\\s*.*";
        String expresionDeclaracionClaseProtegida = expresionDeclaracionClasePublico.replace("public", "protected");
        String expresionDeclaracionClasePrivada = expresionDeclaracionClasePublico.replace("public", "private");

        ArrayList<String> listadoExpresionesDeclaracionClase = new ArrayList<String>();

        listadoExpresionesDeclaracionClase.add(expresionDeclaracionClaseDefault);
        listadoExpresionesDeclaracionClase.add(expresionDeclaracionClasePublico);
        listadoExpresionesDeclaracionClase.add(expresionDeclaracionClaseProtegida);
        listadoExpresionesDeclaracionClase.add(expresionDeclaracionClasePrivada);

        for (String expresionDeclaracionClase : listadoExpresionesDeclaracionClase)
        {
            Pattern patronDeclaracionClase = Pattern.compile(expresionDeclaracionClase);

            Matcher matcherDeclaracionClase = patronDeclaracionClase.matcher(linea);

            esDeclaracionClase = matcherDeclaracionClase.matches();

            if (esDeclaracionClase)
            {
                break;
            }
        }

        return esDeclaracionClase;
    }


    /**
     * Metodo encargado de identificar si la linea de codigo corresponde a una declaracion de metodo
     * @param linea Linea de texto a evaluar
     * @return Valor booleano que indica si la linea corresponde a una declaracion de clase
     */
    private Boolean esDeclaracionMetodo(String linea)
    {
        Boolean esDeclaracionMetodo = false;

        String expresionDeclaracionMetodoPublico = "^public\\s+\\w*<*\\w*>*\\s*\\w*\\s*\\w+\\(.*\\)\\s*.*";
        String expresionDeclaracionMetodoProtegido = expresionDeclaracionMetodoPublico.replace("public", "protected");
        String expresionDeclaracionMetodoPrivado = expresionDeclaracionMetodoPublico.replace("public", "private");

        ArrayList<String> listadoExpresionesDeclaracionMetodo = new ArrayList<String>();

        listadoExpresionesDeclaracionMetodo.add(expresionDeclaracionMetodoPublico);
        listadoExpresionesDeclaracionMetodo.add(expresionDeclaracionMetodoProtegido);
        listadoExpresionesDeclaracionMetodo.add(expresionDeclaracionMetodoPrivado);

        for (String expresionDeclaracionMetodo : listadoExpresionesDeclaracionMetodo)
        {
            Pattern patronDeclaracionMetodo = Pattern.compile(expresionDeclaracionMetodo);

            Matcher matcherDeclaracionMetodo = patronDeclaracionMetodo.matcher(linea);

            esDeclaracionMetodo = matcherDeclaracionMetodo.matches();

            if (esDeclaracionMetodo)
            {
                break;
            }
        }

        return  esDeclaracionMetodo;
    }

    /**
     * Valida si la linea corresponde a un comentario
     * @param linea linea a evaluar
     * @return
     */
    private Boolean esLineaComentario(String linea)
    {
        Boolean esLineaComentario = false;

        if (linea.isEmpty() || linea == "" || linea.startsWith("//") || linea.startsWith("/*") || linea.startsWith("*")  || linea.startsWith("*/"))
        {
            esLineaComentario = true;
        }

        return esLineaComentario;
    }
}
