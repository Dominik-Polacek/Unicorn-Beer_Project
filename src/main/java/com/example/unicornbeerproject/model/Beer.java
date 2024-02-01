package com.example.unicornbeerproject.model;

import com.example.unicornbeerproject.dto.BeerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @Id
    private Long id;
    private String uid;
    private String brand;
    private String name;
    private String style;
    private String hop;
    private String yeast;
    private String malts;
    private String ibu;
    private String alcohol;
    private String blg;
    private Integer rating;
    private String comment;

    public Beer(BeerDTO beerDTO) {
        this.id = beerDTO.id();
        this.uid = beerDTO.uid();
        this.brand = beerDTO.brand();
        this.name = beerDTO.name();
        this.style = beerDTO.style();
        this.hop = beerDTO.hop();
        this.yeast = beerDTO.yeast();
        this.malts = beerDTO.malts();
        this.ibu = beerDTO.ibu();
        this.alcohol = beerDTO.alcohol();
        this.blg = beerDTO.blg();
        this.rating = beerDTO.rating();
        this.comment = beerDTO.comment();
    }
}
