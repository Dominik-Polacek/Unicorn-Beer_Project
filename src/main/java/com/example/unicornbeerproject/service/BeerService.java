package com.example.unicornbeerproject.service;

import com.example.unicornbeerproject.dto.BeerDTO;
import com.example.unicornbeerproject.dto.RatingDTO;
import com.example.unicornbeerproject.model.Beer;
import com.example.unicornbeerproject.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void loadData() {
        Beer[] beers = restTemplate.getForObject("https://random-data-api.com/api/v2/beers?size=100", Beer[].class);
        if (beers != null) {
            beerRepository.saveAll(Arrays.asList(beers));
        }
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Beer findById(Long id) {
        return beerRepository.findById(id).orElse(null);
    }

    public void save(BeerDTO beerDTO) {
        beerRepository.save(new Beer(beerDTO));
    }

    public Optional<Beer> deleteById(Long id) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        optionalBeer.ifPresent(beerRepository::delete);

        return optionalBeer;
    }

    public void updateRating(Long id, RatingDTO ratingDTO) {
        if (ratingDTO.rating() < 0 || ratingDTO.rating() > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        beerRepository.findById(id).ifPresent(beer -> {
            beer.setRating(ratingDTO.rating());
            beer.setComment(ratingDTO.comment());
            beerRepository.save(beer);
        });
    }

}
