package com.example.unicornbeerproject.controller;

import com.example.unicornbeerproject.dto.BeerDTO;
import com.example.unicornbeerproject.dto.RatingDTO;
import com.example.unicornbeerproject.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BeerService beerService;

    @GetMapping("")
    public ResponseEntity<?> loadBeers() {
        beerService.loadData();
        List<BeerDTO> beerDTOList = beerService.findAll()
                .stream()
                .map(BeerDTO::new).toList();
        return ResponseEntity.ok(beerDTOList);
    }

    @GetMapping("/beers")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(beerService.findAll()
                .stream()
                .map(BeerDTO::new)
                .toList());
    }

    @PostMapping("/beers")
    public ResponseEntity<?> add(@RequestBody BeerDTO beerDTO) {
        beerService.save(beerDTO);
        return ResponseEntity.ok(beerDTO);
    }

    @GetMapping("/beers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        BeerDTO beerDTO = new BeerDTO(beerService.findById(id));
        return ResponseEntity.ok(beerDTO);
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(beerService.deleteById(id));
    }

    @PutMapping("/beers/{id}")
    public ResponseEntity<?> updateRating(@RequestBody RatingDTO ratingDTO, @PathVariable Long id) {
        beerService.updateRating(id, ratingDTO);
        return ResponseEntity.ok(ratingDTO);
    }

}
