package com.elmaghraby.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elmaghraby.app.entities.PriceTable;

@Repository
public interface PriceTableRepository extends JpaRepository<PriceTable, Long> {

}
