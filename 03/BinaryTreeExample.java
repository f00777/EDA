class NodoBST {
    int valor;
    NodoBST izquierdo;
    NodoBST derecho;

    public NodoBST(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioBusqueda {
    NodoBST raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // Método para insertar un valor
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private NodoBST insertarRecursivo(NodoBST actual, int valor) {
        if (actual == null) {
            return new NodoBST(valor);
        }

        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }

        return actual;
    }

    // Método para buscar un valor
    public boolean buscar(int valor) {
        NodoBST resultado = buscarRecursivo(raiz, valor);
        return resultado != null;
    }

    private NodoBST buscarRecursivo(NodoBST actual, int valor) {
        // Caso base: raíz es null o el valor está en la raíz
        if (actual == null || actual.valor == valor) {
            return actual;
        }

        // El valor es menor que el valor de la raíz
        if (valor < actual.valor) {
            return buscarRecursivo(actual.izquierdo, valor);
        }

        // El valor es mayor que el valor de la raíz
        return buscarRecursivo(actual.derecho, valor);
    }
}

public class BinaryTreeExample {
    public static void main(String[] args) {
        ArbolBinarioBusqueda bst = new ArbolBinarioBusqueda();

        // Insertar valores
        bst.insertar(50);
        bst.insertar(30);
        bst.insertar(20);
        bst.insertar(40);
        bst.insertar(70);
        bst.insertar(60);
        bst.insertar(80);

        // Buscar valores
        System.out.println("¿Existe el 40 en el árbol? " + bst.buscar(40));
        System.out.println("¿Existe el 90 en el árbol? " + bst.buscar(90));
    }
}
