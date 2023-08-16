package com.example.storageservice.persistence.repository;

import com.example.storageservice.persistence.entity.OnSaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OnSaleItemRepository extends JpaRepository<OnSaleItem, UUID> {
}
