package com.FTTech.FTTech.dataAccess.abstracts;

import com.FTTech.FTTech.entities.concretes.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item,Integer> {
    List<Item> findByExpirationDateBefore(LocalDate date);
}
