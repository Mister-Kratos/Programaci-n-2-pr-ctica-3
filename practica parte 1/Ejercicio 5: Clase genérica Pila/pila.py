class Pila:
    def __init__(self):
        self.elementos = []
    
    def apilar(self, item):
        self.elementos.append(item)
    
    def desapilar(self):
        if not self.esta_vacia():
            return self.elementos.pop()
        return None
    
    def mostrar(self):
        print("Contenido de la pila (LIFO):")
        for item in reversed(self.elementos):
            print(item)
    
    def esta_vacia(self):
        return len(self.elementos) == 0

# Prueba con diferentes tipos
pila_enteros = Pila()
pila_enteros.apilar(10)
pila_enteros.apilar(20)
pila_enteros.apilar(30)
pila_enteros.mostrar()
print("Desapilado:", pila_enteros.desapilar())

pila_strings = Pila()
pila_strings.apilar("Uno")
pila_strings.apilar("Dos")
pila_strings.apilar("Tres")
pila_strings.mostrar()