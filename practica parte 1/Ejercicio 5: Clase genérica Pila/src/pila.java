import java.util.ArrayList;
import java.util.List;

class Pila<T> {
    private List<T> elementos;
    
    public Pila() {
        elementos = new ArrayList<>();
    }
    
    public void apilar(T item) {
        elementos.add(item);
    }
    
    public T desapilar() {
        if (!estaVacia()) {
            return elementos.remove(elementos.size() - 1);
        }
        return null;
    }
    
    public void mostrar() {
        System.out.println("Contenido de la pila (LIFO):");
        for (int i = elementos.size() - 1; i >= 0; i--) {
            System.out.println(elementos.get(i));
        }
    }
    
    public boolean estaVacia() {
        return elementos.isEmpty();
    }
}

public class MainPila {
    public static void main(String[] args) {
        Pila<Integer> pilaEnteros = new Pila<>();
        pilaEnteros.apilar(10);
        pilaEnteros.apilar(20);
        pilaEnteros.apilar(30);
        pilaEnteros.mostrar();
        System.out.println("Desapilado: " + pilaEnteros.desapilar());
        
        Pila<String> pilaStrings = new Pila<>();
        pilaStrings.apilar("Uno");
        pilaStrings.apilar("Dos");
        pilaStrings.apilar("Tres");
        pilaStrings.mostrar();
    }
}