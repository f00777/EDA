import java.util.Random;
import java.util.TreeMap;

/**
 * Implementacion didactica de un Arbol Rojo-Negro.
 *
 * Es la MISMA estructura (y la misma logica) que usa java.util.TreeMap
 * internamente; java.util.TreeSet a su vez usa un TreeMap por dentro.
 *
 * Incluye: busqueda, insercion, eliminacion y rebalanceo (rotaciones y
 * recoloreos), con una "traza" que imprime cada caso aplicado.
 *
 * Compilar:  javac ArbolRojoNegro.java
 * Ejecutar:  java ArbolRojoNegro
 */
public class ArbolRojoNegro<K extends Comparable<K>, V> {

    private static final boolean ROJO = false;
    private static final boolean NEGRO = true;

    /** Equivalente a TreeMap.Entry<K,V> */
    static final class Nodo<K, V> {
        K clave;
        V valor;
        Nodo<K, V> izq;
        Nodo<K, V> der;
        Nodo<K, V> padre;
        boolean color = NEGRO;

        Nodo(K clave, V valor, Nodo<K, V> padre) {
            this.clave = clave;
            this.valor = valor;
            this.padre = padre;
        }
    }

    private Nodo<K, V> raiz;
    private int tamano;
    private boolean traza = true;

    public void setTraza(boolean traza) { this.traza = traza; }
    public int size() { return tamano; }

    private void log(String s) {
        if (traza) System.out.println(s);
    }

    // =====================================================================
    // BUSQUEDA  -  O(log n)
    // =====================================================================
    public V buscar(K clave) {
        Nodo<K, V> n = buscarNodo(clave);
        return n == null ? null : n.valor;
    }

    private Nodo<K, V> buscarNodo(K clave) {
        if (clave == null) throw new NullPointerException();
        Nodo<K, V> p = raiz;
        while (p != null) {
            int cmp = clave.compareTo(p.clave);
            if (cmp < 0)      p = p.izq;   // ir a la izquierda
            else if (cmp > 0) p = p.der;   // ir a la derecha
            else              return p;    // encontrado
        }
        return null;                        // no esta
    }

    /** Devuelve el camino recorrido al buscar (para mostrar en clases). */
    public String caminoBusqueda(K clave) {
        StringBuilder sb = new StringBuilder();
        Nodo<K, V> p = raiz;
        while (p != null) {
            sb.append(p.clave);
            int cmp = clave.compareTo(p.clave);
            if (cmp == 0) return sb.append("  -> ENCONTRADO").toString();
            sb.append(cmp < 0 ? " -izq-> " : " -der-> ");
            p = (cmp < 0) ? p.izq : p.der;
        }
        return sb.append("nil  -> NO ENCONTRADO").toString();
    }

    // =====================================================================
    // INSERCION  -  O(log n)
    // =====================================================================
    public V insertar(K clave, V valor) {
        if (clave == null) throw new NullPointerException();
        log("insertar(" + clave + ")");
        Nodo<K, V> t = raiz;
        if (t == null) {                       // arbol vacio
            raiz = new Nodo<>(clave, valor, null);  // la raiz es negra
            tamano = 1;
            log("  arbol vacio: " + clave + " es la raiz (negra)");
            return null;
        }
        int cmp;
        Nodo<K, V> padre;
        do {                                   // descenso como en un BST
            padre = t;
            cmp = clave.compareTo(t.clave);
            if (cmp < 0)      t = t.izq;
            else if (cmp > 0) t = t.der;
            else {                             // la clave ya existe
                V viejo = t.valor;
                t.valor = valor;
                return viejo;
            }
        } while (t != null);

        Nodo<K, V> e = new Nodo<>(clave, valor, padre);
        if (cmp < 0) padre.izq = e;
        else         padre.der = e;
        arreglarInsercion(e);                  // REBALANCEO
        tamano++;
        return null;
    }

