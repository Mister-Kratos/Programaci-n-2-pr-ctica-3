import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Cliente implements Serializable {
    private int id;
    private String nombre;
    private int telefono;
    
    public Cliente(int id, String nombre, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getTelefono() {
        return telefono;
    }
}

class ArchivoCliente {
    private String nomA;
    
    public ArchivoCliente(String nombreArchivo) {
        this.nomA = nombreArchivo;
    }
    
    public void crearArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
            oos.writeObject(new ArrayList<Cliente>());
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }
    
    public void guardaCliente(Cliente c) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            clientes.add(c);
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
                oos.writeObject(clientes);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al guardar cliente: " + ex.getMessage());
        }
    }
    
    public Cliente buscarCliente(int c) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            
            for (Cliente cli : clientes) {
                if (cli.getId() == c) {
                    return cli;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
    
    public Cliente buscarCelularCliente(int c) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            
            for (Cliente cli : clientes) {
                if (cli.getTelefono() == c) {
                    return cli;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar cliente por teléfono: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        ArchivoCliente archivo = new ArchivoCliente("clientes.dat");
        archivo.crearArchivo();
        
        archivo.guardaCliente(new Cliente(1, "Carlos Ruiz", 7654321));
        archivo.guardaCliente(new Cliente(2, "Ana Xambrana", 7123456));
        
        Cliente cli = archivo.buscarCliente(1);
        if (cli != null) {
            System.out.println("Cliente encontrado: " + cli.getNombre() + ", Tel: " + cli.getTelefono());
        }
        
        Cliente cliCel = archivo.buscarCelularCliente(7123456);
        if (cliCel != null) {
            System.out.println("Cliente por teléfono: " + cliCel.getNombre());
        }
    }
}