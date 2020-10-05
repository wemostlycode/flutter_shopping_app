package com.shopping.backend.service;

import com.shopping.backend.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    public Page<Product> getAllProducts(int pageNumber, int pageSize);

}
