package com.example.tuan5.service;

import com.example.tuan5.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> listProduct = new ArrayList<>();

    public ProductService() {
    }

    public void add(Product newProduct) {
        newProduct.setId(listProduct.size() + 1);
        listProduct.add(newProduct);
    }

    public List<Product> GetAll() {
        return listProduct;
    }

    public Product get(int id) {
        return listProduct.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void edit(Product editProduct) {
        Product find = listProduct.get(editProduct.getId());
        if (find != null) {
            find.setName(editProduct.getName());
            find.setImage(editProduct.getImage());
            find.setPrice(editProduct.getPrice());
        }
    }

    public void delete(int id) {
        Iterator<Product> iterator = listProduct.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Product> search(String keyword) {
        return listProduct.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
