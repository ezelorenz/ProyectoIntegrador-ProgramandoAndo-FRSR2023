
package maquetaIntegrador;

import com.jakewharton.fliptables.FlipTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MetodosProducto {
    
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
    
    public static void mostrarProducto(String buscado, List<Producto> objetivo){
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
    }
}
