package com.example.unicornbeerproject.service;

import com.example.unicornbeerproject.model.Beer;
import com.example.unicornbeerproject.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void loadBeerData() {
        Beer[] beers = restTemplate.getForObject("https://random-data-api.com/api/v2/beers?size=100", Beer[].class);
        if (beers != null) {
            beerRepository.saveAll(Arrays.asList(beers));
        }
    }
    public List<Beer> findAll() {
        return beerRepository.findAll();
    }


}
