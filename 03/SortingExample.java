import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

// 1. Implementando Comparable (Orden natural: por ID)
class Estudiante implements Comparable<Estudiante> {
    int id;
    String nombre;

    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Estudiante otro) {
        // Orden ascendente por ID
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return "Estudiante{id=" + id + ", nombre='" + nombre + "'}";
    }
}

// 2. Implementando Comparator (Orden externo: por Nombre)
class ComparadorPorNombre implements Comparator<Estudiante> {
    @Override
    public int compare(Estudiante e1, Estudiante e2) {
        return e1.nombre.compareTo(e2.nombre);
    }
}

public class SortingExample {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante(3, "Carlos"));
        estudiantes.add(new Estudiante(1, "Ana"));
        estudiantes.add(new Estudiante(2, "Beatriz"));

        System.out.println("--- Lista Original ---");
        for (Estudiante e : estudiantes) System.out.println(e);

        // Uso de Comparable
        Collections.sort(estudiantes);
        System.out.println("\n--- Orden Natural (Comparable, por ID) ---");
        for (Estudiante e : estudiantes) System.out.println(e);

        // Uso de Comparator
        Collections.sort(estudiantes, new ComparadorPorNombre());
        System.out.println("\n--- Orden Externo (Comparator, por Nombre) ---");
        for (Estudiante e : estudiantes) System.out.println(e);

        // 3. Uso de Collator (Para strings con caracteres especiales)
        List<String> palabras = new ArrayList<>();
        palabras.add("Zebra");
        palabras.add("Árbol");
        palabras.add("Avión");

        System.out.println("\n--- Orden String Normal vs Collator ---");
        Collections.sort(palabras); // Ordena por valor ASCII/Unicode
        System.out.println("String Normal: " + palabras); // Puede poner Árbol al final

        Collator collator = Collator.getInstance(new Locale("es", "ES"));
        Collections.sort(palabras, collator);
        System.out.println("Collator (ES): " + palabras); // Entiende que Á va con A
    }
}
