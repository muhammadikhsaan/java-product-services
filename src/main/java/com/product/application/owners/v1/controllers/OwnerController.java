package com.product.application.owners.v1.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.product.application.owners.v1.models.entities.OwnerEntity;
import com.product.application.owners.v1.models.payload.OwnerPayload;
import com.product.application.owners.v1.services.interfaces.OwnerServiceInterface;
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
@RequestMapping("/api/v1/owner")
public class OwnerController {
    
    @Autowired
    private OwnerServiceInterface ownerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<PaginationEntitiy<OwnerPayload>> get(){
        PaginationEntitiy<OwnerPayload> response = new PaginationEntitiy<OwnerPayload>();

        try {
            Iterable<OwnerEntity> categories = this.ownerService.getOwners();
            List<OwnerPayload> data = new ArrayList<OwnerPayload>();
            Long count = this.ownerService.ownerCounts();

            for (OwnerEntity categorie : categories) {
                OwnerPayload mapper = this.modelMapper.map(categorie, OwnerPayload.class);
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
    public ResponseEntity<GeneralResponse<OwnerPayload>> getByID(@PathVariable("id") Long id){
        GeneralResponse<OwnerPayload> response = new GeneralResponse<OwnerPayload>();

        try {
            OwnerEntity payload = this.ownerService.getOwner(id);
            OwnerPayload data = this.modelMapper.map(payload, OwnerPayload.class);

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
    public ResponseEntity<GeneralResponse<OwnerPayload>> post(@Valid @RequestBody OwnerEntity body, Errors errors) {
        GeneralResponse<OwnerPayload> response = new GeneralResponse<OwnerPayload>();
        
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            OwnerEntity payload = this.ownerService.saveOwner(body);
            OwnerPayload data = this.modelMapper.map(payload, OwnerPayload.class);
    
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
    public ResponseEntity<GeneralResponse<OwnerPayload>> put(@PathVariable("id") Long id, @RequestBody OwnerEntity body) {
        GeneralResponse<OwnerPayload> response = new GeneralResponse<OwnerPayload>();
        
        if (Objects.isNull(id)) {
            response.setStatus(false);
            response.setMessage("Owner can't be updated");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        try {
            body.setId(id);
            OwnerEntity payload = this.ownerService.updatedOwner(body);
            OwnerPayload data = this.modelMapper.map(payload, OwnerPayload.class);
    
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
            this.ownerService.deleteOwner(id);
            response.setStatus(true);
            response.setMessage(null);
            response.setData("Successfully delete Owner");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }
}