    /** Equivalente a TreeMap.fixAfterInsertion */
    private void arreglarInsercion(Nodo<K, V> x) {
        x.color = ROJO;                        // el nuevo nodo entra rojo
        if (colorDe(padreDe(x)) == NEGRO)
            log("  padre " + padreDe(x).clave + " negro: no hay violacion");

        while (x != null && x != raiz && x.padre.color == ROJO) {
            Nodo<K, V> abuelo = padreDe(padreDe(x));
            if (padreDe(x) == izqDe(abuelo)) {
                Nodo<K, V> tio = derDe(abuelo);
                if (colorDe(tio) == ROJO) {
                    log("  Caso 1 (tio " + nombre(tio) + " rojo): recolorear padre, tio y abuelo");
                    setColor(padreDe(x), NEGRO);
                    setColor(tio, NEGRO);
                    setColor(abuelo, ROJO);
                    x = abuelo;
                } else {
                    if (x == derDe(padreDe(x))) {
                        log("  Caso 2 (tio " + nombre(tio) + " negro, triangulo): rotar IZQ en " + padreDe(x).clave);
                        x = padreDe(x);
                        rotarIzq(x);
                    }
                    log("  Caso 3 (tio negro, linea): recolorear y rotar DER en " + padreDe(padreDe(x)).clave);
                    setColor(padreDe(x), NEGRO);
                    setColor(padreDe(padreDe(x)), ROJO);
                    rotarDer(padreDe(padreDe(x)));
                }
            } else {                           // simetrico (izq <-> der)
                Nodo<K, V> tio = izqDe(abuelo);
                if (colorDe(tio) == ROJO) {
                    log("  Caso 1 (tio " + nombre(tio) + " rojo): recolorear padre, tio y abuelo");
                    setColor(padreDe(x), NEGRO);
                    setColor(tio, NEGRO);
                    setColor(abuelo, ROJO);
                    x = abuelo;
                } else {
                    if (x == izqDe(padreDe(x))) {
                        log("  Caso 2 (tio " + nombre(tio) + " negro, triangulo): rotar DER en " + padreDe(x).clave);
                        x = padreDe(x);
                        rotarDer(x);
                    }
                    log("  Caso 3 (tio negro, linea): recolorear y rotar IZQ en " + padreDe(padreDe(x)).clave);
                    setColor(padreDe(x), NEGRO);
                    setColor(padreDe(padreDe(x)), ROJO);
                    rotarIzq(padreDe(padreDe(x)));
                }
            }
        }
        if (raiz.color == ROJO) log("  la raiz " + raiz.clave + " se pinta negra");
        raiz.color = NEGRO;                    // la raiz siempre es negra
    }

    // =====================================================================
    // ELIMINACION  -  O(log n)
    // =====================================================================
    public V eliminar(K clave) {
        log("eliminar(" + clave + ")");
        Nodo<K, V> p = buscarNodo(clave);
        if (p == null) {
            log("  no existe");
            return null;
        }
        V viejo = p.valor;
        eliminarNodo(p);
        return viejo;
    }

    /** Equivalente a TreeMap.deleteEntry */
    private void eliminarNodo(Nodo<K, V> p) {
        tamano--;
        // Caso 2 hijos: copiar el sucesor en p y eliminar el sucesor
        if (p.izq != null && p.der != null) {
            Nodo<K, V> s = sucesor(p);
            log("  " + p.clave + " tiene 2 hijos: se reemplaza por su sucesor " + s.clave);
            p.clave = s.clave;
            p.valor = s.valor;
            p = s;
        }
        Nodo<K, V> reemplazo = (p.izq != null ? p.izq : p.der);

        if (reemplazo != null) {               // Caso 1 hijo
            log("  " + p.clave + " tiene 1 hijo (" + reemplazo.clave + "): el hijo toma su lugar");
            reemplazo.padre = p.padre;
            if (p.padre == null)          raiz = reemplazo;
            else if (p == p.padre.izq)    p.padre.izq = reemplazo;
            else                          p.padre.der = reemplazo;
            p.izq = p.der = p.padre = null;
            if (p.color == NEGRO) arreglarEliminacion(reemplazo);
        } else if (p.padre == null) {          // era el unico nodo
            raiz = null;
        } else {                               // Caso hoja
            if (p.color == NEGRO) {
                log("  " + p.clave + " es hoja NEGRA: se genera un 'doble negro'");
                arreglarEliminacion(p);        // p actua como nodo fantasma
            } else {
                log("  " + p.clave + " es hoja ROJA: se elimina sin rebalancear");
            }
            if (p.padre != null) {
                if (p == p.padre.izq)      p.padre.izq = null;
                else if (p == p.padre.der) p.padre.der = null;
                p.padre = null;
            }
        }
    }

    /** Menor nodo mayor que t (el minimo del subarbol derecho). */
    private Nodo<K, V> sucesor(Nodo<K, V> t) {
        if (t == null) return null;
        if (t.der != null) {
            Nodo<K, V> p = t.der;
            while (p.izq != null) p = p.izq;
            return p;
        }
        Nodo<K, V> p = t.padre, ch = t;
        while (p != null && ch == p.der) {
            ch = p;
            p = p.padre;
        }
        return p;
    }

