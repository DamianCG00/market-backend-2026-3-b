package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import java.util.List;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.IProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    public IProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        //Se "castea" Iterable a la lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByCantidadOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getById(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    public Producto addProducto(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void deleteProducto(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}