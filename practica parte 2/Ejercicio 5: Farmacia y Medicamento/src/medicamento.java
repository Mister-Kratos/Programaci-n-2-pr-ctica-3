import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Medicamento implements Serializable {
    private String nombre;
    private int codMedicamento;
    private String tipo;
    private double precio;
    
    public void leer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre medicamento: ");
        this.nombre = sc.nextLine();
        System.out.print("Código: ");
        this.codMedicamento = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.print("Tipo (tos/resfriado/etc): ");
        this.tipo = sc.nextLine();
        System.out.print("Precio: ");
        this.precio = sc.nextDouble();
    }
    
    public void mostrar() {
        System.out.println("Medicamento: " + nombre + " - Cód: " + codMedicamento);
        System.out.println("Tipo: " + tipo + " - Precio: " + precio);
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public double getPrecio() {
        return precio;
    }
}

class Farmacia implements Serializable {
    private String nombreFarmacia;
    private int sucursal;
    private String direccion;
    private List<Medicamento> medicamentos;
    
    public Farmacia() {
        medicamentos = new ArrayList<>();
    }
    
    public void leer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre farmacia: ");
        this.nombreFarmacia = sc.nextLine();
        System.out.print("Sucursal: ");
        this.sucursal = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.print("Dirección: ");
        this.direccion = sc.nextLine();
        
        System.out.print("Cantidad medicamentos: ");
        int n = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nMedicamento " + (i+1) + ":");
            Medicamento med = new Medicamento();
            med.leer();
            medicamentos.add(med);
        }
    }
    
    public void mostrar() {
        System.out.println("\nFarmacia: " + nombreFarmacia);
        System.out.println("Sucursal: " + sucursal + " - Dirección: " + direccion);
        System.out.println("Medicamentos:");
        for (Medicamento med : medicamentos) {
            med.mostrar();
        }
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public int getSucursal() {
        return sucursal;
    }
    
    public void mostrarMedicamentos(String tipo) {
        System.out.println("\nMedicamentos para " + tipo + ":");
        for (Medicamento med : medicamentos) {
            if (med.getTipo().equalsIgnoreCase(tipo)) {
                med.mostrar();
            }
        }
    }
    
    public Medicamento buscaMedicamento(String nombre) {
        for (Medicamento med : medicamentos) {
            if (med.nombre.equalsIgnoreCase(nombre)) {
                return med;
            }
        }
        return null;
    }
}

class ArchFarmacia {
    private String na;
    
    public ArchFarmacia(String nombreArchivo) {
        this.na = nombreArchivo;
    }
    
    public void crearArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(na))) {
            oos.writeObject(new ArrayList<Farmacia>());
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }
    
    public void adicionar(Farmacia farmacia) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            List<Farmacia> farmacias = (List<Farmacia>) ois.readObject();
            farmacias.add(farmacia);
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(na))) {
                oos.writeObject(farmacias);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al guardar farmacia: " + ex.getMessage());
        }
    }
    
    public void listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            List<Farmacia> farmacias = (List<Farmacia>) ois.readObject();
            
            for (Farmacia farm : farmacias) {
                farm.mostrar();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al listar farmacias: " + e.getMessage());
        }
    }
    
    public void mostrarMedicamentosResfrios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            List<Farmacia> farmacias = (List<Farmacia>) ois.readObject();
            
            System.out.println("\nMedicamentos para resfriados:");
            for (Farmacia farm : farmacias) {
                farm.mostrarMedicamentos("resfriado");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar medicamentos: " + e.getMessage());
        }
    }
    
    public double precioMedicamentoTos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            List<Farmacia> farmacias = (List<Farmacia>) ois.readObject();
            
            double total = 0.0;
            int count = 0;
            for (Farmacia farm : farmacias) {
                for (Medicamento med : farm.medicamentos) {
                    if (med.getTipo().equalsIgnoreCase("tos")) {
                        total += med.getPrecio();
                        count++;
                    }
                }
            }
            return count > 0 ? total / count : 0.0;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al calcular precio: " + e.getMessage());
            return 0.0;
        }
    }
    
    public void mostrarMedicamentosMenorTos(double maxPrecio) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            List<Farmacia> farmacias = (List<Farmacia>) ois.readObject();
            
            System.out.println("\nMedicamentos para tos con precio menor a " + maxPrecio + ":");
            for (Farmacia farm : farmacias) {
                for (Medicamento med : farm.medicamentos) {
                    if (med.getTipo().equalsIgnoreCase("tos") && med.getPrecio() < maxPrecio) {
                        med.mostrar();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar medicamentos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArchFarmacia archivo = new ArchFarmacia("farmacias.dat");
        archivo.crearArchivo();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nIngrese datos de farmacia 1:");
        Farmacia farmacia1 = new Farmacia();
        farmacia1.leer();
        archivo.adicionar(farmacia1);
        
        System.out.println("\nIngrese datos de farmacia 2:");
        Farmacia farmacia2 = new Farmacia();
        farmacia2.leer();
        archivo.adicionar(farmacia2);
        
        System.out.println("\nListado completo:");
        archivo.listar();
        
        System.out.println("\nMedicamentos para resfriados:");
        archivo.mostrarMedicamentosResfrios();
        
        double precioPromedio = archivo.precioMedicamentoTos();
        System.out.println("\nPrecio promedio medicamentos para tos: " + precioPromedio);
        
        archivo.mostrarMedicamentosMenorT