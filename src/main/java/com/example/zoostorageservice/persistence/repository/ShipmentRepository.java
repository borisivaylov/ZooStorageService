package com.example.zoostorageservice.persistence.repository;

import com.example.zoostorageservice.persistence.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    Shipment findShipmentByItemId(UUID itemId);

}
