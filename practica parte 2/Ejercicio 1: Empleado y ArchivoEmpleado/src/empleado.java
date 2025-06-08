import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Empleado implements Serializable {
    private String nombre;
    private float sueldo;
    
    public Empleado(String nombre, float sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public float getSueldo() {
        return sueldo;
    }
}

class ArchivoEmpleado {
    private String nomA;
    
    public ArchivoEmpleado(String nombreArchivo) {
        this.nomA = nombreArchivo;
    }
    
    public void crearArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
            oos.writeObject(new ArrayList<Empleado>());
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }
    
    public void guardarEmpleado(Empleado e) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Empleado> empleados = (List<Empleado>) ois.readObject();
            empleados.add(e);
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
                oos.writeObject(empleados);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al guardar empleado: " + ex.getMessage());
        }
    }
    
    public Empleado buscaEmpleado(String n) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Empleado> empleados = (List<Empleado>) ois.readObject();
            
            for (Empleado emp : empleados) {
                if (emp.getNombre().equals(n)) {
                    return emp;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }
    
    public Empleado mayorSalario(float sueldo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Empleado> empleados = (List<Empleado>) ois.readObject();
            
            for (Empleado emp : empleados) {
                if (emp.getSueldo() > sueldo) {
                    return emp;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        ArchivoEmpleado archivo = new ArchivoEmpleado("empleados.dat");
        archivo.crearArchivo();
        
        archivo.guardarEmpleado(new Empleado("Juana Garcia", 2500));
        archivo.guardarEmpleado(new Empleado("Maria Chavez", 3500));
        
        Empleado emp = archivo.buscaEmpleado("Juana Garcia");
        if (emp != null) {
            System.out.println("Empleado encontrado: " + emp.getNombre() + ", Sueldo: " + emp.getSueldo());
        }
        
        Empleado empMayor = archivo.mayorSalario(3000);
        if (empMayor != null) {
            System.out.println("Empleado con sueldo mayor: " + empMayor.getNombre());
        }
    }
}