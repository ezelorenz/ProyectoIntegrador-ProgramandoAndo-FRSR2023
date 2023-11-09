package maquetaIntegrador;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private String cantidad;
    private double precio_compra;
    private double precio_venta;

    public Producto(int id, String nombre, String cantidad, double precio_compra, double precio_venta) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        try {
        this.precio_compra = Double.parseDouble(precio_compra);
        }catch(NumberFormatException e){
        }
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
}
