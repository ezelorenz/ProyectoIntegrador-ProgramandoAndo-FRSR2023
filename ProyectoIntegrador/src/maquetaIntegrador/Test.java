
package maquetaIntegrador;


import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    
    /*
        Los metodos guardarListaDePersonas y obtenerListaDePersona
        deben estar en otra seccion, inserto todo junto para armar la maqueta
    */
    
    
    public static void guardarListaDeProductos(File file, List<Producto> lista){

      try{
          FileOutputStream ficheroSalida = new FileOutputStream (file);
          ObjectOutputStream objetoSalida =new ObjectOutputStream (ficheroSalida);
          objetoSalida.writeObject (lista);
          objetoSalida.close ();
          
      }catch (FileNotFoundException e){
          System.out.println ("El fichero no exite");
          
      } catch(Exception e){
          System.out.println (e.getMessage ());
      }

    }
    
    public static List<Producto>obtenerListaDeProductos(File file){

        List<Producto>lista= new ArrayList<> ();
        try{
            FileInputStream fis = new FileInputStream (file);
            ObjectInputStream ois = new ObjectInputStream (fis);
            lista=(List<Producto>)ois.readObject ();
            ois.close ();
            
        }catch(FileNotFoundException e){
            System.out.println ("Fichero no existe");
            
        }catch (Exception e){
            System.out.println (e.getMessage ());
        }
        return lista;
    }
    
    
    
     public static void main (String[] args) {

       File file = new File ("D:\\ProyectoIntegrador\\src\\BasesDeDatos\\BaseProducto.txt");
       int opcion = 0;
       List<Producto>objetivo=obtenerListaDeProductos(file);
        Scanner sc=new Scanner (System.in);
        do{
            System.out.println ("Registro de Producto");
            System.out.println ("===================\n");
            System.out.println ("1-Ingresar un nuevo Producto");
            System.out.println ("2-Listar registro de Productos");
            System.out.println ("3-Buscar Producto");
            System.out.println ("4-Eliminar Producto");
            System.out.println ("5-Modificar datos de Producto");
            System.out.println ("6-Salir");
            System.out.println ("\nIngresar una opcion\n");
            opcion=sc.nextInt ();
            
            switch (opcion){
                case 1:
                    System.out.println ("Registro de Producto");
                    System.out.println ("===================\n");
                    System.out.println ("Ingrese los siguientes datos:\n");
                    sc.nextLine ();
                    System.out.println ("Id:");
                    String id=sc.nextLine ();
                    System.out.println ("Nombre:");
                    String nombre=sc.nextLine ();
                    System.out.println ("Cantidad:");
                    String cantidad=sc.nextLine ();
                    System.out.println ("Precio de Compra:");
                    double precio_compra = sc.nextDouble();
                    System.out.println("Precio de Venta:");
                    double precio_venta = sc.nextDouble();
                    objetivo.add (new Producto (id,nombre,cantidad,precio_compra,precio_venta));
                    guardarListaDeProductos (file,objetivo);
                    break;
                case 2:
                    System.out.println ("Listar datos del Producto");
                    System.out.println ("=======================\n");
                    System.out.println (FlipTableConverters.fromIterable (objetivo,Producto.class));
                    break;
                case 3:
                    System.out.println ("Buscar Producto");
                    System.out.println ("===============\n");
                    System.out.println ("Ingresa Id del Producto");
                    String buscado=sc.next ();
                    String mensaje="No se encontro el Producto\n";
                    Producto x=null;
                    for (Producto o:objetivo){
                        if(o.getId ().equals (buscado)){
                            mensaje="Producto encontrado\n";
                            x=o;
                        }
                    }
                    System.out.println ("\n"+mensaje);
                    String []headers={"Id","Nombre","Cantidad","Precio de Compra","Precio de Venta"};
                    if(x!=null){
                        String [][] data={{ x.getId (),
                                            x.getNombre (),
                                            x.getCantidad (),
                                            String.valueOf(x.getPrecio_compra ()),
                                            String.valueOf(x.getPrecio_venta())}
                                        };
                        
                        System.out.println (FlipTable.of(headers,data));
                    }
                    break;
                case 4:
                    /* Podemos ampliar el codigo haicendo una busqueda por id, otra por nombre, etc*/
                    System.out.println ("Eliminar Producto");
                    System.out.println ("================\n");
                    System.out.println ("Ingrese el id del Producto");
                    String eliminar=sc.next ();
                    String mensaje2="No se encontro el producto\n";
                    for (int i=0;i< objetivo.size ();i++){

                        if(objetivo.get (i).getId ().equals (eliminar)){
                            objetivo.remove (i);
                            mensaje2="Producto eliminado\n";
                        }
                    }
                    guardarListaDeProductos (file,objetivo);
                    System.out.println (mensaje2);
                    break;
                case 5:
                    System.out.println ("Modificar Producto");
                    System.out.println ("=================\n");
                    System.out.println ("Ingrese el Id del Producto");
                    
                    String modificar=sc.next ();
                    String mensaje3="No se encontro el Producto\n";
                    Producto producto=null;
                    
                    for(Producto o:objetivo){
                        if(o.getId ().equals (modificar)){
                            producto=o;
                            mensaje3="Producto encontrado";
                        }
                    }
                    System.out.println (mensaje3);
                    
                    int opcion2 = 0;
                    
                    if(producto!=null){
                        do{
                            System.out.println ("1-Modificar Nombre");
                            System.out.println ("2-Modificar Cantidad");
                            System.out.println ("3-Modificar Precio de Compra");
                            System.out.println ("4-Modificar Precio de Venta");
                            System.out.println ("5-Cancelar");
                            
                            opcion2=sc.nextInt ();
                            
                            switch (opcion2){
                                case 1:
                                    sc.nextLine ();
                                    System.out.println ("Nombre actual: "+producto.getNombre());
                                    producto.setNombre (sc.nextLine ());
                                    break;
                                case 2:
                                    System.out.println ("Cantidad actual: "+producto.getCantidad());
                                    producto.setCantidad (sc.nextLine ());
                                    break;
                                case 3:
                                    System.out.println("Precio de Compra actual "+producto.getPrecio_compra());
                                    producto.setPrecio_compra(sc.nextDouble ());
                                    sc.nextLine ();
                                    break;
                                case 4:
                                    System.out.println("Precio de Venta actual "+producto.getPrecio_venta());
                                    producto.setPrecio_compra(sc.nextDouble());
                                    sc.nextLine();
                                    break;
                                case 5:
                                    System.out.println ("\nOpcion cancelada\n");
                                    guardarListaDeProductos (file,objetivo);
                                    break;
                                default:
                                    System.out.println ("\nOpcion invalida\n");
                            }
                        }while (opcion2!=5);
                    }
                    break;
                case 6:
                    System.out.println ("Saliendo del programa");
                    break;
                default:
                    System.out.println ("Opcion invalida");
            }
            
        }while (opcion!=6);

    }

}
