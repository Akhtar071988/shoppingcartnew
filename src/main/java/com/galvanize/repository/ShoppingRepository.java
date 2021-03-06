package com.galvanize.repository;

import com.galvanize.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    Integer deleteByShopperId(long Id);
}
