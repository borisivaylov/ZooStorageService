package com.example.zoostorageservice.core.getItem;

import com.example.zoostorageservice.API.Item.item.add.ShipmentRequest;
import com.example.zoostorageservice.API.Item.item.getItem.GetItemService;
import com.example.zoostorageservice.API.Item.item.getItem.ItemResponse;
import com.example.zoostorageservice.persistance.entity.Shipment;
import com.example.zoostorageservice.persistance.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemOperation implements GetItemService {

    private final ShipmentRepository shipmentRepository;

    public ItemResponse process(ShipmentRequest itemRequest){

        Shipment shipment = shipmentRepository.findShipmentByItemId(itemRequest.getId());

        return ItemResponse.builder()
                .id(shipment.getItemId())
                .price(shipment.getPrice())
                .quantity(shipment.getQuantity())
                .build();
    }

}
