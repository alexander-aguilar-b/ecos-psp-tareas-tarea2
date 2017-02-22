package co.edu.uniandes.ecos.psp;

/**
 * Created by edgaguil on 21/02/2017.
 */
public class ModeloResultado {

    // Numero total de LOC en el directorio
    private int numeroTotalLOC;

    // Numero total de clases directorio
    private int numeroClases;

    // Numero total de LOC en las clases
    private int numeroLOCClases;

    // Numero total de metodos
    private int numeroMetodos;

    // Numero total de LOC en los metodos
    private int numeroLOCMetodos;

    // Obtiene el numero total de lineas de codigo en el directorio
    public int getNumeroTotalLOC()
    {
        return numeroTotalLOC;
    }

    // Establece el numero total de lineas de codigo en el directorio
    public void setNumeroTotalLOC(int numeroTotalLOC)
    {
        this.numeroTotalLOC = numeroTotalLOC;
    }

    // Obtiene el numero total de clases
    public int getNumeroClases()
    {
        return numeroClases;
    }

    // Establece el numero total clases
    public void setNumeroClases(int numeroClases)
    {
        this.numeroClases = numeroClases;
    }

    // Obtiene el numero total de lineas de codigo las clases
    public int getNumeroLOCClases()
    {
        return numeroLOCClases;
    }

    public void setNumeroLOCClases(int numeroLOCClases)
    {
        this.numeroLOCClases = numeroLOCClases;
    }

    public int getNumeroMetodos()
    {
        return numeroMetodos;
    }

    public void setNumeroMetodos(int numeroMetodos)
    {
        this.numeroMetodos = numeroMetodos;
    }

    public int getNumeroLOCMetodos()
    {
        return numeroLOCMetodos;
    }

    public void setNumeroLOCMetodos(int numeroLOCMetodos)
    {
        this.numeroLOCMetodos = numeroLOCMetodos;
    }
}
