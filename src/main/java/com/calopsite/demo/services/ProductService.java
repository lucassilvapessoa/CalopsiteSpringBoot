package com.calopsite.demo.services;

import com.calopsite.demo.domain.entities.Product;
import com.calopsite.demo.utils.exceptions.InvalidQuantityException;
import com.calopsite.demo.utils.exceptions.NotFoundException;
import com.calopsite.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void checkProductQuantity(Long productId, Float quantity){
        if( (getProductIfExist(productId).getQuantity()) <= quantity)
            throw new InvalidQuantityException(HttpStatus.BAD_REQUEST,"Quantidade Invalida");
    }

    public Product getProductIfExist(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new NotFoundException(HttpStatus.BAD_REQUEST,"Produto não cadastrado");
        return product.get();
    }

    public void updateQuantity(Long productId, Float quantity){
        Product product = getProductIfExist(productId);
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

}
