package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.itmo.models.user.Product;
import ru.itmo.repositories.ProductRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(ApplicationArguments args) {
        productRepository.save(new Product("apple", 100.0, ""));
        productRepository.save(new Product("potatoe", 30.0, ""));
    }
}
