package com.restaurant.service;

import com.restaurant.dao.ProductDAO;
import com.restaurant.dto.Product;
import com.restaurant.util.BusKeyGen;
import com.restaurant.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martha on 2/25/2017.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDAO productDAO;

    @Override
    public Product createProduct(String productName) {
        // Validate args
        if(!Validate.valid(productName))return null;
        if(productDAO.containsProductByName(productName))return null;
        // Operate
        Product productObject = new Product(productName, BusKeyGen.nextKey());
        return productDAO.writeProduct(productObject);
    }

    @Override
    public Product findProduct(int productId) {
        // Validate args
        if(!Validate.valid(productId))return null;
        // Persist
        return productDAO.readProduct(productId);
    }

    @Override
    public Product findProduct(String productName) {
        // Validate args
        if(!Validate.valid(productName)) return null;
        // Persist
        return productDAO.readProduct(productName);
    }

    @Override
    public Product updateProductName(int productId, String productName) {
        // Validate
        if(!Validate.valid(productName, productId))return null;
        Product product = productDAO.readProduct(productId);
        if(product == null) return null;
        // Form entity
        product.setProductName(productName);
        // Persist
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int productId) {
        // Validate
        if(!Validate.valid(productId)) return false;
        // Persist
        return productDAO.deleteProduct(productId);
    }
}
