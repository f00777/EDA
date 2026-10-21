import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Ejemplos de uso de java.util.PriorityQueue
 * (cola de prioridad implementada con un Heap Binario de minimos en un arreglo).
 *
 * Compilar:  javac EjemploPriorityQueue.java
 * Ejecutar:  java EjemploPriorityQueue
 */
public class EjemploPriorityQueue {

    /** Tarea con prioridad (menor numero = mas urgente). */
    record Tarea(String nombre, int prioridad) { }

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. Insercion: offer / add  -  O(log n)
        // ---------------------------------------------------------------
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : new int[]{3, 5, 8, 10, 7}) pq.offer(x);
        System.out.println("Heap inicial (arreglo interno): " + pq);
        pq.offer(2);
        // toString muestra el ARREGLO interno del heap, NO un orden total
        System.out.println("Despues de offer(2):            " + pq + "   <- igual a la ppt");
        System.out.println();

        // ---------------------------------------------------------------
        // 2. Busqueda
        // ---------------------------------------------------------------
        System.out.println("peek()      = " + pq.peek() + "   O(1): siempre el minimo");
        System.out.println("contains(7) = " + pq.contains(7) + "  O(n): busqueda lineal");
        System.out.println();

        // ---------------------------------------------------------------
        // 3. Eliminacion: poll  -  O(log n)
        // ---------------------------------------------------------------
        System.out.println("poll() -> " + pq.poll() + "   arreglo: " + pq + "   <- igual a la ppt");
        System.out.println("remove(5) -> " + pq.remove(5) + "   arreglo: " + pq + "   O(n)");
        System.out.println();

        // Sacar todo: los elementos salen ORDENADOS (heapsort)
        System.out.print("Vaciando con poll(): ");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " ");
        System.out.println("\n");

        // ---------------------------------------------------------------
        // 4. Max-heap usando un Comparator
        // ---------------------------------------------------------------
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(4, 1, 9, 7, 3));
        System.out.print("Max-heap con poll(): ");
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " ");
        System.out.println("\n");

        // ---------------------------------------------------------------
        // 5. Objetos propios: planificador de tareas
        // ---------------------------------------------------------------
        PriorityQueue<Tarea> tareas =
                new PriorityQueue<>(Comparator.comparingInt(Tarea::prioridad));
        tareas.add(new Tarea("Estudiar EDA", 2));
        tareas.add(new Tarea("Entregar tarea", 1));
        tareas.add(new Tarea("Ver serie", 5));
        tareas.add(new Tarea("Almorzar", 3));
        System.out.println("Atendiendo tareas por prioridad:");
        while (!tareas.isEmpty()) {
            Tarea t = tareas.poll();
            System.out.println("  [" + t.prioridad() + "] " + t.nombre());
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 6. Aplicacion: los k mayores elementos con un min-heap de tamano k
        // ---------------------------------------------------------------
        int[] datos = {15, 3, 42, 8, 23, 4, 16, 50, 1};
        int k = 3;
        PriorityQueue<Integer> topK = new PriorityQueue<>();
        for (int x : datos) {
            topK.offer(x);
            if (topK.size() > k) topK.poll();     // saca el menor
        }
        System.out.println("Los " + k + " mayores: " + topK);

        // ---------------------------------------------------------------
        // 7. null NO esta permitido
        // ---------------------------------------------------------------
        try {
            topK.offer(null);
        } catch (NullPointerException e) {
            System.out.println("offer(null) -> NullPointerException");
        }
    }
}
