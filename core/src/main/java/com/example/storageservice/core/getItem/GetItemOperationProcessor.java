package com.example.storageservice.core.getItem;


import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetItemOperationProcessor implements com.example.storageservice.api.Item.getItem.GetItemOperation {

    private final ShipmentRepository shipmentRepository;

    public ItemResponse process(ShipmentRequest itemRequest){

        Shipment shipment = shipmentRepository.findShipmentByItemId(itemRequest.getId())
                .orElseThrow(()-> new NoSuchElementException("No such shipment"));;

        return ItemResponse.builder()
                .id(shipment.getItemId())
                .price(shipment.getPrice())
                .quantity(shipment.getQuantity())
                .build();
    }

}
