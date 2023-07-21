package com.example.storageservice.persistence.repository;


import com.example.storageservice.persistence.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    Shipment findShipmentByItemId(UUID itemId);

}
