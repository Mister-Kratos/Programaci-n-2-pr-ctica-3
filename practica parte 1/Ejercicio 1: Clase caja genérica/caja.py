class Caja:
    def __init__(self):
        self.contenido = None
    
    def guardar(self, item):
        self.contenido = item
    
    def obtener(self):
        return self.contenido

# Prueba
caja_num = Caja()
caja_num.guardar(100)
print("Caja numérica:", caja_num.obtener())

caja_texto = Caja()
caja_texto.guardar("Hola UMSA")
print("Caja texto:", caja_texto.obtener())