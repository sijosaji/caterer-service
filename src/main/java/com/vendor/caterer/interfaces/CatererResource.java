package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.dto.CatererUpdateRequest;
import com.vendor.caterer.dto.SearchRequest;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("v1/caterers")
public interface CatererResource {
    @PostMapping
    ResponseEntity addCaterer(@RequestBody CatererCreateRequest createRequest);

    @GetMapping("/{id}")
    ResponseEntity<Caterer> getCaterer(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<Caterer> updateCaterer(@PathVariable UUID id, @RequestBody CatererUpdateRequest updateRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCaterer(@PathVariable UUID id);
    @PostMapping("search")
    Pagination<Caterer> getCaterers(@RequestBody SearchRequest searchRequest);
}
