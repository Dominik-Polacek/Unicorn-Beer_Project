package com.example.unicornbeerproject.controller;

import com.example.unicornbeerproject.dto.BeerDTO;
import com.example.unicornbeerproject.model.Beer;
import com.example.unicornbeerproject.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BeerService beerService;

    @GetMapping("/load-beers")
    public ResponseEntity<?> loadBeers() {
        beerService.loadBeerData();
        List<BeerDTO> beerDTOList = beerService.findAll()
                .stream()
                .map(BeerDTO::new).toList();
        return ResponseEntity.ok(beerDTOList);
    }

    @GetMapping("/load-beers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        BeerDTO beerDTO = new BeerDTO(beerService.findBeerById(id));
        return ResponseEntity.ok(beerDTO);
    }
}
