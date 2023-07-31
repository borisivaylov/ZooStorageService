package com.example.storageservice.core.changePrice;


import com.example.storageservice.api.Item.changePrice.ChangePriceRequest;
import com.example.storageservice.api.Item.changePrice.ChangePriceResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

@Service
@RequiredArgsConstructor
public class ChangePriceOperationProcessor implements com.example.storageservice.api.Item.changePrice.ChangePriceOperation {

    private final ShipmentRepository shipmentRepository;

   public ChangePriceResponse process(ChangePriceRequest changePriceRequest){
            Shipment shipment = shipmentRepository.findShipmentByItemId(changePriceRequest.getId());
            shipment.setPrice(changePriceRequest.getPrice());

            ChangePriceResponse changePriceResponse = ChangePriceResponse.builder()
                    .id(changePriceRequest.getId())
                    .price(changePriceRequest.getPrice())
                    .build();

                    shipmentRepository.save(shipment);

            return changePriceResponse;
    }

}


