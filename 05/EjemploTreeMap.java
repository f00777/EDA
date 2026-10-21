import java.util.Map;
import java.util.TreeMap;

/**
 * Ejemplos de uso de java.util.TreeMap
 * (mapa clave -> valor ordenado por clave, implementado con un Arbol Rojo-Negro).
 *
 * Compilar:  javac EjemploTreeMap.java
 * Ejecutar:  java EjemploTreeMap
 */
public class EjemploTreeMap {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. Insercion: put(clave, valor)  -  O(log n)
        // ---------------------------------------------------------------
        TreeMap<Integer, String> alumnos = new TreeMap<>();
        alumnos.put(20, "Ana");
        alumnos.put(10, "Benja");
        alumnos.put(30, "Carla");
        alumnos.put(15, "Diego");
        alumnos.put(25, "Elisa");
        alumnos.put(12, "Fran");
        System.out.println("Mapa (ordenado por clave): " + alumnos);

        // put sobre una clave existente REEMPLAZA el valor y retorna el anterior
        String anterior = alumnos.put(15, "Daniela");
        System.out.println("put(15, Daniela) reemplazo a: " + anterior);
        System.out.println();

        // ---------------------------------------------------------------
        // 2. Busqueda  -  O(log n)
        // ---------------------------------------------------------------
        System.out.println("get(25)          = " + alumnos.get(25));
        System.out.println("get(27)          = " + alumnos.get(27));
        System.out.println("containsKey(12)  = " + alumnos.containsKey(12));
        System.out.println("getOrDefault(99) = " + alumnos.getOrDefault(99, "(no existe)"));
        System.out.println("containsValue(\"Ana\") = " + alumnos.containsValue("Ana") + "  <- O(n)!");
        System.out.println();

        // ---------------------------------------------------------------
        // 3. Navegacion (NavigableMap)
        // ---------------------------------------------------------------
        System.out.println("firstKey()      = " + alumnos.firstKey());
        System.out.println("lastEntry()     = " + alumnos.lastEntry());
        System.out.println("floorKey(14)    = " + alumnos.floorKey(14));     // mayor <= 14
        System.out.println("ceilingKey(16)  = " + alumnos.ceilingKey(16));   // menor >= 16
        System.out.println("headMap(15)     = " + alumnos.headMap(15));      // claves < 15
        System.out.println("tailMap(15)     = " + alumnos.tailMap(15));      // claves >= 15
        System.out.println("descendingMap() = " + alumnos.descendingMap());
        System.out.println();

        // ---------------------------------------------------------------
        // 4. Eliminacion  -  O(log n)
        // ---------------------------------------------------------------
        System.out.println("remove(30)        -> " + alumnos.remove(30) + "  " + alumnos);
        System.out.println("pollFirstEntry()  -> " + alumnos.pollFirstEntry() + "  " + alumnos);
        System.out.println();

        // ---------------------------------------------------------------
        // 5. Recorrido en orden de las entradas
        // ---------------------------------------------------------------
        for (Map.Entry<Integer, String> e : alumnos.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 6. Aplicacion: frecuencia de palabras en orden alfabetico
        // ---------------------------------------------------------------
        String texto = "rojo negro rojo arbol heap arbol rojo cola";
        TreeMap<String, Integer> frecuencia = new TreeMap<>();
        for (String p : texto.split(" ")) {
            frecuencia.merge(p, 1, Integer::sum);
        }
        System.out.println("Frecuencias: " + frecuencia);

        // ---------------------------------------------------------------
        // 7. Aplicacion: tramos de notas (busqueda por rango con floorEntry)
        // ---------------------------------------------------------------
        TreeMap<Double, String> tramos = new TreeMap<>();
        tramos.put(1.0, "Reprobado");
        tramos.put(4.0, "Aprobado");
        tramos.put(5.5, "Bueno");
        tramos.put(6.5, "Excelente");
        for (double nota : new double[]{3.2, 4.0, 5.9, 7.0}) {
            System.out.println("Nota " + nota + " -> " + tramos.floorEntry(nota).getValue());
        }
    }
}
