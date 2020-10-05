package com.shopping.backend.service.impl;

import com.shopping.backend.model.Product;
import com.shopping.backend.repository.ProductRepository;
import com.shopping.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.unsorted());
        return productRepository.findAll(pageRequest);
    }
}
