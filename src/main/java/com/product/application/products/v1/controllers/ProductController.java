package com.product.application.products.v1.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.product.application.products.v1.models.entities.ProductEntity;
import com.product.application.products.v1.models.payload.ProductCategoryPayload;
import com.product.application.products.v1.models.payload.ProductPayload;
import com.product.application.products.v1.services.interfaces.ProductServiceInterfaces;
import com.product.shared.response.general.GeneralResponse;
import com.product.shared.response.pagination.PaginationEntitiy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductServiceInterfaces productService;
    
    @GetMapping
    public ResponseEntity<PaginationEntitiy<ProductCategoryPayload>> get(){
        PaginationEntitiy<ProductCategoryPayload> response = new PaginationEntitiy<ProductCategoryPayload>();

        try {
            List<ProductEntity> products = this.productService.getProducts();
            List<ProductCategoryPayload> data = new ArrayList<ProductCategoryPayload>();
            Long count = this.productService.productsCount();

            for (ProductEntity product : products) {
                ProductCategoryPayload categoriesMapper = this.modelMapper.map(product, ProductCategoryPayload.class);
                data.add(categoriesMapper);
            }

            response.setCount(count);
            response.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.err.println(e);
            response.setCount(Long.valueOf(0));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<ProductCategoryPayload>> getByID(@PathVariable("id") Long id){
        GeneralResponse<ProductCategoryPayload> response = new GeneralResponse<ProductCategoryPayload>();

        try {
            ProductEntity payload = this.productService.getProduct(id);
            ProductCategoryPayload data = this.modelMapper.map(payload, ProductCategoryPayload.class);

            response.setStatus(true);
            response.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<ProductPayload>> post(@Valid @RequestBody ProductEntity body, Errors errors) {
        GeneralResponse<ProductPayload> response = new GeneralResponse<ProductPayload>();
        
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            ProductEntity payload = this.productService.saveProduct(body);
            ProductPayload data = this.modelMapper.map(payload, ProductPayload.class);
    
            response.setStatus(true);
            response.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GeneralResponse<ProductPayload>> put(@PathVariable("id") Long id, @RequestBody ProductEntity body) {
        GeneralResponse<ProductPayload> response = new GeneralResponse<ProductPayload>();
        
        if (Objects.isNull(id)) {
            response.setStatus(false);
            response.setMessage("Product can't be updated");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            body.setId(id);
            ProductEntity payload = this.productService.updatedProduct(body);
            ProductPayload categoriesPayload = this.modelMapper.map(payload, ProductPayload.class);
    
            response.setStatus(true);
            response.setData(categoriesPayload);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<String>> delete(@PathVariable("id") Long id){
        GeneralResponse<String> response = new GeneralResponse<String>();

        try {
            this.productService.deleteProduct(id);
            response.setStatus(true);
            response.setMessage(null);
            response.setData("Successfully delete Product");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }
}
