package com.example.zoostorageservice.Bussiness.operations.importItem;

import com.example.zoostorageservice.API.Item.ShipmentRequest;
import com.example.zoostorageservice.Data.Entity.Shipment;
import com.example.zoostorageservice.Data.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportItemService {

    private final ShipmentRepository shipmentRepository;

    public Shipment importItem(ImportRequest importRequest){
        Shipment shipment = shipmentRepository.findById(importRequest.getId()).orElseThrow(()->
                new IllegalArgumentException("There is no item with id:"+importRequest.getId()));
                shipment.setQuantity(shipment.getQuantity()+importRequest.getQuantity());
                return shipmentRepository.save(shipment);
    }
}
