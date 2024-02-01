package com.example.unicornbeerproject.dto;


import com.example.unicornbeerproject.model.Beer;

public record BeerDTO(Long id, String uid, String brand, String name, String style, String hop,
                      String yeast, String malts, String ibu, String alcohol, String blg, Integer rating, String comment) {

    public BeerDTO(Beer beer) {
        this(beer.getId(), beer.getUid(), beer.getBrand(), beer.getName(), beer.getStyle(), beer.getHop(),
                beer.getYeast(), beer.getMalts(), beer.getIbu(), beer.getAlcohol(), beer.getBlg(), beer.getRating(), beer.getComment());
    }

}
