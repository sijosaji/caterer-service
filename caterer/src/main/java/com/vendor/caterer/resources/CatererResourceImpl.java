package com.vendor.caterer.resources;

import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.interfaces.CatererResource;
import com.vendor.caterer.services.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatererResourceImpl implements CatererResource {
    @Autowired
    private CatererService catererService;
    @Override
    public ResponseEntity addCaterer(CatererCreateRequest createRequest) {
        return catererService.saveCaterer(createRequest);
    }
}
