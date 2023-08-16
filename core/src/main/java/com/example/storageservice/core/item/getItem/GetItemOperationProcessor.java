package com.example.storageservice.core.item.getItem;


import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetItemOperationProcessor implements com.example.storageservice.api.Item.getItem.GetItemOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;

    public ItemResponse process(ItemRequest itemRequest){

        Shipment shipment = shipmentRepository.findShipmentByItemId(itemRequest.getId())
                .orElseThrow(()-> new NoSuchElementException("No such shipment"));

        if (onSaleItemRepository.existsById(shipment.getItemId()))
        {
            double onSalePercent = onSaleItemRepository.findById(shipment.getItemId())
                    .orElseThrow(()-> new NoSuchElementException("No such item.")).getDiscount();
            shipment.setPrice(shipment.getPrice() * (1-onSalePercent/100));
        }

        return ItemResponse.builder()
                .id(shipment.getItemId())
                .price(shipment.getPrice())
                .quantity(shipment.getQuantity())
                .build();
    }

}
