package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import java.util.List;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.IProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
//Le da acceso a la Base de Datos (BD)


public class ProductoRepository implements ProductRepository {

    @Autowired
    private IProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAll() {
        //Se "castea" Iterable a la lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }
    //Pbtener productos por categoria
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByCantidadOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }
    //Obtener productos escasos
    public Optional<List<Product>> getScarceProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }
    //obtener un producto dado el ID
    //Obtener un producto dado el ID
    @Override
    public Optional<Product> getProduct(int productoId) {
        return productoCrudRepository.findById(productoId).map(producto -> productMapper.toProduct(producto));
    }

    //Guardar un producto
    @Override
    public Product save(Product product) {
        // 1. Convertimos el Product (dominio) a Producto (entidad)
        Producto producto = productMapper.toProducto(product);
        // 2. Lo guardamos en la BD y convertimos el resultado de vuelta a Product
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}