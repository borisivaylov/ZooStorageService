package com.example.zoostorageservice.persistance.repository;

import com.example.zoostorageservice.persistance.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    Shipment findShipmentByItemId(UUID itemId);

}
