package com.example.storageservice.core.getItemByIdReference;


import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceRequest;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemByIdReference implements com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReference {

    private final ShipmentRepository shipmentRepository;
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
