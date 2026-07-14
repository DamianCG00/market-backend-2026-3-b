package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ICompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.IProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private ICompraCrudRepository compraCrudRepository;

    @Autowired
    private IProductoCrudRepository productoCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        //Asignación correcta de la compra y del producto real a cada línea antes de guardar.
        //Ambos son obligatorios: id_compra e id_producto son insertable=false/updatable=false
        //en CompraProducto, así que Hibernate solo los puede escribir a partir de las
        //entidades asociadas (@MapsId), no a partir del id embebido por sí solo.
        compra.getProductos().forEach(compraProducto -> {
            compraProducto.setCompra(compra);

            Integer idProducto = compraProducto.getId().getIdProducto();
            Producto producto = productoCrudRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException(
                            "No existe el producto con id " + idProducto));
            compraProducto.setProducto(producto);
        });

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}