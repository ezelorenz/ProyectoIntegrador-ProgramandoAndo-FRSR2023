package maquetaIntegrador;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("Selecciones una opcion");
            System.out.println("\t1-Ingresar a proveedores:");
            System.out.println("\t2-Ingresar a Menu");
            System.out.println("\t3-Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("*=====*=====*=====*======*\n");
                    MetodosProveedor.mostrarProveedor();
                    System.out.println("*=====*=====*=====*======*\n");
                    break;
                case 2:
                    MetodosProducto.mostrarMenu();
                    break;
                case 3:
                    System.out.println("Gracias");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 3);
    }
}
