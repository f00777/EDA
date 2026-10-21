import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Implementacion didactica de un Heap Binario de minimos.
 *
 * Es la MISMA estructura (y la misma logica) que usa java.util.PriorityQueue:
 * un arbol binario COMPLETO guardado en un arreglo, donde
 *      padre(i) = (i - 1) / 2,   izq(i) = 2i + 1,   der(i) = 2i + 2
 * y cada padre es <= que sus hijos.
 *
 * Compilar:  javac MinHeap.java
 * Ejecutar:  java MinHeap
 */
public class MinHeap<E extends Comparable<E>> {

    private static final int CAPACIDAD_INICIAL = 11;   // igual que PriorityQueue

    private Object[] cola;
    private int tamano;
    private boolean traza = true;

    public MinHeap() {
        cola = new Object[CAPACIDAD_INICIAL];
    }

    /** Construye el heap a partir de un arreglo en O(n) (heapify). */
    public MinHeap(E[] datos) {
        cola = Arrays.copyOf(datos, Math.max(datos.length, 1), Object[].class);
        tamano = datos.length;
        heapify();
    }

    public void setTraza(boolean traza) { this.traza = traza; }
    public int size() { return tamano; }
    public boolean isEmpty() { return tamano == 0; }

    private void log(String s) {
        if (traza) System.out.println(s);
    }

    @SuppressWarnings("unchecked")
    private E get(int i) { return (E) cola[i]; }

    // =====================================================================
    // INSERCION (offer)  -  O(log n)
    // =====================================================================
    public boolean offer(E e) {
        if (e == null) throw new NullPointerException();
        log("offer(" + e + ")");
        int i = tamano;
        if (i >= cola.length) crecer();
        tamano = i + 1;
        log("  se coloca " + e + " al final (indice " + i + ")");
        subir(i, e);                         // siftUp
        return true;
    }

    /** siftUp: sube x desde la posicion k mientras sea menor que su padre. */
    private void subir(int k, E x) {
        while (k > 0) {
            int padre = (k - 1) >>> 1;
            E e = get(padre);
            if (x.compareTo(e) >= 0) {
                log("  " + x + " >= padre " + e + ": se detiene");
                break;
            }
            log("  " + x + " < padre " + e + " (indice " + padre + "): intercambiar");
            cola[k] = e;                     // el padre baja
            k = padre;
        }
        cola[k] = x;
        log("  arreglo: " + this);
    }

    /** Igual que PriorityQueue: +2 si es pequeno, +50% si es grande. */
    private void crecer() {
        int viejo = cola.length;
        int nuevo = viejo + (viejo < 64 ? viejo + 2 : viejo >> 1);
        cola = Arrays.copyOf(cola, nuevo);
    }

    // =====================================================================
    // BUSQUEDA (peek / contains)
    // =====================================================================
    /** El minimo siempre esta en la raiz: O(1). */
    public E peek() {
        return tamano == 0 ? null : get(0);
    }

