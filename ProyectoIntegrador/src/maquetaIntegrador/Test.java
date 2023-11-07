package maquetaIntegrador;

import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        String contraseña;

        System.out.println("Bienvenido a Programando Ando Stock");

        // Pedir la contraseña
        do {
            System.out.println("Por favor, ingrese la clave:");
            contraseña = sc.next();
            if (!contraseña.equals("4444")) {
                System.out.println("Clave incorrecta. Intente de nuevo.");
            }
        } while (!contraseña.equals("4444"));

        do {
            System.out.println("Selecciones una opcion");
            System.out.println("\t1-Ingresar a proveedores");
            System.out.println("\t2-Ingresar a Menu");
            System.out.println("\t3-Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("*=====*=====*=====*======*\n");
                    MetodosProveedor.mostrarProveedor();
                    System.out.println("*=====*=====*=====*======*\n");
                    break;
                case 2:
                    try {
                        MetodosProducto.mostrarMenu();
                    } catch (IOException e) {
                        System.out.println("Ocurrió un error de entrada/salida al ejecutar la función debido a un problema con el archivo de datos.");
                    }
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
