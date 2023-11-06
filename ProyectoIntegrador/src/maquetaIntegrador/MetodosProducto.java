package maquetaIntegrador;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodosProducto {

    public static void mostrarMenu() throws IOException {
        String ruta = "\\src\\maquetaIntegrador";
        File file = new File(ruta + "\\BaseProduc1tos.txt");
        int opcion = 0;
        List<Producto> objetivos = MetodosProducto.obtenerListaDeProductos(file);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Menu");
            System.out.println("===================\n");
            System.out.println("1-Ingresar un nuevo Producto");
            System.out.println("2-Listar registro de Productos");
            System.out.println("3-Buscar Producto");
            System.out.println("4-Eliminar Producto");
            System.out.println("5-Modificar datos de Producto");
            System.out.println("6-Salir");
            System.out.println("\nIngresar una opcion\n");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    registrarProducto(objetivos, sc, file);
                    break;
                case 2:

                    System.out.println("Listar datos del Producto");
                    System.out.println("=======================\n");

                    // Definir los nombres de las columnas
                    String[] headers = {"ID", "Nombre", "Cantidad", "Precio Compra", "Precio Venta"};

                    // Crear una matriz de objetos para los datos
                    Object[][] data = new Object[objetivos.size()][headers.length];

                    // Llenar la matriz de datos con los valores de la lista de productos
                    for (int i = 0; i < objetivos.size(); i++) {
                        Producto producto = objetivos.get(i);
                        data[i][0] = producto.getId();
                        data[i][1] = producto.getNombre();
                        data[i][2] = producto.getCantidad();
                        data[i][3] = producto.getPrecio_compra();
                        data[i][4] = producto.getPrecio_venta();
                    }

                    // Generar y mostrar la tabla
                    System.out.println(FlipTableConverters.fromObjects(headers, data));

                    //System.out.println (FlipTableConverters.fromIterable (objetivos,Producto.class));
                    break;
                case 3:
                    System.out.println("Buscar Producto");
                    System.out.println("===============\n");
                    System.out.println("Ingresa Id del Producto");
                    String buscado = sc.next();
                    mostrarProducto(buscado, objetivos);
                    break;
                case 4:
                    /* Podemos ampliar el codigo haciendo una busqueda por id, otra por nombre, etc*/
                    //No esta funcionando la eliminacion diferenciada

                    int eliminar = 0;
                    do {
                        System.out.println("Eliminar Producto");
                        System.out.println("================\n");
                        System.out.println("Eliminar por:");
                        System.out.println("1. Id");
                        System.out.println("2. Nombre");
                        System.out.println("3. Salir/Cancelar");
                        eliminar = sc.nextInt();
                        sc.nextLine();
                        switch (eliminar) {
                            case 1:
                                System.out.println("Ingrese el Id del producto");
                                String idProducto = sc.nextLine();
                                for (int i = 0; i < objetivos.size(); i++) {
                                    objetivos.remove(i);
                                    System.out.println("Producto eliminado\n");
                                    break;
                                }
                                break;
                            case 2:
                                System.out.println("Ingrese el nombre del producto");
                                String nombreProducto = sc.nextLine();
                                for (int i = 0; i < objetivos.size(); i++) {
                                    if (objetivos.get(i).getNombre().equals(nombreProducto)) {
                                        objetivos.remove(i);
                                        System.out.println("Producto eliminado\n");
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("\nOperacion cancelada\n");
                                MetodosProducto.guardarListaDeProductos(file, objetivos);
                                break;
                            default:
                                System.out.println("Opción inválida\n");
                                break;
                        }
                        guardarListaDeProductos(file, objetivos);
                    } while (eliminar != 3);
                    break;
                case 5:
                    System.out.println("Modificar Producto");
                    System.out.println("=================\n");
                    System.out.println("Ingrese el Id del Producto");

                    String modificar = sc.next();
                    String mensaje3 = "No se encontro el Producto\n";
                    Producto producto = null;

                    for (Producto objetivo : objetivos) {
                        if (Integer.valueOf(objetivo.getId()).equals(modificar)) {
                            producto = objetivo;
                            mensaje3 = "Producto encontrado";
                        }
                    }
                    System.out.println(mensaje3 + "\n");

                    int opcion2 = 0;

                    if (producto != null) {
                        do {
                            System.out.println("1-Modificar Nombre");
                            System.out.println("2-Modificar Cantidad");
                            System.out.println("3-Modificar Precio de Compra");
                            System.out.println("4-Modificar Precio de Venta");
                            System.out.println("5-Cancelar");

                            opcion2 = sc.nextInt();

                            switch (opcion2) {
                                case 1:
                                    //sc.nextLine ();
                                    System.out.println("Nombre actual: " + producto.getNombre());
                                    System.out.println("Ingrese nuevo nombre");
                                    producto.setNombre(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.println("Cantidad actual: " + producto.getCantidad());
                                    System.out.println("Ingrese nueva cantidad");
                                    producto.setCantidad(sc.nextLine());
                                    break;
                                case 3:
                                    System.out.println("Precio de Compra actual " + producto.getPrecio_compra());
                                    System.out.println("Ingrese nuevo precio de compra");
                                    producto.setPrecio_compra(sc.nextDouble());
                                    sc.nextLine();
                                    break;
                                case 4:
                                    System.out.println("Precio de Venta actual " + producto.getPrecio_venta());
                                    System.out.println("Ingrese nuevo precio de venta");
                                    producto.setPrecio_venta(sc.nextDouble());
                                    sc.nextLine();
                                    break;
                                case 5:
                                    System.out.println("\nOpcion cancelada\n");
                                    MetodosProducto.guardarListaDeProductos(file, objetivos);
                                    break;
                                default:
                                    System.out.println("\nOpcion invalida\n");
                            }
                        } while (opcion2 != 5);
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);
    }

    public static void guardarListaDeProductos(File file, List<Producto> lista) {

        try {
            FileOutputStream ficheroSalida = new FileOutputStream(file);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
            objetoSalida.writeObject(lista);
            objetoSalida.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no exite");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<Producto> obtenerListaDeProductos(File file) {

        List<Producto> lista = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (List<Producto>) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no existe");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static void mostrarProducto(String buscado, List<Producto> objetivos) {
        String mensaje = "No se encontro el Producto\n";
        Producto producto = null;
        for (Producto objetivo : objetivos) {
            if (Integer.valueOf(objetivo.getId()).equals(buscado)) {
                mensaje = "Producto encontrado\n";
                producto = objetivo;
            }
        }
        System.out.println("\n" + mensaje);
        String[] headers = {"Id", "Nombre", "Cantidad", "Precio de Compra", "Precio de Venta"};
        if (producto != null) {
            String[][] data = {{String.valueOf(producto.getId()),
                producto.getNombre(),
                producto.getCantidad(),
                String.valueOf(producto.getPrecio_compra()),
                String.valueOf(producto.getPrecio_venta())}
            };

            System.out.println(FlipTable.of(headers, data));
        }
    }

    public static void registrarProducto(List<Producto> objetivos, Scanner sc, File file) throws IOException {
        System.out.println("Registro de Producto");
        System.out.println("===================\n");
        System.out.println("Ingrese los siguientes datos:\n");
        sc.nextLine();
        /*System.out.println ("Id:");
                    String id=sc.nextLine ();
                    int idEntero = Integer.parseInt(id);
                    boolean repetido = false;
                    do{         
                        for(Producto obj:objetivo){
                            if(Integer.valueOf(obj.getId ()).equals (id)){
                                repetido = true;
                                System.out.println("ID existente. Pruebe otro ID");
                                id = sc.nextLine();
                            }
                            else{
                                repetido = false;
                            }
                        }
                    }while(repetido == true);*/
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("Cantidad:");
        String cantidad = sc.nextLine();
        System.out.println("Precio de Compra:");
        double precio_compra = sc.nextDouble();
        double precio_venta = precio_compra * 1.3;
        objetivos.add(new Producto(1, nombre, cantidad, precio_compra, precio_venta));

        FileWriter fw = new FileWriter("listaProductos.txt", false);
        File archivo = new File("listaProductos.txt");
        FileWriter escribir;
        try {
            escribir = new FileWriter(archivo, false);
            PrintWriter linea = new PrintWriter(escribir);
            for (Producto a : objetivos) {
                linea.println(a.getId() + ","
                        + a.getNombre() + ","
                        + a.getCantidad() + ","
                        + a.getPrecio_compra() + ","
                        + a.getPrecio_venta());
            }
            linea.close();
            escribir.close();
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo");
        }
        //MetodosProducto.guardarListaDeProductos(file, objetivo);

    }
}
