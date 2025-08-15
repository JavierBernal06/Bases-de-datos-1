package CodigoPostal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class codigop2 {
    public static void main(String[] args) {
        String archivoCSV= "codigos_postales_hmo.csv"; //Ruta del archivo
        String separador=","; //Separador de columnas del csv
        int ColumnaCP= 0; //indice para cada columna

        Map<String, Integer> conteo= new HashMap<>(); //crea un mapa clave= codigo postal, valor= integer(Cantidad)

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) { //abre el archivo y garantiza que se cierre
            String linea; //variable para leer cada linea
            while ((linea = br.readLine()) != null) { //lee linea por linea
                String[] campos = linea.split(separador); //separa en columnas usando el separador
                if (campos.length > ColumnaCP) { //verifica que exista otra columna
                    String codigoPostal = campos[ColumnaCP].trim();//Quita espacios en blanco del codigo postal
                    conteo.put(codigoPostal, conteo.getOrDefault(codigoPostal, 0) + 1);
                    //actualiza el conteo en el map, el default es 0, si aparece el cp se suma 1
                    //y se devuelve al put, si vuelve a aparecer se vuelve a sumar pero ahora parte de 1
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> ordenarporCP = new TreeMap<>(conteo);
        //usa treemap para ordenar por clave(codigo postal)
        for (Map.Entry<String, Integer> entry : ordenarporCP.entrySet()) { //recorre cada (Cp,conteo) del map
             System.out.println("Codigo postal: " + entry.getKey() + " - Numero de asentamientos: " + entry.getValue() );
              //imprime una linea por codigo postal getkey obtiene la clave y getvalue el valor
            //es decir clave=codigo postal valor=Conteo
        }

    }

}
