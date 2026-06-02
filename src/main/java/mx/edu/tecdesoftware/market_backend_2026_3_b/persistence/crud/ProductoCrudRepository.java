package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer> {

    //obtener la lista de productos filtrados por id  de categoria y ordenados ascendentemente por nombre

        /* Select *
        from Categorias
        WHERE id_categoria = ?
        ORDER BY Nombre ASC

         */


    List<Producto> findByCantidadOrderByNombre(int idCategoria);


    //Obtener las productos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);
}



