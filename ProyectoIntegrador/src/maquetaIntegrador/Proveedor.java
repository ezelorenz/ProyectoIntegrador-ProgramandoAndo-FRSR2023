package maquetaIntegrador;

public class Proveedor {
    private String producto;
    private String descripcion;
    private String marca;
    private String cantidad;
    private String unidad;
    private String proveedor;
    private String categoria;

    public Proveedor(String producto, String descripcion, String marca, String cantidad, String unidad, String proveedor, String categoria) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.proveedor = proveedor;
        this.categoria = categoria;
    }

    public Proveedor() {
    }

    
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto:").append(producto);
        sb.append(", Descripcion:").append(descripcion);
        sb.append(", Marca:").append(marca);
        sb.append(", Cantidad:").append(cantidad);
        sb.append(", Unidad:").append(unidad);
        sb.append(", Proveedor:").append(proveedor);
        sb.append(", Categoria:").append(categoria);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
