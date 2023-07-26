package com.example.storageservice.core.changePrice;


import com.example.storageservice.api.Item.changePrice.ChangePriceRequest;
import com.example.storageservice.api.Item.changePrice.ChangePriceResponse;
import com.example.storageservice.api.Item.changePrice.ChangePriceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

@Service
@RequiredArgsConstructor
public class ChangePriceOperation implements ChangePriceService {

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


