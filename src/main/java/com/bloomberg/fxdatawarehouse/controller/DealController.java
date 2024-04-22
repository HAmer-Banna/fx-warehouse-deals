package com.bloomberg.fxdatawarehouse.controller;


import java.util.List;

import com.bloomberg.fxdatawarehouse.model.Deal;
import com.bloomberg.fxdatawarehouse.service.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Long id) {
        Deal deal = dealService.getDealById(id);
        return ResponseEntity.ok(deal);
    }

    @PostMapping
    public ResponseEntity<?> createDeal(@RequestBody Deal deal) {
        dealService.saveDeal(deal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeal(@PathVariable Long id, @Valid @RequestBody Deal deal) {
        dealService.updateDeal(id, deal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.ok().build();
    }
}

