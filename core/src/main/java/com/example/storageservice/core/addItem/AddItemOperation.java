package com.example.storageservice.core.addItem;


import com.example.storageservice.api.Item.add.AddItemService;
import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.add.ShipmentResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

@Service
@RequiredArgsConstructor
public class AddItemOperation implements AddItemService {

    private final ShipmentRepository shipmentRepository;


    @Override
    public ShipmentResponse process(ShipmentRequest shipmentRequest)
    {
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
