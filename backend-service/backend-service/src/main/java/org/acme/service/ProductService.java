package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProductService {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public ProductService() {
        // Inicializar con algunos datos de prueba
        addProduct(new Product(null, "Laptop Gamer", 1250.00, "Computación"));
        addProduct(new Product(null, "Mouse Inalámbrico", 25.50, "Accesorios"));
        addProduct(new Product(null, "Teclado Mecánico", 75.00, "Accesorios"));
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product addProduct(Product product) {
        Long id = idCounter.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    public Optional<Product> update(Long id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            return Optional.empty();
        }
        updatedProduct.setId(id);
        products.put(id, updatedProduct);
        return Optional.of(updatedProduct);
    }

    public boolean delete(Long id) {
        return products.remove(id) != null;
    }
}

