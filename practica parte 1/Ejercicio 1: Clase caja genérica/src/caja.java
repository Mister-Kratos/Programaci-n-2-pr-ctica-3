public class Caja<T> {
    private T contenido;
    
    public void guardar(T item) {
        this.contenido = item;
    }
    
    public T obtener() {
        return contenido;
    }

    public static void main(String[] args) {
        Caja<Integer> cajaNum = new Caja<>();
        cajaNum.guardar(100);
        System.out.println("Caja numérica: " + cajaNum.obtener());
        
        Caja<String> cajaTexto = new Caja<>();
        cajaTexto.guardar("Hola UMSA");
        System.out.println("Caja texto: " + cajaTexto.obtener());
    }
} 