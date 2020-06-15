package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.itmo.models.user.Product;
import ru.itmo.repositories.ProductRepository;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(ApplicationArguments args) {
        addProductToDB(new Product("apple", 100.0, ""));
        addProductToDB(new Product("potatoe", 30.0, ""));
    }

    private void addProductToDB(Product product) {
        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            if (p.getName().equals(product.getName())) {
                return;
            }
        }
        productRepository.save(product);
    }
}
