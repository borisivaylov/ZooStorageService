package com.example.zoostorageservice.Bussiness.operations.getItem;

import com.example.zoostorageservice.Data.Entity.Shipment;
import com.example.zoostorageservice.Data.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetItemService {

    private final ShipmentRepository shipmentRepository;

    public Set<Shipment> getItem(ItemRequest itemRequest){
        Set<Shipment> shipment = shipmentRepository.findShipmentByItemId(itemRequest.getId());
        return shipment;
    }

}
