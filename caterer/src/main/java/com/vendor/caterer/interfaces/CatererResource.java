package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.CatererCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("v1/restaurants")
public interface CatererResource {
    @PostMapping
    ResponseEntity addCaterer(@RequestBody CatererCreateRequest createRequest);
}
