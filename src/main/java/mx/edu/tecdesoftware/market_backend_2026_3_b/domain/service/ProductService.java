package mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sun.org.apache.xml.internal.serializer.Version.getProduct;

@Service
public class ProductService {


    //con esto lo llama
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts()
    {
        return  productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId)
    {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId)
    {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product)
    {
        return productRepository.save(product);
    }



    //verificar algo
    public boolean delate(int productId)
    {
        if (getProduct(productId).isPresent())
        {
            productRepository.delate(productId);
            return true;
        } else {
            return false;
        }



    }




}
