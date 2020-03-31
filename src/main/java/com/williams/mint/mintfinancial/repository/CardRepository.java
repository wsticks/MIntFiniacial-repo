package com.williams.mint.mintfinancial.repository;

import com.williams.mint.mintfinancial.model.entity.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {

   List<Card> findAll();
   Card findByBin (String bin);
}
