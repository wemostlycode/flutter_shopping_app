package com.shopping.backend.controller;

import com.shopping.backend.constants.ApiNameConstants;
import com.shopping.backend.constants.Constant;
import com.shopping.backend.dto.response.APIResponse;
import com.shopping.backend.dto.response.ResponseUtil;
import com.shopping.backend.model.Product;
import com.shopping.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = ApiNameConstants.PRODUCTS)
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getAllProducts(@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize);

        return responseUtil.successResponse(products.getContent());

    }

}
