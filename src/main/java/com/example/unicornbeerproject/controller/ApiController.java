package com.example.unicornbeerproject.controller;

import com.example.unicornbeerproject.dto.BeerDTO;
import com.example.unicornbeerproject.model.Beer;
import com.example.unicornbeerproject.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/add")
    public ResponseEntity<?> addBeer(@RequestBody BeerDTO beerDTO) {
        beerService.saveBeer(beerDTO);
        return ResponseEntity.ok(beerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable Long id) {
        return ResponseEntity.ok(beerService.deleteById(id));
    }

}
