package com.vendor.caterer.services;

import com.vendor.caterer.dao.CatererRepository;
import com.vendor.caterer.dao.MenuItemRepository;
import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.dto.CatererUpdateRequest;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.MenuItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository dao;
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ResponseEntity<MenuItem> saveMenuItem(MenuItem createRequest) {
        //createRequest.getItems().forEach(menuItem -> menuItem.setId(UUID.randomUUID()));
        MenuItem menuItem = convertCreateRequestModelToDocModel(createRequest);
        dao.save(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    private MenuItem convertCreateRequestModelToDocModel(MenuItem createRequest) {
        MenuItem menu = MODEL_MAPPER.map(createRequest, MenuItem.class);
        menu.setId(UUID.randomUUID());
        return menu;
    }
//
//    private Caterer convertUpdateRequestModelToDocModel(Caterer caterer,CatererUpdateRequest catererUpdateRequest) {
//        //TODO: CHANGE THIS TO MODEL_MAPPER IN FUTURE
//        caterer.setName(catererUpdateRequest.getName());
//        caterer.setBranchId(catererUpdateRequest.getBranchId());
//        caterer.setPhoneNumber(catererUpdateRequest.getPhoneNumber());
//        caterer.setWebsite(catererUpdateRequest.getWebsite().toString());
//        caterer.setEmailId(catererUpdateRequest.getEmailId());
//        caterer.setCatererLocation(catererUpdateRequest.getCatererLocation());
//        caterer.setLastUpdated(LocalDateTime.now().toString());
//        caterer.setAddress(catererUpdateRequest.getAddress());
//        return caterer;
//    }
//
//    public ResponseEntity<Caterer> getCaterer(UUID id){
//        Optional<Caterer> restaurant = dao.findById(id);
//        return restaurant.map(caterer -> ResponseEntity.status(HttpStatus.ACCEPTED).body(caterer)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//    public ResponseEntity<Caterer> updateCaterer(UUID id, CatererUpdateRequest updateRequest) {
//        Optional<Caterer> caterer = dao.findById(id);
//
//        if (caterer.isPresent()) {
//            Caterer restaurant = convertUpdateRequestModelToDocModel(caterer.get(),updateRequest);
//            dao.save(restaurant);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(restaurant);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    public ResponseEntity<Void> deleteCaterer(UUID id) {
//        if (dao.existsById(id)) {
//            dao.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
