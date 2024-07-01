package com.example.shoppingcartapiv2.service;

import org.springframework.stereotype.Component;

import com.example.shoppingcartapiv2.entity.Catalogue;
import com.example.shoppingcartapiv2.repository.CatalogueRepository;

import jakarta.annotation.PostConstruct;

@Component
public class Dataloader {

  private CatalogueRepository catalogueRepository;

  public Dataloader(CatalogueRepository catalogueRepository) {
    this.catalogueRepository = catalogueRepository;
  }

  @PostConstruct
  public void loadData() {
    catalogueRepository.deleteAll();
    System.out.println("Loading data...");
    catalogueRepository.save(new Catalogue("Apple"));
    System.err.println("Done loading data...");
    catalogueRepository.save(new Catalogue("Banana"));
    catalogueRepository.save(new Catalogue("Orange"));
  }
}