    /** Equivalente a TreeMap.fixAfterDeletion */
    private void arreglarEliminacion(Nodo<K, V> x) {
        while (x != raiz && colorDe(x) == NEGRO) {
            if (x == izqDe(padreDe(x))) {
                Nodo<K, V> her = derDe(padreDe(x));            // hermano
                if (colorDe(her) == ROJO) {
                    log("  Caso 1 (hermano " + nombre(her) + " rojo): recolorear y rotar IZQ en " + padreDe(x).clave);
                    setColor(her, NEGRO);
                    setColor(padreDe(x), ROJO);
                    rotarIzq(padreDe(x));
                    her = derDe(padreDe(x));
                }
                if (colorDe(izqDe(her)) == NEGRO && colorDe(derDe(her)) == NEGRO) {
                    log("  Caso 2 (hermano " + nombre(her) + " negro con hijos negros): hermano a rojo, subir");
                    setColor(her, ROJO);
                    x = padreDe(x);
                } else {
                    if (colorDe(derDe(her)) == NEGRO) {
                        log("  Caso 3 (sobrino lejano negro, cercano rojo): rotar DER en " + nombre(her));
                        setColor(izqDe(her), NEGRO);
                        setColor(her, ROJO);
                        rotarDer(her);
                        her = derDe(padreDe(x));
                    }
                    log("  Caso 4 (sobrino lejano rojo): recolorear y rotar IZQ en " + padreDe(x).clave);
                    setColor(her, colorDe(padreDe(x)));
                    setColor(padreDe(x), NEGRO);
                    setColor(derDe(her), NEGRO);
                    rotarIzq(padreDe(x));
                    x = raiz;
                }
            } else {                                           // simetrico
                Nodo<K, V> her = izqDe(padreDe(x));
                if (colorDe(her) == ROJO) {
                    log("  Caso 1 (hermano " + nombre(her) + " rojo): recolorear y rotar DER en " + padreDe(x).clave);
                    setColor(her, NEGRO);
                    setColor(padreDe(x), ROJO);
                    rotarDer(padreDe(x));
                    her = izqDe(padreDe(x));
                }
                if (colorDe(derDe(her)) == NEGRO && colorDe(izqDe(her)) == NEGRO) {
                    log("  Caso 2 (hermano " + nombre(her) + " negro con hijos negros): hermano a rojo, subir");
                    setColor(her, ROJO);
                    x = padreDe(x);
                } else {
                    if (colorDe(izqDe(her)) == NEGRO) {
                        log("  Caso 3 (sobrino lejano negro, cercano rojo): rotar IZQ en " + nombre(her));
                        setColor(derDe(her), NEGRO);
                        setColor(her, ROJO);
                        rotarIzq(her);
                        her = izqDe(padreDe(x));
                    }
                    log("  Caso 4 (sobrino lejano rojo): recolorear y rotar DER en " + padreDe(x).clave);
                    setColor(her, colorDe(padreDe(x)));
                    setColor(padreDe(x), NEGRO);
                    setColor(izqDe(her), NEGRO);
                    rotarDer(padreDe(x));
                    x = raiz;
                }
            }
        }
        setColor(x, NEGRO);
    }

    // =====================================================================
    // ROTACIONES  -  O(1)
    // =====================================================================
    private void rotarIzq(Nodo<K, V> p) {
        if (p == null) return;
        Nodo<K, V> r = p.der;
        p.der = r.izq;
        if (r.izq != null) r.izq.padre = p;
        r.padre = p.padre;
        if (p.padre == null)        raiz = r;
        else if (p.padre.izq == p)  p.padre.izq = r;
        else                        p.padre.der = r;
        r.izq = p;
        p.padre = r;
    }

    private void rotarDer(Nodo<K, V> p) {
        if (p == null) return;
        Nodo<K, V> l = p.izq;
        p.izq = l.der;
        if (l.der != null) l.der.padre = p;
        l.padre = p.padre;
        if (p.padre == null)        raiz = l;
        else if (p.padre.der == p)  p.padre.der = l;
        else                        p.padre.izq = l;
        l.der = p;
        p.padre = l;
    }

