package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import java.io.Serializable;

public class CompraProductoPK implements Serializable {

    @Column(name = "Id_compra")
    private Integer idCompra;

    @Column(name = "Id_producto")
    private Integer IdProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Integer idProducto) {
        IdProducto = idProducto;
    }
}
