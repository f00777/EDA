import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Ejemplos de uso de java.util.TreeSet
 * (conjunto ordenado, sin duplicados, implementado con un Arbol Rojo-Negro).
 *
 * Compilar:  javac EjemploTreeSet.java
 * Ejecutar:  java EjemploTreeSet
 */
public class EjemploTreeSet {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. Insercion: se mantiene ORDENADO y SIN DUPLICADOS
        // ---------------------------------------------------------------
        TreeSet<Integer> set = new TreeSet<>();
        int[] datos = {10, 20, 30, 15, 25, 12, 20, 10};
        for (int x : datos) {
            boolean agregado = set.add(x);           // O(log n)
            System.out.println("add(" + x + ") -> " + agregado + "   " + set);
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 2. Busqueda
        // ---------------------------------------------------------------
        System.out.println("contains(15) = " + set.contains(15));   // O(log n)
        System.out.println("contains(27) = " + set.contains(27));
        System.out.println("first()      = " + set.first());        // minimo
        System.out.println("last()       = " + set.last());         // maximo
        System.out.println();

        // ---------------------------------------------------------------
        // 3. Metodos de navegacion (NavigableSet)
        // ---------------------------------------------------------------
        System.out.println("floor(14)   = " + set.floor(14));    // mayor <= 14
        System.out.println("lower(15)   = " + set.lower(15));    // mayor <  15
        System.out.println("ceiling(16) = " + set.ceiling(16));  // menor >= 16
        System.out.println("higher(25)  = " + set.higher(25));   // menor >  25
        System.out.println("headSet(15)       = " + set.headSet(15));        // < 15
        System.out.println("tailSet(15)       = " + set.tailSet(15));        // >= 15
        System.out.println("subSet(12, 25)    = " + set.subSet(12, 25));     // [12, 25)
        System.out.println("descendingSet()   = " + set.descendingSet());
        System.out.println();

        // ---------------------------------------------------------------
        // 4. Eliminacion
        // ---------------------------------------------------------------
        set.remove(30);                               // O(log n)
        System.out.println("remove(30)  -> " + set);
        System.out.println("pollFirst() -> " + set.pollFirst() + "  " + set);
        System.out.println("pollLast()  -> " + set.pollLast() + "  " + set);
        System.out.println();

        // ---------------------------------------------------------------
        // 5. Comparator personalizado: Strings por largo y luego alfabetico
        // ---------------------------------------------------------------
        Comparator<String> porLargo = Comparator.comparingInt(String::length)
                                                .thenComparing(Comparator.naturalOrder());
        TreeSet<String> palabras = new TreeSet<>(porLargo);
        palabras.add("arbol");
        palabras.add("heap");
        palabras.add("rojo");
        palabras.add("negro");
        palabras.add("ed");
        palabras.add("java");
        System.out.println("Ordenado por largo: " + palabras);

        // Orden inverso
        TreeSet<Integer> inverso = new TreeSet<>(Comparator.reverseOrder());
        inverso.addAll(java.util.List.of(5, 1, 9, 3));
        System.out.println("Orden inverso:      " + inverso);
        System.out.println();

        // ---------------------------------------------------------------
        // 6. Iteracion siempre en orden (recorrido inorden del arbol)
        // ---------------------------------------------------------------
        TreeSet<Integer> numeros = new TreeSet<>(java.util.List.of(42, 7, 19, 3, 88));
        Iterator<Integer> it = numeros.iterator();
        System.out.print("Iterando: ");
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // ---------------------------------------------------------------
        // 7. null NO esta permitido (no se puede comparar)
        // ---------------------------------------------------------------
        try {
            numeros.add(null);
        } catch (NullPointerException e) {
            System.out.println("add(null) -> NullPointerException");
        }

        // ---------------------------------------------------------------
        // 8. Aplicacion: eliminar duplicados y ordenar
        // ---------------------------------------------------------------
        String texto = "el arbol rojo negro es un arbol binario de busqueda balanceado";
        TreeSet<String> unicas = new TreeSet<>(java.util.Arrays.asList(texto.split(" ")));
        System.out.println("Palabras unicas ordenadas: " + unicas);
    }
}
