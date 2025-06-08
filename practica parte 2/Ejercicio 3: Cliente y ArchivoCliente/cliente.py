import pickle

class Cliente:
    def __init__(self, id_cliente, nombre, telefono):
        self.id = id_cliente
        self.nombre = nombre
        self.telefono = telefono
    
    def get_id(self):
        return self.id
    
    def get_nombre(self):
        return self.nombre
    
    def get_telefono(self):
        return self.telefono

class ArchivoCliente:
    def __init__(self, nombre_archivo):
        self.nomA = nombre_archivo
    
    def crear_archivo(self):
        try:
            with open(self.nomA, 'wb') as f:
                pickle.dump([], f)
        except IOError:
            print("Error al crear archivo")
    
    def guarda_cliente(self, cliente):
        try:
            with open(self.nomA, 'rb') as f:
                clientes = pickle.load(f)
            
            clientes.append(cliente)
            
            with open(self.nomA, 'wb') as f:
                pickle.dump(clientes, f)
        except IOError:
            print("Error al guardar cliente")
    
    def buscar_cliente(self, id_cliente):
        try:
            with open(self.nomA, 'rb') as f:
                clientes = pickle.load(f)
            
            for cli in clientes:
                if cli.get_id() == id_cliente:
                    return cli
            return None
        except IOError:
            print("Error al buscar cliente")
            return None
    
    def buscar_celular_cliente(self, telefono):
        try:
            with open(self.nomA, 'rb') as f:
                clientes = pickle.load(f)
            
            for cli in clientes:
                if cli.get_telefono() == telefono:
                    return cli
            return None
        except IOError:
            print("rror al buscar cliente por teléfono")
            return None

archivo = ArchivoCliente("clientes.dat")
archivo.crear_archivo()

archivo.guarda_cliente(Cliente(1, "Carlos Ruiz", 7654321))
archivo.guarda_cliente(Cliente(2, "Ana Zambrano", 7123456))

cli = archivo.buscar_cliente(1)
if cli:
    print(f"Cliente encontrado: {cli.get_nombre()}, Tel: {cli.get_telefono()}")

cli_cel = archivo.buscar_celular_cliente(7123456)
if cli_cel:
    print(f"xliente por teléfono: {cli_cel.get_nombre()}")