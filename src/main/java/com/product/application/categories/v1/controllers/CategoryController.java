package com.product.application.categories.v1.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.product.application.categories.v1.models.entities.CategoryEntity;
import com.product.application.categories.v1.models.payload.CategoryPayload;
import com.product.application.categories.v1.services.interfaces.CategoryServiceInterfaces;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceInterfaces categoriesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<PaginationEntitiy<CategoryPayload>> get(){
        PaginationEntitiy<CategoryPayload> response = new PaginationEntitiy<CategoryPayload>();

        try {
            Iterable<CategoryEntity> categories = this.categoriesService.getCategories();
            List<CategoryPayload> data = new ArrayList<CategoryPayload>();
            Long count = this.categoriesService.categoriesCount();

            for (CategoryEntity categorie : categories) {
                CategoryPayload mapper = this.modelMapper.map(categorie, CategoryPayload.class);
                data.add(mapper);
            }

            response.setCount(count);
            response.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setCount(Long.valueOf(0));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<CategoryPayload>> getByID(@PathVariable("id") Long id){
        GeneralResponse<CategoryPayload> response = new GeneralResponse<CategoryPayload>();

        try {
            CategoryEntity payload = this.categoriesService.getCategory(id);
            CategoryPayload data = this.modelMapper.map(payload, CategoryPayload.class);

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
    public ResponseEntity<GeneralResponse<CategoryPayload>> post(@Valid @RequestBody CategoryEntity body, Errors errors) {
        GeneralResponse<CategoryPayload> response = new GeneralResponse<CategoryPayload>();
        
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            CategoryEntity payload = this.categoriesService.saveCategory(body);
            CategoryPayload data = this.modelMapper.map(payload, CategoryPayload.class);
    
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
    public ResponseEntity<GeneralResponse<CategoryPayload>> put(@PathVariable("id") Long id, @RequestBody CategoryEntity body) {
        GeneralResponse<CategoryPayload> response = new GeneralResponse<CategoryPayload>();
        
        if (Objects.isNull(id)) {
            response.setStatus(false);
            response.setMessage("Data can't be updated");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            body.setId(id);
            CategoryEntity payload = this.categoriesService.updatedCategory(body);
            CategoryPayload data = this.modelMapper.map(payload, CategoryPayload.class);
    
            response.setStatus(true);
            response.setData(data);
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
            this.categoriesService.deleteCategory(id);
            response.setStatus(true);
            response.setMessage(null);
            response.setData("Successfully delete Categories");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }
}
