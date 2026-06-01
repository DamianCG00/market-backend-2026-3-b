package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;

import  java.util.*;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;


    //SELECT * FORM productos
    public List<Producto> getAll() {
        //Se "castea" Iterable a lista
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
