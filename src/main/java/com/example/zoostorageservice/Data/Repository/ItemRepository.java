package com.example.zoostorageservice.Data.Repository;

import com.example.zoostorageservice.Data.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
