package com.example.zoostorageservice.Data.Repository;

import com.example.zoostorageservice.Data.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

    Set<Shipment> findShipmentByItemId(UUID itemId);

}
