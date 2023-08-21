package com.example.storageservice.core.item.getItemByIdReference;


import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceOperation;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceRequest;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetItemByIdReferenceOperationProcessor implements GetItemByIdReferenceOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    @Override
    public GetItemByIdReferenceResponse process(GetItemByIdReferenceRequest getItemByIdReferenceRequest) {

        Shipment shipment = shipmentRepository.getReferenceById(getItemByIdReferenceRequest.getItemId());


        return GetItemByIdReferenceResponse.builder()
                .ItemId(shipment.getItemId())
                .price(shipment.getPrice())
                .quantity(shipment.getQuantity())
                .build();
    }
}
