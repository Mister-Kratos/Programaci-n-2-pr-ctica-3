import pickle

class Empleado:
    def __init__(self, nombre, sueldo):
        self.nombre = nombre
        self.sueldo = sueldo
    
    def get_nombre(self):
        return self.nombre
    
    def get_sueldo(self):
        return self.sueldo

class ArchivoEmpleado:
    def __init__(self, nombre_archivo):
        self.nomA = nombre_archivo
    
    def crear_archivo(self):
        try:
            with open(self.nomA, 'wb') as f:
                pickle.dump([], f)
        except IOError:
            print("Error al crear archivo")
    
    def guardar_empleado(self, empleado):
        try:
            with open(self.nomA, 'rb') as f:
                empleados = pickle.load(f)
            
            empleados.append(empleado)
            
            with open(self.nomA, 'wb') as f:
                pickle.dump(empleados, f)
        except IOError:
            print("Error al guardar empleado")
    
    def busca_empleado(self, nombre):
        try:
            with open(self.nomA, 'rb') as f:
                empleados = pickle.load(f)
            
            for emp in empleados:
                if emp.get_nombre() == nombre:
                    return emp
            return None
        except IOError:
            print("Error al buscar empleado")
            return None
    
    def mayor_salario(self, sueldo):
        try:
            with open(self.nomA, 'rb') as f:
                empleados = pickle.load(f)
            
            for emp in empleados:
                if emp.get_sueldo() > sueldo:
                    return emp
            return None
        except IOError:
            print("Error al buscar empleado")
            return None

archivo = ArchivoEmpleado("empleados.dat")
archivo.crear_archivo()

archivo.guardar_empleado(Empleado("Juana Garcia", 2500))
archivo.guardar_empleado(Empleado("Maria Chavez", 3500))

emp = archivo.busca_empleado("Juana Garcia")
if emp:
    print(f"Empleado encontrado: {emp.get_nombre()}, Sueldo: {emp.get_sueldo()}")

emp_mayor = archivo.mayor_salario(3000)
if emp_mayor:
    print(f"Empleado con sueldo mayor: {emp_mayor.get_nombre()}")