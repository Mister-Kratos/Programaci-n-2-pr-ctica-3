import java.util.ArrayList;
import java.util.List;

class Catalogo<T> {
    private List<T> elementos;
    
    public Catalogo() {
        elementos = new ArrayList<>();
    }
    
    public void agregar(T item) {
        elementos.add(item);
    }
    
    public T buscar(String criterio) {
        for (T item : elementos) {
            if (item.toString().toLowerCase().contains(criterio.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
    
    public void mostrarTodos() {
        for (T item : elementos) {
            System.out.println(item);
        }
    }
}

class Libro {
    private String titulo;
    private String autor;
    
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    
    @Override
    public String toString() {
        return "Libro: " + titulo + " - " + autor;
    }
}

public class MainCatalogo {
    public static void main(String[] args) {
        Catalogo<Libro> catalogoLibros = new Catalogo<>();
        catalogoLibros.agregar(new Libro("Python 101", "Juan Perez"));
        catalogoLibros.agregar(new Libro("Java Básico", "Maria Gomez"));
        
        System.out.println("\nBuscando libro 'Python':");
        System.out.println(catalogoLibros.buscar("Python"));
        
        System.out.println("\nTodos los libros:");
        catalogoLibros.mostrarTodos();
    }
}