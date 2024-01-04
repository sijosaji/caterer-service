package com.vendor.caterer.resources;

import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.dto.CatererUpdateRequest;
import com.vendor.caterer.interfaces.CatererResource;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.services.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CatererResourceImpl implements CatererResource {
    @Autowired
    private CatererService catererService;
    @Override
    public ResponseEntity addCaterer(CatererCreateRequest createRequest) {
        return catererService.saveCaterer(createRequest);
    }

    @Override
    public ResponseEntity<Caterer> getCaterer(UUID id) {
        return catererService.getCaterer(id);
    }

    @Override
    public ResponseEntity<Caterer> updateCaterer(UUID id, CatererUpdateRequest updateRequest) {
        return catererService.updateCaterer(id,updateRequest);
    }

    @Override
    public ResponseEntity<Void> deleteCaterer(UUID id) {
        return catererService.deleteCaterer(id);
    }
}
