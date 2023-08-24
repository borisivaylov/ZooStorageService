package com.example.storageservice.core.item.getItem;


import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.getItem.GetItemOperation;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.core.item.ifonsale.CheckIfOnSaleOperationProcessor;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetItemOperationProcessor implements GetItemOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    private final CheckIfOnSaleOperationProcessor checkIfOnSaleOperationProcessor;

    public ItemResponse process(ItemRequest itemRequest) {

        Shipment shipment = shipmentRepository.findShipmentByItemId(itemRequest.getId())
                .orElseThrow(()-> new NoSuchElementException("No such shipment"));
        boolean onSale = checkIfOnSaleOperationProcessor.process(CheckIfOnSaleInput.builder().itemId(shipment.getItemId()).build()).isOnSale();

        if(onSale)
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
