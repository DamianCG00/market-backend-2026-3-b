package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Esto asume que en tu entidad 'Producto' tienes un atributo llamado 'idCategoria'
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Esto asume que en tu entidad 'Producto' tienes atributos llamados 'cantidadStock' y 'estado'
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}