package com.example.shoppingcartapiv2.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.example.shoppingcartapiv2.entity.Catalogue;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {

  List<Catalogue> findByNameContaining(@Param("name") String name);

}
