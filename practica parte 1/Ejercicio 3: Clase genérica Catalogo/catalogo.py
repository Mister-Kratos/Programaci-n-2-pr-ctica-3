class Catalogo:
    def __init__(self):
        self.elementos = []
    
    def agregar(self, item):
        self.elementos.append(item)
    
    def buscar(self, criterio):
        for item in self.elementos:
            if criterio.lower() in str(item).lower():
                return item
        return None
    
    def mostrar_todos(self):
        for item in self.elementos:
            print(item)

# Prueba con libros
class Libro:
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor
    
    def __str__(self):
        return f"Libro: {self.titulo} - {self.autor}"

catalogo_libros = Catalogo()
catalogo_libros.agregar(Libro("Python 101", "Juan Perez"))
catalogo_libros.agregar(Libro("Java Básico", "Maria Gomez"))

print("\nBuscando libro 'Python':")
print(catalogo_libros.buscar("Python"))
print("\nTodos los libros:")
catalogo_libros.mostrar_todos()