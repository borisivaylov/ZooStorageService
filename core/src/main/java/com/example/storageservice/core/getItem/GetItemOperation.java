package com.example.storageservice.core.getItem;


import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.getItem.GetItemService;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

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