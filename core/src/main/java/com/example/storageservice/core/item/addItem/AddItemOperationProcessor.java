package com.example.storageservice.core.item.addItem;

import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.add.ShipmentResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Adds an item if it exists in the store database
@Service
@RequiredArgsConstructor
public class AddItemOperationProcessor implements com.example.storageservice.api.Item.add.AddItemOperation {

    private final ShipmentRepository shipmentRepository;
    private  final ZooStoreRestExport zooStoreRestExport;

    @Override
    public ShipmentResponse process(ShipmentRequest shipmentRequest) {


        zooStoreRestExport.getItemById(shipmentRequest.getId().toString());

        Shipment shipment = Shipment.builder().itemId(shipmentRequest.getId()).
                price(shipmentRequest.getPrice()).
                quantity(shipmentRequest.getQuantity())
                .build();

        shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                                            .shipmentId(shipment.getShipmentId())
                                            .id(shipment.getItemId())
                                            .price(shipment.getPrice())
                                            .quantity(shipment.getQuantity())
                                            .build();
    }
}