    /** Un heap NO esta ordenado para buscar: recorrido lineal O(n). */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < tamano; i++) {
                if (o.equals(cola[i])) return i;
            }
        }
        return -1;
    }

    // =====================================================================
    // ELIMINACION (poll / remove)
    // =====================================================================
    /** Saca el minimo: O(log n). */
    public E poll() {
        if (tamano == 0) return null;
        E resultado = get(0);
        log("poll() -> " + resultado);
        int n = --tamano;
        E x = get(n);                        // ultimo elemento
        cola[n] = null;
        if (n > 0) {
            log("  se mueve el ultimo (" + x + ") a la raiz");
            bajar(0, x);                     // siftDown
        }
        return resultado;
    }

    public E remove() {
        E x = poll();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    /** Elimina un elemento cualquiera: O(n) por la busqueda. */
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i < 0) return false;
        log("remove(" + o + ") en indice " + i);
        removeAt(i);
        return true;
    }

    private void removeAt(int i) {
        int s = --tamano;
        if (s == i) {                        // era el ultimo
            cola[i] = null;
            log("  arreglo: " + this);
            return;
        }
        E movido = get(s);
        cola[s] = null;
        log("  se mueve el ultimo (" + movido + ") al indice " + i);
        bajar(i, movido);
        if (cola[i] == movido) {             // no bajo: quizas debe subir
            subir(i, movido);
        }
    }

    /** siftDown: baja x desde k intercambiandolo con el MENOR de sus hijos. */
    private void bajar(int k, E x) {
        int mitad = tamano >>> 1;            // nodos con al menos un hijo
        while (k < mitad) {
            int hijo = (k << 1) + 1;         // hijo izquierdo
            E c = get(hijo);
            int der = hijo + 1;
            if (der < tamano && c.compareTo(get(der)) > 0) {
                hijo = der;                  // el derecho es menor
                c = get(hijo);
            }
            if (x.compareTo(c) <= 0) {
                log("  " + x + " <= menor hijo " + c + ": se detiene");
                break;
            }
            log("  " + x + " > menor hijo " + c + " (indice " + hijo + "): intercambiar");
            cola[k] = c;                     // el hijo sube
            k = hijo;
        }
        cola[k] = x;
        log("  arreglo: " + this);
    }

    // =====================================================================
    // REBALANCEO GLOBAL (heapify)  -  O(n)
    // =====================================================================
    private void heapify() {
        boolean t = traza;
        traza = false;
        for (int i = (tamano >>> 1) - 1; i >= 0; i--) {
            bajar(i, get(i));
        }
        traza = t;
    }

    // =====================================================================
    // IMPRESION
    // =====================================================================
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(cola, tamano));
    }

    /** Imprime el heap por niveles. */
    public void imprimirArbol() {
        int nivel = 0, i = 0;
        while (i < tamano) {
            int cantidad = 1 << nivel;
            StringBuilder sb = new StringBuilder("  nivel " + nivel + ": ");
            for (int j = 0; j < cantidad && i < tamano; j++, i++) {
                sb.append(cola[i]).append("  ");
            }
            System.out.println(sb);
            nivel++;
        }
        System.out.println();
    }

    // =====================================================================
    // DEMO: reproduce los ejemplos de la presentacion
    // =====================================================================
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.setTraza(false);
        for (int x : new int[]{3, 5, 8, 10, 7}) heap.offer(x);
        heap.setTraza(true);

        System.out.println("===== HEAP INICIAL =====");
        System.out.println("arreglo: " + heap);
        heap.imprimirArbol();

        System.out.println("===== INSERCION =====");
        heap.offer(2);
        heap.imprimirArbol();

        System.out.println("===== BUSQUEDA =====");
        System.out.println("peek()       = " + heap.peek() + "   (O(1), es la raiz)");
        System.out.println("contains(7)  = " + heap.contains(7) + "  (O(n), recorre el arreglo)");
        System.out.println("contains(4)  = " + heap.contains(4));
        System.out.println();

        System.out.println("===== ELIMINACION =====");
        heap.poll();
        heap.imprimirArbol();
        heap.remove((Object) 5);
        heap.imprimirArbol();

        System.out.println("===== HEAPIFY (construir desde un arreglo en O(n)) =====");
        MinHeap<Integer> h2 = new MinHeap<>(new Integer[]{9, 4, 7, 1, 8, 2, 6});
        System.out.println("entrada: [9, 4, 7, 1, 8, 2, 6]  ->  heap: " + h2);
        h2.imprimirArbol();

        System.out.println("===== HEAPSORT: sacar todo con poll() =====");
        h2.setTraza(false);
        StringBuilder sb = new StringBuilder();
        while (!h2.isEmpty()) sb.append(h2.poll()).append(' ');
        System.out.println(sb.toString().trim());
    }
}