    // ---- Helpers null-safe (igual que en TreeMap): nil cuenta como NEGRO
    private static <K, V> boolean colorDe(Nodo<K, V> p) { return p == null ? NEGRO : p.color; }
    private static <K, V> Nodo<K, V> padreDe(Nodo<K, V> p) { return p == null ? null : p.padre; }
    private static <K, V> Nodo<K, V> izqDe(Nodo<K, V> p) { return p == null ? null : p.izq; }
    private static <K, V> Nodo<K, V> derDe(Nodo<K, V> p) { return p == null ? null : p.der; }
    private static <K, V> void setColor(Nodo<K, V> p, boolean c) { if (p != null) p.color = c; }
    private static <K, V> String nombre(Nodo<K, V> p) { return p == null ? "nil" : String.valueOf(p.clave); }

    // =====================================================================
    // VERIFICACION DE PROPIEDADES E IMPRESION
    // =====================================================================
    public boolean esValido() {
        if (colorDe(raiz) == ROJO) return false;
        try {
            alturaNegra(raiz);
            return true;
        } catch (IllegalStateException e) {
            System.out.println("Invalido: " + e.getMessage());
            return false;
        }
    }

    private int alturaNegra(Nodo<K, V> n) {
        if (n == null) return 1;                       // nil es negro
        if (n.color == ROJO && (colorDe(n.izq) == ROJO || colorDe(n.der) == ROJO))
            throw new IllegalStateException("rojo con hijo rojo en " + n.clave);
        int hi = alturaNegra(n.izq), hd = alturaNegra(n.der);
        if (hi != hd)
            throw new IllegalStateException("altura negra distinta en " + n.clave);
        return hi + (n.color == NEGRO ? 1 : 0);
    }

    /** Imprime el arbol "acostado" (raiz a la izquierda, derecha arriba). */
    public void imprimir() {
        if (raiz == null) {
            System.out.println("  (vacio)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, 1, sb);
        System.out.print(sb);
        System.out.println("  inorden: " + inorden() + "   valido=" + esValido());
        System.out.println();
    }

    private void imprimir(Nodo<K, V> n, int nivel, StringBuilder sb) {
        if (n == null) return;
        imprimir(n.der, nivel + 1, sb);
        sb.append("        ".repeat(nivel))
          .append(n.clave).append(n.color == ROJO ? "(R)" : "(N)").append('\n');
        imprimir(n.izq, nivel + 1, sb);
    }

    public String inorden() {
        StringBuilder sb = new StringBuilder("[");
        inorden(raiz, sb);
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        return sb.append("]").toString();
    }

    private void inorden(Nodo<K, V> n, StringBuilder sb) {
        if (n == null) return;
        inorden(n.izq, sb);
        sb.append(n.clave).append(", ");
        inorden(n.der, sb);
    }

    // =====================================================================
    // DEMO: reproduce los ejemplos de la presentacion
    // =====================================================================
    public static void main(String[] args) {
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();

        System.out.println("===== INSERCION: 10, 20, 30, 15, 25, 12 =====\n");
        for (int x : new int[]{10, 20, 30, 15, 25, 12}) {
            arbol.insertar(x, "v" + x);
            arbol.imprimir();
        }

        System.out.println("===== BUSQUEDA =====\n");
        System.out.println("buscar(15): " + arbol.caminoBusqueda(15));
        System.out.println("buscar(27): " + arbol.caminoBusqueda(27));
        System.out.println();

        System.out.println("===== ELIMINACION: 30, 10, 25 =====\n");
        for (int x : new int[]{30, 10, 25}) {
            arbol.eliminar(x);
            arbol.imprimir();
        }

        // Prueba aleatoria contra java.util.TreeMap
        System.out.println("===== PRUEBA ALEATORIA vs java.util.TreeMap =====");
        ArbolRojoNegro<Integer, Integer> a = new ArbolRojoNegro<>();
        a.setTraza(false);
        TreeMap<Integer, Integer> ref = new TreeMap<>();
        Random rnd = new Random(42);
        boolean ok = true;
        for (int i = 0; i < 20000; i++) {
            int k = rnd.nextInt(2000);
            if (rnd.nextBoolean()) { a.insertar(k, k); ref.put(k, k); }
            else                   { a.eliminar(k);    ref.remove(k); }
            if (a.size() != ref.size()) ok = false;
        }
        ok &= a.esValido() && a.inorden().equals(ref.keySet().toString());
        System.out.println("tamano=" + a.size() + "  propiedades RN y contenido correctos: " + ok);
    }
}
