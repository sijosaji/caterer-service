package com.vendor.caterer.services;

import com.vendor.caterer.dao.CatererRepository;
import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.model.Caterer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class CatererService {
    @Autowired
    private CatererRepository dao;
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ResponseEntity<Caterer> saveCaterer(CatererCreateRequest createRequest) {
        createRequest.getItems().forEach(menuItem -> menuItem.setId(UUID.randomUUID()));
        Caterer restaurant = convertCreateRequestModelToDocModel(createRequest);
        dao.save(restaurant);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(restaurant);
    }

    private Caterer convertCreateRequestModelToDocModel(CatererCreateRequest createRequest) {
        Caterer caterer =  MODEL_MAPPER.map(createRequest, Caterer.class);
        UUID catererId = UUID.randomUUID();
        caterer.setId(catererId);
        if (Objects.isNull(createRequest.getBranchId())) {
            caterer.setBranchId(catererId);
        }
        caterer.setCreatedOn(LocalDateTime.now().toString());
        caterer.setLastUpdated(LocalDateTime.now().toString());
        return caterer;
    }
}
