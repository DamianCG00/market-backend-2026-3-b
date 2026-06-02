package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {


    private ProductoCrudRepository

    //SELECT * FORM productos
    public List<Producto> getAll() {
        //Se "castea" iterable a lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //Obtener productos por categoria
    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepostory.findByOrderByNombre
    }
}