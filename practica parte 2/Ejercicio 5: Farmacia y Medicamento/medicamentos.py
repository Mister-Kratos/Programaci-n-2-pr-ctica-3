import pickle

class Medicamento:
    def __init__(self, nombre="", codigo=0, tipo="", precio=0.0):
        self.nombre = nombre
        self.codMedicamento = codigo
        self.tipo = tipo
        self.precio = precio
    
    def leer(self):
        self.nombre = input("Nombre medicamento: ")
        self.codMedicamento = int(input("Código: "))
        self.tipo = input("Tipo (tos/resfriado/etc): ")
        self.precio = float(input("Precio: "))
    
    def mostrar(self):
        print(f"Medicamento: {self.nombre} - Cód: {self.codMedicamento}")
        print(f"Tipo: {self.tipo} - Precio: {self.precio}")
    
    def get_tipo(self):
        return self.tipo
    
    def get_precio(self):
        return self.precio

class Farmacia:
    def __init__(self):
        self.nombreFarmacia = ""
        self.sucursal = 0
        self.direccion = ""
        self.medicamentos = []
    
    def leer(self):
        self.nombreFarmacia = input("Nombre farmacia: ")
        self.sucursal = int(input("Sucursal: "))
        self.direccion = input("Dirección: ")
        n = int(input("Cantidad medicamentos: "))
        for _ in range(n):
            med = Medicamento()
            med.leer()
            self.medicamentos.append(med)
    
    def mostrar(self):
        print(f"\nFarmacia: {self.nombreFarmacia}")
        print(f"Sucursal: {self.sucursal} - Dirección: {self.direccion}")
        print("Medicamentos:")
        for med in self.medicamentos:
            med.mostrar()
    
    def get_direccion(self):
        return self.direccion
    
    def get_sucursal(self):
        return self.sucursal
    
    def mostrar_medicamentos(self, tipo):
        print(f"\nMedicamentos para {tipo}:")
        for med in self.medicamentos:
            if med.get_tipo().lower() == tipo.lower():
                med.mostrar()
    
    def busca_medicamento(self, nombre):
        for med in self.medicamentos:
            if med.nombre.lower() == nombre.lower():
                return med
        return None

class ArchFarmacia:
    def __init__(self, nombre_archivo):
        self.na = nombre_archivo
    
    def crear_archivo(self):
        try:
            with open(self.na, 'wb') as f:
                pickle.dump([], f)
        except IOError:
            print("Error al crear archivo")
    
    def adicionar(self, farmacia):
        try:
            with open(self.na, 'rb') as f:
                farmacias = pickle.load(f)
            
            farmacias.append(farmacia)
            
            with open(self.na, 'wb') as f:
                pickle.dump(farmacias, f)
        except IOError:
            print("Error al guardar farmacia")
    
    def listar(self):
        try:
            with open(self.na, 'rb') as f:
                farmacias = pickle.load(f)
            
            for farm in farmacias:
                farm.mostrar()
        except IOError:
            print("Error al listar farmacias")
    
    def mostrar_medicamentos_resfrios(self):
        try:
            with open(self.na, 'rb') as f:
                farmacias = pickle.load(f)
            
            print("\nMedicamentos para resfriados:")
            for farm in farmacias:
                farm.mostrar_medicamentos("resfriado")
        except IOError:
            print("Error al buscar medicamentos")
    
    def precio_medicamento_tos(self):
        try:
            with open(self.na, 'rb') as f:
                farmacias = pickle.load(f)
            
            total = 0.0
            count = 0
            for farm in farmacias:
                for med in farm.medicamentos:
                    if med.get_tipo().lower() == "tos":
                        total += med.get_precio()
                        count += 1
            return total / count if count > 0 else 0.0
        except IOError:
            print("Error al calcular precio")
            return 0.0
    
    def mostrar_medicamentos_menor_tos(self, max_precio):
        try:
            with open(self.na, 'rb') as f:
                farmacias = pickle.load(f)
            
            print(f"\nMedicamentos para tos con precio menor a {max_precio}:")
            for farm in farmacias:
                for med in farm.medicamentos:
                    if med.get_tipo().lower() == "tos" and med.get_precio() < max_precio:
                        med.mostrar()
        except IOError:
            print("Error al buscar medicamentos")

archivo = ArchFarmacia("farmacias.dat")
archivo.crear_archivo()

farmacia1 = Farmacia()
print("\nIngrese datos de farmacia 1:")
farmacia1.leer()
archivo.adicionar(farmacia1)

farmacia2 = Farmacia()
print("\nIngrese datos de farmacia 2:")
farmacia2.leer()
archivo.adicionar(farmacia2)

print("\nListado completo:")
archivo.listar()

print("\nMedicamentos para resfriados:")
archivo.mostrar_medicamentos_resfrios()

precio_promedio = archivo.precio_medicamento_tos()
print(f"\nPrecio promedio medicamentos para tos: {precio_promedio}")

archivo.mostrar_medicamentos_menor_tos(50.0)